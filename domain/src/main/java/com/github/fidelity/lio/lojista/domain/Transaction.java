package com.github.fidelity.lio.lojista.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaction implements Serializable {
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
    private Long amount = null;
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

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTerminalNumber() {
        return terminalNumber;
    }

    public Card getCard() {
        return card;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getAuthorizationCode() {
        return authorizationCode;
    }

    public PaymentProduct getPaymentProduct() {
        return paymentProduct;
    }

    public Long getAmount() {
        return amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
