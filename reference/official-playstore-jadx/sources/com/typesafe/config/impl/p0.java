package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
public final class p0 extends g implements n2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b2 f14607l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14608m;

    public p0(l6.k kVar, b2 b2Var, int i10) {
        super(kVar);
        this.f14607l = b2Var;
        this.f14608m = i10;
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new p0(w1Var, this.f14607l, this.f14608m);
    }

    @Override // com.typesafe.config.impl.g
    public final g E(g1 g1Var) {
        b2 b2Var = this.f14607l;
        g1 g1Var2 = b2Var.f14509a;
        g1Var2.getClass();
        h1 h1Var = new h1();
        h1Var.a(g1Var);
        h1Var.a(g1Var2);
        g1 g1VarB = h1Var.b();
        if (g1VarB != b2Var.f14509a) {
            b2Var = new b2(g1VarB, b2Var.f14510b);
        }
        return new p0(this.f14537i, b2Var, g1Var.b() + this.f14608m);
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        sb2.append(this.f14607l.toString());
    }

    @Override // com.typesafe.config.impl.g
    public final int J() {
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0122  */
    @Override // com.typesafe.config.impl.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.typesafe.config.impl.n1 K(com.typesafe.config.impl.m1 r18, com.typesafe.config.impl.o1 r19) {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.p0.K(com.typesafe.config.impl.m1, com.typesafe.config.impl.o1):com.typesafe.config.impl.n1");
    }

    @Override // l6.o
    public final Object a() {
        throw new ConfigException.NotResolved(null, "need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    @Override // l6.o
    public final int b() {
        throw new ConfigException.NotResolved(null, "need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (obj instanceof p0) {
            if (this.f14607l.equals(((p0) obj).f14607l)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        return this.f14607l.hashCode();
    }

    @Override // com.typesafe.config.impl.n2
    public final Collection p() {
        return Collections.singleton(this);
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof p0;
    }

    @Override // com.typesafe.config.impl.g
    public final boolean w() {
        return false;
    }
}
