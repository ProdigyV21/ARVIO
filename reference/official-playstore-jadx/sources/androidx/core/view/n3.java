package androidx.core.view;

import android.os.Build;
import android.view.View;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class n3 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q3 f2295b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q3 f2296a;

    static {
        int i10 = Build.VERSION.SDK_INT;
        f2295b = (i10 >= 30 ? new g3() : i10 >= 29 ? new f3() : new e3()).b().f2300a.a().f2300a.b().f2300a.c();
    }

    public n3(q3 q3Var) {
        this.f2296a = q3Var;
    }

    public q3 a() {
        return this.f2296a;
    }

    public q3 b() {
        return this.f2296a;
    }

    public q3 c() {
        return this.f2296a;
    }

    public void d(View view) {
    }

    public v e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n3)) {
            return false;
        }
        n3 n3Var = (n3) obj;
        return o() == n3Var.o() && n() == n3Var.n() && Objects.equals(k(), n3Var.k()) && Objects.equals(i(), n3Var.i()) && Objects.equals(e(), n3Var.e());
    }

    public androidx.core.graphics.c f(int i10) {
        return androidx.core.graphics.c.f2085e;
    }

    public androidx.core.graphics.c g(int i10) {
        if ((i10 & 8) == 0) {
            return androidx.core.graphics.c.f2085e;
        }
        throw new IllegalArgumentException("Unable to query the maximum insets for IME");
    }

    public androidx.core.graphics.c h() {
        return k();
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(o()), Boolean.valueOf(n()), k(), i(), e());
    }

    public androidx.core.graphics.c i() {
        return androidx.core.graphics.c.f2085e;
    }

    public androidx.core.graphics.c j() {
        return k();
    }

    public androidx.core.graphics.c k() {
        return androidx.core.graphics.c.f2085e;
    }

    public androidx.core.graphics.c l() {
        return k();
    }

    public q3 m(int i10, int i11, int i12, int i13) {
        return f2295b;
    }

    public boolean n() {
        return false;
    }

    public boolean o() {
        return false;
    }

    public boolean p(int i10) {
        return true;
    }

    public void q(androidx.core.graphics.c[] cVarArr) {
    }

    public void r(q3 q3Var) {
    }

    public void s(androidx.core.graphics.c cVar) {
    }
}
