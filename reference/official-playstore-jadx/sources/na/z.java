package na;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20692i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20693l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s f20694m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.work.impl.constraints.k f20695n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(s sVar, d7.d dVar) {
        super(dVar);
        this.f20694m = sVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20692i = obj;
        this.f20693l |= Integer.MIN_VALUE;
        return this.f20694m.collect(null, this);
    }
}
