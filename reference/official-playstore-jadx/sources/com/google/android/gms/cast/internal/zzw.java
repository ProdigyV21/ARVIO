package com.google.android.gms.cast.internal;

import android.os.Handler;
import androidx.loader.content.j;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
final class zzw extends zzai {
    private final AtomicReference zza;
    private final Handler zzb;

    public zzw(zzx zzxVar) {
        this.zza = new AtomicReference(zzxVar);
        this.zzb = new j(zzxVar.getLooper(), 2);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzb(int i10) {
        zzx zzxVarZzq = zzq();
        if (zzxVarZzq == null) {
            return;
        }
        zzx.zzf.d("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i10));
        if (i10 != 0) {
            zzxVarZzq.triggerConnectionSuspended(2);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzc(int i10) {
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzd(int i10) {
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zze(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzO(applicationMetadata);
        zzxVar.zzT(applicationMetadata.getApplicationId());
        zzxVar.zzU(str2);
        zzxVar.zzS(str);
        synchronized (zzx.zzD) {
            try {
                if (zzxVar.zzV() != null) {
                    zzxVar.zzV().setResult(new zzr(new Status(0, null, null, null), applicationMetadata, str, str2, z));
                    zzxVar.zzW(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzf(int i10) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzH(i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzg(int i10) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzM(i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzh(int i10) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzM(i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzi(int i10) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzT(null);
        zzxVar.zzU(null);
        zzxVar.zzM(i10);
        if (zzxVar.zzQ() != null) {
            this.zzb.post(new zzs(this, zzxVar, i10));
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzj(String str, double d4, boolean z) {
        zzx.zzf.d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzk(zzac zzacVar) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzx.zzf.d("onDeviceStatusChanged", new Object[0]);
        this.zzb.post(new zzt(this, zzxVar, zzacVar));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzl(zza zzaVar) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzx.zzf.d("onApplicationStatusChanged", new Object[0]);
        this.zzb.post(new zzu(this, zzxVar, zzaVar));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzm(String str, String str2) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzx.zzf.d("Receive (type=text, ns=%s) %s", str, str2);
        this.zzb.post(new zzv(this, zzxVar, str, str2));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzn(String str, byte[] bArr) {
        if (((zzx) this.zza.get()) == null) {
            return;
        }
        int i10 = zzx.zze;
        zzx.zzf.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzo(String str, long j10, int i10) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzL(j10, i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzp(String str, long j10) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzL(j10, 0);
    }

    public final zzx zzq() {
        zzx zzxVar = (zzx) this.zza.getAndSet(null);
        if (zzxVar == null) {
            return null;
        }
        zzxVar.zzI();
        return zzxVar;
    }

    public final boolean zzr() {
        return this.zza.get() == null;
    }
}
