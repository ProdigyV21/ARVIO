package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.compose.foundation.c;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.t;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.api.j;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.d3;
import com.google.android.gms.internal.cast.u0;
import com.google.android.gms.internal.cast.y2;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class CastRemoteDisplayClient extends n {
    private static final a zzc;
    private static final i zzd;
    private final Logger zza;
    private VirtualDisplay zzb;

    static {
        zzv zzvVar = new zzv();
        zzc = zzvVar;
        zzd = new i("CastRemoteDisplay.API", zzvVar, com.google.android.gms.cast.internal.zzal.zzd);
    }

    public CastRemoteDisplayClient(Context context) {
        super(context, null, zzd, d.f12682a, m.f12824c);
        this.zza = new Logger("CastRemoteDisplay");
    }

    public h startRemoteDisplay(CastDevice castDevice, String str, int i10, PendingIntent pendingIntent) {
        return zze(castDevice, str, i10, pendingIntent, null);
    }

    public h stopRemoteDisplay() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12810d = 8402;
        xVarBuilder.f12807a = new t() { // from class: com.google.android.gms.cast.zzz
            @Override // com.google.android.gms.common.api.internal.t
            public final void accept(Object obj, Object obj2) throws RemoteException {
                y2 y2Var = (y2) obj;
                zzx zzxVar = new zzx(this.zza, (com.google.android.gms.tasks.i) obj2);
                d3 d3Var = (d3) y2Var.getService();
                y2Var.getContext();
                j jVarI = u0.i();
                Parcel parcelZza = d3Var.zza();
                a1.d(parcelZza, zzxVar);
                a1.c(parcelZza, jVarI);
                d3Var.zzd(6, parcelZza);
            }
        };
        return doWrite(xVarBuilder.a());
    }

    public final /* synthetic */ void zza() {
        VirtualDisplay virtualDisplay = this.zzb;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                Logger logger = this.zza;
                int displayId = this.zzb.getDisplay().getDisplayId();
                logger.d(c.p(displayId, "releasing virtual display: ", new StringBuilder(String.valueOf(displayId).length() + 27)), new Object[0]);
            }
            VirtualDisplay virtualDisplay2 = this.zzb;
            if (virtualDisplay2 != null) {
                virtualDisplay2.release();
                this.zzb = null;
            }
        }
    }

    public final /* synthetic */ Logger zzb() {
        return this.zza;
    }

    public final /* synthetic */ VirtualDisplay zzc() {
        return this.zzb;
    }

    public final /* synthetic */ void zzd(VirtualDisplay virtualDisplay) {
        this.zzb = virtualDisplay;
    }

    public final h zze(final CastDevice castDevice, final String str, final int i10, final PendingIntent pendingIntent, final zzag zzagVar) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12810d = 8401;
        xVarBuilder.f12807a = new t() { // from class: com.google.android.gms.cast.zzaa
            @Override // com.google.android.gms.common.api.internal.t
            public final void accept(Object obj, Object obj2) throws RemoteException {
                y2 y2Var = (y2) obj;
                Bundle bundle = new Bundle();
                bundle.putInt("configuration", i10);
                zzag zzagVar2 = zzagVar;
                zzw zzwVar = new zzw(this.zza, (com.google.android.gms.tasks.i) obj2, y2Var, zzagVar2);
                d3 d3Var = (d3) y2Var.getService();
                String deviceId = castDevice.getDeviceId();
                y2Var.getContext();
                j jVarI = u0.i();
                String str2 = str;
                PendingIntent pendingIntent2 = pendingIntent;
                Parcel parcelZza = d3Var.zza();
                a1.d(parcelZza, zzwVar);
                a1.c(parcelZza, pendingIntent2);
                parcelZza.writeString(deviceId);
                parcelZza.writeString(str2);
                a1.c(parcelZza, bundle);
                a1.c(parcelZza, jVarI);
                d3Var.zzd(8, parcelZza);
            }
        };
        return doWrite(xVarBuilder.a());
    }
}
