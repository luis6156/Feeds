package com.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

//        FirstBean firstBean = (FirstBean) context.getBean(FirstBean.class);
        FirstBean firstBean = (FirstBean) context.getBean("another");
        log.info(firstBean.getText());

        SecondBean secondBean = context.getBean(SecondBean.class);
        secondBean.printText();
    }
}
