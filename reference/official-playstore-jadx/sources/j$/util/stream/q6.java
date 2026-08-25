package j$.util.stream;

import j$.util.Objects;
import j$.util.function.IntConsumer$CC;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public class q6 extends u6 implements IntConsumer {
    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.u6
    public final void p(Object obj, int i10, int i11, Object obj2) {
        int[] iArr = (int[]) obj;
        IntConsumer intConsumer = (IntConsumer) obj2;
        while (i10 < i11) {
            intConsumer.accept(iArr[i10]);
            i10++;
        }
    }

    @Override // j$.util.stream.u6
    public final int q(Object obj) {
        return ((int[]) obj).length;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            g((IntConsumer) consumer);
        } else {
            if (g8.f18703a) {
                g8.a(getClass(), "{0} calling SpinedBuffer.OfInt.forEach(Consumer)");
                throw null;
            }
            j$.com.android.tools.r8.a.j((p6) spliterator(), consumer);
        }
    }

    @Override // j$.util.stream.u6
    public final Object[] t() {
        return new int[8][];
    }

    @Override // j$.util.stream.u6
    public final Object newArray(int i10) {
        return new int[i10];
    }

    @Override // java.util.function.IntConsumer
    public void accept(int i10) {
        u();
        int[] iArr = (int[]) this.f18909e;
        int i11 = this.f18646b;
        this.f18646b = i11 + 1;
        iArr[i11] = i10;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        j$.util.h0 h0VarSpliterator = spliterator();
        Objects.requireNonNull(h0VarSpliterator);
        return new j$.util.q0(h0VarSpliterator);
    }

    @Override // j$.util.stream.u6, java.lang.Iterable, j$.util.stream.g2
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public j$.util.h0 spliterator() {
        return new p6(this, 0, this.f18647c, 0, this.f18646b);
    }

    public final String toString() {
        int[] iArr = (int[]) b();
        if (iArr.length < 200) {
            return String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.f18647c), Arrays.toString(iArr));
        }
        return String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.f18647c), Arrays.toString(Arrays.copyOf(iArr, 200)));
    }
}
