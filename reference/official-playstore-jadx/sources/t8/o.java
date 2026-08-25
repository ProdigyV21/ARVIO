package t8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21958i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ p f21959l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(p pVar, int i10) {
        super(0);
        this.f21958i = i10;
        this.f21959l = pVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f21958i) {
            case 0:
                p pVar = this.f21959l;
                y8.b0 b0Var = ((s8.a) pVar.f21962r.f2140l).f21655l;
                pVar.f19274o.b();
                b0Var.getClass();
                return h0.A0(new ArrayList());
            case 1:
                HashMap map = new HashMap();
                u9.s sVar = this.f21959l.f21963s;
                kotlin.reflect.m mVar = p.f21960w[0];
                for (Map.Entry entry : ((Map) sVar.invoke()).entrySet()) {
                    String str = (String) entry.getKey();
                    y8.y yVar = (y8.y) entry.getValue();
                    n9.b bVarD = n9.b.d(str);
                    o5.a aVarB = yVar.b();
                    int iOrdinal = ((z8.a) aVarB.f20777d).ordinal();
                    if (iOrdinal == 2) {
                        map.put(bVarD, bVarD);
                    } else if (iOrdinal == 5) {
                        String str2 = aVarB.f20775b;
                        if (((z8.a) aVarB.f20777d) != z8.a.MULTIFILE_CLASS_PART) {
                            str2 = null;
                        }
                        if (str2 != null) {
                            map.put(bVarD, n9.b.d(str2));
                        }
                    }
                }
                return map;
            default:
                this.f21959l.f21961q.getClass();
                kotlin.collections.z zVar = kotlin.collections.z.f19728i;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(zVar, 10));
                Iterator<E> it = zVar.iterator();
                while (it.hasNext()) {
                    arrayList.add(((w8.t) it.next()).c());
                }
                return arrayList;
        }
    }
}
