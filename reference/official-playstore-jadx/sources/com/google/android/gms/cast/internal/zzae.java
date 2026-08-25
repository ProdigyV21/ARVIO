package com.google.android.gms.cast.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;

/* JADX INFO: loaded from: classes4.dex */
public class zzae extends zzc {
    public zzae(q qVar) {
        super(qVar);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ x createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.d
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public void doExecute(zzx zzxVar) throws RemoteException {
        throw null;
    }
}
