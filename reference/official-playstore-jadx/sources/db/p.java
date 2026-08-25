package db;

import eb.j0;
import java.util.Map;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final p f14918l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final p f14919m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14920i;

    static {
        int i10 = 1;
        f14918l = new p(i10, 0);
        f14919m = new p(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(int i10, int i11) {
        super(i10);
        this.f14920i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f14920i) {
            case 0:
                ab.a aVar = (ab.a) obj;
                aVar.a("JsonPrimitive", new r(o.f14911l), (12 & 8) == 0);
                aVar.a("JsonNull", new r(o.f14912m), (12 & 8) == 0);
                aVar.a("JsonLiteral", new r(o.f14913n), (12 & 8) == 0);
                aVar.a("JsonObject", new r(o.f14914o), (12 & 8) == 0);
                aVar.a("JsonArray", new r(o.f14915p), (12 & 8) == 0);
                return t0.f22605a;
            default:
                Map.Entry entry = (Map.Entry) obj;
                String str = (String) entry.getKey();
                m mVar = (m) entry.getValue();
                StringBuilder sb2 = new StringBuilder();
                j0.a(sb2, str);
                sb2.append(':');
                sb2.append(mVar);
                return sb2.toString();
        }
    }
}
