package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class x0 extends androidx.loader.content.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y0 f12811b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(y0 y0Var, Looper looper) {
        super(looper, 3);
        this.f12811b = y0Var;
    }

    @Override // androidx.loader.content.j, android.os.Handler
    public final void handleMessage(Message message) {
        int i10 = message.what;
        if (i10 != 0) {
            if (i10 == 1) {
                RuntimeException runtimeException = (RuntimeException) message.obj;
                Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: ".concat(String.valueOf(runtimeException.getMessage())));
                throw runtimeException;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 59);
            sb2.append("TransformationResultHandler received unknown message type: ");
            sb2.append(i10);
            Log.e("TransformedResultImpl", sb2.toString());
            return;
        }
        com.google.android.gms.common.api.v vVar = (com.google.android.gms.common.api.v) message.obj;
        y0 y0Var = this.f12811b;
        synchronized (y0Var.f12813b) {
            try {
                y0 y0Var2 = y0Var.f12812a;
                com.google.android.gms.common.internal.t.i(y0Var2);
                if (vVar == null) {
                    y0Var2.c(new Status(13, "Transform returned null", null, null));
                } else {
                    y0Var2.b(vVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
