package ka;

import d7.j;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ kotlin.jvm.internal.k0 f19542i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f19543l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(kotlin.jvm.internal.k0 k0Var, boolean z) {
        super(2);
        this.f19542i = k0Var;
        this.f19543l = z;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        d7.j jVar = (d7.j) obj;
        j.a aVar = (j.a) obj2;
        if (!(aVar instanceof x)) {
            return jVar.plus(aVar);
        }
        kotlin.jvm.internal.k0 k0Var = this.f19542i;
        if (((d7.j) k0Var.f19746i).get(aVar.getKey()) != null) {
            k0Var.f19746i = ((d7.j) k0Var.f19746i).minusKey(aVar.getKey());
            return jVar.plus(((x) aVar).V());
        }
        x xVarG = (x) aVar;
        if (this.f19543l) {
            xVarG = xVarG.G();
        }
        return jVar.plus(xVarG);
    }
}
