package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import androidx.loader.content.j;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.i;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import org.json.JSONObject;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteMediaClient implements Cast.MessageReceivedCallback {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    private final com.google.android.gms.cast.internal.zzar zzd;
    private final zzax zze;

    @NotOnlyInitialized
    private final MediaQueue zzf;
    private com.google.android.gms.cast.zzq zzg;
    private i zzh;
    private ParseAdsInfoCallback zzm;
    private static final Logger zza = new Logger("RemoteMediaClient");
    public static final String NAMESPACE = com.google.android.gms.cast.internal.zzar.zzb;
    private final List zzi = new CopyOnWriteArrayList();
    private final List zzj = new CopyOnWriteArrayList();
    private final Map zzk = new ConcurrentHashMap();
    private final Map zzl = new ConcurrentHashMap();
    private final Object zzb = new Object();
    private final Handler zzc = new j(Looper.getMainLooper(), 2);

    public static abstract class Callback {
        public void onAdBreakStatusUpdated() {
        }

        public void onMediaError(MediaError mediaError) {
        }

        public void onMetadataUpdated() {
        }

        public void onPreloadStatusUpdated() {
        }

        public void onQueueStatusUpdated() {
        }

        public void onSendingRemoteMediaRequest() {
        }

        public void onStatusUpdated() {
        }

        public void zza(String str, long j10, int i10, long j11, long j12) {
        }

        public void zzb(int[] iArr) {
        }

        public void zzc(int[] iArr, int i10) {
        }

        public void zzd(int[] iArr) {
        }

        public void zze(int[] iArr) {
        }

        public void zzf(MediaQueueItem[] mediaQueueItemArr) {
        }

        public void zzg(List list, List list2, int i10) {
        }

        public void zzh() {
        }
    }

    @Deprecated
    public interface Listener {
        void onAdBreakStatusUpdated();

        void onMetadataUpdated();

        void onPreloadStatusUpdated();

        void onQueueStatusUpdated();

        void onSendingRemoteMediaRequest();

        void onStatusUpdated();
    }

    public interface MediaChannelResult extends x {
        JSONObject getCustomData();

        MediaError getMediaError();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();
    }

    public interface ParseAdsInfoCallback {
        List<AdBreakInfo> parseAdBreaksFromMediaStatus(MediaStatus mediaStatus);

        boolean parseIsPlayingAdFromMediaStatus(MediaStatus mediaStatus);
    }

    public interface ProgressListener {
        void onProgressUpdated(long j10, long j11);
    }

    public RemoteMediaClient(com.google.android.gms.cast.internal.zzar zzarVar) {
        zzax zzaxVar = new zzax(this);
        this.zze = zzaxVar;
        t.i(zzarVar);
        this.zzd = zzarVar;
        zzarVar.zzi(new zzbe(this, null));
        zzarVar.zze(zzaxVar);
        this.zzf = new MediaQueue(this, 20, 20);
    }

    public static v zzn(int i10, String str) {
        zzaz zzazVar = new zzaz();
        zzazVar.setResult(new zzay(zzazVar, new Status(i10, str, null, null)));
        return zzazVar;
    }

    private final boolean zzx() {
        return this.zzg != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzy, reason: merged with bridge method [inline-methods] */
    public final void zzo(Set set) {
        MediaInfo media;
        HashSet hashSet = new HashSet(set);
        if (isPlaying() || isPaused() || isBuffering() || zzi()) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((ProgressListener) it.next()).onProgressUpdated(getApproximateStreamPosition(), getStreamDuration());
            }
        } else {
            if (!isLoadingNextItem()) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((ProgressListener) it2.next()).onProgressUpdated(0L, 0L);
                }
                return;
            }
            MediaQueueItem loadingItem = getLoadingItem();
            if (loadingItem == null || (media = loadingItem.getMedia()) == null) {
                return;
            }
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                ((ProgressListener) it3.next()).onProgressUpdated(0L, media.getStreamDuration());
            }
        }
    }

    private static final zzbc zzz(zzbc zzbcVar) {
        try {
            zzbcVar.zzc();
            return zzbcVar;
        } catch (IllegalArgumentException e5) {
            throw e5;
        } catch (Throwable unused) {
            zzbcVar.setResult(new zzbb(zzbcVar, new Status(2100, null, null, null)));
            return zzbcVar;
        }
    }

    @Deprecated
    public void addListener(Listener listener) {
        t.e("Must be called from the main thread.");
        if (listener != null) {
            this.zzi.add(listener);
        }
    }

    public boolean addProgressListener(ProgressListener progressListener, long j10) {
        t.e("Must be called from the main thread.");
        if (progressListener == null) {
            return false;
        }
        Map map = this.zzk;
        if (map.containsKey(progressListener)) {
            return false;
        }
        Map map2 = this.zzl;
        Long lValueOf = Long.valueOf(j10);
        zzbg zzbgVar = (zzbg) map2.get(lValueOf);
        if (zzbgVar == null) {
            zzbgVar = new zzbg(this, j10);
            map2.put(lValueOf, zzbgVar);
        }
        zzbgVar.zzb(progressListener);
        map.put(progressListener, zzbgVar);
        if (!hasMediaSession()) {
            return true;
        }
        zzbgVar.zze();
        return true;
    }

    public long getApproximateAdBreakClipPositionMs() {
        long jZzy;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            jZzy = this.zzd.zzy();
        }
        return jZzy;
    }

    public long getApproximateLiveSeekableRangeEnd() {
        long jZzx;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            jZzx = this.zzd.zzx();
        }
        return jZzx;
    }

    public long getApproximateLiveSeekableRangeStart() {
        long jZzw;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            jZzw = this.zzd.zzw();
        }
        return jZzw;
    }

    public long getApproximateStreamPosition() {
        long jZzv;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            jZzv = this.zzd.zzv();
        }
        return jZzv;
    }

    public MediaQueueItem getCurrentItem() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getCurrentItemId());
    }

    public int getIdleReason() {
        int idleReason;
        synchronized (this.zzb) {
            try {
                t.e("Must be called from the main thread.");
                MediaStatus mediaStatus = getMediaStatus();
                idleReason = mediaStatus != null ? mediaStatus.getIdleReason() : 0;
            } catch (Throwable th) {
                throw th;
            }
        }
        return idleReason;
    }

    public MediaQueueItem getLoadingItem() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getLoadingItemId());
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfoZzB;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            mediaInfoZzB = this.zzd.zzB();
        }
        return mediaInfoZzB;
    }

    public MediaQueue getMediaQueue() {
        MediaQueue mediaQueue;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            mediaQueue = this.zzf;
        }
        return mediaQueue;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatusZzA;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            mediaStatusZzA = this.zzd.zzA();
        }
        return mediaStatusZzA;
    }

    public String getNamespace() {
        t.e("Must be called from the main thread.");
        return this.zzd.zzd();
    }

    public int getPlayerState() {
        int playerState;
        synchronized (this.zzb) {
            try {
                t.e("Must be called from the main thread.");
                MediaStatus mediaStatus = getMediaStatus();
                playerState = mediaStatus != null ? mediaStatus.getPlayerState() : 1;
            } catch (Throwable th) {
                throw th;
            }
        }
        return playerState;
    }

    public MediaQueueItem getPreloadedItem() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getPreloadedItemId());
    }

    public long getStreamDuration() {
        long jZzz;
        synchronized (this.zzb) {
            t.e("Must be called from the main thread.");
            jZzz = this.zzd.zzz();
        }
        return jZzz;
    }

    public boolean hasMediaSession() {
        t.e("Must be called from the main thread.");
        return isBuffering() || zzi() || isPlaying() || isPaused() || isLoadingNextItem();
    }

    public boolean isBuffering() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 4;
    }

    public boolean isLiveStream() {
        t.e("Must be called from the main thread.");
        MediaInfo mediaInfo = getMediaInfo();
        return mediaInfo != null && mediaInfo.getStreamType() == 2;
    }

    public boolean isLoadingNextItem() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || mediaStatus.getLoadingItemId() == 0) ? false : true;
    }

    public boolean isPaused() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return false;
        }
        if (mediaStatus.getPlayerState() != 3) {
            return isLiveStream() && getIdleReason() == 2;
        }
        return true;
    }

    public boolean isPlaying() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 2;
    }

    public boolean isPlayingAd() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.isPlayingAd();
    }

    @Deprecated
    public v load(MediaInfo mediaInfo) {
        return load(mediaInfo, new MediaLoadOptions.Builder().build());
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzd.zzL(str2);
    }

    public v pause() {
        return pause(null);
    }

    public v play() {
        return play(null);
    }

    public v queueAppendItem(MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public v queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i10, long j10, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzz zzzVar = new zzz(this, mediaQueueItem, i10, j10, jSONObject);
        zzz(zzzVar);
        return zzzVar;
    }

    public v queueInsertItems(MediaQueueItem[] mediaQueueItemArr, int i10, JSONObject jSONObject) throws IllegalArgumentException {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzy zzyVar = new zzy(this, mediaQueueItemArr, i10, jSONObject);
        zzz(zzyVar);
        return zzyVar;
    }

    public v queueJumpToItem(int i10, long j10, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzai zzaiVar = new zzai(this, i10, j10, jSONObject);
        zzz(zzaiVar);
        return zzaiVar;
    }

    public v queueLoad(MediaQueueItem[] mediaQueueItemArr, int i10, int i11, long j10, JSONObject jSONObject) throws IllegalArgumentException {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzx zzxVar = new zzx(this, mediaQueueItemArr, i10, i11, j10, jSONObject);
        zzz(zzxVar);
        return zzxVar;
    }

    public v queueMoveItemToNewIndex(int i10, int i11, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzaj zzajVar = new zzaj(this, i10, i11, jSONObject);
        zzz(zzajVar);
        return zzajVar;
    }

    public v queueNext(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzaf zzafVar = new zzaf(this, jSONObject);
        zzz(zzafVar);
        return zzafVar;
    }

    public v queuePrev(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzae zzaeVar = new zzae(this, jSONObject);
        zzz(zzaeVar);
        return zzaeVar;
    }

    public v queueRemoveItem(int i10, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzah zzahVar = new zzah(this, i10, jSONObject);
        zzz(zzahVar);
        return zzahVar;
    }

    public v queueRemoveItems(int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzab zzabVar = new zzab(this, iArr, jSONObject);
        zzz(zzabVar);
        return zzabVar;
    }

    public v queueReorderItems(int[] iArr, int i10, JSONObject jSONObject) throws IllegalArgumentException {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzac zzacVar = new zzac(this, iArr, i10, jSONObject);
        zzz(zzacVar);
        return zzacVar;
    }

    public v queueSetRepeatMode(int i10, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzag zzagVar = new zzag(this, i10, jSONObject);
        zzz(zzagVar);
        return zzagVar;
    }

    public v queueShuffle(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzad zzadVar = new zzad(this, true, jSONObject);
        zzz(zzadVar);
        return zzadVar;
    }

    public v queueUpdateItems(MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzaa zzaaVar = new zzaa(this, mediaQueueItemArr, jSONObject);
        zzz(zzaaVar);
        return zzaaVar;
    }

    public void registerCallback(Callback callback) {
        t.e("Must be called from the main thread.");
        if (callback != null) {
            this.zzj.add(callback);
        }
    }

    @Deprecated
    public void removeListener(Listener listener) {
        t.e("Must be called from the main thread.");
        if (listener != null) {
            this.zzi.remove(listener);
        }
    }

    public void removeProgressListener(ProgressListener progressListener) {
        t.e("Must be called from the main thread.");
        zzbg zzbgVar = (zzbg) this.zzk.remove(progressListener);
        if (zzbgVar != null) {
            zzbgVar.zzc(progressListener);
            if (zzbgVar.zzd()) {
                return;
            }
            this.zzl.remove(Long.valueOf(zzbgVar.zza()));
            zzbgVar.zzf();
        }
    }

    public v requestStatus() {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzu zzuVar = new zzu(this);
        zzz(zzuVar);
        return zzuVar;
    }

    @Deprecated
    public v seek(long j10) {
        return seek(j10, 0, null);
    }

    public v setActiveMediaTracks(long[] jArr) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzv zzvVar = new zzv(this, jArr);
        zzz(zzvVar);
        return zzvVar;
    }

    public void setParseAdsInfoCallback(ParseAdsInfoCallback parseAdsInfoCallback) {
        t.e("Must be called from the main thread.");
        this.zzm = parseAdsInfoCallback;
    }

    public v setPlaybackRate(double d4) {
        return setPlaybackRate(d4, null);
    }

    public v setStreamMute(boolean z) {
        return setStreamMute(z, null);
    }

    public v setStreamVolume(double d4) throws IllegalArgumentException {
        return setStreamVolume(d4, null);
    }

    public v setTextTrackStyle(TextTrackStyle textTrackStyle) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzw zzwVar = new zzw(this, textTrackStyle);
        zzz(zzwVar);
        return zzwVar;
    }

    public v skipAd() {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzt zztVar = new zzt(this);
        zzz(zztVar);
        return zztVar;
    }

    public v stop() {
        return stop(null);
    }

    public void togglePlayback() {
        t.e("Must be called from the main thread.");
        int playerState = getPlayerState();
        if (playerState == 4 || playerState == 2) {
            pause();
        } else {
            play();
        }
    }

    public void unregisterCallback(Callback callback) {
        t.e("Must be called from the main thread.");
        if (callback != null) {
            this.zzj.remove(callback);
        }
    }

    public final void zza(com.google.android.gms.cast.zzq zzqVar) {
        com.google.android.gms.cast.zzq zzqVar2 = this.zzg;
        if (zzqVar2 == zzqVar) {
            return;
        }
        if (zzqVar2 != null) {
            this.zzd.zzh();
            this.zzf.zza();
            zzqVar2.zzr(getNamespace());
            this.zze.zza(null);
            this.zzc.removeCallbacksAndMessages(null);
        }
        this.zzg = zzqVar;
        if (zzqVar != null) {
            this.zze.zza(zzqVar);
        }
    }

    public final void zzb() {
        com.google.android.gms.cast.zzq zzqVar = this.zzg;
        if (zzqVar == null) {
            return;
        }
        zzqVar.zzq(getNamespace(), this);
        requestStatus();
    }

    public final v zzc() {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzak zzakVar = new zzak(this, true);
        zzz(zzakVar);
        return zzakVar;
    }

    public final v zzd(int[] iArr) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzal zzalVar = new zzal(this, true, iArr);
        zzz(zzalVar);
        return zzalVar;
    }

    public final v zze(int i10, int i11, int i12) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzam zzamVar = new zzam(this, true, i10, i11, i12);
        zzz(zzamVar);
        return zzamVar;
    }

    public final h zzf(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return l.m(new com.google.android.gms.cast.internal.zzap());
        }
        this.zzh = new i();
        zza.d("create SessionState with cached mediaInfo and mediaStatus", new Object[0]);
        MediaInfo mediaInfo = getMediaInfo();
        MediaStatus mediaStatus = getMediaStatus();
        SessionState sessionStateBuild = null;
        if (mediaInfo != null && mediaStatus != null) {
            MediaLoadRequestData.Builder builder = new MediaLoadRequestData.Builder();
            builder.setMediaInfo(mediaInfo);
            builder.setCurrentTime(getApproximateStreamPosition());
            builder.setQueueData(mediaStatus.getQueueData());
            builder.setPlaybackRate(mediaStatus.getPlaybackRate());
            builder.setActiveTrackIds(mediaStatus.getActiveTrackIds());
            builder.setCustomData(mediaStatus.getCustomData());
            MediaLoadRequestData mediaLoadRequestDataBuild = builder.build();
            SessionState.Builder builder2 = new SessionState.Builder();
            builder2.setLoadRequestData(mediaLoadRequestDataBuild);
            sessionStateBuild = builder2.build();
        }
        if (sessionStateBuild != null) {
            this.zzh.b(sessionStateBuild);
        } else {
            this.zzh.a(new com.google.android.gms.cast.internal.zzap());
        }
        return this.zzh.f13855a;
    }

    public final void zzg(SessionState sessionState) {
        MediaLoadRequestData loadRequestData;
        if (sessionState == null || (loadRequestData = sessionState.getLoadRequestData()) == null) {
            return;
        }
        zza.d("resume SessionState", new Object[0]);
        load(loadRequestData);
    }

    public final v zzh(String str, List list) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzao zzaoVar = new zzao(this, true, str, null);
        zzz(zzaoVar);
        return zzaoVar;
    }

    public final boolean zzi() {
        t.e("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 5;
    }

    public final boolean zzj() {
        t.e("Must be called from the main thread.");
        if (!isLiveStream()) {
            return true;
        }
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || !mediaStatus.isMediaCommandSupported(2L) || mediaStatus.getLiveSeekableRange() == null) ? false : true;
    }

    public final int zzk() {
        MediaQueueItem loadingItem;
        if (getMediaInfo() != null && hasMediaSession()) {
            if (isBuffering()) {
                return 6;
            }
            if (isPlaying()) {
                return 3;
            }
            if (isPaused()) {
                return 2;
            }
            if (isLoadingNextItem() && (loadingItem = getLoadingItem()) != null && loadingItem.getMedia() != null) {
                return 6;
            }
        }
        return 0;
    }

    public final boolean zzl() {
        if (!hasMediaSession()) {
            return false;
        }
        MediaStatus mediaStatus = getMediaStatus();
        t.i(mediaStatus);
        if (mediaStatus.isMediaCommandSupported(128L) || mediaStatus.getQueueRepeatMode() != 0) {
            return true;
        }
        Integer indexById = mediaStatus.getIndexById(mediaStatus.getCurrentItemId());
        return indexById != null && indexById.intValue() > 0;
    }

    public final boolean zzm() {
        if (!hasMediaSession()) {
            return false;
        }
        MediaStatus mediaStatus = getMediaStatus();
        t.i(mediaStatus);
        if (mediaStatus.isMediaCommandSupported(64L) || mediaStatus.getQueueRepeatMode() != 0) {
            return true;
        }
        Integer indexById = mediaStatus.getIndexById(mediaStatus.getCurrentItemId());
        return indexById != null && indexById.intValue() < mediaStatus.getQueueItemCount() + (-1);
    }

    public final /* synthetic */ void zzp() {
        for (zzbg zzbgVar : this.zzl.values()) {
            if (hasMediaSession() && !zzbgVar.zzg()) {
                zzbgVar.zze();
            } else if (!hasMediaSession() && zzbgVar.zzg()) {
                zzbgVar.zzf();
            }
            if (zzbgVar.zzg() && (isBuffering() || zzi() || isPaused() || isLoadingNextItem())) {
                zzo(zzbgVar.zzh());
            }
        }
    }

    public final /* synthetic */ Object zzr() {
        return this.zzb;
    }

    public final /* synthetic */ Handler zzs() {
        return this.zzc;
    }

    public final /* synthetic */ com.google.android.gms.cast.internal.zzar zzt() {
        return this.zzd;
    }

    public final /* synthetic */ List zzu() {
        return this.zzi;
    }

    public final /* synthetic */ List zzv() {
        return this.zzj;
    }

    public final /* synthetic */ ParseAdsInfoCallback zzw() {
        return this.zzm;
    }

    public v load(MediaInfo mediaInfo, MediaLoadOptions mediaLoadOptions) {
        MediaLoadRequestData.Builder builder = new MediaLoadRequestData.Builder();
        builder.setMediaInfo(mediaInfo);
        builder.setAutoplay(Boolean.valueOf(mediaLoadOptions.getAutoplay()));
        builder.setCurrentTime(mediaLoadOptions.getPlayPosition());
        builder.setPlaybackRate(mediaLoadOptions.getPlaybackRate());
        builder.setActiveTrackIds(mediaLoadOptions.getActiveTrackIds());
        builder.setCustomData(mediaLoadOptions.getCustomData());
        builder.setCredentials(mediaLoadOptions.getCredentials());
        builder.setCredentialsType(mediaLoadOptions.getCredentialsType());
        return load(builder.build());
    }

    public v pause(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzap zzapVar = new zzap(this, jSONObject);
        zzz(zzapVar);
        return zzapVar;
    }

    public v play(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzar zzarVar = new zzar(this, jSONObject);
        zzz(zzarVar);
        return zzarVar;
    }

    @Deprecated
    public v seek(long j10, int i10) {
        return seek(j10, i10, null);
    }

    public v setPlaybackRate(double d4, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzav zzavVar = new zzav(this, d4, jSONObject);
        zzz(zzavVar);
        return zzavVar;
    }

    public v setStreamMute(boolean z, JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzau zzauVar = new zzau(this, z, jSONObject);
        zzz(zzauVar);
        return zzauVar;
    }

    public v setStreamVolume(double d4, JSONObject jSONObject) throws IllegalArgumentException {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzat zzatVar = new zzat(this, d4, jSONObject);
        zzz(zzatVar);
        return zzatVar;
    }

    public v stop(JSONObject jSONObject) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzaq zzaqVar = new zzaq(this, jSONObject);
        zzz(zzaqVar);
        return zzaqVar;
    }

    @Deprecated
    public v seek(long j10, int i10, JSONObject jSONObject) {
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(j10);
        builder.setResumeState(i10);
        builder.setCustomData(jSONObject);
        return seek(builder.build());
    }

    public v queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i10, JSONObject jSONObject) {
        return queueInsertAndPlayItem(mediaQueueItem, i10, -1L, jSONObject);
    }

    public v queueJumpToItem(int i10, JSONObject jSONObject) {
        return queueJumpToItem(i10, -1L, jSONObject);
    }

    public v queueLoad(MediaQueueItem[] mediaQueueItemArr, int i10, int i11, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(mediaQueueItemArr, i10, i11, -1L, jSONObject);
    }

    public v seek(MediaSeekOptions mediaSeekOptions) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzas zzasVar = new zzas(this, mediaSeekOptions);
        zzz(zzasVar);
        return zzasVar;
    }

    @Deprecated
    public v load(MediaInfo mediaInfo, boolean z) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        return load(mediaInfo, builder.build());
    }

    @Deprecated
    public v load(MediaInfo mediaInfo, boolean z, long j10) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        builder.setPlayPosition(j10);
        return load(mediaInfo, builder.build());
    }

    @Deprecated
    public v load(MediaInfo mediaInfo, boolean z, long j10, JSONObject jSONObject) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        builder.setPlayPosition(j10);
        builder.setCustomData(jSONObject);
        return load(mediaInfo, builder.build());
    }

    @Deprecated
    public v load(MediaInfo mediaInfo, boolean z, long j10, long[] jArr, JSONObject jSONObject) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        builder.setPlayPosition(j10);
        builder.setActiveTrackIds(jArr);
        builder.setCustomData(jSONObject);
        return load(mediaInfo, builder.build());
    }

    public v load(MediaLoadRequestData mediaLoadRequestData) {
        t.e("Must be called from the main thread.");
        if (!zzx()) {
            return zzn(17, null);
        }
        zzan zzanVar = new zzan(this, mediaLoadRequestData);
        zzz(zzanVar);
        return zzanVar;
    }
}
