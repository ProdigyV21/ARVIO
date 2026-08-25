package cb;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class h1 implements ab.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7511a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ab.f f7512b;

    public h1(String str, ab.f fVar) {
        this.f7511a = str;
        this.f7512b = fVar;
    }

    @Override // ab.g
    public final boolean b() {
        return false;
    }

    @Override // ab.g
    public final int c(String str) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // ab.g
    public final int d() {
        return 0;
    }

    @Override // ab.g
    public final String e(int i10) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h1)) {
            return false;
        }
        h1 h1Var = (h1) obj;
        return kotlin.jvm.internal.p.a(this.f7511a, h1Var.f7511a) && kotlin.jvm.internal.p.a(this.f7512b, h1Var.f7512b);
    }

    @Override // ab.g
    public final List f(int i10) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // ab.g
    public final ab.g g(int i10) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // ab.g
    public final List getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // ab.g
    public final a.a getKind() {
        return this.f7512b;
    }

    @Override // ab.g
    public final String h() {
        return this.f7511a;
    }

    public final int hashCode() {
        return (this.f7512b.hashCode() * 31) + this.f7511a.hashCode();
    }

    @Override // ab.g
    public final boolean i(int i10) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    @Override // ab.g
    public final boolean isInline() {
        return false;
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("PrimitiveDescriptor("), this.f7511a, ')');
    }
}
