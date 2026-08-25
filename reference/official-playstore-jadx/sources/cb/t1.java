package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class t1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7563i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ eb.b f7564l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ya.c f7565m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7566n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t1(eb.b bVar, ya.c cVar, Object obj, int i10) {
        super(0);
        this.f7563i = i10;
        this.f7564l = bVar;
        this.f7565m = cVar;
        this.f7566n = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7563i) {
            case 0:
                eb.b bVar = this.f7564l;
                if (bVar.x()) {
                    bVar.getClass();
                    return eb.r.j(bVar, this.f7565m);
                }
                bVar.getClass();
                return null;
            default:
                eb.b bVar2 = this.f7564l;
                bVar2.getClass();
                return eb.r.j(bVar2, this.f7565m);
        }
    }
}
