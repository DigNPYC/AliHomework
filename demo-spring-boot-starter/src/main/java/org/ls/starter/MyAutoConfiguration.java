package org.ls.starter;
/**
 * @author banma-0148
 * @date 2023/04/14
 */

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李帅
 * @version $ Id: MyAutoConfiguration, v 0.1 2023/04/14 9:35 banma-0148 Exp $
 */
@Configuration
@ConditionalOnBean(ConfigMarker.class)
public class MyAutoConfiguration {

    static {
        System.out.println("MyAutoConfiguration init....");
    }

    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }
}