package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class b3 implements c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f18636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18637b;

    @Override // j$.util.stream.g2
    public final /* synthetic */ void forEach(Consumer consumer) {
        v3.Q(this, consumer);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.T(this, j10, j11);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ Object[] m(IntFunction intFunction) {
        return v3.L(this, intFunction);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.g2
    public final /* bridge */ /* synthetic */ g2 a(int i10) {
        a(i10);
        throw null;
    }

    @Override // j$.util.stream.f2, j$.util.stream.g2
    public final f2 a(int i10) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ void k(Object[] objArr, int i10) {
        v3.N(this, (Integer[]) objArr, i10);
    }

    @Override // j$.util.stream.f2
    public final void e(Object obj, int i10) {
        int i11 = this.f18637b;
        System.arraycopy(this.f18636a, 0, (int[]) obj, i10, i11);
    }

    @Override // j$.util.stream.f2
    public final void g(Object obj) {
        IntConsumer intConsumer = (IntConsumer) obj;
        for (int i10 = 0; i10 < this.f18637b; i10++) {
            intConsumer.accept(this.f18636a[i10]);
        }
    }

    public b3(long j10) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18636a = new int[(int) j10];
        this.f18637b = 0;
    }

    public b3(int[] iArr) {
        this.f18636a = iArr;
        this.f18637b = iArr.length;
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        int i10 = this.f18637b;
        int[] iArr = this.f18636a;
        Spliterators.a(((int[]) Objects.requireNonNull(iArr)).length, 0, i10);
        return new j$.util.z0(iArr, 0, i10, 1040);
    }

    @Override // j$.util.stream.f2, j$.util.stream.g2
    public final j$.util.n0 spliterator() {
        int i10 = this.f18637b;
        int[] iArr = this.f18636a;
        Spliterators.a(((int[]) Objects.requireNonNull(iArr)).length, 0, i10);
        return new j$.util.z0(iArr, 0, i10, 1040);
    }

    @Override // j$.util.stream.f2
    public final Object b() {
        int[] iArr = this.f18636a;
        int length = iArr.length;
        int i10 = this.f18637b;
        return length == i10 ? iArr : Arrays.copyOf(iArr, i10);
    }

    @Override // j$.util.stream.g2
    public final long count() {
        return this.f18637b;
    }

    public String toString() {
        int[] iArr = this.f18636a;
        return String.format("IntArrayNode[%d][%s]", Integer.valueOf(iArr.length - this.f18637b), Arrays.toString(iArr));
    }
}
