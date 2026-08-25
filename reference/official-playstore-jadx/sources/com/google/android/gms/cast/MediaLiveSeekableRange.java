package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaLiveSeekableRange extends a {
    private final long zzb;
    private final long zzc;
    private final boolean zzd;
    private final boolean zze;
    private static final Logger zza = new Logger("MediaLiveSeekableRange");
    public static final Parcelable.Creator<MediaLiveSeekableRange> CREATOR = new zzbs();

    public static class Builder {
        private long zza;
        private long zzb;
        private boolean zzc;
        private boolean zzd;

        public MediaLiveSeekableRange build() {
            return new MediaLiveSeekableRange(this.zza, this.zzb, this.zzc, this.zzd);
        }

        public Builder setEndTime(long j10) {
            this.zzb = j10;
            return this;
        }

        public Builder setIsLiveDone(boolean z) {
            this.zzd = z;
            return this;
        }

        public Builder setIsMovingWindow(boolean z) {
            this.zzc = z;
            return this;
        }

        public Builder setStartTime(long j10) {
            this.zza = j10;
            return this;
        }
    }

    public MediaLiveSeekableRange(long j10, long j11, boolean z, boolean z5) {
        this.zzb = Math.max(j10, 0L);
        this.zzc = Math.max(j11, 0L);
        this.zzd = z;
        this.zze = z5;
    }

    public static MediaLiveSeekableRange zzb(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(TtmlNode.START) && jSONObject.has(TtmlNode.END)) {
            try {
                return new MediaLiveSeekableRange(CastUtils.secToMillisec(jSONObject.getDouble(TtmlNode.START)), CastUtils.secToMillisec(jSONObject.getDouble(TtmlNode.END)), jSONObject.optBoolean("isMovingWindow"), jSONObject.optBoolean("isLiveDone"));
            } catch (JSONException unused) {
                zza.e("Ignoring Malformed MediaLiveSeekableRange: ".concat(jSONObject.toString()), new Object[0]);
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLiveSeekableRange)) {
            return false;
        }
        MediaLiveSeekableRange mediaLiveSeekableRange = (MediaLiveSeekableRange) obj;
        return this.zzb == mediaLiveSeekableRange.zzb && this.zzc == mediaLiveSeekableRange.zzc && this.zzd == mediaLiveSeekableRange.zzd && this.zze == mediaLiveSeekableRange.zze;
    }

    public long getEndTime() {
        return this.zzc;
    }

    public long getStartTime() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzb), Long.valueOf(this.zzc), Boolean.valueOf(this.zzd), Boolean.valueOf(this.zze)});
    }

    public boolean isLiveDone() {
        return this.zze;
    }

    public boolean isMovingWindow() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        long startTime = getStartTime();
        a.a.l0(parcel, 2, 8);
        parcel.writeLong(startTime);
        long endTime = getEndTime();
        a.a.l0(parcel, 3, 8);
        parcel.writeLong(endTime);
        boolean zIsMovingWindow = isMovingWindow();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(zIsMovingWindow ? 1 : 0);
        boolean zIsLiveDone = isLiveDone();
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(zIsLiveDone ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TtmlNode.START, CastUtils.millisecToSec(this.zzb));
            jSONObject.put(TtmlNode.END, CastUtils.millisecToSec(this.zzc));
            jSONObject.put("isMovingWindow", this.zzd);
            jSONObject.put("isLiveDone", this.zze);
            return jSONObject;
        } catch (JSONException unused) {
            zza.e("Error transforming MediaLiveSeekableRange into JSONObject", new Object[0]);
            return new JSONObject();
        }
    }
}
