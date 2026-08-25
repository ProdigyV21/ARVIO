package ma;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20309i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f20310l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20311m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(f fVar, f7.c cVar) {
        super(cVar);
        this.f20310l = fVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20309i = obj;
        this.f20311m |= Integer.MIN_VALUE;
        Object objX = this.f20310l.x(null, 0, 0L, this);
        return objX == e7.a.f15033i ? objX : new o(objX);
    }
}
