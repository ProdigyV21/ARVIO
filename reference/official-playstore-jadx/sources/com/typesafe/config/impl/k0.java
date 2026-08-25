package com.typesafe.config.impl;

import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
public final class k0 extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c2 f14565a;

    public k0(c2 c2Var) {
        this.f14565a = c2Var;
    }

    @Override // com.typesafe.config.impl.a
    public final Collection b() {
        return Collections.singletonList(this.f14565a);
    }
}
