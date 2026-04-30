package com.komal.komal.dto;

public class DeadlockResponse {
    public boolean deadlock;
    public String message;

    public DeadlockResponse(boolean deadlock, String message) {
        this.deadlock = deadlock;
        this.message = message;
    }
}