package com.google.common.util.concurrent;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.work.Worker;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.tasks.RuntimeExecutionException;
import j$.util.Objects;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes4.dex */
public final class w0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14242i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f14243l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f14244m;

    public /* synthetic */ w0(int i10, Object obj, boolean z, Object obj2) {
        this.f14242i = i10;
        this.f14244m = obj;
        this.f14243l = obj2;
    }

    private final void a() {
        try {
            ((Runnable) this.f14244m).run();
            synchronized (((androidx.work.impl.utils.l) this.f14243l).f7047n) {
                ((androidx.work.impl.utils.l) this.f14243l).b();
            }
        } catch (Throwable th) {
            synchronized (((androidx.work.impl.utils.l) this.f14243l).f7047n) {
                ((androidx.work.impl.utils.l) this.f14243l).b();
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [com.google.android.gms.common.api.internal.k, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v12, types: [com.google.android.gms.common.api.internal.k, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void b() {
        com.google.android.gms.common.api.internal.a0 a0Var = (com.google.android.gms.common.api.internal.a0) this.f14244m;
        if (a0Var.f12694l) {
            com.google.android.gms.common.api.internal.e1 e1Var = (com.google.android.gms.common.api.internal.e1) this.f14243l;
            o3.b bVar = e1Var.f12729b;
            if (bVar.f20729l != 0 && bVar.f20730m != null) {
                ?? r32 = a0Var.f12693i;
                Activity activityA = a0Var.a();
                PendingIntent pendingIntent = bVar.f20730m;
                com.google.android.gms.common.internal.t.i(pendingIntent);
                int i10 = e1Var.f12728a;
                int i11 = GoogleApiActivity.f12666l;
                Intent intent = new Intent(activityA, (Class<?>) GoogleApiActivity.class);
                intent.putExtra("pending_intent", pendingIntent);
                intent.putExtra("failing_client_id", i10);
                intent.putExtra("notify_manager", false);
                r32.startActivityForResult(intent, 1);
                return;
            }
            Activity activityA2 = a0Var.a();
            int i12 = bVar.f20729l;
            o3.e eVar = a0Var.f12697o;
            if (eVar.a(activityA2, i12, null) != null) {
                eVar.e(a0Var.a(), a0Var.f12693i, bVar.f20729l, a0Var);
                return;
            }
            if (bVar.f20729l != 18) {
                int i13 = e1Var.f12728a;
                a0Var.f12695m.set(null);
                a0Var.f12699q.j(bVar, i13);
                return;
            }
            Activity activityA3 = a0Var.a();
            ProgressBar progressBar = new ProgressBar(activityA3, null, R.attr.progressBarStyleLarge);
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(0);
            AlertDialog.Builder builder = new AlertDialog.Builder(activityA3);
            builder.setView(progressBar);
            builder.setMessage(com.google.android.gms.common.internal.d0.b(activityA3, 18));
            builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
            AlertDialog alertDialogCreate = builder.create();
            o3.e.g(activityA3, alertDialogCreate, "GooglePlayServicesUpdatingDialog", a0Var);
            Context applicationContext = a0Var.a().getApplicationContext();
            io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this, alertDialogCreate, 15);
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            com.google.android.gms.common.api.internal.j0 j0Var = new com.google.android.gms.common.api.internal.j0(cVar);
            qb.d.N(applicationContext, j0Var, intentFilter, 2);
            j0Var.f12759a = applicationContext;
            if (o3.g.c(applicationContext, "com.google.android.gms")) {
                return;
            }
            com.google.android.gms.common.api.internal.a0 a0Var2 = (com.google.android.gms.common.api.internal.a0) this.f14244m;
            a0Var2.f12695m.set(null);
            androidx.loader.content.j jVar = a0Var2.f12699q.x;
            jVar.sendMessage(jVar.obtainMessage(3));
            if (alertDialogCreate.isShowing()) {
                alertDialogCreate.dismiss();
            }
            synchronized (j0Var) {
                try {
                    Context context = j0Var.f12759a;
                    if (context != null) {
                        context.unregisterReceiver(j0Var);
                    }
                    j0Var.f12759a = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private final void c() {
        com.google.android.gms.tasks.p pVar = (com.google.android.gms.tasks.p) this.f14244m;
        synchronized (pVar.f13874m) {
            ((com.google.android.gms.tasks.c) pVar.f13875n).onComplete((com.google.android.gms.tasks.h) this.f14243l);
        }
    }

    private final void d() {
        com.google.android.gms.tasks.p pVar = (com.google.android.gms.tasks.p) this.f14244m;
        synchronized (pVar.f13874m) {
            try {
                com.google.android.gms.tasks.d dVar = (com.google.android.gms.tasks.d) pVar.f13875n;
                if (dVar != null) {
                    Exception excE = ((com.google.android.gms.tasks.h) this.f14243l).e();
                    com.google.android.gms.common.internal.t.i(excE);
                    dVar.onFailure(excE);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void e() {
        com.google.android.gms.tasks.p pVar = (com.google.android.gms.tasks.p) this.f14244m;
        synchronized (pVar.f13874m) {
            try {
                com.google.android.gms.tasks.e eVar = (com.google.android.gms.tasks.e) pVar.f13875n;
                if (eVar != null) {
                    eVar.onSuccess(((com.google.android.gms.tasks.h) this.f14243l).f());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void f() {
        try {
            g();
        } catch (Error e5) {
            synchronized (((com.google.firebase.concurrent.o) this.f14244m).f14339l) {
                ((com.google.firebase.concurrent.o) this.f14244m).f14340m = 1;
                throw e5;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        if (r1 == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004c, code lost:
    
        r1 = r1 | java.lang.Thread.interrupted();
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004e, code lost:
    
        ((java.lang.Runnable) r10.f14243l).run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005c, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005d, code lost:
    
        com.google.firebase.concurrent.o.f14337p.log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + ((java.lang.Runnable) r10.f14243l), (java.lang.Throwable) r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007a, code lost:
    
        r10.f14243l = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007c, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x003c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g() {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L2:
            java.lang.Object r2 = r10.f14244m     // Catch: java.lang.Throwable -> L58
            com.google.firebase.concurrent.o r2 = (com.google.firebase.concurrent.o) r2     // Catch: java.lang.Throwable -> L58
            java.util.ArrayDeque r2 = r2.f14339l     // Catch: java.lang.Throwable -> L58
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L58
            r3 = 1
            if (r0 != 0) goto L2c
            java.lang.Object r0 = r10.f14244m     // Catch: java.lang.Throwable -> L20
            com.google.firebase.concurrent.o r0 = (com.google.firebase.concurrent.o) r0     // Catch: java.lang.Throwable -> L20
            int r4 = r0.f14340m     // Catch: java.lang.Throwable -> L20
            r5 = 4
            if (r4 != r5) goto L22
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto L46
        L18:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            goto L46
        L20:
            r0 = move-exception
            goto L7d
        L22:
            long r6 = r0.f14341n     // Catch: java.lang.Throwable -> L20
            r8 = 1
            long r6 = r6 + r8
            r0.f14341n = r6     // Catch: java.lang.Throwable -> L20
            r0.f14340m = r5     // Catch: java.lang.Throwable -> L20
            r0 = r3
        L2c:
            java.lang.Object r4 = r10.f14244m     // Catch: java.lang.Throwable -> L20
            com.google.firebase.concurrent.o r4 = (com.google.firebase.concurrent.o) r4     // Catch: java.lang.Throwable -> L20
            java.util.ArrayDeque r4 = r4.f14339l     // Catch: java.lang.Throwable -> L20
            java.lang.Object r4 = r4.poll()     // Catch: java.lang.Throwable -> L20
            java.lang.Runnable r4 = (java.lang.Runnable) r4     // Catch: java.lang.Throwable -> L20
            r10.f14243l = r4     // Catch: java.lang.Throwable -> L20
            if (r4 != 0) goto L47
            java.lang.Object r0 = r10.f14244m     // Catch: java.lang.Throwable -> L20
            com.google.firebase.concurrent.o r0 = (com.google.firebase.concurrent.o) r0     // Catch: java.lang.Throwable -> L20
            r0.f14340m = r3     // Catch: java.lang.Throwable -> L20
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto L46
            goto L18
        L46:
            return
        L47:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L20
            boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L58
            r1 = r1 | r2
            r2 = 0
            java.lang.Object r3 = r10.f14243l     // Catch: java.lang.Throwable -> L5a java.lang.RuntimeException -> L5c
            java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L5a java.lang.RuntimeException -> L5c
            r3.run()     // Catch: java.lang.Throwable -> L5a java.lang.RuntimeException -> L5c
        L55:
            r10.f14243l = r2     // Catch: java.lang.Throwable -> L58
            goto L2
        L58:
            r0 = move-exception
            goto L7f
        L5a:
            r0 = move-exception
            goto L7a
        L5c:
            r3 = move-exception
            java.util.logging.Logger r4 = com.google.firebase.concurrent.o.f14337p     // Catch: java.lang.Throwable -> L5a
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L5a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5a
            r6.<init>()     // Catch: java.lang.Throwable -> L5a
            java.lang.String r7 = "Exception while executing runnable "
            r6.append(r7)     // Catch: java.lang.Throwable -> L5a
            java.lang.Object r7 = r10.f14243l     // Catch: java.lang.Throwable -> L5a
            java.lang.Runnable r7 = (java.lang.Runnable) r7     // Catch: java.lang.Throwable -> L5a
            r6.append(r7)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L5a
            r4.log(r5, r6, r3)     // Catch: java.lang.Throwable -> L5a
            goto L55
        L7a:
            r10.f14243l = r2     // Catch: java.lang.Throwable -> L58
            throw r0     // Catch: java.lang.Throwable -> L58
        L7d:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L20
            throw r0     // Catch: java.lang.Throwable -> L58
        L7f:
            if (r1 == 0) goto L88
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
        L88:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.w0.g():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        Throwable thTryInternalFastPathGetFailure;
        com.google.android.gms.common.internal.n nVar;
        com.google.android.gms.common.internal.n a1Var;
        switch (this.f14242i) {
            case 0:
                v0 v0Var = (v0) this.f14244m;
                Future future = (Future) this.f14243l;
                if ((future instanceof w4.a) && (thTryInternalFastPathGetFailure = ((w4.a) future).tryInternalFastPathGetFailure()) != null) {
                    v0Var.onFailure(thTryInternalFastPathGetFailure);
                    return;
                }
                try {
                    v0Var.onSuccess(xc.d.j0(future));
                    return;
                } catch (ExecutionException e5) {
                    v0Var.onFailure(e5.getCause());
                    return;
                } catch (Throwable th) {
                    v0Var.onFailure(th);
                    return;
                }
            case 1:
                ((androidx.core.app.h) this.f14243l).f1987i = this.f14244m;
                return;
            case 2:
                ((Application) this.f14243l).unregisterActivityLifecycleCallbacks((androidx.core.app.h) this.f14244m);
                return;
            case 3:
                Object obj = this.f14244m;
                Object obj2 = this.f14243l;
                try {
                    Method method = androidx.core.app.i.f2000d;
                    if (method != null) {
                        method.invoke(obj2, obj, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        androidx.core.app.i.f2001e.invoke(obj2, obj, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e6) {
                    if (e6.getClass() == RuntimeException.class && e6.getMessage() != null && e6.getMessage().startsWith("Unable to stop")) {
                        throw e6;
                    }
                    return;
                } catch (Throwable th2) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th2);
                    return;
                }
            case 4:
                a8.e eVar = (a8.e) this.f14243l;
                Typeface typeface = (Typeface) this.f14244m;
                i0.o oVar = (i0.o) eVar.f183l;
                if (oVar != null) {
                    oVar.onFontRetrieved(typeface);
                    return;
                }
                return;
            case 5:
                ((androidx.core.provider.g) this.f14243l).accept(this.f14244m);
                return;
            case 6:
                if (((androidx.work.impl.v) this.f14244m).A.f7037i instanceof androidx.work.impl.utils.futures.a) {
                    return;
                }
                try {
                    ((d1) this.f14243l).get();
                    m2.t.d().a(androidx.work.impl.v.C, "Starting work for " + ((androidx.work.impl.v) this.f14244m).f7090n.f6924c);
                    androidx.work.impl.v vVar = (androidx.work.impl.v) this.f14244m;
                    vVar.A.j(vVar.f7091o.startWork());
                    return;
                } catch (Throwable th3) {
                    ((androidx.work.impl.v) this.f14244m).A.i(th3);
                    return;
                }
            case 7:
                String str = (String) this.f14243l;
                androidx.work.impl.v vVar2 = (androidx.work.impl.v) this.f14244m;
                androidx.work.impl.model.p pVar = vVar2.f7090n;
                try {
                    try {
                        m2.r rVar = (m2.r) vVar2.A.get();
                        if (rVar == null) {
                            m2.t.d().b(androidx.work.impl.v.C, pVar.f6924c + " returned a null result. Treating it as a failure.");
                        } else {
                            m2.t.d().a(androidx.work.impl.v.C, pVar.f6924c + " returned a " + rVar + ".");
                            vVar2.f7093q = rVar;
                        }
                    } catch (Throwable th4) {
                        vVar2.b();
                        throw th4;
                    }
                    break;
                } catch (InterruptedException e10) {
                    e = e10;
                    m2.t.d().c(androidx.work.impl.v.C, str + " failed because it threw an exception/error", e);
                } catch (CancellationException e11) {
                    m2.t tVarD = m2.t.d();
                    String str2 = androidx.work.impl.v.C;
                    String str3 = str + " was cancelled";
                    if (tVarD.f20216a <= 4) {
                        Log.i(str2, str3, e11);
                    }
                } catch (ExecutionException e12) {
                    e = e12;
                    m2.t.d().c(androidx.work.impl.v.C, str + " failed because it threw an exception/error", e);
                }
                vVar2.b();
                return;
            case 8:
                androidx.work.impl.model.p pVarD = ((androidx.work.impl.foreground.c) this.f14244m).f6865i.f6969f.d((String) this.f14243l);
                if (pVarD == null || !pVarD.b()) {
                    return;
                }
                synchronized (((androidx.work.impl.foreground.c) this.f14244m).f6867m) {
                    ((androidx.work.impl.foreground.c) this.f14244m).f6870p.put(a.a.N(pVarD), pVarD);
                    androidx.work.impl.foreground.c cVar = (androidx.work.impl.foreground.c) this.f14244m;
                    ((androidx.work.impl.foreground.c) this.f14244m).f6871q.put(a.a.N(pVarD), androidx.work.impl.constraints.m.a(cVar.f6872r, pVarD, cVar.f6866l.b(), (androidx.work.impl.foreground.c) this.f14244m));
                    break;
                }
                return;
            case 9:
                a();
                return;
            case 10:
                if (((androidx.work.impl.utils.p) this.f14244m).f7056i.f7037i instanceof androidx.work.impl.utils.futures.a) {
                    return;
                }
                try {
                    m2.j jVar = (m2.j) ((androidx.work.impl.utils.futures.k) this.f14243l).get();
                    if (jVar == null) {
                        throw new IllegalStateException("Worker was marked important (" + ((androidx.work.impl.utils.p) this.f14244m).f7058m.f6924c + ") but did not provide ForegroundInfo");
                    }
                    m2.t.d().a(androidx.work.impl.utils.p.f7055q, "Updating notification for " + ((androidx.work.impl.utils.p) this.f14244m).f7058m.f6924c);
                    androidx.work.impl.utils.p pVar2 = (androidx.work.impl.utils.p) this.f14244m;
                    pVar2.f7056i.j(pVar2.f7060o.a(pVar2.f7057l, pVar2.f7059n.getId(), jVar));
                    return;
                } catch (Throwable th5) {
                    ((androidx.work.impl.utils.p) this.f14244m).f7056i.i(th5);
                    return;
                }
            case 11:
                o3.b bVar = (o3.b) this.f14243l;
                com.google.android.gms.common.api.internal.g0 g0Var = (com.google.android.gms.common.api.internal.g0) this.f14244m;
                com.google.android.gms.common.api.internal.h hVar = (com.google.android.gms.common.api.internal.h) g0Var.f12738f;
                com.google.android.gms.common.api.g gVar = (com.google.android.gms.common.api.g) g0Var.f12734b;
                com.google.android.gms.common.api.internal.e0 e0Var = (com.google.android.gms.common.api.internal.e0) hVar.f12749t.get((com.google.android.gms.common.api.internal.a) g0Var.f12735c);
                if (e0Var == null) {
                    return;
                }
                if (!bVar.d()) {
                    e0Var.o(bVar, null);
                    return;
                }
                g0Var.f12733a = true;
                if (gVar.requiresSignIn()) {
                    if (!g0Var.f12733a || (nVar = (com.google.android.gms.common.internal.n) g0Var.f12736d) == null) {
                        return;
                    }
                    gVar.getRemoteService(nVar, (Set) g0Var.f12737e);
                    return;
                }
                try {
                    gVar.getRemoteService(null, gVar.getScopesForConnectionlessNonSignIn());
                    return;
                } catch (SecurityException e13) {
                    Log.e("GoogleApiManager", "Failed to get service from broker. ", e13);
                    gVar.disconnect("Failed to get service from broker.");
                    e0Var.o(new o3.b(10, null, null), null);
                    return;
                }
            case 12:
                com.google.android.gms.common.api.internal.n nVar2 = (com.google.android.gms.common.api.internal.n) this.f14243l;
                com.google.android.gms.common.api.internal.m mVar = (com.google.android.gms.common.api.internal.m) this.f14244m;
                Object obj3 = nVar2.f12768b;
                if (obj3 == null) {
                    mVar.b();
                    return;
                }
                try {
                    mVar.a(obj3);
                    return;
                } catch (RuntimeException e14) {
                    mVar.b();
                    throw e14;
                }
            case 13:
                com.google.android.gms.common.api.internal.v0 v0Var2 = (com.google.android.gms.common.api.internal.v0) this.f14244m;
                u4.g gVar2 = (u4.g) this.f14243l;
                v0Var2.getClass();
                o3.b bVar2 = gVar2.f22054l;
                if (bVar2.d()) {
                    com.google.android.gms.common.internal.j0 j0Var = gVar2.f22055m;
                    com.google.android.gms.common.internal.t.i(j0Var);
                    o3.b bVar3 = j0Var.f12879m;
                    if (!bVar3.d()) {
                        Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(String.valueOf(bVar3)), new Exception());
                        v0Var2.f12805i.C(bVar3);
                        v0Var2.f12804h.disconnect();
                        return;
                    }
                    com.google.android.gms.common.api.internal.g0 g0Var2 = v0Var2.f12805i;
                    IBinder iBinder = j0Var.f12878l;
                    if (iBinder == null) {
                        a1Var = null;
                    } else {
                        int i10 = com.google.android.gms.common.internal.a.f12829d;
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                        a1Var = iInterfaceQueryLocalInterface instanceof com.google.android.gms.common.internal.n ? (com.google.android.gms.common.internal.n) iInterfaceQueryLocalInterface : new com.google.android.gms.common.internal.a1(iBinder, "com.google.android.gms.common.internal.IAccountAccessor", 1);
                    }
                    Set set = v0Var2.f12802f;
                    g0Var2.getClass();
                    if (a1Var == null || set == null) {
                        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                        g0Var2.C(new o3.b(4, null, null));
                    } else {
                        g0Var2.f12736d = a1Var;
                        g0Var2.f12737e = set;
                        if (g0Var2.f12733a) {
                            ((com.google.android.gms.common.api.g) g0Var2.f12734b).getRemoteService(a1Var, set);
                        }
                    }
                } else {
                    v0Var2.f12805i.C(bVar2);
                }
                v0Var2.f12804h.disconnect();
                return;
            case 14:
                b();
                return;
            case 15:
                com.google.android.gms.common.api.internal.a0 a0Var = (com.google.android.gms.common.api.internal.a0) this.f14243l;
                androidx.appcompat.widget.f0 f0Var = (androidx.appcompat.widget.f0) this.f14244m;
                if (f0Var.f1566l > 0) {
                    Bundle bundle = (Bundle) f0Var.f1568n;
                    a0Var.b(bundle != null ? bundle.getBundle("ConnectionlessLifecycleHelper") : null);
                }
                if (f0Var.f1566l >= 2) {
                    a0Var.f12694l = true;
                    a0Var.d();
                }
                if (f0Var.f1566l >= 3) {
                    a0Var.d();
                }
                if (f0Var.f1566l >= 4) {
                    a0Var.c();
                    return;
                }
                return;
            case 16:
                com.google.android.gms.tasks.h hVar2 = (com.google.android.gms.tasks.h) this.f14243l;
                if (((com.google.android.gms.tasks.t) hVar2).f13883d) {
                    ((com.google.android.gms.tasks.n) this.f14244m).f13870n.l();
                    return;
                }
                try {
                    ((com.google.android.gms.tasks.n) this.f14244m).f13870n.j(((com.google.android.gms.tasks.n) this.f14244m).f13869m.then(hVar2));
                    return;
                } catch (RuntimeExecutionException e15) {
                    if (e15.getCause() instanceof Exception) {
                        ((com.google.android.gms.tasks.n) this.f14244m).f13870n.k((Exception) e15.getCause());
                        return;
                    } else {
                        ((com.google.android.gms.tasks.n) this.f14244m).f13870n.k(e15);
                        return;
                    }
                } catch (Exception e16) {
                    ((com.google.android.gms.tasks.n) this.f14244m).f13870n.k(e16);
                    return;
                }
            case 17:
                com.google.android.gms.tasks.n nVar3 = (com.google.android.gms.tasks.n) this.f14244m;
                try {
                    com.google.android.gms.tasks.h hVar3 = (com.google.android.gms.tasks.h) nVar3.f13869m.then((com.google.android.gms.tasks.h) this.f14243l);
                    if (hVar3 == null) {
                        nVar3.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    androidx.appcompat.app.a1 a1Var2 = com.google.android.gms.tasks.j.f13857b;
                    hVar3.c(a1Var2, nVar3);
                    hVar3.b(a1Var2, nVar3);
                    com.google.android.gms.tasks.t tVar = (com.google.android.gms.tasks.t) hVar3;
                    tVar.f13881b.a(new com.google.android.gms.tasks.p((Executor) a1Var2, (com.google.android.gms.tasks.b) nVar3));
                    tVar.n();
                    return;
                } catch (RuntimeExecutionException e17) {
                    if (e17.getCause() instanceof Exception) {
                        nVar3.f13870n.k((Exception) e17.getCause());
                        return;
                    } else {
                        nVar3.f13870n.k(e17);
                        return;
                    }
                } catch (Exception e18) {
                    nVar3.f13870n.k(e18);
                    return;
                }
            case 18:
                c();
                return;
            case 19:
                d();
                return;
            case 20:
                e();
                return;
            case 21:
                com.google.android.gms.tasks.p pVar3 = (com.google.android.gms.tasks.p) this.f14244m;
                try {
                    com.google.android.gms.tasks.h hVarThen = ((com.google.android.gms.tasks.g) pVar3.f13874m).then(((com.google.android.gms.tasks.h) this.f14243l).f());
                    if (hVarThen == null) {
                        pVar3.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    androidx.appcompat.app.a1 a1Var3 = com.google.android.gms.tasks.j.f13857b;
                    hVarThen.c(a1Var3, pVar3);
                    hVarThen.b(a1Var3, pVar3);
                    com.google.android.gms.tasks.t tVar2 = (com.google.android.gms.tasks.t) hVarThen;
                    tVar2.f13881b.a(new com.google.android.gms.tasks.p((Executor) a1Var3, (com.google.android.gms.tasks.b) pVar3));
                    tVar2.n();
                    return;
                } catch (RuntimeExecutionException e19) {
                    if (e19.getCause() instanceof Exception) {
                        pVar3.onFailure((Exception) e19.getCause());
                        return;
                    } else {
                        pVar3.onFailure(e19);
                        return;
                    }
                } catch (CancellationException unused) {
                    pVar3.b();
                    return;
                } catch (Exception e20) {
                    pVar3.onFailure(e20);
                    return;
                }
            case 22:
                com.google.android.gms.tasks.t tVar3 = (com.google.android.gms.tasks.t) this.f14243l;
                try {
                    tVar3.j(((Callable) this.f14244m).call());
                    return;
                } catch (Exception e21) {
                    tVar3.k(e21);
                    return;
                } catch (Throwable th6) {
                    tVar3.k(new RuntimeException(th6));
                    return;
                }
            case 23:
                f();
                return;
            case 24:
                ((io.sentry.android.core.performance.g) this.f14244m).f16708m = SystemClock.uptimeMillis();
                ((Handler) this.f14243l).post(new androidx.activity.n(this, 21));
                return;
            case 25:
                ((ka.l) this.f14244m).A((ka.k1) this.f14243l);
                return;
            case 26:
                ((ka.l) this.f14243l).A((la.d) this.f14244m);
                return;
            case 27:
                ka.l lVar = (ka.l) this.f14243l;
                try {
                    lVar.resumeWith(((d1) this.f14244m).get());
                    return;
                } catch (Throwable th7) {
                    Throwable cause = th7.getCause();
                    if (cause == null) {
                        cause = th7;
                    }
                    if (th7 instanceof CancellationException) {
                        lVar.cancel(cause);
                        return;
                    } else {
                        lVar.resumeWith(new x6.c0(cause));
                        return;
                    }
                }
            case 28:
                try {
                    ((Worker) this.f14244m).getClass();
                    throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for \n `getForegroundInfo()`");
                } catch (Throwable th8) {
                    ((androidx.work.impl.utils.futures.k) this.f14243l).i(th8);
                    return;
                }
            default:
                m2.t tVarD2 = m2.t.d();
                String str4 = n2.a.f20461e;
                StringBuilder sb2 = new StringBuilder("Scheduling work ");
                androidx.work.impl.model.p pVar4 = (androidx.work.impl.model.p) this.f14243l;
                sb2.append(pVar4.f6922a);
                tVarD2.a(str4, sb2.toString());
                ((n2.a) this.f14244m).f20462a.b(pVar4);
                return;
        }
    }

    public String toString() {
        switch (this.f14242i) {
            case 0:
                androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(w0.class.getSimpleName(), 13);
                v0 v0Var = (v0) this.f14244m;
                io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(21);
                ((io.sentry.internal.debugmeta.c) i1Var.f1061n).f17368m = cVar;
                i1Var.f1061n = cVar;
                cVar.f17367l = v0Var;
                return i1Var.toString();
            case 23:
                Runnable runnable = (Runnable) this.f14243l;
                if (runnable != null) {
                    return "SequentialExecutorWorker{running=" + runnable + "}";
                }
                StringBuilder sb2 = new StringBuilder("SequentialExecutorWorker{state=");
                int i10 = ((com.google.firebase.concurrent.o) this.f14244m).f14340m;
                sb2.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "null" : "RUNNING" : "QUEUED" : "QUEUING" : "IDLE");
                sb2.append("}");
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ w0(Object obj, Object obj2, int i10) {
        this.f14242i = i10;
        this.f14243l = obj;
        this.f14244m = obj2;
    }

    public w0(com.google.android.gms.common.api.internal.a0 a0Var, com.google.android.gms.common.api.internal.e1 e1Var) {
        this.f14242i = 14;
        Objects.requireNonNull(a0Var);
        this.f14244m = a0Var;
        this.f14243l = e1Var;
    }

    public w0(com.google.firebase.concurrent.o oVar) {
        this.f14242i = 23;
        this.f14244m = oVar;
    }
}
