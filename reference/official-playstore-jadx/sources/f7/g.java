package f7;

import d7.k;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g extends a {
    public g(d7.d dVar) {
        super(dVar);
        if (dVar != null && dVar.getContext() != k.f14688i) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // d7.d
    public d7.j getContext() {
        return k.f14688i;
    }
}
