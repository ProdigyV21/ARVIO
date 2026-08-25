package z0;

import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes3.dex */
public final class s extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f23115i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f23116l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k0 f23118n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a0 f23119o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f23120p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ t f23121q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f23122r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(t tVar, f7.c cVar) {
        super(cVar);
        this.f23121q = tVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23120p = obj;
        this.f23122r |= Integer.MIN_VALUE;
        return this.f23121q.a(null, this);
    }
}
