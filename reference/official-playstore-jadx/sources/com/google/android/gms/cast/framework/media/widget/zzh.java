package com.google.android.gms.cast.framework.media.widget;

import android.view.View;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzh implements View.OnClickListener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzh(ExpandedControllerActivity expandedControllerActivity) {
        Objects.requireNonNull(expandedControllerActivity);
        this.zza = expandedControllerActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        RemoteMediaClient remoteMediaClientZza;
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        if (!expandedControllerActivity.zzi().isClickable() || (remoteMediaClientZza = expandedControllerActivity.zza()) == null) {
            return;
        }
        remoteMediaClientZza.skipAd();
    }
}
