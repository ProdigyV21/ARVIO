package i9;

import g8.a0;
import g8.b1;
import g8.h0;
import g8.v0;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f16248a = new d();

    public static v0 c(g8.a aVar) {
        while (aVar instanceof g8.c) {
            g8.c cVar = (g8.c) aVar;
            if (cVar.getKind() != 2) {
                break;
            }
            aVar = (g8.c) x.S0(cVar.k());
            if (aVar == null) {
                return null;
            }
        }
        return aVar.f();
    }

    public final boolean a(g8.k kVar, g8.k kVar2, boolean z) {
        if ((kVar instanceof g8.f) && (kVar2 instanceof g8.f)) {
            return kotlin.jvm.internal.p.a(((g8.f) kVar).g(), ((g8.f) kVar2).g());
        }
        if ((kVar instanceof b1) && (kVar2 instanceof b1)) {
            return b((b1) kVar, (b1) kVar2, z, c.f16247i);
        }
        if (!(kVar instanceof g8.a) || !(kVar2 instanceof g8.a)) {
            return ((kVar instanceof h0) && (kVar2 instanceof h0)) ? kotlin.jvm.internal.p.a(((h0) kVar).c(), ((h0) kVar2).c()) : kotlin.jvm.internal.p.a(kVar, kVar2);
        }
        g8.a aVar = (g8.a) kVar;
        g8.a aVar2 = (g8.a) kVar2;
        if (!aVar.equals(aVar2)) {
            if (kotlin.jvm.internal.p.a(aVar.getName(), aVar2.getName()) && ((!(aVar instanceof a0) || !(aVar2 instanceof a0) || ((a0) aVar).i0() == ((a0) aVar2).i0()) && ((!kotlin.jvm.internal.p.a(aVar.d(), aVar2.d()) || (z && kotlin.jvm.internal.p.a(c(aVar), c(aVar2)))) && !f.o(aVar) && !f.o(aVar2)))) {
                g8.k kVarD = aVar.d();
                g8.k kVarD2 = aVar2.d();
                if (((kVarD instanceof g8.c) || (kVarD2 instanceof g8.c)) ? false : a(kVarD, kVarD2, z)) {
                    n nVar = new n(new androidx.loader.app.d(aVar, aVar2, z));
                    if (nVar.m(aVar, aVar2, null, true).c() != 1 || nVar.m(aVar2, aVar, null, true).c() != 1) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final boolean b(b1 b1Var, b1 b1Var2, boolean z, r7.p pVar) {
        if (b1Var.equals(b1Var2)) {
            return true;
        }
        if (kotlin.jvm.internal.p.a(b1Var.d(), b1Var2.d())) {
            return false;
        }
        g8.k kVarD = b1Var.d();
        g8.k kVarD2 = b1Var2.d();
        return (((kVarD instanceof g8.c) || (kVarD2 instanceof g8.c)) ? ((Boolean) pVar.invoke(kVarD, kVarD2)).booleanValue() : a(kVarD, kVarD2, z)) && b1Var.getIndex() == b1Var2.getIndex();
    }
}
