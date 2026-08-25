package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import android.widget.ImageView;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzc implements View.OnClickListener {
    final /* synthetic */ UIMediaController zza;

    public zzc(UIMediaController uIMediaController) {
        Objects.requireNonNull(uIMediaController);
        this.zza = uIMediaController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zza.onPlayPauseToggleClicked((ImageView) view);
    }
}
