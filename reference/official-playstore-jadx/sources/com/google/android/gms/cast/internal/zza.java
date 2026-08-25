package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zza extends a {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private final String zza;

    public zza() {
        this.zza = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zza) {
            return CastUtils.zza(this.zza, ((zza) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        String str = this.zza;
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, str, false);
        a.a.n0(iM0, parcel);
    }

    public final String zza() {
        return this.zza;
    }

    public zza(String str) {
        this.zza = str;
    }
}
