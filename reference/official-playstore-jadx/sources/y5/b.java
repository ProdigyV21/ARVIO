package y5;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f22875i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ua.a f22876l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f22877m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ c f22878n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f22879o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, f7.c cVar2) {
        super(cVar2);
        this.f22878n = cVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f22877m = obj;
        this.f22879o |= Integer.MIN_VALUE;
        return this.f22878n.b(this);
    }
}
