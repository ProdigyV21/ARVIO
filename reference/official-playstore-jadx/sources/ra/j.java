package ra;

import ka.m0;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends h {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Runnable f21431m;

    public j(Runnable runnable, long j10, i iVar) {
        super(j10, iVar);
        this.f21431m = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f21431m.run();
        } finally {
            this.f21430l.getClass();
        }
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Task[");
        Runnable runnable = this.f21431m;
        sb2.append(runnable.getClass().getSimpleName());
        sb2.append('@');
        sb2.append(m0.j(runnable));
        sb2.append(", ");
        sb2.append(this.f21429i);
        sb2.append(", ");
        sb2.append(this.f21430l);
        sb2.append(']');
        return sb2.toString();
    }
}
