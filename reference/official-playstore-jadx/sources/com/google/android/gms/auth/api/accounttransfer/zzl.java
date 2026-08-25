package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.internal.auth.n;
import com.google.android.gms.internal.auth.s;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
abstract class zzl extends y {
    protected i zzb;

    public /* synthetic */ zzl(int i10, zzk zzkVar) {
        super(null, false, i10);
    }

    @Override // com.google.android.gms.common.api.internal.y
    public final /* bridge */ /* synthetic */ void doExecute(b bVar, i iVar) throws RemoteException {
        this.zzb = iVar;
        zza((s) ((n) bVar).getService());
    }

    public abstract void zza(s sVar) throws RemoteException;
}
