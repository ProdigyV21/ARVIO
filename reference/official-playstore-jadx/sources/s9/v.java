package s9;

import a9.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21754i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x f21755l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t0 f21756m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u f21757n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(x xVar, t0 t0Var, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u uVar, int i10) {
        super(0);
        this.f21754i = i10;
        this.f21755l = xVar;
        this.f21756m = t0Var;
        this.f21757n = uVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f21754i) {
            case 0:
                x xVar = this.f21755l;
                m mVar = xVar.f21764a;
                androidx.appcompat.view.menu.e eVarA = xVar.a(mVar.f21733c);
                return (k9.g) mVar.f21731a.f21710e.m(eVarA, this.f21756m, this.f21757n.getReturnType());
            case 1:
                x xVar2 = this.f21755l;
                u9.p pVar = xVar2.f21764a.f21731a.f21706a;
                v vVar = new v(xVar2, this.f21756m, this.f21757n, 0);
                pVar.getClass();
                return new u9.k(pVar, vVar);
            case 2:
                x xVar3 = this.f21755l;
                m mVar2 = xVar3.f21764a;
                androidx.appcompat.view.menu.e eVarA2 = xVar3.a(mVar2.f21733c);
                return (k9.g) mVar2.f21731a.f21710e.e(eVarA2, this.f21756m, this.f21757n.getReturnType());
            default:
                x xVar4 = this.f21755l;
                u9.p pVar2 = xVar4.f21764a.f21731a.f21706a;
                v vVar2 = new v(xVar4, this.f21756m, this.f21757n, 2);
                pVar2.getClass();
                return new u9.k(pVar2, vVar2);
        }
    }
}
