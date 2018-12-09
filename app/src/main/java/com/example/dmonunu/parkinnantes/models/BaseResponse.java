package com.example.dmonunu.parkinnantes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BaseResponse<T> implements Serializable {

    @SerializedName("nhits")
    @Expose
    private int nhits;

    @SerializedName("parameters")
    @Expose
    private Parameters parameters;

    @SerializedName("records")
    @Expose
    List<Record<T>> records;


    public int getNhits() {
        return nhits;
    }

    public void setNhits(int nhits) {
        this.nhits = nhits;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public List<Record<T>> getRecords() {
        return records;
    }

    public void setRecords(List<Record<T>> records) {
        this.records = records;
    }
}
