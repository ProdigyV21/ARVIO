package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzam extends a {
    public static final Parcelable.Creator<zzam> CREATOR = new zzan();
    private final float zza;
    private final float zzb;
    private final float zzc;

    public zzam(float f10, float f11, float f12) {
        this.zza = f10;
        this.zzb = f11;
        this.zzc = f12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzam)) {
            return false;
        }
        zzam zzamVar = (zzam) obj;
        return this.zza == zzamVar.zza && this.zzb == zzamVar.zzb && this.zzc == zzamVar.zzc;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.zza), Float.valueOf(this.zzb), Float.valueOf(this.zzc)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        float f10 = this.zza;
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 2, 4);
        parcel.writeFloat(f10);
        float f11 = this.zzb;
        a.a.l0(parcel, 3, 4);
        parcel.writeFloat(f11);
        float f12 = this.zzc;
        a.a.l0(parcel, 4, 4);
        parcel.writeFloat(f12);
        a.a.n0(iM0, parcel);
    }
}
