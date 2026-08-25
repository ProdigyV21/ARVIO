package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzl extends a {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    private final int zza;

    public zzl(int i10) {
        this.zza = i10;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int i11 = this.zza;
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }
}
