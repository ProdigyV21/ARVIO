package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzh extends zzo {
    final /* synthetic */ String zza;
    final /* synthetic */ LaunchOptions zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzh(zzm zzmVar, q qVar, String str, LaunchOptions launchOptions) {
        super(qVar);
        this.zza = str;
        this.zzb = launchOptions;
        Objects.requireNonNull(zzmVar);
    }

    @Override // com.google.android.gms.cast.zzo, com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzx zzxVar) throws RemoteException {
        try {
            zzxVar.zzq(this.zza, this.zzb, this);
        } catch (IllegalStateException unused) {
            zzc(2001);
        }
    }
}
