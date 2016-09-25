package com.github.fidelity.lio.lojista.domain;

public class Checkout {
    private String merchant;
    private String merchantId;
    private String accessToken;
    private String clientId;
    private String orderId;
    private Long points;
    private Long amount;
    private String fidelityChannel;
    private String phone;

    public Checkout(String merchantId, String accessToken, String clientId, String merchant,
                    String orderId, Long points, Long amount, String fidelityChannel,
                    String phone) {
        this.merchant = merchant;
        this.merchantId = merchantId;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.orderId = orderId;
        this.points = points;
        this.amount = amount;
        this.fidelityChannel = fidelityChannel;
        this.phone = phone;
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

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getFidelityChannel() {
        return fidelityChannel;
    }

    public void setFidelityChannel(String fidelityChannel) {
        this.fidelityChannel = fidelityChannel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
