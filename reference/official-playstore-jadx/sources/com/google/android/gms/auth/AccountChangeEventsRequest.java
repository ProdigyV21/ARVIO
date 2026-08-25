package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class AccountChangeEventsRequest extends a {
    public static final Parcelable.Creator<AccountChangeEventsRequest> CREATOR = new zzb();
    final int zza;
    int zzb;

    @Deprecated
    String zzc;
    Account zzd;

    public AccountChangeEventsRequest() {
        this.zza = 1;
    }

    public Account getAccount() {
        return this.zzd;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzc;
    }

    public int getEventIndex() {
        return this.zzb;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzd = account;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzc = str;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int i10) {
        this.zzb = i10;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        int i12 = this.zzb;
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i12);
        a.a.g0(parcel, 3, this.zzc, false);
        a.a.f0(parcel, 4, this.zzd, i10, false);
        a.a.n0(iM0, parcel);
    }

    public AccountChangeEventsRequest(int i10, int i11, String str, Account account) {
        this.zza = i10;
        this.zzb = i11;
        this.zzc = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzd = account;
        } else {
            this.zzd = new Account(str, "com.google");
        }
    }
}
