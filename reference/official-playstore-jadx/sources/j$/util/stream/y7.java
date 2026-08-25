package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class y7 extends a8 implements Spliterator, Consumer {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f18984f;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
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

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.f18984f = obj;
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        while (e() != z7.NO_MORE && this.f18626a.tryAdvance(this)) {
            if (a(1L) == 1) {
                consumer.n(this.f18984f);
                this.f18984f = null;
                return true;
            }
        }
        return false;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        f7 f7Var = null;
        while (true) {
            z7 z7VarE = e();
            if (z7VarE == z7.NO_MORE) {
                return;
            }
            z7 z7Var = z7.MAYBE_MORE;
            Spliterator spliterator = this.f18626a;
            if (z7VarE == z7Var) {
                int i10 = this.f18628c;
                if (f7Var == null) {
                    f7Var = new f7(i10);
                } else {
                    f7Var.f18702a = 0;
                }
                long j10 = 0;
                while (spliterator.tryAdvance(f7Var)) {
                    j10++;
                    if (j10 >= i10) {
                        break;
                    }
                }
                if (j10 == 0) {
                    return;
                }
                long jA = a(j10);
                for (int i11 = 0; i11 < jA; i11++) {
                    consumer.n(f7Var.f18693b[i11]);
                }
            } else {
                spliterator.forEachRemaining(consumer);
                return;
            }
        }
    }

    @Override // j$.util.stream.a8
    public final Spliterator b(Spliterator spliterator) {
        return new y7(spliterator, this);
    }
}
