package ab;

import java.util.List;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f784a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.reflect.d f785b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f786c;

    public b(i iVar, kotlin.reflect.d dVar) {
        this.f784a = iVar;
        this.f785b = dVar;
        this.f786c = iVar.f800a + '<' + dVar.r() + '>';
    }

    @Override // ab.g
    public final boolean b() {
        return false;
    }

    @Override // ab.g
    public final int c(String str) {
        return this.f784a.c(str);
    }

    @Override // ab.g
    public final int d() {
        return this.f784a.f802c;
    }

    @Override // ab.g
    public final String e(int i10) {
        return this.f784a.f805f[i10];
    }

    public final boolean equals(Object obj) {
        b bVar = obj instanceof b ? (b) obj : null;
        return bVar != null && this.f784a.equals(bVar.f784a) && p.a(bVar.f785b, this.f785b);
    }

    @Override // ab.g
    public final List f(int i10) {
        return this.f784a.f807h[i10];
    }

    @Override // ab.g
    public final g g(int i10) {
        return this.f784a.f806g[i10];
    }

    @Override // ab.g
    public final List getAnnotations() {
        return this.f784a.f803d;
    }

    @Override // ab.g
    public final a.a getKind() {
        return this.f784a.f801b;
    }

    @Override // ab.g
    public final String h() {
        return this.f786c;
    }

    public final int hashCode() {
        return this.f786c.hashCode() + (this.f785b.hashCode() * 31);
    }

    @Override // ab.g
    public final boolean i(int i10) {
        return this.f784a.f808i[i10];
    }

    @Override // ab.g
    public final boolean isInline() {
        return false;
    }

    public final String toString() {
        return "ContextDescriptor(kClass: " + this.f785b + ", original: " + this.f784a + ')';
    }
}
