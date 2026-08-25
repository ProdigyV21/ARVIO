package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class AdBreakStatus extends a {

    @Deprecated
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    private final long zzb;
    private final long zzc;
    private final String zzd;
    private final String zze;
    private final long zzf;
    private static final Logger zza = new Logger("AdBreakStatus");
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = new zzc();

    public static class Builder {
        private long zza;
        private long zzb;
        private String zzc;
        private String zzd;
        private long zze = -1;

        public AdBreakStatus build() {
            return new AdBreakStatus(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        public Builder setBreakClipId(String str) {
            this.zzd = str;
            return this;
        }

        public Builder setBreakId(String str) {
            this.zzc = str;
            return this;
        }

        public Builder setCurrentBreakClipTimeInMs(long j10) {
            this.zzb = j10;
            return this;
        }

        public Builder setCurrentBreakTimeInMs(long j10) {
            this.zza = j10;
            return this;
        }

        public Builder setWhenSkippableInMs(long j10) {
            this.zze = j10;
            return this;
        }
    }

    public AdBreakStatus(long j10, long j11, String str, String str2, long j12) {
        this.zzb = j10;
        this.zzc = j11;
        this.zzd = str;
        this.zze = str2;
        this.zzf = j12;
    }

    public static AdBreakStatus zzb(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("currentBreakTime") && jSONObject.has("currentBreakClipTime")) {
            try {
                long jSecToMillisec = CastUtils.secToMillisec(jSONObject.getLong("currentBreakTime"));
                long jSecToMillisec2 = CastUtils.secToMillisec(jSONObject.getLong("currentBreakClipTime"));
                String strOptStringOrNull = CastUtils.optStringOrNull(jSONObject, "breakId");
                String strOptStringOrNull2 = CastUtils.optStringOrNull(jSONObject, "breakClipId");
                long jOptLong = jSONObject.optLong("whenSkippable", -1L);
                if (jOptLong != -1) {
                    jOptLong = CastUtils.secToMillisec(jOptLong);
                }
                return new AdBreakStatus(jSecToMillisec, jSecToMillisec2, strOptStringOrNull, strOptStringOrNull2, jOptLong);
            } catch (JSONException e5) {
                zza.e(e5, "Error while creating an AdBreakClipInfo from JSON", new Object[0]);
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        return this.zzb == adBreakStatus.zzb && this.zzc == adBreakStatus.zzc && CastUtils.zza(this.zzd, adBreakStatus.zzd) && CastUtils.zza(this.zze, adBreakStatus.zze) && this.zzf == adBreakStatus.zzf;
    }

    public String getBreakClipId() {
        return this.zze;
    }

    public String getBreakId() {
        return this.zzd;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzc;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzb;
    }

    public long getWhenSkippableInMs() {
        return this.zzf;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzb), Long.valueOf(this.zzc), this.zzd, this.zze, Long.valueOf(this.zzf)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        long currentBreakTimeInMs = getCurrentBreakTimeInMs();
        a.a.l0(parcel, 2, 8);
        parcel.writeLong(currentBreakTimeInMs);
        long currentBreakClipTimeInMs = getCurrentBreakClipTimeInMs();
        a.a.l0(parcel, 3, 8);
        parcel.writeLong(currentBreakClipTimeInMs);
        a.a.g0(parcel, 4, getBreakId(), false);
        a.a.g0(parcel, 5, getBreakClipId(), false);
        long whenSkippableInMs = getWhenSkippableInMs();
        a.a.l0(parcel, 6, 8);
        parcel.writeLong(whenSkippableInMs);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("currentBreakTime", CastUtils.millisecToSec(this.zzb));
            jSONObject.put("currentBreakClipTime", CastUtils.millisecToSec(this.zzc));
            jSONObject.putOpt("breakId", this.zzd);
            jSONObject.putOpt("breakClipId", this.zze);
            long j10 = this.zzf;
            if (j10 == -1) {
                return jSONObject;
            }
            jSONObject.put("whenSkippable", CastUtils.millisecToSec(j10));
            return jSONObject;
        } catch (JSONException e5) {
            zza.e(e5, "Error transforming AdBreakStatus into JSONObject", new Object[0]);
            return new JSONObject();
        }
    }
}
