package u0;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f22014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f22015d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f22016e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f22017f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f22018g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f22019h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22020i;

    public final float a(long j10) {
        if (j10 < this.f22016e) {
            return 0.0f;
        }
        long j11 = this.f22018g;
        if (j11 < 0 || j10 < j11) {
            return h.b((j10 - r0) / this.f22012a, 0.0f, 1.0f) * 0.5f;
        }
        float f10 = this.f22019h;
        return (h.b((j10 - j11) / this.f22020i, 0.0f, 1.0f) * f10) + (1.0f - f10);
    }
}
