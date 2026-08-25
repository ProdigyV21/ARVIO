package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.MutatePriority;

/* JADX INFO: loaded from: classes3.dex */
public final class j0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m0 f5401i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public MutatePriority f5402l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public r7.p f5403m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f5404n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ m0 f5405o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5406p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(m0 m0Var, d7.d dVar) {
        super(dVar);
        this.f5405o = m0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f5404n = obj;
        this.f5406p |= Integer.MIN_VALUE;
        return this.f5405o.scroll(null, null, this);
    }
}
