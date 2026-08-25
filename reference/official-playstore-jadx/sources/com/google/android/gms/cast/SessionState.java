package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import c4.e;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class SessionState extends a {
    public static final Parcelable.Creator<SessionState> CREATOR = new zzde();
    String zza;
    private final MediaLoadRequestData zzb;
    private final JSONObject zzc;

    public static class Builder {
        private MediaLoadRequestData zza;
        private JSONObject zzb;

        public SessionState build() {
            return new SessionState(this.zza, this.zzb);
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzb = jSONObject;
            return this;
        }

        public Builder setLoadRequestData(MediaLoadRequestData mediaLoadRequestData) {
            this.zza = mediaLoadRequestData;
            return this;
        }
    }

    public SessionState(MediaLoadRequestData mediaLoadRequestData, JSONObject jSONObject) {
        this.zzb = mediaLoadRequestData;
        this.zzc = jSONObject;
    }

    public static SessionState fromJson(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("loadRequestData");
        return new SessionState(jSONObjectOptJSONObject != null ? MediaLoadRequestData.fromJson(jSONObjectOptJSONObject) : null, jSONObject.optJSONObject("customData"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionState)) {
            return false;
        }
        SessionState sessionState = (SessionState) obj;
        if (e.a(this.zzc, sessionState.zzc)) {
            return t.l(this.zzb, sessionState.zzb);
        }
        return false;
    }

    public JSONObject getCustomData() {
        return this.zzc;
    }

    public MediaLoadRequestData getLoadRequestData() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzb, String.valueOf(this.zzc)});
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            MediaLoadRequestData mediaLoadRequestData = this.zzb;
            if (mediaLoadRequestData != null) {
                jSONObject.put("loadRequestData", mediaLoadRequestData.toJson());
            }
            jSONObject.put("customData", this.zzc);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzc;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, getLoadRequestData(), i10, false);
        a.a.g0(parcel, 3, this.zza, false);
        a.a.n0(iM0, parcel);
    }
}
