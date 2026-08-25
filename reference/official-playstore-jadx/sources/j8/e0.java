package j8;

import g8.c1;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f19262q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f19263r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g8.p f19264s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public v9.j f19265t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList f19266u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ArrayList f19267v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final u9.v f19268w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(f8.j jVar, f9.f fVar, u9.v vVar) {
        super(vVar, jVar, fVar, g8.v0.f15554b);
        if (fVar == null) {
            A0(2);
            throw null;
        }
        if (vVar == null) {
            A0(4);
            throw null;
        }
        this.f19267v = new ArrayList();
        this.f19268w = vVar;
        this.f19262q = 2;
    }

    public static /* synthetic */ void A0(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                i11 = 2;
                break;
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = "kind";
                break;
            case 2:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
                objArr[0] = "source";
                break;
            case 4:
                objArr[0] = "storageManager";
                break;
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
            case 6:
                objArr[0] = "modality";
                break;
            case 9:
                objArr[0] = "visibility";
                break;
            case 12:
                objArr[0] = "supertype";
                break;
            case 14:
                objArr[0] = "typeParameters";
                break;
            case 16:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i10) {
            case 5:
                objArr[1] = "getAnnotations";
                break;
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
            case 7:
                objArr[1] = "getModality";
                break;
            case 8:
                objArr[1] = "getKind";
                break;
            case 10:
                objArr[1] = "getVisibility";
                break;
            case 11:
                objArr[1] = "getTypeConstructor";
                break;
            case 13:
                objArr[1] = "getConstructors";
                break;
            case 15:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 17:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 18:
                objArr[1] = "getStaticScope";
                break;
            case 19:
                objArr[1] = "getSealedSubclasses";
                break;
        }
        switch (i10) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                break;
            case 6:
                objArr[2] = "setModality";
                break;
            case 9:
                objArr[2] = "setVisibility";
                break;
            case 12:
                objArr[2] = "addSupertype";
                break;
            case 14:
                objArr[2] = "setTypeParameterDescriptors";
                break;
            case 16:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                throw new IllegalStateException(str2);
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                throw new IllegalArgumentException(str2);
        }
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
        v9.j jVar = this.f19265t;
        if (jVar != null) {
            return jVar;
        }
        A0(11);
        throw null;
    }

    @Override // j8.b0
    public final p9.n g0(w9.e eVar) {
        return p9.m.f21235b;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return h8.g.f15863a;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        g8.p pVar = this.f19264s;
        if (pVar != null) {
            return pVar;
        }
        A0(10);
        throw null;
    }

    @Override // g8.f
    public final Collection h() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        A0(13);
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
        ArrayList arrayList = this.f19266u;
        if (arrayList != null) {
            return arrayList;
        }
        A0(15);
        throw null;
    }

    @Override // g8.f, g8.a0
    public final int o() {
        int i10 = this.f19263r;
        if (i10 != 0) {
            return i10;
        }
        A0(7);
        throw null;
    }

    @Override // g8.f
    public final int p0() {
        int i10 = this.f19262q;
        if (i10 != 0) {
            return i10;
        }
        A0(8);
        throw null;
    }

    @Override // g8.f
    public final boolean q() {
        return false;
    }

    public final String toString() {
        return o.A0(this);
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
