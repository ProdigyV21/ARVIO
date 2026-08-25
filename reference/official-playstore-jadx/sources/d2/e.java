package d2;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends RuntimeException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14663i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Throwable f14664l;

    public e(Throwable th, int i10) {
        super(th);
        this.f14663i = i10;
        this.f14664l = th;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.f14664l;
    }
}
