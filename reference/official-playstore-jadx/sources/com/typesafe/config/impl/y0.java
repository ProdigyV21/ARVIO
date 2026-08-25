package com.typesafe.config.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.net.MalformedURLException;

/* JADX INFO: loaded from: classes4.dex */
public final class y0 extends f1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final File f14647e;

    public y0(File file, l6.l lVar) {
        this.f14647e = file;
        l(lVar);
    }

    @Override // com.typesafe.config.impl.f1
    public final w1 e() {
        String externalForm;
        String path = this.f14647e.getPath();
        try {
            externalForm = new File(path).toURI().toURL().toExternalForm();
        } catch (MalformedURLException unused) {
            externalForm = null;
        }
        return new w1(path, -1, -1, 2, externalForm, null, null);
    }

    @Override // com.typesafe.config.impl.f1
    public final int g() {
        return y.e(this.f14647e.getName());
    }

    @Override // com.typesafe.config.impl.f1
    public final Reader o() {
        boolean zF = x.f();
        File file = this.f14647e;
        if (zF) {
            f1.r("Loading config from a file: " + file);
        }
        return f1.c(new FileInputStream(file));
    }

    @Override // com.typesafe.config.impl.f1
    public final l6.m q(String str) {
        File parentFile;
        File file = new File(str).isAbsolute() ? new File(str) : (new File(str).isAbsolute() || (parentFile = this.f14647e.getParentFile()) == null) ? null : new File(parentFile, str);
        if (file == null) {
            return null;
        }
        if (file.exists()) {
            f1.r(file + " exists, so loading it as a file");
            return new y0(file, this.f14533b.d(null));
        }
        f1.r(file + " does not exist, so trying it as a classpath resource");
        return super.q(str);
    }

    @Override // com.typesafe.config.impl.f1
    public final String toString() {
        return y0.class.getSimpleName() + "(" + this.f14647e.getPath() + ")";
    }
}
