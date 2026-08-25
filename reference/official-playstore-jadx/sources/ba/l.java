package ba;

import g8.b1;
import g8.e1;
import java.util.Collections;
import java.util.List;
import v9.b0;
import v9.g0;
import v9.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l f7294b = new l(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f7295c = new l(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7296a;

    public /* synthetic */ l(int i10) {
        this.f7296a = i10;
    }

    @Override // ba.e
    public final String a() {
        switch (this.f7296a) {
            case 0:
                return "second parameter must be of type KProperty<*> or its supertype";
            default:
                return "should not have varargs or parameters with default values";
        }
    }

    @Override // ba.e
    public final String b(r8.g gVar) {
        switch (this.f7296a) {
        }
        return t7.a.B(this, gVar);
    }

    @Override // ba.e
    public final boolean c(r8.g gVar) {
        b0 b0VarR;
        switch (this.f7296a) {
            case 0:
                e1 e1Var = (e1) gVar.e().get(1);
                a1.a aVar = d8.o.f14727d;
                int i10 = m9.d.f20302a;
                g8.b0 b0VarD = i9.f.d(e1Var);
                aVar.getClass();
                g8.f fVarD = g8.y.d(b0VarD, d8.p.Q);
                if (fVarD == null) {
                    b0VarR = null;
                } else {
                    k0.f22220l.getClass();
                    b0VarR = v9.e.r(Collections.singletonList(new g0((b1) kotlin.collections.x.R0(fVarD.g().getParameters()))), k0.f22221m, fVarD.g(), false);
                }
                if (b0VarR == null) {
                    return false;
                }
                return w9.c.f22506a.d(b0VarR, v9.b1.g(e1Var.getType(), false));
            default:
                List<e1> listE = gVar.e();
                if (!listE.isEmpty()) {
                    for (e1 e1Var2 : listE) {
                        if (m9.d.a(e1Var2) || e1Var2.q0() != null) {
                            return false;
                        }
                    }
                }
                return true;
        }
    }
}
