package j8;

import g8.b1;
import g8.z0;
import io.ktor.http.ContentDisposition;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j extends p implements b1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f19286o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f19287p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f19288q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final u9.s f19289r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final u9.s f19290s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final u9.v f19291t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(u9.v vVar, g8.k kVar, h8.h hVar, f9.f fVar, int i10, boolean z, int i11, z0 z0Var) {
        super(kVar, hVar, fVar, g8.v0.f15554b);
        if (vVar == null) {
            v(0);
            throw null;
        }
        if (kVar == null) {
            v(1);
            throw null;
        }
        if (hVar == null) {
            v(2);
            throw null;
        }
        if (fVar == null) {
            v(3);
            throw null;
        }
        if (i10 == 0) {
            v(4);
            throw null;
        }
        if (z0Var == null) {
            v(6);
            throw null;
        }
        this.f19286o = i10;
        this.f19287p = z;
        this.f19288q = i11;
        this.f19289r = vVar.d(new g(this, vVar, z0Var));
        this.f19290s = vVar.d(new h(this, fVar, 0));
        this.f19291t = vVar;
    }

    public static /* synthetic */ void v(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 12:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
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
                objArr[0] = "containingDeclaration";
                break;
            case 2:
                objArr[0] = "annotations";
                break;
            case 3:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 4:
                objArr[0] = "variance";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
                objArr[0] = "supertypeLoopChecker";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
            case 12:
                objArr[0] = "bounds";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i10) {
            case 7:
                objArr[1] = "getVariance";
                break;
            case 8:
                objArr[1] = "getUpperBounds";
                break;
            case 9:
                objArr[1] = "getTypeConstructor";
                break;
            case 10:
                objArr[1] = "getDefaultType";
                break;
            case 11:
                objArr[1] = "getOriginal";
                break;
            case 12:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
            case 13:
                objArr[1] = "processBoundsWithoutCycles";
                break;
            case 14:
                objArr[1] = "getStorageManager";
                break;
        }
        switch (i10) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                break;
            case 12:
                objArr[2] = "processBoundsWithoutCycles";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                throw new IllegalStateException(str2);
            case 12:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public abstract List H0();

    @Override // g8.b1
    public final u9.v J() {
        u9.v vVar = this.f19291t;
        if (vVar != null) {
            return vVar;
        }
        v(14);
        throw null;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.v(this, obj);
    }

    @Override // g8.b1
    public final boolean O() {
        return false;
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.h a() {
        return this;
    }

    @Override // g8.b1, g8.h
    public final v9.o0 g() {
        v9.o0 o0Var = (v9.o0) this.f19289r.invoke();
        if (o0Var != null) {
            return o0Var;
        }
        v(9);
        throw null;
    }

    @Override // g8.b1
    public final int getIndex() {
        return this.f19288q;
    }

    @Override // g8.b1
    public final List getUpperBounds() {
        List listD = ((i) g()).d();
        if (listD != null) {
            return listD;
        }
        v(8);
        throw null;
    }

    @Override // g8.h
    public final v9.b0 m() {
        v9.b0 b0Var = (v9.b0) this.f19290s.invoke();
        if (b0Var != null) {
            return b0Var;
        }
        v(10);
        throw null;
    }

    @Override // g8.b1
    public final boolean u() {
        return this.f19287p;
    }

    @Override // g8.b1
    public final int y() {
        int i10 = this.f19286o;
        if (i10 != 0) {
            return i10;
        }
        v(7);
        throw null;
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.k a() {
        return this;
    }

    @Override // j8.p, j8.o, g8.k
    public final b1 a() {
        return this;
    }

    @Override // j8.p
    /* JADX INFO: renamed from: D0 */
    public final g8.n a() {
        return this;
    }

    public List G0(List list) {
        return list;
    }
}
