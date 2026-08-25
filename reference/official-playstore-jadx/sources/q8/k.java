package q8;

import d8.p;
import j8.t0;
import java.util.Map;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import u9.s;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21350h;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final s f21351g;

    static {
        m0 m0Var = l0.f19747a;
        f21350h = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(k.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public k(w8.a aVar, androidx.core.provider.e eVar) {
        super(eVar, aVar, p.f14754w);
        this.f21351g = ((s8.a) eVar.f2140l).f21645a.d(new t0(this, 4));
    }

    @Override // q8.b, h8.b
    public final Map a() {
        kotlin.reflect.m mVar = f21350h[0];
        return (Map) this.f21351g.invoke();
    }
}
