package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class h implements WorkAccountApi.AddAccountResult {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Account f13038m = new Account("DUMMY_NAME", "com.google");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Status f13039i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Account f13040l;

    public h(Status status, Account account) {
        this.f13039i = status;
        this.f13040l = account == null ? f13038m : account;
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi.AddAccountResult
    public final Account getAccount() {
        return this.f13040l;
    }

    @Override // com.google.android.gms.auth.account.WorkAccountApi.AddAccountResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.f13039i;
    }
}
