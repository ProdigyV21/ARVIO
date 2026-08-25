package ma;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20306i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f20307l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20308m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(f fVar, f7.c cVar) {
        super(cVar);
        this.f20307l = fVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20306i = obj;
        this.f20308m |= Integer.MIN_VALUE;
        Object objW = f.w(this.f20307l, this);
        return objW == e7.a.f15033i ? objW : new o(objW);
    }
}
