package com.typesafe.config.impl;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class o implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14595a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ClassLoader f14596b;

    public /* synthetic */ o(ClassLoader classLoader, int i10) {
        this.f14595a = i10;
        this.f14596b = classLoader;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f14595a) {
            case 0:
                ClassLoader classLoader = this.f14596b;
                try {
                    return w.f14631a.f14511l.f14609i.N(x.a(classLoader, "unresolvedReference", new o(classLoader, 1))).f14511l.n(new com.typesafe.config.a());
                } catch (ExceptionInInitializerError e5) {
                    throw y.b(e5);
                }
            default:
                l6.l lVar = new l6.l(0, null, true, null, null);
                ClassLoader classLoader2 = this.f14596b;
                if (classLoader2 != null) {
                    lVar = new l6.l(0, null, true, null, classLoader2);
                }
                return f1.h("reference.conf", lVar).j().f14511l;
        }
    }
}
