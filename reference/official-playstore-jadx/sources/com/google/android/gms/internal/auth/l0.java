package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class l0 implements ProxyApi.SpatulaHeaderResult {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Status f13059i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f13060l;

    public l0(Status status) {
        com.google.android.gms.common.internal.t.i(status);
        this.f13059i = status;
        this.f13060l = "";
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi.SpatulaHeaderResult
    public final String getSpatulaHeader() {
        return this.f13060l;
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi.SpatulaHeaderResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.f13059i;
    }

    public l0(String str) {
        com.google.android.gms.common.internal.t.i(str);
        this.f13060l = str;
        this.f13059i = Status.f12670o;
    }
}
