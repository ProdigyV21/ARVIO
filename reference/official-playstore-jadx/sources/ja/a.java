package ja;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19410a = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "value");
    private volatile Object value;

    public a(Object obj) {
        this.value = obj;
    }

    public final boolean a(Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = f19410a;
            if (atomicReferenceFieldUpdater.compareAndSet(this, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(this) == obj);
        return false;
    }

    public final Object b() {
        return this.value;
    }

    public final void c(Object obj) {
        this.value = obj;
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
