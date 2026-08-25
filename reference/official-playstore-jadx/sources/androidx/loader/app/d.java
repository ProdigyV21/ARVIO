package androidx.loader.app;

import androidx.lifecycle.k0;
import androidx.lifecycle.q0;
import e.i;
import g8.b1;
import g8.h;
import io.sentry.android.core.SentryAndroidOptions;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.LinkedBlockingQueue;
import ka.m0;
import ka.o2;
import m2.f0;
import org.slf4j.ILoggerFactory;
import r7.p;
import v9.o0;
import w9.c;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements k0, c.a, ILoggerFactory {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3322i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3323l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f3324m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f3325n;

    public d(g8.a aVar, g8.a aVar2, boolean z) {
        this.f3322i = 2;
        this.f3323l = z;
        this.f3324m = aVar;
        this.f3325n = aVar2;
    }

    public static void g(BufferedInputStream bufferedInputStream, long j10) throws IOException {
        while (j10 > 0) {
            long jSkip = bufferedInputStream.skip(j10);
            if (jSkip != 0) {
                j10 -= jSkip;
            } else {
                if (bufferedInputStream.read() == -1) {
                    throw new EOFException("Unexpected end of stream while skipping bytes");
                }
                j10--;
            }
        }
    }

    @Override // org.slf4j.ILoggerFactory
    public synchronized vc.b a(String str) {
        xc.b bVar;
        bVar = (xc.b) ((HashMap) this.f3324m).get(str);
        if (bVar == null) {
            bVar = new xc.b(str, (LinkedBlockingQueue) this.f3325n, this.f3323l);
            ((HashMap) this.f3324m).put(str, bVar);
        }
        return bVar;
    }

    @Override // w9.c.a
    public boolean b(o0 o0Var, o0 o0Var2) {
        boolean z = this.f3323l;
        g8.a aVar = (g8.a) this.f3324m;
        g8.a aVar2 = (g8.a) this.f3325n;
        if (o0Var.equals(o0Var2)) {
            return true;
        }
        h hVarE = o0Var.e();
        h hVarE2 = o0Var2.e();
        if (!(hVarE instanceof b1) || !(hVarE2 instanceof b1)) {
            return false;
        }
        i9.b bVar = new i9.b(aVar, aVar2, 0);
        return i9.d.f16248a.b((b1) hVarE, (b1) hVarE2, z, bVar);
    }

    @Override // androidx.lifecycle.k0
    public void c(Object obj) {
        ((a) this.f3325n).onLoadFinished((androidx.loader.content.e) this.f3324m, obj);
        this.f3323l = true;
    }

    public void d() throws IllegalAccessException, InvocationTargetException {
        ((ma.f) this.f3324m).g(new CancellationException("onBack cancelled"), true);
        ((o2) this.f3325n).cancel((CancellationException) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0058 A[Catch: all -> 0x0045, TryCatch #1 {all -> 0x0045, blocks: (B:5:0x0011, B:6:0x001d, B:8:0x0025, B:21:0x0058, B:13:0x0038, B:15:0x0040, B:18:0x0047, B:20:0x004f, B:24:0x005f, B:27:0x0069), top: B:50:0x0011, outer: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.sentry.android.core.d1 e(java.io.BufferedInputStream r10, int r11, java.io.File r12) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.f3324m
            io.sentry.android.core.SentryAndroidOptions r0 = (io.sentry.android.core.SentryAndroidOptions) r0
            r1 = 0
            io.sentry.android.core.c1 r2 = new io.sentry.android.core.c1     // Catch: java.lang.Throwable -> L7a
            r2.<init>(r10, r11)     // Catch: java.lang.Throwable -> L7a
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L7c
            java.nio.charset.Charset r11 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L7c
            r10.<init>(r2, r11)     // Catch: java.lang.Throwable -> L7c
            io.sentry.h2 r11 = new io.sentry.h2     // Catch: java.lang.Throwable -> L45
            r11.<init>(r10)     // Catch: java.lang.Throwable -> L45
            io.sentry.vendor.gson.stream.a r3 = r11.f17313i     // Catch: java.lang.Throwable -> L45
            r11.beginObject()     // Catch: java.lang.Throwable -> L45
            r4 = r1
            r5 = r4
        L1d:
            io.sentry.vendor.gson.stream.b r6 = r3.peek()     // Catch: java.lang.Throwable -> L45
            io.sentry.vendor.gson.stream.b r7 = io.sentry.vendor.gson.stream.b.NAME     // Catch: java.lang.Throwable -> L45
            if (r6 != r7) goto L5f
            java.lang.String r6 = r3.nextName()     // Catch: java.lang.Throwable -> L45
            int r7 = r6.hashCode()     // Catch: java.lang.Throwable -> L45
            r8 = 55126294(0x3492916, float:5.9115755E-37)
            if (r7 == r8) goto L47
            r8 = 1874684019(0x6fbd6873, float:1.1723788E29)
            if (r7 == r8) goto L38
            goto L58
        L38:
            java.lang.String r7 = "platform"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L45
            if (r6 == 0) goto L58
            java.lang.String r4 = r11.l0()     // Catch: java.lang.Throwable -> L45
            goto L5b
        L45:
            r11 = move-exception
            goto L7e
        L47:
            java.lang.String r7 = "timestamp"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L45
            if (r6 == 0) goto L58
            io.sentry.ILogger r5 = r0.getLogger()     // Catch: java.lang.Throwable -> L45
            java.util.Date r5 = r11.I(r5)     // Catch: java.lang.Throwable -> L45
            goto L5b
        L58:
            r11.skipValue()     // Catch: java.lang.Throwable -> L45
        L5b:
            if (r4 == 0) goto L1d
            if (r5 == 0) goto L1d
        L5f:
            java.lang.String r11 = "native"
            boolean r11 = r11.equals(r4)     // Catch: java.lang.Throwable -> L45
            if (r11 == 0) goto L73
            if (r5 == 0) goto L73
            io.sentry.android.core.d1 r11 = new io.sentry.android.core.d1     // Catch: java.lang.Throwable -> L45
            long r3 = r5.getTime()     // Catch: java.lang.Throwable -> L45
            r11.<init>(r12, r3)     // Catch: java.lang.Throwable -> L45
            r1 = r11
        L73:
            r10.close()     // Catch: java.lang.Throwable -> L7c
            r2.close()     // Catch: java.lang.Throwable -> L7a
            return r1
        L7a:
            r10 = move-exception
            goto L90
        L7c:
            r10 = move-exception
            goto L87
        L7e:
            r10.close()     // Catch: java.lang.Throwable -> L82
            goto L86
        L82:
            r10 = move-exception
            r11.addSuppressed(r10)     // Catch: java.lang.Throwable -> L7c
        L86:
            throw r11     // Catch: java.lang.Throwable -> L7c
        L87:
            r2.close()     // Catch: java.lang.Throwable -> L8b
            goto L8f
        L8b:
            r11 = move-exception
            r10.addSuppressed(r11)     // Catch: java.lang.Throwable -> L7a
        L8f:
            throw r10     // Catch: java.lang.Throwable -> L7a
        L90:
            io.sentry.ILogger r11 = r0.getLogger()
            io.sentry.w5 r0 = io.sentry.w5.DEBUG
            java.lang.String r12 = r12.getName()
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r12
            java.lang.String r12 = "Error parsing event JSON from: %s"
            r11.k(r0, r10, r12, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.loader.app.d.e(java.io.BufferedInputStream, int, java.io.File):io.sentry.android.core.d1");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0055 A[Catch: all -> 0x0046, TryCatch #1 {all -> 0x0046, blocks: (B:4:0x0011, B:5:0x001d, B:7:0x0025, B:20:0x0055, B:12:0x0038, B:14:0x0041, B:17:0x0048, B:19:0x0050, B:24:0x005e), top: B:40:0x0011, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.mediarouter.media.q1 f(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L67
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L67
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L67
            byte[] r9 = r9.getBytes(r3)     // Catch: java.lang.Throwable -> L67
            r2.<init>(r9)     // Catch: java.lang.Throwable -> L67
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L67
            io.sentry.h2 r9 = new io.sentry.h2     // Catch: java.lang.Throwable -> L46
            r9.<init>(r1)     // Catch: java.lang.Throwable -> L46
            io.sentry.vendor.gson.stream.a r2 = r9.f17313i     // Catch: java.lang.Throwable -> L46
            r9.beginObject()     // Catch: java.lang.Throwable -> L46
            r3 = -1
            r4 = r0
        L1d:
            io.sentry.vendor.gson.stream.b r5 = r2.peek()     // Catch: java.lang.Throwable -> L46
            io.sentry.vendor.gson.stream.b r6 = io.sentry.vendor.gson.stream.b.NAME     // Catch: java.lang.Throwable -> L46
            if (r5 != r6) goto L5c
            java.lang.String r5 = r2.nextName()     // Catch: java.lang.Throwable -> L46
            int r6 = r5.hashCode()     // Catch: java.lang.Throwable -> L46
            r7 = -1106363674(0xffffffffbe0e3ae6, float:-0.13889655)
            if (r6 == r7) goto L48
            r7 = 3575610(0x368f3a, float:5.010497E-39)
            if (r6 == r7) goto L38
            goto L55
        L38:
            java.lang.String r6 = "type"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L46
            if (r5 == 0) goto L55
            java.lang.String r4 = r9.l0()     // Catch: java.lang.Throwable -> L46
            goto L58
        L46:
            r9 = move-exception
            goto L6d
        L48:
            java.lang.String r6 = "length"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L46
            if (r5 == 0) goto L55
            int r3 = r9.nextInt()     // Catch: java.lang.Throwable -> L46
            goto L58
        L55:
            r9.skipValue()     // Catch: java.lang.Throwable -> L46
        L58:
            if (r4 == 0) goto L1d
            if (r3 < 0) goto L1d
        L5c:
            if (r3 < 0) goto L69
            androidx.mediarouter.media.q1 r9 = new androidx.mediarouter.media.q1     // Catch: java.lang.Throwable -> L46
            r9.<init>(r4, r3)     // Catch: java.lang.Throwable -> L46
            r1.close()     // Catch: java.lang.Throwable -> L67
            return r9
        L67:
            r9 = move-exception
            goto L76
        L69:
            r1.close()     // Catch: java.lang.Throwable -> L67
            return r0
        L6d:
            r1.close()     // Catch: java.lang.Throwable -> L71
            goto L75
        L71:
            r1 = move-exception
            r9.addSuppressed(r1)     // Catch: java.lang.Throwable -> L67
        L75:
            throw r9     // Catch: java.lang.Throwable -> L67
        L76:
            java.lang.Object r1 = r8.f3324m
            io.sentry.android.core.SentryAndroidOptions r1 = (io.sentry.android.core.SentryAndroidOptions) r1
            io.sentry.ILogger r1 = r1.getLogger()
            io.sentry.w5 r2 = io.sentry.w5.DEBUG
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = "Error parsing item header"
            r1.k(r2, r9, r4, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.loader.app.d.f(java.lang.String):androidx.mediarouter.media.q1");
    }

    public String toString() {
        switch (this.f3322i) {
            case 0:
                return ((a) this.f3325n).toString();
            default:
                return super.toString();
        }
    }

    public d() {
        this.f3322i = 4;
        this.f3323l = false;
        this.f3324m = new HashMap();
        this.f3325n = new LinkedBlockingQueue();
    }

    public d(SentryAndroidOptions sentryAndroidOptions) {
        this.f3322i = 3;
        this.f3325n = new ArrayList();
        this.f3323l = false;
        this.f3324m = sentryAndroidOptions;
    }

    public d(ka.k0 k0Var, boolean z, p pVar, i iVar) {
        this.f3322i = 1;
        this.f3323l = z;
        this.f3324m = f0.a(-2, 1, 4);
        this.f3325n = m0.p(k0Var, null, 0, new q0(iVar, pVar, this, null, 2), 3);
    }

    public d(androidx.loader.content.e eVar, a aVar) {
        this.f3322i = 0;
        this.f3323l = false;
        this.f3324m = eVar;
        this.f3325n = aVar;
    }
}
