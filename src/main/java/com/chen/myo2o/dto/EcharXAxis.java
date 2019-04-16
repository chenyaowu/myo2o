package com.chen.myo2o.dto;

import java.util.HashSet;

/**
 * 迎合echar里的xAxis项
 */
public class EcharXAxis {
    private String type= "category";
    //为了去重
    private HashSet<String> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashSet<String> getData() {
        return data;
    }

    public void setData(HashSet<String> data) {
        this.data = data;
    }
}
