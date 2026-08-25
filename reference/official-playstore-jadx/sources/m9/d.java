package m9;

import d8.k;
import ea.o;
import f9.e;
import f9.f;
import g8.b0;
import g8.e1;
import g8.h;
import g8.h0;
import g8.i;
import g8.o0;
import java.util.Collections;
import kotlin.jvm.internal.k0;
import r7.l;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f20302a = 0;

    static {
        f.j("value");
    }

    public static final boolean a(e1 e1Var) {
        return o.h(Collections.singletonList(e1Var), a.f20298l, b.f20300i).booleanValue();
    }

    public static g8.c b(g8.c cVar, l lVar) {
        return (g8.c) o.f(Collections.singletonList(cVar), new a(1), new ea.a(lVar, new k0()));
    }

    public static final f9.c c(g8.l lVar) {
        e eVarG = i9.f.g(lVar);
        if (!eVarG.d()) {
            eVarG = null;
        }
        if (eVarG != null) {
            return eVarG.g();
        }
        return null;
    }

    public static final g8.f d(h8.b bVar) {
        h hVarE = bVar.getType().I0().e();
        if (hVarE instanceof g8.f) {
            return (g8.f) hVarE;
        }
        return null;
    }

    public static final k e(g8.k kVar) {
        return i9.f.d(kVar).j();
    }

    public static final f9.b f(h hVar) {
        g8.k kVarD;
        f9.b bVarF;
        if (hVar == null || (kVarD = hVar.d()) == null) {
            return null;
        }
        if (kVarD instanceof h0) {
            return new f9.b(((h0) kVarD).c(), hVar.getName());
        }
        if (!(kVarD instanceof i) || (bVarF = f((h) kVarD)) == null) {
            return null;
        }
        return bVarF.d(hVar.getName());
    }

    public static final f9.c g(g8.k kVar) {
        f9.c cVarH = i9.f.h(kVar);
        return cVarH != null ? cVarH : i9.f.g(kVar.d()).b(kVar.getName()).g();
    }

    public static final void h(b0 b0Var) {
        if (b0Var.X(w9.f.f22509a) != null) {
            throw new ClassCastException();
        }
    }

    public static final g8.c i(g8.c cVar) {
        return cVar instanceof o0 ? ((o0) cVar).T() : cVar;
    }
}
