package cn.echocow.gendemo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 列对应
 *
 * @author echo cow
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnClass {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列大小
     */
    private Integer columnSize;
    /**
     * 列的类型
     */
    private String columnType;
    /**
     * 列的注释
     */
    private String columnComment;
    /**
     * 是否能为空值
     */
    private Boolean nullAble;
}
