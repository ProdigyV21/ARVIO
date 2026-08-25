package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public final class r0 extends CountedCompleter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v3 f18841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Spliterator f18842b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f18843c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ConcurrentHashMap f18844d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q0 f18845e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r0 f18846f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g2 f18847g;

    public r0(v3 v3Var, Spliterator spliterator, q0 q0Var) {
        super(null);
        this.f18841a = v3Var;
        this.f18842b = spliterator;
        this.f18843c = d.e(spliterator.estimateSize());
        this.f18844d = new ConcurrentHashMap(Math.max(16, d.f18655g << 1));
        this.f18845e = q0Var;
        this.f18846f = null;
    }

    public r0(r0 r0Var, Spliterator spliterator, r0 r0Var2) {
        super(r0Var);
        this.f18841a = r0Var.f18841a;
        this.f18842b = spliterator;
        this.f18843c = r0Var.f18843c;
        this.f18844d = r0Var.f18844d;
        this.f18845e = r0Var.f18845e;
        this.f18846f = r0Var2;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.f18842b;
        long j10 = this.f18843c;
        boolean z = false;
        r0 r0Var = this;
        while (spliterator.estimateSize() > j10 && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            r0 r0Var2 = new r0(r0Var, spliteratorTrySplit, r0Var.f18846f);
            r0 r0Var3 = new r0(r0Var, spliterator, r0Var2);
            r0Var.addToPendingCount(1);
            r0Var3.addToPendingCount(1);
            r0Var.f18844d.put(r0Var2, r0Var3);
            if (r0Var.f18846f != null) {
                r0Var2.addToPendingCount(1);
                if (r0Var.f18844d.replace(r0Var.f18846f, r0Var, r0Var2)) {
                    r0Var.addToPendingCount(-1);
                } else {
                    r0Var2.addToPendingCount(-1);
                }
            }
            if (z) {
                spliterator = spliteratorTrySplit;
                r0Var = r0Var2;
                r0Var2 = r0Var3;
            } else {
                r0Var = r0Var3;
            }
            z = !z;
            r0Var2.fork();
        }
        if (r0Var.getPendingCount() > 0) {
            o oVar = new o(18);
            v3 v3Var = r0Var.f18841a;
            y1 y1VarA0 = v3Var.A0(v3Var.k0(spliterator), oVar);
            r0Var.f18841a.E0(spliterator, y1VarA0);
            r0Var.f18847g = y1VarA0.build();
            r0Var.f18842b = null;
        }
        r0Var.tryComplete();
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        g2 g2Var = this.f18847g;
        if (g2Var != null) {
            g2Var.forEach(this.f18845e);
            this.f18847g = null;
        } else {
            Spliterator spliterator = this.f18842b;
            if (spliterator != null) {
                this.f18841a.E0(spliterator, this.f18845e);
                this.f18842b = null;
            }
        }
        r0 r0Var = (r0) this.f18844d.remove(this);
        if (r0Var != null) {
            r0Var.tryComplete();
        }
    }
}
