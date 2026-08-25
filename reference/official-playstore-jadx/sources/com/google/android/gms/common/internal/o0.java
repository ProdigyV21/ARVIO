package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class o0 extends androidx.loader.content.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f12893b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o0(f fVar, Looper looper) {
        super(looper, 4);
        this.f12893b = fVar;
    }

    @Override // androidx.loader.content.j, android.os.Handler
    public final void handleMessage(Message message) {
        Boolean bool;
        k0 k0Var;
        f fVar = this.f12893b;
        if (fVar.zzd.get() != message.arg1) {
            int i10 = message.what;
            if ((i10 == 2 || i10 == 1 || i10 == 7) && (k0Var = (k0) message.obj) != null) {
                k0Var.c();
                return;
            }
            return;
        }
        int i11 = message.what;
        if ((i11 == 1 || i11 == 7 || ((i11 == 4 && !fVar.enableLocalFallback()) || message.what == 5)) && !fVar.isConnecting()) {
            k0 k0Var2 = (k0) message.obj;
            if (k0Var2 != null) {
                k0Var2.c();
                return;
            }
            return;
        }
        int i12 = message.what;
        if (i12 == 4) {
            fVar.zzn(new o3.b(message.arg2, null, null));
            if (fVar.zzg() && !fVar.zzo()) {
                fVar.zzd(3, null);
                return;
            }
            o3.b bVarZzm = fVar.zzm() != null ? fVar.zzm() : new o3.b(8, null, null);
            fVar.zzc.j(bVarZzm);
            fVar.onConnectionFailed(bVarZzm);
            return;
        }
        if (i12 == 5) {
            o3.b bVarZzm2 = fVar.zzm() != null ? fVar.zzm() : new o3.b(8, null, null);
            fVar.zzc.j(bVarZzm2);
            fVar.onConnectionFailed(bVarZzm2);
            return;
        }
        if (i12 == 3) {
            Object obj = message.obj;
            o3.b bVar = new o3.b(message.arg2, obj instanceof PendingIntent ? (PendingIntent) obj : null, null);
            fVar.zzc.j(bVar);
            fVar.onConnectionFailed(bVar);
            return;
        }
        if (i12 == 6) {
            fVar.zzd(5, null);
            if (fVar.zzk() != null) {
                fVar.zzk().c(message.arg2);
            }
            fVar.onConnectionSuspended(message.arg2);
            fVar.zze(5, 1, null);
            return;
        }
        if (i12 == 2 && !fVar.isConnected()) {
            k0 k0Var3 = (k0) message.obj;
            if (k0Var3 != null) {
                k0Var3.c();
                return;
            }
            return;
        }
        int i13 = message.what;
        if (i13 != 2 && i13 != 1 && i13 != 7) {
            Log.wtf("GmsClient", androidx.compose.foundation.c.p(i13, "Don't know how to handle message: ", new StringBuilder(String.valueOf(i13).length() + 34)), new Exception());
            return;
        }
        k0 k0Var4 = (k0) message.obj;
        synchronized (k0Var4) {
            try {
                bool = k0Var4.f12882a;
                if (k0Var4.f12883b) {
                    String string = k0Var4.toString();
                    StringBuilder sb2 = new StringBuilder(string.length() + 47);
                    sb2.append("Callback proxy ");
                    sb2.append(string);
                    sb2.append(" being reused. This is not safe.");
                    Log.w("GmsClient", sb2.toString());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (bool != null) {
            f fVar2 = k0Var4.f12887f;
            int i14 = k0Var4.f12885d;
            if (i14 != 0) {
                fVar2.zzd(1, null);
                Bundle bundle = k0Var4.f12886e;
                k0Var4.b(new o3.b(i14, bundle != null ? (PendingIntent) bundle.getParcelable(f.KEY_PENDING_INTENT) : null, null));
            } else if (!k0Var4.a()) {
                fVar2.zzd(1, null);
                k0Var4.b(new o3.b(8, null, null));
            }
        }
        synchronized (k0Var4) {
            k0Var4.f12883b = true;
        }
        k0Var4.c();
    }
}
