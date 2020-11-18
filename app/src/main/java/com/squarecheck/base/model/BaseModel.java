package com.squarecheck.base.model;

import java.util.List;

public class BaseModel<T, U, V> {
    private List<T> data;
    private U links;
    private V meta;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public U getLinks() {
        return links;
    }

    public void setLinks(U links) {
        this.links = links;
    }

    public V getMeta() {
        return meta;
    }

    public void setMeta(V meta) {
        this.meta = meta;
    }
}
