package com.google.android.gms.internal.cast;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.uicontroller.zza;

/* JADX INFO: loaded from: classes4.dex */
public final class a2 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SeekBar f13158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f13159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final zza f13160c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f13161d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Boolean f13162e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable f13163f;

    public a2(SeekBar seekBar, long j10, zza zzaVar) {
        this.f13163f = null;
        this.f13158a = seekBar;
        this.f13159b = j10;
        this.f13160c = zzaVar;
        seekBar.setEnabled(false);
        this.f13163f = seekBar.getThumb();
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        zza zzaVar = this.f13160c;
        SeekBar seekBar = this.f13158a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            seekBar.setMax(zzaVar.zzb());
            seekBar.setProgress(zzaVar.zzc());
            seekBar.setEnabled(false);
            return;
        }
        if (this.f13161d) {
            seekBar.setMax(zzaVar.zzb());
            if (remoteMediaClient.isLiveStream() && zzaVar.zze()) {
                seekBar.setProgress(zzaVar.zzg());
            } else {
                seekBar.setProgress(zzaVar.zzc());
            }
            if (remoteMediaClient.isPlayingAd()) {
                seekBar.setEnabled(false);
            } else {
                seekBar.setEnabled(true);
            }
            RemoteMediaClient remoteMediaClient2 = getRemoteMediaClient();
            if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession()) {
                return;
            }
            Boolean bool = this.f13162e;
            if (bool == null || bool.booleanValue() != remoteMediaClient2.zzj()) {
                boolean zZzj = remoteMediaClient2.zzj();
                this.f13162e = Boolean.valueOf(zZzj);
                if (!zZzj) {
                    seekBar.setThumb(new ColorDrawable(0));
                    seekBar.setClickable(false);
                    seekBar.setOnTouchListener(new z1());
                } else {
                    Drawable drawable = this.f13163f;
                    if (drawable != null) {
                        seekBar.setThumb(drawable);
                    }
                    seekBar.setClickable(true);
                    seekBar.setOnTouchListener(null);
                }
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j10, long j11) {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.f13159b);
        }
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
        a();
    }
}
