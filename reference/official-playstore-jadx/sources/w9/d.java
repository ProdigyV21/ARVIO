package w9;

import g8.b1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.NoWhenBranchMatchedException;
import v9.b0;
import v9.d1;
import v9.o0;
import v9.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f22507a = new d();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [v9.v] */
    /* JADX WARN: Type inference failed for: r0v2, types: [v9.v] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r3v0, types: [g8.b1] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3 */
    public static b0 b(b0 b0Var) {
        v9.w type;
        o0 o0VarI0 = b0Var.I0();
        ?? r32 = 0;
        if (o0VarI0 instanceof j9.c) {
            j9.c cVar = (j9.c) o0VarI0;
            r0 r0Var = cVar.f19406a;
            r0 r0Var2 = r0Var.c() == 2 ? r0Var : null;
            d1 d1VarL0 = (r0Var2 == null || (type = r0Var2.getType()) == null) ? null : type.L0();
            if (cVar.f19407b == null) {
                Collection collectionD = cVar.d();
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collectionD, 10));
                Iterator it = collectionD.iterator();
                while (it.hasNext()) {
                    arrayList.add(((v9.w) it.next()).L0());
                }
                cVar.f19407b = new i(r0Var, new h(0, arrayList), (b1) r32, 8);
            }
            return new g(1, cVar.f19407b, d1VarL0, b0Var.H0(), b0Var.J0(), 32);
        }
        if (!(o0VarI0 instanceof v9.v) || !b0Var.J0()) {
            return b0Var;
        }
        ?? r02 = (v9.v) o0VarI0;
        LinkedHashSet linkedHashSet = r02.f22254b;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(linkedHashSet, 10));
        Iterator it2 = linkedHashSet.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            arrayList2.add(v9.b1.g((v9.w) it2.next(), true));
            z = true;
        }
        if (z) {
            v9.w wVar = r02.f22253a;
            d1 d1VarG = wVar != null ? v9.b1.g(wVar, true) : null;
            arrayList2.isEmpty();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(arrayList2);
            linkedHashSet2.hashCode();
            v9.v vVar = new v9.v(linkedHashSet2);
            vVar.f22253a = d1VarG;
            r32 = vVar;
        }
        if (r32 != 0) {
            r02 = r32;
        }
        return r02.c();
    }

    public final d1 a(y9.h hVar) {
        d1 d1VarJ;
        if (!(hVar instanceof v9.w)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        d1 d1VarL0 = ((v9.w) hVar).L0();
        if (d1VarL0 instanceof b0) {
            d1VarJ = b((b0) d1VarL0);
        } else {
            if (!(d1VarL0 instanceof v9.q)) {
                throw new NoWhenBranchMatchedException();
            }
            v9.q qVar = (v9.q) d1VarL0;
            b0 b0Var = qVar.f22242m;
            b0 b0Var2 = qVar.f22241l;
            b0 b0VarB = b(b0Var2);
            b0 b0VarB2 = b(b0Var);
            d1VarJ = (b0VarB == b0Var2 && b0VarB2 == b0Var) ? d1VarL0 : v9.e.j(b0VarB, b0VarB2);
        }
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.j jVar = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.j(1, this, 6);
        v9.w wVarD = v9.c.d(d1VarL0);
        return v9.c.x(d1VarJ, wVarD != null ? (v9.w) jVar.invoke(wVarD) : null);
    }
}
