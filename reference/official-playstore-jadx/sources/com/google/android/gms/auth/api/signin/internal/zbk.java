package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
final class zbk extends zbl {
    public zbk(q qVar) {
        super(qVar);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ x createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final /* bridge */ /* synthetic */ void doExecute(b bVar) throws RemoteException {
        zbe zbeVar = (zbe) bVar;
        ((zbs) zbeVar.getService()).zbc(new zbj(this), zbeVar.zba());
    }
}
