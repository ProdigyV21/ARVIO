package kotlin.jvm.internal;

import coil.compose.AsyncImagePainter;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements k, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f19729i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f19730l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f19731m = 2;

    public a(Object obj) {
        this.f19729i = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f19730l == aVar.f19730l && this.f19731m == aVar.f19731m && p.a(this.f19729i, aVar.f19729i) && AsyncImagePainter.class.equals(AsyncImagePainter.class);
    }

    @Override // kotlin.jvm.internal.k
    public final int getArity() {
        return 2;
    }

    public final int hashCode() {
        return ((((((((((AsyncImagePainter.class.hashCode() + ((this.f19729i != null ? r0.hashCode() : 0) * 31)) * 31) - 584535736) * 31) + 1554688608) * 31) + (this.f19730l ? 1231 : 1237)) * 31) + 2) * 31) + this.f19731m;
    }

    public final String toString() {
        return l0.f19747a.i(this);
    }
}
