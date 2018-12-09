package com.example.dmonunu.parkinnantes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Record<T> implements Serializable {
    @SerializedName("datasetid")
    @Expose
    private String datasetId;

    @SerializedName("recordid")
    @Expose
    private String recordId;

    @SerializedName("fields")
    @Expose
    private T fields;

    @SerializedName("record_timestamp")
    @Expose
    private String recordTimestamp;

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public T getFields() {
        return fields;
    }

    public void setFields(T fields) {
        this.fields = fields;
    }

    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }
}
