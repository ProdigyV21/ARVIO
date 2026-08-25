package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class c8 extends a7 {
    @Override // j$.util.stream.a7
    public final a7 e(Spliterator spliterator) {
        return new c8(this.f18618b, spliterator, this.f18617a);
    }

    @Override // j$.util.stream.a7
    public final void d() {
        v6 v6Var = new v6();
        this.f18624h = v6Var;
        Objects.requireNonNull(v6Var);
        this.f18621e = this.f18618b.F0(new b8(v6Var, 0));
        this.f18622f = new j$.util.function.i(this, 12);
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        Object obj;
        Objects.requireNonNull(consumer);
        boolean zA = a();
        if (!zA) {
            return zA;
        }
        v6 v6Var = (v6) this.f18624h;
        long j10 = this.f18623g;
        if (v6Var.f18647c != 0) {
            if (j10 >= v6Var.count()) {
                throw new IndexOutOfBoundsException(Long.toString(j10));
            }
            for (int i10 = 0; i10 <= v6Var.f18647c; i10++) {
                long j11 = v6Var.f18648d[i10];
                Object[] objArr = v6Var.f18931f[i10];
                if (j10 < ((long) objArr.length) + j11) {
                    obj = objArr[(int) (j10 - j11)];
                }
            }
            throw new IndexOutOfBoundsException(Long.toString(j10));
        }
        if (j10 < v6Var.f18646b) {
            obj = v6Var.f18930e[(int) j10];
        } else {
            throw new IndexOutOfBoundsException(Long.toString(j10));
        }
        consumer.n(obj);
        return zA;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        if (this.f18624h == null && !this.f18625i) {
            Objects.requireNonNull(consumer);
            c();
            Objects.requireNonNull(consumer);
            b8 b8Var = new b8(consumer, 1);
            this.f18618b.E0(this.f18620d, b8Var);
            this.f18625i = true;
            return;
        }
        while (tryAdvance(consumer)) {
        }
    }
}
