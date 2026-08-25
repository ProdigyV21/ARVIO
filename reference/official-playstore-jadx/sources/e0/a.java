package e0;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f14969c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f14970d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f14971a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f14972b;

    static {
        if (g.f14986n) {
            f14970d = null;
            f14969c = null;
        } else {
            f14970d = new a(null, false);
            f14969c = new a(null, true);
        }
    }

    public a(Throwable th, boolean z) {
        this.f14971a = z;
        this.f14972b = th;
    }
}
