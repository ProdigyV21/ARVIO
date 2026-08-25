package cb;

import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class k1 implements ab.g, l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ab.g f7526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f7528c;

    public k1(ab.g gVar) {
        this.f7526a = gVar;
        this.f7527b = gVar.h() + '?';
        this.f7528c = b1.b(gVar);
    }

    @Override // cb.l
    public final Set a() {
        return this.f7528c;
    }

    @Override // ab.g
    public final boolean b() {
        return true;
    }

    @Override // ab.g
    public final int c(String str) {
        return this.f7526a.c(str);
    }

    @Override // ab.g
    public final int d() {
        return this.f7526a.d();
    }

    @Override // ab.g
    public final String e(int i10) {
        return this.f7526a.e(i10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof k1) {
            return kotlin.jvm.internal.p.a(this.f7526a, ((k1) obj).f7526a);
        }
        return false;
    }

    @Override // ab.g
    public final List f(int i10) {
        return this.f7526a.f(i10);
    }

    @Override // ab.g
    public final ab.g g(int i10) {
        return this.f7526a.g(i10);
    }

    @Override // ab.g
    public final List getAnnotations() {
        return this.f7526a.getAnnotations();
    }

    @Override // ab.g
    public final a.a getKind() {
        return this.f7526a.getKind();
    }

    @Override // ab.g
    public final String h() {
        return this.f7527b;
    }

    public final int hashCode() {
        return this.f7526a.hashCode() * 31;
    }

    @Override // ab.g
    public final boolean i(int i10) {
        return this.f7526a.i(i10);
    }

    @Override // ab.g
    public final boolean isInline() {
        return this.f7526a.isInline();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f7526a);
        sb2.append('?');
        return sb2.toString();
    }
}
