package t8;

import androidx.appcompat.app.i1;
import com.google.common.util.concurrent.r0;
import j8.v0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p8.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21945i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ androidx.core.provider.e f21946l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f21947m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(androidx.core.provider.e eVar, n nVar) {
        super(0);
        this.f21946l = eVar;
        this.f21947m = nVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r26v1 */
    /* JADX WARN: Type inference failed for: r26v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r26v3 */
    /* JADX WARN: Type inference failed for: r2v0, types: [t8.n, t8.z] */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v6, types: [j8.k, j8.w, r8.b] */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.lang.Object, x6.s] */
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
    public final Object invoke() throws Throwable {
        Object obj;
        ?? arrayList;
        ?? r26;
        x6.x xVar;
        switch (this.f21945i) {
            case 0:
                ?? r22 = this.f21947m;
                w8.g gVar = r22.f21951o;
                androidx.core.provider.e eVar = r22.f21989b;
                g8.f fVar = r22.f21950n;
                List<w8.k> listI = gVar.i();
                ArrayList arrayList2 = new ArrayList(listI.size());
                for (w8.k kVar : listI) {
                    r8.b bVarV0 = r8.b.V0(fVar, qb.l.z(eVar, kVar), false, ((s8.a) eVar.f2140l).f21654j.c(kVar));
                    androidx.core.provider.e eVarG = qb.d.g(eVar, bVarV0, kVar, fVar.n().size(), eVar.f2142n);
                    androidx.mediarouter.media.x xVarT = z.t(eVarG, bVarV0, kVar.e());
                    List listN = fVar.n();
                    ArrayList typeParameters = kVar.getTypeParameters();
                    ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(typeParameters, 10));
                    Iterator it = typeParameters.iterator();
                    while (it.hasNext()) {
                        arrayList3.add(((s8.h) eVarG.f2141m).a((w8.x) it.next()));
                    }
                    bVarV0.U0(xVarT.f4506b, k0.a(kVar.getVisibility()), kotlin.collections.x.I0(listN, arrayList3));
                    bVarV0.O0(false);
                    bVarV0.P0(xVarT.f4507c);
                    bVarV0.Q0(fVar.m());
                    ((s8.a) eVarG.f2140l).f21651g.getClass();
                    arrayList2.add(bVarV0);
                }
                boolean zO = gVar.o();
                h8.f fVar2 = h8.g.f15863a;
                androidx.core.provider.e eVar2 = this.f21946l;
                if (zO) {
                    r8.b bVarV02 = r8.b.V0(fVar, fVar2, true, ((s8.a) eVar.f2140l).f21654j.c(gVar));
                    ArrayList<w8.v> arrayListK = gVar.k();
                    ArrayList arrayList4 = new ArrayList(arrayListK.size());
                    u8.a aVarC = qb.l.C(2, false, null, 6);
                    int i10 = 0;
                    for (w8.v vVar : arrayListK) {
                        arrayList4.add(new v0(bVarV02, null, i10, fVar2, vVar.getName(), ((i1) eVar.f2144p).d0(vVar.getType(), aVarC), false, false, false, null, ((s8.a) eVar.f2140l).f21654j.c(vVar)));
                        i10++;
                    }
                    obj = null;
                    bVarV02.P0(false);
                    g8.p visibility = fVar.getVisibility();
                    if (visibility.equals(p8.r.f21177b)) {
                        visibility = p8.r.f21178c;
                    }
                    bVarV02.T0(arrayList4, visibility);
                    bVarV02.O0(false);
                    bVarV02.Q0(fVar.m());
                    int i11 = 2;
                    String strF = r0.f(bVarV02, 2);
                    if (arrayList2.isEmpty()) {
                        arrayList2.add(bVarV02);
                        ((s8.a) eVar2.f2140l).f21651g.getClass();
                    } else {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            if (!r0.f((g8.e) it2.next(), i11).equals(strF)) {
                                i11 = 2;
                            }
                        }
                        arrayList2.add(bVarV02);
                        ((s8.a) eVar2.f2140l).f21651g.getClass();
                    }
                } else {
                    obj = null;
                }
                ((s8.a) eVar2.f2140l).x.h(eVar2, fVar, arrayList2);
                x8.m mVar = ((s8.a) eVar2.f2140l).f21661r;
                boolean zIsEmpty = arrayList2.isEmpty();
                List listF = arrayList2;
                if (zIsEmpty) {
                    boolean zM = gVar.m();
                    gVar.D();
                    if (zM) {
                        s8.a aVar = (s8.a) eVar.f2140l;
                        i1 i1Var = (i1) eVar.f2144p;
                        ?? V0 = r8.b.V0(fVar, fVar2, true, aVar.f21654j.c(gVar));
                        if (zM) {
                            List listQ = gVar.q();
                            arrayList = new ArrayList(listQ.size());
                            u8.a aVarC2 = qb.l.C(2, true, null, 6);
                            ArrayList arrayList5 = new ArrayList();
                            ArrayList<w8.q> arrayList6 = new ArrayList();
                            for (Object obj2 : listQ) {
                                if (kotlin.jvm.internal.p.a(((w8.q) obj2).getName(), p8.b0.f21089b)) {
                                    arrayList5.add(obj2);
                                } else {
                                    arrayList6.add(obj2);
                                }
                            }
                            arrayList5.size();
                            w8.q qVar = (w8.q) kotlin.collections.x.o0(arrayList5);
                            if (qVar != null) {
                                w8.w wVarY = qVar.y();
                                if (wVarY instanceof w8.f) {
                                    w8.f fVar3 = (w8.f) wVarY;
                                    xVar = new x6.x(i1Var.c0(fVar3, aVarC2, true), i1Var.d0(fVar3.v(), aVarC2));
                                } else {
                                    xVar = new x6.x(i1Var.d0(wVarY, aVarC2), null);
                                }
                                r22.w(arrayList, V0, 0, qVar, (v9.w) xVar.f22608i, (v9.w) xVar.f22609l);
                            }
                            int i12 = qVar != null ? 1 : 0;
                            int i13 = 0;
                            for (w8.q qVar2 : arrayList6) {
                                r22.w(arrayList, V0, i13 + i12, qVar2, i1Var.d0(qVar2.y(), aVarC2), null);
                                i13++;
                            }
                        } else {
                            arrayList = Collections.EMPTY_LIST;
                        }
                        V0.P0(false);
                        g8.p visibility2 = fVar.getVisibility();
                        if (visibility2.equals(p8.r.f21177b)) {
                            visibility2 = p8.r.f21178c;
                        }
                        V0.T0(arrayList, visibility2);
                        V0.O0(true);
                        V0.Q0(fVar.m());
                        ((s8.a) eVar.f2140l).f21651g.getClass();
                        r26 = V0;
                    } else {
                        r26 = obj;
                    }
                    listF = t7.a.F(r26);
                }
                return kotlin.collections.x.c1(mVar.c(eVar2, listF));
            default:
                androidx.core.provider.e eVar3 = this.f21946l;
                return kotlin.collections.x.g1(((s8.a) eVar3.f2140l).x.e(eVar3, this.f21947m.f21950n));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(n nVar, androidx.core.provider.e eVar) {
        super(0);
        this.f21947m = nVar;
        this.f21946l = eVar;
    }
}
