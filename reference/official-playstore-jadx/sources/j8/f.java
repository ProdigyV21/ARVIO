package j8;

import g8.a1;
import java.util.List;
import v9.b1;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f extends p implements a1 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f19269s;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final u9.v f19270o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final g8.p f19271p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f19272q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final e f19273r;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f19269s = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(f.class), "constructors", "getConstructors()Ljava/util/Collection;"))};
    }

    public f(u9.v vVar, g8.k kVar, h8.h hVar, f9.f fVar, g8.p pVar) {
        super(kVar, hVar, fVar, g8.v0.f15554b);
        this.f19270o = vVar;
        this.f19271p = pVar;
        vVar.d(new a8.g0(this, 29));
        this.f19273r = new e(this);
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.u(this, obj);
    }

    @Override // g8.a0
    public final boolean V() {
        return false;
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.h a() {
        return this;
    }

    @Override // g8.h
    public final v9.o0 g() {
        return this.f19273r;
    }

    @Override // g8.o, g8.a0
    public final g8.p getVisibility() {
        return this.f19271p;
    }

    @Override // g8.i
    public final boolean i() {
        return b1.c(((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.w) this).o0(), new ab.h(this, 19), null);
    }

    @Override // g8.a0
    public final boolean i0() {
        return false;
    }

    @Override // g8.a0
    public final boolean isExternal() {
        return false;
    }

    @Override // g8.i
    public final List n() {
        List list = this.f19272q;
        if (list != null) {
            return list;
        }
        kotlin.jvm.internal.p.i("declaredTypeParametersImpl");
        throw null;
    }

    @Override // j8.o
    public final String toString() {
        return "typealias " + getName().d();
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.k a() {
        return this;
    }

    @Override // j8.p
    /* JADX INFO: renamed from: D0 */
    public final g8.n a() {
        return this;
    }
}
