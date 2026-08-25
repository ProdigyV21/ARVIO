package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import j$.util.Objects;
import o3.b;

/* JADX INFO: loaded from: classes4.dex */
final class zzy extends com.google.android.gms.cast.zzp {
    final /* synthetic */ CastSession zza;

    public /* synthetic */ zzy(CastSession castSession, byte[] bArr) {
        Objects.requireNonNull(castSession);
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zza() {
        CastSession castSession = this.zza;
        if (castSession.zzi() == null) {
            return;
        }
        try {
            if (castSession.zzk() != null) {
                castSession.zzk().zzb();
            }
            castSession.zzi().zze(null);
        } catch (RemoteException e5) {
            CastSession.zzb.d(e5, "Unable to call %s on %s.", "onConnected", "zzak");
        }
        CastSession castSession2 = this.zza;
        if (castSession2.zzl() != null) {
            castSession2.zzl().zza();
        }
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zzb(int i10) {
        CastSession castSession = this.zza;
        if (castSession.zzi() == null) {
            return;
        }
        try {
            castSession.zzi().zzg(new b(i10, null, null));
        } catch (RemoteException e5) {
            CastSession.zzb.d(e5, "Unable to call %s on %s.", "onConnectionFailed", "zzak");
        }
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zzc(int i10) {
        CastSession castSession = this.zza;
        if (castSession.zzi() == null) {
            return;
        }
        try {
            castSession.zzi().zzf(i10);
        } catch (RemoteException e5) {
            CastSession.zzb.d(e5, "Unable to call %s on %s.", "onConnectionSuspended", "zzak");
        }
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zzd(int i10) {
        CastSession castSession = this.zza;
        if (castSession.zzi() == null) {
            return;
        }
        try {
            castSession.zzi().zzg(new b(i10, null, null));
        } catch (RemoteException e5) {
            CastSession.zzb.d(e5, "Unable to call %s on %s.", "onDisconnected", "zzak");
        }
    }
}
