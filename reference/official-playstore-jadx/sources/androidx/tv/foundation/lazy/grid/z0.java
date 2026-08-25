package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.MutatePriority;

/* JADX INFO: loaded from: classes3.dex */
public final class z0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b1 f5343i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public MutatePriority f5344l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public r7.p f5345m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f5346n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ b1 f5347o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5348p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(b1 b1Var, d7.d dVar) {
        super(dVar);
        this.f5347o = b1Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f5346n = obj;
        this.f5348p |= Integer.MIN_VALUE;
        return this.f5347o.scroll(null, null, this);
    }
}
