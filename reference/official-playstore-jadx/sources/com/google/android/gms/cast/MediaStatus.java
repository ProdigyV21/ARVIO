package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import c4.e;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.t;
import io.ktor.http.LinkHeader;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaStatus extends a {
    public static final long COMMAND_DISLIKE = 32768;
    public static final long COMMAND_EDIT_TRACKS = 4096;
    public static final long COMMAND_FOLLOW = 65536;
    public static final long COMMAND_LIKE = 16384;
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_PLAYBACK_RATE = 8192;
    public static final long COMMAND_QUEUE_NEXT = 64;
    public static final long COMMAND_QUEUE_PREVIOUS = 128;
    public static final long COMMAND_QUEUE_REPEAT = 3072;
    public static final long COMMAND_QUEUE_REPEAT_ALL = 1024;
    public static final long COMMAND_QUEUE_REPEAT_ONE = 2048;
    public static final long COMMAND_QUEUE_SHUFFLE = 256;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_AD = 512;

    @Deprecated
    public static final long COMMAND_SKIP_BACKWARD = 32;

    @Deprecated
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_STREAM_TRANSFER = 262144;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final long COMMAND_UNFOLLOW = 131072;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_LOADING = 5;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    MediaInfo zza;
    long zzb;
    int zzc;
    double zzd;
    int zze;
    int zzf;
    long zzg;
    long zzh;
    double zzi;
    boolean zzj;
    long[] zzk;
    int zzl;
    int zzm;
    String zzn;
    JSONObject zzo;
    int zzp;
    final List zzq;
    boolean zzr;
    AdBreakStatus zzs;
    VideoInfo zzt;
    MediaLiveSeekableRange zzu;
    MediaQueueData zzv;
    boolean zzw;
    private final SparseArray zzy;
    private final Writer zzz;
    private static final Logger zzx = new Logger("MediaStatus");
    public static final Parcelable.Creator<MediaStatus> CREATOR = new zzbz();

    public static class Builder {
        private MediaInfo zza;
        private long zzb;
        private double zzd;
        private long zzg;
        private long zzh;
        private double zzi;
        private boolean zzj;
        private long[] zzk;
        private JSONObject zzn;
        private boolean zzq;
        private AdBreakStatus zzr;
        private VideoInfo zzs;
        private MediaLiveSeekableRange zzt;
        private MediaQueueData zzu;
        private int zzc = 0;
        private int zze = 0;
        private int zzf = 0;
        private int zzl = 0;
        private int zzm = 0;
        private int zzo = 0;
        private final List zzp = new ArrayList();

        public MediaStatus build() {
            MediaStatus mediaStatus = new MediaStatus(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, null, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu);
            mediaStatus.zzo = this.zzn;
            return mediaStatus;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzk = jArr;
            return this;
        }

        public Builder setAdBreakStatus(AdBreakStatus adBreakStatus) {
            this.zzr = adBreakStatus;
            return this;
        }

        public Builder setCurrentItemId(int i10) {
            this.zzc = i10;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzn = jSONObject;
            return this;
        }

        public Builder setIdleReason(int i10) {
            this.zzf = i10;
            return this;
        }

        public Builder setIsMute(boolean z) {
            this.zzj = z;
            return this;
        }

        public Builder setIsPlayingAd(boolean z) {
            this.zzq = z;
            return this;
        }

        public Builder setLiveSeekableRange(MediaLiveSeekableRange mediaLiveSeekableRange) {
            this.zzt = mediaLiveSeekableRange;
            return this;
        }

        public Builder setLoadingItemId(int i10) {
            this.zzl = i10;
            return this;
        }

        public Builder setMediaInfo(MediaInfo mediaInfo) {
            this.zza = mediaInfo;
            return this;
        }

        public Builder setMediaSessionId(long j10) {
            this.zzb = j10;
            return this;
        }

        public Builder setPlaybackRate(double d4) {
            this.zzd = d4;
            return this;
        }

        public Builder setPlayerState(int i10) {
            this.zze = i10;
            return this;
        }

        public Builder setPreloadedItemId(int i10) {
            this.zzm = i10;
            return this;
        }

        public Builder setQueueData(MediaQueueData mediaQueueData) {
            this.zzu = mediaQueueData;
            return this;
        }

        public Builder setQueueItems(List<MediaQueueItem> list) {
            List list2 = this.zzp;
            list2.clear();
            list2.addAll(list);
            return this;
        }

        public Builder setQueueRepeatMode(int i10) {
            this.zzo = i10;
            return this;
        }

        public Builder setStreamPosition(long j10) {
            this.zzg = j10;
            return this;
        }

        public Builder setStreamVolume(double d4) {
            this.zzi = d4;
            return this;
        }

        public Builder setSupportedMediaCommands(long j10) {
            this.zzh = j10;
            return this;
        }

        public Builder setVideoInfo(VideoInfo videoInfo) {
            this.zzs = videoInfo;
            return this;
        }
    }

    public class Writer {
        final /* synthetic */ MediaStatus zza;

        public Writer(MediaStatus mediaStatus) {
            Objects.requireNonNull(mediaStatus);
            this.zza = mediaStatus;
        }

        public void setActiveTrackIds(long[] jArr) {
            this.zza.zzk = jArr;
        }

        public void setAdBreakStatus(AdBreakStatus adBreakStatus) {
            this.zza.zzs = adBreakStatus;
        }

        public void setCurrentItemId(int i10) {
            this.zza.zzc = i10;
        }

        public void setCustomData(JSONObject jSONObject) {
            MediaStatus mediaStatus = this.zza;
            mediaStatus.zzo = jSONObject;
            mediaStatus.zzn = null;
        }

        public void setIdleReason(int i10) {
            this.zza.zzf = i10;
        }

        public void setIsPlayingAd(boolean z) {
            this.zza.zzr = z;
        }

        public void setLiveSeekableRange(MediaLiveSeekableRange mediaLiveSeekableRange) {
            this.zza.zzu = mediaLiveSeekableRange;
        }

        public void setLoadingItemId(int i10) {
            this.zza.zzl = i10;
        }

        public void setMediaInfo(MediaInfo mediaInfo) {
            this.zza.zza = mediaInfo;
        }

        public void setMute(boolean z) {
            this.zza.zzj = z;
        }

        public void setPlaybackRate(double d4) {
            this.zza.zzd = d4;
        }

        public void setPlayerState(int i10) {
            this.zza.zze = i10;
        }

        public void setPreloadedItemId(int i10) {
            this.zza.zzm = i10;
        }

        public void setQueueData(MediaQueueData mediaQueueData) {
            this.zza.zzv = mediaQueueData;
        }

        public void setQueueItems(List<MediaQueueItem> list) {
            this.zza.zzd(list);
        }

        public void setQueueRepeatMode(int i10) {
            this.zza.zzp = i10;
        }

        public void setShuffle(boolean z) {
            this.zza.zzw = z;
        }

        public void setStreamPosition(long j10) {
            this.zza.zzg = j10;
        }

        public void setStreamVolume(double d4) {
            this.zza.zzi = d4;
        }

        public void setSupportedMediaCommands(long j10) {
            this.zza.zzh = j10;
        }

        public void setVideoInfo(VideoInfo videoInfo) {
            this.zza.zzt = videoInfo;
        }
    }

    public MediaStatus(MediaInfo mediaInfo, long j10, int i10, double d4, int i11, int i12, long j11, long j12, double d10, boolean z, long[] jArr, int i13, int i14, String str, int i15, List list, boolean z5, AdBreakStatus adBreakStatus, VideoInfo videoInfo, MediaLiveSeekableRange mediaLiveSeekableRange, MediaQueueData mediaQueueData) {
        this.zzq = new ArrayList();
        this.zzy = new SparseArray();
        this.zzz = new Writer(this);
        this.zza = mediaInfo;
        this.zzb = j10;
        this.zzc = i10;
        this.zzd = d4;
        this.zze = i11;
        this.zzf = i12;
        this.zzg = j11;
        this.zzh = j12;
        this.zzi = d10;
        this.zzj = z;
        this.zzk = jArr;
        this.zzl = i13;
        this.zzm = i14;
        this.zzn = str;
        if (str != null) {
            try {
                this.zzo = new JSONObject(this.zzn);
            } catch (JSONException unused) {
                this.zzo = null;
                this.zzn = null;
            }
        } else {
            this.zzo = null;
        }
        this.zzp = i15;
        if (list != null && !list.isEmpty()) {
            zzd(list);
        }
        this.zzr = z5;
        this.zzs = adBreakStatus;
        this.zzt = videoInfo;
        this.zzu = mediaLiveSeekableRange;
        this.zzv = mediaQueueData;
        boolean z10 = false;
        if (mediaQueueData != null && mediaQueueData.zza()) {
            z10 = true;
        }
        this.zzw = z10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
    public final void zzd(List list) {
        List list2 = this.zzq;
        list2.clear();
        SparseArray sparseArray = this.zzy;
        sparseArray.clear();
        if (list != null) {
            for (int i10 = 0; i10 < list.size(); i10++) {
                MediaQueueItem mediaQueueItem = (MediaQueueItem) list.get(i10);
                list2.add(mediaQueueItem);
                sparseArray.put(mediaQueueItem.getItemId(), Integer.valueOf(i10));
            }
        }
    }

    private static final boolean zzf(int i10, int i11, int i12, int i13) {
        if (i10 != 1) {
            return false;
        }
        if (i11 != 1) {
            if (i11 == 2) {
                return i13 != 2;
            }
            if (i11 != 3) {
                return true;
            }
        }
        return i12 == 0;
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        return (this.zzo == null) == (mediaStatus.zzo == null) && this.zzb == mediaStatus.zzb && this.zzc == mediaStatus.zzc && this.zzd == mediaStatus.zzd && this.zze == mediaStatus.zze && this.zzf == mediaStatus.zzf && this.zzg == mediaStatus.zzg && this.zzi == mediaStatus.zzi && this.zzj == mediaStatus.zzj && this.zzl == mediaStatus.zzl && this.zzm == mediaStatus.zzm && this.zzp == mediaStatus.zzp && Arrays.equals(this.zzk, mediaStatus.zzk) && CastUtils.zza(Long.valueOf(this.zzh), Long.valueOf(mediaStatus.zzh)) && CastUtils.zza(this.zzq, mediaStatus.zzq) && CastUtils.zza(this.zza, mediaStatus.zza) && ((jSONObject = this.zzo) == null || (jSONObject2 = mediaStatus.zzo) == null || e.a(jSONObject, jSONObject2)) && this.zzr == mediaStatus.isPlayingAd() && CastUtils.zza(this.zzs, mediaStatus.zzs) && CastUtils.zza(this.zzt, mediaStatus.zzt) && CastUtils.zza(this.zzu, mediaStatus.zzu) && t.l(this.zzv, mediaStatus.zzv) && this.zzw == mediaStatus.zzw;
    }

    public long[] getActiveTrackIds() {
        return this.zzk;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzs;
    }

    public AdBreakInfo getCurrentAdBreak() {
        MediaInfo mediaInfo;
        List<AdBreakInfo> adBreaks;
        AdBreakStatus adBreakStatus = this.zzs;
        if (adBreakStatus == null) {
            return null;
        }
        String breakId = adBreakStatus.getBreakId();
        if (!TextUtils.isEmpty(breakId) && (mediaInfo = this.zza) != null && (adBreaks = mediaInfo.getAdBreaks()) != null && !adBreaks.isEmpty()) {
            for (AdBreakInfo adBreakInfo : adBreaks) {
                if (breakId.equals(adBreakInfo.getId())) {
                    return adBreakInfo;
                }
            }
        }
        return null;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        MediaInfo mediaInfo;
        List<AdBreakClipInfo> adBreakClips;
        AdBreakStatus adBreakStatus = this.zzs;
        if (adBreakStatus == null) {
            return null;
        }
        String breakClipId = adBreakStatus.getBreakClipId();
        if (!TextUtils.isEmpty(breakClipId) && (mediaInfo = this.zza) != null && (adBreakClips = mediaInfo.getAdBreakClips()) != null && !adBreakClips.isEmpty()) {
            for (AdBreakClipInfo adBreakClipInfo : adBreakClips) {
                if (breakClipId.equals(adBreakClipInfo.getId())) {
                    return adBreakClipInfo;
                }
            }
        }
        return null;
    }

    public int getCurrentItemId() {
        return this.zzc;
    }

    public JSONObject getCustomData() {
        return this.zzo;
    }

    public int getIdleReason() {
        return this.zzf;
    }

    public Integer getIndexById(int i10) {
        return (Integer) this.zzy.get(i10);
    }

    public MediaQueueItem getItemById(int i10) {
        Integer num = (Integer) this.zzy.get(i10);
        if (num == null) {
            return null;
        }
        return (MediaQueueItem) this.zzq.get(num.intValue());
    }

    public MediaQueueItem getItemByIndex(int i10) {
        if (i10 < 0) {
            return null;
        }
        List list = this.zzq;
        if (i10 >= list.size()) {
            return null;
        }
        return (MediaQueueItem) list.get(i10);
    }

    public MediaLiveSeekableRange getLiveSeekableRange() {
        return this.zzu;
    }

    public int getLoadingItemId() {
        return this.zzl;
    }

    public MediaInfo getMediaInfo() {
        return this.zza;
    }

    public double getPlaybackRate() {
        return this.zzd;
    }

    public int getPlayerState() {
        return this.zze;
    }

    public int getPreloadedItemId() {
        return this.zzm;
    }

    public MediaQueueData getQueueData() {
        return this.zzv;
    }

    public MediaQueueItem getQueueItem(int i10) {
        return getItemByIndex(i10);
    }

    public MediaQueueItem getQueueItemById(int i10) {
        return getItemById(i10);
    }

    public int getQueueItemCount() {
        return this.zzq.size();
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzq;
    }

    public int getQueueRepeatMode() {
        return this.zzp;
    }

    public long getStreamPosition() {
        return this.zzg;
    }

    public double getStreamVolume() {
        return this.zzi;
    }

    public long getSupportedMediaCommands() {
        return this.zzh;
    }

    public VideoInfo getVideoInfo() {
        return this.zzt;
    }

    public Writer getWriter() {
        return this.zzz;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Long.valueOf(this.zzb), Integer.valueOf(this.zzc), Double.valueOf(this.zzd), Integer.valueOf(this.zze), Integer.valueOf(this.zzf), Long.valueOf(this.zzg), Long.valueOf(this.zzh), Double.valueOf(this.zzi), Boolean.valueOf(this.zzj), Integer.valueOf(Arrays.hashCode(this.zzk)), Integer.valueOf(this.zzl), Integer.valueOf(this.zzm), String.valueOf(this.zzo), Integer.valueOf(this.zzp), this.zzq, Boolean.valueOf(this.zzr), this.zzs, this.zzt, this.zzu, this.zzv});
    }

    public boolean isMediaCommandSupported(long j10) {
        return (j10 & this.zzh) != 0;
    }

    public boolean isMute() {
        return this.zzj;
    }

    public boolean isPlayingAd() {
        return this.zzr;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mediaSessionId", this.zzb);
            int i10 = this.zze;
            String str = "IDLE";
            if (i10 != 1) {
                if (i10 == 2) {
                    str = "PLAYING";
                } else if (i10 == 3) {
                    str = "PAUSED";
                } else if (i10 == 4) {
                    str = "BUFFERING";
                } else if (i10 == 5) {
                    str = "LOADING";
                }
            }
            jSONObject.put("playerState", str);
            JSONArray jSONArray = null;
            if (this.zze == 1) {
                int i11 = this.zzf;
                jSONObject.putOpt("idleReason", i11 != 1 ? i11 != 2 ? i11 != 3 ? i11 != 4 ? null : MediaError.ERROR_TYPE_ERROR : "INTERRUPTED" : "CANCELLED" : "FINISHED");
            }
            jSONObject.put("playbackRate", this.zzd);
            jSONObject.put("currentTime", CastUtils.millisecToSec(this.zzg));
            jSONObject.put("supportedMediaCommands", this.zzh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("level", this.zzi);
            jSONObject2.put("muted", this.zzj);
            jSONObject.put("volume", jSONObject2);
            if (this.zzk != null) {
                jSONArray = new JSONArray();
                for (long j10 : this.zzk) {
                    jSONArray.put(j10);
                }
            }
            jSONObject.putOpt("activeTrackIds", jSONArray);
            jSONObject.putOpt("customData", this.zzo);
            jSONObject.putOpt("shuffle", Boolean.valueOf(this.zzw));
            MediaInfo mediaInfo = this.zza;
            if (mediaInfo != null) {
                jSONObject.putOpt(LinkHeader.Parameters.Media, mediaInfo.zzb());
            }
            int i12 = this.zzc;
            if (i12 != 0) {
                jSONObject.put("currentItemId", i12);
            }
            int i13 = this.zzm;
            if (i13 != 0) {
                jSONObject.put("preloadedItemId", i13);
            }
            int i14 = this.zzl;
            if (i14 != 0) {
                jSONObject.put("loadingItemId", i14);
            }
            AdBreakStatus adBreakStatus = this.zzs;
            if (adBreakStatus != null) {
                jSONObject.putOpt("breakStatus", adBreakStatus.zza());
            }
            VideoInfo videoInfo = this.zzt;
            if (videoInfo != null) {
                jSONObject.putOpt("videoInfo", videoInfo.zza());
            }
            MediaQueueData mediaQueueData = this.zzv;
            if (mediaQueueData != null) {
                jSONObject.putOpt("queueData", mediaQueueData.zzb());
            }
            MediaLiveSeekableRange mediaLiveSeekableRange = this.zzu;
            if (mediaLiveSeekableRange != null) {
                jSONObject.putOpt("liveSeekableRange", mediaLiveSeekableRange.zza());
            }
            jSONObject.putOpt("repeatMode", MediaCommon.zza(Integer.valueOf(this.zzp)));
            List list = this.zzq;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    jSONArray2.put(((MediaQueueItem) it.next()).toJson());
                }
                jSONObject.put("items", jSONArray2);
            }
            return jSONObject;
        } catch (JSONException e5) {
            zzx.e(e5, "Error transforming MediaStatus into JSONObject", new Object[0]);
            return new JSONObject();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzo;
        this.zzn = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, getMediaInfo(), i10, false);
        long j10 = this.zzb;
        a.a.l0(parcel, 3, 8);
        parcel.writeLong(j10);
        int currentItemId = getCurrentItemId();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(currentItemId);
        double playbackRate = getPlaybackRate();
        a.a.l0(parcel, 5, 8);
        parcel.writeDouble(playbackRate);
        int playerState = getPlayerState();
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(playerState);
        int idleReason = getIdleReason();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(idleReason);
        long streamPosition = getStreamPosition();
        a.a.l0(parcel, 8, 8);
        parcel.writeLong(streamPosition);
        long j11 = this.zzh;
        a.a.l0(parcel, 9, 8);
        parcel.writeLong(j11);
        double streamVolume = getStreamVolume();
        a.a.l0(parcel, 10, 8);
        parcel.writeDouble(streamVolume);
        boolean zIsMute = isMute();
        a.a.l0(parcel, 11, 4);
        parcel.writeInt(zIsMute ? 1 : 0);
        a.a.e0(parcel, 12, getActiveTrackIds(), false);
        int loadingItemId = getLoadingItemId();
        a.a.l0(parcel, 13, 4);
        parcel.writeInt(loadingItemId);
        int preloadedItemId = getPreloadedItemId();
        a.a.l0(parcel, 14, 4);
        parcel.writeInt(preloadedItemId);
        a.a.g0(parcel, 15, this.zzn, false);
        int i11 = this.zzp;
        a.a.l0(parcel, 16, 4);
        parcel.writeInt(i11);
        a.a.k0(parcel, 17, this.zzq, false);
        boolean zIsPlayingAd = isPlayingAd();
        a.a.l0(parcel, 18, 4);
        parcel.writeInt(zIsPlayingAd ? 1 : 0);
        a.a.f0(parcel, 19, getAdBreakStatus(), i10, false);
        a.a.f0(parcel, 20, getVideoInfo(), i10, false);
        a.a.f0(parcel, 21, getLiveSeekableRange(), i10, false);
        a.a.f0(parcel, 22, getQueueData(), i10, false);
        a.a.n0(iM0, parcel);
    }

    public final long zza() {
        return this.zzb;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x018e A[EDGE_INSN: B:102:0x018e->B:103:0x0192 BREAK  A[LOOP:0: B:95:0x017a->B:99:0x0187]] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzb(org.json.JSONObject r14, int r15) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 914
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.zzb(org.json.JSONObject, int):int");
    }

    public final boolean zzc() {
        MediaInfo mediaInfo = this.zza;
        return zzf(this.zze, this.zzf, this.zzl, mediaInfo == null ? -1 : mediaInfo.getStreamType());
    }

    public MediaStatus(JSONObject jSONObject) throws JSONException {
        this(null, 0L, 0, 0.0d, 0, 0, 0L, 0L, 0.0d, false, null, 0, 0, null, 0, null, false, null, null, null, null);
        zzb(jSONObject, 0);
    }
}
