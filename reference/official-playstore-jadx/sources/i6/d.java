package i6;

import f7.j;
import fi.iki.elonen.f;
import ka.k0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends j implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16233i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f16234l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f16235m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(f fVar, String str, d7.d dVar, int i10) {
        super(2, dVar);
        this.f16233i = i10;
        this.f16234l = fVar;
        this.f16235m = str;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f16233i) {
            case 0:
                return new d(this.f16234l, this.f16235m, dVar, 0);
            default:
                return new d(this.f16234l, this.f16235m, dVar, 1);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        k0 k0Var = (k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f16233i) {
            case 0:
                return ((d) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            default:
                d dVar2 = (d) create(k0Var, dVar);
                t0 t0Var = t0.f22605a;
                dVar2.invokeSuspend(t0Var);
                return t0Var;
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f16233i) {
            case 0:
                k2.c.G(obj);
                return ((h6.d) this.f16234l.f15416i).g(this.f16235m);
            default:
                k2.c.G(obj);
                ((h6.d) this.f16234l.f15416i).remove(this.f16235m);
                return t0.f22605a;
        }
    }
}
