package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5806i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ int f5807l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a0(int i10, int i11, d7.d dVar) {
        super(i10, dVar);
        this.f5806i = i11;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f5806i) {
            case 0:
                a0 a0Var = new a0(2, 0, dVar);
                a0Var.f5807l = ((Number) obj).intValue();
                return a0Var;
            default:
                a0 a0Var2 = new a0(2, 1, dVar);
                a0Var2.f5807l = ((Number) obj).intValue();
                return a0Var2;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f5806i;
        int iIntValue = ((Number) obj).intValue();
        d7.d dVar = (d7.d) obj2;
        switch (i10) {
        }
        return ((a0) create(Integer.valueOf(iIntValue), dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f5806i) {
            case 0:
                k2.c.G(obj);
                return Boolean.valueOf(this.f5807l == 0);
            default:
                k2.c.G(obj);
                return Boolean.valueOf(this.f5807l > 0);
        }
    }
}
