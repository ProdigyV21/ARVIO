package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.n;
import com.google.android.gms.tasks.t;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
public class CrashlyticsBackgroundWorker {
    private final Executor executor;
    private h tail = l.n(null);
    private final Object tailLock = new Object();
    private final ThreadLocal<Boolean> isExecutorThread = new ThreadLocal<>();

    public CrashlyticsBackgroundWorker(Executor executor) {
        this.executor = executor;
        executor.execute(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.1
            @Override // java.lang.Runnable
            public void run() {
                CrashlyticsBackgroundWorker.this.isExecutorThread.set(Boolean.TRUE);
            }
        });
    }

    private <T> h ignoreResult(h hVar) {
        return hVar.d(this.executor, new com.google.android.gms.tasks.a<T, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.4
            @Override // com.google.android.gms.tasks.a
            public Void then(h hVar2) throws Exception {
                return null;
            }
        });
    }

    private boolean isRunningOnThread() {
        return Boolean.TRUE.equals(this.isExecutorThread.get());
    }

    private <T> com.google.android.gms.tasks.a<Void, T> newContinuation(final Callable<T> callable) {
        return new com.google.android.gms.tasks.a<Void, T>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.3
            @Override // com.google.android.gms.tasks.a
            public T then(h hVar) throws Exception {
                return (T) callable.call();
            }
        };
    }

    public void checkRunningOnThread() {
        if (!isRunningOnThread()) {
            throw new IllegalStateException("Not running on background worker thread as intended.");
        }
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public h submit(final Runnable runnable) {
        return submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.2
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                runnable.run();
                return null;
            }
        });
    }

    public <T> h submitTask(Callable<h> callable) {
        t tVar;
        synchronized (this.tailLock) {
            h hVar = this.tail;
            Executor executor = this.executor;
            com.google.android.gms.tasks.a<Void, T> aVarNewContinuation = newContinuation(callable);
            t tVar2 = (t) hVar;
            tVar2.getClass();
            tVar = new t();
            tVar2.f13881b.a(new n(executor, aVarNewContinuation, tVar, 1));
            tVar2.n();
            this.tail = ignoreResult(tVar);
        }
        return tVar;
    }

    public <T> h submit(Callable<T> callable) {
        t tVarD;
        synchronized (this.tailLock) {
            tVarD = this.tail.d(this.executor, newContinuation(callable));
            this.tail = ignoreResult(tVarD);
        }
        return tVarD;
    }
}
