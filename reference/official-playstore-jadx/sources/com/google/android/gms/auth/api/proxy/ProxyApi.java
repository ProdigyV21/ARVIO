package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
public interface ProxyApi {

    public interface ProxyResult extends x {
        ProxyResponse getResponse();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();
    }

    public interface SpatulaHeaderResult extends x {
        String getSpatulaHeader();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();
    }

    @Deprecated
    v getSpatulaHeader(q qVar);

    @Deprecated
    v performProxyRequest(q qVar, ProxyRequest proxyRequest);
}
