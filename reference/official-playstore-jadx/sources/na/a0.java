package na;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public androidx.work.impl.constraints.k f20520i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20521l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20522m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ androidx.work.impl.constraints.k f20523n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f20524o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(androidx.work.impl.constraints.k kVar, d7.d dVar) {
        super(dVar);
        this.f20523n = kVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20521l = obj;
        this.f20522m |= Integer.MIN_VALUE;
        return this.f20523n.emit(null, this);
    }
}
