package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class d8 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Runnable f18668a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Runnable f18669b;

    public d8(Runnable runnable, Runnable runnable2) {
        this.f18668a = runnable;
        this.f18669b = runnable2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f18668a.run();
            this.f18669b.run();
        } catch (Throwable th) {
            try {
                this.f18669b.run();
            } catch (Throwable th2) {
                try {
                    th.addSuppressed(th2);
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
