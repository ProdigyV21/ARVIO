package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class f0 extends g0 implements w {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y f3228o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ h0 f3229p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f0(h0 h0Var, y yVar, k0 k0Var) {
        super(h0Var, k0Var);
        this.f3229p = h0Var;
        this.f3228o = yVar;
    }

    @Override // androidx.lifecycle.g0
    public final void b() {
        this.f3228o.getLifecycle().removeObserver(this);
    }

    @Override // androidx.lifecycle.g0
    public final boolean c(y yVar) {
        return this.f3228o == yVar;
    }

    @Override // androidx.lifecycle.g0
    public final boolean d() {
        return this.f3228o.getLifecycle().getCurrentState().a(q.f3269n);
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        y yVar2 = this.f3228o;
        q currentState = yVar2.getLifecycle().getCurrentState();
        if (currentState == q.f3266i) {
            this.f3229p.h(this.f3235i);
            return;
        }
        q qVar = null;
        while (qVar != currentState) {
            a(d());
            qVar = currentState;
            currentState = yVar2.getLifecycle().getCurrentState();
        }
    }
}
