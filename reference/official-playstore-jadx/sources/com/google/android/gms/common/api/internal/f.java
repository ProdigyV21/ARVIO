package com.google.android.gms.common.api.internal;

import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends androidx.loader.content.j {
    public final void a(com.google.android.gms.common.api.y yVar, com.google.android.gms.common.api.x xVar) {
        int i10 = BasePendingResult.zad;
        com.google.android.gms.common.internal.t.i(yVar);
        sendMessage(obtainMessage(1, new Pair(yVar, xVar)));
    }

    @Override // androidx.loader.content.j, android.os.Handler
    public final void handleMessage(Message message) {
        int i10 = message.what;
        if (i10 != 1) {
            if (i10 != 2) {
                Log.wtf("BasePendingResult", androidx.compose.foundation.c.p(i10, "Don't know how to handle message: ", new StringBuilder(String.valueOf(i10).length() + 34)), new Exception());
                return;
            } else {
                ((BasePendingResult) message.obj).forceFailureUnlessReady(Status.f12673r);
                return;
            }
        }
        Pair pair = (Pair) message.obj;
        com.google.android.gms.common.api.y yVar = (com.google.android.gms.common.api.y) pair.first;
        com.google.android.gms.common.api.x xVar = (com.google.android.gms.common.api.x) pair.second;
        try {
            yVar.onResult(xVar);
        } catch (RuntimeException e5) {
            BasePendingResult.zal(xVar);
            throw e5;
        }
    }
}
