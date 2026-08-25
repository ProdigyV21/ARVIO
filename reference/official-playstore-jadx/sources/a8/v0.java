package a8;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes5.dex */
public final class v0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f288i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ w0 f289l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v0(w0 w0Var, int i10) {
        super(0);
        this.f288i = i10;
        this.f289l = w0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.util.ArrayList] */
    @Override // r7.a
    public final Object invoke() throws InvalidProtocolBufferException {
        ?? SingletonList;
        int i10 = this.f288i;
        w0 w0Var = this.f289l;
        switch (i10) {
            case 0:
                z1 z1Var = w0Var.f297c;
                kotlin.reflect.m mVar = w0.f296h[0];
                l8.e eVar = (l8.e) z1Var.invoke();
                if (eVar == null) {
                    return null;
                }
                o5.a aVar = eVar.f20064b;
                String[] strArr = (String[]) aVar.f20779f;
                String[] strArr2 = (String[]) aVar.f20781h;
                if (strArr == null || strArr2 == null) {
                    return null;
                }
                x6.x xVarH = e9.h.h(strArr, strArr2);
                return new x6.j0((e9.g) xVarH.f22608i, (a9.n0) xVarH.f22609l, (e9.f) aVar.f20778e);
            default:
                z1 z1Var2 = w0Var.f297c;
                kotlin.reflect.m mVar2 = w0.f296h[0];
                l8.e eVar2 = (l8.e) z1Var2.invoke();
                if (eVar2 == null) {
                    return p9.m.f21235b;
                }
                z1 z1Var3 = w0Var.f199a;
                kotlin.reflect.m mVar3 = h0.f198b[0];
                androidx.appcompat.app.i1 i1Var = ((l8.h) z1Var3.invoke()).f20069b;
                y8.p pVar = (y8.p) i1Var.f1059l;
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) i1Var.f1061n;
                Class cls = eVar2.f20063a;
                f9.b bVarA = m8.c.a(cls);
                Object obj = concurrentHashMap.get(bVarA);
                if (obj == null) {
                    f9.c cVarG = m8.c.a(cls).g();
                    o5.a aVar2 = eVar2.f20064b;
                    z8.a aVar3 = (z8.a) aVar2.f20777d;
                    z8.a aVar4 = z8.a.MULTIFILE_CLASS;
                    if (aVar3 == aVar4) {
                        String[] strArr3 = (String[]) aVar2.f20779f;
                        if (aVar3 != aVar4) {
                            strArr3 = null;
                        }
                        List listAsList = strArr3 != null ? Arrays.asList(strArr3) : null;
                        if (listAsList == null) {
                            listAsList = kotlin.collections.z.f19728i;
                        }
                        SingletonList = new ArrayList();
                        Iterator it = listAsList.iterator();
                        while (it.hasNext()) {
                            f9.b bVarJ = f9.b.j(new f9.c(n9.b.d((String) it.next()).f20498a.replace('/', '.')));
                            l8.c cVar = (l8.c) i1Var.f1060m;
                            pVar.c().f21708c.getClass();
                            e9.f fVar = e9.f.f15073g;
                            kb.d dVarB = cVar.b(bVarJ);
                            l8.e eVar3 = dVarB != null ? (l8.e) dVarB.f19673l : null;
                            if (eVar3 != null) {
                                SingletonList.add(eVar3);
                            }
                        }
                    } else {
                        SingletonList = Collections.singletonList(eVar2);
                    }
                    f8.j jVar = new f8.j(pVar.c().f21707b, cVarG, 1);
                    ArrayList arrayList = new ArrayList();
                    Iterator it2 = SingletonList.iterator();
                    while (it2.hasNext()) {
                        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t tVarA = pVar.a(jVar, (y8.y) it2.next());
                        if (tVarA != null) {
                            arrayList.add(tVarA);
                        }
                    }
                    p9.n nVarL = t7.a.l("package " + cVarG + " (" + eVar2 + ')', kotlin.collections.x.c1(arrayList));
                    Object objPutIfAbsent = concurrentHashMap.putIfAbsent(bVarA, nVarL);
                    obj = objPutIfAbsent == null ? nVarL : objPutIfAbsent;
                }
                return (p9.n) obj;
        }
    }
}
