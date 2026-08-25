package j8;

import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19388m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g8.k f19389n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final q9.f f19390o;

    public x(g8.f fVar) {
        super(h8.g.f15863a, f9.h.f15375d);
        this.f19389n = fVar;
        this.f19390o = new q9.c(fVar);
    }

    public static /* synthetic */ void D0(int i10) {
        String str = (i10 == 7 || i10 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 7 || i10 == 8) ? 2 : 3];
        switch (i10) {
            case 1:
            case 4:
                objArr[0] = "value";
                break;
            case 2:
            case 5:
                objArr[0] = "annotations";
                break;
            case 3:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 6:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
                break;
            case 9:
                objArr[0] = "newOwner";
                break;
            case 10:
                objArr[0] = "outType";
                break;
        }
        if (i10 == 7) {
            objArr[1] = "getValue";
        } else if (i10 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
        } else {
            objArr[1] = "getContainingDeclaration";
        }
        switch (i10) {
            case 7:
            case 8:
                break;
            case 9:
                objArr[2] = "copy";
                break;
            case 10:
                objArr[2] = "setOutType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 7 && i10 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 1 || i10 == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 1 || i10 == 2) ? 2 : 3];
        if (i10 == 1 || i10 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
        } else if (i10 != 3) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "newOwner";
        }
        if (i10 == 1) {
            objArr[1] = "getValue";
        } else if (i10 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
        } else {
            objArr[1] = "getContainingDeclaration";
        }
        if (i10 != 1 && i10 != 2) {
            if (i10 != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "copy";
            }
        }
        String str2 = String.format(str, objArr);
        if (i10 != 1 && i10 != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // g8.k
    public final g8.k d() {
        switch (this.f19388m) {
            case 0:
                g8.f fVar = (g8.f) this.f19389n;
                if (fVar != null) {
                    return fVar;
                }
                v(2);
                throw null;
            default:
                g8.k kVar = this.f19389n;
                if (kVar != null) {
                    return kVar;
                }
                D0(8);
                throw null;
        }
    }

    @Override // g8.s0
    public final q9.f getValue() {
        switch (this.f19388m) {
            case 0:
                q9.c cVar = (q9.c) this.f19390o;
                if (cVar != null) {
                    return cVar;
                }
                v(1);
                throw null;
            default:
                androidx.appcompat.app.o0 o0Var = (androidx.appcompat.app.o0) this.f19390o;
                if (o0Var != null) {
                    return o0Var;
                }
                D0(7);
                throw null;
        }
    }

    @Override // j8.o
    public String toString() {
        switch (this.f19388m) {
            case 0:
                return "class " + ((g8.f) this.f19389n).getName() + "::this";
            default:
                return super.toString();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public x(g8.k kVar, androidx.appcompat.app.o0 o0Var, h8.h hVar) {
        this(kVar, o0Var, hVar, f9.h.f15375d);
        if (kVar == null) {
            D0(0);
            throw null;
        }
        if (hVar != null) {
        } else {
            D0(2);
            throw null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(g8.k kVar, androidx.appcompat.app.o0 o0Var, h8.h hVar, f9.f fVar) {
        super(hVar, fVar);
        if (kVar == null) {
            D0(3);
            throw null;
        }
        if (hVar == null) {
            D0(5);
            throw null;
        }
        if (fVar != null) {
            this.f19389n = kVar;
            this.f19390o = o0Var;
            return;
        }
        D0(6);
        throw null;
    }
}
