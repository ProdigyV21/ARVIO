package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class VastAdsRequest extends a {
    public static final Parcelable.Creator<VastAdsRequest> CREATOR = new zzdg();
    private final String zza;
    private final String zzb;

    public static class Builder {
        private String zza;
        private String zzb;

        public VastAdsRequest build() {
            return new VastAdsRequest(this.zza, this.zzb);
        }

        public Builder setAdTagUrl(String str) {
            this.zza = str;
            return this;
        }

        public Builder setAdsResponse(String str) {
            this.zzb = str;
            return this;
        }
    }

    public VastAdsRequest(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public static VastAdsRequest fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(CastUtils.optStringOrNull(jSONObject, "adTagUrl"), CastUtils.optStringOrNull(jSONObject, "adsResponse"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        return CastUtils.zza(this.zza, vastAdsRequest.zza) && CastUtils.zza(this.zzb, vastAdsRequest.zzb);
    }

    public String getAdTagUrl() {
        return this.zza;
    }

    public String getAdsResponse() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getAdTagUrl(), false);
        a.a.g0(parcel, 3, getAdsResponse(), false);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.zza;
            if (str != null) {
                jSONObject.put("adTagUrl", str);
            }
            String str2 = this.zzb;
            if (str2 != null) {
                jSONObject.put("adsResponse", str2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
