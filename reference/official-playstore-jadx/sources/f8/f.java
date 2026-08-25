package f8;

import g8.b0;
import j8.d0;
import java.util.Collection;
import java.util.Collections;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import u9.s;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements i8.c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f15311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f15312d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f9.c f15313e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f9.f f15314f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f9.b f15315g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f15316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f15317b;

    static {
        m0 m0Var = l0.f19747a;
        f15312d = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(f.class), "cloneable", "getCloneable()Lorg/jetbrains/kotlin/descriptors/impl/ClassDescriptorImpl;"))};
        f15311c = new e();
        f15313e = d8.q.f14766l;
        f9.e eVar = d8.p.f14735c;
        f15314f = eVar.f();
        f15315g = f9.b.j(eVar.g());
    }

    public f(u9.p pVar, d0 d0Var) {
        this.f15316a = d0Var;
        this.f15317b = new u9.l(pVar, new a8.l0(this, pVar, 11));
    }

    @Override // i8.c
    public final boolean a(f9.c cVar, f9.f fVar) {
        return fVar.equals(f15314f) && cVar.equals(f15313e);
    }

    @Override // i8.c
    public final g8.f b(f9.b bVar) {
        if (!bVar.equals(f15315g)) {
            return null;
        }
        kotlin.reflect.m mVar = f15312d[0];
        return (j8.m) this.f15317b.invoke();
    }

    @Override // i8.c
    public final Collection c(f9.c cVar) {
        if (!cVar.equals(f15313e)) {
            return kotlin.collections.b0.f19686i;
        }
        kotlin.reflect.m mVar = f15312d[0];
        return Collections.singleton((j8.m) this.f15317b.invoke());
    }
}
