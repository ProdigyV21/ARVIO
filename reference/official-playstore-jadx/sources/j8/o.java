package j8;

import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o extends androidx.tvprovider.media.tv.h implements g8.k {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f9.f f19328l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(h8.h hVar, f9.f fVar) {
        super(hVar);
        if (hVar == null) {
            v(0);
            throw null;
        }
        if (fVar == null) {
            v(1);
            throw null;
        }
        this.f19328l = fVar;
    }

    public static String A0(g8.k kVar) {
        try {
            String str = h9.h.f15909e.v(kVar) + "[" + kVar.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(kVar)) + "]";
            if (str != null) {
                return str;
            }
            v(5);
            throw null;
        } catch (Throwable unused) {
            String str2 = kVar.getClass().getSimpleName() + " " + kVar.getName();
            if (str2 != null) {
                return str2;
            }
            v(6);
            throw null;
        }
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 2 || i10 == 3 || i10 == 5 || i10 == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 2 || i10 == 3 || i10 == 5 || i10 == 6) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
                break;
            case 4:
                objArr[0] = "descriptor";
                break;
            default:
                objArr[0] = "annotations";
                break;
        }
        if (i10 == 2) {
            objArr[1] = "getName";
        } else if (i10 == 3) {
            objArr[1] = "getOriginal";
        } else if (i10 == 5 || i10 == 6) {
            objArr[1] = "toString";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
        }
        if (i10 != 2 && i10 != 3) {
            if (i10 == 4) {
                objArr[2] = "toString";
            } else if (i10 != 5 && i10 != 6) {
                objArr[2] = "<init>";
            }
        }
        String str2 = String.format(str, objArr);
        if (i10 != 2 && i10 != 3 && i10 != 5 && i10 != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // g8.d0
    public final f9.f getName() {
        f9.f fVar = this.f19328l;
        if (fVar != null) {
            return fVar;
        }
        v(2);
        throw null;
    }

    public String toString() {
        return A0(this);
    }

    public g8.k a() {
        return this;
    }
}
