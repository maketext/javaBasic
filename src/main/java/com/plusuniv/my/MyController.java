package com.plusuniv.my;

import com.plusuniv.ChangUtils;
import com.plusuniv.dto.Custom;
import com.plusuniv.dto.MyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

@RestController
@ConfigurationProperties("custom")
class MyController {

    @Autowired
    Custom custom;

    @Autowired
    ChangUtils utils;

    @Autowired
    MyRepository myRepository;

    @GetMapping("/")
    String home() {
        return custom.getList().toString();
    }

    @GetMapping("/추가")
    String insert() {
        return String.format("%d 개의 row(s) 생성.", myRepository.save(new MyTable(1, 2)));
    }
    @GetMapping("/조회")
    String select() {
        if(myRepository.read() == null)
            return "데이터가 없습니다.";
        return myRepository.read().toString();
    }

}