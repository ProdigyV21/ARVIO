package ka;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends y1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19559o = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "_disposer");

    @q7.w
    private volatile Object _disposer;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final l f19560l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public z0 f19561m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ e f19562n;

    public c(e eVar, l lVar) {
        this.f19562n = eVar;
        this.f19560l = lVar;
    }

    @Override // r7.l
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return x6.t0.f22605a;
    }

    @Override // ka.y1
    public final void invoke(Throwable th) {
        l lVar = this.f19560l;
        if (th != null) {
            lVar.getClass();
            c2.a aVarC = lVar.C(new v(th, false), null);
            if (aVarC != null) {
                lVar.f(aVarC);
                d dVar = (d) f19559o.get(this);
                if (dVar != null) {
                    dVar.b();
                    return;
                }
                return;
            }
            return;
        }
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = e.f19578b;
        e eVar = this.f19562n;
        if (atomicIntegerFieldUpdater.decrementAndGet(eVar) == 0) {
            p0[] p0VarArr = eVar.f19579a;
            ArrayList arrayList = new ArrayList(p0VarArr.length);
            for (p0 p0Var : p0VarArr) {
                arrayList.add(p0Var.k());
            }
            lVar.resumeWith(arrayList);
        }
    }
}
