package x8;

import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22654i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f22655l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f22656m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(String str, String str2, int i10) {
        super(1);
        this.f22654i = i10;
        this.f22655l = str;
        this.f22656m = str2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f22654i;
        t0 t0Var = t0.f22605a;
        String str = this.f22656m;
        String str2 = this.f22655l;
        switch (i10) {
            case 0:
                n nVar = (n) obj;
                d dVar = j.f22658b;
                nVar.a(str2, dVar);
                d dVar2 = j.f22657a;
                nVar.a(str, dVar, dVar, dVar2, dVar2);
                nVar.b(str2, dVar2);
                break;
            case 1:
                n nVar2 = (n) obj;
                d dVar3 = j.f22658b;
                nVar2.a(str2, dVar3);
                nVar2.a(str, dVar3, dVar3, dVar3);
                nVar2.b(str2, dVar3);
                break;
            case 2:
                n nVar3 = (n) obj;
                d dVar4 = j.f22658b;
                nVar3.a(str2, dVar4);
                d dVar5 = j.f22657a;
                nVar3.a(str, dVar4, dVar4, j.f22659c, dVar5);
                nVar3.b(str2, dVar5);
                break;
            case 3:
                n nVar4 = (n) obj;
                d dVar6 = j.f22658b;
                nVar4.a(str2, dVar6);
                d dVar7 = j.f22659c;
                nVar4.a(str2, dVar7);
                d dVar8 = j.f22657a;
                nVar4.a(str, dVar6, dVar7, dVar7, dVar8);
                nVar4.b(str2, dVar8);
                break;
            case 4:
                n nVar5 = (n) obj;
                d dVar9 = j.f22659c;
                nVar5.a(str2, dVar9);
                nVar5.b(str, j.f22658b, dVar9);
                break;
            default:
                n nVar6 = (n) obj;
                nVar6.a(str2, j.f22657a);
                nVar6.b(str, j.f22658b, j.f22659c);
                break;
        }
        return t0Var;
    }
}
