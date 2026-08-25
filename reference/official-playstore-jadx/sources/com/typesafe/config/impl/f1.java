package com.typesafe.config.impl;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.typesafe.config.ConfigException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;

/* JADX INFO: loaded from: classes4.dex */
public abstract class f1 implements l6.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.android.gms.common.api.internal.f1 f14531d = new com.google.android.gms.common.api.internal.f1(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f14532a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l6.l f14533b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public l6.k f14534c;

    public static BufferedReader c(InputStream inputStream) {
        try {
            return new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e5) {
            throw new ConfigException.BugOrBroken(e5, "Java runtime does not support UTF-8");
        }
    }

    public static c1 h(String str, l6.l lVar) {
        ClassLoader contextClassLoader = (ClassLoader) lVar.f20042e;
        if (contextClassLoader == null) {
            contextClassLoader = Thread.currentThread().getContextClassLoader();
        }
        if (contextClassLoader != null) {
            return new c1(str, lVar);
        }
        throw new ConfigException.BugOrBroken(null, "null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()");
    }

    public static f1 i(URL url, l6.l lVar) {
        File file;
        if (!url.getProtocol().equals("file")) {
            d1 d1Var = new d1(url);
            d1Var.l(lVar);
            return d1Var;
        }
        try {
            file = new File(url.toURI());
        } catch (IllegalArgumentException unused) {
            file = new File(url.getPath());
        } catch (URISyntaxException unused2) {
            file = new File(url.getPath());
        }
        return new y0(file, lVar);
    }

    public static void r(String str) {
        if (x.f()) {
            x.e(str);
        }
    }

    @Override // l6.m
    public final l6.l a() {
        return this.f14533b;
    }

    @Override // l6.m
    public final c b(l6.l lVar) {
        com.google.android.gms.common.api.internal.f1 f1Var = f14531d;
        LinkedList linkedList = (LinkedList) f1Var.get();
        if (linkedList.size() >= 50) {
            throw new ConfigException.Parse(this.f14534c, "include statements nested more than 50 times, you probably have a cycle in your includes. Trace: " + linkedList, null);
        }
        linkedList.addFirst(this);
        try {
            g gVarK = k(lVar);
            if (gVarK instanceof c) {
                return (c) gVarK;
            }
            throw new ConfigException.WrongType(gVarK.f14537i, "", "object at file root", g8.b.q(gVarK.b()));
        } finally {
            linkedList.removeFirst();
            if (linkedList.isEmpty()) {
                f1Var.remove();
            }
        }
    }

    public int d() {
        return 0;
    }

    public abstract w1 e();

    public final l6.l f(l6.l lVar) {
        int iG = lVar.f20038a;
        if (iG == 0) {
            iG = g();
        }
        if (iG == 0) {
            iG = 2;
        }
        l6.l lVarE = lVar.e(iG);
        w1 w1Var = x.f14641a;
        l6.c cVar = (l6.c) lVarE.f20041d;
        a2 a2Var = r.f14614a;
        if (cVar != a2Var) {
            lVarE = cVar != null ? lVarE.c(cVar.c()) : lVarE.c(a2Var);
        }
        l6.c cVar2 = (l6.c) lVarE.f20041d;
        return lVarE.c(cVar2 instanceof v0 ? (v0) cVar2 : new y1(cVar2));
    }

    public int g() {
        return 0;
    }

    public final c j() {
        g gVarK = k(this.f14533b);
        if (gVarK instanceof c) {
            return (c) gVarK;
        }
        throw new ConfigException.WrongType(gVarK.f14537i, "", "object at file root", g8.b.q(gVarK.b()));
    }

    public final g k(l6.l lVar) {
        l6.l lVarF = f(lVar);
        String str = (String) lVarF.f20040c;
        l6.k kVarG = str != null ? w1.g(str) : this.f14534c;
        try {
            return n(kVarG, lVarF);
        } catch (IOException e5) {
            if (lVarF.f20039b) {
                r(e5.getMessage() + ". Allowing Missing File, this can be turned off by setting ConfigParseOptions.allowMissing = false");
                return new v1(w1.g(kVarG.description() + " (not found)"), Collections.EMPTY_MAP);
            }
            r("exception loading " + kVarG.description() + ": " + e5.getClass().getName() + ": " + e5.getMessage());
            throw new ConfigException.IO(kVarG, e5.getClass().getName() + ": " + e5.getMessage(), e5);
        }
    }

    public final void l(l6.l lVar) {
        this.f14533b = f(lVar);
        this.f14532a = new io.sentry.internal.debugmeta.c(this);
        String str = (String) this.f14533b.f20040c;
        if (str != null) {
            this.f14534c = w1.g(str);
        } else {
            this.f14534c = e();
        }
    }

    public final g m(Reader reader, l6.k kVar, l6.l lVar) throws IOException {
        b bVarK;
        boolean z;
        int i10 = lVar.f20038a;
        if (i10 == 3) {
            Properties properties = new Properties();
            properties.load(reader);
            return k1.a(kVar, properties.entrySet());
        }
        e2 e2Var = new e2(kVar, reader, i10 != 1);
        if (i10 == 0) {
            i10 = 2;
        }
        m mVar = new m(i10, kVar, e2Var);
        ArrayList arrayList = new ArrayList();
        c2 c2VarF = mVar.f();
        g gVarB = null;
        if (c2VarF != m2.f14581a) {
            throw new ConfigException.BugOrBroken(null, "token stream did not begin with START, had " + c2VarF);
        }
        c2 c2VarG = mVar.g(arrayList);
        if (c2VarG == m2.f14586f || c2VarG == m2.f14588h) {
            bVarK = mVar.k(c2VarG);
            z = false;
        } else {
            if (mVar.f14573d == 1) {
                if (c2VarG == m2.f14582b) {
                    throw mVar.h("Empty document");
                }
                throw mVar.h("Document must have an object or array at root, unexpected token: " + c2VarG);
            }
            mVar.l(c2VarG);
            bVarK = mVar.j(false);
            z = true;
        }
        if ((bVarK instanceof h0) && z) {
            arrayList.addAll(((d0) bVarK).f14517a);
        } else {
            arrayList.add(bVarK);
        }
        c2 c2VarG2 = mVar.g(arrayList);
        if (c2VarG2 != m2.f14582b) {
            throw mVar.h("Document has trailing tokens after first object or array: " + c2VarG2);
        }
        l6.k kVar2 = mVar.f14574e;
        j0 j0Var = z ? new j0(Collections.singletonList(new h0(arrayList)), kVar2) : new j0(arrayList, kVar2);
        io.sentry.internal.debugmeta.c cVar = this.f14532a;
        int i11 = lVar.f20038a;
        l6.c cVar2 = (l6.c) lVar.f20041d;
        o0 o0Var = new o0(i11, kVar, j0Var, cVar2 instanceof v0 ? (v0) cVar2 : new y1(cVar2), cVar);
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            boolean z5 = false;
            for (a aVar : j0Var.f14517a) {
                if (aVar instanceof c0) {
                    arrayList2.add(((c0) aVar).c());
                } else if (aVar instanceof l0) {
                    c2 c2Var = ((l0) aVar).f14568a;
                    c2 c2Var2 = m2.f14581a;
                    if (c2Var instanceof h2) {
                        o0Var.f14597a++;
                        if (z5 && gVarB == null) {
                            arrayList2.clear();
                        } else if (gVarB != null) {
                            g gVarQ = gVarB.Q(gVarB.f14537i.c(new ArrayList(arrayList2)));
                            arrayList2.clear();
                            return gVarQ;
                        }
                        z5 = true;
                    } else {
                        continue;
                    }
                } else if (aVar instanceof d0) {
                    gVarB = o0Var.b((d0) aVar, arrayList2);
                }
            }
            return gVarB;
        }
    }

    public g n(l6.k kVar, l6.l lVar) throws IOException {
        int i10 = lVar.f20038a;
        Reader readerP = p(lVar);
        int iD = d();
        if (iD != 0) {
            if (x.f() && i10 != 0) {
                r("Overriding syntax " + g8.b.s(i10) + " with Content-Type which specified " + g8.b.s(iD));
            }
            lVar = lVar.e(iD);
        }
        try {
            return m(readerP, kVar, lVar);
        } finally {
            readerP.close();
        }
    }

    public abstract Reader o();

    public Reader p(l6.l lVar) {
        return o();
    }

    public l6.m q(String str) {
        if (str.startsWith(DomExceptionUtils.SEPARATOR)) {
            str = str.substring(1);
        }
        return h(str, this.f14533b.d(null));
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
