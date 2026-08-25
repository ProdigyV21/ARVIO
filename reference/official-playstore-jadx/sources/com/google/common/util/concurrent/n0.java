package com.google.common.util.concurrent;

import com.google.common.collect.o4;
import j$.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 extends u {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final c1 f14222q = new c1(n0.class);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public com.google.common.collect.c1 f14223o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m0 f14224p;

    public n0(com.google.common.collect.h1 h1Var, androidx.loader.content.g gVar) {
        int size = h1Var.size();
        this.f14240i = null;
        this.f14241l = size;
        this.f14223o = h1Var;
        this.f14224p = new m0(this, gVar);
        s0 s0Var = s0.f14233i;
        Objects.requireNonNull(this.f14223o);
        if (!this.f14223o.isEmpty()) {
            androidx.activity.s sVar = new androidx.activity.s(this, (Object) null, 17);
            o4 it = this.f14223o.iterator();
            while (it.hasNext()) {
                d1 d1Var = (d1) it.next();
                if (d1Var.isDone()) {
                    h(null);
                } else {
                    d1Var.addListener(sVar, s0Var);
                }
            }
            return;
        }
        m0 m0Var = this.f14224p;
        if (m0Var != null) {
            try {
                m0Var.f14212m.getClass();
                m0Var.run();
            } catch (RejectedExecutionException e5) {
                m0Var.f14213n.setException(e5);
            }
        }
    }

    @Override // com.google.common.util.concurrent.p
    public final void afterDone() {
        super.afterDone();
        com.google.common.collect.c1 c1Var = this.f14223o;
        this.f14223o = null;
        this.f14224p = null;
        if (isCancelled() && (c1Var != null)) {
            boolean zWasInterrupted = wasInterrupted();
            o4 it = c1Var.iterator();
            while (it.hasNext()) {
                ((Future) it.next()).cancel(zWasInterrupted);
            }
        }
    }

    public final void h(com.google.common.collect.c1 c1Var) {
        int iO = u.f14238m.o(this);
        ac.b.t(iO >= 0, "Less than 0 remaining futures");
        if (iO == 0) {
            if (c1Var != null) {
                o4 it = c1Var.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    if (!future.isCancelled()) {
                        boolean z = false;
                        while (true) {
                            try {
                                future.get();
                                break;
                            } catch (InterruptedException unused) {
                                z = true;
                            } catch (Throwable th) {
                                if (z) {
                                    Thread.currentThread().interrupt();
                                }
                                throw th;
                            }
                        }
                        if (z) {
                            try {
                                Thread.currentThread().interrupt();
                            } catch (ExecutionException e5) {
                                i(e5.getCause());
                            } catch (Throwable th2) {
                                i(th2);
                            }
                        }
                    }
                }
            }
            this.f14240i = null;
            m0 m0Var = this.f14224p;
            if (m0Var != null) {
                try {
                    m0Var.f14212m.getClass();
                    m0Var.run();
                } catch (RejectedExecutionException e6) {
                    m0Var.f14213n.setException(e6);
                }
            }
            this.f14223o = null;
        }
    }

    public final void i(Throwable th) {
        th.getClass();
        boolean z = th instanceof Error;
        if (z) {
            f14222q.a().log(Level.SEVERE, z ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
        }
    }

    @Override // com.google.common.util.concurrent.p
    public final void interruptTask() {
        m0 m0Var = this.f14224p;
        if (m0Var != null) {
            m0Var.c();
        }
    }

    @Override // com.google.common.util.concurrent.p
    public final String pendingToString() {
        com.google.common.collect.c1 c1Var = this.f14223o;
        if (c1Var == null) {
            return super.pendingToString();
        }
        return "futures=" + c1Var;
    }
}
