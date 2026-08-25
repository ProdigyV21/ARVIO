package com.google.android.gms.common.api.internal;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f12759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f12760b;

    public j0(io.sentry.internal.debugmeta.c cVar) {
        this.f12760b = cVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            io.sentry.internal.debugmeta.c cVar = this.f12760b;
            a0 a0Var = (a0) ((com.google.common.util.concurrent.w0) cVar.f17368m).f14244m;
            a0Var.f12695m.set(null);
            androidx.loader.content.j jVar = a0Var.f12699q.x;
            jVar.sendMessage(jVar.obtainMessage(3));
            AlertDialog alertDialog = (AlertDialog) cVar.f17367l;
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            synchronized (this) {
                try {
                    Context context2 = this.f12759a;
                    if (context2 != null) {
                        context2.unregisterReceiver(this);
                    }
                    this.f12759a = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
