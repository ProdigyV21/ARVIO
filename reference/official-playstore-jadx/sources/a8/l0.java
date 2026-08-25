package a8;

import android.content.Context;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlinx.serialization.json.internal.JsonException;

/* JADX INFO: loaded from: classes5.dex */
public final class l0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f213i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f214l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f215m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l0(androidx.navigation.k kVar, androidx.navigation.i iVar, boolean z) {
        super(0);
        this.f213i = 2;
        this.f214l = kVar;
        this.f215m = iVar;
    }

    /* JADX WARN: Type inference failed for: r0v117, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v92, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v98, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r2v31, types: [kotlin.jvm.internal.r, r7.l] */
    /* JADX WARN: Type inference failed for: r2v86, types: [kotlin.jvm.internal.r, r7.a] */
    /* JADX WARN: Type inference failed for: r4v19, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() {
        Collection collectionU;
        ArrayList arrayList;
        String strC;
        kotlin.reflect.t tVarS;
        String[] strArrNames;
        int i10 = 0;
        switch (this.f213i) {
            case 0:
                m0 m0Var = (m0) this.f214l;
                j0 j0Var = m0Var.f219q;
                String str = (String) this.f215m;
                String str2 = m0Var.f220r;
                j0Var.getClass();
                if (str.equals("<init>")) {
                    collectionU = kotlin.collections.x.c1(j0Var.t());
                    arrayList = new ArrayList();
                    for (Object obj : collectionU) {
                        g8.j jVar = (g8.j) obj;
                        if (jVar.Z() && i9.h.c(jVar.d())) {
                            String strC2 = c2.c(jVar).c();
                            if (!kotlin.text.u.P(strC2, "constructor-impl", false) || !kotlin.text.u.K(strC2, ")V", false)) {
                                throw new IllegalArgumentException(("Invalid signature of " + jVar + ": " + strC2).toString());
                            }
                            strC = kotlin.text.o.s0(strC2, "V") + e9.b.b(m9.d.f(jVar.d()).c());
                        } else {
                            strC = c2.c(jVar).c();
                        }
                        if (kotlin.jvm.internal.p.a(strC, str2)) {
                            arrayList.add(obj);
                        }
                    }
                } else {
                    collectionU = j0Var.u(f9.f.j(str));
                    arrayList = new ArrayList();
                    for (Object obj2 : collectionU) {
                        if (kotlin.jvm.internal.p.a(c2.c((g8.w) obj2).c(), str2)) {
                            arrayList.add(obj2);
                        }
                    }
                }
                if (arrayList.size() == 1) {
                    return (g8.w) kotlin.collections.x.R0(arrayList);
                }
                String strU0 = kotlin.collections.x.u0(collectionU, "\n", null, null, b.f156s, 30);
                StringBuilder sbR = androidx.fragment.app.a2.r("Function '", str, "' (JVM signature: ", str2, ") not resolved in ");
                sbR.append(j0Var);
                sbR.append(':');
                sbR.append(strU0.length() == 0 ? " no members found" : "\n".concat(strU0));
                throw new x1(sbR.toString());
            case 1:
                u1 u1Var = (u1) this.f214l;
                List listG0 = u1Var.f282i.G0();
                if (listG0.isEmpty()) {
                    return kotlin.collections.z.f19728i;
                }
                x6.s sVarO = f4.f.o(2, new t1(u1Var, i10));
                r7.a aVar = (r7.a) this.f215m;
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listG0, 10));
                for (Object obj3 : listG0) {
                    int i11 = i10 + 1;
                    if (i10 < 0) {
                        t7.a.Q();
                        throw null;
                    }
                    v9.r0 r0Var = (v9.r0) obj3;
                    if (r0Var.b()) {
                        tVarS = kotlin.reflect.t.f19910c;
                    } else {
                        u1 u1Var2 = new u1(r0Var.getType(), aVar == null ? null : new s1(u1Var, i10, sVarO));
                        int iC = h.f0.c(r0Var.c());
                        if (iC == 0) {
                            kotlin.reflect.t tVar = kotlin.reflect.t.f19910c;
                            tVarS = com.google.common.util.concurrent.p0.s(u1Var2);
                        } else if (iC == 1) {
                            tVarS = new kotlin.reflect.t(2, u1Var2);
                        } else {
                            if (iC != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            tVarS = new kotlin.reflect.t(3, u1Var2);
                        }
                    }
                    arrayList2.add(tVarS);
                    i10 = i11;
                }
                return arrayList2;
            case 2:
                ((androidx.navigation.k) this.f214l).d((androidx.navigation.i) this.f215m);
                return x6.t0.f22605a;
            case 3:
                ((androidx.navigation.compose.p) this.f214l).e((androidx.navigation.i) this.f215m, false);
                return x6.t0.f22605a;
            case 4:
                androidx.work.impl.constraints.trackers.f fVar = ((androidx.work.impl.constraints.controllers.d) this.f214l).f6798a;
                androidx.work.impl.constraints.controllers.b bVar = (androidx.work.impl.constraints.controllers.b) this.f215m;
                synchronized (fVar.f6829c) {
                    if (fVar.f6830d.remove(bVar) && fVar.f6830d.isEmpty()) {
                        fVar.d();
                    }
                    break;
                }
                return x6.t0.f22605a;
            case 5:
                return new File(((Context) this.f214l).getApplicationContext().getFilesDir(), kotlin.jvm.internal.p.g(kotlin.jvm.internal.p.g(".preferences_pb", ((b1.a) this.f215m).f7106a), "datastore/"));
            case 6:
                Class cls = (Class) this.f214l;
                Map map = (Map) this.f215m;
                StringBuilder sb2 = new StringBuilder();
                sb2.append('@');
                sb2.append(cls.getCanonicalName());
                kotlin.collections.x.t0(map.entrySet(), sb2, ", ", "(", ")", b8.c.f7230i, 48);
                return sb2.toString();
            case 7:
                return new cb.k((ya.h) ((cb.q) this.f214l).f7545a.invoke((kotlin.reflect.d) this.f215m));
            case 8:
                cb.a0 a0Var = (cb.a0) this.f214l;
                String str3 = (String) this.f215m;
                Enum[] enumArr = a0Var.f7457a;
                cb.z zVar = new cb.z(str3, enumArr.length);
                for (Enum r02 : enumArr) {
                    zVar.j(r02.name(), false);
                }
                return zVar;
            case 9:
                ((kotlin.jvm.internal.f0) this.f214l).f19738i = ((Boolean) ((r7.a) this.f215m).invoke()).booleanValue();
                return x6.t0.f22605a;
            case 10:
                ab.g gVar = (ab.g) this.f214l;
                db.b bVar2 = (db.b) this.f215m;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                eb.r.n(gVar, bVar2);
                int iD = gVar.d();
                for (int i12 = 0; i12 < iD; i12++) {
                    List listF = gVar.f(i12);
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj4 : listF) {
                        if (obj4 instanceof db.w) {
                            arrayList3.add(obj4);
                        }
                    }
                    db.w wVar = (db.w) kotlin.collections.x.T0(arrayList3);
                    if (wVar != null && (strArrNames = wVar.names()) != null) {
                        for (String str4 : strArrNames) {
                            if (linkedHashMap.containsKey(str4)) {
                                StringBuilder sbT = a0.c.t("The suggested name '", str4, "' for property ");
                                sbT.append(gVar.e(i12));
                                sbT.append(" is already one of the names for property ");
                                sbT.append(gVar.e(((Number) kotlin.collections.h0.s0(linkedHashMap, str4)).intValue()));
                                sbT.append(" in ");
                                sbT.append(gVar);
                                throw new JsonException(sbT.toString());
                            }
                            linkedHashMap.put(str4, Integer.valueOf(i12));
                        }
                    }
                }
                return linkedHashMap.isEmpty() ? kotlin.collections.a0.f19683i : linkedHashMap;
            case 11:
                u9.p pVar = (u9.p) this.f215m;
                g8.b0 b0Var = ((f8.f) this.f214l).f15316a;
                List listH0 = b0Var.B(f8.f.f15313e).h0();
                ArrayList arrayList4 = new ArrayList();
                for (Object obj5 : listH0) {
                    if (obj5 instanceof d8.d) {
                        arrayList4.add(obj5);
                    }
                }
                j8.m mVar = new j8.m((d8.d) kotlin.collections.x.m0(arrayList4), f8.f.f15314f, 4, 2, Collections.singletonList(b0Var.j().e()), pVar);
                mVar.G0(new f8.a(pVar, mVar), kotlin.collections.b0.f19686i, null);
                return mVar;
            case 12:
                f8.h hVar = (f8.h) this.f214l;
                return new f8.n(hVar.k(), (u9.p) this.f215m, new g0(hVar, 17));
            case 13:
                f8.n nVar = (f8.n) this.f214l;
                j8.d0 d0Var = nVar.g().f15318a;
                f8.f.f15311c.getClass();
                return g8.y.f(d0Var, f8.f.f15315g, new androidx.emoji2.text.e0((u9.p) this.f215m, nVar.g().f15318a)).m();
            case 14:
                t8.i iVar = (t8.i) this.f214l;
                g8.f fVar2 = (g8.f) this.f215m;
                androidx.core.provider.e eVar = iVar.f21932t;
                s8.a aVar2 = (s8.a) eVar.f2140l;
                return new t8.i(new androidx.core.provider.e(new s8.a(aVar2.f21645a, aVar2.f21646b, aVar2.f21647c, aVar2.f21648d, aVar2.f21649e, aVar2.f21650f, aVar2.f21652h, aVar2.f21653i, aVar2.f21654j, aVar2.k, aVar2.f21655l, aVar2.f21656m, aVar2.f21657n, aVar2.f21658o, aVar2.f21659p, aVar2.f21660q, aVar2.f21661r, aVar2.f21662s, aVar2.f21663t, aVar2.f21664u, aVar2.f21665v, aVar2.f21666w), (s8.h) eVar.f2141m, (x6.s) eVar.f2142n), iVar.d(), iVar.f21930r, fVar2);
            case 15:
                LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) ((r7.a) this.f214l).invoke();
                i2.m mVar2 = (i2.m) this.f215m;
                return Float.valueOf(mVar2.getCanScrollForward() ? lazyLayoutItemProvider.getItemCount() + 1.0f : mVar2.getCurrentPosition());
            case 16:
                j8.r0 r0Var2 = (j8.r0) this.f214l;
                u9.v vVar = r0Var2.O;
                g8.a1 a1Var = r0Var2.P;
                g8.e eVar2 = (g8.e) this.f215m;
                h8.h annotations = eVar2.getAnnotations();
                int kind = eVar2.getKind();
                g8.a1 a1Var2 = r0Var2.P;
                j8.r0 r0Var3 = new j8.r0(vVar, a1Var, eVar2, r0Var2, annotations, kind, a1Var2.f());
                j8.r0.R.getClass();
                v9.y0 y0VarD = a1Var2.r() == null ? null : v9.y0.d(a1Var2.F());
                if (y0VarD == null) {
                    return null;
                }
                g8.s0 s0VarI = eVar2.I();
                j8.d dVarB = s0VarI != null ? s0VarI.b(y0VarD) : null;
                List listS0 = eVar2.s0();
                ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(listS0, 10));
                Iterator it = listS0.iterator();
                while (it.hasNext()) {
                    arrayList5.add(((g8.s0) it.next()).b(y0VarD));
                }
                r0Var3.L0(null, dVarB, arrayList5, a1Var2.n(), r0Var2.e(), r0Var2.f19379q, 1, a1Var2.getVisibility());
                return r0Var3;
            case 17:
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k kVar = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k) this.f214l;
                return kotlin.collections.x.c1(kVar.f19873v.f21731a.f21710e.E(kVar.G, (a9.b0) this.f215m));
            case 18:
                return ((s8.a) ((androidx.core.provider.e) this.f214l).f2140l).f21658o.j().i(((q8.b) this.f215m).f21333a).m();
            case 19:
                androidx.core.provider.e eVar3 = (androidx.core.provider.e) this.f214l;
                return ((s8.a) eVar3.f2140l).f21660q.b((p8.y) eVar3.f2143o.getValue(), ((g8.g) this.f215m).getAnnotations());
            case 20:
                androidx.core.provider.e eVar4 = (androidx.core.provider.e) this.f214l;
                return ((s8.a) eVar4.f2140l).f21660q.b((p8.y) eVar4.f2143o.getValue(), (h8.h) this.f215m);
            case 21:
                return new t8.p(((s8.f) this.f214l).f21675a, (w8.t) this.f215m);
            case 22:
                s9.m mVar3 = ((s9.g0) this.f214l).f21697a;
                return mVar3.f21731a.f21710e.g((a9.k1) this.f215m, mVar3.f21732b);
            case 23:
                p8.n nVar2 = ((s8.a) ((androidx.core.provider.e) this.f214l).f2140l).f21646b;
                f9.c cVar = ((t8.u) this.f215m).f21973o.f19274o;
                nVar2.getClass();
                return null;
            case 24:
                w9.e eVar5 = (w9.e) this.f214l;
                y9.h hVar2 = (y9.h) ((v9.x) this.f215m).f22259m.invoke();
                eVar5.getClass();
                return (v9.w) hVar2;
            default:
                Iterable iterable = (List) ((w9.i) this.f214l).f22522e.getValue();
                if (iterable == null) {
                    iterable = kotlin.collections.z.f19728i;
                }
                w9.e eVar6 = (w9.e) this.f215m;
                ArrayList arrayList6 = new ArrayList(kotlin.collections.s.U(iterable, 10));
                Iterator it2 = iterable.iterator();
                while (it2.hasNext()) {
                    arrayList6.add(((v9.d1) it2.next()).N0(eVar6));
                }
                return arrayList6;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l0(Object obj, Object obj2, int i10) {
        super(0);
        this.f213i = i10;
        this.f214l = obj;
        this.f215m = obj2;
    }
}
