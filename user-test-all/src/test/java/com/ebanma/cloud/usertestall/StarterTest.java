package com.ebanma.cloud.usertestall;

import com.ebanma.cloud.annotation.EnableRegisterServer;
import com.ebanma.cloud.bean.SimpleBean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableRegisterServer
public class StarterTest {
    @Autowired
    private SimpleBean simpleBean;

    @Test
    public void startTest() {
        System.out.println(simpleBean);
    }
}
