package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzo implements zza {
    final /* synthetic */ zzs zza;

    public zzo(zzs zzsVar) {
        Objects.requireNonNull(zzsVar);
        this.zza = zzsVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        this.zza.zze(bitmap, 3);
    }
}
