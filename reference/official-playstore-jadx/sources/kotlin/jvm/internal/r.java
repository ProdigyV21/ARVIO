package kotlin.jvm.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r implements k, Serializable {
    private final int arity;

    public r(int i10) {
        this.arity = i10;
    }

    @Override // kotlin.jvm.internal.k
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        return l0.f19747a.j(this);
    }
}
