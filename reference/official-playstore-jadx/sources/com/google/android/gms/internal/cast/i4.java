package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class i4 extends j4 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient int f13312m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f13313n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ j4 f13314o;

    public i4(j4 j4Var, int i10, int i11) {
        this.f13314o = j4Var;
        this.f13312m = i10;
        this.f13313n = i11;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final Object[] a() {
        return this.f13314o.a();
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int b() {
        return this.f13314o.b() + this.f13312m;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int c() {
        return this.f13314o.b() + this.f13312m + this.f13313n;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final boolean e() {
        return true;
    }

    @Override // com.google.android.gms.internal.cast.j4, java.util.List
    /* JADX INFO: renamed from: g */
    public final j4 subList(int i10, int i11) {
        u0.w(i10, i11, this.f13313n);
        int i12 = this.f13312m;
        return this.f13314o.subList(i10 + i12, i11 + i12);
    }

    @Override // java.util.List
    public final Object get(int i10) {
        u0.r(i10, this.f13313n);
        return this.f13314o.get(i10 + this.f13312m);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13313n;
    }
}
