package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class r {
    private AtomicReference<Object> internalScopeRef = new AtomicReference<>();

    public abstract void addObserver(x xVar);

    public abstract q getCurrentState();

    public na.h1<q> getCurrentStateFlow() {
        na.j1 j1VarB = na.y0.b(getCurrentState());
        addObserver(new m(j1VarB, 0));
        return na.y0.e(j1VarB);
    }

    public final AtomicReference<Object> getInternalScopeRef() {
        return this.internalScopeRef;
    }

    public abstract void removeObserver(x xVar);

    public final void setInternalScopeRef(AtomicReference<Object> atomicReference) {
        this.internalScopeRef = atomicReference;
    }
}
