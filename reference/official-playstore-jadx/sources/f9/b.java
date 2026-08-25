package f9;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f15355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f15356b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f15357c;

    public b(c cVar, c cVar2, boolean z) {
        if (cVar == null) {
            a(1);
            throw null;
        }
        this.f15355a = cVar;
        this.f15356b = cVar2;
        this.f15357c = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r10) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f9.b.a(int):void");
    }

    public static b e(String str, boolean z) {
        String str2;
        if (str == null) {
            a(12);
            throw null;
        }
        int iLastIndexOf = str.lastIndexOf(DomExceptionUtils.SEPARATOR);
        if (iLastIndexOf == -1) {
            str2 = "";
        } else {
            String strReplace = str.substring(0, iLastIndexOf).replace('/', '.');
            str = str.substring(iLastIndexOf + 1);
            str2 = strReplace;
        }
        return new b(new c(str2), new c(str), z);
    }

    public static b j(c cVar) {
        if (cVar != null) {
            return new b(cVar.e(), cVar.f());
        }
        a(0);
        throw null;
    }

    public final c b() {
        c cVar = this.f15355a;
        boolean zD = cVar.d();
        c cVar2 = this.f15356b;
        if (zD) {
            if (cVar2 != null) {
                return cVar2;
            }
            a(9);
            throw null;
        }
        return new c(cVar.b() + "." + cVar2.b());
    }

    public final String c() {
        c cVar = this.f15355a;
        boolean zD = cVar.d();
        c cVar2 = this.f15356b;
        if (zD) {
            return cVar2.b();
        }
        String str = cVar.b().replace('.', '/') + DomExceptionUtils.SEPARATOR + cVar2.b();
        if (str != null) {
            return str;
        }
        a(14);
        throw null;
    }

    public final b d(f fVar) {
        if (fVar != null) {
            return new b(g(), this.f15356b.c(fVar), this.f15357c);
        }
        a(8);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass()) {
            b bVar = (b) obj;
            if (this.f15355a.equals(bVar.f15355a) && this.f15356b.equals(bVar.f15356b) && this.f15357c == bVar.f15357c) {
                return true;
            }
        }
        return false;
    }

    public final b f() {
        c cVarE = this.f15356b.e();
        if (cVarE.d()) {
            return null;
        }
        return new b(g(), cVarE, this.f15357c);
    }

    public final c g() {
        c cVar = this.f15355a;
        if (cVar != null) {
            return cVar;
        }
        a(5);
        throw null;
    }

    public final c h() {
        c cVar = this.f15356b;
        if (cVar != null) {
            return cVar;
        }
        a(6);
        throw null;
    }

    public final int hashCode() {
        return Boolean.valueOf(this.f15357c).hashCode() + ((this.f15356b.hashCode() + (this.f15355a.hashCode() * 31)) * 31);
    }

    public final f i() {
        f fVarF = this.f15356b.f();
        if (fVarF != null) {
            return fVarF;
        }
        a(7);
        throw null;
    }

    public final String toString() {
        return this.f15355a.d() ? DomExceptionUtils.SEPARATOR.concat(c()) : c();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(c cVar, f fVar) {
        this(cVar, c.j(fVar), false);
        if (cVar == null) {
            a(3);
            throw null;
        }
        if (fVar != null) {
        } else {
            a(4);
            throw null;
        }
    }
}
