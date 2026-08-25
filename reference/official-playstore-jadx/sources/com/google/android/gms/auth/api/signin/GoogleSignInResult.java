package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class GoogleSignInResult implements x {
    private final Status zba;
    private final GoogleSignInAccount zbb;

    public GoogleSignInResult(GoogleSignInAccount googleSignInAccount, Status status) {
        this.zbb = googleSignInAccount;
        this.zba = status;
    }

    public GoogleSignInAccount getSignInAccount() {
        return this.zbb;
    }

    @Override // com.google.android.gms.common.api.x
    public Status getStatus() {
        return this.zba;
    }

    public boolean isSuccess() {
        return this.zba.d();
    }
}
