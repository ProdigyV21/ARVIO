package com.typesafe.config.impl;

import java.io.FileNotFoundException;
import java.io.Reader;

/* JADX INFO: loaded from: classes4.dex */
public final class z0 extends f1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14650e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f14651f;

    public z0(String str, String str2, l6.l lVar) {
        this.f14650e = str;
        this.f14651f = str2;
        l(lVar);
    }

    @Override // com.typesafe.config.impl.f1
    public final w1 e() {
        return w1.g(this.f14650e);
    }

    @Override // com.typesafe.config.impl.f1
    public final Reader o() throws FileNotFoundException {
        throw new FileNotFoundException(this.f14651f);
    }
}
