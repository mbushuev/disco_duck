package com.example.maks.discoduck.api.response;


import com.google.gson.annotations.SerializedName;

class MessageHeader {
    @SerializedName("status_code")
    int code;

    @SerializedName("execute_time")
    double executeTime;
}
