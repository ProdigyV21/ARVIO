package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes4.dex */
public final class q0 implements ServiceConnection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12896i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f12897l;

    public q0(f fVar, int i10) {
        this.f12897l = fVar;
        this.f12896i = i10;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        f fVar = this.f12897l;
        if (iBinder == null) {
            fVar.zzf(16);
            return;
        }
        synchronized (fVar.zzh()) {
            try {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                fVar.zzi((iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof q)) ? new l0(iBinder) : (q) iInterfaceQueryLocalInterface);
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f12897l.zzb(0, null, this.f12896i);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        f fVar = this.f12897l;
        synchronized (fVar.zzh()) {
            fVar.zzi(null);
        }
        f fVar2 = this.f12897l;
        int i10 = this.f12896i;
        Handler handler = fVar2.zzb;
        handler.sendMessage(handler.obtainMessage(6, i10, 1));
    }
}
