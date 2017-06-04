package com.example.maks.discoduck.api.response;


class AbstractResponse <T>{
    private Message<T> message;

    Message<T> getMessage() {
        return message;
    }
}
