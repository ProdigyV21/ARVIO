package com.google.firebase.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f14318b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ long f14319c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ TimeUnit f14320d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f14321e;

    public /* synthetic */ c(f fVar, Object obj, long j10, TimeUnit timeUnit, int i10) {
        this.f14317a = i10;
        this.f14318b = fVar;
        this.f14321e = obj;
        this.f14319c = j10;
        this.f14320d = timeUnit;
    }

    @Override // com.google.firebase.concurrent.i
    public final ScheduledFuture a(g gVar) {
        switch (this.f14317a) {
            case 0:
                Runnable runnable = (Runnable) this.f14321e;
                f fVar = this.f14318b;
                return fVar.f14333l.schedule(new e(fVar, runnable, gVar, 1), this.f14319c, this.f14320d);
            default:
                Callable callable = (Callable) this.f14321e;
                f fVar2 = this.f14318b;
                return fVar2.f14333l.schedule(new androidx.work.impl.f(fVar2, callable, gVar, 1), this.f14319c, this.f14320d);
        }
    }
}
