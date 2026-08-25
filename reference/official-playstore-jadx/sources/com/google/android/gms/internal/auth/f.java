package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zze;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends com.google.android.gms.common.api.internal.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f13027a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(com.google.android.gms.common.api.i iVar, com.google.android.gms.common.api.q qVar, String str) {
        super(iVar, qVar);
        this.f13027a = str;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ com.google.android.gms.common.api.x createFailedResult(Status status) {
        return new h(status, null);
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final /* bridge */ /* synthetic */ void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException {
        ((zze) ((k) bVar).getService()).zzd(new e(0, this), this.f13027a);
    }
}
