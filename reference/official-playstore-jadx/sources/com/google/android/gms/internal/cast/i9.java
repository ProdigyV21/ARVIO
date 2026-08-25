package com.google.android.gms.internal.cast;

import j$.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class i9 extends q9 {
    public static Object D(Object obj) throws ExecutionException {
        if (obj instanceof d9) {
            Throwable th = ((d9) obj).f13234b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof f9) {
            throw new ExecutionException(((f9) obj).f13251a);
        }
        if (obj == q9.f13555p) {
            return null;
        }
        return obj;
    }

    public static Object G(i9 i9Var) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = i9Var.get();
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
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static void H(i9 i9Var) {
        i9Var.getClass();
        for (p9 p9VarY = q9.f13558s.y(i9Var); p9VarY != null; p9VarY = p9VarY.f13545b) {
            Thread thread = p9VarY.f13544a;
            if (thread != null) {
                p9VarY.f13544a = null;
                LockSupport.unpark(thread);
            }
        }
        i9Var.E();
        g9 g9VarA = q9.f13558s.A(i9Var);
        g9 g9Var = null;
        while (g9VarA != null) {
            g9 g9Var2 = g9VarA.f13265c;
            g9VarA.f13265c = g9Var;
            g9Var = g9VarA;
            g9VarA = g9Var2;
        }
        while (g9Var != null) {
            Runnable runnable = g9Var.f13263a;
            g9 g9Var3 = g9Var.f13265c;
            Objects.requireNonNull(runnable);
            Executor executor = g9Var.f13264b;
            Objects.requireNonNull(executor);
            J(runnable, executor);
            g9Var = g9Var3;
        }
    }

    public static void J(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e5) {
            Logger loggerA = q9.f13556q.a();
            Level level = Level.SEVERE;
            String strValueOf = String.valueOf(runnable);
            String strValueOf2 = String.valueOf(executor);
            loggerA.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", androidx.fragment.app.a2.n(new StringBuilder(strValueOf.length() + 57 + strValueOf2.length()), "RuntimeException while executing runnable ", strValueOf, " with executor ", strValueOf2), (Throwable) e5);
        }
    }

    public void E() {
    }

    public abstract String F();

    public final void I(StringBuilder sb2) {
        try {
            Object objG = G(this);
            sb2.append("SUCCESS, result=[");
            if (objG == null) {
                sb2.append("null");
            } else if (objG == this) {
                sb2.append("this future");
            } else {
                sb2.append(objG.getClass().getName());
                sb2.append("@");
                sb2.append(Integer.toHexString(System.identityHashCode(objG)));
            }
            sb2.append("]");
        } catch (CancellationException unused) {
            sb2.append("CANCELLED");
        } catch (ExecutionException e5) {
            sb2.append("FAILURE, cause=[");
            sb2.append(e5.getCause());
            sb2.append("]");
        } catch (Exception e6) {
            sb2.append("UNKNOWN, cause=[");
            sb2.append(e6.getClass());
            sb2.append(" thrown from get()]");
        }
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        g9 g9Var;
        g9 g9Var2 = g9.f13262d;
        u0.p(executor, "Executor was null.");
        if (!isDone() && (g9Var = this.f13560n) != g9Var2) {
            g9 g9Var3 = new g9(runnable, executor);
            do {
                g9Var3.f13265c = g9Var;
                if (q9.f13558s.x(this, g9Var, g9Var3)) {
                    return;
                } else {
                    g9Var = this.f13560n;
                }
            } while (g9Var != g9Var2);
        }
        J(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        d9 d9Var;
        Object obj = this.f13559m;
        if (obj != null) {
            return false;
        }
        if (q9.f13557r) {
            d9Var = new d9(new CancellationException("Future.cancel() was called."), z);
        } else {
            d9Var = z ? d9.f13231c : d9.f13232d;
            Objects.requireNonNull(d9Var);
        }
        if (!q9.f13558s.B(this, obj, d9Var)) {
            return false;
        }
        H(this);
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        p9 p9Var = p9.f13543c;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.f13559m;
        if (obj2 != null) {
            return D(obj2);
        }
        p9 p9Var2 = this.f13561o;
        if (p9Var2 != p9Var) {
            p9 p9Var3 = new p9();
            do {
                u0 u0Var = q9.f13558s;
                u0Var.t(p9Var3, p9Var2);
                if (u0Var.v(this, p9Var2, p9Var3)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            C(p9Var3);
                            throw new InterruptedException();
                        }
                        obj = this.f13559m;
                    } while (obj == null);
                    return D(obj);
                }
                p9Var2 = this.f13561o;
            } while (p9Var2 != p9Var);
        }
        Object obj3 = this.f13559m;
        Objects.requireNonNull(obj3);
        return D(obj3);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f13559m instanceof d9;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f13559m != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006f  */
    @Override // com.google.android.gms.internal.cast.u0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.Class r1 = r6.getClass()
            java.lang.String r1 = r1.getName()
            java.lang.String r2 = "com.google.common.util.concurrent."
            boolean r1 = r1.startsWith(r2)
            if (r1 == 0) goto L21
            java.lang.Class r1 = r6.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r0.append(r1)
            goto L2c
        L21:
            java.lang.Class r1 = r6.getClass()
            java.lang.String r1 = r1.getName()
            r0.append(r1)
        L2c:
            r1 = 64
            r0.append(r1)
            int r1 = java.lang.System.identityHashCode(r6)
            java.lang.String r1 = java.lang.Integer.toHexString(r1)
            r0.append(r1)
            java.lang.String r1 = "[status="
            r0.append(r1)
            java.lang.Object r1 = r6.f13559m
            boolean r1 = r1 instanceof com.google.android.gms.internal.cast.d9
            java.lang.String r2 = "]"
            if (r1 == 0) goto L4f
            java.lang.String r1 = "CANCELLED"
            r0.append(r1)
            goto La9
        L4f:
            boolean r1 = r6.isDone()
            if (r1 == 0) goto L59
            r6.I(r0)
            goto La9
        L59:
            int r1 = r0.length()
            java.lang.String r3 = "PENDING"
            r0.append(r3)
            java.lang.String r3 = r6.F()     // Catch: java.lang.Throwable -> L71
            r4 = 0
            if (r3 == 0) goto L6f
            boolean r5 = r3.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r5 == 0) goto L8c
        L6f:
            r3 = r4
            goto L8c
        L71:
            r3 = move-exception
            boolean r4 = r3 instanceof java.lang.Error
            if (r4 == 0) goto L7e
            boolean r4 = r3 instanceof java.lang.StackOverflowError
            if (r4 == 0) goto L7b
            goto L7e
        L7b:
            java.lang.Error r3 = (java.lang.Error) r3
            throw r3
        L7e:
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r4 = "Exception thrown from implementation: "
            java.lang.String r3 = r4.concat(r3)
        L8c:
            if (r3 == 0) goto L99
            java.lang.String r4 = ", info=["
            r0.append(r4)
            r0.append(r3)
            r0.append(r2)
        L99:
            boolean r3 = r6.isDone()
            if (r3 == 0) goto La9
            int r3 = r0.length()
            r0.delete(r1, r3)
            r6.I(r0)
        La9:
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.i9.toString():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ab  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x009e -> B:29:0x006b). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(long r18, java.util.concurrent.TimeUnit r20) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.i9.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }
}
