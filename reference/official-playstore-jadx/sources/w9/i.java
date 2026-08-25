package w9;

import g8.b1;
import j8.t0;
import java.util.Collection;
import java.util.List;
import kotlin.collections.z;
import v9.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements j9.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0 f22518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public r7.a f22519b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f22520c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b1 f22521d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f22522e;

    public i(r0 r0Var, r7.a aVar, i iVar, b1 b1Var) {
        this.f22518a = r0Var;
        this.f22519b = aVar;
        this.f22520c = iVar;
        this.f22521d = b1Var;
        this.f22522e = f4.f.o(2, new t0(this, 11));
    }

    @Override // j9.b
    public final r0 b() {
        return this.f22518a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // v9.o0
    public final Collection d() {
        List list = (List) this.f22522e.getValue();
        return list == null ? z.f19728i : list;
    }

    @Override // v9.o0
    public final g8.h e() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!i.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        i iVar = (i) obj;
        i iVar2 = this.f22520c;
        if (iVar2 == null) {
            iVar2 = this;
        }
        i iVar3 = iVar.f22520c;
        if (iVar3 != null) {
            iVar = iVar3;
        }
        return iVar2 == iVar;
    }

    @Override // v9.o0
    public final boolean f() {
        return false;
    }

    @Override // v9.o0
    public final List getParameters() {
        return z.f19728i;
    }

    public final int hashCode() {
        i iVar = this.f22520c;
        return iVar != null ? iVar.hashCode() : super.hashCode();
    }

    @Override // v9.o0
    public final d8.k j() {
        return this.f22518a.getType().I0().j();
    }

    public final String toString() {
        return "CapturedType(" + this.f22518a + ')';
    }

    public /* synthetic */ i(r0 r0Var, h hVar, b1 b1Var, int i10) {
        this(r0Var, (i10 & 2) != 0 ? null : hVar, (i) null, (i10 & 8) != 0 ? null : b1Var);
    }
}
