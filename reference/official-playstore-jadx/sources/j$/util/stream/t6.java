package j$.util.stream;

import j$.util.Objects;
import java.util.Comparator;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t6 implements j$.util.n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f18884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18885b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f18886c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f18888e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u6 f18889f;

    public abstract void a(int i10, Object obj, Object obj2);

    public abstract j$.util.n0 b(Object obj, int i10, int i11);

    public abstract j$.util.n0 c(int i10, int i11, int i12, int i13);

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 16464;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    public t6(u6 u6Var, int i10, int i11, int i12, int i13) {
        this.f18889f = u6Var;
        this.f18884a = i10;
        this.f18885b = i11;
        this.f18886c = i12;
        this.f18887d = i13;
        Object[] objArr = u6Var.f18910f;
        this.f18888e = objArr == null ? u6Var.f18909e : objArr[i10];
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        int i10 = this.f18884a;
        int i11 = this.f18887d;
        int i12 = this.f18885b;
        if (i10 == i12) {
            return ((long) i11) - ((long) this.f18886c);
        }
        long[] jArr = this.f18889f.f18648d;
        return ((jArr[i12] + ((long) i11)) - jArr[i10]) - ((long) this.f18886c);
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(Object obj) {
        Objects.requireNonNull(obj);
        int i10 = this.f18884a;
        int i11 = this.f18885b;
        if (i10 >= i11 && (i10 != i11 || this.f18886c >= this.f18887d)) {
            return false;
        }
        Object obj2 = this.f18888e;
        int i12 = this.f18886c;
        this.f18886c = i12 + 1;
        a(i12, obj2, obj);
        int i13 = this.f18886c;
        Object obj3 = this.f18888e;
        u6 u6Var = this.f18889f;
        if (i13 == u6Var.q(obj3)) {
            this.f18886c = 0;
            int i14 = this.f18884a + 1;
            this.f18884a = i14;
            Object[] objArr = u6Var.f18910f;
            if (objArr != null && i14 <= i11) {
                this.f18888e = objArr[i14];
            }
        }
        return true;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(Object obj) {
        u6 u6Var;
        Objects.requireNonNull(obj);
        int i10 = this.f18884a;
        int i11 = this.f18887d;
        int i12 = this.f18885b;
        if (i10 < i12 || (i10 == i12 && this.f18886c < i11)) {
            int i13 = this.f18886c;
            while (true) {
                u6Var = this.f18889f;
                if (i10 >= i12) {
                    break;
                }
                Object obj2 = u6Var.f18910f[i10];
                u6Var.p(obj2, i13, u6Var.q(obj2), obj);
                i10++;
                i13 = 0;
            }
            u6Var.p(this.f18884a == i12 ? this.f18888e : u6Var.f18910f[i12], i13, i11, obj);
            this.f18884a = i12;
            this.f18886c = i11;
        }
    }

    @Override // j$.util.Spliterator
    public final j$.util.n0 trySplit() {
        int i10 = this.f18884a;
        int i11 = this.f18885b;
        if (i10 < i11) {
            int i12 = i11 - 1;
            int i13 = this.f18886c;
            u6 u6Var = this.f18889f;
            j$.util.n0 n0VarC = c(i10, i12, i13, u6Var.q(u6Var.f18910f[i12]));
            this.f18884a = i11;
            this.f18886c = 0;
            this.f18888e = u6Var.f18910f[i11];
            return n0VarC;
        }
        if (i10 != i11) {
            return null;
        }
        int i14 = this.f18886c;
        int i15 = (this.f18887d - i14) / 2;
        if (i15 == 0) {
            return null;
        }
        j$.util.n0 n0VarB = b(this.f18888e, i14, i15);
        this.f18886c += i15;
        return n0VarB;
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        forEachRemaining((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
        return tryAdvance((Object) intConsumer);
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.h0 trySplit() {
        return (j$.util.h0) trySplit();
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        forEachRemaining((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
        return tryAdvance((Object) longConsumer);
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.k0 trySplit() {
        return (j$.util.k0) trySplit();
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        forEachRemaining((Object) doubleConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
        return tryAdvance((Object) doubleConsumer);
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.e0 trySplit() {
        return (j$.util.e0) trySplit();
    }
}
