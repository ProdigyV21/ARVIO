package ya;

import cb.r1;
import io.ktor.http.LinkHeader;
import java.util.Map;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22989i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n f22990l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(n nVar, int i10) {
        super(1);
        this.f22989i = i10;
        this.f22990l = nVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f22989i) {
            case 0:
                ab.a aVar = (ab.a) obj;
                for (Map.Entry entry : this.f22990l.f22995e.entrySet()) {
                    aVar.a((String) entry.getKey(), ((h) entry.getValue()).getDescriptor(), (12 & 8) == 0);
                }
                break;
            default:
                ab.a aVar2 = (ab.a) obj;
                aVar2.a(LinkHeader.Parameters.Type, r1.f7554b, (12 & 8) == 0);
                StringBuilder sb2 = new StringBuilder("kotlinx.serialization.Sealed<");
                n nVar = this.f22990l;
                sb2.append(nVar.f22991a.r());
                sb2.append('>');
                m mVar = new m(nVar, 0);
                aVar2.a("value", xc.d.J(sb2.toString(), ab.l.f816b, new ab.g[0], mVar), (12 & 8) == 0);
                aVar2.f778b = nVar.f22992b;
                break;
        }
        return t0.f22605a;
    }
}
