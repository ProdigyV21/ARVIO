package d8;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import v9.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14699i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f14700l;

    public /* synthetic */ h(Object obj, int i10) {
        this.f14699i = i10;
        this.f14700l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f14699i) {
            case 0:
                k kVar = (k) this.f14700l;
                EnumMap enumMap = new EnumMap(m.class);
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                for (m mVar : m.values()) {
                    String strD = mVar.f14721i.d();
                    if (strD == null) {
                        k.a(47);
                        throw null;
                    }
                    b0 b0VarM = kVar.j(strD).m();
                    if (b0VarM == null) {
                        k.a(48);
                        throw null;
                    }
                    String strD2 = mVar.f14722l.d();
                    if (strD2 == null) {
                        k.a(47);
                        throw null;
                    }
                    b0 b0VarM2 = kVar.j(strD2).m();
                    if (b0VarM2 == null) {
                        k.a(48);
                        throw null;
                    }
                    enumMap.put(mVar, b0VarM2);
                    map.put(b0VarM, b0VarM2);
                    map2.put(b0VarM2, b0VarM);
                }
                return new j(enumMap, map, map2);
            case 1:
                StringBuilder sb2 = new StringBuilder("Scope for type parameter ");
                j8.h hVar = (j8.h) this.f14700l;
                sb2.append(((f9.f) hVar.f19281l).d());
                return ac.b.w(sb2.toString(), ((j8.j) hVar.f19282m).getUpperBounds());
            case 2:
                j8.s sVar = (j8.s) this.f14700l;
                sVar.getClass();
                HashSet hashSet = new HashSet();
                for (f9.f fVar : (Set) sVar.f19339e.f19344s.invoke()) {
                    if (fVar == null) {
                        j8.s.h(5);
                        throw null;
                    }
                    Collection collection = (Collection) sVar.f19336b.invoke(fVar);
                    if (collection == null) {
                        j8.s.h(7);
                        throw null;
                    }
                    hashSet.addAll(collection);
                    Collection collection2 = (Collection) sVar.f19337c.invoke(fVar);
                    if (collection2 == null) {
                        j8.s.h(3);
                        throw null;
                    }
                    hashSet.addAll(collection2);
                }
                return hashSet;
            default:
                return (List) this.f14700l;
        }
    }
}
