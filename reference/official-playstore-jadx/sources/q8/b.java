package q8;

import g8.v0;
import io.ktor.http.LinkHeader;
import java.util.Map;
import kotlin.collections.a0;
import kotlin.collections.x;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import u9.s;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public class b implements h8.b, r8.i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21332f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.c f21333a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v0 f21334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s f21335c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w8.b f21336d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f21337e;

    static {
        m0 m0Var = l0.f19747a;
        f21332f = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(b.class), LinkHeader.Parameters.Type, "getType()Lorg/jetbrains/kotlin/types/SimpleType;"))};
    }

    public b(androidx.core.provider.e eVar, w8.a aVar, f9.c cVar) {
        s8.a aVar2 = (s8.a) eVar.f2140l;
        this.f21333a = cVar;
        this.f21334b = aVar != null ? aVar2.f21654j.c(aVar) : v0.f15554b;
        this.f21335c = aVar2.f21645a.d(new a8.l0(eVar, this, 18));
        this.f21336d = aVar != null ? (w8.b) x.n0(aVar.g()) : null;
        this.f21337e = false;
    }

    @Override // h8.b
    public Map a() {
        return a0.f19683i;
    }

    @Override // r8.i
    public final boolean b() {
        return this.f21337e;
    }

    @Override // h8.b
    public final f9.c c() {
        return this.f21333a;
    }

    @Override // h8.b
    public final v0 f() {
        return this.f21334b;
    }

    @Override // h8.b
    public final w getType() {
        kotlin.reflect.m mVar = f21332f[0];
        return (v9.b0) this.f21335c.invoke();
    }
}
