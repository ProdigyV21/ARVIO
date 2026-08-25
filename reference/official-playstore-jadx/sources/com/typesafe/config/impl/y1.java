package com.typesafe.config.impl;

import java.io.File;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public final class y1 implements v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l6.c f14648a;

    public y1(l6.c cVar) {
        this.f14648a = cVar;
    }

    @Override // l6.e
    public final l6.j a(l6.b bVar, File file) {
        l6.c cVar = this.f14648a;
        if (cVar instanceof l6.e) {
            return ((l6.e) cVar).a(bVar, file);
        }
        l6.l lVarC = bVar.c();
        w1 w1Var = x.f14641a;
        return a2.f(new p(1), file.getPath(), lVarC).d().f14609i;
    }

    @Override // l6.f
    public final l6.j b(l6.b bVar, URL url) {
        l6.c cVar = this.f14648a;
        return cVar instanceof l6.f ? ((l6.f) cVar).b(bVar, url) : f1.i(url, bVar.c()).j().f14511l.f14609i;
    }

    @Override // l6.d
    public final l6.j d(l6.b bVar, String str) {
        l6.c cVar = this.f14648a;
        if (cVar instanceof l6.d) {
            return ((l6.d) cVar).d(bVar, str);
        }
        l6.l lVarC = bVar.c();
        w1 w1Var = x.f14641a;
        return a2.f(new p(0), str, lVarC).d().f14609i;
    }

    @Override // l6.c
    public final l6.j e(l6.b bVar, String str) {
        return this.f14648a.e(bVar, str);
    }

    @Override // l6.c
    public final l6.c c() {
        return this;
    }
}
