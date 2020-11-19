package com.squarecheck.shared.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squarecheck.base.model.BaseModel;

public class APIResponseCollection<T extends BaseModel> {
    @SerializedName("data")
    @Expose
    private T data = null;
    @SerializedName("links")
    @Expose
    private Link links;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
