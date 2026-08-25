package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzd implements View.OnClickListener {
    final /* synthetic */ UIMediaController zza;

    public zzd(UIMediaController uIMediaController) {
        Objects.requireNonNull(uIMediaController);
        this.zza = uIMediaController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zza.onSkipNextClicked(view);
    }
}
