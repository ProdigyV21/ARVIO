package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.d0;
import com.google.android.gms.internal.cast.h0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Session {
    private static final Logger zza = new Logger("Session");
    private final zzau zzb;
    private final zzbf zzc;

    /* JADX WARN: Multi-variable type inference failed */
    public Session(Context context, String str, String str2) {
        zzau zzauVarD0 = null;
        zzbf zzbfVar = new zzbf(this, 0 == true ? 1 : 0);
        this.zzc = zzbfVar;
        try {
            zzauVarD0 = d0.a(context).d0(str, str2, zzbfVar);
        } catch (RemoteException | ModuleUnavailableException e5) {
            d0.f13226a.d(e5, "Unable to call %s on %s.", "newSessionImpl", h0.class.getSimpleName());
        }
        this.zzb = zzauVarD0;
    }

    public abstract void end(boolean z);

    public final String getCategory() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzg();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "getCategory", "zzau");
            }
        }
        return null;
    }

    public final String getSessionId() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzh();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "getSessionId", "zzau");
            }
        }
        return null;
    }

    public long getSessionRemainingTimeMs() {
        t.e("Must be called from the main thread.");
        return 0L;
    }

    public boolean isConnected() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzi();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "isConnected", "zzau");
            }
        }
        return false;
    }

    public boolean isConnecting() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzj();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "isConnecting", "zzau");
            }
        }
        return false;
    }

    public boolean isDisconnected() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzl();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "isDisconnected", "zzau");
            }
        }
        return true;
    }

    public boolean isDisconnecting() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzk();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "isDisconnecting", "zzau");
            }
        }
        return false;
    }

    public boolean isResuming() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzm();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "isResuming", "zzau");
            }
        }
        return false;
    }

    public boolean isSuspended() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzn();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "isSuspended", "zzau");
            }
        }
        return false;
    }

    public final void notifyFailedToResumeSession(int i10) {
        zzau zzauVar = this.zzb;
        if (zzauVar == null) {
            return;
        }
        try {
            zzauVar.zzt(i10);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "notifyFailedToResumeSession", "zzau");
        }
    }

    public final void notifyFailedToStartSession(int i10) {
        zzau zzauVar = this.zzb;
        if (zzauVar == null) {
            return;
        }
        try {
            zzauVar.zzq(i10);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "notifyFailedToStartSession", "zzau");
        }
    }

    public final void notifySessionEnded(int i10) {
        zzau zzauVar = this.zzb;
        if (zzauVar == null) {
            return;
        }
        try {
            zzauVar.zzr(i10);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "notifySessionEnded", "zzau");
        }
    }

    public final void notifySessionResumed(boolean z) {
        zzau zzauVar = this.zzb;
        if (zzauVar == null) {
            return;
        }
        try {
            zzauVar.zzs(z);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "notifySessionResumed", "zzau");
        }
    }

    public final void notifySessionStarted(String str) {
        zzau zzauVar = this.zzb;
        if (zzauVar == null) {
            return;
        }
        try {
            zzauVar.zzp(str);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "notifySessionStarted", "zzau");
        }
    }

    public final void notifySessionSuspended(int i10) {
        zzau zzauVar = this.zzb;
        if (zzauVar == null) {
            return;
        }
        try {
            zzauVar.zzu(i10);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "notifySessionSuspended", "zzau");
        }
    }

    public void onResuming(Bundle bundle) {
    }

    public void onStarting(Bundle bundle) {
    }

    public abstract void resume(Bundle bundle);

    public abstract void start(Bundle bundle);

    public void zza(Bundle bundle) {
    }

    public final int zzm() {
        t.e("Must be called from the main thread.");
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                if (zzauVar.zze() >= 211100000) {
                    return zzauVar.zzo();
                }
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "getSessionStartType", "zzau");
            }
        }
        return 0;
    }

    public final b zzn() {
        zzau zzauVar = this.zzb;
        if (zzauVar != null) {
            try {
                return zzauVar.zzf();
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "getWrappedObject", "zzau");
            }
        }
        return null;
    }
}
