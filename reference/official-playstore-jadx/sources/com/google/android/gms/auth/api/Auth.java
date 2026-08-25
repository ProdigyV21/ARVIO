package com.google.android.gms.auth.api;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import m4.o;

/* JADX INFO: loaded from: classes4.dex */
public final class Auth {
    public static final i GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi;

    @Deprecated
    public static final i PROXY_API;

    @Deprecated
    public static final ProxyApi ProxyApi;
    public static final h zba;
    public static final h zbb;
    public static final i zbc;
    public static final o zbd;
    private static final a zbe;
    private static final a zbf;

    static {
        h hVar = new h();
        zba = hVar;
        h hVar2 = new h();
        zbb = hVar2;
        zba zbaVar = new zba();
        zbe = zbaVar;
        zbb zbbVar = new zbb();
        zbf = zbbVar;
        PROXY_API = AuthProxy.API;
        zbc = new i("Auth.CREDENTIALS_API", zbaVar, hVar);
        GOOGLE_SIGN_IN_API = new i("Auth.GOOGLE_SIGN_IN_API", zbbVar, hVar2);
        ProxyApi = AuthProxy.ProxyApi;
        zbd = new o();
        GoogleSignInApi = new com.google.android.gms.auth.api.signin.internal.zbd();
    }

    private Auth() {
    }
}
