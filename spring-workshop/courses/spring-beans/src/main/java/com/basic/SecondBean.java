package com.basic;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SecondBean {
    private FirstBean firstBean;

    public void printText() {
        log.info(firstBean.getText());
    }
}
