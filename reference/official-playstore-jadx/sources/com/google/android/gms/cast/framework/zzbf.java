package com.google.android.gms.cast.framework;

import android.os.Bundle;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzbf extends zzbb {
    final /* synthetic */ Session zza;

    public /* synthetic */ zzbf(Session session, byte[] bArr) {
        Objects.requireNonNull(session);
        this.zza = session;
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final b zzb() {
        return new d(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final void zzc(Bundle bundle) {
        this.zza.onStarting(bundle);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final void zzd(Bundle bundle) {
        this.zza.start(bundle);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final void zze(Bundle bundle) {
        this.zza.onResuming(bundle);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final void zzf(Bundle bundle) {
        this.zza.resume(bundle);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final void zzg(boolean z) {
        this.zza.end(z);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final void zzh(Bundle bundle) {
        this.zza.zza(bundle);
    }

    @Override // com.google.android.gms.cast.framework.zzbc
    public final long zzi() {
        return this.zza.getSessionRemainingTimeMs();
    }
}
