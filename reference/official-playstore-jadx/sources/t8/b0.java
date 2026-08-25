package t8;

import java.util.Collection;
import java.util.Set;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends ea.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g8.f f21903b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Set f21904c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ kotlin.jvm.internal.r f21905d;

    /* JADX WARN: Multi-variable type inference failed */
    public b0(g8.f fVar, Set set, r7.l lVar) {
        this.f21903b = fVar;
        this.f21904c = set;
        this.f21905d = (kotlin.jvm.internal.r) lVar;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // ea.c
    public final boolean b(Object obj) {
        g8.f fVar = (g8.f) obj;
        if (fVar == this.f21903b) {
            return true;
        }
        p9.n nVarJ0 = fVar.j0();
        if (!(nVarJ0 instanceof d0)) {
            return true;
        }
        this.f21904c.addAll((Collection) this.f21905d.invoke(nVarJ0));
        return false;
    }

    @Override // ea.c
    public final /* bridge */ /* synthetic */ Object result() {
        return t0.f22605a;
    }
}
