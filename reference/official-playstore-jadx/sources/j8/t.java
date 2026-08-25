package j8;

import g8.c1;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final v9.j f19342q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final s f19343r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final u9.s f19344s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final h8.h f19345t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(u9.v vVar, g8.f fVar, v9.b0 b0Var, f9.f fVar2, u9.s sVar, h8.h hVar, g8.v0 v0Var) {
        super(vVar, fVar, fVar2, v0Var);
        if (vVar == null) {
            A0(6);
            throw null;
        }
        if (fVar == null) {
            A0(7);
            throw null;
        }
        if (b0Var == null) {
            A0(8);
            throw null;
        }
        if (fVar2 == null) {
            A0(9);
            throw null;
        }
        if (sVar == null) {
            A0(10);
            throw null;
        }
        if (v0Var == null) {
            A0(12);
            throw null;
        }
        this.f19345t = hVar;
        this.f19342q = new v9.j(this, Collections.EMPTY_LIST, Collections.singleton(b0Var), vVar);
        this.f19343r = new s(this, vVar);
        this.f19344s = sVar;
    }

    public static /* synthetic */ void A0(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                i11 = 2;
                break;
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = "enumClass";
                break;
            case 2:
            case 9:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
            case 10:
                objArr[0] = "enumMemberNames";
                break;
            case 4:
            case 11:
                objArr[0] = "annotations";
                break;
            case 5:
            case 12:
                objArr[0] = "source";
                break;
            case 6:
            default:
                objArr[0] = "storageManager";
                break;
            case 7:
                objArr[0] = "containingClass";
                break;
            case 8:
                objArr[0] = "supertype";
                break;
            case 13:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
        }
        switch (i10) {
            case 14:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 15:
                objArr[1] = "getStaticScope";
                break;
            case 16:
                objArr[1] = "getConstructors";
                break;
            case 17:
                objArr[1] = "getTypeConstructor";
                break;
            case 18:
                objArr[1] = "getKind";
                break;
            case 19:
                objArr[1] = "getModality";
                break;
            case 20:
                objArr[1] = "getVisibility";
                break;
            case 21:
                objArr[1] = "getAnnotations";
                break;
            case 22:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 23:
                objArr[1] = "getSealedSubclasses";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
        }
        switch (i10) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "<init>";
                break;
            case 13:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                break;
            default:
                objArr[2] = "create";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static t G0(u9.v vVar, g8.f fVar, f9.f fVar2, u9.s sVar, h8.h hVar, g8.v0 v0Var) {
        if (vVar == null) {
            A0(0);
            throw null;
        }
        if (fVar == null) {
            A0(1);
            throw null;
        }
        if (fVar2 == null) {
            A0(2);
            throw null;
        }
        if (sVar == null) {
            A0(3);
            throw null;
        }
        if (v0Var != null) {
            return new t(vVar, fVar, fVar.m(), fVar2, sVar, hVar, v0Var);
        }
        A0(5);
        throw null;
    }

    @Override // g8.f
    public final g8.e C() {
        return null;
    }

    @Override // g8.f
    public final boolean E0() {
        return false;
    }

    @Override // g8.f
    public final c1 S() {
        return null;
    }

    @Override // g8.a0
    public final boolean V() {
        return false;
    }

    @Override // g8.f
    public final boolean Y() {
        return false;
    }

    @Override // g8.f
    public final boolean c0() {
        return false;
    }

    @Override // g8.h
    public final v9.o0 g() {
        v9.j jVar = this.f19342q;
        if (jVar != null) {
            return jVar;
        }
        A0(17);
        throw null;
    }

    @Override // j8.b0
    public final p9.n g0(w9.e eVar) {
        s sVar = this.f19343r;
        if (sVar != null) {
            return sVar;
        }
        A0(14);
        throw null;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        h8.h hVar = this.f19345t;
        if (hVar != null) {
            return hVar;
        }
        A0(21);
        throw null;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        return g8.q.f15528e;
    }

    @Override // g8.f
    public final Collection h() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        A0(16);
        throw null;
    }

    @Override // g8.i
    public final boolean i() {
        return false;
    }

    @Override // g8.a0
    public final boolean i0() {
        return false;
    }

    @Override // g8.f
    public final boolean isInline() {
        return false;
    }

    @Override // g8.f
    public final p9.n j0() {
        return p9.m.f21235b;
    }

    @Override // g8.f, g8.i
    public final List n() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        A0(22);
        throw null;
    }

    @Override // g8.f, g8.a0
    public final int o() {
        return 1;
    }

    @Override // g8.f
    public final int p0() {
        return 4;
    }

    @Override // g8.f
    public final boolean q() {
        return false;
    }

    public final String toString() {
        return "enum entry " + getName();
    }

    @Override // g8.f
    public final Collection w() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        A0(23);
        throw null;
    }
}
