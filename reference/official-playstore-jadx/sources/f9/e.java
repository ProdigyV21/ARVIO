package f9;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f15361e = f.l("<root>");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Pattern f15362f = Pattern.compile("\\.");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f15363g = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient c f15365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient e f15366c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient f f15367d;

    public e(c cVar, String str) {
        if (str == null) {
            a(0);
            throw null;
        }
        this.f15364a = str;
        this.f15365b = cVar;
    }

    public static /* synthetic */ void a(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 9:
            case 15:
            case 16:
            case 17:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                i11 = 2;
                break;
            case 9:
            case 15:
            case 16:
            case 17:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        if (i10 != 1) {
            switch (i10) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 18:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                    break;
                case 9:
                    objArr[0] = ContentDisposition.Parameters.Name;
                    break;
                case 15:
                    objArr[0] = "segment";
                    break;
                case 16:
                    objArr[0] = "other";
                    break;
                case 17:
                    objArr[0] = "shortName";
                    break;
                default:
                    objArr[0] = "fqName";
                    break;
            }
        } else {
            objArr[0] = "safe";
        }
        switch (i10) {
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
            case 6:
                objArr[1] = "toSafe";
                break;
            case 7:
            case 8:
                objArr[1] = "parent";
                break;
            case 9:
            case 15:
            case 16:
            case 17:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                break;
            case 10:
            case 11:
                objArr[1] = "shortName";
                break;
            case 12:
            case 13:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 14:
                objArr[1] = "pathSegments";
                break;
            case 18:
                objArr[1] = "toString";
                break;
        }
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                break;
            case 9:
                objArr[2] = "child";
                break;
            case 15:
            case 16:
                objArr[2] = "startsWith";
                break;
            case 17:
                objArr[2] = "topLevel";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 18:
                throw new IllegalStateException(str2);
            case 9:
            case 15:
            case 16:
            case 17:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public final e b(f fVar) {
        String strD;
        if (fVar == null) {
            a(9);
            throw null;
        }
        String str = this.f15364a;
        if (str.isEmpty()) {
            strD = fVar.d();
        } else {
            strD = str + "." + fVar.d();
        }
        return new e(strD, this, fVar);
    }

    public final void c() {
        String str = this.f15364a;
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            this.f15367d = f.i(str.substring(iLastIndexOf + 1));
            this.f15366c = new e(str.substring(0, iLastIndexOf));
        } else {
            this.f15367d = f.i(str);
            this.f15366c = c.f15358c.i();
        }
    }

    public final boolean d() {
        if (this.f15365b != null) {
            return true;
        }
        String str = this.f15364a;
        if (str != null) {
            return str.indexOf(60) < 0;
        }
        a(4);
        throw null;
    }

    public final List e() {
        List list;
        String str = this.f15364a;
        if (str.isEmpty()) {
            list = Collections.EMPTY_LIST;
        } else {
            String[] strArrSplit = f15362f.split(str);
            ArrayList arrayList = new ArrayList(strArrSplit.length);
            for (String str2 : strArrSplit) {
                f15363g.getClass();
                arrayList.add(f.i(str2));
            }
            list = arrayList;
        }
        if (list != null) {
            return list;
        }
        a(14);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && this.f15364a.equals(((e) obj).f15364a);
    }

    public final f f() {
        f fVar = this.f15367d;
        if (fVar != null) {
            if (fVar != null) {
                return fVar;
            }
            a(10);
            throw null;
        }
        if (this.f15364a.isEmpty()) {
            throw new IllegalStateException("root");
        }
        c();
        f fVar2 = this.f15367d;
        if (fVar2 != null) {
            return fVar2;
        }
        a(11);
        throw null;
    }

    public final c g() {
        c cVar = this.f15365b;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(this);
        this.f15365b = cVar2;
        return cVar2;
    }

    public final int hashCode() {
        return this.f15364a.hashCode();
    }

    public final String toString() {
        String strD = this.f15364a;
        if (strD.isEmpty()) {
            strD = f15361e.d();
        }
        if (strD != null) {
            return strD;
        }
        a(18);
        throw null;
    }

    public e(String str) {
        if (str != null) {
            this.f15364a = str;
        } else {
            a(2);
            throw null;
        }
    }

    public e(String str, e eVar, f fVar) {
        if (str != null) {
            this.f15364a = str;
            this.f15366c = eVar;
            this.f15367d = fVar;
            return;
        }
        a(3);
        throw null;
    }
}
