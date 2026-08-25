package com.google.android.gms.cast.internal;

/* JADX INFO: loaded from: classes4.dex */
public class zzq {
    protected final Logger zza;
    private final String zzb;
    private zzas zzc;

    public zzq(String str, String str2, String str3) {
        CastUtils.throwIfInvalidNamespace(str);
        this.zzb = str;
        this.zza = new Logger("MediaControlChannel", null);
    }

    public final String zzd() {
        return this.zzb;
    }

    public final void zze(zzas zzasVar) {
        this.zzc = zzasVar;
        if (zzasVar == null) {
            zzh();
        }
    }

    public final void zzf(String str, long j10, String str2) throws IllegalStateException {
        Logger logger = this.zza;
        logger.v("Sending text message: %s to: %s", str, null);
        zzas zzasVar = this.zzc;
        if (zzasVar == null) {
            logger.e("Attempt to send text message without a sink", new Object[0]);
        } else {
            zzasVar.zzb(this.zzb, str, j10, null);
        }
    }

    public final long zzg() {
        zzas zzasVar = this.zzc;
        if (zzasVar != null) {
            return zzasVar.zzc();
        }
        this.zza.e("Attempt to generate requestId without a sink", new Object[0]);
        return 0L;
    }

    public void zzh() {
        throw null;
    }
}
