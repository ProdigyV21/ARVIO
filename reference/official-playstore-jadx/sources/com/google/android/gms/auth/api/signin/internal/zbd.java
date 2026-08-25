package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.i0;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.t;
import com.google.android.gms.common.api.v;

/* JADX INFO: loaded from: classes4.dex */
public final class zbd implements GoogleSignInApi {
    private static final GoogleSignInOptions zba(q qVar) {
        i iVar = Auth.PROXY_API;
        qVar.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInApi
    public final Intent getSignInIntent(q qVar) {
        return zbm.zbc(((i0) qVar).f12756b.getApplicationContext(), zba(qVar));
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInApi
    public final GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        return zbm.zbd(intent);
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInApi
    public final v revokeAccess(q qVar) {
        return zbm.zbf(qVar, ((i0) qVar).f12756b.getApplicationContext(), false);
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInApi
    public final v signOut(q qVar) {
        return zbm.zbg(qVar, ((i0) qVar).f12756b.getApplicationContext(), false);
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInApi
    public final t silentSignIn(q qVar) {
        return zbm.zbe(qVar, ((i0) qVar).f12756b.getApplicationContext(), zba(qVar), false);
    }
}
