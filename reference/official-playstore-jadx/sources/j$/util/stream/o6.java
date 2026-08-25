package j$.util.stream;

import j$.util.Objects;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public class o6 extends u6 implements DoubleConsumer {
    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.u6
    public final void p(Object obj, int i10, int i11, Object obj2) {
        double[] dArr = (double[]) obj;
        DoubleConsumer doubleConsumer = (DoubleConsumer) obj2;
        while (i10 < i11) {
            doubleConsumer.accept(dArr[i10]);
            i10++;
        }
    }

    @Override // j$.util.stream.u6
    public final int q(Object obj) {
        return ((double[]) obj).length;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            g((DoubleConsumer) consumer);
        } else {
            if (g8.f18703a) {
                g8.a(getClass(), "{0} calling SpinedBuffer.OfDouble.forEach(Consumer)");
                throw null;
            }
            j$.com.android.tools.r8.a.i((n6) spliterator(), consumer);
        }
    }

    @Override // j$.util.stream.u6
    public final Object[] t() {
        return new double[8][];
    }

    @Override // j$.util.stream.u6
    public final Object newArray(int i10) {
        return new double[i10];
    }

    @Override // java.util.function.DoubleConsumer
    public void accept(double d4) {
        u();
        double[] dArr = (double[]) this.f18909e;
        int i10 = this.f18646b;
        this.f18646b = i10 + 1;
        dArr[i10] = d4;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        j$.util.e0 e0VarSpliterator = spliterator();
        Objects.requireNonNull(e0VarSpliterator);
        return new j$.util.s0(e0VarSpliterator);
    }

    @Override // j$.util.stream.u6, java.lang.Iterable, j$.util.stream.g2
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public j$.util.e0 spliterator() {
        return new n6(this, 0, this.f18647c, 0, this.f18646b);
    }

    public final String toString() {
        double[] dArr = (double[]) b();
        if (dArr.length < 200) {
            return String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(dArr.length), Integer.valueOf(this.f18647c), Arrays.toString(dArr));
        }
        return String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(dArr.length), Integer.valueOf(this.f18647c), Arrays.toString(Arrays.copyOf(dArr, 200)));
    }
}
