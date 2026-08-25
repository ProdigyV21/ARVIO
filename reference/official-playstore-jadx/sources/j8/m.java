package j8;

import g8.c1;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class m extends l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f19318q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f19319r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final v9.j f19320s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public p9.n f19321t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Set f19322u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g8.e f19323v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(g8.k kVar, f9.f fVar, int i10, int i11, List list, u9.v vVar) {
        super(vVar, kVar, fVar, g8.v0.f15554b);
        if (kVar == null) {
            A0(0);
            throw null;
        }
        if (fVar == null) {
            A0(1);
            throw null;
        }
        if (i10 == 0) {
            A0(2);
            throw null;
        }
        if (i11 == 0) {
            A0(3);
            throw null;
        }
        if (list == null) {
            A0(4);
            throw null;
        }
        if (vVar == null) {
            A0(6);
            throw null;
        }
        this.f19318q = i10;
        this.f19319r = i11;
        this.f19320s = new v9.j(this, Collections.EMPTY_LIST, list, vVar);
    }

    public static /* synthetic */ void A0(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 12:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                i11 = 2;
                break;
            case 12:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 2:
                objArr[0] = "modality";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "supertypes";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
                objArr[0] = "storageManager";
                break;
            case 7:
                objArr[0] = "unsubstitutedMemberScope";
                break;
            case 8:
                objArr[0] = "constructors";
                break;
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
            case 12:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i10) {
            case 9:
                objArr[1] = "getAnnotations";
                break;
            case 10:
                objArr[1] = "getTypeConstructor";
                break;
            case 11:
                objArr[1] = "getConstructors";
                break;
            case 12:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
            case 13:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 14:
                objArr[1] = "getStaticScope";
                break;
            case 15:
                objArr[1] = "getKind";
                break;
            case 16:
                objArr[1] = "getModality";
                break;
            case 17:
                objArr[1] = "getVisibility";
                break;
            case 18:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 19:
                objArr[1] = "getSealedSubclasses";
                break;
        }
        switch (i10) {
            case 7:
            case 8:
                objArr[2] = "initialize";
                break;
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                break;
            case 12:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                throw new IllegalStateException(str2);
            case 12:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // g8.f
    public final g8.e C() {
        return this.f19323v;
    }

    @Override // g8.f
    public final boolean E0() {
        return false;
    }

    public final void G0(p9.n nVar, Set set, k kVar) {
        this.f19321t = nVar;
        this.f19322u = set;
        this.f19323v = kVar;
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
        v9.j jVar = this.f19320s;
        if (jVar != null) {
            return jVar;
        }
        A0(10);
        throw null;
    }

    @Override // j8.b0
    public final p9.n g0(w9.e eVar) {
        p9.n nVar = this.f19321t;
        if (nVar != null) {
            return nVar;
        }
        A0(13);
        throw null;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return h8.g.f15863a;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        return g8.q.f15528e;
    }

    @Override // g8.f
    public final Collection h() {
        Set set = this.f19322u;
        if (set != null) {
            return set;
        }
        A0(11);
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
        A0(18);
        throw null;
    }

    @Override // g8.f, g8.a0
    public final int o() {
        int i10 = this.f19318q;
        if (i10 != 0) {
            return i10;
        }
        A0(16);
        throw null;
    }

    @Override // g8.f
    public final int p0() {
        int i10 = this.f19319r;
        if (i10 != 0) {
            return i10;
        }
        A0(15);
        throw null;
    }

    @Override // g8.f
    public final boolean q() {
        return false;
    }

    public String toString() {
        return "class " + getName();
    }

    @Override // g8.f
    public final Collection w() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        A0(19);
        throw null;
    }
}
