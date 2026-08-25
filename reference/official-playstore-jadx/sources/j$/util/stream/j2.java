package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class j2 implements g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f18739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18740b;

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

    public j2(long j10, IntFunction intFunction) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18739a = (Object[]) intFunction.apply((int) j10);
        this.f18740b = 0;
    }

    public j2(Object[] objArr) {
        this.f18739a = objArr;
        this.f18740b = objArr.length;
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        int i10 = this.f18740b;
        Object[] objArr = this.f18739a;
        Spliterators.a(((Object[]) Objects.requireNonNull(objArr)).length, 0, i10);
        return new j$.util.t0(objArr, 0, i10, 1040);
    }

    @Override // j$.util.stream.g2
    public final void k(Object[] objArr, int i10) {
        System.arraycopy(this.f18739a, 0, objArr, i10, this.f18740b);
    }

    @Override // j$.util.stream.g2
    public final Object[] m(IntFunction intFunction) {
        Object[] objArr = this.f18739a;
        if (objArr.length == this.f18740b) {
            return objArr;
        }
        throw new IllegalStateException();
    }

    @Override // j$.util.stream.g2
    public final long count() {
        return this.f18740b;
    }

    @Override // j$.util.stream.g2
    public final void forEach(Consumer consumer) {
        for (int i10 = 0; i10 < this.f18740b; i10++) {
            consumer.n(this.f18739a[i10]);
        }
    }

    public String toString() {
        Object[] objArr = this.f18739a;
        return String.format("ArrayNode[%d][%s]", Integer.valueOf(objArr.length - this.f18740b), Arrays.toString(objArr));
    }
}
