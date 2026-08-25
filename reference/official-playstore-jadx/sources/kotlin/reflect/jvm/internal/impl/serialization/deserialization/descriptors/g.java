package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import androidx.emoji2.text.e0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends s {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final w9.e f19856g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final u9.s f19857h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u9.s f19858i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ k f19859j;

    public g(k kVar, w9.e eVar) {
        this.f19859j = kVar;
        s9.m mVar = kVar.f19873v;
        a9.n nVar = kVar.f19866o;
        List list = nVar.A;
        List list2 = nVar.B;
        List list3 = nVar.C;
        List list4 = nVar.f590u;
        c9.f fVar = mVar.f21732b;
        s9.j jVar = mVar.f21731a;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list4, 10));
        Iterator it = list4.iterator();
        while (it.hasNext()) {
            arrayList.add(t7.a.x(fVar, ((Number) it.next()).intValue()));
        }
        super(mVar, list, list2, list3, new r(arrayList, 1));
        this.f19856g = eVar;
        u9.p pVar = jVar.f21706a;
        e eVar2 = new e(this, 0);
        pVar.getClass();
        this.f19857h = new u9.l(pVar, eVar2);
        u9.p pVar2 = jVar.f21706a;
        e eVar3 = new e(this, 1);
        pVar2.getClass();
        this.f19858i = new u9.l(pVar2, eVar3);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s, p9.o, p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        xc.d.D0(this.f19894b.f21731a.f21714i, bVar, this.f19859j, fVar);
        return this.f19895c.e(fVar);
    }

    @Override // p9.o, p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        g8.f fVar2;
        s9.m mVar = this.f19894b;
        o8.c cVar = mVar.f21731a.f21714i;
        k kVar = this.f19859j;
        xc.d.D0(cVar, bVar, kVar, fVar);
        e0 e0Var = kVar.z;
        if (e0Var != null && (fVar2 = (g8.f) ((u9.r) e0Var.f2601m).invoke(fVar)) != null) {
            return fVar2;
        }
        if (q(fVar)) {
            return (g8.f) mVar.f21731a.f21724t.f21696b.invoke(new s9.f(l(fVar), null));
        }
        n nVar = this.f19895c;
        if (nVar.d().contains(fVar)) {
            return nVar.g(fVar);
        }
        return null;
    }

    @Override // p9.o, p9.p
    public final Collection f(p9.f fVar, r7.l lVar) {
        return (Collection) this.f19857h.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s, p9.o, p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        xc.d.D0(this.f19894b.f21731a.f21714i, eVar, this.f19859j, fVar);
        return this.f19895c.f(fVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.ArrayList] */
    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final void h(ArrayList arrayList) {
        ?? arrayList2;
        e0 e0Var = this.f19859j.z;
        if (e0Var != null) {
            Set setKeySet = ((LinkedHashMap) e0Var.f2600l).keySet();
            arrayList2 = new ArrayList();
            Iterator it = setKeySet.iterator();
            while (it.hasNext()) {
                g8.f fVar = (g8.f) ((u9.r) e0Var.f2601m).invoke((f9.f) it.next());
                if (fVar != null) {
                    arrayList2.add(fVar);
                }
            }
        } else {
            arrayList2 = 0;
        }
        if (arrayList2 == 0) {
            arrayList2 = z.f19728i;
        }
        arrayList.addAll(arrayList2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final void j(f9.f fVar, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = ((Collection) this.f19858i.invoke()).iterator();
        while (it.hasNext()) {
            arrayList2.addAll(((v9.w) it.next()).l().c(fVar, o8.e.f20795m));
        }
        s9.m mVar = this.f19894b;
        arrayList.addAll(mVar.f21731a.f21718n.e(fVar, this.f19859j));
        ArrayList arrayList3 = new ArrayList(arrayList);
        mVar.f21731a.f21721q.a().h(fVar, arrayList2, arrayList3, this.f19859j, new f(arrayList));
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final void k(f9.f fVar, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = ((Collection) this.f19858i.invoke()).iterator();
        while (it.hasNext()) {
            arrayList2.addAll(((v9.w) it.next()).l().g(fVar, o8.e.f20795m));
        }
        ArrayList arrayList3 = new ArrayList(arrayList);
        this.f19894b.f21731a.f21721q.a().h(fVar, arrayList2, arrayList3, this.f19859j, new f(arrayList));
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final f9.b l(f9.f fVar) {
        return this.f19859j.f19869r.d(fVar);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final Set n() {
        List listD = this.f19859j.x.d();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            Set setD = ((v9.w) it.next()).l().d();
            if (setD == null) {
                return null;
            }
            kotlin.collections.x.b0(linkedHashSet, setD);
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final Set o() {
        k kVar = this.f19859j;
        List listD = kVar.x.d();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(linkedHashSet, ((v9.w) it.next()).l().a());
        }
        linkedHashSet.addAll(this.f19894b.f21731a.f21718n.c(kVar));
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final Set p() {
        List listD = this.f19859j.x.d();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(linkedHashSet, ((v9.w) it.next()).l().b());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final boolean r(v vVar) {
        return this.f19894b.f21731a.f21719o.d(this.f19859j, vVar);
    }
}
