package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzbe implements com.google.android.gms.cast.internal.zzao {
    final /* synthetic */ RemoteMediaClient zza;

    public /* synthetic */ zzbe(RemoteMediaClient remoteMediaClient, byte[] bArr) {
        Objects.requireNonNull(remoteMediaClient);
        this.zza = remoteMediaClient;
    }

    private final void zzn() {
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient.zzw() == null || (mediaStatus = remoteMediaClient.getMediaStatus()) == null) {
            return;
        }
        mediaStatus.getWriter().setIsPlayingAd(remoteMediaClient.zzw().parseIsPlayingAdFromMediaStatus(mediaStatus));
        List<AdBreakInfo> adBreaksFromMediaStatus = remoteMediaClient.zzw().parseAdBreaksFromMediaStatus(mediaStatus);
        MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
        if (mediaInfo != null) {
            mediaInfo.getWriter().setAdBreaks(adBreaksFromMediaStatus);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zza() {
        zzn();
        RemoteMediaClient remoteMediaClient = this.zza;
        remoteMediaClient.zzp();
        Iterator it = remoteMediaClient.zzu().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onStatusUpdated();
        }
        Iterator it2 = remoteMediaClient.zzv().iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzb() {
        zzn();
        RemoteMediaClient remoteMediaClient = this.zza;
        Iterator it = remoteMediaClient.zzu().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onMetadataUpdated();
        }
        Iterator it2 = remoteMediaClient.zzv().iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onMetadataUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzc() {
        RemoteMediaClient remoteMediaClient = this.zza;
        Iterator it = remoteMediaClient.zzu().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onQueueStatusUpdated();
        }
        Iterator it2 = remoteMediaClient.zzv().iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onQueueStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzd() {
        RemoteMediaClient remoteMediaClient = this.zza;
        Iterator it = remoteMediaClient.zzu().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onPreloadStatusUpdated();
        }
        Iterator it2 = remoteMediaClient.zzv().iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onPreloadStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zze() {
        RemoteMediaClient remoteMediaClient = this.zza;
        Iterator it = remoteMediaClient.zzu().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onAdBreakStatusUpdated();
        }
        Iterator it2 = remoteMediaClient.zzv().iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onAdBreakStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzf(MediaError mediaError) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).onMediaError(mediaError);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzg(int[] iArr) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzb(iArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzh(int[] iArr, int i10) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzc(iArr, i10);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzi(int[] iArr) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzd(iArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzj(int[] iArr) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zze(iArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzk(MediaQueueItem[] mediaQueueItemArr) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzf(mediaQueueItemArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzl(List list, List list2, int i10) {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzg(list, list2, i10);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzm() {
        Iterator it = this.zza.zzv().iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzh();
        }
    }
}
