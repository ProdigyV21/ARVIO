package j8;

import g8.a;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j0 extends p implements g8.o0 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f19292o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f19293p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f19294q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final g8.p0 f19295r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f19296s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f19297t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public g8.p f19298u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g8.w f19299v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(int i10, g8.p pVar, g8.p0 p0Var, h8.h hVar, f9.f fVar, boolean z, boolean z5, boolean z10, int i11, g8.v0 v0Var) {
        super(p0Var.d(), hVar, fVar, v0Var);
        if (i10 == 0) {
            v(0);
            throw null;
        }
        if (pVar == null) {
            v(1);
            throw null;
        }
        if (hVar == null) {
            v(3);
            throw null;
        }
        if (v0Var == null) {
            v(5);
            throw null;
        }
        this.f19299v = null;
        this.f19294q = i10;
        this.f19298u = pVar;
        this.f19295r = p0Var;
        this.f19292o = z;
        this.f19293p = z5;
        this.f19296s = z10;
        this.f19297t = i11;
    }

    public static /* synthetic */ void v(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 7:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i11 = 2;
                break;
            case 7:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = "visibility";
                break;
            case 2:
                objArr[0] = "correspondingProperty";
                break;
            case 3:
                objArr[0] = "annotations";
                break;
            case 4:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
            case 7:
                objArr[0] = "substitutor";
                break;
            case 16:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "modality";
                break;
        }
        switch (i10) {
            case 6:
                objArr[1] = "getKind";
                break;
            case 7:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
            case 8:
                objArr[1] = "substitute";
                break;
            case 9:
                objArr[1] = "getTypeParameters";
                break;
            case 10:
                objArr[1] = "getModality";
                break;
            case 11:
                objArr[1] = "getVisibility";
                break;
            case 12:
                objArr[1] = "getCorrespondingVariable";
                break;
            case 13:
                objArr[1] = "getCorrespondingProperty";
                break;
            case 14:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 15:
                objArr[1] = "getOverriddenDescriptors";
                break;
        }
        switch (i10) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                break;
            case 7:
                objArr[2] = "substitute";
                break;
            case 16:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                throw new IllegalStateException(str2);
            case 7:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // g8.w
    public final boolean A() {
        return false;
    }

    @Override // g8.w
    public final boolean B0() {
        return false;
    }

    @Override // g8.o0
    public final boolean E() {
        return this.f19292o;
    }

    public final ArrayList G0(boolean z) {
        ArrayList arrayList = new ArrayList(0);
        for (g8.p0 p0Var : T().k()) {
            g8.l getter = z ? p0Var.getGetter() : p0Var.getSetter();
            if (getter != null) {
                arrayList.add(getter);
            }
        }
        return arrayList;
    }

    @Override // g8.a
    public final g8.s0 I() {
        return T().I();
    }

    @Override // g8.a
    public final g8.s0 M() {
        return T().M();
    }

    @Override // g8.o0
    public final g8.p0 T() {
        g8.p0 p0Var = this.f19295r;
        if (p0Var != null) {
            return p0Var;
        }
        v(13);
        throw null;
    }

    @Override // g8.a0
    public final boolean V() {
        return false;
    }

    @Override // g8.w, g8.x0
    public final g8.w b(y0 y0Var) {
        if (y0Var != null) {
            return this;
        }
        v(7);
        throw null;
    }

    @Override // g8.a
    public final Object b0(a.InterfaceC0225a interfaceC0225a) {
        return null;
    }

    @Override // g8.a
    public final boolean f0() {
        return false;
    }

    @Override // g8.c
    public final int getKind() {
        int i10 = this.f19297t;
        if (i10 != 0) {
            return i10;
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
        v(9);
        throw null;
    }

    @Override // g8.o, g8.a0
    public final g8.p getVisibility() {
        g8.p pVar = this.f19298u;
        if (pVar != null) {
            return pVar;
        }
        v(11);
        throw null;
    }

    @Override // g8.a0
    public final boolean i0() {
        return false;
    }

    @Override // g8.a0
    public final boolean isExternal() {
        return this.f19293p;
    }

    @Override // g8.w
    public final boolean isInfix() {
        return false;
    }

    @Override // g8.w
    public final boolean isInline() {
        return this.f19296s;
    }

    @Override // g8.w
    public final boolean isOperator() {
        return false;
    }

    @Override // g8.w
    public final boolean isSuspend() {
        return false;
    }

    @Override // g8.w
    public final g8.w n0() {
        return this.f19299v;
    }

    @Override // g8.a0
    public final int o() {
        int i10 = this.f19294q;
        if (i10 != 0) {
            return i10;
        }
        v(10);
        throw null;
    }

    @Override // g8.a
    public final List s0() {
        List listS0 = T().s0();
        if (listS0 != null) {
            return listS0;
        }
        v(14);
        throw null;
    }

    @Override // g8.c
    public final g8.c v0(g8.f fVar, int i10, g8.p pVar) {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    @Override // g8.w
    public final boolean y0() {
        return false;
    }

    @Override // g8.c
    public final void z0(Collection collection) {
        if (collection != null) {
            return;
        }
        v(16);
        throw null;
    }

    @Override // g8.x0
    public final /* bridge */ /* synthetic */ g8.l b(y0 y0Var) {
        b(y0Var);
        return this;
    }
}
