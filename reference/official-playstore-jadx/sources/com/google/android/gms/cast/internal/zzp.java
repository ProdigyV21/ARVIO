package com.google.android.gms.cast.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class zzp {
    private final int zza;

    public zzp(int i10, zzaa zzaaVar) {
        this.zza = i10;
    }

    public final int zza() {
        return this.zza;
    }

    public final boolean zzb(int i10) {
        return (this.zza & i10) == i10;
    }

    public final boolean zzc() {
        return !(!zzb(32) || zzb(64) || zzb(128)) || zzb(64);
    }

    public final boolean zzd() {
        return zzc() || zzb(128);
    }
}
