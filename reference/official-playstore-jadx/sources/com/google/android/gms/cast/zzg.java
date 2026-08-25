package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzg extends zzo {
    final /* synthetic */ String zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzg(zzm zzmVar, q qVar, String str) {
        super(qVar);
        this.zza = str;
        Objects.requireNonNull(zzmVar);
    }

    @Override // com.google.android.gms.cast.zzo, com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzx zzxVar) throws RemoteException {
        try {
            String str = this.zza;
            LaunchOptions launchOptions = new LaunchOptions();
            launchOptions.setRelaunchIfRunning(false);
            zzxVar.zzq(str, launchOptions, this);
        } catch (IllegalStateException unused) {
            zzc(2001);
        }
    }
}
