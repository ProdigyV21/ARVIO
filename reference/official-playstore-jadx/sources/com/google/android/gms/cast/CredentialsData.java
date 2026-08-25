package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class CredentialsData extends a {
    public static final Parcelable.Creator<CredentialsData> CREATOR = new zzal();
    public static final String CREDENTIALS_TYPE_ANDROID = "android";
    public static final String CREDENTIALS_TYPE_CLOUD = "cloud";
    public static final String CREDENTIALS_TYPE_IOS = "ios";
    public static final String CREDENTIALS_TYPE_WEB = "web";
    private final String zza;
    private final String zzb;

    public static class Builder {
        private String zza;
        private String zzb = CredentialsData.CREDENTIALS_TYPE_ANDROID;

        public CredentialsData build() {
            return new CredentialsData(this.zza, this.zzb);
        }

        public Builder setCredentials(String str) {
            this.zza = str;
            return this;
        }

        public Builder setCredentialsType(String str) {
            this.zzb = str;
            return this;
        }
    }

    public CredentialsData(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public static CredentialsData fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new CredentialsData(CastUtils.optStringOrNull(jSONObject, "credentials"), CastUtils.optStringOrNull(jSONObject, "credentialsType"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CredentialsData)) {
            return false;
        }
        CredentialsData credentialsData = (CredentialsData) obj;
        return t.l(this.zza, credentialsData.zza) && t.l(this.zzb, credentialsData.zzb);
    }

    public String getCredentials() {
        return this.zza;
    }

    public String getCredentialsType() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, getCredentials(), false);
        a.a.g0(parcel, 2, getCredentialsType(), false);
        a.a.n0(iM0, parcel);
    }
}
