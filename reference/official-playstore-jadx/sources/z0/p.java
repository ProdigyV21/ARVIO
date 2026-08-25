package z0;

import na.l0;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f23097i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f23098l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l0 f23099m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(l0 l0Var, d7.d dVar) {
        super(dVar);
        this.f23099m = l0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23097i = obj;
        this.f23098l |= Integer.MIN_VALUE;
        return this.f23099m.emit(null, this);
    }
}
