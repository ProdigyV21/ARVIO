package com.google.android.datatransport.runtime;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes4.dex */
public final class s implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Executor f12606i;

    public s(ExecutorService executorService) {
        this.f12606i = executorService;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f12606i.execute(new r(runnable));
    }
}
