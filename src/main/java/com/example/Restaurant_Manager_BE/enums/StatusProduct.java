package com.example.Restaurant_Manager_BE.enums;

public enum StatusProduct {
    AVAILABLE("Còn hàng"),
    OUT_OF_STOCK("Hết hàng");

    private String desc;

    StatusProduct(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
