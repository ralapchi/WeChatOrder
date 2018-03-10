package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by qidd on 2018-3-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        String name = "qidd";
        String password = "qiddddd";
        logger.debug("debug...");
        logger.info("info....");
        logger.error("error...");
        logger.info("name: {} , password :{}", name, password);
        logger.warn("warn........");
    }

}
