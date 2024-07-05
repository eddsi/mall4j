package com.yami.shop.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yami.shop.bean.dto.sector.CreateSectorDTO;
import com.yami.shop.bean.model.Sector;
import com.yami.shop.service.SectorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.admin.controller
 * @Project：mall4j
 * @name：SectorController
 * @Date：2024/7/6 01:40
 * @Filename：SectorController
 * @Description:
 */
@RestController
@RequestMapping("/shop/sector")
public class SectorController {

    @Resource
    private SectorService sectorService;

    @Operation(summary = "添加行业", description = "添加行业")
    @PostMapping
    public ResponseEntity<String> addSector(@RequestBody CreateSectorDTO sectorDTO) {
        Sector sector = new Sector();
        sector.setName(sectorDTO.getName());
        sectorService.save(sector);
        return ResponseEntity.ok("添加成功");
    }

    @Operation(summary = "删除行业", description = "删除行业")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSector(Long id) {
        sectorService.removeById(id);
        return ResponseEntity.ok("删除成功");
    }

    @Operation(summary = "修改行业", description = "修改行业")
    @PostMapping("/update")
    public ResponseEntity<String> updateSector(@RequestBody Sector sector) {
        sectorService.updateById(sector);
        return ResponseEntity.ok("修改成功");
    }

    @Operation(summary = "查询行业", description = "查询行业")
    @GetMapping("/query")
    public ResponseEntity<List<Sector>> querySector() {
        return ResponseEntity.ok(sectorService.list());
    }
}