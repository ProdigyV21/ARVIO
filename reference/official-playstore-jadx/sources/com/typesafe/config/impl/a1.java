package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.Reader;
import java.util.Properties;

/* JADX INFO: loaded from: classes4.dex */
public final class a1 extends f1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Properties f14505e;

    public a1(Properties properties, l6.l lVar) {
        this.f14505e = properties;
        l(lVar);
    }

    @Override // com.typesafe.config.impl.f1
    public final w1 e() {
        return w1.g("properties");
    }

    @Override // com.typesafe.config.impl.f1
    public final int g() {
        return 3;
    }

    @Override // com.typesafe.config.impl.f1
    public final g n(l6.k kVar, l6.l lVar) {
        boolean zF = x.f();
        Properties properties = this.f14505e;
        if (zF) {
            f1.r("Loading config from properties " + properties);
        }
        return k1.a(kVar, properties.entrySet());
    }

    @Override // com.typesafe.config.impl.f1
    public final Reader o() {
        throw new ConfigException.BugOrBroken(null, "reader() should not be called on props");
    }

    @Override // com.typesafe.config.impl.f1
    public final String toString() {
        return a1.class.getSimpleName() + "(" + this.f14505e.size() + " props)";
    }
}
