package com.algosec.point;

import com.algosec.point.config.XPointApplicationConfig;
import org.springframework.boot.SpringApplication;

/**
 * Created by Alexander on 09.12.2017.
 */
public class XPointApplication {
    private XPointApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(XPointApplicationConfig.class, args);
    }
}
