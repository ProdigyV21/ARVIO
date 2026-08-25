package q9;

import kotlin.jvm.internal.p;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements e, h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g8.f f21359i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final g8.f f21360l;

    public c(g8.f fVar) {
        this.f21359i = fVar;
        this.f21360l = fVar;
    }

    public final boolean equals(Object obj) {
        c cVar = obj instanceof c ? (c) obj : null;
        return p.a(this.f21359i, cVar != null ? cVar.f21359i : null);
    }

    @Override // q9.f
    public final w getType() {
        return this.f21359i.m();
    }

    public final int hashCode() {
        return this.f21359i.hashCode();
    }

    @Override // q9.h
    public final g8.f r() {
        return this.f21359i;
    }

    public final String toString() {
        return "Class{" + this.f21359i.m() + '}';
    }
}
