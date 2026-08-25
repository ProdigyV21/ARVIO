package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f0 f22416i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f22417l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f0 f22418m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22419n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(f0 f0Var, f7.c cVar) {
        super(cVar);
        this.f22418m = f0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f22417l = obj;
        this.f22419n |= Integer.MIN_VALUE;
        return f0.c(this.f22418m, this);
    }
}
