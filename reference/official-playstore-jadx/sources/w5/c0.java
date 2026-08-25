package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f22391i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f0 f22392l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22393m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(f0 f0Var, f7.c cVar) {
        super(cVar);
        this.f22392l = f0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f22391i = obj;
        this.f22393m |= Integer.MIN_VALUE;
        return f0.b(this.f22392l, this);
    }
}
