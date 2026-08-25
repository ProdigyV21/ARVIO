package com.google.android.gms.internal.cast;

import android.app.Activity;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzg;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzh;
import com.google.android.gms.cast.framework.zzbd;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 implements zzg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Activity f13480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ zzh f13481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ o0 f13482c;

    public n0(o0 o0Var, Activity activity, zzh zzhVar) {
        this.f13480a = activity;
        this.f13481b = zzhVar;
        this.f13482c = o0Var;
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zza() {
        if (this.f13482c.f13502p) {
            Activity activity = this.f13480a;
            zzbd.zza(activity);
            this.f13481b.zze(new m0(this, activity, 1));
        }
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zzb() {
        if (this.f13482c.f13502p) {
            Activity activity = this.f13480a;
            zzbd.zza(activity);
            this.f13481b.zzd(new m0(this, activity, 0));
        }
    }
}
