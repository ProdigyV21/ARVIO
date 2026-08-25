package c1;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.x;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f7368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicBoolean f7369b;

    public b(LinkedHashMap linkedHashMap, boolean z) {
        this.f7368a = linkedHashMap;
        this.f7369b = new AtomicBoolean(z);
    }

    @Override // c1.g
    public final Object a(e eVar) {
        return this.f7368a.get(eVar);
    }

    public final void b() {
        if (this.f7369b.get()) {
            throw new IllegalStateException("Do mutate preferences once returned to DataStore.");
        }
    }

    public final void c(e eVar) {
        b();
        this.f7368a.remove(eVar);
    }

    public final void d(e eVar, Object obj) {
        b();
        if (obj == null) {
            c(eVar);
            return;
        }
        boolean z = obj instanceof Set;
        LinkedHashMap linkedHashMap = this.f7368a;
        if (z) {
            linkedHashMap.put(eVar, Collections.unmodifiableSet(x.g1((Iterable) obj)));
        } else {
            linkedHashMap.put(eVar, obj);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        return p.a(this.f7368a, ((b) obj).f7368a);
    }

    public final int hashCode() {
        return this.f7368a.hashCode();
    }

    public final String toString() {
        return x.u0(this.f7368a.entrySet(), ",\n", "{\n", "\n}", a.f7367i, 24);
    }

    public /* synthetic */ b(boolean z) {
        this(new LinkedHashMap(), z);
    }
}
