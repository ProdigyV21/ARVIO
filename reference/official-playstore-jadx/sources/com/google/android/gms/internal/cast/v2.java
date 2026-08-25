package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class v2 extends com.google.android.gms.common.api.internal.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ x2 f13636a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v2(x2 x2Var, com.google.android.gms.common.api.q qVar) {
        super(x2Var.f13683a, qVar);
        this.f13636a = x2Var;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ com.google.android.gms.common.api.x createFailedResult(Status status) {
        return new w2(status);
    }
}
