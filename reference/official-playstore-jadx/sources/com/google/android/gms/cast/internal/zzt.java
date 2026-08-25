package com.google.android.gms.cast.internal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzt implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ zzac zzb;

    public zzt(zzw zzwVar, zzx zzxVar, zzac zzacVar) {
        this.zza = zzxVar;
        this.zzb = zzacVar;
        Objects.requireNonNull(zzwVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzJ(this.zzb);
    }
}
