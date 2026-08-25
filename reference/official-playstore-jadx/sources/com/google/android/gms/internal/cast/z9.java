package com.google.android.gms.internal.cast;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes4.dex */
public final class z9 extends AtomicReference implements Runnable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t9 f13732m = new t9();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final t9 f13733n = new t9();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Callable f13734i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ aa f13735l;

    public z9(aa aaVar, Callable callable) {
        this.f13735l = aaVar;
        callable.getClass();
        this.f13734i = callable;
    }

    public final void a(Thread thread) {
        Runnable runnable = (Runnable) get();
        s9 s9Var = null;
        boolean z = false;
        int i10 = 0;
        while (true) {
            boolean z5 = runnable instanceof s9;
            t9 t9Var = f13733n;
            if (!z5) {
                if (runnable != t9Var) {
                    break;
                }
            } else {
                s9Var = (s9) runnable;
            }
            i10++;
            if (i10 <= 1000) {
                Thread.yield();
            } else if (runnable == t9Var || compareAndSet(runnable, t9Var)) {
                z = Thread.interrupted() || z;
                LockSupport.park(s9Var);
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        Thread threadCurrentThread = Thread.currentThread();
        if (compareAndSet(null, threadCurrentThread)) {
            aa aaVar = this.f13735l;
            boolean zIsDone = aaVar.isDone();
            t9 t9Var = f13732m;
            if (zIsDone) {
                objCall = null;
            } else {
                try {
                    objCall = this.f13734i.call();
                } catch (Throwable th) {
                    try {
                        if (th instanceof InterruptedException) {
                            Thread.currentThread().interrupt();
                        }
                        if (!compareAndSet(threadCurrentThread, t9Var)) {
                            a(threadCurrentThread);
                        }
                        if (q9.f13558s.B(aaVar, null, new f9(th))) {
                            i9.H(aaVar);
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        if (!compareAndSet(threadCurrentThread, t9Var)) {
                            a(threadCurrentThread);
                        }
                        aaVar.getClass();
                        if (q9.f13558s.B(aaVar, null, q9.f13555p)) {
                            i9.H(aaVar);
                        }
                        throw th2;
                    }
                }
            }
            if (!compareAndSet(threadCurrentThread, t9Var)) {
                a(threadCurrentThread);
            }
            if (zIsDone) {
                return;
            }
            aaVar.getClass();
            if (objCall == null) {
                objCall = q9.f13555p;
            }
            if (q9.f13558s.B(aaVar, null, objCall)) {
                i9.H(aaVar);
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String strQ;
        Runnable runnable = (Runnable) get();
        if (runnable == f13732m) {
            strQ = "running=[DONE]";
        } else if (runnable instanceof s9) {
            strQ = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            strQ = androidx.compose.material3.d.q(new StringBuilder(String.valueOf(name).length() + 21), "running=[RUNNING ON ", name, "]");
        } else {
            strQ = "running=[NOT STARTED YET]";
        }
        String string = this.f13734i.toString();
        return androidx.compose.material3.d.q(new StringBuilder(strQ.length() + 2 + String.valueOf(string).length()), strQ, ", ", string);
    }
}
