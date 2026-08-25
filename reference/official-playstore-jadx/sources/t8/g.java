package t8;

import g8.b1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21927i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ i f21928l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(i iVar, int i10) {
        super(0);
        this.f21927i = i10;
        this.f21928l = iVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f21927i) {
            case 0:
                return g8.y.c(this.f21928l);
            case 1:
                i iVar = this.f21928l;
                w8.g gVar = iVar.f21930r;
                ArrayList<w8.x> typeParameters = gVar.getTypeParameters();
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(typeParameters, 10));
                for (w8.x xVar : typeParameters) {
                    b1 b1VarA = ((s8.h) iVar.f21932t.f2141m).a(xVar);
                    if (b1VarA == null) {
                        throw new AssertionError("Parameter " + xVar + " surely belongs to class " + gVar + ", so it must be resolved");
                    }
                    arrayList.add(b1VarA);
                }
                return arrayList;
            default:
                i iVar2 = this.f21928l;
                if (m9.d.f(iVar2) == null) {
                    return null;
                }
                ((s8.a) iVar2.f21929q.f2140l).f21666w.getClass();
                return null;
        }
    }
}
