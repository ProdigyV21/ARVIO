package ga;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class s implements m, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f15600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15601b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f15602c;

    public s(m mVar, int i10, int i11) {
        this.f15600a = mVar;
        this.f15601b = i10;
        this.f15602c = i11;
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "startIndex should be non-negative, but is ").toString());
        }
        if (i11 < 0) {
            throw new IllegalArgumentException(a0.c.i(i11, "endIndex should be non-negative, but is ").toString());
        }
        if (i11 < i10) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i11, i10, "endIndex should be not less than startIndex, but was ", " < ").toString());
        }
    }

    @Override // ga.f
    public final m a(int i10) {
        int i11 = this.f15602c;
        int i12 = this.f15601b;
        return i10 >= i11 - i12 ? g.f15574a : new s(this.f15600a, i12 + i10, i11);
    }

    @Override // ga.f
    public final m b(int i10) {
        int i11 = this.f15602c;
        int i12 = this.f15601b;
        return i10 >= i11 - i12 ? this : new s(this.f15600a, i12, i10 + i12);
    }

    @Override // ga.m
    public final Iterator iterator() {
        return new k(this);
    }
}
