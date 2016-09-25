package com.github.fidelity.lio.lojista.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import rx.Observable;

public class Order implements Serializable {
    private String id = null;
    private String number = null;
    private String reference = null;
    private String status = null;
    @SerializedName("created_at")
    private String createdAt = null;
    @SerializedName("updated_at")
    private String updatedAt = null;
    private List<OrderItem> items = null;
    private String notes = null;
    private List<Transaction> transactions = null;
    private Integer price = null;
    private Integer remaining = null;

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getReference() {
        return reference;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getNotes() {
        return notes;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getRemaining() {
        Observable.from(transactions)
                .map(Transaction::getAmount)
                .reduce(price, (a, b) -> a - b.intValue())
                .subscribe(r -> remaining = r);

        return remaining >= 0 ? remaining : 0;
    }
}
