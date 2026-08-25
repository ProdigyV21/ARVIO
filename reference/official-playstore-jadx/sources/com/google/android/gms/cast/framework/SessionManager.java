package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;

/* JADX INFO: loaded from: classes4.dex */
public class SessionManager {
    private static final Logger zza = new Logger("SessionManager");
    private final zzaw zzb;
    private final Context zzc;

    public SessionManager(zzaw zzawVar, Context context) {
        this.zzb = zzawVar;
        this.zzc = context;
    }

    public void addSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) throws NullPointerException {
        t.e("Must be called from the main thread.");
        addSessionManagerListener(sessionManagerListener, Session.class);
    }

    public void endCurrentSession(boolean z) {
        t.e("Must be called from the main thread.");
        try {
            zza.i("End session for %s", this.zzc.getPackageName());
            this.zzb.zzj(true, z);
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "endCurrentSession", "zzaw");
        }
    }

    public CastSession getCurrentCastSession() {
        t.e("Must be called from the main thread.");
        Session currentSession = getCurrentSession();
        if (currentSession == null || !(currentSession instanceof CastSession)) {
            return null;
        }
        return (CastSession) currentSession;
    }

    public Session getCurrentSession() {
        t.e("Must be called from the main thread.");
        try {
            return (Session) d.g0(this.zzb.zze());
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "getWrappedCurrentSession", "zzaw");
            return null;
        }
    }

    public void removeSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) {
        t.e("Must be called from the main thread.");
        removeSessionManagerListener(sessionManagerListener, Session.class);
    }

    public void startSession(Intent intent) {
        try {
            Logger logger = zza;
            Context context = this.zzc;
            logger.i("Start session for %s", context.getPackageName());
            Bundle extras = intent.getExtras();
            if (extras != null && extras.getString("CAST_INTENT_TO_CAST_ROUTE_ID_KEY") != null) {
                String string = extras.getString("CAST_INTENT_TO_CAST_DEVICE_NAME_KEY");
                if (!extras.getBoolean("CAST_INTENT_TO_CAST_NO_TOAST_KEY")) {
                    Toast.makeText(context, context.getString(R.string.cast_connecting_to_device, string), 0).show();
                }
                this.zzb.zzm(new Bundle(extras));
                intent.removeExtra("CAST_INTENT_TO_CAST_ROUTE_ID_KEY");
            }
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "startSession", "zzaw");
        }
    }

    public final int zza() {
        try {
            return this.zzb.zzl();
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "addCastStateListener", "zzaw");
            return 1;
        }
    }

    public final void zzb(CastStateListener castStateListener) throws NullPointerException {
        t.i(castStateListener);
        try {
            this.zzb.zzh(new zzz(castStateListener));
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "addCastStateListener", "zzaw");
        }
    }

    public final void zzc(CastStateListener castStateListener) {
        try {
            this.zzb.zzi(new zzz(castStateListener));
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "removeCastStateListener", "zzaw");
        }
    }

    public final b zzd() {
        try {
            return this.zzb.zzk();
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "getWrappedThis", "zzaw");
            return null;
        }
    }

    public <T extends Session> void addSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class<T> cls) throws NullPointerException {
        if (sessionManagerListener != null) {
            t.i(cls);
            t.e("Must be called from the main thread.");
            try {
                this.zzb.zzf(new zzbg(sessionManagerListener, cls));
                return;
            } catch (RemoteException e5) {
                zza.d(e5, "Unable to call %s on %s.", "addSessionManagerListener", "zzaw");
                return;
            }
        }
        throw new NullPointerException("SessionManagerListener can't be null");
    }

    public <T extends Session> void removeSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class<T> cls) {
        t.i(cls);
        t.e("Must be called from the main thread.");
        if (sessionManagerListener == null) {
            return;
        }
        try {
            this.zzb.zzg(new zzbg(sessionManagerListener, cls));
        } catch (RemoteException e5) {
            zza.d(e5, "Unable to call %s on %s.", "removeSessionManagerListener", "zzaw");
        }
    }
}
