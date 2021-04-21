package com.spring.practice.data.request;

import lombok.Getter;

@Getter
public class SchoolRequest {
    private final String name;
    private final String address;
    private final String phoneNumber;

    public SchoolRequest(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
