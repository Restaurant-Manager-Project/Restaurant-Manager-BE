package com.example.Restaurant_Manager_BE.enums;

public enum StatusTable {
    AVAILABLE(1, "Bàn trống"),
    OCCUPIED(2, "Đang sử dụng"),
    RESERVED(3, "Đã đặt trước");

    private long id;
    private String description;

    StatusTable(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }
}
