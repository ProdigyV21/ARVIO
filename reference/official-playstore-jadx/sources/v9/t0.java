package v9;

import io.ktor.http.LinkHeader;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 extends s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22250a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w f22251b;

    public t0(int i10, w wVar) {
        if (i10 == 0) {
            d(0);
            throw null;
        }
        if (wVar == null) {
            d(1);
            throw null;
        }
        this.f22250a = i10;
        this.f22251b = wVar;
    }

    public static /* synthetic */ void d(int i10) {
        String str = (i10 == 4 || i10 == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 4 || i10 == 5) ? 2 : 3];
        switch (i10) {
            case 1:
            case 2:
            case 3:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 4:
            case 5:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
                break;
            case 6:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "projection";
                break;
        }
        if (i10 == 4) {
            objArr[1] = "getProjectionKind";
        } else if (i10 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
        } else {
            objArr[1] = "getType";
        }
        if (i10 == 3) {
            objArr[2] = "replaceType";
        } else if (i10 != 4 && i10 != 5) {
            if (i10 != 6) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "refine";
            }
        }
        String str2 = String.format(str, objArr);
        if (i10 != 4 && i10 != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // v9.r0
    public final r0 a(w9.e eVar) {
        if (eVar != null) {
            return new t0(this.f22250a, this.f22251b);
        }
        d(6);
        throw null;
    }

    @Override // v9.r0
    public final boolean b() {
        return false;
    }

    @Override // v9.r0
    public final int c() {
        int i10 = this.f22250a;
        if (i10 != 0) {
            return i10;
        }
        d(4);
        throw null;
    }

    @Override // v9.r0
    public final w getType() {
        w wVar = this.f22251b;
        if (wVar != null) {
            return wVar;
        }
        d(5);
        throw null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public t0(w wVar) {
        this(1, wVar);
        if (wVar != null) {
        } else {
            d(2);
            throw null;
        }
    }
}
