package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Looper;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class x0 extends l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f12937d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f12938e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile androidx.loader.content.j f12939f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b4.a f12940g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f12941h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f12942i;

    public x0(Context context, Looper looper) {
        w0 w0Var = new w0(this);
        this.f12938e = context.getApplicationContext();
        androidx.loader.content.j jVar = new androidx.loader.content.j(looper, w0Var, 4);
        Looper.getMainLooper();
        this.f12939f = jVar;
        this.f12940g = b4.a.a();
        this.f12941h = 5000L;
        this.f12942i = 300000L;
    }

    @Override // com.google.android.gms.common.internal.l
    public final o3.b c(u0 u0Var, ServiceConnection serviceConnection, String str, Executor executor) {
        o3.b bVarA;
        HashMap map = this.f12937d;
        synchronized (map) {
            try {
                v0 v0Var = (v0) map.get(u0Var);
                if (executor == null) {
                    executor = null;
                }
                if (v0Var == null) {
                    v0Var = new v0(this, u0Var);
                    v0Var.f12929i.put(serviceConnection, serviceConnection);
                    bVarA = v0Var.a(executor, str);
                    map.put(u0Var, v0Var);
                } else {
                    this.f12939f.removeMessages(0, u0Var);
                    if (v0Var.f12929i.containsKey(serviceConnection)) {
                        String string = u0Var.toString();
                        StringBuilder sb2 = new StringBuilder(string.length() + 81);
                        sb2.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                        sb2.append(string);
                        throw new IllegalStateException(sb2.toString());
                    }
                    v0Var.f12929i.put(serviceConnection, serviceConnection);
                    int i10 = v0Var.f12930l;
                    if (i10 == 1) {
                        serviceConnection.onServiceConnected(v0Var.f12934p, v0Var.f12932n);
                    } else if (i10 == 2) {
                        bVarA = v0Var.a(executor, str);
                    }
                    bVarA = null;
                }
                if (v0Var.f12931m) {
                    return o3.b.f20727p;
                }
                if (bVarA == null) {
                    bVarA = new o3.b(-1, null, null);
                }
                return bVarA;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.l
    public final void d(u0 u0Var, ServiceConnection serviceConnection) {
        t.j(serviceConnection, "ServiceConnection must not be null");
        HashMap map = this.f12937d;
        synchronized (map) {
            try {
                v0 v0Var = (v0) map.get(u0Var);
                if (v0Var == null) {
                    String string = u0Var.toString();
                    StringBuilder sb2 = new StringBuilder(string.length() + 50);
                    sb2.append("Nonexistent connection status for service config: ");
                    sb2.append(string);
                    throw new IllegalStateException(sb2.toString());
                }
                if (!v0Var.f12929i.containsKey(serviceConnection)) {
                    String string2 = u0Var.toString();
                    StringBuilder sb3 = new StringBuilder(string2.length() + 76);
                    sb3.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                    sb3.append(string2);
                    throw new IllegalStateException(sb3.toString());
                }
                v0Var.f12929i.remove(serviceConnection);
                if (v0Var.f12929i.isEmpty()) {
                    this.f12939f.sendMessageDelayed(this.f12939f.obtainMessage(0, u0Var), this.f12941h);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
