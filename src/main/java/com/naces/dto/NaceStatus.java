package com.naces.dto;

public enum NaceStatus {
    NOT_FOUND("No Record Found against order id");
    public final String code;
    NaceStatus(String code) {
        this.code = code;
    }
}
