package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import java.util.List;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class TokenData extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<TokenData> CREATOR = new zzm();
    final int zza;
    private final String zzb;
    private final Long zzc;
    private final boolean zzd;
    private final boolean zze;
    private final List zzf;
    private final String zzg;

    public TokenData(int i10, String str, Long l10, boolean z, boolean z5, List list, String str2) {
        this.zza = i10;
        t.f(str);
        this.zzb = str;
        this.zzc = l10;
        this.zzd = z;
        this.zze = z5;
        this.zzf = list;
        this.zzg = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) obj;
        return TextUtils.equals(this.zzb, tokenData.zzb) && t.l(this.zzc, tokenData.zzc) && this.zzd == tokenData.zzd && this.zze == tokenData.zze && t.l(this.zzf, tokenData.zzf) && t.l(this.zzg, tokenData.zzg);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzb, this.zzc, Boolean.valueOf(this.zzd), Boolean.valueOf(this.zze), this.zzf, this.zzg});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        a.a.g0(parcel, 2, this.zzb, false);
        Long l10 = this.zzc;
        if (l10 != null) {
            a.a.l0(parcel, 3, 8);
            parcel.writeLong(l10.longValue());
        }
        boolean z = this.zzd;
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z5 = this.zze;
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(z5 ? 1 : 0);
        a.a.i0(parcel, this.zzf, 6);
        a.a.g0(parcel, 7, this.zzg, false);
        a.a.n0(iM0, parcel);
    }

    public final String zza() {
        return this.zzb;
    }
}
