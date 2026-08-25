package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m6 implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f18791a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18792b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f18793c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18794d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f18795e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ v6 f18796f;

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

    public m6(v6 v6Var, int i10, int i11, int i12, int i13) {
        this.f18796f = v6Var;
        this.f18791a = i10;
        this.f18792b = i11;
        this.f18793c = i12;
        this.f18794d = i13;
        Object[][] objArr = v6Var.f18931f;
        this.f18795e = objArr == null ? v6Var.f18930e : objArr[i10];
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        int i10 = this.f18791a;
        int i11 = this.f18794d;
        int i12 = this.f18792b;
        if (i10 == i12) {
            return ((long) i11) - ((long) this.f18793c);
        }
        long[] jArr = this.f18796f.f18648d;
        return ((jArr[i12] + ((long) i11)) - jArr[i10]) - ((long) this.f18793c);
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        int i10 = this.f18791a;
        int i11 = this.f18792b;
        if (i10 >= i11 && (i10 != i11 || this.f18793c >= this.f18794d)) {
            return false;
        }
        Object[] objArr = this.f18795e;
        int i12 = this.f18793c;
        this.f18793c = i12 + 1;
        consumer.n(objArr[i12]);
        if (this.f18793c == this.f18795e.length) {
            this.f18793c = 0;
            int i13 = this.f18791a + 1;
            this.f18791a = i13;
            Object[][] objArr2 = this.f18796f.f18931f;
            if (objArr2 != null && i13 <= i11) {
                this.f18795e = objArr2[i13];
            }
        }
        return true;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        v6 v6Var;
        Objects.requireNonNull(consumer);
        int i10 = this.f18791a;
        int i11 = this.f18794d;
        int i12 = this.f18792b;
        if (i10 < i12 || (i10 == i12 && this.f18793c < i11)) {
            int i13 = this.f18793c;
            while (true) {
                v6Var = this.f18796f;
                if (i10 >= i12) {
                    break;
                }
                Object[] objArr = v6Var.f18931f[i10];
                while (i13 < objArr.length) {
                    consumer.n(objArr[i13]);
                    i13++;
                }
                i10++;
                i13 = 0;
            }
            Object[] objArr2 = this.f18791a == i12 ? this.f18795e : v6Var.f18931f[i12];
            while (i13 < i11) {
                consumer.n(objArr2[i13]);
                i13++;
            }
            this.f18791a = i12;
            this.f18793c = i11;
        }
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        int i10 = this.f18791a;
        int i11 = this.f18792b;
        if (i10 < i11) {
            int i12 = i11 - 1;
            int i13 = this.f18793c;
            v6 v6Var = this.f18796f;
            m6 m6Var = new m6(v6Var, i10, i12, i13, v6Var.f18931f[i12].length);
            this.f18791a = i11;
            this.f18793c = 0;
            this.f18795e = v6Var.f18931f[i11];
            return m6Var;
        }
        if (i10 != i11) {
            return null;
        }
        int i14 = this.f18793c;
        int i15 = (this.f18794d - i14) / 2;
        if (i15 == 0) {
            return null;
        }
        Object[] objArr = this.f18795e;
        int i16 = i14 + i15;
        Spliterators.a(((Object[]) Objects.requireNonNull(objArr)).length, i14, i16);
        j$.util.t0 t0Var = new j$.util.t0(objArr, i14, i16, 1040);
        this.f18793c += i15;
        return t0Var;
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }
}
