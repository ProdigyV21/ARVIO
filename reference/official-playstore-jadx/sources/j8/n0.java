package j8;

import g8.e1;
import io.ktor.http.LinkHeader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 extends j0 implements g8.r0 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public e1 f19327w;
    public final g8.r0 x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(g8.p0 p0Var, h8.h hVar, int i10, g8.p pVar, boolean z, boolean z5, boolean z10, int i11, g8.r0 r0Var, g8.v0 v0Var) {
        super(i10, pVar, p0Var, hVar, f9.f.l("<set-" + p0Var.getName() + ">"), z, z5, z10, i11, v0Var);
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
        this.x = r0Var != null ? r0Var : this;
    }

    public static v0 H0(n0 n0Var, v9.w wVar, h8.h hVar) {
        if (wVar == null) {
            v(8);
            throw null;
        }
        if (hVar != null) {
            return new v0(n0Var, null, 0, hVar, f9.h.f15378g, wVar, false, false, false, null, g8.v0.f15554b);
        }
        v(9);
        throw null;
    }

    public static /* synthetic */ void v(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 10:
            case 11:
            case 12:
            case 13:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 10:
            case 11:
            case 12:
            case 13:
                i11 = 2;
                break;
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
            case 9:
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
                objArr[0] = "parameter";
                break;
            case 7:
                objArr[0] = "setterDescriptor";
                break;
            case 8:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
            default:
                objArr[0] = "correspondingProperty";
                break;
        }
        switch (i10) {
            case 10:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 11:
                objArr[1] = "getValueParameters";
                break;
            case 12:
                objArr[1] = "getReturnType";
                break;
            case 13:
                objArr[1] = "getOriginal";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
        }
        switch (i10) {
            case 6:
                objArr[2] = "initialize";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "createSetterParameter";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 10:
            case 11:
            case 12:
            case 13:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // j8.p, j8.o, g8.k
    /* JADX INFO: renamed from: I0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final g8.r0 a() {
        g8.r0 r0Var = this.x;
        if (r0Var != null) {
            return r0Var;
        }
        v(13);
        throw null;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.o(this, obj);
    }

    @Override // g8.a
    public final List e() {
        e1 e1Var = this.f19327w;
        if (e1Var == null) {
            throw new IllegalStateException();
        }
        List listSingletonList = Collections.singletonList(e1Var);
        if (listSingletonList != null) {
            return listSingletonList;
        }
        v(11);
        throw null;
    }

    @Override // g8.a
    public final v9.w getReturnType() {
        v9.b0 b0VarW = m9.d.e(this).w();
        if (b0VarW != null) {
            return b0VarW;
        }
        v(12);
        throw null;
    }

    @Override // g8.c, g8.a
    public final Collection k() {
        return G0(false);
    }
}
