package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f14205i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d1 f14206l;

    public f(p pVar, d1 d1Var) {
        this.f14205i = pVar;
        this.f14206l = d1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        p pVar = this.f14205i;
        if (pVar.value != this) {
            return;
        }
        if (p.ATOMIC_HELPER.b(pVar, this, p.f(this.f14206l))) {
            p.c(pVar, false);
        }
    }
}
