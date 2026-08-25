package f8;

import h.f0;
import kotlin.collections.x;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import u9.s;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends d8.k {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f15319h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d8.n f15320f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final s f15321g;

    static {
        m0 m0Var = l0.f19747a;
        f15319h = new kotlin.reflect.m[]{m0Var.g(new b0(m0Var.b(h.class), "customizer", "getCustomizer()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsCustomizer;"))};
    }

    public h(u9.p pVar) {
        super(pVar);
        this.f15321g = new u9.l(pVar, new a8.l0(this, pVar, 12));
        int iC = f0.c(1);
        if (iC == 1) {
            c(false);
        } else {
            if (iC != 2) {
                return;
            }
            c(true);
        }
    }

    public final n K() {
        kotlin.reflect.m mVar = f15319h[0];
        return (n) this.f15321g.invoke();
    }

    @Override // d8.k
    public final i8.b d() {
        return K();
    }

    @Override // d8.k
    public final Iterable l() {
        return x.G0(super.l(), new f(this.f14709d, k()));
    }

    @Override // d8.k
    public final i8.d p() {
        return K();
    }
}
