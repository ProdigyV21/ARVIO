package f7;

import kotlin.jvm.internal.k;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h extends g implements k, i {
    private final int arity;

    public h(int i10, d7.d dVar) {
        super(dVar);
        this.arity = i10;
    }

    @Override // kotlin.jvm.internal.k
    public int getArity() {
        return this.arity;
    }

    @Override // f7.a
    public String toString() {
        return getCompletion() == null ? l0.f19747a.i(this) : super.toString();
    }
}
