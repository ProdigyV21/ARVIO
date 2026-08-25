package j$.util.stream;

import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class s3 extends v6 implements g2, y1 {
    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    @Override // j$.util.stream.y1
    public final g2 build() {
        return this;
    }

    @Override // j$.util.stream.l5
    public final void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.V(this, j10, j11, intFunction);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.g2
    public final g2 a(int i10) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.g2
    public final void k(Object[] objArr, int i10) {
        long j10 = i10;
        long jCount = count() + j10;
        if (jCount > objArr.length || jCount < j10) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.f18647c == 0) {
            System.arraycopy(this.f18930e, 0, objArr, i10, this.f18646b);
            return;
        }
        for (int i11 = 0; i11 < this.f18647c; i11++) {
            Object[] objArr2 = this.f18931f[i11];
            System.arraycopy(objArr2, 0, objArr, i10, objArr2.length);
            i10 += this.f18931f[i11].length;
        }
        int i12 = this.f18646b;
        if (i12 > 0) {
            System.arraycopy(this.f18930e, 0, objArr, i10, i12);
        }
    }

    @Override // j$.util.stream.g2
    public final Object[] m(IntFunction intFunction) {
        long jCount = count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jCount);
        k(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        clear();
        p(j10);
    }
}
