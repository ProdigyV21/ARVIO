package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends CountedCompleter {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f18655g = ForkJoinPool.getCommonPoolParallelism() << 2;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v3 f18656a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Spliterator f18657b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f18658c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f18659d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f18660e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f18661f;

    public abstract Object a();

    public abstract d c(Spliterator spliterator);

    public d(v3 v3Var, Spliterator spliterator) {
        super(null);
        this.f18656a = v3Var;
        this.f18657b = spliterator;
        this.f18658c = 0L;
    }

    public d(d dVar, Spliterator spliterator) {
        super(dVar);
        this.f18657b = spliterator;
        this.f18656a = dVar.f18656a;
        this.f18658c = dVar.f18658c;
    }

    public static long e(long j10) {
        long j11 = j10 / ((long) f18655g);
        if (j11 > 0) {
            return j11;
        }
        return 1L;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return this.f18661f;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    public void d(Object obj) {
        this.f18661f = obj;
    }

    public final boolean b() {
        return ((d) getCompleter()) == null;
    }

    @Override // java.util.concurrent.CountedCompleter
    public void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.f18657b;
        long jEstimateSize = spliterator.estimateSize();
        long jE = this.f18658c;
        if (jE == 0) {
            jE = e(jEstimateSize);
            this.f18658c = jE;
        }
        boolean z = false;
        d dVar = this;
        while (jEstimateSize > jE && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            d dVarC = dVar.c(spliteratorTrySplit);
            dVar.f18659d = dVarC;
            d dVarC2 = dVar.c(spliterator);
            dVar.f18660e = dVarC2;
            dVar.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                dVar = dVarC;
                dVarC = dVarC2;
            } else {
                dVar = dVarC2;
            }
            z = !z;
            dVarC.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        dVar.d(dVar.a());
        dVar.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter countedCompleter) {
        this.f18657b = null;
        this.f18660e = null;
        this.f18659d = null;
    }
}
