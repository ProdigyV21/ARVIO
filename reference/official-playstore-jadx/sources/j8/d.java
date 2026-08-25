package j8;

import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d extends o implements g8.s0 {
    public static /* synthetic */ void v(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                i11 = 2;
                break;
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 2:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
                objArr[0] = "substitutor";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractReceiverParameterDescriptor";
                break;
            default:
                objArr[0] = "annotations";
                break;
        }
        switch (i10) {
            case 4:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 5:
                objArr[1] = "getTypeParameters";
                break;
            case 6:
                objArr[1] = "getType";
                break;
            case 7:
                objArr[1] = "getValueParameters";
                break;
            case 8:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 9:
                objArr[1] = "getVisibility";
                break;
            case 10:
                objArr[1] = "getOriginal";
                break;
            case 11:
                objArr[1] = "getSource";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractReceiverParameterDescriptor";
                break;
        }
        switch (i10) {
            case 3:
                objArr[2] = "substitute";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // g8.a
    public final g8.s0 I() {
        return null;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.h(this, obj);
    }

    @Override // g8.a
    public final g8.s0 M() {
        return null;
    }

    @Override // j8.o, g8.k
    public final g8.a a() {
        return this;
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

    @Override // g8.n
    public final g8.v0 f() {
        return g8.v0.f15554b;
    }

    @Override // g8.a
    public final boolean f0() {
        return false;
    }

    @Override // g8.a
    public final v9.w getReturnType() {
        return getType();
    }

    @Override // g8.d1
    public final v9.w getType() {
        v9.w type = getValue().getType();
        if (type != null) {
            return type;
        }
        v(6);
        throw null;
    }

    @Override // g8.a
    public final List getTypeParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        v(5);
        throw null;
    }

    @Override // g8.o, g8.a0
    public final g8.p getVisibility() {
        return g8.q.f15529f;
    }

    @Override // g8.a
    public final Collection k() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        v(8);
        throw null;
    }

    @Override // j8.o, g8.k
    public final g8.k a() {
        return this;
    }

    @Override // g8.x0
    public final d b(y0 y0Var) {
        if (y0Var == null) {
            v(3);
            throw null;
        }
        if (!y0Var.f22263a.e()) {
            v9.w wVarI = d() instanceof g8.f ? y0Var.i(3, getType()) : y0Var.i(1, getType());
            if (wVarI == null) {
                return null;
            }
            if (wVarI != getType()) {
                return new x(d(), new q9.i(wVarI, null), getAnnotations());
            }
        }
        return this;
    }
}
