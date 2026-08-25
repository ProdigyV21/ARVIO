package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public interface WorkAccountApi {

    @Deprecated
    public interface AddAccountResult extends x {
        Account getAccount();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();
    }

    @Deprecated
    v addWorkAccount(q qVar, String str);

    @Deprecated
    v removeWorkAccount(q qVar, Account account);

    @Deprecated
    void setWorkAuthenticatorEnabled(q qVar, boolean z);

    @Deprecated
    v setWorkAuthenticatorEnabledWithResult(q qVar, boolean z);
}
