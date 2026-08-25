package f9;

import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f15368i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f15369l;

    public f(String str, boolean z) {
        if (str == null) {
            a(0);
            throw null;
        }
        this.f15368i = str;
        this.f15369l = z;
    }

    public static /* synthetic */ void a(int i10) {
        String str = (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4) ? 2 : 3];
        if (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/name/Name";
        } else {
            objArr[0] = ContentDisposition.Parameters.Name;
        }
        if (i10 == 1) {
            objArr[1] = "asString";
        } else if (i10 == 2) {
            objArr[1] = "getIdentifier";
        } else if (i10 == 3 || i10 == 4) {
            objArr[1] = "asStringStripSpecialMarkers";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
        }
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            case 5:
                objArr[2] = "identifier";
                break;
            case 6:
                objArr[2] = "isValidIdentifier";
                break;
            case 7:
                objArr[2] = "identifierIfValid";
                break;
            case 8:
                objArr[2] = "special";
                break;
            case 9:
                objArr[2] = "guessByFirstCharacter";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 1 && i10 != 2 && i10 != 3 && i10 != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static f i(String str) {
        if (str != null) {
            return str.startsWith("<") ? l(str) : j(str);
        }
        a(9);
        throw null;
    }

    public static f j(String str) {
        if (str != null) {
            return new f(str, false);
        }
        a(5);
        throw null;
    }

    public static boolean k(String str) {
        if (str == null) {
            a(6);
            throw null;
        }
        if (str.isEmpty() || str.startsWith("<")) {
            return false;
        }
        for (int i10 = 0; i10 < str.length(); i10++) {
            char cCharAt = str.charAt(i10);
            if (cCharAt == '.' || cCharAt == '/' || cCharAt == '\\') {
                return false;
            }
        }
        return true;
    }

    public static f l(String str) {
        if (str == null) {
            a(8);
            throw null;
        }
        if (str.startsWith("<")) {
            return new f(str, true);
        }
        throw new IllegalArgumentException("special name must start with '<': ".concat(str));
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f15368i.compareTo(((f) obj).f15368i);
    }

    public final String d() {
        String str = this.f15368i;
        if (str != null) {
            return str;
        }
        a(1);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.f15369l == fVar.f15369l && this.f15368i.equals(fVar.f15368i);
    }

    public final String f() {
        if (this.f15369l) {
            throw new IllegalStateException("not identifier: " + this);
        }
        String strD = d();
        if (strD != null) {
            return strD;
        }
        a(2);
        throw null;
    }

    public final int hashCode() {
        return (this.f15368i.hashCode() * 31) + (this.f15369l ? 1 : 0);
    }

    public final String toString() {
        return this.f15368i;
    }
}
