package com.google.android.gms.cast.framework;

/* JADX INFO: loaded from: classes4.dex */
public final class zzo {
    private int zza = 0;
    private int zzb = -1;

    public final zzo zza(int i10) {
        this.zza = i10;
        return this;
    }

    public final zzo zzb(int i10) {
        this.zzb = i10;
        return this;
    }

    public final zzp zzc() {
        int iZzb = this.zza;
        if (iZzb == 0) {
            iZzb = CastContext.zzb(this.zzb);
            this.zza = iZzb;
        }
        return new zzp(iZzb, this.zzb, null);
    }
}
