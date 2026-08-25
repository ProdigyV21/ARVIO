package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.d0;
import com.google.android.gms.internal.cast.h0;

/* JADX INFO: loaded from: classes4.dex */
public class ReconnectionService extends Service {
    private static final Logger zza = new Logger("ReconnectionService");
    private zzar zzb;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        zzar zzarVar = this.zzb;
        if (zzarVar != null) {
            try {
                return zzarVar.zzg(intent);
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "onBind", "zzar");
            }
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        b bVarZzd = sharedInstance.getSessionManager().zzd();
        b bVarZza = sharedInstance.zzc().zza();
        Logger logger = d0.f13226a;
        zzar zzarVarS = null;
        if (bVarZzd != null && bVarZza != null) {
            try {
                zzarVarS = d0.a(getApplicationContext()).S(new d(this), bVarZzd, bVarZza);
            } catch (RemoteException | ModuleUnavailableException e5) {
                d0.f13226a.d(e5, "Unable to call %s on %s.", "newReconnectionServiceImpl", h0.class.getSimpleName());
            }
        }
        this.zzb = zzarVarS;
        if (zzarVarS != null) {
            try {
                zzarVarS.zze();
            } catch (RemoteException e6) {
                zza.d(e6, "Unable to call %s on %s.", "onCreate", "zzar");
            }
            super.onCreate();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        zzar zzarVar = this.zzb;
        if (zzarVar != null) {
            try {
                zzarVar.zzh();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "onDestroy", "zzar");
            }
            super.onDestroy();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        zzar zzarVar = this.zzb;
        if (zzarVar != null) {
            try {
                return zzarVar.zzf(intent, i10, i11);
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "onStartCommand", "zzar");
            }
        }
        return 2;
    }
}
