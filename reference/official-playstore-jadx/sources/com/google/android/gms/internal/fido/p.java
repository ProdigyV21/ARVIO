package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient int f13803m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f13804n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ q f13805o;

    public p(q qVar, int i10, int i11) {
        this.f13805o = qVar;
        this.f13803m = i10;
        this.f13804n = i11;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int b() {
        return this.f13805o.c() + this.f13803m + this.f13804n;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int c() {
        return this.f13805o.c() + this.f13803m;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final Object[] e() {
        return this.f13805o.e();
    }

    @Override // com.google.android.gms.internal.fido.q, java.util.List
    /* JADX INFO: renamed from: g */
    public final q subList(int i10, int i11) {
        b.m(i10, i11, this.f13804n);
        int i12 = this.f13803m;
        return this.f13805o.subList(i10 + i12, i11 + i12);
    }

    @Override // java.util.List
    public final Object get(int i10) {
        b.e(i10, this.f13804n);
        return this.f13805o.get(i10 + this.f13803m);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13804n;
    }
}
