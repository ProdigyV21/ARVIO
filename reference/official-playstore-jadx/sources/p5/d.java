package p5;

import com.google.firebase.installations.h;
import io.sentry.util.l;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f21066d = TimeUnit.HOURS.toMillis(24);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f21067e = TimeUnit.MINUTES.toMillis(30);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f21068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f21069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f21070c;

    public d() {
        if (l.f17964l == null) {
            Pattern pattern = h.f14388c;
            l.f17964l = new l(16);
        }
        l lVar = l.f17964l;
        if (h.f14389d == null) {
            h.f14389d = new h(lVar);
        }
        this.f21068a = h.f14389d;
    }

    public final synchronized long a(int i10) {
        if (!(i10 == 429 || (i10 >= 500 && i10 < 600))) {
            return f21066d;
        }
        double dPow = Math.pow(2.0d, this.f21070c);
        this.f21068a.getClass();
        return (long) Math.min(dPow + ((long) (Math.random() * 1000.0d)), f21067e);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized boolean b() {
        /*
            r4 = this;
            monitor-enter(r4)
            int r0 = r4.f21070c     // Catch: java.lang.Throwable -> L16
            if (r0 == 0) goto L18
            com.google.firebase.installations.h r0 = r4.f21068a     // Catch: java.lang.Throwable -> L16
            q5.a r0 = r0.f14390a     // Catch: java.lang.Throwable -> L16
            long r0 = r0.currentTimeMillis()     // Catch: java.lang.Throwable -> L16
            long r2 = r4.f21069b     // Catch: java.lang.Throwable -> L16
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L14
            goto L18
        L14:
            r0 = 0
            goto L19
        L16:
            r0 = move-exception
            goto L1b
        L18:
            r0 = 1
        L19:
            monitor-exit(r4)
            return r0
        L1b:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L16
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p5.d.b():boolean");
    }

    public final synchronized void c() {
        this.f21070c = 0;
    }

    public final synchronized void d(int i10) {
        if ((i10 >= 200 && i10 < 300) || i10 == 401 || i10 == 404) {
            c();
            return;
        }
        this.f21070c++;
        this.f21069b = this.f21068a.f14390a.currentTimeMillis() + a(i10);
    }
}
