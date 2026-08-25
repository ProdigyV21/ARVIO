package com.google.android.gms.cast.framework.media.uicontroller;

import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzh extends com.google.android.gms.cast.framework.media.widget.zzd {
    final /* synthetic */ UIMediaController zza;

    public zzh(UIMediaController uIMediaController) {
        Objects.requireNonNull(uIMediaController);
        this.zza = uIMediaController;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zza(CastSeekBar castSeekBar) {
        this.zza.zzc(castSeekBar);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zzb(CastSeekBar castSeekBar) {
        this.zza.zzd(castSeekBar);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zzc(CastSeekBar castSeekBar, int i10, boolean z) {
        this.zza.zze(castSeekBar, i10, z);
    }
}
