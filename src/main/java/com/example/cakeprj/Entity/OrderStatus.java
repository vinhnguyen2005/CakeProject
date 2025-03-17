package com.example.cakeprj.Entity;

public enum OrderStatus {
    CHO_XAC_NHAN("Chờ xác nhận"),
    XAC_NHAN("Xác Nhận"),
    DANG_GIAO("Đang giao"),
    DA_GIAO("Đã giao"),
    DA_HUY("Đã hủy");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
