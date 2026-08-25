package j$.util.stream;

import j$.util.Spliterator;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j3 implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g2 f18741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Spliterator f18743c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Spliterator f18744d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Deque f18745e;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 64;
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

    public j3(g2 g2Var) {
        this.f18741a = g2Var;
    }

    public final Deque b() {
        ArrayDeque arrayDeque = new ArrayDeque(8);
        int iO = this.f18741a.o();
        while (true) {
            iO--;
            if (iO < this.f18742b) {
                return arrayDeque;
            }
            arrayDeque.addFirst(this.f18741a.a(iO));
        }
    }

    public static g2 a(Deque deque) {
        while (true) {
            ArrayDeque arrayDeque = (ArrayDeque) deque;
            g2 g2Var = (g2) arrayDeque.pollFirst();
            if (g2Var == null) {
                return null;
            }
            if (g2Var.o() != 0) {
                for (int iO = g2Var.o() - 1; iO >= 0; iO--) {
                    arrayDeque.addFirst(g2Var.a(iO));
                }
            } else if (g2Var.count() > 0) {
                return g2Var;
            }
        }
    }

    public final boolean c() {
        if (this.f18741a == null) {
            return false;
        }
        if (this.f18744d != null) {
            return true;
        }
        Spliterator spliterator = this.f18743c;
        if (spliterator == null) {
            Deque dequeB = b();
            this.f18745e = dequeB;
            g2 g2VarA = a(dequeB);
            if (g2VarA != null) {
                this.f18744d = g2VarA.spliterator();
                return true;
            }
            this.f18741a = null;
            return false;
        }
        this.f18744d = spliterator;
        return true;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        g2 g2Var = this.f18741a;
        if (g2Var == null || this.f18744d != null) {
            return null;
        }
        Spliterator spliterator = this.f18743c;
        if (spliterator != null) {
            return spliterator.trySplit();
        }
        if (this.f18742b < g2Var.o() - 1) {
            g2 g2Var2 = this.f18741a;
            int i10 = this.f18742b;
            this.f18742b = i10 + 1;
            return g2Var2.a(i10).spliterator();
        }
        g2 g2VarA = this.f18741a.a(this.f18742b);
        this.f18741a = g2VarA;
        if (g2VarA.o() == 0) {
            Spliterator spliterator2 = this.f18741a.spliterator();
            this.f18743c = spliterator2;
            return spliterator2.trySplit();
        }
        g2 g2Var3 = this.f18741a;
        this.f18742b = 1;
        return g2Var3.a(0).spliterator();
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        long jCount = 0;
        if (this.f18741a == null) {
            return 0L;
        }
        Spliterator spliterator = this.f18743c;
        if (spliterator != null) {
            return spliterator.estimateSize();
        }
        for (int i10 = this.f18742b; i10 < this.f18741a.o(); i10++) {
            jCount += this.f18741a.a(i10).count();
        }
        return jCount;
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.n0 trySplit() {
        return (j$.util.n0) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.h0 trySplit() {
        return (j$.util.h0) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.k0 trySplit() {
        return (j$.util.k0) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.e0 trySplit() {
        return (j$.util.e0) trySplit();
    }
}
