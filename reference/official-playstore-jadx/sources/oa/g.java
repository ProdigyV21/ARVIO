package oa;

import ka.v1;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h f20818i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f20819l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public v1 f20820m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f20821n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ h f20822o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f20823p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(h hVar, d7.d dVar) {
        super(dVar);
        this.f20822o = hVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20821n = obj;
        this.f20823p |= Integer.MIN_VALUE;
        return this.f20822o.emit(null, this);
    }
}
