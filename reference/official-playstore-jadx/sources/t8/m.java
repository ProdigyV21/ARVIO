package t8;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.i0;
import kotlin.collections.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21948i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n f21949l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(n nVar, int i10) {
        super(0);
        this.f21948i = i10;
        this.f21949l = nVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f21948i) {
            case 0:
                List listA = this.f21949l.f21951o.A();
                ArrayList arrayList = new ArrayList();
                for (Object obj : listA) {
                    if (((w8.n) obj).C()) {
                        arrayList.add(obj);
                    }
                }
                int iQ0 = i0.q0(kotlin.collections.s.U(arrayList, 10));
                if (iQ0 < 16) {
                    iQ0 = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
                for (Object obj2 : arrayList) {
                    linkedHashMap.put(((w8.n) obj2).getName(), obj2);
                }
                return linkedHashMap;
            case 1:
                return kotlin.collections.x.g1(this.f21949l.f21951o.x());
            default:
                n nVar = this.f21949l;
                return q0.b0(nVar.a(), nVar.b());
        }
    }
}
