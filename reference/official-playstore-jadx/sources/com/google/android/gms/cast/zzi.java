package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzi extends zzo {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzi(zzm zzmVar, q qVar, String str, String str2, zzbn zzbnVar) {
        super(qVar);
        this.zza = str;
        this.zzb = str2;
        Objects.requireNonNull(zzmVar);
    }

    @Override // com.google.android.gms.cast.zzo, com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzx zzxVar) throws RemoteException {
        try {
            zzxVar.zzr(this.zza, this.zzb, null, this);
        } catch (IllegalStateException unused) {
            zzc(2001);
        }
    }
}
