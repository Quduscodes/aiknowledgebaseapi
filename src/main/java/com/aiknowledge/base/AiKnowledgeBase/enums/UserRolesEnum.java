package com.aiknowledge.base.AiKnowledgeBase.enums;


public enum UserRolesEnum {
    USER("USER"),
    ADMIN("ADMIN");

    private final String stringValue;

    UserRolesEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
