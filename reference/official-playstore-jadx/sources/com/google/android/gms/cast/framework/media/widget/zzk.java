package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.Cast;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzk extends Cast.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzk(ExpandedControllerActivity expandedControllerActivity) {
        Objects.requireNonNull(expandedControllerActivity);
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onDeviceNameChanged() {
        this.zza.zzc();
    }
}
