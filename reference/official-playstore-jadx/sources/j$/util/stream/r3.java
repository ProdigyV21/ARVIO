package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class r3 extends CountedCompleter implements l5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Spliterator f18848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v3 f18849b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f18850c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f18851d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f18852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f18853f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f18854g;

    public abstract r3 a(Spliterator spliterator, long j10, long j11);

    public /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    public /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    public /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    public r3(Spliterator spliterator, v3 v3Var, int i10) {
        this.f18848a = spliterator;
        this.f18849b = v3Var;
        this.f18850c = d.e(spliterator.estimateSize());
        this.f18851d = 0L;
        this.f18852e = i10;
    }

    public r3(r3 r3Var, Spliterator spliterator, long j10, long j11, int i10) {
        super(r3Var);
        this.f18848a = spliterator;
        this.f18849b = r3Var.f18849b;
        this.f18850c = r3Var.f18850c;
        this.f18851d = j10;
        this.f18852e = j11;
        if (j10 < 0 || j11 < 0 || (j10 + j11) - 1 >= i10) {
            throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j10), Long.valueOf(j10), Long.valueOf(j11), Integer.valueOf(i10)));
        }
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.f18848a;
        r3 r3VarA = this;
        while (spliterator.estimateSize() > r3VarA.f18850c && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            r3VarA.setPendingCount(1);
            long jEstimateSize = spliteratorTrySplit.estimateSize();
            r3 r3Var = r3VarA;
            r3Var.a(spliteratorTrySplit, r3VarA.f18851d, jEstimateSize).fork();
            r3VarA = r3Var.a(spliterator, r3Var.f18851d + jEstimateSize, r3Var.f18852e - jEstimateSize);
        }
        r3 r3Var2 = r3VarA;
        r3Var2.f18849b.E0(spliterator, r3Var2);
        r3Var2.propagateCompletion();
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        long j11 = this.f18852e;
        if (j10 > j11) {
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }
        int i10 = (int) this.f18851d;
        this.f18853f = i10;
        this.f18854g = i10 + ((int) j11);
    }
}
