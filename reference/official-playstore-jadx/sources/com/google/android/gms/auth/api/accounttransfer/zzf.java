package com.google.android.gms.auth.api.accounttransfer;

/* JADX INFO: loaded from: classes4.dex */
final class zzf extends zzj {
    final /* synthetic */ zzg zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzf(zzg zzgVar, zzl zzlVar) {
        super(zzlVar);
        this.zza = zzgVar;
    }

    @Override // com.google.android.gms.internal.auth.l, com.google.android.gms.internal.auth.r
    public final void zzc(DeviceMetaData deviceMetaData) {
        this.zza.zzb.b(deviceMetaData);
    }
}
