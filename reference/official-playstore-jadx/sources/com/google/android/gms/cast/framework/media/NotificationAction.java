package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationAction extends a {
    public static final Parcelable.Creator<NotificationAction> CREATOR = new zzq();
    private final String zza;
    private final int zzb;
    private final String zzc;

    public static final class Builder {
        String zza;
        int zzb;
        String zzc;

        public NotificationAction build() {
            return new NotificationAction(this.zza, this.zzb, this.zzc);
        }

        public Builder setAction(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("action cannot be null or an empty string.");
            }
            this.zza = str;
            return this;
        }

        public Builder setContentDescription(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("contentDescription cannot be null  or an empty string.");
            }
            this.zzc = str;
            return this;
        }

        public Builder setIconResId(int i10) {
            this.zzb = i10;
            return this;
        }
    }

    public NotificationAction(String str, int i10, String str2) {
        this.zza = str;
        this.zzb = i10;
        this.zzc = str2;
    }

    public String getAction() {
        return this.zza;
    }

    public String getContentDescription() {
        return this.zzc;
    }

    public int getIconResId() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getAction(), false);
        int iconResId = getIconResId();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(iconResId);
        a.a.g0(parcel, 4, getContentDescription(), false);
        a.a.n0(iM0, parcel);
    }
}
