package com.github.fidelity.lio.lojista.domain;

import com.google.gson.annotations.SerializedName;

public class Checkout {
    @SerializedName("merchant_id")
    private String merchantId;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("points_quantity")
    private String pointsQuantity;
    private String amount;
    @SerializedName("fidelity_channel")
    private String fidelityChannel;
    @SerializedName("phone_number")
    private String phoneNumber;

    public Checkout(String merchantId, String orderId, String pointsQuantity, String amount,
                    String fidelityChannel, String phoneNumber) {
        this.merchantId = merchantId;
        this.orderId = orderId;
        this.pointsQuantity = pointsQuantity;
        this.amount = amount;
        this.fidelityChannel = fidelityChannel;
        this.phoneNumber = phoneNumber;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPointsQuantity() {
        return pointsQuantity;
    }

    public void setPointsQuantity(String pointsQuantity) {
        this.pointsQuantity = pointsQuantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFidelityChannel() {
        return fidelityChannel;
    }

    public void setFidelityChannel(String fidelityChannel) {
        this.fidelityChannel = fidelityChannel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
