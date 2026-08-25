package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.StrictMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class v0 implements ServiceConnection, y0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashMap f12929i = new HashMap();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f12930l = 2;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f12931m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public IBinder f12932n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final u0 f12933o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ComponentName f12934p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ x0 f12935q;

    public v0(x0 x0Var, u0 u0Var) {
        this.f12935q = x0Var;
        this.f12933o = u0Var;
    }

    public final o3.b a(Executor executor, String str) throws Throwable {
        try {
            Intent intentA = n0.a(this.f12935q.f12938e, this.f12933o);
            this.f12930l = 3;
            StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
            if (Build.VERSION.SDK_INT >= 31) {
                StrictMode.setVmPolicy(c4.j.a(new StrictMode.VmPolicy.Builder(vmPolicy)).build());
            }
            try {
                x0 x0Var = this.f12935q;
                b4.a aVar = x0Var.f12940g;
                Context context = x0Var.f12938e;
                u0 u0Var = this.f12933o;
                try {
                    boolean zC = aVar.c(context, str, intentA, this, 4225, executor);
                    this.f12931m = zC;
                    if (zC) {
                        x0Var.f12939f.sendMessageDelayed(x0Var.f12939f.obtainMessage(1, u0Var), x0Var.f12942i);
                        o3.b bVar = o3.b.f20727p;
                        StrictMode.setVmPolicy(vmPolicy);
                        return bVar;
                    }
                    this.f12930l = 2;
                    try {
                        x0Var.f12940g.b(x0Var.f12938e, this);
                    } catch (IllegalArgumentException unused) {
                    }
                    o3.b bVar2 = new o3.b(16, null, null);
                    StrictMode.setVmPolicy(vmPolicy);
                    return bVar2;
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    StrictMode.setVmPolicy(vmPolicy);
                    throw th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (zzaf e5) {
            return e5.f12946i;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        x0 x0Var = this.f12935q;
        synchronized (x0Var.f12937d) {
            try {
                x0Var.f12939f.removeMessages(1, this.f12933o);
                this.f12932n = iBinder;
                this.f12934p = componentName;
                Iterator it = this.f12929i.values().iterator();
                while (it.hasNext()) {
                    ((ServiceConnection) it.next()).onServiceConnected(componentName, iBinder);
                }
                this.f12930l = 1;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        x0 x0Var = this.f12935q;
        synchronized (x0Var.f12937d) {
            try {
                x0Var.f12939f.removeMessages(1, this.f12933o);
                this.f12932n = null;
                this.f12934p = componentName;
                Iterator it = this.f12929i.values().iterator();
                while (it.hasNext()) {
                    ((ServiceConnection) it.next()).onServiceDisconnected(componentName);
                }
                this.f12930l = 2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
