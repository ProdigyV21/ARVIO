package com.google.common.util.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class r extends u0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public d1 f14228i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public com.google.common.base.m f14229l;

    @Override // com.google.common.util.concurrent.p
    public final void afterDone() {
        maybePropagateCancellationTo(this.f14228i);
        this.f14228i = null;
        this.f14229l = null;
    }

    @Override // com.google.common.util.concurrent.p
    public final String pendingToString() {
        String str;
        d1 d1Var = this.f14228i;
        com.google.common.base.m mVar = this.f14229l;
        String strPendingToString = super.pendingToString();
        if (d1Var != null) {
            str = "inputFuture=[" + d1Var + "], ";
        } else {
            str = "";
        }
        if (mVar == null) {
            if (strPendingToString != null) {
                return androidx.compose.material3.d.m(str, strPendingToString);
            }
            return null;
        }
        return str + "function=[" + mVar + "]";
    }

    @Override // java.lang.Runnable
    public final void run() {
        d1<Object> d1Var = this.f14228i;
        com.google.common.base.m mVar = this.f14229l;
        if ((isCancelled() | (d1Var == null)) || (mVar == null)) {
            return;
        }
        this.f14228i = null;
        if (d1Var.isCancelled()) {
            setFuture(d1Var);
            return;
        }
        try {
            try {
                Object objApply = mVar.apply(xc.d.j0(d1Var));
                this.f14229l = null;
                set(objApply);
            } catch (Throwable th) {
                try {
                    if (th instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                    }
                    setException(th);
                } finally {
                    this.f14229l = null;
                }
            }
        } catch (Error e5) {
            setException(e5);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (ExecutionException e6) {
            setException(e6.getCause());
        } catch (Exception e10) {
            setException(e10);
        }
    }
}
