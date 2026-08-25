package com.google.android.gms.cast;

import a0.c;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbn extends a {
    public static final Parcelable.Creator<zzbn> CREATOR = new zzbo();
    private final int zza;

    public zzbn() {
        this.zza = 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzbn) && this.zza == ((zzbn) obj).zza;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza)});
    }

    public final String toString() {
        int i10 = this.zza;
        return c.l("joinOptions(connectionType=", i10 != 0 ? i10 != 2 ? "UNKNOWN" : "INVISIBLE" : "STRONG", ")");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i11);
        a.a.n0(iM0, parcel);
    }

    public zzbn(int i10) {
        this.zza = i10;
    }
}
