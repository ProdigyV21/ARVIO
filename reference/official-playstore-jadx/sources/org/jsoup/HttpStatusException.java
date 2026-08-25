package org.jsoup;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class HttpStatusException extends IOException {
    private final int statusCode;
    private final String url;

    public HttpStatusException(String str, int i10, String str2) {
        super(str + ". Status=" + i10 + ", URL=[" + str2 + "]");
        this.statusCode = i10;
        this.url = str2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }
}
