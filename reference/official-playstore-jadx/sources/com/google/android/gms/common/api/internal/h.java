package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class h implements Handler.Callback {
    public static h C;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f12740i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f12741l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public com.google.android.gms.common.internal.a0 f12742m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public u3.c f12743n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Context f12744o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final o3.e f12745p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f12746q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AtomicInteger f12747r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicInteger f12748s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ConcurrentHashMap f12749t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public a0 f12750u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final t.f f12751v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final t.f f12752w;
    public final androidx.loader.content.j x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public volatile boolean f12753y;
    public static final Status z = new Status(4, "Sign-out occurred while this API call was in progress.", null, null);
    public static final Status A = new Status(4, "The user must be signed in to make this API call.", null, null);
    public static final Object B = new Object();

    public h(Context context, Looper looper) {
        o3.e eVar = o3.e.f20741d;
        this.f12740i = 10000L;
        this.f12741l = false;
        this.f12747r = new AtomicInteger(1);
        this.f12748s = new AtomicInteger(0);
        this.f12749t = new ConcurrentHashMap(5, 0.75f, 1);
        this.f12750u = null;
        this.f12751v = new t.f(0);
        this.f12752w = new t.f(0);
        this.f12753y = true;
        this.f12744o = context;
        androidx.loader.content.j jVar = new androidx.loader.content.j(looper, this, 3);
        Looper.getMainLooper();
        this.x = jVar;
        this.f12745p = eVar;
        this.f12746q = new io.sentry.internal.debugmeta.c(19);
        PackageManager packageManager = context.getPackageManager();
        if (c4.d.f7406e == null) {
            c4.d.f7406e = Boolean.valueOf(c4.d.c() && packageManager.hasSystemFeature("android.hardware.type.automotive"));
        }
        if (c4.d.f7406e.booleanValue()) {
            this.f12753y = false;
        }
        jVar.sendMessage(jVar.obtainMessage(6));
    }

    public static void a() {
        synchronized (B) {
            try {
                h hVar = C;
                if (hVar != null) {
                    hVar.f12748s.incrementAndGet();
                    androidx.loader.content.j jVar = hVar.x;
                    jVar.sendMessageAtFrontOfQueue(jVar.obtainMessage(10));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static Status d(a aVar, o3.b bVar) {
        String str = aVar.f12690b.f12688c;
        String strValueOf = String.valueOf(bVar);
        return new Status(17, a2.n(new StringBuilder(String.valueOf(str).length() + 63 + strValueOf.length()), "API: ", str, " is not available on this device. Connection failed with: ", strValueOf), bVar.f20730m, bVar);
    }

    public static h e(Context context) {
        h hVar;
        synchronized (B) {
            try {
                if (C == null) {
                    Looper looper = com.google.android.gms.common.internal.l.b().getLooper();
                    Context applicationContext = context.getApplicationContext();
                    Object obj = o3.e.f20740c;
                    C = new h(applicationContext, looper);
                }
                hVar = C;
            } catch (Throwable th) {
                throw th;
            }
        }
        return hVar;
    }

    public final e0 b(com.google.android.gms.common.api.n nVar) {
        a apiKey = nVar.getApiKey();
        ConcurrentHashMap concurrentHashMap = this.f12749t;
        e0 e0Var = (e0) concurrentHashMap.get(apiKey);
        if (e0Var == null) {
            e0Var = new e0(this, nVar);
            concurrentHashMap.put(apiKey, e0Var);
        }
        if (e0Var.f12717d.requiresSignIn()) {
            this.f12752w.add(apiKey);
        }
        e0Var.r();
        return e0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(com.google.android.gms.tasks.i r9, int r10, com.google.android.gms.common.api.n r11) {
        /*
            r8 = this;
            if (r10 == 0) goto L88
            com.google.android.gms.common.api.internal.a r3 = r11.getApiKey()
            boolean r11 = r8.g()
            if (r11 != 0) goto Ld
            goto L4c
        Ld:
            com.google.android.gms.common.internal.u r11 = com.google.android.gms.common.internal.u.d()
            java.lang.Object r11 = r11.f12919a
            com.google.android.gms.common.internal.v r11 = (com.google.android.gms.common.internal.v) r11
            r0 = 1
            if (r11 == 0) goto L4f
            boolean r1 = r11.f12925l
            if (r1 == 0) goto L4c
            boolean r11 = r11.f12926m
            j$.util.concurrent.ConcurrentHashMap r1 = r8.f12749t
            java.lang.Object r1 = r1.get(r3)
            com.google.android.gms.common.api.internal.e0 r1 = (com.google.android.gms.common.api.internal.e0) r1
            if (r1 == 0) goto L4a
            com.google.android.gms.common.api.g r2 = r1.f12717d
            boolean r4 = r2 instanceof com.google.android.gms.common.internal.f
            if (r4 == 0) goto L4c
            com.google.android.gms.common.internal.f r2 = (com.google.android.gms.common.internal.f) r2
            boolean r4 = r2.hasConnectionInfo()
            if (r4 == 0) goto L4a
            boolean r4 = r2.isConnecting()
            if (r4 != 0) goto L4a
            com.google.android.gms.common.internal.i r11 = com.google.android.gms.common.api.internal.n0.a(r1, r2, r10)
            if (r11 == 0) goto L4c
            int r2 = r1.f12726n
            int r2 = r2 + r0
            r1.f12726n = r2
            boolean r0 = r11.f12855m
            goto L4f
        L4a:
            r0 = r11
            goto L4f
        L4c:
            r10 = 0
            r1 = r8
            goto L69
        L4f:
            com.google.android.gms.common.api.internal.n0 r11 = new com.google.android.gms.common.api.internal.n0
            r1 = 0
            if (r0 == 0) goto L5a
            long r4 = java.lang.System.currentTimeMillis()
            goto L5b
        L5a:
            r4 = r1
        L5b:
            if (r0 == 0) goto L61
            long r1 = android.os.SystemClock.elapsedRealtime()
        L61:
            r0 = r11
            r6 = r1
            r1 = r8
            r2 = r10
            r0.<init>(r1, r2, r3, r4, r6)
            r10 = r0
        L69:
            if (r10 == 0) goto L89
            com.google.android.gms.tasks.t r9 = r9.f13855a
            androidx.loader.content.j r11 = r1.x
            j$.util.Objects.requireNonNull(r11)
            com.google.android.gms.common.api.internal.h0 r0 = new com.google.android.gms.common.api.internal.h0
            r0.<init>(r11)
            r9.getClass()
            com.google.android.gms.tasks.p r11 = new com.google.android.gms.tasks.p
            r11.<init>(r0, r10)
            com.google.android.gms.tasks.r r10 = r9.f13881b
            r10.a(r11)
            r9.n()
            return
        L88:
            r1 = r8
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.h.c(com.google.android.gms.tasks.i, int, com.google.android.gms.common.api.n):void");
    }

    public final void f(a0 a0Var) {
        synchronized (B) {
            try {
                if (this.f12750u != a0Var) {
                    this.f12750u = a0Var;
                    this.f12751v.clear();
                }
                this.f12751v.addAll(a0Var.f12698p);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean g() {
        if (this.f12741l) {
            return false;
        }
        com.google.android.gms.common.internal.v vVar = (com.google.android.gms.common.internal.v) com.google.android.gms.common.internal.u.d().f12919a;
        if (vVar != null && !vVar.f12925l) {
            return false;
        }
        int iM = this.f12746q.M(203400000);
        return iM == -1 || iM == 0;
    }

    public final com.google.android.gms.tasks.t h(com.google.android.gms.common.api.n nVar, q qVar, z zVar, Runnable runnable) {
        com.google.android.gms.tasks.i iVar = new com.google.android.gms.tasks.i();
        c(iVar, qVar.f12782c, nVar);
        q0 q0Var = new q0(new b1(new r0(qVar, zVar, runnable), iVar), this.f12748s.get(), nVar);
        androidx.loader.content.j jVar = this.x;
        jVar.sendMessage(jVar.obtainMessage(8, q0Var));
        return iVar.f13855a;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x02e9  */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean handleMessage(android.os.Message r15) {
        /*
            Method dump skipped, instruction units count: 1086
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.h.handleMessage(android.os.Message):boolean");
    }

    public final boolean i(o3.b bVar, int i10) {
        o3.e eVar = this.f12745p;
        eVar.getClass();
        Context context = this.f12744o;
        if (!e4.b.c(context)) {
            int i11 = bVar.f20729l;
            PendingIntent activity = bVar.f20730m;
            if (!((i11 == 0 || activity == null) ? false : true)) {
                activity = null;
                Intent intentA = eVar.a(context, i11, null);
                if (intentA != null) {
                    activity = PendingIntent.getActivity(context, 0, intentA, 201326592);
                }
            }
            if (activity != null) {
                int i12 = GoogleApiActivity.f12666l;
                Intent intent = new Intent(context, (Class<?>) GoogleApiActivity.class);
                intent.putExtra("pending_intent", activity);
                intent.putExtra("failing_client_id", i10);
                intent.putExtra("notify_manager", true);
                eVar.f(context, i11, PendingIntent.getActivity(context, 0, intent, o4.e.f20773a | C.BUFFER_FLAG_FIRST_SAMPLE));
                return true;
            }
        }
        return false;
    }

    public final void j(o3.b bVar, int i10) {
        if (i(bVar, i10)) {
            return;
        }
        androidx.loader.content.j jVar = this.x;
        jVar.sendMessage(jVar.obtainMessage(5, i10, 0, bVar));
    }
}
