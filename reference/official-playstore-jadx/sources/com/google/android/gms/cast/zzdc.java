package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.x;
import j$.util.Objects;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes4.dex */
abstract class zzdc extends com.google.android.gms.cast.internal.zzc {
    private com.google.android.gms.cast.internal.zzat zza;
    protected final WeakReference zzg;
    final /* synthetic */ RemoteMediaPlayer zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdc(RemoteMediaPlayer remoteMediaPlayer, q qVar) {
        super(qVar);
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzh = remoteMediaPlayer;
        this.zzg = new WeakReference(qVar);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ x createFailedResult(Status status) {
        return new zzdb(this, status);
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final void doExecute(b bVar) throws RemoteException {
        com.google.android.gms.cast.internal.zzx zzxVar = (com.google.android.gms.cast.internal.zzx) bVar;
        RemoteMediaPlayer remoteMediaPlayer = this.zzh;
        synchronized (remoteMediaPlayer.zzf()) {
            try {
                q qVar = (q) this.zzg.get();
                if (qVar == null) {
                    setResult(new zzdb(this, new Status(2100, null, null, null)));
                    return;
                }
                remoteMediaPlayer.zzh().zza(qVar);
                try {
                    zza(zzxVar);
                } catch (IllegalArgumentException e5) {
                    throw e5;
                } catch (Throwable unused) {
                    setResult(new zzdb(this, new Status(2100, null, null, null)));
                }
                this.zzh.zzh().zza(null);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap;

    public final com.google.android.gms.cast.internal.zzat zzb() {
        if (this.zza == null) {
            this.zza = new zzda(this);
        }
        return this.zza;
    }
}
