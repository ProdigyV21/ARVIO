package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.t;
import com.google.android.gms.common.api.v;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public interface GoogleSignInApi {
    public static final String EXTRA_SIGN_IN_ACCOUNT = "signInAccount";

    Intent getSignInIntent(q qVar);

    GoogleSignInResult getSignInResultFromIntent(Intent intent);

    v revokeAccess(q qVar);

    v signOut(q qVar);

    t silentSignIn(q qVar);
}
