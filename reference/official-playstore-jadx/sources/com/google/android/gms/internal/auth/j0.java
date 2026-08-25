package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 implements ProxyApi {
    @Override // com.google.android.gms.auth.api.proxy.ProxyApi
    public final com.google.android.gms.common.api.v getSpatulaHeader(com.google.android.gms.common.api.q qVar) {
        com.google.android.gms.common.internal.t.i(qVar);
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new i0(AuthProxy.API, qVar));
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi
    public final com.google.android.gms.common.api.v performProxyRequest(com.google.android.gms.common.api.q qVar, ProxyRequest proxyRequest) {
        com.google.android.gms.common.internal.t.i(qVar);
        com.google.android.gms.common.internal.t.i(proxyRequest);
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new g0(qVar, proxyRequest));
    }
}
