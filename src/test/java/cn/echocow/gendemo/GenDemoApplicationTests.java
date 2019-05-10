package cn.echocow.gendemo;

import cn.echocow.gendemo.config.GenConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenDemoApplicationTests {

    @Autowired
    private GenConfig genConfig;

    @Test
    public void contextLoads() {
        assertNotNull(genConfig);
        assertEquals(genConfig.getUsername(), "root");
    }

}
