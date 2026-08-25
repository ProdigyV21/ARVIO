package d8;

import j8.d0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14725i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d0 f14726l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(d0 d0Var, int i10) {
        super(0);
        this.f14725i = i10;
        this.f14726l = d0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f14725i) {
            case 0:
                return this.f14726l.B(q.f14764i).l();
            case 1:
                return new f8.g(this.f14726l);
            default:
                d0 d0Var = this.f14726l;
                androidx.work.impl.constraints.j jVar = d0Var.f19256q;
                if (jVar == null) {
                    throw new AssertionError(a0.c.p(new StringBuilder("Dependencies of module "), d0Var.getName().f15368i, " were not set before querying module content"));
                }
                List list = jVar.f6812a;
                d0Var.D0();
                list.contains(d0Var);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((d0) it.next()).getClass();
                }
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList.add(((d0) it2.next()).f19257r);
                }
                return new j8.n(arrayList, "CompositeProvider@ModuleDescriptor for " + d0Var.getName());
        }
    }
}
