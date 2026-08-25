package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzg implements View.OnClickListener {
    final /* synthetic */ long zza;
    final /* synthetic */ UIMediaController zzb;

    public zzg(UIMediaController uIMediaController, long j10) {
        this.zza = j10;
        Objects.requireNonNull(uIMediaController);
        this.zzb = uIMediaController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzb.onRewindClicked(view, this.zza);
    }
}
