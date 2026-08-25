package e3;

import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements Provider, d3.e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f15025c = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile d f15026a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Object f15027b;

    public static Provider a(d dVar) {
        if (dVar instanceof c) {
            return dVar;
        }
        c cVar = new c();
        cVar.f15027b = f15025c;
        cVar.f15026a = dVar;
        return cVar;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        Object obj;
        Object obj2 = this.f15027b;
        Object obj3 = f15025c;
        if (obj2 != obj3) {
            return obj2;
        }
        synchronized (this) {
            try {
                obj = this.f15027b;
                if (obj == obj3) {
                    obj = this.f15026a.get();
                    Object obj4 = this.f15027b;
                    if (obj4 != obj3 && obj4 != obj) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj4 + " & " + obj + ". This is likely due to a circular dependency.");
                    }
                    this.f15027b = obj;
                    this.f15026a = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }
}
