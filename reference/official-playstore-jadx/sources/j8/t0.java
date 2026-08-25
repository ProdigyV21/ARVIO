package j8;

import androidx.work.impl.WorkDatabase;
import com.google.accompanist.drawablepainter.DrawablePainter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19346i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f19347l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t0(Object obj, int i10) {
        super(0);
        this.f19346i = i10;
        this.f19347l = obj;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r5v18, types: [i9.n] */
    /* JADX WARN: Type inference failed for: r8v2, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // r7.a
    public final Object invoke() {
        ?? arrayList;
        h8.m mVar;
        k9.b bVarA;
        int iJ0;
        long size;
        long j10;
        ArrayList arrayList2;
        x6.x xVar;
        x6.x xVar2;
        boolean z = false;
        switch (this.f19346i) {
            case 0:
                return (List) ((u0) this.f19347l).f19348v.getValue();
            case 1:
                return ((v9.r0) this.f19347l).getType();
            case 2:
                p9.h hVar = (p9.h) this.f19347l;
                List listH = hVar.h();
                ArrayList arrayList3 = new ArrayList(3);
                b bVar = hVar.f21224b;
                Collection collectionD = bVar.g().d();
                ArrayList arrayList4 = new ArrayList();
                Iterator it = collectionD.iterator();
                while (it.hasNext()) {
                    kotlin.collections.x.b0(arrayList4, a.a.P(((v9.w) it.next()).l(), null, 3));
                }
                ArrayList arrayList5 = new ArrayList();
                for (Object obj : arrayList4) {
                    if (obj instanceof g8.c) {
                        arrayList5.add(obj);
                    }
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj2 : arrayList5) {
                    f9.f name = ((g8.c) obj2).getName();
                    Object arrayList6 = linkedHashMap.get(name);
                    if (arrayList6 == null) {
                        arrayList6 = new ArrayList();
                        linkedHashMap.put(name, arrayList6);
                    }
                    ((List) arrayList6).add(obj2);
                }
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    f9.f fVar = (f9.f) entry.getKey();
                    List list = (List) entry.getValue();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    for (Object obj3 : list) {
                        Boolean boolValueOf = Boolean.valueOf(((g8.c) obj3) instanceof g8.w);
                        Object arrayList7 = linkedHashMap2.get(boolValueOf);
                        if (arrayList7 == null) {
                            arrayList7 = new ArrayList();
                            linkedHashMap2.put(boolValueOf, arrayList7);
                        }
                        ((List) arrayList7).add(obj3);
                    }
                    for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                        boolean zBooleanValue = ((Boolean) entry2.getKey()).booleanValue();
                        List list2 = (List) entry2.getValue();
                        ?? r52 = i9.n.f16259c;
                        if (zBooleanValue) {
                            arrayList = new ArrayList();
                            for (Object obj4 : listH) {
                                if (kotlin.jvm.internal.p.a(((g8.w) obj4).getName(), fVar)) {
                                    arrayList.add(obj4);
                                }
                            }
                        } else {
                            arrayList = kotlin.collections.z.f19728i;
                        }
                        r52.h(fVar, list2, arrayList, bVar, new p9.g(arrayList3, hVar));
                    }
                }
                return kotlin.collections.x.I0(listH, ea.o.e(arrayList3));
            case 3:
                p9.s sVar = (p9.s) this.f19347l;
                return sVar.i(a.a.P(sVar.f21244b, null, 3));
            case 4:
                Object obj5 = q8.e.f21343a;
                w8.b bVar2 = ((q8.k) this.f19347l).f21336d;
                w8.m mVar2 = bVar2 instanceof w8.m ? (w8.m) bVar2 : null;
                k9.i iVar = (mVar2 == null || (mVar = (h8.m) q8.e.f21344b.get(mVar2.e().d())) == null) ? null : new k9.i(f9.b.j(d8.p.f14753v), f9.f.j(mVar.name()));
                Map mapSingletonMap = iVar != null ? Collections.singletonMap(q8.c.f21340c, iVar) : null;
                return mapSingletonMap == null ? kotlin.collections.a0.f19683i : mapSingletonMap;
            case 5:
                w8.b bVar3 = ((q8.l) this.f19347l).f21336d;
                if (bVar3 instanceof w8.e) {
                    Object obj6 = q8.e.f21343a;
                    bVarA = q8.e.a(((w8.e) bVar3).c());
                } else if (bVar3 instanceof w8.m) {
                    Object obj7 = q8.e.f21343a;
                    bVarA = q8.e.a(Collections.singletonList(bVar3));
                } else {
                    bVarA = null;
                }
                Map mapSingletonMap2 = bVarA != null ? Collections.singletonMap(q8.c.f21339b, bVarA) : null;
                return mapSingletonMap2 == null ? kotlin.collections.a0.f19683i : mapSingletonMap2;
            case 6:
                Set setKeySet = ((LinkedHashMap) ((t9.c) this.f19347l).f22003s.f2603o).keySet();
                ArrayList arrayList8 = new ArrayList();
                for (Object obj8 : setKeySet) {
                    f9.b bVar4 = (f9.b) obj8;
                    if (bVar4.f15356b.e().d() && !s9.g.f21694c.contains(bVar4)) {
                        arrayList8.add(obj8);
                    }
                }
                ArrayList arrayList9 = new ArrayList(kotlin.collections.s.U(arrayList8, 10));
                Iterator it2 = arrayList8.iterator();
                while (it2.hasNext()) {
                    arrayList9.add(((f9.b) it2.next()).i());
                }
                return arrayList9;
            case 7:
                t8.d dVar = (t8.d) this.f19347l;
                t8.p pVar = dVar.f21911c;
                u9.s sVar2 = pVar.f21963s;
                kotlin.reflect.m mVar3 = t8.p.f21960w[0];
                Collection collectionValues = ((Map) sVar2.invoke()).values();
                ArrayList arrayList10 = new ArrayList();
                Iterator it3 = collectionValues.iterator();
                while (it3.hasNext()) {
                    kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t tVarA = ((s8.a) dVar.f21910b.f2140l).f21648d.a(pVar, (y8.y) it3.next());
                    if (tVarA != null) {
                        arrayList10.add(tVarA);
                    }
                }
                return (p9.n[]) com.google.common.util.concurrent.r0.v(arrayList10).toArray(new p9.n[0]);
            case 8:
                return new v9.f(((v9.g) this.f19347l).c());
            case 9:
                return v9.c.o(((v9.g0) this.f19347l).f22210a);
            case 10:
                return x9.k.b(x9.j.I, ((fi.iki.elonen.f) this.f19347l).toString());
            case 11:
                r7.a aVar = ((w9.i) this.f19347l).f22519b;
                if (aVar != null) {
                    return (List) aVar.invoke();
                }
                return null;
            case 12:
                androidx.appcompat.view.menu.e eVar = (androidx.appcompat.view.menu.e) this.f19347l;
                String strD = eVar.d();
                WorkDatabase workDatabase = (WorkDatabase) eVar.f1284b;
                workDatabase.a();
                workDatabase.b();
                return workDatabase.h().getWritableDatabase().g0(strD);
            case 13:
                return new androidx.vectordrawable.graphics.drawable.e((DrawablePainter) this.f19347l, 2);
            case 14:
                ya.k kVar = (ya.k) this.f19347l;
                return new ab.b(xc.d.J("kotlinx.serialization.Polymorphic", ab.c.f787b, new ab.g[0], new s8.d(kVar, 17)), kVar.f22987a);
            case 15:
                return xc.d.J("io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider.Config", ab.c.f788c, new ab.g[0], new ya.m((ya.n) this.f19347l, 1));
            case 16:
                yb.i iVar2 = (yb.i) this.f19347l;
                ClassLoader classLoader = iVar2.f23039a;
                xb.s sVar3 = iVar2.f23040b;
                ArrayList<URL> list3 = Collections.list(classLoader.getResources(""));
                ArrayList arrayList11 = new ArrayList();
                for (URL url : list3) {
                    if (kotlin.jvm.internal.p.a(url.getProtocol(), "file")) {
                        String str = xb.f0.f22733l;
                        xVar2 = new x6.x(sVar3, v1.i.h(new File(url.toURI())));
                    } else {
                        xVar2 = null;
                    }
                    if (xVar2 != null) {
                        arrayList11.add(xVar2);
                    }
                }
                ArrayList list4 = Collections.list(classLoader.getResources("META-INF/MANIFEST.MF"));
                ArrayList arrayList12 = new ArrayList();
                Iterator it4 = list4.iterator();
                while (it4.hasNext()) {
                    String string = ((URL) it4.next()).toString();
                    if (kotlin.text.u.P(string, "jar:file:", z) && (iJ0 = kotlin.text.o.j0(6, string, "!")) != -1) {
                        String str2 = xb.f0.f22733l;
                        xb.f0 f0VarH = v1.i.h(new File(URI.create(string.substring(4, iJ0))));
                        xb.p pVarOpenReadOnly = sVar3.openReadOnly(f0VarH);
                        try {
                            size = pVarOpenReadOnly.size() - ((long) 22);
                            j10 = 0;
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                xc.d.L(pVarOpenReadOnly, th);
                                throw th2;
                            }
                        }
                        if (size < 0) {
                            throw new IOException("not a zip: size=" + pVarOpenReadOnly.size());
                        }
                        long jMax = Math.max(size - 65536, 0L);
                        while (true) {
                            long j11 = j10;
                            xb.i0 i0Var = new xb.i0(pVarOpenReadOnly.l(size));
                            try {
                                if (i0Var.j() == 101010256) {
                                    int iL = i0Var.l() & 65535;
                                    int iL2 = i0Var.l() & 65535;
                                    arrayList2 = arrayList12;
                                    long jL = i0Var.l() & 65535;
                                    if (jL != (i0Var.l() & 65535) || iL != 0 || iL2 != 0) {
                                        throw new IOException("unsupported zip: spanned");
                                    }
                                    i0Var.skip(4L);
                                    long j12 = ((long) i0Var.j()) & 4294967295L;
                                    int iL3 = i0Var.l() & 65535;
                                    yb.g gVar = new yb.g(jL, j12, iL3);
                                    i0Var.o(iL3);
                                    i0Var.close();
                                    long j13 = size - ((long) 20);
                                    if (j13 > j11) {
                                        i0Var = new xb.i0(pVarOpenReadOnly.l(j13));
                                        try {
                                            if (i0Var.j() == 117853008) {
                                                int iJ = i0Var.j();
                                                long jK = i0Var.k();
                                                if (i0Var.j() != 1 || iJ != 0) {
                                                    throw new IOException("unsupported zip: spanned");
                                                }
                                                i0Var = new xb.i0(pVarOpenReadOnly.l(jK));
                                                try {
                                                    int iJ2 = i0Var.j();
                                                    if (iJ2 != 101075792) {
                                                        throw new IOException("bad zip: expected " + yb.b.d(101075792) + " but was " + yb.b.d(iJ2));
                                                    }
                                                    i0Var.skip(12L);
                                                    int iJ3 = i0Var.j();
                                                    int iJ4 = i0Var.j();
                                                    long jK2 = i0Var.k();
                                                    if (jK2 != i0Var.k() || iJ3 != 0 || iJ4 != 0) {
                                                        throw new IOException("unsupported zip: spanned");
                                                    }
                                                    i0Var.skip(8L);
                                                    gVar = new yb.g(jK2, i0Var.k(), iL3);
                                                } catch (Throwable th3) {
                                                    try {
                                                        throw th3;
                                                    } finally {
                                                    }
                                                }
                                                throw th;
                                            }
                                        } finally {
                                            try {
                                                throw th3;
                                            } finally {
                                            }
                                        }
                                    }
                                    yb.g gVar2 = gVar;
                                    long j14 = gVar2.f23034b;
                                    ArrayList arrayList13 = new ArrayList();
                                    i0Var = new xb.i0(pVarOpenReadOnly.l(j14));
                                    try {
                                        long j15 = gVar2.f23033a;
                                        long j16 = j11;
                                        while (j16 < j15) {
                                            yb.j jVarE = yb.b.e(i0Var);
                                            long j17 = j14;
                                            if (jVarE.f23048g >= j17) {
                                                throw new IOException("bad zip: local file header offset >= central directory offset");
                                            }
                                            xb.f0 f0Var = yb.i.f23038d;
                                            if (v1.i.c(jVarE.f23042a)) {
                                                arrayList13.add(jVarE);
                                            }
                                            j16++;
                                            j14 = j17;
                                            throw th;
                                        }
                                        i0Var.close();
                                        xb.r0 r0Var = new xb.r0(f0VarH, sVar3, yb.b.b(arrayList13));
                                        pVarOpenReadOnly.close();
                                        xVar = new x6.x(r0Var, yb.i.f23038d);
                                    } catch (Throwable th32) {
                                    }
                                } else {
                                    ArrayList arrayList14 = arrayList12;
                                    i0Var.close();
                                    size--;
                                    if (size < jMax) {
                                        throw new IOException("not a zip: end of central directory signature not found");
                                    }
                                    arrayList12 = arrayList14;
                                    j10 = j11;
                                }
                            } finally {
                                i0Var.close();
                            }
                        }
                    } else {
                        arrayList2 = arrayList12;
                        xVar = null;
                    }
                    ArrayList arrayList15 = arrayList2;
                    if (xVar != null) {
                        arrayList15.add(xVar);
                    }
                    arrayList12 = arrayList15;
                    z = false;
                }
                return kotlin.collections.x.I0(arrayList11, arrayList12);
            default:
                File file = (File) ((z0.a0) this.f19347l).f23062a.invoke();
                String absolutePath = file.getAbsolutePath();
                synchronized (z0.a0.f23061j) {
                    LinkedHashSet linkedHashSet = z0.a0.f23060i;
                    if (linkedHashSet.contains(absolutePath)) {
                        throw new IllegalStateException(("There are multiple DataStores active for the same file: " + file + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
                    }
                    linkedHashSet.add(absolutePath);
                }
                return file;
        }
    }
}
