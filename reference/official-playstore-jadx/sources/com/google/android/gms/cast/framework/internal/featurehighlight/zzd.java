package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzd extends AnimatorListenerAdapter {
    final /* synthetic */ zzh zza;

    public zzd(zzh zzhVar) {
        Objects.requireNonNull(zzhVar);
        this.zza = zzhVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        zzh zzhVar = this.zza;
        Animator animatorZzk = zzhVar.zzk();
        zzhVar.zza = animatorZzk;
        animatorZzk.start();
    }
}
