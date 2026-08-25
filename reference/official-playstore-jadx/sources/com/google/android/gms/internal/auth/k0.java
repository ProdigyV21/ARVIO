package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class k0 implements ProxyApi.ProxyResult {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Status f13053i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ProxyResponse f13054l;

    public k0(Status status) {
        this.f13053i = status;
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult
    public final ProxyResponse getResponse() {
        return this.f13054l;
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.f13053i;
    }

    public k0(ProxyResponse proxyResponse) {
        this.f13054l = proxyResponse;
        this.f13053i = Status.f12670o;
    }
}
