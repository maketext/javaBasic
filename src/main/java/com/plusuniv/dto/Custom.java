package com.plusuniv.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties("custom")
@Component
public class Custom
{
    List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }
}