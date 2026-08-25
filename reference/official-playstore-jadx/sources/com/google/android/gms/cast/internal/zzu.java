package com.google.android.gms.cast.internal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzu implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ zza zzb;

    public zzu(zzw zzwVar, zzx zzxVar, zza zzaVar) {
        this.zza = zzxVar;
        this.zzb = zzaVar;
        Objects.requireNonNull(zzwVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzK(this.zzb);
    }
}
