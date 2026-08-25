package com.google.android.gms.auth.api;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.m;
import com.google.android.gms.internal.auth.f0;
import com.google.android.gms.internal.auth.j0;

/* JADX INFO: loaded from: classes4.dex */
public final class AuthProxy {
    public static final i API;
    public static final ProxyApi ProxyApi;
    public static final h zza;
    private static final a zzb;

    static {
        h hVar = new h();
        zza = hVar;
        zza zzaVar = new zza();
        zzb = zzaVar;
        API = new i("Auth.PROXY_API", zzaVar, hVar);
        ProxyApi = new j0();
    }

    public static ProxyClient getClient(Activity activity, AuthProxyOptions authProxyOptions) {
        i iVar = API;
        if (authProxyOptions == null) {
            authProxyOptions = AuthProxyOptions.zza;
        }
        return new f0(activity, activity, iVar, authProxyOptions, m.f12824c);
    }

    public static ProxyClient getClient(Context context, AuthProxyOptions authProxyOptions) {
        i iVar = API;
        if (authProxyOptions == null) {
            authProxyOptions = AuthProxyOptions.zza;
        }
        return new f0(context, null, iVar, authProxyOptions, m.f12824c);
    }
}
