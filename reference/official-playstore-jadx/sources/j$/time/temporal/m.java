package j$.time.temporal;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18365b;

    public /* synthetic */ m(int i10, int i11) {
        this.f18364a = i11;
        this.f18365b = i10;
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        switch (this.f18364a) {
            case 0:
                int iL = temporal.l(a.DAY_OF_WEEK);
                int i10 = this.f18365b;
                if (iL == i10) {
                    return temporal;
                }
                return temporal.d(iL - i10 >= 0 ? 7 - r0 : -r0, ChronoUnit.DAYS);
            default:
                int iL2 = temporal.l(a.DAY_OF_WEEK);
                int i11 = this.f18365b;
                if (iL2 == i11) {
                    return temporal;
                }
                return temporal.v(i11 - iL2 >= 0 ? 7 - r1 : -r1, ChronoUnit.DAYS);
        }
    }
}
