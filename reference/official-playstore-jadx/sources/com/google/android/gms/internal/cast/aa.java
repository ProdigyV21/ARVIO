package com.google.android.gms.internal.cast;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes4.dex */
public final class aa extends i9 implements RunnableFuture, h9 {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile z9 f13169t;

    public aa(Callable callable) {
        super(14);
        this.f13169t = new z9(this, callable);
    }

    @Override // com.google.android.gms.internal.cast.i9
    public final void E() {
        z9 z9Var;
        Object obj = this.f13559m;
        if ((obj instanceof d9) && ((d9) obj).f13233a && (z9Var = this.f13169t) != null) {
            t9 t9Var = z9.f13733n;
            t9 t9Var2 = z9.f13732m;
            Runnable runnable = (Runnable) z9Var.get();
            if (runnable instanceof Thread) {
                s9 s9Var = new s9(z9Var);
                s9Var.a(Thread.currentThread());
                if (z9Var.compareAndSet(runnable, s9Var)) {
                    try {
                        Thread thread = (Thread) runnable;
                        thread.interrupt();
                        if (((Runnable) z9Var.getAndSet(t9Var2)) == t9Var) {
                            LockSupport.unpark(thread);
                        }
                    } catch (Throwable th) {
                        if (((Runnable) z9Var.getAndSet(t9Var2)) == t9Var) {
                            LockSupport.unpark((Thread) runnable);
                        }
                        throw th;
                    }
                }
            }
        }
        this.f13169t = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.cast.i9
    public final String F() {
        z9 z9Var = this.f13169t;
        if (z9Var != null) {
            String string = z9Var.toString();
            return androidx.compose.material3.d.q(new StringBuilder(string.length() + 7), "task=[", string, "]");
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        StringBuilder sb2 = new StringBuilder(String.valueOf(delay).length() + 21);
        sb2.append("remaining delay=[");
        sb2.append(delay);
        sb2.append(" ms]");
        return sb2.toString();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        z9 z9Var = this.f13169t;
        if (z9Var != null) {
            z9Var.run();
        }
        this.f13169t = null;
    }
}
