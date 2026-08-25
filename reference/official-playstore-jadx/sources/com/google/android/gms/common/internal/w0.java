package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class w0 implements Handler.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ x0 f12936i;

    public /* synthetic */ w0(x0 x0Var) {
        this.f12936i = x0Var;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i10 = message.what;
        if (i10 == 0) {
            x0 x0Var = this.f12936i;
            synchronized (x0Var.f12937d) {
                try {
                    u0 u0Var = (u0) message.obj;
                    v0 v0Var = (v0) x0Var.f12937d.get(u0Var);
                    if (v0Var != null && v0Var.f12929i.isEmpty()) {
                        if (v0Var.f12931m) {
                            u0 u0Var2 = v0Var.f12933o;
                            x0 x0Var2 = v0Var.f12935q;
                            x0Var2.f12939f.removeMessages(1, u0Var2);
                            x0Var2.f12940g.b(x0Var2.f12938e, v0Var);
                            v0Var.f12931m = false;
                            v0Var.f12930l = 2;
                        }
                        x0Var.f12937d.remove(u0Var);
                    }
                } finally {
                }
            }
            return true;
        }
        if (i10 != 1) {
            return false;
        }
        x0 x0Var3 = this.f12936i;
        synchronized (x0Var3.f12937d) {
            try {
                u0 u0Var3 = (u0) message.obj;
                v0 v0Var2 = (v0) x0Var3.f12937d.get(u0Var3);
                if (v0Var2 != null && v0Var2.f12930l == 3) {
                    String strValueOf = String.valueOf(u0Var3);
                    StringBuilder sb2 = new StringBuilder(strValueOf.length() + 47);
                    sb2.append("Timeout waiting for ServiceConnection callback ");
                    sb2.append(strValueOf);
                    Log.e("GmsClientSupervisor", sb2.toString(), new Exception());
                    ComponentName componentName = v0Var2.f12934p;
                    if (componentName == null) {
                        componentName = u0Var3.f12922c;
                    }
                    if (componentName == null) {
                        String str = u0Var3.f12921b;
                        t.i(str);
                        componentName = new ComponentName(str, "unknown");
                    }
                    v0Var2.onServiceDisconnected(componentName);
                }
            } finally {
            }
        }
        return true;
    }
}
