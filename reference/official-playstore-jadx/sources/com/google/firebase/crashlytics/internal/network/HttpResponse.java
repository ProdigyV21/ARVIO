package com.google.firebase.crashlytics.internal.network;

/* JADX INFO: loaded from: classes4.dex */
public class HttpResponse {
    private final String body;
    private final int code;

    public HttpResponse(int i10, String str) {
        this.code = i10;
        this.body = str;
    }

    public String body() {
        return this.body;
    }

    public int code() {
        return this.code;
    }
}
