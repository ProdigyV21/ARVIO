package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Throwable f22576i;

    public c0(Throwable th) {
        this.f22576i = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c0) {
            return kotlin.jvm.internal.p.a(this.f22576i, ((c0) obj).f22576i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f22576i.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.f22576i + ')';
    }
}
