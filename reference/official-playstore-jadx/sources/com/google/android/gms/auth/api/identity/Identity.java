package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.internal.t;
import m4.b;
import m4.e;
import m4.l;

/* JADX INFO: loaded from: classes4.dex */
public final class Identity {
    private Identity() {
    }

    public static AuthorizationClient getAuthorizationClient(Activity activity) {
        t.i(activity);
        zbc zbcVarZbb = new zbb(null).zbb();
        i iVar = b.f20224a;
        zbb zbbVarZbc = zbb.zbc(zbcVarZbb);
        zbbVarZbc.zba(l.a());
        return new b(activity, activity, iVar, zbbVarZbc.zbb(), m.f12824c);
    }

    public static CredentialSavingClient getCredentialSavingClient(Activity activity) {
        t.i(activity);
        return new e(activity, new zbi());
    }

    public static SignInClient getSignInClient(Activity activity) {
        t.i(activity);
        return new m4.i(activity, new zbv());
    }

    public static CredentialSavingClient getCredentialSavingClient(Context context) {
        t.i(context);
        return new e(context, new zbi());
    }

    public static SignInClient getSignInClient(Context context) {
        t.i(context);
        return new m4.i(context, new zbv());
    }

    public static AuthorizationClient getAuthorizationClient(Context context) {
        t.i(context);
        zbc zbcVarZbb = new zbb(null).zbb();
        i iVar = b.f20224a;
        zbb zbbVarZbc = zbb.zbc(zbcVarZbb);
        zbbVarZbc.zba(l.a());
        return new b(context, null, iVar, zbbVarZbc.zbb(), m.f12824c);
    }
}
