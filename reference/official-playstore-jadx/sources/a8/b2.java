package a8;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h9.h f169a = h9.h.f15907c;

    public static void a(g8.c cVar, StringBuilder sb2) {
        f9.c cVar2 = e2.f188a;
        g8.s0 s0VarF0 = cVar.I() != null ? ((g8.f) cVar.d()).F0() : null;
        g8.s0 s0VarM = cVar.M();
        h9.h hVar = f169a;
        if (s0VarF0 != null) {
            sb2.append(hVar.V(s0VarF0.getType()));
            sb2.append(".");
        }
        boolean z = (s0VarF0 == null || s0VarM == null) ? false : true;
        if (z) {
            sb2.append("(");
        }
        if (s0VarM != null) {
            sb2.append(hVar.V(s0VarM.getType()));
            sb2.append(".");
        }
        if (z) {
            sb2.append(")");
        }
    }

    public static String b(g8.w wVar) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("fun ");
        a(wVar, sb2);
        f9.f name = wVar.getName();
        h9.h hVar = f169a;
        sb2.append(hVar.N(name, true));
        kotlin.collections.x.t0(wVar.e(), sb2, ", ", "(", ")", b.f158u, 48);
        sb2.append(": ");
        sb2.append(hVar.V(wVar.getReturnType()));
        return sb2.toString();
    }

    public static String c(g8.p0 p0Var) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(p0Var.K() ? "var " : "val ");
        a(p0Var, sb2);
        f9.f name = p0Var.getName();
        h9.h hVar = f169a;
        sb2.append(hVar.N(name, true));
        sb2.append(": ");
        sb2.append(hVar.V(p0Var.getType()));
        return sb2.toString();
    }
}
