package s9;

import a9.k1;
import g8.a1;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21687i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ g0 f21688l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(g0 g0Var, int i10) {
        super(1);
        this.f21687i = i10;
        this.f21688l = g0Var;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f21687i) {
            case 0:
                int iIntValue = ((Number) obj).intValue();
                m mVar = this.f21688l.f21697a;
                c9.f fVar = mVar.f21732b;
                j jVar = mVar.f21731a;
                f9.b bVarR = t7.a.r(fVar, iIntValue);
                return bVarR.f15357c ? (g8.f) jVar.f21724t.f21696b.invoke(new f(bVarR, null)) : g8.y.e(jVar.f21707b, bVarR);
            case 1:
                int iIntValue2 = ((Number) obj).intValue();
                m mVar2 = this.f21688l.f21697a;
                f9.b bVarR2 = t7.a.r(mVar2.f21732b, iIntValue2);
                if (!bVarR2.f15357c) {
                    g8.h hVarE = g8.y.e(mVar2.f21731a.f21707b, bVarR2);
                    if (hVarE instanceof a1) {
                        return (a1) hVarE;
                    }
                }
                return null;
            default:
                k1 k1Var = (k1) obj;
                androidx.work.impl.constraints.j jVar2 = this.f21688l.f21697a.f21734d;
                int i10 = k1Var.f520m;
                if ((i10 & 256) == 256) {
                    return k1Var.f530w;
                }
                if ((i10 & 512) == 512) {
                    return jVar2.b(k1Var.x);
                }
                return null;
        }
    }
}
