package j8;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends j0 implements g8.q0 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public v9.w f19324w;
    public final g8.q0 x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(g8.p0 p0Var, h8.h hVar, int i10, g8.p pVar, boolean z, boolean z5, boolean z10, int i11, g8.q0 q0Var, g8.v0 v0Var) {
        super(i10, pVar, p0Var, hVar, f9.f.l("<get-" + p0Var.getName() + ">"), z, z5, z10, i11, v0Var);
        if (hVar == null) {
            v(1);
            throw null;
        }
        if (i10 == 0) {
            v(2);
            throw null;
        }
        if (pVar == null) {
            v(3);
            throw null;
        }
        if (i11 == 0) {
            v(4);
            throw null;
        }
        if (v0Var == null) {
            v(5);
            throw null;
        }
        this.x = q0Var != null ? q0Var : this;
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 6 || i10 == 7 || i10 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 6 || i10 == 7 || i10 == 8) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "modality";
                break;
            case 3:
                objArr[0] = "visibility";
                break;
            case 4:
                objArr[0] = "kind";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
                break;
            default:
                objArr[0] = "correspondingProperty";
                break;
        }
        if (i10 == 6) {
            objArr[1] = "getOverriddenDescriptors";
        } else if (i10 == 7) {
            objArr[1] = "getValueParameters";
        } else if (i10 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
        } else {
            objArr[1] = "getOriginal";
        }
        if (i10 != 6 && i10 != 7 && i10 != 8) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 6 && i10 != 7 && i10 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // j8.p, j8.o, g8.k
    /* JADX INFO: renamed from: H0, reason: merged with bridge method [inline-methods] */
    public final g8.q0 a() {
        g8.q0 q0Var = this.x;
        if (q0Var != null) {
            return q0Var;
        }
        v(8);
        throw null;
    }

    public final void I0(v9.w wVar) {
        if (wVar == null) {
            wVar = T().getType();
        }
        this.f19324w = wVar;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.p(this, obj);
    }

    @Override // g8.a
    public final List e() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        v(7);
        throw null;
    }

    @Override // g8.a
    public final v9.w getReturnType() {
        return this.f19324w;
    }

    @Override // g8.c, g8.a
    public final Collection k() {
        return G0(true);
    }
}
