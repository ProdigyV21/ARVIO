package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class y {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long[] f7588e = new long[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ab.g f7589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final eb.n f7590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f7591c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long[] f7592d;

    public y(ab.g gVar, eb.n nVar) {
        this.f7589a = gVar;
        this.f7590b = nVar;
        int iD = gVar.d();
        if (iD <= 64) {
            this.f7591c = iD != 64 ? (-1) << iD : 0L;
            this.f7592d = f7588e;
            return;
        }
        this.f7591c = 0L;
        int i10 = (iD - 1) >>> 6;
        long[] jArr = new long[i10];
        if ((iD & 63) != 0) {
            jArr[i10 - 1] = (-1) << iD;
        }
        this.f7592d = jArr;
    }
}
