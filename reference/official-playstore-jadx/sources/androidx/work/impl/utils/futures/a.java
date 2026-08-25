package androidx.work.impl.utils.futures;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f7012c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f7013d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7014a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f7015b;

    static {
        if (i.f7033n) {
            f7013d = null;
            f7012c = null;
        } else {
            f7013d = new a(null, false);
            f7012c = new a(null, true);
        }
    }

    public a(Throwable th, boolean z) {
        this.f7014a = z;
        this.f7015b = th;
    }
}
