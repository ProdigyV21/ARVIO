package com.google.android.gms.cast.framework.devicesuggestions;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceSuggestionResult extends a {
    public static final int CONFIDENCE_HIGH = 3;
    public static final int CONFIDENCE_LOW = 1;
    public static final int CONFIDENCE_MID = 2;
    public static final Parcelable.Creator<DeviceSuggestionResult> CREATOR = new zza();
    private final String zza;
    private final String zzb;
    private final int zzc;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Confidence {
    }

    public DeviceSuggestionResult(String str, String str2, int i10) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = i10;
    }

    public int getConfidence() {
        return this.zzc;
    }

    public String getDeviceName() {
        return this.zzb;
    }

    public String getRouteId() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, getRouteId(), false);
        a.a.g0(parcel, 2, getDeviceName(), false);
        int confidence = getConfidence();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(confidence);
        a.a.n0(iM0, parcel);
    }
}
