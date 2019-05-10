package cn.echocow.gendemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * lombok 自动生成必要的方法
 * ConfigurationProperties 和配置文件前缀进行对应
 *
 * @author echocow
 */
@Data
@Component
@ConfigurationProperties(prefix = "application.generate")
public class GenConfig {
    /**
     * 数据库驱动类
     */
    private String driverClass;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 库名
     */
    private String catalog;

    /**
     * 链接地址
     */
    private String url;

}
