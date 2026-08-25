package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzz extends zzal {
    private final CastStateListener zza;

    public zzz(CastStateListener castStateListener) {
        this.zza = castStateListener;
    }

    @Override // com.google.android.gms.cast.framework.zzam
    public final b zzb() {
        return new d(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzam
    public final void zzc(int i10) {
        this.zza.onCastStateChanged(i10);
    }
}
