package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzaa extends a {
    public static final Parcelable.Creator<zzaa> CREATOR = new zzab();
    private final int zza;
    private final boolean zzb;
    private final boolean zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final boolean zzj;

    public zzaa(int i10, boolean z, boolean z5, String str, String str2, String str3, String str4, String str5, boolean z10, boolean z11) {
        this.zza = i10;
        this.zzb = z;
        this.zzc = z5;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzh = str5;
        this.zzi = z10;
        this.zzj = z11;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaa)) {
            return false;
        }
        zzaa zzaaVar = (zzaa) obj;
        return this.zza == zzaaVar.zza && this.zzb == zzaaVar.zzb && this.zzc == zzaaVar.zzc && TextUtils.equals(this.zzd, zzaaVar.zzd) && TextUtils.equals(this.zze, zzaaVar.zze) && TextUtils.equals(this.zzf, zzaaVar.zzf) && TextUtils.equals(this.zzg, zzaaVar.zzg) && TextUtils.equals(this.zzh, zzaaVar.zzh) && this.zzi == zzaaVar.zzi && this.zzj == zzaaVar.zzj;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Boolean.valueOf(this.zzb), Boolean.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, this.zzh, Boolean.valueOf(this.zzi), Boolean.valueOf(this.zzj)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i11);
        boolean z = this.zzb;
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z5 = this.zzc;
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(z5 ? 1 : 0);
        a.a.g0(parcel, 5, this.zzd, false);
        a.a.g0(parcel, 6, this.zze, false);
        a.a.g0(parcel, 7, this.zzf, false);
        a.a.g0(parcel, 8, this.zzg, false);
        a.a.g0(parcel, 9, this.zzh, false);
        boolean z10 = this.zzi;
        a.a.l0(parcel, 10, 4);
        parcel.writeInt(z10 ? 1 : 0);
        boolean z11 = this.zzj;
        a.a.l0(parcel, 11, 4);
        parcel.writeInt(z11 ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final String zza() {
        return this.zzd;
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzh;
    }
}
