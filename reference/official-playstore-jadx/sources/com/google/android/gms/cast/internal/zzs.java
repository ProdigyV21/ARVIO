package com.google.android.gms.cast.internal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzs implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ int zzb;

    public zzs(zzw zzwVar, zzx zzxVar, int i10) {
        this.zza = zzxVar;
        this.zzb = i10;
        Objects.requireNonNull(zzwVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzQ().onApplicationDisconnected(this.zzb);
    }
}
