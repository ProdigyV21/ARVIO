package com.google.firebase.concurrent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14322a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f14323b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Runnable f14324c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ long f14325d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ long f14326e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ TimeUnit f14327f;

    public /* synthetic */ d(f fVar, Runnable runnable, long j10, long j11, TimeUnit timeUnit, int i10) {
        this.f14322a = i10;
        this.f14323b = fVar;
        this.f14324c = runnable;
        this.f14325d = j10;
        this.f14326e = j11;
        this.f14327f = timeUnit;
    }

    @Override // com.google.firebase.concurrent.i
    public final ScheduledFuture a(g gVar) {
        switch (this.f14322a) {
            case 0:
                f fVar = this.f14323b;
                return fVar.f14333l.scheduleAtFixedRate(new e(fVar, this.f14324c, gVar, 0), this.f14325d, this.f14326e, this.f14327f);
            default:
                f fVar2 = this.f14323b;
                return fVar2.f14333l.scheduleWithFixedDelay(new e(fVar2, this.f14324c, gVar, 2), this.f14325d, this.f14326e, this.f14327f);
        }
    }
}
