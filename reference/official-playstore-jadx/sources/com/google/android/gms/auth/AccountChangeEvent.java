package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.a2;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class AccountChangeEvent extends a {
    public static final Parcelable.Creator<AccountChangeEvent> CREATOR = new zza();
    final int zza;
    final long zzb;
    final String zzc;
    final int zzd;
    final int zze;
    final String zzf;

    public AccountChangeEvent(int i10, long j10, String str, int i11, int i12, String str2) {
        this.zza = i10;
        this.zzb = j10;
        t.i(str);
        this.zzc = str;
        this.zzd = i11;
        this.zze = i12;
        this.zzf = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountChangeEvent)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
        return this.zza == accountChangeEvent.zza && this.zzb == accountChangeEvent.zzb && t.l(this.zzc, accountChangeEvent.zzc) && this.zzd == accountChangeEvent.zzd && this.zze == accountChangeEvent.zze && t.l(this.zzf, accountChangeEvent.zzf);
    }

    public String getAccountName() {
        return this.zzc;
    }

    public String getChangeData() {
        return this.zzf;
    }

    public int getChangeType() {
        return this.zzd;
    }

    public int getEventIndex() {
        return this.zze;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), Long.valueOf(this.zzb), this.zzc, Integer.valueOf(this.zzd), Integer.valueOf(this.zze), this.zzf});
    }

    public String toString() {
        int i10 = this.zzd;
        String str = i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "UNKNOWN" : "RENAMED_TO" : "RENAMED_FROM" : "REMOVED" : "ADDED";
        String str2 = this.zzc;
        String str3 = this.zzf;
        int i11 = this.zze;
        StringBuilder sbR = a2.r("AccountChangeEvent {accountName = ", str2, ", changeType = ", str, ", changeData = ");
        sbR.append(str3);
        sbR.append(", eventIndex = ");
        sbR.append(i11);
        sbR.append("}");
        return sbR.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        long j10 = this.zzb;
        a.a.l0(parcel, 2, 8);
        parcel.writeLong(j10);
        a.a.g0(parcel, 3, this.zzc, false);
        int i12 = this.zzd;
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(i12);
        int i13 = this.zze;
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(i13);
        a.a.g0(parcel, 6, this.zzf, false);
        a.a.n0(iM0, parcel);
    }

    public AccountChangeEvent(long j10, String str, int i10, int i11, String str2) {
        this.zza = 1;
        this.zzb = j10;
        t.i(str);
        this.zzc = str;
        this.zzd = i10;
        this.zze = i11;
        this.zzf = str2;
    }
}
