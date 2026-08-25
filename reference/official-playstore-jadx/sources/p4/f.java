package p4;

import com.google.common.util.concurrent.r0;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends e {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final f f21050o = new f(new Object[0], 0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Object[] f21051m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f21052n;

    public f(Object[] objArr, int i10) {
        this.f21051m = objArr;
        this.f21052n = i10;
    }

    @Override // p4.a
    public final Object[] a() {
        return this.f21051m;
    }

    @Override // p4.a
    public final int b() {
        return 0;
    }

    @Override // p4.a
    public final int c() {
        return this.f21052n;
    }

    @Override // p4.e, p4.a
    public final int d(Object[] objArr) {
        Object[] objArr2 = this.f21051m;
        int i10 = this.f21052n;
        System.arraycopy(objArr2, 0, objArr, 0, i10);
        return i10;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        r0.G(i10, this.f21052n);
        Object obj = this.f21051m[i10];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f21052n;
    }
}
