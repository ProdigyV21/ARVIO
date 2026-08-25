package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.i0;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.api.x;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = com.google.android.gms.cast.internal.zzar.zzb;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private final Object zza;
    private final com.google.android.gms.cast.internal.zzar zzb;
    private final zzcz zzc;
    private OnPreloadStatusUpdatedListener zzd;
    private OnQueueStatusUpdatedListener zze;
    private OnMetadataUpdatedListener zzf;
    private OnStatusUpdatedListener zzg;

    @Deprecated
    public interface MediaChannelResult extends x {
        JSONObject getCustomData();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();
    }

    @Deprecated
    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    @Deprecated
    public interface OnPreloadStatusUpdatedListener {
        void onPreloadStatusUpdated();
    }

    @Deprecated
    public interface OnQueueStatusUpdatedListener {
        void onQueueStatusUpdated();
    }

    @Deprecated
    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    public RemoteMediaPlayer() {
        com.google.android.gms.cast.internal.zzar zzarVar = new com.google.android.gms.cast.internal.zzar(null);
        this.zza = new Object();
        this.zzb = zzarVar;
        zzarVar.zzi(new zzcl(this));
        zzcz zzczVar = new zzcz(this);
        this.zzc = zzczVar;
        zzarVar.zze(zzczVar);
    }

    public long getApproximateStreamPosition() {
        long jZzv;
        synchronized (this.zza) {
            jZzv = this.zzb.zzv();
        }
        return jZzv;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfoZzB;
        synchronized (this.zza) {
            mediaInfoZzB = this.zzb.zzB();
        }
        return mediaInfoZzB;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatusZzA;
        synchronized (this.zza) {
            mediaStatusZzA = this.zzb.zzA();
        }
        return mediaStatusZzA;
    }

    public String getNamespace() {
        return this.zzb.zzd();
    }

    public long getStreamDuration() {
        long jZzz;
        synchronized (this.zza) {
            jZzz = this.zzb.zzz();
        }
        return jZzz;
    }

    public v load(q qVar, MediaInfo mediaInfo) {
        return load(qVar, mediaInfo, true, -1L, null, null);
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzb.zzL(str2);
    }

    public v pause(q qVar) {
        return pause(qVar, null);
    }

    public v play(q qVar) {
        return play(qVar, null);
    }

    public v queueAppendItem(q qVar, MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(qVar, new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public v queueInsertAndPlayItem(q qVar, MediaQueueItem mediaQueueItem, int i10, long j10, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcf(this, qVar, mediaQueueItem, i10, j10, jSONObject));
    }

    public v queueInsertItems(q qVar, MediaQueueItem[] mediaQueueItemArr, int i10, JSONObject jSONObject) throws IllegalArgumentException {
        return ((i0) qVar).f12756b.doWrite(new zzce(this, qVar, mediaQueueItemArr, i10, jSONObject));
    }

    public v queueJumpToItem(q qVar, int i10, long j10, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzco(this, qVar, i10, j10, jSONObject));
    }

    public v queueLoad(q qVar, MediaQueueItem[] mediaQueueItemArr, int i10, int i11, long j10, JSONObject jSONObject) throws IllegalArgumentException {
        return ((i0) qVar).f12756b.doWrite(new zzcd(this, qVar, mediaQueueItemArr, i10, i11, j10, jSONObject));
    }

    public v queueMoveItemToNewIndex(q qVar, int i10, int i11, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcp(this, qVar, i10, i11, jSONObject));
    }

    public v queueNext(q qVar, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzck(this, qVar, jSONObject));
    }

    public v queuePrev(q qVar, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcj(this, qVar, jSONObject));
    }

    public v queueRemoveItem(q qVar, int i10, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcn(this, qVar, i10, jSONObject));
    }

    public v queueRemoveItems(q qVar, int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        return ((i0) qVar).f12756b.doWrite(new zzch(this, qVar, iArr, jSONObject));
    }

    public v queueReorderItems(q qVar, int[] iArr, int i10, JSONObject jSONObject) throws IllegalArgumentException {
        return ((i0) qVar).f12756b.doWrite(new zzci(this, qVar, iArr, i10, jSONObject));
    }

    public v queueSetRepeatMode(q qVar, int i10, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcm(this, qVar, i10, jSONObject));
    }

    public v queueUpdateItems(q qVar, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcg(this, qVar, mediaQueueItemArr, jSONObject));
    }

    public v requestStatus(q qVar) {
        return ((i0) qVar).f12756b.doWrite(new zzcx(this, qVar));
    }

    public v seek(q qVar, long j10) {
        return seek(qVar, j10, 0, null);
    }

    public v setActiveMediaTracks(q qVar, long[] jArr) {
        return ((i0) qVar).f12756b.doWrite(new zzcb(this, qVar, jArr));
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener onMetadataUpdatedListener) {
        this.zzf = onMetadataUpdatedListener;
    }

    public void setOnPreloadStatusUpdatedListener(OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener) {
        this.zzd = onPreloadStatusUpdatedListener;
    }

    public void setOnQueueStatusUpdatedListener(OnQueueStatusUpdatedListener onQueueStatusUpdatedListener) {
        this.zze = onQueueStatusUpdatedListener;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener onStatusUpdatedListener) {
        this.zzg = onStatusUpdatedListener;
    }

    public v setStreamMute(q qVar, boolean z) {
        return setStreamMute(qVar, z, null);
    }

    public v setStreamVolume(q qVar, double d4) throws IllegalArgumentException {
        return setStreamVolume(qVar, d4, null);
    }

    public v setTextTrackStyle(q qVar, TextTrackStyle textTrackStyle) {
        return ((i0) qVar).f12756b.doWrite(new zzcc(this, qVar, textTrackStyle));
    }

    public v stop(q qVar) {
        return stop(qVar, null);
    }

    public final /* synthetic */ int zza(int i10) {
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return -1;
        }
        for (int i11 = 0; i11 < mediaStatus.getQueueItemCount(); i11++) {
            MediaQueueItem queueItem = mediaStatus.getQueueItem(i11);
            if (queueItem != null && queueItem.getItemId() == i10) {
                return i11;
            }
        }
        return -1;
    }

    public final /* synthetic */ void zzb() {
        OnStatusUpdatedListener onStatusUpdatedListener = this.zzg;
        if (onStatusUpdatedListener != null) {
            onStatusUpdatedListener.onStatusUpdated();
        }
    }

    public final /* synthetic */ void zzc() {
        OnMetadataUpdatedListener onMetadataUpdatedListener = this.zzf;
        if (onMetadataUpdatedListener != null) {
            onMetadataUpdatedListener.onMetadataUpdated();
        }
    }

    public final /* synthetic */ void zzd() {
        OnQueueStatusUpdatedListener onQueueStatusUpdatedListener = this.zze;
        if (onQueueStatusUpdatedListener != null) {
            onQueueStatusUpdatedListener.onQueueStatusUpdated();
        }
    }

    public final /* synthetic */ void zze() {
        OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener = this.zzd;
        if (onPreloadStatusUpdatedListener != null) {
            onPreloadStatusUpdatedListener.onPreloadStatusUpdated();
        }
    }

    public final /* synthetic */ Object zzf() {
        return this.zza;
    }

    public final /* synthetic */ com.google.android.gms.cast.internal.zzar zzg() {
        return this.zzb;
    }

    public final /* synthetic */ zzcz zzh() {
        return this.zzc;
    }

    public v load(q qVar, MediaInfo mediaInfo, boolean z) {
        return load(qVar, mediaInfo, z, -1L, null, null);
    }

    public v pause(q qVar, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcr(this, qVar, jSONObject));
    }

    public v play(q qVar, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzct(this, qVar, jSONObject));
    }

    public v seek(q qVar, long j10, int i10) {
        return seek(qVar, j10, i10, null);
    }

    public v setStreamMute(q qVar, boolean z, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcw(this, qVar, z, jSONObject));
    }

    public v setStreamVolume(q qVar, double d4, JSONObject jSONObject) throws IllegalArgumentException {
        return ((i0) qVar).f12756b.doWrite(new zzcv(this, qVar, d4, jSONObject));
    }

    public v stop(q qVar, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcs(this, qVar, jSONObject));
    }

    public v load(q qVar, MediaInfo mediaInfo, boolean z, long j10) {
        return load(qVar, mediaInfo, z, j10, null, null);
    }

    public v seek(q qVar, long j10, int i10, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcu(this, qVar, j10, i10, jSONObject));
    }

    public v load(q qVar, MediaInfo mediaInfo, boolean z, long j10, JSONObject jSONObject) {
        return load(qVar, mediaInfo, z, j10, null, jSONObject);
    }

    public v queueInsertAndPlayItem(q qVar, MediaQueueItem mediaQueueItem, int i10, JSONObject jSONObject) {
        return queueInsertAndPlayItem(qVar, mediaQueueItem, i10, -1L, jSONObject);
    }

    public v queueJumpToItem(q qVar, int i10, JSONObject jSONObject) {
        return queueJumpToItem(qVar, i10, -1L, jSONObject);
    }

    public v queueLoad(q qVar, MediaQueueItem[] mediaQueueItemArr, int i10, int i11, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(qVar, mediaQueueItemArr, i10, i11, -1L, jSONObject);
    }

    public v load(q qVar, MediaInfo mediaInfo, boolean z, long j10, long[] jArr, JSONObject jSONObject) {
        return ((i0) qVar).f12756b.doWrite(new zzcq(this, qVar, mediaInfo, z, j10, jArr, jSONObject));
    }
}
