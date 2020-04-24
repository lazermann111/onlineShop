package com.skillsup.model;

public enum Gender {
    MALE, FEMALE, UNKNOWN;

    public static Gender fromCode(String code) {
        return "M".equalsIgnoreCase(code) ? MALE : // M m
                "F".equalsIgnoreCase(code) ? FEMALE // F f
                        : null;
    }
//    public static Gender fromString(String code) {
//        return (MALE.equals(code) ? MALE : (FEMALE.equals(code) ? FEMALE: UNKNOWN));
//    }
}
