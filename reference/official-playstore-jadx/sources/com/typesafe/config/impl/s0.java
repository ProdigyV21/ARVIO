package com.typesafe.config.impl;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class s0 extends g implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f14617l;

    public s0(l6.k kVar, String str) {
        super(kVar);
        this.f14617l = str;
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        sb2.append(y.d(this.f14617l));
    }

    @Override // com.typesafe.config.impl.g
    public final String M() {
        return this.f14617l;
    }

    @Override // l6.o
    public final int b() {
        return 6;
    }
}
