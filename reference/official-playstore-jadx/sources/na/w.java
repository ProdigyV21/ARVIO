package na;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20663i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x f20664l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20665m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(x xVar, d7.d dVar) {
        super(dVar);
        this.f20664l = xVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20663i = obj;
        this.f20665m |= Integer.MIN_VALUE;
        return this.f20664l.emit(null, this);
    }
}
