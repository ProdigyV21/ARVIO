package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.h;
import com.google.android.gms.common.api.internal.i0;
import com.google.android.gms.common.api.internal.w;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import java.util.Iterator;
import java.util.Set;
import v3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zbm {
    private static final a zba = new a("GoogleSignInCommon", new String[0]);

    public static Intent zba(Context context, GoogleSignInOptions googleSignInOptions) {
        zba.a("getFallbackSignInIntent()", new Object[0]);
        Intent intentZbc = zbc(context, googleSignInOptions);
        intentZbc.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return intentZbc;
    }

    public static Intent zbb(Context context, GoogleSignInOptions googleSignInOptions) {
        zba.a("getNoImplementationSignInIntent()", new Object[0]);
        Intent intentZbc = zbc(context, googleSignInOptions);
        intentZbc.setAction("com.google.android.gms.auth.NO_IMPL");
        return intentZbc;
    }

    public static Intent zbc(Context context, GoogleSignInOptions googleSignInOptions) {
        zba.a("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    public static GoogleSignInResult zbd(Intent intent) {
        Status status = Status.f12672q;
        if (intent == null) {
            return new GoogleSignInResult(null, status);
        }
        Status status2 = (Status) intent.getParcelableExtra("googleSignInStatus");
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        if (googleSignInAccount != null) {
            return new GoogleSignInResult(googleSignInAccount, Status.f12670o);
        }
        if (status2 != null) {
            status = status2;
        }
        return new GoogleSignInResult(null, status);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.common.api.t zbe(com.google.android.gms.common.api.q r6, android.content.Context r7, com.google.android.gms.auth.api.signin.GoogleSignInOptions r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.zbm.zbe(com.google.android.gms.common.api.q, android.content.Context, com.google.android.gms.auth.api.signin.GoogleSignInOptions, boolean):com.google.android.gms.common.api.t");
    }

    public static v zbf(q qVar, Context context, boolean z) {
        zba.a("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zbh(context);
        if (z) {
            return zbb.zba(savedRefreshToken);
        }
        return ((i0) qVar).f12756b.doWrite(new zbk(qVar));
    }

    public static v zbg(q qVar, Context context, boolean z) {
        zba.a("Signing out", new Object[0]);
        zbh(context);
        if (!z) {
            return ((i0) qVar).f12756b.doWrite(new zbi(qVar));
        }
        w wVar = new w(qVar);
        wVar.setResult(Status.f12670o);
        return wVar;
    }

    private static void zbh(Context context) {
        zbn.zbc(context).zbd();
        Set set = q.f12827a;
        synchronized (set) {
        }
        Iterator it = set.iterator();
        if (it.hasNext()) {
            ((q) it.next()).getClass();
            throw new UnsupportedOperationException();
        }
        h.a();
    }
}
