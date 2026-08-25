package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public final class a1 extends o4.a implements n {
    @Override // com.google.android.gms.common.internal.n
    public final Account zzb() {
        Parcel parcelA = a(2, c());
        Account account = (Account) p4.g.a(parcelA, Account.CREATOR);
        parcelA.recycle();
        return account;
    }
}
