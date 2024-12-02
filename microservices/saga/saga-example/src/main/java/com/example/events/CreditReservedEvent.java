package com.example.events;

public class CreditReservedEvent {
    public String orderId;
    public boolean success;

    public CreditReservedEvent(String orderId, boolean success) {
        this.orderId = orderId;
        this.success = success;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CreditReservedEvent() {
    }
    // Constructor, Getters, Setters
}

