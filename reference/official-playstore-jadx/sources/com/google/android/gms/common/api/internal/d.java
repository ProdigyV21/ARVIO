package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d extends BasePendingResult implements e {
    private final com.google.android.gms.common.api.i api;
    private final com.google.android.gms.common.api.c clientKey;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(com.google.android.gms.common.api.i iVar, com.google.android.gms.common.api.q qVar) {
        super(qVar);
        com.google.android.gms.common.internal.t.j(qVar, "GoogleApiClient must not be null");
        com.google.android.gms.common.internal.t.j(iVar, "Api must not be null");
        this.clientKey = iVar.f12687b;
        this.api = iVar;
    }

    public abstract void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException;

    public final com.google.android.gms.common.api.i getApi() {
        return this.api;
    }

    public final com.google.android.gms.common.api.c getClientKey() {
        return this.clientKey;
    }

    public void onSetFailedResult(com.google.android.gms.common.api.x xVar) {
    }

    public final void run(com.google.android.gms.common.api.b bVar) throws DeadObjectException {
        try {
            doExecute(bVar);
        } catch (DeadObjectException e5) {
            setFailedResult(new Status(8, e5.getLocalizedMessage(), null, null));
            throw e5;
        } catch (RemoteException e6) {
            setFailedResult(new Status(8, e6.getLocalizedMessage(), null, null));
        }
    }

    public final void setFailedResult(Status status) {
        com.google.android.gms.common.internal.t.a("Failed result must not be success", !status.d());
        com.google.android.gms.common.api.x xVarCreateFailedResult = createFailedResult(status);
        setResult(xVarCreateFailedResult);
        onSetFailedResult(xVarCreateFailedResult);
    }
}
