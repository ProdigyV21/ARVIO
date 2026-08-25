package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbg extends zzax {

    @NotOnlyInitialized
    private final SessionManagerListener zza;
    private final Class zzb;

    public zzbg(SessionManagerListener sessionManagerListener, Class cls) {
        this.zza = sessionManagerListener;
        this.zzb = cls;
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final b zzb() {
        return new d(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzc(b bVar) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStarting((Session) cls.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzd(b bVar, String str) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStarted((Session) cls.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zze(b bVar, int i10) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStartFailed((Session) cls.cast(session), i10);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzf(b bVar) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionEnding((Session) cls.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzg(b bVar, int i10) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionEnded((Session) cls.cast(session), i10);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzh(b bVar, String str) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResuming((Session) cls.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzi(b bVar, boolean z) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResumed((Session) cls.cast(session), z);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzj(b bVar, int i10) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResumeFailed((Session) cls.cast(session), i10);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzk(b bVar, int i10) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) d.g0(bVar);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionSuspended((Session) cls.cast(session), i10);
    }
}
