package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class a3 extends com.google.android.gms.common.internal.k implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f13164d = new Logger("CastRemoteDisplayClientImpl");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CastRemoteDisplay.CastRemoteDisplaySessionCallbacks f13165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CastDevice f13166b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bundle f13167c;

    public a3(Context context, Looper looper, com.google.android.gms.common.internal.h hVar, CastDevice castDevice, Bundle bundle, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks, com.google.android.gms.common.api.o oVar, com.google.android.gms.common.api.p pVar) {
        super(context, looper, 83, hVar, oVar, pVar);
        f13164d.d("instance created", new Object[0]);
        this.f13165a = castRemoteDisplaySessionCallbacks;
        this.f13166b = castDevice;
        this.f13167c = bundle;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
    }

    @Override // com.google.android.gms.common.internal.f
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        return iInterfaceQueryLocalInterface instanceof d3 ? (d3) iInterfaceQueryLocalInterface : new d3(iBinder);
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final void disconnect() {
        f13164d.d("disconnect", new Object[0]);
        try {
            d3 d3Var = (d3) getService();
            getContext();
            com.google.android.gms.common.api.j jVarI = u0.i();
            Parcel parcelZza = d3Var.zza();
            a1.c(parcelZza, jVarI);
            d3Var.zzd(3, parcelZza);
        } catch (RemoteException | IllegalStateException unused) {
        } catch (Throwable th) {
            super.disconnect();
            throw th;
        }
        super.disconnect();
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }
}
