package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 extends com.google.android.gms.common.api.n implements ProxyClient, com.google.android.gms.internal.cast.q3 {
    @Override // com.google.android.gms.auth.api.proxy.ProxyClient
    public com.google.android.gms.tasks.h getSpatulaHeader() {
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12807a = new m1();
        xVarBuilder.f12810d = 1520;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyClient
    public com.google.android.gms.tasks.h performProxyRequest(ProxyRequest proxyRequest) {
        com.google.android.gms.common.api.internal.x xVarBuilder = com.google.android.gms.common.api.internal.y.builder();
        xVarBuilder.f12807a = new p2(this, proxyRequest, 1);
        xVarBuilder.f12810d = 1518;
        return doWrite(xVarBuilder.a());
    }
}
