package com.google.android.gms.common.api.internal;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends com.google.android.gms.common.api.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BasePendingResult f12779a;

    public p(BasePendingResult basePendingResult) {
        this.f12779a = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.v
    public final void addStatusListener(com.google.android.gms.common.api.u uVar) {
        this.f12779a.addStatusListener(uVar);
    }

    @Override // com.google.android.gms.common.api.v
    public final com.google.android.gms.common.api.x await(long j10, TimeUnit timeUnit) {
        return this.f12779a.await(0L, TimeUnit.MILLISECONDS);
    }

    @Override // com.google.android.gms.common.api.v
    public final void cancel() {
        this.f12779a.cancel();
    }

    @Override // com.google.android.gms.common.api.v
    public final void setResultCallback(com.google.android.gms.common.api.y yVar) {
        this.f12779a.setResultCallback(yVar);
    }
}
