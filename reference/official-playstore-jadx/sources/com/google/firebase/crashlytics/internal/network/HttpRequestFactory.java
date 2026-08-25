package com.google.firebase.crashlytics.internal.network;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class HttpRequestFactory {
    public HttpGetRequest buildHttpGetRequest(String str) {
        return buildHttpGetRequest(str, Collections.EMPTY_MAP);
    }

    public HttpGetRequest buildHttpGetRequest(String str, Map<String, String> map) {
        return new HttpGetRequest(str, map);
    }
}
