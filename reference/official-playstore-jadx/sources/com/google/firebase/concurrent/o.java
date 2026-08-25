package com.google.firebase.concurrent;

import com.google.android.gms.common.internal.t;
import com.google.common.util.concurrent.w0;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class o implements Executor {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Logger f14337p = Logger.getLogger(o.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Executor f14338i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayDeque f14339l = new ArrayDeque();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14340m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f14341n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final w0 f14342o = new w0(this);

    public o(Executor executor) {
        t.i(executor);
        this.f14338i = executor;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        t.i(runnable);
        synchronized (this.f14339l) {
            int i10 = this.f14340m;
            if (i10 != 4 && i10 != 3) {
                long j10 = this.f14341n;
                n nVar = new n(runnable);
                this.f14339l.add(nVar);
                this.f14340m = 2;
                try {
                    this.f14338i.execute(this.f14342o);
                    if (this.f14340m != 2) {
                        return;
                    }
                    synchronized (this.f14339l) {
                        try {
                            if (this.f14341n == j10 && this.f14340m == 2) {
                                this.f14340m = 3;
                            }
                        } finally {
                        }
                    }
                    return;
                } catch (Error | RuntimeException e5) {
                    synchronized (this.f14339l) {
                        try {
                            int i11 = this.f14340m;
                            boolean z = true;
                            if ((i11 != 1 && i11 != 2) || !this.f14339l.removeLastOccurrence(nVar)) {
                                z = false;
                            }
                            if (!(e5 instanceof RejectedExecutionException) || z) {
                                throw e5;
                            }
                        } finally {
                        }
                    }
                    return;
                }
            }
            this.f14339l.add(runnable);
        }
    }

    public final String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.f14338i + "}";
    }
}
