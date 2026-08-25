package l8;

import m8.o;
import m8.z;
import p8.m;
import p8.n;
import y8.x;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements n, x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClassLoader f20062a;

    public /* synthetic */ c(ClassLoader classLoader) {
        this.f20062a = classLoader;
    }

    @Override // p8.n
    public o a(m mVar) {
        Class<?> cls;
        f9.b bVar = mVar.f21169a;
        f9.c cVarG = bVar.g();
        String strReplace = bVar.h().b().replace('.', '$');
        if (!cVarG.d()) {
            strReplace = cVarG.b() + '.' + strReplace;
        }
        try {
            cls = Class.forName(strReplace, false, this.f20062a);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            return new o(cls);
        }
        return null;
    }

    @Override // y8.x
    public kb.d b(f9.b bVar) {
        Class<?> cls;
        e eVarA;
        String strReplace = bVar.h().b().replace('.', '$');
        if (!bVar.g().d()) {
            strReplace = bVar.g() + '.' + strReplace;
        }
        try {
            cls = Class.forName(strReplace, false, this.f20062a);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null || (eVarA = d.a(cls)) == null) {
            return null;
        }
        return new kb.d(eVarA, 22);
    }

    @Override // y8.x
    public kb.d c(w8.g gVar) {
        Class<?> cls;
        e eVarA;
        try {
            cls = Class.forName(gVar.c().b(), false, this.f20062a);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null || (eVarA = d.a(cls)) == null) {
            return null;
        }
        return new kb.d(eVarA, 22);
    }

    @Override // p8.n
    public z d(f9.c cVar) {
        return new z(cVar);
    }
}
