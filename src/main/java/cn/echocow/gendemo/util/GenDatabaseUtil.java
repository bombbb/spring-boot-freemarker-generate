package cn.echocow.gendemo.util;

import cn.echocow.gendemo.config.GenConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 *
 * @author echo
 */
@Slf4j
@Component
public class GenDatabaseUtil {
    private final GenConfig genConfig;

    public GenDatabaseUtil(GenConfig genConfig) {
        this.genConfig = genConfig;
    }

    /**
     * 获取数据库元数据
     *
     * @return 元数据
     * @throws Exception 异常
     */
    private DatabaseMetaData getMetaData() throws Exception {
        Class.forName(genConfig.getDriverClass());
        return DriverManager.getConnection(genConfig.getUrl(),
                genConfig.getUsername(), genConfig.getPassword()).getMetaData();
    }

    /**
     * 获取库的所有表
     *
     * @return 所有表
     */
    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            ResultSet resultSet = getMetaData().getTables(genConfig.getCatalog(), null,
                    "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tables.add(tableName);
            }
            resultSet.close();
        } catch (Exception e) {
            log.error("Please check your database conf! {}", e.getMessage());
            e.printStackTrace();
        }
        return tables;
    }

    /**
     * 获取指定表的所有列
     *
     * @param tableName 表名
     * @return 所有列的集合
     */
    public List<ColumnClass> getColumns(String tableName) {
        try (ResultSet resultSet = getMetaData().getColumns(genConfig.getCatalog(), null, tableName, "%")) {
            return getColumns(resultSet, tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某列的结果集抽取
     *
     * @param resultSet 结果集
     * @param tableName 表名
     * @throws SQLException 异常
     */
    private List<ColumnClass> getColumns(ResultSet resultSet, String tableName) throws SQLException {
        List<ColumnClass> columns = new ArrayList<>();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            String remarks = resultSet.getString("REMARKS");
            Boolean nullAble = resultSet.getInt("NULLABLE") == 1;
            columns.add(new ColumnClass(
                    tableName,
                    GenUtil.underlineToHump(columnName),
                    resultSet.getInt("COLUMN_SIZE"),
                    GenUtil.fieldConversion(resultSet.getString("TYPE_NAME")),
                    remarks, nullAble
            ));
        }
        return columns;
    }
}
