package com.google.android.gms.auth.api.signin;

import a1.a;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.tasks.h;
import f4.d;
import o3.e;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class GoogleSignInClient extends n {
    private static final zbb zbb = new zbb(null);
    static int zba = 1;

    public GoogleSignInClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new a());
    }

    private final synchronized int zba() {
        int i10;
        try {
            i10 = zba;
            if (i10 == 1) {
                Context applicationContext = getApplicationContext();
                e eVar = e.f20741d;
                int iB = eVar.b(applicationContext, 12451000);
                if (iB == 0) {
                    i10 = 4;
                    zba = 4;
                } else if (eVar.a(applicationContext, iB, null) != null || d.a(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) {
                    i10 = 2;
                    zba = 2;
                } else {
                    i10 = 3;
                    zba = 3;
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return i10;
    }

    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        int iZba = zba();
        int i10 = iZba - 1;
        if (iZba != 0) {
            return i10 != 2 ? i10 != 3 ? zbm.zbb(applicationContext, (GoogleSignInOptions) getApiOptions()) : zbm.zbc(applicationContext, (GoogleSignInOptions) getApiOptions()) : zbm.zba(applicationContext, (GoogleSignInOptions) getApiOptions());
        }
        throw null;
    }

    public h revokeAccess() {
        return t.n(zbm.zbf(asGoogleApiClient(), getApplicationContext(), zba() == 3), new a());
    }

    public h signOut() {
        return t.n(zbm.zbg(asGoogleApiClient(), getApplicationContext(), zba() == 3), new a());
    }

    public h silentSignIn() {
        return t.n(zbm.zbe(asGoogleApiClient(), getApplicationContext(), (GoogleSignInOptions) getApiOptions(), zba() == 3), zbb);
    }

    public GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, null, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new m(new a(), Looper.getMainLooper()));
    }
}
