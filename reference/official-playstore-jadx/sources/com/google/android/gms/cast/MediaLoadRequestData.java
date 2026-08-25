package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import c4.e;
import com.google.android.gms.cast.MediaQueueData;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaLoadRequestData extends a implements RequestData {
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    public static final long PLAY_POSITION_UNASSIGNED = -1;
    String zza;
    private final MediaInfo zzc;
    private final MediaQueueData zzd;
    private final Boolean zze;
    private final long zzf;
    private final double zzg;
    private final long[] zzh;
    private final JSONObject zzi;
    private final String zzj;
    private final String zzk;
    private final String zzl;
    private final String zzm;
    private long zzn;
    private static final Logger zzb = new Logger("MediaLoadRequestData");
    public static final Parcelable.Creator<MediaLoadRequestData> CREATOR = new zzbt();

    public static class Builder {
        private MediaInfo zza;
        private MediaQueueData zzb;
        private Boolean zzc;
        private long zzd;
        private double zze;
        private long[] zzf;
        private JSONObject zzg;
        private String zzh;
        private String zzi;
        private String zzj;
        private String zzk;
        private long zzl;

        public Builder() {
            this.zzc = Boolean.TRUE;
            this.zzd = -1L;
            this.zze = 1.0d;
        }

        public MediaLoadRequestData build() {
            return new MediaLoadRequestData(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, null);
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzf = jArr;
            return this;
        }

        public Builder setAtvCredentials(String str) {
            this.zzj = str;
            return this;
        }

        public Builder setAtvCredentialsType(String str) {
            this.zzk = str;
            return this;
        }

        public Builder setAutoplay(Boolean bool) {
            this.zzc = bool;
            return this;
        }

        public Builder setCredentials(String str) {
            this.zzh = str;
            return this;
        }

        public Builder setCredentialsType(String str) {
            this.zzi = str;
            return this;
        }

        public Builder setCurrentTime(long j10) {
            this.zzd = j10;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzg = jSONObject;
            return this;
        }

        public Builder setMediaInfo(MediaInfo mediaInfo) {
            this.zza = mediaInfo;
            return this;
        }

        public Builder setPlaybackRate(double d4) {
            if (Double.compare(d4, 2.0d) > 0 || Double.compare(d4, 0.5d) < 0) {
                throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
            }
            this.zze = d4;
            return this;
        }

        public Builder setQueueData(MediaQueueData mediaQueueData) {
            this.zzb = mediaQueueData;
            return this;
        }

        public final Builder zza(long j10) {
            this.zzl = j10;
            return this;
        }

        public Builder(MediaLoadRequestData mediaLoadRequestData) {
            this.zzc = Boolean.TRUE;
            this.zzd = -1L;
            this.zze = 1.0d;
            this.zza = mediaLoadRequestData.getMediaInfo();
            this.zzb = mediaLoadRequestData.getQueueData();
            this.zzc = mediaLoadRequestData.getAutoplay();
            this.zzd = mediaLoadRequestData.getCurrentTime();
            this.zze = mediaLoadRequestData.getPlaybackRate();
            this.zzf = mediaLoadRequestData.getActiveTrackIds();
            this.zzg = mediaLoadRequestData.getCustomData();
            this.zzh = mediaLoadRequestData.getCredentials();
            this.zzi = mediaLoadRequestData.getCredentialsType();
            this.zzj = mediaLoadRequestData.zza();
            this.zzk = mediaLoadRequestData.zzb();
            this.zzl = mediaLoadRequestData.getRequestId();
        }
    }

    private MediaLoadRequestData(MediaInfo mediaInfo, MediaQueueData mediaQueueData, Boolean bool, long j10, double d4, long[] jArr, JSONObject jSONObject, String str, String str2, String str3, String str4, long j11) {
        this.zzc = mediaInfo;
        this.zzd = mediaQueueData;
        this.zze = bool;
        this.zzf = j10;
        this.zzg = d4;
        this.zzh = jArr;
        this.zzi = jSONObject;
        this.zzj = str;
        this.zzk = str2;
        this.zzl = str3;
        this.zzm = str4;
        this.zzn = j11;
    }

    public static MediaLoadRequestData fromJson(JSONObject jSONObject) {
        Builder builder = new Builder();
        try {
            if (jSONObject.has(LinkHeader.Parameters.Media)) {
                builder.setMediaInfo(new MediaInfo(jSONObject.getJSONObject(LinkHeader.Parameters.Media)));
            }
            if (jSONObject.has("queueData")) {
                MediaQueueData.Builder builder2 = new MediaQueueData.Builder();
                builder2.zza(jSONObject.getJSONObject("queueData"));
                builder.setQueueData(builder2.build());
            }
            if (jSONObject.has("autoplay")) {
                builder.setAutoplay(Boolean.valueOf(jSONObject.getBoolean("autoplay")));
            } else {
                builder.setAutoplay(null);
            }
            if (jSONObject.has("currentTime")) {
                builder.setCurrentTime(CastUtils.secToMillisec(jSONObject.getDouble("currentTime")));
            } else {
                builder.setCurrentTime(-1L);
            }
            builder.setPlaybackRate(jSONObject.optDouble("playbackRate", 1.0d));
            builder.setCredentials(CastUtils.optStringOrNull(jSONObject, "credentials"));
            builder.setCredentialsType(CastUtils.optStringOrNull(jSONObject, "credentialsType"));
            builder.setAtvCredentials(CastUtils.optStringOrNull(jSONObject, "atvCredentials"));
            builder.setAtvCredentialsType(CastUtils.optStringOrNull(jSONObject, "atvCredentialsType"));
            builder.zza(jSONObject.optLong("requestId"));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("activeTrackIds");
            if (jSONArrayOptJSONArray != null) {
                long[] jArr = new long[jSONArrayOptJSONArray.length()];
                for (int i10 = 0; i10 < jSONArrayOptJSONArray.length(); i10++) {
                    jArr[i10] = jSONArrayOptJSONArray.getLong(i10);
                }
                builder.setActiveTrackIds(jArr);
            }
            builder.setCustomData(jSONObject.optJSONObject("customData"));
            return builder.build();
        } catch (JSONException unused) {
            return builder.build();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLoadRequestData)) {
            return false;
        }
        MediaLoadRequestData mediaLoadRequestData = (MediaLoadRequestData) obj;
        return e.a(this.zzi, mediaLoadRequestData.zzi) && t.l(this.zzc, mediaLoadRequestData.zzc) && t.l(this.zzd, mediaLoadRequestData.zzd) && t.l(this.zze, mediaLoadRequestData.zze) && this.zzf == mediaLoadRequestData.zzf && this.zzg == mediaLoadRequestData.zzg && Arrays.equals(this.zzh, mediaLoadRequestData.zzh) && t.l(this.zzj, mediaLoadRequestData.zzj) && t.l(this.zzk, mediaLoadRequestData.zzk) && t.l(this.zzl, mediaLoadRequestData.zzl) && t.l(this.zzm, mediaLoadRequestData.zzm) && this.zzn == mediaLoadRequestData.zzn;
    }

    public long[] getActiveTrackIds() {
        return this.zzh;
    }

    public Boolean getAutoplay() {
        return this.zze;
    }

    public String getCredentials() {
        return this.zzj;
    }

    public String getCredentialsType() {
        return this.zzk;
    }

    public long getCurrentTime() {
        return this.zzf;
    }

    @Override // com.google.android.gms.cast.RequestData
    public JSONObject getCustomData() {
        return this.zzi;
    }

    public MediaInfo getMediaInfo() {
        return this.zzc;
    }

    public double getPlaybackRate() {
        return this.zzg;
    }

    public MediaQueueData getQueueData() {
        return this.zzd;
    }

    @Override // com.google.android.gms.cast.RequestData
    public long getRequestId() {
        return this.zzn;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzc, this.zzd, this.zze, Long.valueOf(this.zzf), Double.valueOf(this.zzg), this.zzh, String.valueOf(this.zzi), this.zzj, this.zzk, this.zzl, this.zzm, Long.valueOf(this.zzn)});
    }

    public void setRequestId(long j10) {
        this.zzn = j10;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            MediaInfo mediaInfo = this.zzc;
            if (mediaInfo != null) {
                jSONObject.put(LinkHeader.Parameters.Media, mediaInfo.zzb());
            }
            MediaQueueData mediaQueueData = this.zzd;
            if (mediaQueueData != null) {
                jSONObject.put("queueData", mediaQueueData.zzb());
            }
            jSONObject.putOpt("autoplay", this.zze);
            long j10 = this.zzf;
            if (j10 != -1) {
                jSONObject.put("currentTime", CastUtils.millisecToSec(j10));
            }
            jSONObject.put("playbackRate", this.zzg);
            jSONObject.putOpt("credentials", this.zzj);
            jSONObject.putOpt("credentialsType", this.zzk);
            jSONObject.putOpt("atvCredentials", this.zzl);
            jSONObject.putOpt("atvCredentialsType", this.zzm);
            long[] jArr = this.zzh;
            if (jArr != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i10 = 0; i10 < jArr.length; i10++) {
                    jSONArray.put(i10, jArr[i10]);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            jSONObject.putOpt("customData", this.zzi);
            jSONObject.put("requestId", this.zzn);
            return jSONObject;
        } catch (JSONException e5) {
            zzb.e("Error transforming MediaLoadRequestData into JSONObject", e5);
            return new JSONObject();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzi;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, getMediaInfo(), i10, false);
        a.a.f0(parcel, 3, getQueueData(), i10, false);
        a.a.Y(parcel, 4, getAutoplay());
        long currentTime = getCurrentTime();
        a.a.l0(parcel, 5, 8);
        parcel.writeLong(currentTime);
        double playbackRate = getPlaybackRate();
        a.a.l0(parcel, 6, 8);
        parcel.writeDouble(playbackRate);
        a.a.e0(parcel, 7, getActiveTrackIds(), false);
        a.a.g0(parcel, 8, this.zza, false);
        a.a.g0(parcel, 9, getCredentials(), false);
        a.a.g0(parcel, 10, getCredentialsType(), false);
        a.a.g0(parcel, 11, this.zzl, false);
        a.a.g0(parcel, 12, this.zzm, false);
        long requestId = getRequestId();
        a.a.l0(parcel, 13, 8);
        parcel.writeLong(requestId);
        a.a.n0(iM0, parcel);
    }

    public final String zza() {
        return this.zzl;
    }

    public final String zzb() {
        return this.zzm;
    }

    public /* synthetic */ MediaLoadRequestData(MediaInfo mediaInfo, MediaQueueData mediaQueueData, Boolean bool, long j10, double d4, long[] jArr, JSONObject jSONObject, String str, String str2, String str3, String str4, long j11, byte[] bArr) {
        this(mediaInfo, mediaQueueData, bool, j10, d4, jArr, jSONObject, str, str2, str3, str4, j11);
    }

    public MediaLoadRequestData(MediaInfo mediaInfo, MediaQueueData mediaQueueData, Boolean bool, long j10, double d4, long[] jArr, String str, String str2, String str3, String str4, String str5, long j11) {
        this(mediaInfo, mediaQueueData, bool, j10, d4, jArr, CastUtils.jsonStringToJsonObject(str), str2, str3, str4, str5, j11);
    }
}
