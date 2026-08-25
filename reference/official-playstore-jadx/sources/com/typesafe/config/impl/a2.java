package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class a2 implements v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l6.c f14506a;

    public a2(l6.c cVar) {
        this.f14506a = cVar;
    }

    public static l6.j f(x1 x1Var, String str, l6.l lVar) {
        boolean z;
        if (str.endsWith(".conf") || str.endsWith(".json") || str.endsWith(".properties")) {
            l6.m mVarA = x1Var.a(str, lVar);
            return mVarA.b(mVarA.a().b(lVar.f20039b));
        }
        l6.m mVarA2 = x1Var.a(str.concat(".conf"), lVar);
        l6.m mVarA3 = x1Var.a(str.concat(".json"), lVar);
        l6.m mVarA4 = x1Var.a(str.concat(".properties"), lVar);
        ArrayList arrayList = new ArrayList();
        int i10 = lVar.f20038a;
        l6.j jVarD0 = v1.d0(w1.g(str));
        boolean z5 = true;
        if (i10 == 0 || i10 == 2) {
            try {
                jVarD0 = mVarA2.b(mVarA2.a().b(false).e(2));
                z = true;
            } catch (ConfigException.IO e5) {
                arrayList.add(e5);
                z = false;
            }
        } else {
            z = false;
        }
        if (i10 == 0 || i10 == 1) {
            try {
                jVarD0 = jVarD0.f(mVarA3.b(mVarA3.a().b(false).e(1)));
                z = true;
            } catch (ConfigException.IO e6) {
                arrayList.add(e6);
            }
        }
        if (i10 == 0 || i10 == 3) {
            try {
                jVarD0 = jVarD0.f(mVarA4.b(mVarA4.a().b(false).e(3)));
            } catch (ConfigException.IO e10) {
                arrayList.add(e10);
                z5 = z;
            }
        } else {
            z5 = z;
        }
        if (lVar.f20039b || z5) {
            if (!z5 && x.f()) {
                x.e("Did not find '" + str + "' with any extension (.conf, .json, .properties); but '" + str + "' is allowed to be missing. Exceptions from load attempts should have been logged above.");
            }
            return jVarD0;
        }
        if (x.f()) {
            x.e("Did not find '" + str + "' with any extension (.conf, .json, .properties); exceptions should have been logged above.");
        }
        if (arrayList.isEmpty()) {
            throw new ConfigException.BugOrBroken(null, "should not be reached: nothing found but no exceptions thrown");
        }
        StringBuilder sb2 = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb2.append(((Throwable) it.next()).getMessage());
            sb2.append(", ");
        }
        sb2.setLength(sb2.length() - 2);
        throw new ConfigException.IO(w1.g(str), sb2.toString(), (Throwable) arrayList.get(0));
    }

    @Override // l6.e
    public final l6.j a(l6.b bVar, File file) {
        l6.l lVarC = bVar.c();
        w1 w1Var = x.f14641a;
        c cVar = f(new p(1), file.getPath(), lVarC).d().f14609i;
        l6.c cVar2 = this.f14506a;
        return (cVar2 == null || !(cVar2 instanceof l6.e)) ? cVar : cVar.f(((l6.e) cVar2).a(bVar, file));
    }

    @Override // l6.f
    public final l6.j b(l6.b bVar, URL url) {
        c cVar = f1.i(url, bVar.c()).j().f14511l.f14609i;
        l6.c cVar2 = this.f14506a;
        return (cVar2 == null || !(cVar2 instanceof l6.f)) ? cVar : cVar.f(((l6.f) cVar2).b(bVar, url));
    }

    @Override // l6.c
    public final l6.c c() {
        a2 a2Var = r.f14614a;
        if (this == a2Var) {
            throw new ConfigException.BugOrBroken(null, "trying to create includer cycle");
        }
        l6.c cVar = this.f14506a;
        return cVar == a2Var ? this : cVar != null ? new a2(cVar.c()) : new a2(a2Var);
    }

    @Override // l6.d
    public final l6.j d(l6.b bVar, String str) {
        l6.l lVarC = bVar.c();
        w1 w1Var = x.f14641a;
        c cVar = f(new p(0), str, lVarC).d().f14609i;
        l6.c cVar2 = this.f14506a;
        return (cVar2 == null || !(cVar2 instanceof l6.d)) ? cVar : cVar.f(((l6.d) cVar2).d(bVar, str));
    }

    @Override // l6.c
    public final l6.j e(l6.b bVar, String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException unused) {
            url = null;
        }
        l6.j jVarF = url != null ? f1.i(url, bVar.c()).j().f14511l.f14609i : f(new z1(bVar), str, bVar.c());
        l6.c cVar = this.f14506a;
        return cVar != null ? jVarF.f(cVar.e(bVar, str)) : jVarF;
    }
}
