package com.example.maks.discoduck.utils;

public interface DataListener<T> {
    void onSuccess(T data);

    void onError();
}
