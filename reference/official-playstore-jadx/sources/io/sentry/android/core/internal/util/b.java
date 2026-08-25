package io.sentry.android.core.internal.util;

import android.net.ConnectivityManager;
import android.net.Network;
import android.os.SystemClock;
import io.sentry.o0;
import io.sentry.w5;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f16584a;

    public b(c cVar) {
        this.f16584a = cVar;
    }

    public final void a() {
        this.f16584a.f16598u.set(false);
        io.sentry.util.a aVarA = this.f16584a.f16593p.a();
        try {
            this.f16584a.f16595r = null;
            this.f16584a.f16596s = null;
            c cVar = this.f16584a;
            cVar.f16591n.getClass();
            cVar.f16597t = SystemClock.uptimeMillis();
            this.f16584a.f16589l.getLogger().q(w5.DEBUG, "Cache cleared - network lost/unavailable", new Object[0]);
            Iterator it = this.f16584a.f16592o.iterator();
            while (it.hasNext()) {
                ((o0.b) it.next()).i(o0.a.DISCONNECTED);
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        this.f16584a.f16596s = network;
        if (this.f16584a.f16598u.getAndSet(true)) {
            return;
        }
        io.sentry.util.a aVarA = c.x.a();
        try {
            Iterator it = c.f16587y.iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onAvailable(network);
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006c A[Catch: all -> 0x0076, LOOP:3: B:31:0x0066->B:33:0x006c, LOOP_END, TRY_LEAVE, TryCatch #2 {all -> 0x0076, blocks: (B:30:0x005e, B:31:0x0066, B:33:0x006c), top: B:63:0x005e }] */
    @Override // android.net.ConnectivityManager.NetworkCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCapabilitiesChanged(android.net.Network r8, android.net.NetworkCapabilities r9) {
        /*
            r7 = this;
            io.sentry.android.core.internal.util.c r0 = r7.f16584a
            android.net.Network r0 = r0.f16596s
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto Lb
            return
        Lb:
            io.sentry.android.core.internal.util.c r0 = r7.f16584a
            android.net.NetworkCapabilities r0 = r0.f16595r
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L15
            r3 = r2
            goto L16
        L15:
            r3 = r1
        L16:
            if (r9 != 0) goto L19
            goto L1a
        L19:
            r2 = r1
        L1a:
            if (r3 == r2) goto L1d
            goto L4b
        L1d:
            if (r0 != 0) goto L22
            if (r9 != 0) goto L22
            goto L88
        L22:
            int[] r2 = io.sentry.android.core.internal.util.c.A
            int r3 = r2.length
            r4 = r1
        L26:
            if (r4 >= r3) goto L3a
            r5 = r2[r4]
            if (r5 == 0) goto L37
            boolean r6 = r0.hasCapability(r5)
            boolean r5 = r9.hasCapability(r5)
            if (r6 == r5) goto L37
            goto L4b
        L37:
            int r4 = r4 + 1
            goto L26
        L3a:
            int[] r2 = io.sentry.android.core.internal.util.c.z
            int r3 = r2.length
        L3d:
            if (r1 >= r3) goto L88
            r4 = r2[r1]
            boolean r5 = r0.hasTransport(r4)
            boolean r4 = r9.hasTransport(r4)
            if (r5 == r4) goto L85
        L4b:
            io.sentry.android.core.internal.util.c r0 = r7.f16584a
            r0.V(r9)
            io.sentry.android.core.internal.util.c r0 = r7.f16584a
            io.sentry.o0$a r0 = r0.o()
            io.sentry.android.core.internal.util.c r1 = r7.f16584a
            io.sentry.util.b r1 = r1.f16593p
            io.sentry.util.a r1 = r1.a()
            io.sentry.android.core.internal.util.c r2 = r7.f16584a     // Catch: java.lang.Throwable -> L76
            java.util.ArrayList r2 = r2.f16592o     // Catch: java.lang.Throwable -> L76
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L76
        L66:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L78
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L76
            io.sentry.o0$b r3 = (io.sentry.o0.b) r3     // Catch: java.lang.Throwable -> L76
            r3.i(r0)     // Catch: java.lang.Throwable -> L76
            goto L66
        L76:
            r8 = move-exception
            goto L7c
        L78:
            r1.close()
            goto L88
        L7c:
            r1.close()     // Catch: java.lang.Throwable -> L80
            goto L84
        L80:
            r9 = move-exception
            r8.addSuppressed(r9)
        L84:
            throw r8
        L85:
            int r1 = r1 + 1
            goto L3d
        L88:
            io.sentry.util.b r0 = io.sentry.android.core.internal.util.c.x
            io.sentry.util.a r0 = r0.a()
            java.util.ArrayList r1 = io.sentry.android.core.internal.util.c.f16587y     // Catch: java.lang.Throwable -> La4
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> La4
        L94:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> La4
            if (r2 == 0) goto La6
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> La4
            android.net.ConnectivityManager$NetworkCallback r2 = (android.net.ConnectivityManager.NetworkCallback) r2     // Catch: java.lang.Throwable -> La4
            r2.onCapabilitiesChanged(r8, r9)     // Catch: java.lang.Throwable -> La4
            goto L94
        La4:
            r8 = move-exception
            goto Laa
        La6:
            r0.close()
            return
        Laa:
            r0.close()     // Catch: java.lang.Throwable -> Lae
            goto Lb2
        Lae:
            r9 = move-exception
            r8.addSuppressed(r9)
        Lb2:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.util.b.onCapabilitiesChanged(android.net.Network, android.net.NetworkCapabilities):void");
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        if (network.equals(this.f16584a.f16596s)) {
            a();
            io.sentry.util.a aVarA = c.x.a();
            try {
                Iterator it = c.f16587y.iterator();
                while (it.hasNext()) {
                    ((ConnectivityManager.NetworkCallback) it.next()).onLost(network);
                }
                aVarA.close();
            } catch (Throwable th) {
                try {
                    aVarA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onUnavailable() {
        a();
        io.sentry.util.a aVarA = c.x.a();
        try {
            Iterator it = c.f16587y.iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onUnavailable();
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
