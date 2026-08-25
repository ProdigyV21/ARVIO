package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public final class x4 extends d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v3 f18950h;

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d dVar = this.f18659d;
        if (dVar != null) {
            q4 q4Var = (q4) ((x4) dVar).f18661f;
            q4Var.j((q4) ((x4) this.f18660e).f18661f);
            this.f18661f = q4Var;
        }
        super.onCompletion(countedCompleter);
    }

    public x4(v3 v3Var, v3 v3Var2, Spliterator spliterator) {
        super(v3Var2, spliterator);
        this.f18950h = v3Var;
    }

    public x4(x4 x4Var, Spliterator spliterator) {
        super(x4Var, spliterator);
        this.f18950h = x4Var.f18950h;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new x4(this, spliterator);
    }

    @Override // j$.util.stream.d
    public final Object a() {
        v3 v3Var = this.f18656a;
        q4 q4VarD0 = this.f18950h.D0();
        v3Var.E0(this.f18657b, q4VarD0);
        return q4VarD0;
    }
}
