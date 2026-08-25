package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzq extends RemoteMediaClient.Callback {
    final /* synthetic */ CastSession zza;

    public zzq(CastSession castSession) {
        Objects.requireNonNull(castSession);
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        CastSession castSession = this.zza;
        MediaStatus mediaStatus = castSession.zzk() != null ? castSession.zzk().getMediaStatus() : null;
        if (castSession.zzl() != null) {
            castSession.zzl().zzc(mediaStatus);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zza(String str, long j10, int i10, long j11, long j12) {
        CastSession castSession = this.zza;
        if (castSession.zzl() != null) {
            castSession.zzl().zzb(str, j10, i10, j11, j12);
        }
    }
}
