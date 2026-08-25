package ka;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public class x1 extends f2 implements t {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f19656m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x1(v1 v1Var) {
        super(true);
        boolean z = true;
        B(v1Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2.f19588l;
        o oVar = (o) atomicReferenceFieldUpdater.get(this);
        p pVar = oVar instanceof p ? (p) oVar : null;
        if (pVar == null) {
            z = false;
            break;
        }
        f2 f2VarC = pVar.c();
        while (!f2VarC.v()) {
            o oVar2 = (o) atomicReferenceFieldUpdater.get(f2VarC);
            p pVar2 = oVar2 instanceof p ? (p) oVar2 : null;
            if (pVar2 == null) {
                z = false;
                break;
            }
            f2VarC = pVar2.c();
        }
        this.f19656m = z;
    }

    @Override // ka.t
    public final boolean complete() {
        return D(x6.t0.f22605a);
    }

    @Override // ka.t
    public final boolean i(Throwable th) {
        return D(new v(th, false));
    }

    @Override // ka.f2
    public final boolean v() {
        return this.f19656m;
    }

    @Override // ka.f2
    public final boolean w() {
        return true;
    }
}
