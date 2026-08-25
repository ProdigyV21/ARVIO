package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzb extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ View zza;
    final /* synthetic */ zzg zzb;

    public zzb(zzh zzhVar, View view, boolean z, zzg zzgVar) {
        this.zza = view;
        this.zzb = zzgVar;
        Objects.requireNonNull(zzhVar);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        View view = this.zza;
        if (view.getParent() != null) {
            view.performClick();
        }
        this.zzb.zza();
        return true;
    }
}
