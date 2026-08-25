package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.i0;
import a9.m1;
import a9.t0;
import g8.a1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.z;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements n {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f19881j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f19882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f19883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f19884c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.q f19885d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.q f19886e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u9.r f19887f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u9.s f19888g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final u9.s f19889h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ s f19890i;

    static {
        m0 m0Var = l0.f19747a;
        f19881j = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(q.class), "functionNames", "getFunctionNames()Ljava/util/Set;")), m0Var.g(new b0(m0Var.b(q.class), "variableNames", "getVariableNames()Ljava/util/Set;"))};
    }

    public q(s sVar, List list, List list2, List list3) {
        this.f19890i = sVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            f9.f fVarX = t7.a.x(sVar.f19894b.f21732b, ((i0) ((c0) obj)).f489p);
            Object arrayList = linkedHashMap.get(fVarX);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(fVarX, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        this.f19882a = h(linkedHashMap);
        s sVar2 = this.f19890i;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj2 : list2) {
            f9.f fVarX2 = t7.a.x(sVar2.f19894b.f21732b, ((t0) ((c0) obj2)).f680p);
            Object arrayList2 = linkedHashMap2.get(fVarX2);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap2.put(fVarX2, arrayList2);
            }
            ((List) arrayList2).add(obj2);
        }
        this.f19883b = h(linkedHashMap2);
        this.f19890i.f19894b.f21731a.f21708c.getClass();
        s sVar3 = this.f19890i;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Object obj3 : list3) {
            f9.f fVarX3 = t7.a.x(sVar3.f19894b.f21732b, ((m1) ((c0) obj3)).f572o);
            Object arrayList3 = linkedHashMap3.get(fVarX3);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap3.put(fVarX3, arrayList3);
            }
            ((List) arrayList3).add(obj3);
        }
        this.f19884c = h(linkedHashMap3);
        this.f19885d = this.f19890i.f19894b.f21731a.f21706a.g(new p(this, 0));
        this.f19886e = this.f19890i.f19894b.f21731a.f21706a.g(new p(this, 1));
        this.f19887f = this.f19890i.f19894b.f21731a.f21706a.e(new p(this, 2));
        s sVar4 = this.f19890i;
        u9.p pVar = sVar4.f19894b.f21731a.f21706a;
        o oVar = new o(this, sVar4, 0);
        pVar.getClass();
        this.f19888g = new u9.l(pVar, oVar);
        s sVar5 = this.f19890i;
        u9.p pVar2 = sVar5.f19894b.f21731a.f21706a;
        o oVar2 = new o(this, sVar5, 1);
        pVar2.getClass();
        this.f19889h = new u9.l(pVar2, oVar2);
    }

    public static LinkedHashMap h(LinkedHashMap linkedHashMap) throws IOException {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterable<kotlin.reflect.jvm.internal.impl.protobuf.c> iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
            for (kotlin.reflect.jvm.internal.impl.protobuf.c cVar : iterable) {
                int iA = cVar.a();
                int iF = kotlin.reflect.jvm.internal.impl.protobuf.j.f(iA) + iA;
                if (iF > 4096) {
                    iF = 4096;
                }
                kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(byteArrayOutputStream, iF);
                jVarJ.v(iA);
                cVar.e(jVarJ);
                jVarJ.i();
                arrayList.add(x6.t0.f22605a);
            }
            linkedHashMap2.put(key, byteArrayOutputStream.toByteArray());
        }
        return linkedHashMap2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final Set a() {
        kotlin.reflect.m mVar = f19881j[0];
        return (Set) this.f19888g.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final Set b() {
        kotlin.reflect.m mVar = f19881j[1];
        return (Set) this.f19889h.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final void c(ArrayList arrayList, p9.f fVar, r7.l lVar) {
        boolean zA = fVar.a(p9.f.f21210j);
        i9.i iVar = i9.i.f16251l;
        if (zA) {
            Set<f9.f> setB = b();
            ArrayList arrayList2 = new ArrayList();
            for (f9.f fVar2 : setB) {
                if (((Boolean) lVar.invoke(fVar2)).booleanValue()) {
                    arrayList2.addAll(f(fVar2));
                }
            }
            kotlin.collections.w.X(iVar, arrayList2);
            arrayList.addAll(arrayList2);
        }
        if (fVar.a(p9.f.f21209i)) {
            Set<f9.f> setA = a();
            ArrayList arrayList3 = new ArrayList();
            for (f9.f fVar3 : setA) {
                if (((Boolean) lVar.invoke(fVar3)).booleanValue()) {
                    arrayList3.addAll(e(fVar3));
                }
            }
            kotlin.collections.w.X(iVar, arrayList3);
            arrayList.addAll(arrayList3);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final Set d() {
        return this.f19884c.keySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final Collection e(f9.f fVar) {
        return !a().contains(fVar) ? z.f19728i : (Collection) this.f19885d.invoke(fVar);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final Collection f(f9.f fVar) {
        return !b().contains(fVar) ? z.f19728i : (Collection) this.f19886e.invoke(fVar);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.n
    public final a1 g(f9.f fVar) {
        return (a1) this.f19887f.invoke(fVar);
    }
}
