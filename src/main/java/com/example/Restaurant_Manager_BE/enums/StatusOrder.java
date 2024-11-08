package com.example.Restaurant_Manager_BE.enums;

public enum StatusOrder {
    RECEIVED(1, "Đã tiếp nhận order"),
    PREPARED(2, "Đã chế biến"),
    SERVED(3, "Đã phục vụ");

    private long id;
    private String description;

    StatusOrder(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }
}
