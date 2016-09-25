package com.github.fidelity.lio.lojista.domain;

import com.google.gson.annotations.SerializedName;

public class Transaction {
    private String id = null;
    @SerializedName("transaction_type")
    private String transactionType = null;
    private String status = null;
    private String description = null;
    @SerializedName("terminal_number")
    private Integer terminalNumber = null;
    private Card card = null;
    private Integer number = null;
    @SerializedName("authorization_code")
    private Integer authorizationCode = null;
    @SerializedName("payment_product")
    private PaymentProduct paymentProduct = null;
    private String amount = null;
    @SerializedName("created_at")
    private String createdAt = null;
    @SerializedName("updated_at")
    private String updatedAt = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(Integer terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(Integer authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public PaymentProduct getPaymentProduct() {
        return paymentProduct;
    }

    public void setPaymentProduct(PaymentProduct paymentProduct) {
        this.paymentProduct = paymentProduct;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
