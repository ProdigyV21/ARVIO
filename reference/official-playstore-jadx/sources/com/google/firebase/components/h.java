package com.google.firebase.components;

import android.os.StrictMode;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements m5.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14264a;

    @Override // m5.b
    public final Object get() {
        switch (this.f14264a) {
            case 0:
                return Collections.EMPTY_SET;
            case 1:
                return null;
            case 2:
                return ExecutorsRegistrar.a();
            case 3:
                o oVar = ExecutorsRegistrar.f14305a;
                return new com.google.firebase.concurrent.f(Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()), new com.google.firebase.concurrent.a("Firebase Lite", 0, new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())), (ScheduledExecutorService) ExecutorsRegistrar.f14308d.get());
            case 4:
                o oVar2 = ExecutorsRegistrar.f14305a;
                return new com.google.firebase.concurrent.f(Executors.newCachedThreadPool(new com.google.firebase.concurrent.a("Firebase Blocking", 11, null)), (ScheduledExecutorService) ExecutorsRegistrar.f14308d.get());
            default:
                o oVar3 = ExecutorsRegistrar.f14305a;
                return Executors.newSingleThreadScheduledExecutor(new com.google.firebase.concurrent.a("Firebase Scheduler", 0, null));
        }
    }
}
