package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccount;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class j implements WorkAccountApi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Status f13049a = new Status(13, null, null, null);

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final com.google.android.gms.common.api.v addWorkAccount(com.google.android.gms.common.api.q qVar, String str) {
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new f(WorkAccount.API, qVar, str));
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final com.google.android.gms.common.api.v removeWorkAccount(com.google.android.gms.common.api.q qVar, Account account) {
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new g(WorkAccount.API, qVar, account));
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final void setWorkAuthenticatorEnabled(com.google.android.gms.common.api.q qVar, boolean z) {
        setWorkAuthenticatorEnabledWithResult(qVar, z);
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi
    public final com.google.android.gms.common.api.v setWorkAuthenticatorEnabledWithResult(com.google.android.gms.common.api.q qVar, boolean z) {
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new d(WorkAccount.API, qVar, z));
    }
}
