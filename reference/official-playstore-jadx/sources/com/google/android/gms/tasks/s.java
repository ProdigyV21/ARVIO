package com.google.android.gms.tasks;

import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class s implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.loader.content.j f13879i;

    public s() {
        androidx.loader.content.j jVar = new androidx.loader.content.j(Looper.getMainLooper(), 5, false);
        Looper.getMainLooper();
        this.f13879i = jVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f13879i.post(runnable);
    }
}
