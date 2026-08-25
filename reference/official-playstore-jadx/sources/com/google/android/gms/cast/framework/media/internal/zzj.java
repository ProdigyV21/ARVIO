package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzj implements zza {
    final /* synthetic */ zzl zza;
    final /* synthetic */ zzm zzb;

    public zzj(zzm zzmVar, zzl zzlVar) {
        this.zza = zzlVar;
        Objects.requireNonNull(zzmVar);
        this.zzb = zzmVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        zzl zzlVar = this.zza;
        zzlVar.zzb = bitmap;
        zzm zzmVar = this.zzb;
        zzmVar.zze(zzlVar);
        zzmVar.zzd();
    }
}
