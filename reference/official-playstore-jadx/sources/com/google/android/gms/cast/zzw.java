package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.j;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.d3;
import com.google.android.gms.internal.cast.u0;
import com.google.android.gms.internal.cast.y2;
import com.google.android.gms.tasks.i;
import j$.util.Objects;
import qb.d;

/* JADX INFO: loaded from: classes4.dex */
final class zzw extends zzy {
    final /* synthetic */ i zza;
    final /* synthetic */ y2 zzb;
    final /* synthetic */ CastRemoteDisplayClient zzc;
    final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzw(CastRemoteDisplayClient castRemoteDisplayClient, i iVar, y2 y2Var, zzag zzagVar) {
        super(null);
        this.zza = iVar;
        this.zzb = y2Var;
        this.zzd = zzagVar;
        Objects.requireNonNull(castRemoteDisplayClient);
        this.zzc = castRemoteDisplayClient;
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.c3
    public final void zzb(int i10, int i11, Surface surface, j jVar) throws RemoteException {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzc;
        castRemoteDisplayClient.zzb().d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) castRemoteDisplayClient.getApplicationContext().getSystemService("display");
        Status status = Status.f12672q;
        if (displayManager == null) {
            castRemoteDisplayClient.zzb().e("Unable to get the display manager", new Object[0]);
            d.S(status, null, this.zza);
            return;
        }
        castRemoteDisplayClient.zza();
        castRemoteDisplayClient.zzd(displayManager.createVirtualDisplay("private_display", i10, i11, (Math.min(i10, i11) * 320) / 1080, surface, 2));
        if (castRemoteDisplayClient.zzc() == null) {
            castRemoteDisplayClient.zzb().e("Unable to create virtual display", new Object[0]);
            d.S(status, null, this.zza);
            return;
        }
        Display display = castRemoteDisplayClient.zzc().getDisplay();
        if (display == null) {
            castRemoteDisplayClient.zzb().e("Virtual display does not have a display", new Object[0]);
            d.S(status, null, this.zza);
            return;
        }
        try {
            y2 y2Var = this.zzb;
            d3 d3Var = (d3) y2Var.getService();
            int displayId = display.getDisplayId();
            y2Var.getContext();
            j jVarI = u0.i();
            Parcel parcelZza = d3Var.zza();
            a1.d(parcelZza, this);
            parcelZza.writeInt(displayId);
            a1.c(parcelZza, jVarI);
            d3Var.zzd(5, parcelZza);
        } catch (RemoteException | IllegalStateException unused) {
            this.zzc.zzb().e("Unable to provision the route's new virtual Display", new Object[0]);
            d.S(status, null, this.zza);
        }
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.c3
    public final void zzc(j jVar) {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzc;
        castRemoteDisplayClient.zzb().d("onConnectedWithDisplay", new Object[0]);
        VirtualDisplay virtualDisplayZzc = castRemoteDisplayClient.zzc();
        Status status = Status.f12672q;
        if (virtualDisplayZzc == null) {
            castRemoteDisplayClient.zzb().e("There is no virtual display", new Object[0]);
            d.S(status, null, this.zza);
            return;
        }
        Display display = castRemoteDisplayClient.zzc().getDisplay();
        if (display != null) {
            d.S(Status.f12670o, display, this.zza);
        } else {
            castRemoteDisplayClient.zzb().e("Virtual display no longer has a display", new Object[0]);
            d.S(status, null, this.zza);
        }
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.c3
    public final void zzd(int i10, j jVar) throws RemoteException {
        Object[] objArr = {Integer.valueOf(i10)};
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzc;
        castRemoteDisplayClient.zzb().d("onError: %d", objArr);
        castRemoteDisplayClient.zza();
        d.S(Status.f12672q, null, this.zza);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.c3
    public final void zze(boolean z, j jVar) {
        this.zzc.zzb().d("onRemoteDisplayMuteStateChanged: %b", Boolean.valueOf(z));
        zzag zzagVar = this.zzd;
        if (zzagVar != null) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(z).length() + 33);
            sb2.append("onRemoteDisplayMuteStateChanged: ");
            sb2.append(z);
            String string = sb2.toString();
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = zzagVar.zza;
            castRemoteDisplayLocalService.zzg(string);
            CastRemoteDisplayLocalService.Callbacks callbacks = (CastRemoteDisplayLocalService.Callbacks) castRemoteDisplayLocalService.zzl().get();
            if (callbacks != null) {
                callbacks.onRemoteDisplayMuteStateChanged(z);
            }
        }
    }
}
