package androidx.work.impl.utils;

import java.util.HashMap;
import m2.d0;

/* JADX INFO: loaded from: classes3.dex */
public final class v {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f7082e = m2.t.f("WorkTimer");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f7083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f7084b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f7085c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f7086d = new Object();

    public v(androidx.work.impl.c cVar) {
        this.f7083a = cVar;
    }

    public final void a(androidx.work.impl.model.j jVar) {
        synchronized (this.f7086d) {
            try {
                if (((u) this.f7084b.remove(jVar)) != null) {
                    m2.t.d().a(f7082e, "Stopping timer for " + jVar);
                    this.f7085c.remove(jVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
