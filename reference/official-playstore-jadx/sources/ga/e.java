package ga;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements m, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f15572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f15573c;

    public e(m mVar, int i10, int i11) {
        this.f15571a = i11;
        switch (i11) {
            case 1:
                this.f15572b = mVar;
                this.f15573c = i10;
                if (i10 < 0) {
                    throw new IllegalArgumentException(a0.c.k("count must be non-negative, but was ", i10, '.').toString());
                }
                return;
            default:
                this.f15572b = mVar;
                this.f15573c = i10;
                if (i10 < 0) {
                    throw new IllegalArgumentException(a0.c.k("count must be non-negative, but was ", i10, '.').toString());
                }
                return;
        }
    }

    @Override // ga.f
    public final m a(int i10) {
        switch (this.f15571a) {
            case 0:
                int i11 = this.f15573c + i10;
                return i11 < 0 ? new e(this, i10, 0) : new e(this.f15572b, i11, 0);
            default:
                int i12 = this.f15573c;
                return i10 >= i12 ? g.f15574a : new s(this.f15572b, i10, i12);
        }
    }

    @Override // ga.f
    public final m b(int i10) {
        switch (this.f15571a) {
            case 0:
                int i11 = this.f15573c;
                int i12 = i11 + i10;
                return i12 < 0 ? new e(this, i10, 1) : new s(this.f15572b, i11, i12);
            default:
                return i10 >= this.f15573c ? this : new e(this.f15572b, i10, 1);
        }
    }

    @Override // ga.m
    public final Iterator iterator() {
        switch (this.f15571a) {
            case 0:
                return new d(this);
            default:
                return new d(this, (byte) 0);
        }
    }
}
