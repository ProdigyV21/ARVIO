package p4;

import com.google.common.util.concurrent.r0;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient int f21046m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f21047n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ e f21048o;

    public d(e eVar, int i10, int i11) {
        this.f21048o = eVar;
        this.f21046m = i10;
        this.f21047n = i11;
    }

    @Override // p4.a
    public final Object[] a() {
        return this.f21048o.a();
    }

    @Override // p4.a
    public final int b() {
        return this.f21048o.b() + this.f21046m;
    }

    @Override // p4.a
    public final int c() {
        return this.f21048o.b() + this.f21046m + this.f21047n;
    }

    @Override // p4.e, java.util.List
    /* JADX INFO: renamed from: f */
    public final e subList(int i10, int i11) {
        r0.H(i10, i11, this.f21047n);
        int i12 = this.f21046m;
        return this.f21048o.subList(i10 + i12, i11 + i12);
    }

    @Override // java.util.List
    public final Object get(int i10) {
        r0.G(i10, this.f21047n);
        return this.f21048o.get(i10 + this.f21046m);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f21047n;
    }
}
