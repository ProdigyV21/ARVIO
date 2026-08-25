package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public class k3 implements e2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long[] f18760a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18761b;

    @Override // j$.util.stream.g2
    public final /* synthetic */ void forEach(Consumer consumer) {
        v3.R(this, consumer);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.U(this, j10, j11);
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
        v3.O(this, (Long[]) objArr, i10);
    }

    @Override // j$.util.stream.f2
    public final void e(Object obj, int i10) {
        int i11 = this.f18761b;
        System.arraycopy(this.f18760a, 0, (long[]) obj, i10, i11);
    }

    @Override // j$.util.stream.f2
    public final void g(Object obj) {
        LongConsumer longConsumer = (LongConsumer) obj;
        for (int i10 = 0; i10 < this.f18761b; i10++) {
            longConsumer.accept(this.f18760a[i10]);
        }
    }

    public k3(long j10) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18760a = new long[(int) j10];
        this.f18761b = 0;
    }

    public k3(long[] jArr) {
        this.f18760a = jArr;
        this.f18761b = jArr.length;
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        int i10 = this.f18761b;
        long[] jArr = this.f18760a;
        Spliterators.a(((long[]) Objects.requireNonNull(jArr)).length, 0, i10);
        return new j$.util.b1(jArr, 0, i10, 1040);
    }

    @Override // j$.util.stream.f2, j$.util.stream.g2
    public final j$.util.n0 spliterator() {
        int i10 = this.f18761b;
        long[] jArr = this.f18760a;
        Spliterators.a(((long[]) Objects.requireNonNull(jArr)).length, 0, i10);
        return new j$.util.b1(jArr, 0, i10, 1040);
    }

    @Override // j$.util.stream.f2
    public final Object b() {
        long[] jArr = this.f18760a;
        int length = jArr.length;
        int i10 = this.f18761b;
        return length == i10 ? jArr : Arrays.copyOf(jArr, i10);
    }

    @Override // j$.util.stream.g2
    public final long count() {
        return this.f18761b;
    }

    public String toString() {
        long[] jArr = this.f18760a;
        return String.format("LongArrayNode[%d][%s]", Integer.valueOf(jArr.length - this.f18761b), Arrays.toString(jArr));
    }
}
