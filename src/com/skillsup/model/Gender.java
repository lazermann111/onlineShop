package com.skillsup.model;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromCode(String code) {
        return "M".equalsIgnoreCase(code) ? MALE : // M m
                "F".equalsIgnoreCase(code) ? FEMALE // F f
                        : null;
    }
}
