package com.google.android.gms.cast.framework.media.uicontroller;

import android.text.format.DateUtils;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.t;
import java.text.DateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public final class zza {
    RemoteMediaClient zza;

    private zza() {
    }

    public static zza zza() {
        return new zza();
    }

    private final MediaMetadata zzo() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (mediaInfo = this.zza.getMediaInfo()) == null) {
            return null;
        }
        return mediaInfo.getMetadata();
    }

    private static final String zzp(long j10) {
        return j10 >= 0 ? DateUtils.formatElapsedTime(j10 / 1000) : "-".concat(String.valueOf(DateUtils.formatElapsedTime((-j10) / 1000)));
    }

    public final int zzb() {
        MediaInfo media;
        RemoteMediaClient remoteMediaClient = this.zza;
        long jLongValue = 1;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            RemoteMediaClient remoteMediaClient2 = this.zza;
            if (remoteMediaClient2.isLiveStream()) {
                Long lZzj = zzj();
                if (lZzj != null) {
                    jLongValue = lZzj.longValue();
                } else {
                    Long lZzl = zzl();
                    jLongValue = lZzl != null ? lZzl.longValue() : Math.max(remoteMediaClient2.getApproximateStreamPosition(), 1L);
                }
            } else if (remoteMediaClient2.isLoadingNextItem()) {
                MediaQueueItem loadingItem = remoteMediaClient2.getLoadingItem();
                if (loadingItem != null && (media = loadingItem.getMedia()) != null) {
                    jLongValue = Math.max(media.getStreamDuration(), 1L);
                }
            } else {
                jLongValue = Math.max(remoteMediaClient2.getStreamDuration(), 1L);
            }
        }
        return Math.max((int) (jLongValue - zzh()), 1);
    }

    public final int zzc() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            RemoteMediaClient remoteMediaClient2 = this.zza;
            if (remoteMediaClient2.isLiveStream() || !remoteMediaClient2.isLoadingNextItem()) {
                int approximateStreamPosition = (int) (remoteMediaClient2.getApproximateStreamPosition() - zzh());
                if (remoteMediaClient2.zzj()) {
                    int iZzf = zzf();
                    int iZzg = zzg();
                    int i10 = CastUtils.zza;
                    approximateStreamPosition = Math.min(Math.max(approximateStreamPosition, iZzf), iZzg);
                }
                int iZzb = zzb();
                int i11 = CastUtils.zza;
                return Math.min(Math.max(approximateStreamPosition, 0), iZzb);
            }
        }
        return 0;
    }

    public final boolean zzd(long j10) {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && this.zza.zzj()) {
            return (zzh() + ((long) zzg())) - j10 < 10000;
        }
        return false;
    }

    public final boolean zze() {
        return zzd(zzh() + ((long) zzc()));
    }

    public final int zzf() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzj()) {
            return 0;
        }
        Long lZzk = zzk();
        t.i(lZzk);
        long jLongValue = lZzk.longValue() - zzh();
        int iZzb = zzb();
        int i10 = CastUtils.zza;
        return Math.min(Math.max((int) jLongValue, 0), iZzb);
    }

    public final int zzg() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return zzb();
        }
        if (!this.zza.zzj()) {
            return 0;
        }
        Long lZzl = zzl();
        t.i(lZzl);
        long jLongValue = lZzl.longValue() - zzh();
        int iZzb = zzb();
        int i10 = CastUtils.zza;
        return Math.min(Math.max((int) jLongValue, 0), iZzb);
    }

    public final long zzh() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return 0L;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        Long lZzi = zzi();
        if (lZzi != null) {
            return lZzi.longValue();
        }
        Long lZzk = zzk();
        return lZzk != null ? lZzk.longValue() : remoteMediaClient2.getApproximateStreamPosition();
    }

    public final Long zzi() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return null;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        MediaInfo mediaInfo = remoteMediaClient2.getMediaInfo();
        MediaMetadata mediaMetadataZzo = zzo();
        if (mediaInfo == null || mediaMetadataZzo == null || !mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA)) {
            return null;
        }
        if (mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_DURATION) || remoteMediaClient2.zzj()) {
            return Long.valueOf(mediaMetadataZzo.getTimeMillis(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA));
        }
        return null;
    }

    public final Long zzj() {
        MediaMetadata mediaMetadataZzo;
        Long lZzi;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || (mediaMetadataZzo = zzo()) == null || !mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_DURATION) || (lZzi = zzi()) == null) {
            return null;
        }
        return Long.valueOf(mediaMetadataZzo.getTimeMillis(MediaMetadata.KEY_SECTION_DURATION) + lZzi.longValue());
    }

    public final Long zzk() {
        RemoteMediaClient remoteMediaClient;
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzj() || (mediaStatus = (remoteMediaClient = this.zza).getMediaStatus()) == null || mediaStatus.getLiveSeekableRange() == null) {
            return null;
        }
        return Long.valueOf(remoteMediaClient.getApproximateLiveSeekableRangeStart());
    }

    public final Long zzl() {
        RemoteMediaClient remoteMediaClient;
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzj() || (mediaStatus = (remoteMediaClient = this.zza).getMediaStatus()) == null || mediaStatus.getLiveSeekableRange() == null) {
            return null;
        }
        return Long.valueOf(remoteMediaClient.getApproximateLiveSeekableRangeEnd());
    }

    public final String zzm(long j10) {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return null;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        int i10 = 1;
        if (remoteMediaClient2 != null && remoteMediaClient2.hasMediaSession() && this.zza.isLiveStream() && zzn() != null) {
            i10 = 2;
        }
        if (i10 - 1 == 0) {
            return (remoteMediaClient2.isLiveStream() && zzi() == null) ? zzp(j10) : zzp(j10 - zzh());
        }
        Long lZzn = zzn();
        t.i(lZzn);
        return DateFormat.getTimeInstance().format(new Date(lZzn.longValue() + j10));
    }

    public final Long zzn() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || (mediaInfo = this.zza.getMediaInfo()) == null || mediaInfo.getStartAbsoluteTime() == -1) {
            return null;
        }
        return Long.valueOf(mediaInfo.getStartAbsoluteTime());
    }
}
