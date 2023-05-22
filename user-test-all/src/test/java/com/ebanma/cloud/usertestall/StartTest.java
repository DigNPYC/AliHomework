package com.ebanma.cloud.usertestall;

import com.ebanma.cloud.domain.SimpleBean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 肖露
 * @version $ Id: StartTest, v 0.1 2023/04/12 19:56 banma-0241 Exp $
 */
@SpringBootTest
public class StartTest {
    @Autowired
    private SimpleBean simpleBean;

    @Test
    public void myStartTest() {
        System.out.println(simpleBean);
    }

}
