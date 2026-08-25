package v9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j8.b0 f22217c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f22218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Collection f22219e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(j8.b0 b0Var, List list, Collection collection, u9.v vVar) {
        super(vVar);
        if (list == null) {
            m(1);
            throw null;
        }
        if (collection == null) {
            m(2);
            throw null;
        }
        if (vVar == null) {
            m(3);
            throw null;
        }
        this.f22217c = b0Var;
        this.f22218d = Collections.unmodifiableList(new ArrayList(list));
        this.f22219e = Collections.unmodifiableCollection(collection);
    }

    public static /* synthetic */ void m(int i10) {
        String str = (i10 == 4 || i10 == 5 || i10 == 6 || i10 == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 4 || i10 == 5 || i10 == 6 || i10 == 7) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = "parameters";
                break;
            case 2:
                objArr[0] = "supertypes";
                break;
            case 3:
                objArr[0] = "storageManager";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
                break;
            default:
                objArr[0] = "classDescriptor";
                break;
        }
        if (i10 == 4) {
            objArr[1] = "getParameters";
        } else if (i10 == 5) {
            objArr[1] = "getDeclarationDescriptor";
        } else if (i10 == 6) {
            objArr[1] = "computeSupertypes";
        } else if (i10 != 7) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
        } else {
            objArr[1] = "getSupertypeLoopChecker";
        }
        if (i10 != 4 && i10 != 5 && i10 != 6 && i10 != 7) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 4 && i10 != 5 && i10 != 6 && i10 != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // v9.g
    public final Collection c() {
        Collection collection = this.f22219e;
        if (collection != null) {
            return collection;
        }
        m(6);
        throw null;
    }

    @Override // v9.o0
    public final boolean f() {
        return true;
    }

    @Override // v9.o0
    public final List getParameters() {
        List list = this.f22218d;
        if (list != null) {
            return list;
        }
        m(4);
        throw null;
    }

    @Override // v9.g
    public final g8.z0 h() {
        return g8.y0.f15559l;
    }

    @Override // v9.b
    /* JADX INFO: renamed from: n */
    public final g8.f e() {
        j8.b0 b0Var = this.f22217c;
        if (b0Var != null) {
            return b0Var;
        }
        m(5);
        throw null;
    }

    public final String toString() {
        String str = i9.f.g(this.f22217c).f15364a;
        if (str != null) {
            return str;
        }
        f9.e.a(4);
        throw null;
    }
}
