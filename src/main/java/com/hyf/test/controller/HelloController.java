package com.hyf.test.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * curl http://localhost:8083/test/1
 *
 * @author baB_hyf
 * @date 2021/12/12
 */
@RestController
@RequestMapping("test")
public class HelloController implements Serializable {

    @Resource
    private ApplicationContext context;

    @RequestMapping("1")
    public boolean modifySelf() {
        System.out.println(Arrays.toString(getClass().getInterfaces()));
        // System.out.println(1);
        // aaa();
        // Invoker.aaa();
        // superExist();
        // superProtectMethod();
        return true;
    }

    // public void aaa() {
    //     System.out.println(1);
    // }

    @RequestMapping("2")
    public boolean loadOuterClass() {
        try {
            // see resource directory
            ClassUtils.forName("com.hyf.hotrefresh.hello.Test", null);
            return true;
        } catch (Throwable e) {
        }

        return false;
    }

    @RequestMapping("3")
    public boolean invokeOuterClass() {
        try {
            Class<?> clazz = ClassUtils.forName("com.hyf.hotrefresh.hello.Test", null);
            Method getMethod = clazz.getMethod("get");
            return (boolean) getMethod.invoke(null);
        } catch (Throwable e) {
        }

        return false;
    }

    @RequestMapping("4")
    public String compileParameters(String param) {
        return "4";
    }

    @RequestMapping("5")
    public boolean modifyStaticMethod() {
        return HelloController.staticMethod();
    }

    public static boolean staticMethod() {
        return false;
    }
}
