package com.indigo.hotgear.network;

import com.android.volley.Request;

public enum Method {
    GET_SHOTS(Request.Method.GET, "/shots");
    private int requestMethod;
    private String url;

    Method(int requestMethod, String url) {
        this.requestMethod = requestMethod;
        this.url = url;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public String getUrl() {
        return url;
    }
}
