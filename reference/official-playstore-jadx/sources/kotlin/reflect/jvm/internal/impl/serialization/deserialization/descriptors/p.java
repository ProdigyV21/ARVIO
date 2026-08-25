package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.h2;
import a9.i0;
import a9.k1;
import a9.m1;
import a9.t0;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.z;
import s9.b0;
import s9.g0;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19879i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ q f19880l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(q qVar, int i10) {
        super(1);
        this.f19879i = i10;
        this.f19880l = qVar;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, java.util.Map] */
    @Override // r7.l
    public final Object invoke(Object obj) {
        g8.p pVar;
        k1 k1VarB;
        k1 k1VarB2;
        switch (this.f19879i) {
            case 0:
                f9.f fVar = (f9.f) obj;
                q qVar = this.f19880l;
                LinkedHashMap linkedHashMap = qVar.f19882a;
                a9.a aVar = i0.F;
                s sVar = qVar.f19890i;
                byte[] bArr = (byte[]) linkedHashMap.get(fVar);
                Collection collectionU = bArr != null ? ga.r.U(ga.r.N(new a8.y(aVar, new ByteArrayInputStream(bArr), sVar, 1))) : z.f19728i;
                ArrayList arrayList = new ArrayList(collectionU.size());
                Iterator it = collectionU.iterator();
                while (it.hasNext()) {
                    v vVarE = sVar.f19894b.f21739i.e((i0) it.next());
                    if (!sVar.r(vVarE)) {
                        vVarE = null;
                    }
                    if (vVarE != null) {
                        arrayList.add(vVarE);
                    }
                }
                sVar.j(fVar, arrayList);
                return ea.o.e(arrayList);
            case 1:
                f9.f fVar2 = (f9.f) obj;
                q qVar2 = this.f19880l;
                LinkedHashMap linkedHashMap2 = qVar2.f19883b;
                a9.a aVar2 = t0.F;
                s sVar2 = qVar2.f19890i;
                byte[] bArr2 = (byte[]) linkedHashMap2.get(fVar2);
                Collection collectionU2 = bArr2 != null ? ga.r.U(ga.r.N(new a8.y(aVar2, new ByteArrayInputStream(bArr2), sVar2, 1))) : z.f19728i;
                ArrayList arrayList2 = new ArrayList(collectionU2.size());
                Iterator it2 = collectionU2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(sVar2.f19894b.f21739i.f((t0) it2.next()));
                }
                sVar2.k(fVar2, arrayList2);
                return ea.o.e(arrayList2);
            default:
                q qVar3 = this.f19880l;
                s9.m mVar = qVar3.f19890i.f19894b;
                byte[] bArr3 = (byte[]) qVar3.f19884c.get((f9.f) obj);
                if (bArr3 != null) {
                    m1 m1Var = (m1) m1.z.a(new ByteArrayInputStream(bArr3), mVar.f21731a.f21720p);
                    if (m1Var != null) {
                        s9.x xVar = mVar.f21739i;
                        s9.m mVar2 = xVar.f21764a;
                        c9.f fVar3 = mVar2.f21732b;
                        androidx.work.impl.constraints.j jVar = mVar2.f21734d;
                        List list = m1Var.f578u;
                        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(list, 10));
                        Iterator it3 = list.iterator();
                        while (it3.hasNext()) {
                            arrayList3.add(xVar.f21765b.I((a9.j) it3.next(), fVar3));
                        }
                        h8.h iVar = arrayList3.isEmpty() ? h8.g.f15863a : new h8.i(arrayList3, 0);
                        h2 h2Var = (h2) c9.e.f7435d.c(m1Var.f571n);
                        switch (h2Var == null ? -1 : b0.f21681b[h2Var.ordinal()]) {
                            case 1:
                                pVar = g8.q.f15527d;
                                break;
                            case 2:
                                pVar = g8.q.f15524a;
                                break;
                            case 3:
                                pVar = g8.q.f15525b;
                                break;
                            case 4:
                                pVar = g8.q.f15526c;
                                break;
                            case 5:
                                pVar = g8.q.f15528e;
                                break;
                            case 6:
                                pVar = g8.q.f15529f;
                                break;
                            default:
                                pVar = g8.q.f15524a;
                                break;
                        }
                        w wVar = new w(mVar2.f21731a.f21706a, mVar2.f21733c, iVar, t7.a.x(fVar3, m1Var.f572o), pVar, m1Var, mVar2.f21732b, jVar, mVar2.f21735e, mVar2.f21737g);
                        g0 g0Var = mVar2.a(wVar, m1Var.f573p, mVar2.f21732b, mVar2.f21734d, mVar2.f21735e, mVar2.f21736f).f21738h;
                        List listB = g0Var.b();
                        int i10 = m1Var.f570m;
                        if ((i10 & 4) == 4) {
                            k1VarB = m1Var.f574q;
                        } else {
                            if ((i10 & 8) != 8) {
                                throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias");
                            }
                            k1VarB = jVar.b(m1Var.f575r);
                        }
                        v9.b0 b0VarD = g0Var.d(k1VarB, false);
                        int i11 = m1Var.f570m;
                        if ((i11 & 16) == 16) {
                            k1VarB2 = m1Var.f576s;
                        } else {
                            if ((i11 & 32) != 32) {
                                throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias");
                            }
                            k1VarB2 = jVar.b(m1Var.f577t);
                        }
                        wVar.G0(listB, b0VarD, g0Var.d(k1VarB2, false));
                        return wVar;
                    }
                }
                return null;
        }
    }
}
