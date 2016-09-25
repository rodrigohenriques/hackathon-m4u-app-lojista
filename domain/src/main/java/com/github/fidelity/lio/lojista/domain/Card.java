package com.github.fidelity.lio.lojista.domain;

import java.io.Serializable;

public class Card implements Serializable {
    private String brand = null;
    private Integer bin = null;
    private Integer last = null;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBin() {
        return bin;
    }

    public void setBin(Integer bin) {
        this.bin = bin;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }
}
