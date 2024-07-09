package com.hyf.test.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Dhotcode.sysout.before=true -Dhotcode.log.console=true -Dhotcode.log.level=DEBUG -Dhotcode.dump=true -Dhotcode.base=E:\study\code\idea4\test\target\classes -javaagent:E:\study\code\idea4\source\play\ali\hotcode2\hotcode2.core\target\hotcode2.jar
 *
 * @author baB_hyf
 * @date 2023/04/21
 */
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
