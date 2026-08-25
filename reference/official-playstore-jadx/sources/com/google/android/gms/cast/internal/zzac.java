package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import java.util.Arrays;
import java.util.Locale;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzac extends a {
    public static final Parcelable.Creator<zzac> CREATOR = new zzad();
    private double zza;
    private boolean zzb;
    private int zzc;
    private ApplicationMetadata zzd;
    private int zze;
    private com.google.android.gms.cast.zzao zzf;
    private double zzg;

    public zzac(double d4, boolean z, int i10, ApplicationMetadata applicationMetadata, int i11, com.google.android.gms.cast.zzao zzaoVar, double d10) {
        this.zza = d4;
        this.zzb = z;
        this.zzc = i10;
        this.zzd = applicationMetadata;
        this.zze = i11;
        this.zzf = zzaoVar;
        this.zzg = d10;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzac)) {
            return false;
        }
        zzac zzacVar = (zzac) obj;
        if (this.zza == zzacVar.zza && this.zzb == zzacVar.zzb && this.zzc == zzacVar.zzc && CastUtils.zza(this.zzd, zzacVar.zzd) && this.zze == zzacVar.zze) {
            com.google.android.gms.cast.zzao zzaoVar = this.zzf;
            if (CastUtils.zza(zzaoVar, zzaoVar) && this.zzg == zzacVar.zzg) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Double.valueOf(this.zza), Boolean.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, Integer.valueOf(this.zze), this.zzf, Double.valueOf(this.zzg)});
    }

    public final String toString() {
        return String.format(Locale.ROOT, "volume=%f", Double.valueOf(this.zza));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        double d4 = this.zza;
        a.a.l0(parcel, 2, 8);
        parcel.writeDouble(d4);
        boolean z = this.zzb;
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(z ? 1 : 0);
        int i11 = this.zzc;
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(i11);
        a.a.f0(parcel, 5, this.zzd, i10, false);
        int i12 = this.zze;
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(i12);
        a.a.f0(parcel, 7, this.zzf, i10, false);
        double d10 = this.zzg;
        a.a.l0(parcel, 8, 8);
        parcel.writeDouble(d10);
        a.a.n0(iM0, parcel);
    }

    public final double zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zzc;
    }

    public final int zzd() {
        return this.zze;
    }

    public final ApplicationMetadata zze() {
        return this.zzd;
    }

    public final com.google.android.gms.cast.zzao zzf() {
        return this.zzf;
    }

    public final double zzg() {
        return this.zzg;
    }

    public zzac() {
        this(Double.NaN, false, -1, null, -1, null, Double.NaN);
    }
}
