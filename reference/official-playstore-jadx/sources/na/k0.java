package na;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20601i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20602l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l0 f20603m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(l0 l0Var, d7.d dVar) {
        super(dVar);
        this.f20603m = l0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20601i = obj;
        this.f20602l |= Integer.MIN_VALUE;
        return this.f20603m.emit(null, this);
    }
}
