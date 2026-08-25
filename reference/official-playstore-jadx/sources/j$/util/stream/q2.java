package j$.util.stream;

import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q2 extends i2 implements f2 {
    @Override // j$.util.stream.g2
    public final /* synthetic */ Object[] m(IntFunction intFunction) {
        return v3.L(this, intFunction);
    }

    @Override // j$.util.stream.f2
    public final void g(Object obj) {
        ((f2) this.f18722a).g(obj);
        ((f2) this.f18723b).g(obj);
    }

    @Override // j$.util.stream.f2
    public final void e(Object obj, int i10) {
        g2 g2Var = this.f18722a;
        ((f2) g2Var).e(obj, i10);
        ((f2) this.f18723b).e(obj, i10 + ((int) ((f2) g2Var).count()));
    }

    @Override // j$.util.stream.f2
    public final Object b() {
        long j10 = this.f18724c;
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object objNewArray = newArray((int) j10);
        e(objNewArray, 0);
        return objNewArray;
    }

    public final String toString() {
        long j10 = this.f18724c;
        return j10 < 32 ? String.format("%s[%s.%s]", getClass().getName(), this.f18722a, this.f18723b) : String.format("%s[size=%d]", getClass().getName(), Long.valueOf(j10));
    }
}
