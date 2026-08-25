package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class c1 extends p0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y f12711b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final com.google.android.gms.tasks.i f12712c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v f12713d;

    public c1(int i10, y yVar, com.google.android.gms.tasks.i iVar, v vVar) {
        super(i10);
        this.f12712c = iVar;
        this.f12711b = yVar;
        this.f12713d = vVar;
        if (i10 == 2 && yVar.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void a(Status status) {
        this.f12712c.c(this.f12713d.d(status));
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void b(Exception exc) {
        this.f12712c.c(exc);
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void c(io.sentry.internal.debugmeta.c cVar, boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        Map map = (Map) cVar.f17368m;
        com.google.android.gms.tasks.i iVar = this.f12712c;
        map.put(iVar, boolValueOf);
        iVar.f13855a.a(new io.sentry.internal.debugmeta.c(cVar, iVar));
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void d(e0 e0Var) throws DeadObjectException {
        com.google.android.gms.tasks.i iVar = this.f12712c;
        try {
            this.f12711b.doExecute(e0Var.f12717d, iVar);
        } catch (DeadObjectException e5) {
            throw e5;
        } catch (RemoteException e6) {
            a(d1.e(e6));
        } catch (RuntimeException e10) {
            iVar.c(e10);
        }
    }

    @Override // com.google.android.gms.common.api.internal.p0
    public final o3.d[] f(e0 e0Var) {
        return this.f12711b.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.p0
    public final boolean g(e0 e0Var) {
        return this.f12711b.shouldAutoResolveMissingFeatures();
    }
}
