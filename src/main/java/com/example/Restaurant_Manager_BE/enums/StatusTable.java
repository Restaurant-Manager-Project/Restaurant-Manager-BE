package com.example.Restaurant_Manager_BE.enums;

public enum StatusTable {
    AVAILABLE("Còn trống"),
    OCCUPIED("Đang sử dụng"),
    RESERVED("Đã đặt trước");

    private String desc;

    StatusTable(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
