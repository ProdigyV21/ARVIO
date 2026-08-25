package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Status;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 implements com.google.android.gms.common.api.u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.common.api.v f12842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f12843b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ s f12844c;

    public g0(com.google.android.gms.common.api.v vVar, com.google.android.gms.tasks.i iVar, s sVar) {
        this.f12842a = vVar;
        this.f12843b = iVar;
        this.f12844c = sVar;
    }

    @Override // com.google.android.gms.common.api.u
    public final void a(Status status) {
        boolean zD = status.d();
        com.google.android.gms.tasks.i iVar = this.f12843b;
        if (!zD) {
            iVar.a(t.m(status));
            return;
        }
        iVar.b(this.f12844c.convert(this.f12842a.await(0L, TimeUnit.MILLISECONDS)));
    }
}
