package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 extends d1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f7510l;

    public h0(String str, i0 i0Var) {
        super(str, i0Var, 1);
        this.f7510l = true;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object, x6.s] */
    @Override // cb.d1
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h0) {
            ab.g gVar = (ab.g) obj;
            if (kotlin.jvm.internal.p.a(this.f7477a, gVar.h())) {
                h0 h0Var = (h0) obj;
                if (h0Var.f7510l && Arrays.equals((ab.g[]) this.f7486j.getValue(), (ab.g[]) h0Var.f7486j.getValue())) {
                    int iD = gVar.d();
                    int i10 = this.f7479c;
                    if (i10 == iD) {
                        for (int i11 = 0; i11 < i10; i11++) {
                            if (kotlin.jvm.internal.p.a(g(i11).h(), gVar.g(i11).h()) && kotlin.jvm.internal.p.a(g(i11).getKind(), gVar.g(i11).getKind())) {
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // cb.d1
    public final int hashCode() {
        return super.hashCode() * 31;
    }

    @Override // cb.d1, ab.g
    public final boolean isInline() {
        return this.f7510l;
    }
}
