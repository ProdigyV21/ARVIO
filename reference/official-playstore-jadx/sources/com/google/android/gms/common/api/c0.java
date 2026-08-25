package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends BasePendingResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Status f12681a;

    public c0(Status status) {
        super((q) null);
        this.f12681a = status;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final x createFailedResult(Status status) {
        return this.f12681a;
    }
}
