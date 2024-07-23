import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.yami.shop.api.ApiApplication;
import com.yami.shop.security.common.manager.PasswordManager;

import cn.hutool.crypto.symmetric.AES;
import jakarta.annotation.Resource;

/**
 * @Author：T3yes
 * @Package：PACKAGE_NAME
 * @Project：mall4j
 * @name：Test
 * @Date：2024/7/23 07:19
 * @Filename：Test
 * @Description:
 */
@SpringBootTest(classes = ApiApplication.class)
public class Tests {

    @Value("${auth.password.signKey:-mall4j-password}")
    public String passwordSignKey;
    @Resource
    private PasswordManager passwordManager;

    @Test
    void encode() {
        AES aes = new AES(passwordSignKey.getBytes(StandardCharsets.UTF_8));
        byte[] encrypt = aes.encrypt("123321");
        String s = new String(encrypt);
        System.out.println(s);
    }

}