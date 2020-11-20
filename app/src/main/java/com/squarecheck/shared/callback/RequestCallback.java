package com.squarecheck.shared.callback;

public interface RequestCallback<T> {
    void requestSuccess(T data);

    void requestError(String message);
}
