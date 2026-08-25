package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.LongFunction;

/* JADX INFO: loaded from: classes2.dex */
public class m2 extends d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v3 f18782h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LongFunction f18783i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final BinaryOperator f18784j;

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d dVar = this.f18659d;
        if (dVar != null) {
            this.f18661f = (g2) this.f18784j.apply((g2) ((m2) dVar).f18661f, (g2) ((m2) this.f18660e).f18661f);
        }
        super.onCompletion(countedCompleter);
    }

    public m2(v3 v3Var, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator) {
        super(v3Var, spliterator);
        this.f18782h = v3Var;
        this.f18783i = longFunction;
        this.f18784j = binaryOperator;
    }

    public m2(m2 m2Var, Spliterator spliterator) {
        super(m2Var, spliterator);
        this.f18782h = m2Var.f18782h;
        this.f18783i = m2Var.f18783i;
        this.f18784j = m2Var.f18784j;
    }

    @Override // j$.util.stream.d
    public d c(Spliterator spliterator) {
        return new m2(this, spliterator);
    }

    @Override // j$.util.stream.d
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final g2 a() {
        y1 y1Var = (y1) this.f18783i.apply(this.f18782h.k0(this.f18657b));
        this.f18782h.E0(this.f18657b, y1Var);
        return y1Var.build();
    }
}
