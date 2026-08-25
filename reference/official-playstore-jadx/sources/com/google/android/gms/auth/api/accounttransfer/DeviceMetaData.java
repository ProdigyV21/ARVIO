package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceMetaData extends a {
    public static final Parcelable.Creator<DeviceMetaData> CREATOR = new zzy();
    final int zza;
    private final boolean zzb;
    private final long zzc;
    private final boolean zzd;

    public DeviceMetaData(int i10, boolean z, long j10, boolean z5) {
        this.zza = i10;
        this.zzb = z;
        this.zzc = j10;
        this.zzd = z5;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzc;
    }

    public boolean isChallengeAllowed() {
        return this.zzd;
    }

    public boolean isLockScreenSolved() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        boolean zIsLockScreenSolved = isLockScreenSolved();
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(zIsLockScreenSolved ? 1 : 0);
        long minAgeOfLockScreen = getMinAgeOfLockScreen();
        a.a.l0(parcel, 3, 8);
        parcel.writeLong(minAgeOfLockScreen);
        boolean zIsChallengeAllowed = isChallengeAllowed();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(zIsChallengeAllowed ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
