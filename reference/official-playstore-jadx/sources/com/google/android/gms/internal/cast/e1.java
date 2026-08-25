package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e1 implements com.google.android.gms.tasks.d, com.google.android.gms.tasks.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.common.api.d0 f13236i;

    public /* synthetic */ e1(com.google.android.gms.common.api.d0 d0Var) {
        this.f13236i = d0Var;
    }

    @Override // com.google.android.gms.tasks.d
    public void onFailure(Exception exc) {
        Status status = new Status(8, "unknown error", null, null);
        if (exc instanceof ApiException) {
            ApiException apiException = (ApiException) exc;
            status = new Status(apiException.getStatusCode(), apiException.getMessage(), null, null);
        }
        int i10 = CastSession.zza;
        this.f13236i.setResult(status);
    }

    @Override // com.google.android.gms.tasks.e
    public void onSuccess(Object obj) {
        int i10 = CastSession.zza;
        this.f13236i.setResult(new Status(0, null, null, null));
    }
}
