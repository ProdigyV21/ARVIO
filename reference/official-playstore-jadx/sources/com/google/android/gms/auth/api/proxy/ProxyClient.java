package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface ProxyClient extends r<AuthProxyOptions> {
    /* synthetic */ a getApiKey();

    h getSpatulaHeader();

    h performProxyRequest(ProxyRequest proxyRequest);
}
