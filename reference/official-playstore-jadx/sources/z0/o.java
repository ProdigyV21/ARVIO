package z0;

import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f23093i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f23094l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23095m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ a0 f23096n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(a0 a0Var, d7.d dVar, int i10) {
        super(2, dVar);
        this.f23093i = i10;
        this.f23096n = a0Var;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f23093i) {
            case 0:
                o oVar = new o(this.f23096n, dVar, 0);
                oVar.f23095m = obj;
                return oVar;
            default:
                o oVar2 = new o(this.f23096n, dVar, 1);
                oVar2.f23095m = obj;
                return oVar2;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f23093i) {
            case 0:
                return ((o) create((n) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            default:
                return ((o) create((na.k) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.o.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
