package com.aiknowledge.base.AiKnowledgeBase.exception;


public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
