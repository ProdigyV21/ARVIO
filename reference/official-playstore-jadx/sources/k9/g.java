package k9;

import g8.b0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f19530a;

    public g(Object obj) {
        this.f19530a = obj;
    }

    public abstract v9.w a(b0 b0Var);

    public Object b() {
        return this.f19530a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Object objB = b();
        g gVar = obj instanceof g ? (g) obj : null;
        return kotlin.jvm.internal.p.a(objB, gVar != null ? gVar.b() : null);
    }

    public final int hashCode() {
        Object objB = b();
        if (objB != null) {
            return objB.hashCode();
        }
        return 0;
    }

    public String toString() {
        return String.valueOf(b());
    }
}
