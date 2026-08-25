package androidx.core.os;

/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f2122b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2123c;

    public final void a(f fVar) {
        synchronized (this) {
            while (this.f2123c) {
                try {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                } finally {
                }
            }
            if (this.f2122b == fVar) {
                return;
            }
            this.f2122b = fVar;
            if (this.f2121a) {
                fVar.onCancel();
            }
        }
    }
}
