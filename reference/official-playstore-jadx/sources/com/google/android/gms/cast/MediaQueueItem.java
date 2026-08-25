package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import c4.e;
import com.google.android.gms.cast.internal.CastUtils;
import io.ktor.http.LinkHeader;
import j$.util.Objects;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueueItem extends a {
    public static final Parcelable.Creator<MediaQueueItem> CREATOR = new zzby();
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    String zza;
    private MediaInfo zzb;
    private int zzc;
    private boolean zzd;
    private double zze;
    private double zzf;
    private double zzg;
    private long[] zzh;
    private JSONObject zzi;
    private final Writer zzj;

    public static class Builder {
        private final MediaQueueItem zza;

        public Builder(MediaInfo mediaInfo) throws IllegalArgumentException {
            this.zza = new MediaQueueItem(mediaInfo, (byte[]) null);
        }

        public MediaQueueItem build() {
            MediaQueueItem mediaQueueItem = this.zza;
            mediaQueueItem.zza();
            return mediaQueueItem;
        }

        public Builder clearItemId() {
            this.zza.getWriter().setItemId(0);
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zza.getWriter().setActiveTrackIds(jArr);
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zza.getWriter().setAutoplay(z);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zza.getWriter().setCustomData(jSONObject);
            return this;
        }

        public Builder setItemId(int i10) {
            this.zza.getWriter().setItemId(i10);
            return this;
        }

        public Builder setPlaybackDuration(double d4) {
            this.zza.getWriter().setPlaybackDuration(d4);
            return this;
        }

        public Builder setPreloadTime(double d4) throws IllegalArgumentException {
            this.zza.getWriter().setPreloadTime(d4);
            return this;
        }

        public Builder setStartTime(double d4) throws IllegalArgumentException {
            this.zza.getWriter().setStartTime(d4);
            return this;
        }

        public Builder(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
            this.zza = new MediaQueueItem(mediaQueueItem, (byte[]) null);
        }

        public Builder(JSONObject jSONObject) throws JSONException {
            this.zza = new MediaQueueItem(jSONObject);
        }
    }

    public class Writer {
        final /* synthetic */ MediaQueueItem zza;

        public Writer(MediaQueueItem mediaQueueItem) {
            Objects.requireNonNull(mediaQueueItem);
            this.zza = mediaQueueItem;
        }

        public void setActiveTrackIds(long[] jArr) {
            this.zza.zzh(jArr);
        }

        public void setAutoplay(boolean z) {
            this.zza.zzd(z);
        }

        public void setCustomData(JSONObject jSONObject) {
            this.zza.zzi(jSONObject);
        }

        public void setItemId(int i10) {
            this.zza.zzc(i10);
        }

        public void setMedia(MediaInfo mediaInfo) {
            this.zza.zzb(mediaInfo);
        }

        public void setPlaybackDuration(double d4) {
            if (Double.isNaN(d4)) {
                throw new IllegalArgumentException("playbackDuration cannot be NaN.");
            }
            this.zza.zzf(d4);
        }

        public void setPreloadTime(double d4) {
            if (Double.isNaN(d4) || d4 < 0.0d) {
                throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
            }
            this.zza.zzg(d4);
        }

        public void setStartTime(double d4) {
            if (!Double.isNaN(d4) && d4 < 0.0d) {
                throw new IllegalArgumentException("startTime cannot be negative.");
            }
            this.zza.zze(d4);
        }
    }

    public MediaQueueItem(MediaInfo mediaInfo, int i10, boolean z, double d4, double d10, double d11, long[] jArr, String str) {
        this.zze = Double.NaN;
        this.zzj = new Writer(this);
        this.zzb = mediaInfo;
        this.zzc = i10;
        this.zzd = z;
        this.zze = d4;
        this.zzf = d10;
        this.zzg = d11;
        this.zzh = jArr;
        this.zza = str;
        if (str == null) {
            this.zzi = null;
            return;
        }
        try {
            this.zzi = new JSONObject(this.zza);
        } catch (JSONException unused) {
            this.zzi = null;
            this.zza = null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        JSONObject jSONObject = this.zzi;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaQueueItem.zzi;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || e.a(jSONObject, jSONObject2)) && CastUtils.zza(this.zzb, mediaQueueItem.zzb) && this.zzc == mediaQueueItem.zzc && this.zzd == mediaQueueItem.zzd && ((Double.isNaN(this.zze) && Double.isNaN(mediaQueueItem.zze)) || this.zze == mediaQueueItem.zze) && this.zzf == mediaQueueItem.zzf && this.zzg == mediaQueueItem.zzg && Arrays.equals(this.zzh, mediaQueueItem.zzh);
    }

    public boolean fromJson(JSONObject jSONObject) throws JSONException {
        boolean z;
        long[] jArr;
        boolean z5;
        int i10;
        boolean z10 = false;
        if (jSONObject.has(LinkHeader.Parameters.Media)) {
            this.zzb = new MediaInfo(jSONObject.getJSONObject(LinkHeader.Parameters.Media));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("itemId") && this.zzc != (i10 = jSONObject.getInt("itemId"))) {
            this.zzc = i10;
            z = true;
        }
        if (jSONObject.has("autoplay") && this.zzd != (z5 = jSONObject.getBoolean("autoplay"))) {
            this.zzd = z5;
            z = true;
        }
        double dOptDouble = jSONObject.optDouble("startTime");
        if (Double.isNaN(dOptDouble) != Double.isNaN(this.zze) || (!Double.isNaN(dOptDouble) && Math.abs(dOptDouble - this.zze) > 1.0E-7d)) {
            this.zze = dOptDouble;
            z = true;
        }
        if (jSONObject.has("playbackDuration")) {
            double d4 = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d4 - this.zzf) > 1.0E-7d) {
                this.zzf = d4;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            double d10 = jSONObject.getDouble("preloadTime");
            if (Math.abs(d10 - this.zzg) > 1.0E-7d) {
                this.zzg = d10;
                z = true;
            }
        }
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            jArr = new long[length];
            for (int i11 = 0; i11 < length; i11++) {
                jArr[i11] = jSONArray.getLong(i11);
            }
            long[] jArr2 = this.zzh;
            if (jArr2 == null || jArr2.length != length) {
                z10 = true;
                break;
            }
            for (int i12 = 0; i12 < length; i12++) {
                if (this.zzh[i12] != jArr[i12]) {
                    z10 = true;
                    break;
                }
            }
        } else {
            jArr = null;
        }
        if (z10) {
            this.zzh = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.zzi = jSONObject.getJSONObject("customData");
        return true;
    }

    public long[] getActiveTrackIds() {
        return this.zzh;
    }

    public boolean getAutoplay() {
        return this.zzd;
    }

    public JSONObject getCustomData() {
        return this.zzi;
    }

    public int getItemId() {
        return this.zzc;
    }

    public MediaInfo getMedia() {
        return this.zzb;
    }

    public double getPlaybackDuration() {
        return this.zzf;
    }

    public double getPreloadTime() {
        return this.zzg;
    }

    public double getStartTime() {
        return this.zze;
    }

    public Writer getWriter() {
        return this.zzj;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzb, Integer.valueOf(this.zzc), Boolean.valueOf(this.zzd), Double.valueOf(this.zze), Double.valueOf(this.zzf), Double.valueOf(this.zzg), Integer.valueOf(Arrays.hashCode(this.zzh)), String.valueOf(this.zzi)});
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            MediaInfo mediaInfo = this.zzb;
            if (mediaInfo != null) {
                jSONObject.put(LinkHeader.Parameters.Media, mediaInfo.zzb());
            }
            int i10 = this.zzc;
            if (i10 != 0) {
                jSONObject.put("itemId", i10);
            }
            jSONObject.put("autoplay", this.zzd);
            if (!Double.isNaN(this.zze)) {
                jSONObject.put("startTime", this.zze);
            }
            double d4 = this.zzf;
            if (d4 != Double.POSITIVE_INFINITY) {
                jSONObject.put("playbackDuration", d4);
            }
            jSONObject.put("preloadTime", this.zzg);
            if (this.zzh != null) {
                JSONArray jSONArray = new JSONArray();
                for (long j10 : this.zzh) {
                    jSONArray.put(j10);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject jSONObject2 = this.zzi;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzi;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, getMedia(), i10, false);
        int itemId = getItemId();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(itemId);
        boolean autoplay = getAutoplay();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(autoplay ? 1 : 0);
        double startTime = getStartTime();
        a.a.l0(parcel, 5, 8);
        parcel.writeDouble(startTime);
        double playbackDuration = getPlaybackDuration();
        a.a.l0(parcel, 6, 8);
        parcel.writeDouble(playbackDuration);
        double preloadTime = getPreloadTime();
        a.a.l0(parcel, 7, 8);
        parcel.writeDouble(preloadTime);
        a.a.e0(parcel, 8, getActiveTrackIds(), false);
        a.a.g0(parcel, 9, this.zza, false);
        a.a.n0(iM0, parcel);
    }

    public final void zza() throws IllegalArgumentException {
        if (this.zzb == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
        if (!Double.isNaN(this.zze) && this.zze < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        }
        if (Double.isNaN(this.zzf)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        }
        if (Double.isNaN(this.zzg) || this.zzg < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
        }
    }

    public final /* synthetic */ void zzb(MediaInfo mediaInfo) {
        this.zzb = mediaInfo;
    }

    public final /* synthetic */ void zzc(int i10) {
        this.zzc = i10;
    }

    public final /* synthetic */ void zzd(boolean z) {
        this.zzd = z;
    }

    public final /* synthetic */ void zze(double d4) {
        this.zze = d4;
    }

    public final /* synthetic */ void zzf(double d4) {
        this.zzf = d4;
    }

    public final /* synthetic */ void zzg(double d4) {
        this.zzg = d4;
    }

    public final /* synthetic */ void zzh(long[] jArr) {
        this.zzh = jArr;
    }

    public final /* synthetic */ void zzi(JSONObject jSONObject) {
        this.zzi = jSONObject;
    }

    public /* synthetic */ MediaQueueItem(MediaInfo mediaInfo, byte[] bArr) {
        this(mediaInfo, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, null, null);
        if (mediaInfo == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
    }

    public /* synthetic */ MediaQueueItem(MediaQueueItem mediaQueueItem, byte[] bArr) {
        this(mediaQueueItem.getMedia(), mediaQueueItem.getItemId(), mediaQueueItem.getAutoplay(), mediaQueueItem.getStartTime(), mediaQueueItem.getPlaybackDuration(), mediaQueueItem.getPreloadTime(), mediaQueueItem.getActiveTrackIds(), null);
        if (this.zzb != null) {
            this.zzi = mediaQueueItem.getCustomData();
            return;
        }
        throw new IllegalArgumentException("media cannot be null.");
    }

    public MediaQueueItem(JSONObject jSONObject) throws JSONException {
        this(null, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, null, null);
        fromJson(jSONObject);
    }
}
