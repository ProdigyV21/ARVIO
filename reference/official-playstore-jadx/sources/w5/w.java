package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class w extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f22489i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f22490l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ na.l0 f22491m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(na.l0 l0Var, d7.d dVar) {
        super(dVar);
        this.f22491m = l0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f22489i = obj;
        this.f22490l |= Integer.MIN_VALUE;
        return this.f22491m.emit(null, this);
    }
}
