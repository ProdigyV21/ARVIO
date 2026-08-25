package na;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public androidx.work.impl.constraints.k f20653i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20654l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ androidx.work.impl.constraints.k f20655m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20656n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(androidx.work.impl.constraints.k kVar, d7.d dVar) {
        super(dVar);
        this.f20655m = kVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20654l = obj;
        this.f20656n |= Integer.MIN_VALUE;
        return this.f20655m.emit(null, this);
    }
}
