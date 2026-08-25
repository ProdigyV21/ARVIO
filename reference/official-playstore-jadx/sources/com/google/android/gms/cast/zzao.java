package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzao extends a {
    public static final Parcelable.Creator<zzao> CREATOR = new zzap();
    private final zzam zza;
    private final zzam zzb;

    public zzao(zzam zzamVar, zzam zzamVar2) {
        this.zza = zzamVar;
        this.zzb = zzamVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzao)) {
            return false;
        }
        zzao zzaoVar = (zzao) obj;
        return CastUtils.zza(this.zza, zzaoVar.zza) && CastUtils.zza(this.zzb, zzaoVar.zzb);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        zzam zzamVar = this.zza;
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, zzamVar, i10, false);
        a.a.f0(parcel, 3, this.zzb, i10, false);
        a.a.n0(iM0, parcel);
    }
}
