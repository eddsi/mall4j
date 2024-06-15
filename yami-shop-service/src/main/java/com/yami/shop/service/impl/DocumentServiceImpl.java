package com.yami.shop.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.CreateDocumentDTO;
import com.yami.shop.bean.dto.DocumentSearchDTO;
import com.yami.shop.bean.model.Document;
import com.yami.shop.bean.model.UserCollectDocument;
import com.yami.shop.bean.model.UserDocument;
import com.yami.shop.bean.vo.document.DocumentPageVO;
import com.yami.shop.common.util.MinioUtils;
import com.yami.shop.dao.DocumentMapper;
import com.yami.shop.service.DocumentService;
import com.yami.shop.service.UserCollectDocumentService;
import com.yami.shop.service.UserDocumentService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import io.netty.channel.DefaultEventLoopGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service.impl
 * @Project：mall4j
 * @name：DocumentServiceImpl
 * @Date：2024/6/9 23:42
 * @Filename：DocumentServiceImpl
 * @Description:
 */
@Slf4j
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

    @Autowired
    private MinioUtils minioUtils;
    private DefaultEventLoopGroup defaultEventExecutor = new DefaultEventLoopGroup(2);
    @Autowired
    private UserDocumentService userDocumentService;
    @Autowired
    private UserCollectDocumentService collectDocumentService;

    @Override
    public void createDocument(CreateDocumentDTO createDocumentDTO, String userId) {
        Document document = new Document();
        BeanUtil.copyProperties(createDocumentDTO, document);
        document.setCreator(userId);
        document.setCreateTime(LocalDate.now());
        document.setUpdateTime(LocalDate.now());
        document.setStatus(0);
        // 异步处理,因为pdf转图片比较耗时
        defaultEventExecutor.submit(() -> {
                    log.info("开始创建...");
                    byte[] objectData = minioUtils.getObjectData(createDocumentDTO.getKey());
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectData);
                    List<String> pdfKeys = getPdfKeys(byteArrayInputStream);
                    document.setDocumentPhotos(JSONUtil.toJsonStr(pdfKeys));
                }
        ).addListener(future -> {
            if (future.isSuccess()) {
                save(document);
            }
        });
    }

    @Override
    public IPage<DocumentPageVO> getDocumentPage(DocumentSearchDTO documentSearchDTO, String userId) {
        //查出自己购买的文档
        List<UserDocument> list = userDocumentService.lambdaQuery().eq(UserDocument::getUserId, userId).list();
        Set<Long> buys;
        if (CollectionUtils.isNotEmpty(list)) {
            buys = list.stream().map(UserDocument::getDocumentId).collect(Collectors.toSet());
        } else {
            buys = Collections.emptySet();
        }
        //查出自己收藏的文档
        List<UserCollectDocument> collectList =
                collectDocumentService.lambdaQuery().eq(UserCollectDocument::getUserId, userId).list();
        Set<Long> collects;
        if (CollectionUtils.isNotEmpty(collectList)) {
            collects = collectList.stream().map(UserCollectDocument::getDocumentId).collect(Collectors.toSet());
        } else {
            collects = Collections.emptySet();
        }
        Page<Document> page =
                lambdaQuery().like(StringUtils.isNotBlank(documentSearchDTO.getKeyword()), Document::getKeyword,
                                documentSearchDTO.getKeyword())
                        .like(StringUtils.isNotBlank(documentSearchDTO.getDocumentName()), Document::getName,
                                documentSearchDTO.getDocumentName())
                        .eq(documentSearchDTO.getType() != null, Document::getType, documentSearchDTO.getType())
                        .eq(Document::getStatus, 1)
                        .orderByDesc(Document::getCreateTime)
                        .page(new Page<>(documentSearchDTO.getCurrent(), documentSearchDTO.getSize()));
        return page.convert(document -> {
            DocumentPageVO documentPageVO = BeanUtil.toBean(document, DocumentPageVO.class);
            documentPageVO.setIsBuy(buys.contains(document.getId()));
            documentPageVO.setIsCollect(collects.contains(document.getId()));
            return documentPageVO;
        });
    }

    @Override
    public void collectDocument(Long documentId, String userId) {
        UserCollectDocument one =
                collectDocumentService.lambdaQuery().eq(UserCollectDocument::getDocumentId, documentId)
                        .eq(UserCollectDocument::getUserId, userId).one();
        if (one == null) {
            collectDocumentService.save(new UserCollectDocument().setDocumentId(documentId).setUserId(userId));
        } else {
            collectDocumentService.removeById(one.getId());
        }
    }

    @Override
    public String upload(MultipartFile file) {
        return minioUtils.uploadMultipartFileDown(file);
    }

    @SneakyThrows
    public List<String> getPdfKeys(InputStream inputStream) {
        List<String> pdfs = new ArrayList<>();
        PDDocument document = PDDocument.load(inputStream);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        int numberOfPages = document.getNumberOfPages();
        for (int i = 0; i < numberOfPages; ++i) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bim, "JPEG", os);
            byte[] imageBytes = os.toByteArray();
            InputStream in = new ByteArrayInputStream(imageBytes);
            String upload = minioUtils.upload(in, RandomUtil.randomString(6) + "pdf/" + i + ".jpg", os.size());
            pdfs.add(upload);
            log.info("pdf转图片成功:{}", upload);
        }
        document.close();
        return pdfs;
    }
}