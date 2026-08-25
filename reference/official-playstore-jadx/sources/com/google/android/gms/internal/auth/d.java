package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zze;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends com.google.android.gms.common.api.internal.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f13015a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(com.google.android.gms.common.api.i iVar, com.google.android.gms.common.api.q qVar, boolean z) {
        super(iVar, qVar);
        this.f13015a = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final com.google.android.gms.common.api.x createFailedResult(Status status) {
        return new i(status, 0);
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final /* bridge */ /* synthetic */ void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException {
        ((zze) ((k) bVar).getService()).zzf(this.f13015a);
        setResult(new i(Status.f12670o, 0));
    }
}
