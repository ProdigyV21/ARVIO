package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzi implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ SeekBar zza;
    final /* synthetic */ UIMediaController zzb;

    public zzi(UIMediaController uIMediaController, SeekBar seekBar) {
        this.zza = seekBar;
        Objects.requireNonNull(uIMediaController);
        this.zzb = uIMediaController;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i10, boolean z) {
        UIMediaController uIMediaController = this.zzb;
        RemoteMediaClient remoteMediaClient = uIMediaController.getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && remoteMediaClient.zzj()) {
            if (z) {
                zza zzaVar = uIMediaController.zza;
                if (i10 < zzaVar.zzf()) {
                    SeekBar seekBar2 = this.zza;
                    int iZzf = zzaVar.zzf();
                    seekBar2.setProgress(iZzf);
                    uIMediaController.onSeekBarProgressChanged(seekBar, iZzf, true);
                    return;
                }
            }
            if (z) {
                zza zzaVar2 = uIMediaController.zza;
                if (i10 > zzaVar2.zzg()) {
                    SeekBar seekBar3 = this.zza;
                    int iZzg = zzaVar2.zzg();
                    seekBar3.setProgress(iZzg);
                    uIMediaController.onSeekBarProgressChanged(seekBar, iZzg, true);
                    return;
                }
            }
        }
        uIMediaController.onSeekBarProgressChanged(seekBar, i10, z);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.zzb.onSeekBarStartTrackingTouch(seekBar);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.zzb.onSeekBarStopTrackingTouch(seekBar);
    }
}
