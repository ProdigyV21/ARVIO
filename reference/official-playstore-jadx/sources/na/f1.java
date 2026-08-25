package na;

import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public final class f1 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20564i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f20565l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f1(int i10, int i11, d7.d dVar) {
        super(i10, dVar);
        this.f20564i = i11;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f20564i) {
            case 0:
                f1 f1Var = new f1(2, 0, dVar);
                f1Var.f20565l = obj;
                return f1Var;
            default:
                f1 f1Var2 = new f1(2, 1, dVar);
                f1Var2.f20565l = obj;
                return f1Var2;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f20564i) {
            case 0:
                return ((f1) create((a1) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            default:
                f1 f1Var = (f1) create((String) obj, (d7.d) obj2);
                x6.t0 t0Var = x6.t0.f22605a;
                f1Var.invokeSuspend(t0Var);
                return t0Var;
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f20564i) {
            case 0:
                k2.c.G(obj);
                return Boolean.valueOf(((a1) this.f20565l) != a1.f20525i);
            default:
                k2.c.G(obj);
                Log.e("SessionConfigFetcher", "Error failing to fetch the remote configs: " + ((String) this.f20565l));
                return x6.t0.f22605a;
        }
    }
}
