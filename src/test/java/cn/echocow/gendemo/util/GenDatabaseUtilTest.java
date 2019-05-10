package cn.echocow.gendemo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenDatabaseUtilTest {
    @Autowired
    private GenDatabaseUtil genDatabaseUtil;

    @Test
    public void testTables() {
        genDatabaseUtil.getTables().forEach(System.out::println);
    }

    @Test
    public void testColumns() {
        genDatabaseUtil.getColumns("user").forEach(System.out::println);
    }
}