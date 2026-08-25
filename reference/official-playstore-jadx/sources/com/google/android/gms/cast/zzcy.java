package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.api.y;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzcy implements y {
    final /* synthetic */ zzcz zza;
    private final long zzb;

    public zzcy(zzcz zzczVar, long j10) {
        Objects.requireNonNull(zzczVar);
        this.zza = zzczVar;
        this.zzb = j10;
    }

    @Override // com.google.android.gms.common.api.y
    public final void onResult(x xVar) {
        Status status = (Status) xVar;
        if (status.d()) {
            return;
        }
        zzcz zzczVar = this.zza;
        zzczVar.zza.zzg().zzN(this.zzb, status.f12675i);
    }
}
