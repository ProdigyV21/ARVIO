package androidx.work.impl.utils;

/* JADX INFO: loaded from: classes3.dex */
public final class u implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v f7080i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.work.impl.model.j f7081l;

    public u(v vVar, androidx.work.impl.model.j jVar) {
        this.f7080i = vVar;
        this.f7081l = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f7080i.f7086d) {
            try {
                if (((u) this.f7080i.f7084b.remove(this.f7081l)) != null) {
                    t tVar = (t) this.f7080i.f7085c.remove(this.f7081l);
                    if (tVar != null) {
                        tVar.a(this.f7081l);
                    }
                } else {
                    m2.t.d().a("WrkTimerRunnable", "Timer with " + this.f7081l + " is already marked as complete.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
