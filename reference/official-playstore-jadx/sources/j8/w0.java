package j8;

import g8.g1;
import io.ktor.http.ContentDisposition;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w0 extends p implements g1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public v9.w f19387o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(g8.k kVar, h8.h hVar, f9.f fVar, v9.w wVar, g8.v0 v0Var) {
        super(kVar, hVar, fVar, v0Var);
        if (kVar == null) {
            v(0);
            throw null;
        }
        if (hVar == null) {
            v(1);
            throw null;
        }
        if (fVar == null) {
            v(2);
            throw null;
        }
        if (v0Var == null) {
            v(3);
            throw null;
        }
        this.f19387o = wVar;
    }

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
                i11 = 2;
                break;
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
                objArr[0] = "source";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorImpl";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i10) {
            case 4:
                objArr[1] = "getType";
                break;
            case 5:
                objArr[1] = "getOriginal";
                break;
            case 6:
                objArr[1] = "getValueParameters";
                break;
            case 7:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 8:
                objArr[1] = "getTypeParameters";
                break;
            case 9:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 10:
                objArr[1] = "getReturnType";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorImpl";
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
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public g8.s0 I() {
        return null;
    }

    public g8.s0 M() {
        return null;
    }

    @Override // g8.a
    public final List e() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        v(6);
        throw null;
    }

    @Override // g8.a
    public boolean f0() {
        return false;
    }

    public v9.w getReturnType() {
        v9.w type = getType();
        if (type != null) {
            return type;
        }
        v(10);
        throw null;
    }

    @Override // g8.d1
    public final v9.w getType() {
        v9.w wVar = this.f19387o;
        if (wVar != null) {
            return wVar;
        }
        v(4);
        throw null;
    }

    public List getTypeParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        v(8);
        throw null;
    }
}
