package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/* JADX INFO: loaded from: classes4.dex */
public final class i0 extends t3.a {
    public static final Parcelable.Creator<i0> CREATOR = new android.support.v4.media.d(14);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12859i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Account f12860l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12861m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final GoogleSignInAccount f12862n;

    public i0(int i10, Account account, int i11, GoogleSignInAccount googleSignInAccount) {
        this.f12859i = i10;
        this.f12860l = account;
        this.f12861m = i11;
        this.f12862n = googleSignInAccount;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12859i);
        a.a.f0(parcel, 2, this.f12860l, i10, false);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f12861m);
        a.a.f0(parcel, 4, this.f12862n, i10, false);
        a.a.n0(iM0, parcel);
    }
}
