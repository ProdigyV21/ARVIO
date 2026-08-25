package g8;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15505i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ androidx.emoji2.text.e0 f15506l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g0(androidx.emoji2.text.e0 e0Var, int i10) {
        super(1);
        this.f15505i = i10;
        this.f15506l = e0Var;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        g gVarV;
        switch (this.f15505i) {
            case 0:
                e0 e0Var = (e0) obj;
                f9.b bVar = e0Var.f15500a;
                List list = e0Var.f15501b;
                if (bVar.f15357c) {
                    throw new UnsupportedOperationException("Unresolved local class: " + bVar);
                }
                f9.b bVarF = bVar.f();
                androidx.emoji2.text.e0 e0Var2 = this.f15506l;
                if (bVarF == null || (gVarV = e0Var2.v(bVarF, kotlin.collections.x.h0(list, 1))) == null) {
                    gVarV = (g) ((u9.q) e0Var2.f2602n).invoke(bVar.g());
                }
                g gVar = gVarV;
                boolean z = !bVar.f15356b.e().d();
                u9.p pVar = (u9.p) e0Var2.f2600l;
                f9.f fVarI = bVar.i();
                Integer num = (Integer) kotlin.collections.x.o0(list);
                return new f0(pVar, gVar, fVarI, z, num != null ? num.intValue() : 0);
            default:
                return new f8.j((b0) this.f15506l.f2601m, (f9.c) obj, 1);
        }
    }
}
