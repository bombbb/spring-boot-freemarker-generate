package cn.echocow.gendemo;

import cn.echocow.gendemo.config.GenConfig;
import cn.echocow.gendemo.util.GenDatabaseUtil;
import cn.echocow.gendemo.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成类
 * 运行以后将会创建 entity 目录并生成，但是由于没有导入 jpa 依赖，会报错
 * 暂时不用管
 *
 * @author echo
 */
@Slf4j
@Component
public class GenEntity implements ApplicationRunner {

    private final GenDatabaseUtil genDatabaseUtil;
    private final FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;

    public GenEntity(GenDatabaseUtil genDatabaseUtil, FreeMarkerConfigurationFactory freeMarkerConfigurationFactory) {
        this.genDatabaseUtil = genDatabaseUtil;
        this.freeMarkerConfigurationFactory = freeMarkerConfigurationFactory;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();
        Template entityTemplate = configuration.getTemplate("entity.ftl");
        List<String> tables = genDatabaseUtil.getTables();
        Map<String, Object> data = new HashMap<>(4);
        data.put("package_name", "cn.echocow.generate.entity");
        FileWriter fileWriter;
        for (String table : tables) {
            String entityClassName = GenUtil.underlineToHump(table, true);
            data.put("table_name", table);
            data.put("class_name", entityClassName);
            data.put("columns", genDatabaseUtil.getColumns(table));
            File file = new File("src/main/java/cn/echocow/gendemo/entity/" + GenUtil.underlineToHump(table, true) + GenUtil.SUFFIX);
            if (!file.exists()) {
                if (!new File("src/main/java/cn/echocow/gendemo/entity").mkdirs()) {
                    log.error("创建文件夹失败");
                    return;
                }
                if (!file.createNewFile()) {
                    log.error("{} 创建文件失败", table);
                    return;
                }
            }
            fileWriter = new FileWriter(file);
            entityTemplate.process(data, fileWriter);
            log.info("Table {} generate succeed!", table);
        }
    }
}
