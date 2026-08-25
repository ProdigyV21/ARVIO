package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zze extends AnimatorListenerAdapter {
    final /* synthetic */ Runnable zza;
    final /* synthetic */ zzh zzb;

    public zze(zzh zzhVar, Runnable runnable) {
        this.zza = runnable;
        Objects.requireNonNull(zzhVar);
        this.zzb = zzhVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        zzh zzhVar = this.zzb;
        zzhVar.setVisibility(8);
        zzhVar.zza = null;
        this.zza.run();
    }
}
