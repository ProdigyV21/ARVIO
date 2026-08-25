package na;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f0 f20549i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20550l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20551m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f20552n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f20553o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(f0 f0Var, d7.d dVar) {
        super(dVar);
        this.f20552n = f0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20550l = obj;
        this.f20551m |= Integer.MIN_VALUE;
        return this.f20552n.emit(null, this);
    }
}
