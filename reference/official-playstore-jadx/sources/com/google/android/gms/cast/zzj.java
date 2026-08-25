package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzj extends com.google.android.gms.cast.internal.zzae {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzj(zzm zzmVar, q qVar) {
        super(qVar);
        Objects.requireNonNull(zzmVar);
    }

    @Override // com.google.android.gms.cast.internal.zzae, com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzx zzxVar) throws RemoteException {
        try {
            zzxVar.zzs(this);
        } catch (IllegalStateException unused) {
            zzc(2001);
        }
    }
}
