package com.google.android.gms.cast.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.j;
import com.google.android.gms.tasks.i;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzi extends zzaf {
    final /* synthetic */ i zza;

    public zzi(zzn zznVar, i iVar) {
        this.zza = iVar;
        Objects.requireNonNull(zznVar);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzb(Bundle bundle, j jVar) {
        this.zza.b(bundle);
    }
}
