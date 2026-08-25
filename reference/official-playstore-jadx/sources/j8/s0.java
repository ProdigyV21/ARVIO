package j8;

import g8.y0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class s0 extends j {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ArrayList f19340u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f19341v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(g8.k kVar, h8.h hVar, boolean z, int i10, f9.f fVar, int i11, u9.v vVar) {
        super(vVar, kVar, hVar, fVar, i10, z, i11, y0.f15559l);
        if (kVar == null) {
            v(19);
            throw null;
        }
        if (hVar == null) {
            v(20);
            throw null;
        }
        if (i10 == 0) {
            v(21);
            throw null;
        }
        if (fVar == null) {
            v(22);
            throw null;
        }
        if (vVar == null) {
            v(25);
            throw null;
        }
        this.f19340u = new ArrayList(1);
        this.f19341v = false;
    }

    public static s0 I0(g8.k kVar, h8.h hVar, boolean z, int i10, f9.f fVar, int i11, u9.v vVar) {
        if (kVar == null) {
            v(6);
            throw null;
        }
        if (hVar == null) {
            v(7);
            throw null;
        }
        if (i10 == 0) {
            v(8);
            throw null;
        }
        if (fVar == null) {
            v(9);
            throw null;
        }
        if (vVar == null) {
            v(11);
            throw null;
        }
        if (i10 != 0) {
            return new s0(kVar, hVar, z, i10, fVar, i11, vVar);
        }
        v(14);
        throw null;
    }

    public static s0 J0(b bVar, int i10, f9.f fVar, int i11, u9.v vVar) {
        if (i10 == 0) {
            v(2);
            throw null;
        }
        if (vVar == null) {
            v(4);
            throw null;
        }
        s0 s0VarI0 = I0(bVar, h8.g.f15863a, false, i10, fVar, i11, vVar);
        v9.b0 b0VarM = m9.d.e(bVar).m();
        if (s0VarI0.f19341v) {
            throw new IllegalStateException("Type parameter descriptor is already initialized: " + s0VarI0.K0());
        }
        if (!v9.c.g(b0VarM)) {
            s0VarI0.f19340u.add(b0VarM);
        }
        if (!s0VarI0.f19341v) {
            s0VarI0.f19341v = true;
            return s0VarI0;
        }
        throw new IllegalStateException("Type parameter descriptor is already initialized: " + s0VarI0.K0());
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 5 || i10 == 28) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 5 || i10 == 28) ? 2 : 3];
        switch (i10) {
            case 1:
            case 7:
            case 13:
            case 20:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 14:
            case 21:
                objArr[0] = "variance";
                break;
            case 3:
            case 9:
            case 15:
            case 22:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 4:
            case 11:
            case 18:
            case 25:
                objArr[0] = "storageManager";
                break;
            case 5:
            case 28:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
                break;
            case 6:
            case 12:
            case 19:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 10:
            case 16:
            case 23:
                objArr[0] = "source";
                break;
            case 17:
                objArr[0] = "supertypeLoopsResolver";
                break;
            case 24:
                objArr[0] = "supertypeLoopsChecker";
                break;
            case 26:
                objArr[0] = "bound";
                break;
            case 27:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
        }
        if (i10 == 5) {
            objArr[1] = "createWithDefaultBound";
        } else if (i10 != 28) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
        } else {
            objArr[1] = "resolveUpperBounds";
        }
        switch (i10) {
            case 5:
            case 28:
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createForFurtherModification";
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "<init>";
                break;
            case 26:
                objArr[2] = "addUpperBound";
                break;
            case 27:
                objArr[2] = "reportSupertypeLoopError";
                break;
            default:
                objArr[2] = "createWithDefaultBound";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 5 && i10 != 28) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // j8.j
    public final List H0() {
        if (!this.f19341v) {
            throw new IllegalStateException("Type parameter descriptor is not initialized: " + K0());
        }
        ArrayList arrayList = this.f19340u;
        if (arrayList != null) {
            return arrayList;
        }
        v(28);
        throw null;
    }

    public final String K0() {
        return getName() + " declared in " + i9.f.g(d());
    }
}
