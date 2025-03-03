package com.example.Restaurant_Manager_BE.enums;

public enum StatusOrder {
    RECEIVED("Đã tiếp nhận"),
    PREPARED("Đã chế biến"),
    SERVED("Đã phục vụ"),;


    private String desc;

    public String getDesc() {
        return desc;
    }

    StatusOrder(String desc) {
        this.desc = desc;
    }
}
