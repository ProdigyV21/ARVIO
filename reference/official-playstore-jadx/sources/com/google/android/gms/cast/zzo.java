package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
class zzo extends com.google.android.gms.cast.internal.zzc {
    public zzo(q qVar) {
        super(qVar);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ x createFailedResult(Status status) {
        return new zzn(this, status);
    }

    @Override // com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public void doExecute(com.google.android.gms.cast.internal.zzx zzxVar) throws RemoteException {
        throw null;
    }
}
