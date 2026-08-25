package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class ImageHints extends a {
    public static final Parcelable.Creator<ImageHints> CREATOR = new zzh();
    private final int zza;
    private final int zzb;
    private final int zzc;

    public ImageHints(int i10, int i11, int i12) {
        this.zza = i10;
        this.zzb = i11;
        this.zzc = i12;
    }

    public int getHeightInPixels() {
        return this.zzc;
    }

    public int getType() {
        return this.zza;
    }

    public int getWidthInPixels() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int type = getType();
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(type);
        int widthInPixels = getWidthInPixels();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(widthInPixels);
        int heightInPixels = getHeightInPixels();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(heightInPixels);
        a.a.n0(iM0, parcel);
    }
}
