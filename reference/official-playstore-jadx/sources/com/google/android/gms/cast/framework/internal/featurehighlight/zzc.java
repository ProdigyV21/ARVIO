package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.View;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzc implements View.OnLayoutChangeListener {
    final /* synthetic */ zzh zza;

    public zzc(zzh zzhVar, Runnable runnable) {
        Objects.requireNonNull(zzhVar);
        this.zza = zzhVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) {
        zzh zzhVar = this.zza;
        zzhVar.zzc();
        zzhVar.removeOnLayoutChangeListener(this);
    }
}
