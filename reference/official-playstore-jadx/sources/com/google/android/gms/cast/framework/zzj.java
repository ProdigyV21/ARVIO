package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzj extends a {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    private final boolean zza;

    public zzj(boolean z) {
        this.zza = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        boolean z = this.zza;
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
