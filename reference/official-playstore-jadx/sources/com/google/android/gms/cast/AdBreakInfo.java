package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class AdBreakInfo extends a {
    public static final Parcelable.Creator<AdBreakInfo> CREATOR = new zzb();
    private final long zza;
    private final String zzb;
    private final long zzc;
    private final boolean zzd;
    private final String[] zze;
    private final boolean zzf;
    private final boolean zzg;

    public static class Builder {
        private final long zza;
        private String zzb;
        private long zzc;
        private boolean zzd;
        private boolean zze;
        private String[] zzf;
        private boolean zzg;

        public Builder(long j10) {
            this.zza = j10;
        }

        public AdBreakInfo build() {
            return new AdBreakInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg);
        }

        public Builder setBreakClipIds(String[] strArr) {
            this.zzf = strArr;
            return this;
        }

        public Builder setDurationInMs(long j10) {
            this.zzc = j10;
            return this;
        }

        public Builder setId(String str) {
            this.zzb = str;
            return this;
        }

        public Builder setIsEmbedded(boolean z) {
            this.zze = z;
            return this;
        }

        public Builder setIsExpanded(boolean z) {
            this.zzg = z;
            return this;
        }

        public Builder setIsWatched(boolean z) {
            this.zzd = z;
            return this;
        }
    }

    public AdBreakInfo(long j10, String str, long j11, boolean z, String[] strArr, boolean z5, boolean z10) {
        this.zza = j10;
        this.zzb = str;
        this.zzc = j11;
        this.zzd = z;
        this.zze = strArr;
        this.zzf = z5;
        this.zzg = z10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        return CastUtils.zza(this.zzb, adBreakInfo.zzb) && this.zza == adBreakInfo.zza && this.zzc == adBreakInfo.zzc && this.zzd == adBreakInfo.zzd && Arrays.equals(this.zze, adBreakInfo.zze) && this.zzf == adBreakInfo.zzf && this.zzg == adBreakInfo.zzg;
    }

    public String[] getBreakClipIds() {
        return this.zze;
    }

    public long getDurationInMs() {
        return this.zzc;
    }

    public String getId() {
        return this.zzb;
    }

    public long getPlaybackPositionInMs() {
        return this.zza;
    }

    public int hashCode() {
        return this.zzb.hashCode();
    }

    public boolean isEmbedded() {
        return this.zzf;
    }

    public boolean isExpanded() {
        return this.zzg;
    }

    public boolean isWatched() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        long playbackPositionInMs = getPlaybackPositionInMs();
        a.a.l0(parcel, 2, 8);
        parcel.writeLong(playbackPositionInMs);
        a.a.g0(parcel, 3, getId(), false);
        long durationInMs = getDurationInMs();
        a.a.l0(parcel, 4, 8);
        parcel.writeLong(durationInMs);
        boolean zIsWatched = isWatched();
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(zIsWatched ? 1 : 0);
        a.a.h0(parcel, 6, getBreakClipIds(), false);
        boolean zIsEmbedded = isEmbedded();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(zIsEmbedded ? 1 : 0);
        boolean zIsExpanded = isExpanded();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(zIsExpanded ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TtmlNode.ATTR_ID, this.zzb);
            jSONObject.put("position", CastUtils.millisecToSec(this.zza));
            jSONObject.put("isWatched", this.zzd);
            jSONObject.put("isEmbedded", this.zzf);
            jSONObject.put("duration", CastUtils.millisecToSec(this.zzc));
            jSONObject.put("expanded", this.zzg);
            String[] strArr = this.zze;
            if (strArr != null) {
                JSONArray jSONArray = new JSONArray();
                for (String str : strArr) {
                    jSONArray.put(str);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
