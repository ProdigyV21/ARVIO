package com.typesafe.config.impl;

import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public final class b1 extends d1 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c1 f14507g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f14508h;

    public b1(URL url, l6.l lVar, String str, c1 c1Var) {
        super(url);
        this.f14507g = c1Var;
        this.f14508h = str;
        l(lVar);
    }

    @Override // com.typesafe.config.impl.d1, com.typesafe.config.impl.f1
    public final w1 e() {
        return w1.f(this.f14518e, this.f14508h);
    }

    @Override // com.typesafe.config.impl.d1, com.typesafe.config.impl.f1
    public final l6.m q(String str) {
        return this.f14507g.q(str);
    }
}
