package j8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19394i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ z f19395l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(z zVar, int i10) {
        super(0);
        this.f19394i = i10;
        this.f19395l = zVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f19394i) {
            case 0:
                z zVar = this.f19395l;
                d0 d0Var = zVar.f19397m;
                d0Var.D0();
                return Boolean.valueOf(g8.y.h((n) d0Var.f19260u.getValue(), zVar.f19398n));
            case 1:
                z zVar2 = this.f19395l;
                d0 d0Var2 = zVar2.f19397m;
                d0Var2.D0();
                n nVar = (n) d0Var2.f19260u.getValue();
                f9.c cVar = zVar2.f19398n;
                ArrayList arrayList = new ArrayList();
                g8.y.b(nVar, cVar, arrayList);
                return arrayList;
            default:
                z zVar3 = this.f19395l;
                boolean zIsEmpty = zVar3.isEmpty();
                f9.c cVar2 = zVar3.f19398n;
                d0 d0Var3 = zVar3.f19397m;
                if (zIsEmpty) {
                    return p9.m.f21235b;
                }
                List listH0 = zVar3.h0();
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listH0, 10));
                Iterator it = listH0.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((g8.h0) it.next()).l());
                }
                return t7.a.l("package view scope for " + cVar2 + " in " + d0Var3.getName(), kotlin.collections.x.H0(new p0(d0Var3, cVar2), arrayList2));
        }
    }
}
