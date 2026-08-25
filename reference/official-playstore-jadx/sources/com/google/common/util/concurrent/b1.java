package com.google.common.util.concurrent;

import androidx.fragment.app.a2;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b1 extends AtomicReference implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final androidx.emoji2.text.s f14189i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final androidx.emoji2.text.s f14190l;

    static {
        int i10 = 2;
        f14189i = new androidx.emoji2.text.s(i10);
        f14190l = new androidx.emoji2.text.s(i10);
    }

    public abstract void a(Throwable th);

    public abstract void b(Object obj);

    public final void c() {
        androidx.emoji2.text.s sVar = f14190l;
        androidx.emoji2.text.s sVar2 = f14189i;
        Runnable runnable = (Runnable) get();
        if (runnable instanceof Thread) {
            a1 a1Var = new a1(this);
            a1.a(a1Var, Thread.currentThread());
            if (compareAndSet(runnable, a1Var)) {
                try {
                    ((Thread) runnable).interrupt();
                } finally {
                    if (((Runnable) getAndSet(sVar2)) == sVar) {
                        LockSupport.unpark((Thread) runnable);
                    }
                }
            }
        }
    }

    public abstract boolean d();

    public abstract Object e();

    public abstract String f();

    public final void g(Thread thread) {
        Runnable runnable = (Runnable) get();
        a1 a1Var = null;
        boolean z = false;
        int i10 = 0;
        while (true) {
            boolean z5 = runnable instanceof a1;
            androidx.emoji2.text.s sVar = f14190l;
            if (!z5 && runnable != sVar) {
                break;
            }
            if (z5) {
                a1Var = (a1) runnable;
            }
            i10++;
            if (i10 <= 1000) {
                Thread.yield();
            } else if (runnable == sVar || compareAndSet(runnable, sVar)) {
                z = Thread.interrupted() || z;
                LockSupport.park(a1Var);
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread threadCurrentThread = Thread.currentThread();
        Object objE = null;
        if (compareAndSet(null, threadCurrentThread)) {
            boolean zD = d();
            androidx.emoji2.text.s sVar = f14189i;
            if (!zD) {
                try {
                    objE = e();
                } catch (Throwable th) {
                    try {
                        if (th instanceof InterruptedException) {
                            Thread.currentThread().interrupt();
                        }
                        if (!compareAndSet(threadCurrentThread, sVar)) {
                            g(threadCurrentThread);
                        }
                        if (zD) {
                            return;
                        }
                        a(th);
                        return;
                    } finally {
                        if (!compareAndSet(threadCurrentThread, sVar)) {
                            g(threadCurrentThread);
                        }
                        if (!zD) {
                            b(null);
                        }
                    }
                }
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == f14189i) {
            str = "running=[DONE]";
        } else if (runnable instanceof a1) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        StringBuilder sbP = a2.p(str, ", ");
        sbP.append(f());
        return sbP.toString();
    }
}
