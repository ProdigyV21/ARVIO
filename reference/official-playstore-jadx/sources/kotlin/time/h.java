package kotlin.time;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f19970e = new h(4611686018427387903L, true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19971a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f19972b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f19973c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f19974d;

    static {
        new h(Long.MAX_VALUE, false);
    }

    public h(long j10, boolean z) {
        this.f19971a = j10;
        this.f19972b = z;
        long j11 = 10;
        this.f19973c = j10 / j11;
        this.f19974d = j10 % j11;
    }
}
