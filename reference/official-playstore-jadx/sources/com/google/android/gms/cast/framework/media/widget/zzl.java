package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.internal.cast.p1;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzl extends p1 {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzl(ExpandedControllerActivity expandedControllerActivity, byte[] bArr) {
        Objects.requireNonNull(expandedControllerActivity);
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.internal.cast.p1
    public final void zza() {
        this.zza.zzd();
    }
}
