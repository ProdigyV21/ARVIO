package com.google.android.gms.internal.cast;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class t2 extends s2 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a3 f13613c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ q2 f13614d;

    public t2(q2 q2Var, a3 a3Var) {
        this.f13614d = q2Var;
        this.f13613c = a3Var;
    }

    @Override // com.google.android.gms.internal.cast.s2, com.google.android.gms.internal.cast.c3
    public final void zzb(int i10, int i11, Surface surface, com.google.android.gms.common.api.j jVar) {
        Logger logger = x2.f13682d;
        logger.d("onConnected", new Object[0]);
        a3 a3Var = this.f13613c;
        DisplayManager displayManager = (DisplayManager) a3Var.getContext().getSystemService("display");
        q2 q2Var = this.f13614d;
        Status status = Status.f12672q;
        if (displayManager == null) {
            logger.e("Unable to get the display manager", new Object[0]);
            q2Var.setResult(new w2(status));
            return;
        }
        x2 x2Var = q2Var.f13636a;
        x2Var.a();
        VirtualDisplay virtualDisplayCreateVirtualDisplay = displayManager.createVirtualDisplay("private_display", i10, i11, ((i10 < i11 ? i10 : i11) * 320) / 1080, surface, 2);
        x2Var.f13684b = virtualDisplayCreateVirtualDisplay;
        if (virtualDisplayCreateVirtualDisplay == null) {
            logger.e("Unable to create virtual display", new Object[0]);
            q2Var.setResult(new w2(status));
            return;
        }
        if (virtualDisplayCreateVirtualDisplay.getDisplay() == null) {
            logger.e("Virtual display does not have a display", new Object[0]);
            q2Var.setResult(new w2(status));
            return;
        }
        try {
            int displayId = x2Var.f13684b.getDisplay().getDisplayId();
            d3 d3Var = (d3) a3Var.getService();
            a3Var.getContext();
            com.google.android.gms.common.api.j jVarI = u0.i();
            Parcel parcelZza = d3Var.zza();
            a1.d(parcelZza, this);
            parcelZza.writeInt(displayId);
            a1.c(parcelZza, jVarI);
            d3Var.zzd(5, parcelZza);
        } catch (RemoteException | IllegalStateException unused) {
            x2.f13682d.e("Unable to provision the route's new virtual Display", new Object[0]);
            q2Var.setResult(new w2(status));
        }
    }

    @Override // com.google.android.gms.internal.cast.s2, com.google.android.gms.internal.cast.c3
    public final void zzc(com.google.android.gms.common.api.j jVar) {
        Logger logger = x2.f13682d;
        logger.d("onConnectedWithDisplay", new Object[0]);
        q2 q2Var = this.f13614d;
        VirtualDisplay virtualDisplay = q2Var.f13636a.f13684b;
        Status status = Status.f12672q;
        if (virtualDisplay == null) {
            logger.e("There is no virtual display", new Object[0]);
            q2Var.setResult(new w2(status));
            return;
        }
        Display display = virtualDisplay.getDisplay();
        if (display != null) {
            q2Var.setResult(new w2(display));
        } else {
            logger.e("Virtual display no longer has a display", new Object[0]);
            q2Var.setResult(new w2(status));
        }
    }

    @Override // com.google.android.gms.internal.cast.c3
    public final void zzd(int i10, com.google.android.gms.common.api.j jVar) {
        Logger logger = x2.f13682d;
        x2.f13682d.d("onError: %d", Integer.valueOf(i10));
        q2 q2Var = this.f13614d;
        q2Var.f13636a.a();
        q2Var.setResult(new w2(Status.f12672q));
    }
}
