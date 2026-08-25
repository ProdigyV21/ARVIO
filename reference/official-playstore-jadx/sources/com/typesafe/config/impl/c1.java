package com.typesafe.config.impl;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.typesafe.config.ConfigException;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes4.dex */
public final class c1 extends f1 implements e1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14512e;

    public c1(String str, l6.l lVar) {
        this.f14512e = str;
        l(lVar);
    }

    @Override // com.typesafe.config.impl.f1
    public final w1 e() {
        return w1.f(null, this.f14512e);
    }

    @Override // com.typesafe.config.impl.f1
    public final int g() {
        return y.e(this.f14512e);
    }

    @Override // com.typesafe.config.impl.f1
    public final g n(l6.k kVar, l6.l lVar) throws IOException {
        ClassLoader contextClassLoader = (ClassLoader) lVar.f20042e;
        if (contextClassLoader == null) {
            contextClassLoader = Thread.currentThread().getContextClassLoader();
        }
        if (contextClassLoader == null) {
            throw new ConfigException.BugOrBroken(null, "null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()");
        }
        String str = this.f14512e;
        Enumeration<URL> resources = contextClassLoader.getResources(str);
        if (!resources.hasMoreElements()) {
            if (x.f()) {
                f1.r("Loading config from class loader " + contextClassLoader + " but there were no resources called " + str);
            }
            throw new IOException(androidx.compose.material3.d.C("resource not found on classpath: ", str));
        }
        c cVarD0 = v1.d0(kVar);
        while (resources.hasMoreElements()) {
            URL urlNextElement = resources.nextElement();
            if (x.f()) {
                StringBuilder sbT = a0.c.t("Loading config from resource '", str, "' URL ");
                sbT.append(urlNextElement.toExternalForm());
                sbT.append(" from class loader ");
                sbT.append(contextClassLoader);
                f1.r(sbT.toString());
            }
            b1 b1Var = new b1(urlNextElement, lVar, str, this);
            cVarD0 = cVarD0.N(b1Var.k(b1Var.f14533b));
        }
        return cVarD0;
    }

    @Override // com.typesafe.config.impl.f1
    public final Reader o() {
        throw new ConfigException.BugOrBroken(null, "reader() should not be called on resources");
    }

    @Override // com.typesafe.config.impl.f1
    public final l6.m q(String str) {
        if (str.startsWith(DomExceptionUtils.SEPARATOR)) {
            return f1.h(str.substring(1), this.f14533b.d(null));
        }
        String str2 = this.f14512e;
        int iLastIndexOf = str2.lastIndexOf(47);
        String strSubstring = iLastIndexOf < 0 ? null : str2.substring(0, iLastIndexOf);
        return strSubstring == null ? f1.h(str, this.f14533b.d(null)) : f1.h(androidx.compose.foundation.c.t(strSubstring, DomExceptionUtils.SEPARATOR, str), this.f14533b.d(null));
    }

    @Override // com.typesafe.config.impl.f1
    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(c1.class.getSimpleName());
        sb2.append("(");
        return a0.c.p(sb2, this.f14512e, ")");
    }
}
