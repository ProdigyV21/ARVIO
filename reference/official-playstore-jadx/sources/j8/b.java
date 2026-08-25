package j8;

import io.ktor.http.ContentDisposition;
import java.util.Collections;
import java.util.List;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b extends b0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f9.f f19248i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u9.s f19249l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final u9.s f19250m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u9.s f19251n;

    public b(u9.v vVar, f9.f fVar) {
        if (vVar == null) {
            A0(0);
            throw null;
        }
        if (fVar == null) {
            A0(1);
            throw null;
        }
        this.f19248i = fVar;
        this.f19249l = vVar.d(new a(this, 0));
        this.f19250m = vVar.d(new a(this, 1));
        this.f19251n = vVar.d(new a(this, 2));
    }

    public static /* synthetic */ void A0(int i10) {
        String str = (i10 == 2 || i10 == 3 || i10 == 4 || i10 == 5 || i10 == 6 || i10 == 9 || i10 == 12 || i10 == 14 || i10 == 16 || i10 == 17 || i10 == 19 || i10 == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 2 || i10 == 3 || i10 == 4 || i10 == 5 || i10 == 6 || i10 == 9 || i10 == 12 || i10 == 14 || i10 == 16 || i10 == 17 || i10 == 19 || i10 == 20) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
                break;
            case 7:
            case 13:
                objArr[0] = "typeArguments";
                break;
            case 8:
            case 11:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 10:
            case 15:
                objArr[0] = "typeSubstitution";
                break;
            case 18:
                objArr[0] = "substitutor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i10 == 2) {
            objArr[1] = "getName";
        } else if (i10 == 3) {
            objArr[1] = "getOriginal";
        } else if (i10 == 4) {
            objArr[1] = "getUnsubstitutedInnerClassesScope";
        } else if (i10 == 5) {
            objArr[1] = "getThisAsReceiverParameter";
        } else if (i10 == 6) {
            objArr[1] = "getContextReceivers";
        } else if (i10 == 9 || i10 == 12 || i10 == 14 || i10 == 16) {
            objArr[1] = "getMemberScope";
        } else if (i10 == 17) {
            objArr[1] = "getUnsubstitutedMemberScope";
        } else if (i10 == 19) {
            objArr[1] = "substitute";
        } else if (i10 != 20) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
        } else {
            objArr[1] = "getDefaultType";
        }
        switch (i10) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
                break;
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
                objArr[2] = "getMemberScope";
                break;
            case 18:
                objArr[2] = "substitute";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 2 && i10 != 3 && i10 != 4 && i10 != 5 && i10 != 6 && i10 != 9 && i10 != 12 && i10 != 14 && i10 != 16 && i10 != 17 && i10 != 19 && i10 != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // g8.x0
    /* JADX INFO: renamed from: D0, reason: merged with bridge method [inline-methods] */
    public g8.f b(y0 y0Var) {
        if (y0Var != null) {
            return y0Var.f22263a.e() ? this : new a0(this, y0Var);
        }
        A0(18);
        throw null;
    }

    @Override // g8.f
    public final g8.s0 F0() {
        g8.s0 s0Var = (g8.s0) this.f19251n.invoke();
        if (s0Var != null) {
            return s0Var;
        }
        A0(5);
        throw null;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.l(this, obj);
    }

    @Override // g8.f
    public p9.n R() {
        p9.n nVar = (p9.n) this.f19250m.invoke();
        if (nVar != null) {
            return nVar;
        }
        A0(4);
        throw null;
    }

    @Override // g8.f
    public p9.n U() {
        m9.d.h(i9.f.d(this));
        p9.n nVarG0 = g0(w9.e.f22508a);
        if (nVarG0 != null) {
            return nVarG0;
        }
        A0(17);
        throw null;
    }

    @Override // g8.f
    public List W() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        A0(6);
        throw null;
    }

    @Override // j8.b0, g8.f, g8.k
    public final g8.h a() {
        return this;
    }

    @Override // g8.d0
    public final f9.f getName() {
        f9.f fVar = this.f19248i;
        if (fVar != null) {
            return fVar;
        }
        A0(2);
        throw null;
    }

    @Override // g8.f, g8.h
    public final v9.b0 m() {
        v9.b0 b0Var = (v9.b0) this.f19249l.invoke();
        if (b0Var != null) {
            return b0Var;
        }
        A0(20);
        throw null;
    }

    @Override // j8.b0
    public p9.n v(v9.v0 v0Var, w9.e eVar) {
        if (!v0Var.e()) {
            return new p9.s(g0(eVar), new y0(v0Var));
        }
        p9.n nVarG0 = g0(eVar);
        if (nVarG0 != null) {
            return nVarG0;
        }
        A0(12);
        throw null;
    }

    @Override // g8.f
    public final p9.n x(v9.v0 v0Var) {
        m9.d.h(i9.f.d(this));
        p9.n nVarV = v(v0Var, w9.e.f22508a);
        if (nVarV != null) {
            return nVarV;
        }
        A0(16);
        throw null;
    }

    @Override // j8.b0, g8.k
    public final g8.k a() {
        return this;
    }

    @Override // j8.b0, g8.f, g8.k
    public final g8.f a() {
        return this;
    }
}
