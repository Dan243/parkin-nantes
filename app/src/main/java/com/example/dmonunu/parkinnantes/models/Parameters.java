package com.example.dmonunu.parkinnantes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Parameters implements Serializable {

    @SerializedName("dataset")
    @Expose
    private List<String> dataset;

    @SerializedName("timezone")
    @Expose
    private String timezone;

    @SerializedName("rows")
    @Expose
    private String rows;


}
