package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class t2 extends pa.v implements Runnable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f19636o;

    public t2(long j10, f7.c cVar) {
        super(cVar.getContext(), cVar);
        this.f19636o = j10;
    }

    @Override // ka.f2
    public final String F() {
        return super.F() + "(timeMillis=" + this.f19636o + ')';
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r4 = this;
            d7.j r0 = r4.f19541m
            ka.r0 r0 = ka.s0.b(r0)
            boolean r1 = r0 instanceof ka.t0
            if (r1 == 0) goto Ld
            ka.t0 r0 = (ka.t0) r0
            goto Le
        Ld:
            r0 = 0
        Le:
            long r1 = r4.f19636o
            if (r0 == 0) goto L1f
            io.sentry.util.l r3 = kotlin.time.c.f19956l
            kotlin.time.e r3 = kotlin.time.e.MILLISECONDS
            kotlin.reflect.b0.I(r1, r3)
            java.lang.String r0 = r0.x()
            if (r0 != 0) goto L27
        L1f:
            java.lang.String r0 = "Timed out waiting for "
            java.lang.String r3 = " ms"
            java.lang.String r0 = androidx.compose.material3.d.k(r1, r0, r3)
        L27:
            kotlinx.coroutines.TimeoutCancellationException r1 = new kotlinx.coroutines.TimeoutCancellationException
            r1.<init>(r4, r0)
            r4.l(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.t2.run():void");
    }
}
