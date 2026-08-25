package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public final class s0 extends CountedCompleter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Spliterator f18861a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l5 f18862b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v3 f18863c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f18864d;

    public s0(v3 v3Var, Spliterator spliterator, l5 l5Var) {
        super(null);
        this.f18862b = l5Var;
        this.f18863c = v3Var;
        this.f18861a = spliterator;
        this.f18864d = 0L;
    }

    public s0(s0 s0Var, Spliterator spliterator) {
        super(s0Var);
        this.f18861a = spliterator;
        this.f18862b = s0Var.f18862b;
        this.f18864d = s0Var.f18864d;
        this.f18863c = s0Var.f18863c;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.f18861a;
        long jEstimateSize = spliterator.estimateSize();
        long jE = this.f18864d;
        if (jE == 0) {
            jE = d.e(jEstimateSize);
            this.f18864d = jE;
        }
        boolean zR = y6.SHORT_CIRCUIT.r(((a) this.f18863c).f18607m);
        l5 l5Var = this.f18862b;
        boolean z = false;
        s0 s0Var = this;
        while (true) {
            if (zR && l5Var.f()) {
                break;
            }
            if (jEstimateSize <= jE || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                break;
            }
            s0 s0Var2 = new s0(s0Var, spliteratorTrySplit);
            s0Var.addToPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
            } else {
                s0 s0Var3 = s0Var;
                s0Var = s0Var2;
                s0Var2 = s0Var3;
            }
            z = !z;
            s0Var.fork();
            s0Var = s0Var2;
            jEstimateSize = spliterator.estimateSize();
        }
        s0Var.f18863c.f0(spliterator, l5Var);
        s0Var.f18861a = null;
        s0Var.propagateCompletion();
    }
}
