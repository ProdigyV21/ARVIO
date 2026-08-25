package u9;

/* JADX INFO: loaded from: classes5.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f22076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.jvm.internal.r f22077b;

    /* JADX WARN: Multi-variable type inference failed */
    public j(Object obj, r7.a aVar) {
        this.f22076a = obj;
        this.f22077b = (kotlin.jvm.internal.r) aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && j.class == obj.getClass() && this.f22076a.equals(((j) obj).f22076a);
    }

    public final int hashCode() {
        return this.f22076a.hashCode();
    }
}
