package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import j$.util.Objects;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class i1 implements com.google.android.gms.common.api.u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BasePendingResult f12757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ io.sentry.internal.debugmeta.c f12758b;

    public i1(io.sentry.internal.debugmeta.c cVar, BasePendingResult basePendingResult) {
        this.f12757a = basePendingResult;
        Objects.requireNonNull(cVar);
        this.f12758b = cVar;
    }

    @Override // com.google.android.gms.common.api.u
    public final void a(Status status) {
        ((Map) this.f12758b.f17367l).remove(this.f12757a);
    }
}
