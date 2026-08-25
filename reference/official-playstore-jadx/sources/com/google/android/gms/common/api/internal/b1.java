package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class b1 extends p0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.google.android.gms.tasks.i f12703b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f12704c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f12705d;

    public b1(int i10, com.google.android.gms.tasks.i iVar) {
        super(i10);
        this.f12703b = iVar;
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void a(Status status) {
        this.f12703b.c(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void b(Exception exc) {
        this.f12703b.c(exc);
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final /* bridge */ /* synthetic */ void c(io.sentry.internal.debugmeta.c cVar, boolean z) {
        int i10 = this.f12704c;
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void d(e0 e0Var) throws DeadObjectException {
        try {
            j(e0Var);
        } catch (DeadObjectException e5) {
            a(d1.e(e5));
            throw e5;
        } catch (RemoteException e6) {
            a(d1.e(e6));
        } catch (RuntimeException e10) {
            this.f12703b.c(e10);
        }
    }

    @Override // com.google.android.gms.common.api.internal.p0
    public final o3.d[] f(e0 e0Var) {
        switch (this.f12704c) {
            case 0:
                return ((r0) this.f12705d).f12791a.f12781b;
            default:
                r0 r0Var = (r0) e0Var.f12721h.get((l) this.f12705d);
                if (r0Var == null) {
                    return null;
                }
                return r0Var.f12791a.f12781b;
        }
    }

    @Override // com.google.android.gms.common.api.internal.p0
    public final boolean g(e0 e0Var) {
        switch (this.f12704c) {
            case 0:
                return true;
            default:
                return ((r0) e0Var.f12721h.get((l) this.f12705d)) != null;
        }
    }

    public final void j(e0 e0Var) {
        switch (this.f12704c) {
            case 0:
                r0 r0Var = (r0) this.f12705d;
                q qVar = r0Var.f12791a;
                ((t) ((s0) qVar).f12796d.f12787b).accept(e0Var.f12717d, this.f12703b);
                l lVar = qVar.f12780a.f12769c;
                if (lVar != null) {
                    e0Var.f12721h.put(lVar, r0Var);
                }
                break;
            default:
                r0 r0Var2 = (r0) e0Var.f12721h.remove((l) this.f12705d);
                if (r0Var2 == null) {
                    this.f12703b.d(Boolean.FALSE);
                } else {
                    ((t) ((t0) r0Var2.f12792b).f12797b.f12788c).accept(e0Var.f12717d, this.f12703b);
                    n nVar = r0Var2.f12791a.f12780a;
                    nVar.f12768b = null;
                    nVar.f12769c = null;
                }
                break;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b1(l lVar, com.google.android.gms.tasks.i iVar) {
        this(4, iVar);
        this.f12704c = 1;
        this.f12705d = lVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b1(r0 r0Var, com.google.android.gms.tasks.i iVar) {
        this(3, iVar);
        this.f12704c = 0;
        this.f12705d = r0Var;
    }

    private final /* bridge */ /* synthetic */ void h(io.sentry.internal.debugmeta.c cVar, boolean z) {
    }

    private final /* bridge */ /* synthetic */ void i(io.sentry.internal.debugmeta.c cVar, boolean z) {
    }
}
