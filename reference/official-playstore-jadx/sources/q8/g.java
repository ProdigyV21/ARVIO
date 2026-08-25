package q8;

import d8.p;
import java.util.Map;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import u9.s;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21346h;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final s f21347g;

    static {
        m0 m0Var = l0.f19747a;
        f21346h = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(g.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public g(w8.a aVar, androidx.core.provider.e eVar) {
        super(eVar, aVar, p.f14744m);
        this.f21347g = ((s8.a) eVar.f2140l).f21645a.d(f.f21345i);
    }

    @Override // q8.b, h8.b
    public final Map a() {
        kotlin.reflect.m mVar = f21346h[0];
        return (Map) this.f21347g.invoke();
    }
}
