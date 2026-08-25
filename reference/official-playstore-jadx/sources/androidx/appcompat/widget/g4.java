package androidx.appcompat.widget;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class g4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1589a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1591c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1592d;

    public gb.p a() {
        return new gb.p(this.f1589a, this.f1590b, (String[]) this.f1591c, (String[]) this.f1592d);
    }

    public void b(gb.m... mVarArr) {
        if (!this.f1589a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(mVarArr.length);
        for (gb.m mVar : mVarArr) {
            arrayList.add(mVar.f15757a);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        c((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void c(String... strArr) {
        if (!this.f1589a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        this.f1591c = (String[]) strArr.clone();
    }

    public void d(gb.u0... u0VarArr) {
        if (!this.f1589a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(u0VarArr.length);
        for (gb.u0 u0Var : u0VarArr) {
            arrayList.add(u0Var.f15826i);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        e((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void e(String... strArr) {
        if (!this.f1589a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        this.f1592d = (String[]) strArr.clone();
    }
}
