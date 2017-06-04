package com.example.maks.discoduck.api.response;


class Message<T> {
    private MessageHeader header;
    private T body;

    T getBody() {
        return body;
    }
}
