package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.uicontroller.zza;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import com.google.android.gms.cast.framework.media.widget.zzb;
import com.google.android.gms.cast.framework.media.widget.zze;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class l1 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CastSeekBar f13392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f13393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final zza f13394c;

    public l1(CastSeekBar castSeekBar, long j10, zza zzaVar) {
        this.f13392a = castSeekBar;
        this.f13393b = j10;
        this.f13394c = zzaVar;
        castSeekBar.setEnabled(false);
        castSeekBar.zza(null);
        castSeekBar.zzb = null;
        castSeekBar.postInvalidate();
    }

    public final void a() {
        c();
        RemoteMediaClient remoteMediaClient = super.getRemoteMediaClient();
        ArrayList arrayList = null;
        MediaInfo mediaInfo = remoteMediaClient == null ? null : remoteMediaClient.getMediaInfo();
        CastSeekBar castSeekBar = this.f13392a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isLoadingNextItem() || mediaInfo == null) {
            castSeekBar.zza(null);
        } else {
            List<AdBreakInfo> adBreaks = mediaInfo.getAdBreaks();
            if (adBreaks != null) {
                arrayList = new ArrayList();
                for (AdBreakInfo adBreakInfo : adBreaks) {
                    if (adBreakInfo != null) {
                        long playbackPositionInMs = adBreakInfo.getPlaybackPositionInMs();
                        zza zzaVar = this.f13394c;
                        int iZzb = playbackPositionInMs == -1000 ? zzaVar.zzb() : Math.min((int) (playbackPositionInMs - zzaVar.zzh()), zzaVar.zzb());
                        if (iZzb >= 0) {
                            arrayList.add(new com.google.android.gms.cast.framework.media.widget.zza(iZzb, (int) adBreakInfo.getDurationInMs(), adBreakInfo.isExpanded()));
                        }
                    }
                }
            }
            castSeekBar.zza(arrayList);
        }
        b();
    }

    public final void b() {
        RemoteMediaClient remoteMediaClient = super.getRemoteMediaClient();
        CastSeekBar castSeekBar = this.f13392a;
        if (remoteMediaClient == null || !remoteMediaClient.isPlayingAd()) {
            castSeekBar.zzb = null;
            castSeekBar.postInvalidate();
            return;
        }
        int approximateAdBreakClipPositionMs = (int) remoteMediaClient.getApproximateAdBreakClipPositionMs();
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        AdBreakClipInfo currentAdBreakClip = mediaStatus != null ? mediaStatus.getCurrentAdBreakClip() : null;
        int durationInMs = currentAdBreakClip != null ? (int) currentAdBreakClip.getDurationInMs() : approximateAdBreakClipPositionMs;
        if (approximateAdBreakClipPositionMs < 0) {
            approximateAdBreakClipPositionMs = 0;
        }
        if (durationInMs < 0) {
            durationInMs = 1;
        }
        if (approximateAdBreakClipPositionMs > durationInMs) {
            durationInMs = approximateAdBreakClipPositionMs;
        }
        castSeekBar.zzb = new zzb(approximateAdBreakClipPositionMs, durationInMs);
        castSeekBar.postInvalidate();
    }

    public final void c() {
        RemoteMediaClient remoteMediaClient = super.getRemoteMediaClient();
        CastSeekBar castSeekBar = this.f13392a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isPlayingAd()) {
            castSeekBar.setEnabled(false);
        } else {
            castSeekBar.setEnabled(true);
        }
        zze zzeVar = new zze();
        zza zzaVar = this.f13394c;
        zzeVar.zza = zzaVar.zzc();
        zzeVar.zzb = zzaVar.zzb();
        zzeVar.zzc = (int) (-zzaVar.zzh());
        RemoteMediaClient remoteMediaClient2 = super.getRemoteMediaClient();
        zzeVar.zzd = (remoteMediaClient2 != null && remoteMediaClient2.hasMediaSession() && remoteMediaClient2.zzj()) ? zzaVar.zzf() : zzaVar.zzc();
        RemoteMediaClient remoteMediaClient3 = super.getRemoteMediaClient();
        zzeVar.zze = (remoteMediaClient3 != null && remoteMediaClient3.hasMediaSession() && remoteMediaClient3.zzj()) ? zzaVar.zzg() : zzaVar.zzc();
        RemoteMediaClient remoteMediaClient4 = super.getRemoteMediaClient();
        zzeVar.zzf = remoteMediaClient4 != null && remoteMediaClient4.hasMediaSession() && remoteMediaClient4.zzj();
        castSeekBar.zzb(zzeVar);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j10, long j11) {
        c();
        b();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = super.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.f13393b);
        }
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = super.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
        a();
    }
}
