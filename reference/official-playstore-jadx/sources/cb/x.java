package cb;

import io.ktor.util.date.GMTDateParser;

/* JADX INFO: loaded from: classes5.dex */
public final class x implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f7582a = new x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f7583b = new h1("kotlin.time.Duration", ab.e.f797j);

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        io.sentry.util.l lVar = kotlin.time.c.f19956l;
        String strV = dVar.v();
        try {
            long jB = kotlin.reflect.b0.B(strV);
            if (jB == kotlin.time.c.f19959o) {
                throw new IllegalStateException("invariant failed");
            }
            return new kotlin.time.c(jB);
        } catch (IllegalArgumentException e5) {
            throw new IllegalArgumentException(a0.c.l("Invalid ISO duration string format: '", strV, "'."), e5);
        }
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7583b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        long j10;
        long j11;
        int iM;
        long j12 = ((kotlin.time.c) obj).f19960i;
        io.sentry.util.l lVar = kotlin.time.c.f19956l;
        StringBuilder sb2 = new StringBuilder();
        if (j12 < 0) {
            sb2.append('-');
        }
        sb2.append("PT");
        boolean z = true;
        if (j12 < 0) {
            j10 = ((-(j12 >> 1)) << 1) + ((long) (((int) j12) & 1));
            int i10 = kotlin.time.d.f19961a;
        } else {
            j10 = j12;
        }
        long jM = kotlin.time.c.m(j10, kotlin.time.e.HOURS);
        if (kotlin.time.c.k(j10)) {
            j11 = 0;
            iM = 0;
        } else {
            j11 = 0;
            iM = (int) (kotlin.time.c.m(j10, kotlin.time.e.MINUTES) % ((long) 60));
        }
        int iM2 = kotlin.time.c.k(j10) ? 0 : (int) (kotlin.time.c.m(j10, kotlin.time.e.SECONDS) % ((long) 60));
        int iJ = kotlin.time.c.j(j10);
        if (kotlin.time.c.k(j12)) {
            jM = 9999999999999L;
        }
        boolean z5 = jM != j11;
        boolean z10 = (iM2 == 0 && iJ == 0) ? false : true;
        if (iM == 0 && (!z10 || !z5)) {
            z = false;
        }
        if (z5) {
            sb2.append(jM);
            sb2.append('H');
        }
        if (z) {
            sb2.append(iM);
            sb2.append(GMTDateParser.MONTH);
        }
        if (z10 || (!z5 && !z)) {
            kotlin.time.c.d(sb2, iM2, iJ, 9, "S", true);
        }
        eVar.E(sb2.toString());
    }
}
