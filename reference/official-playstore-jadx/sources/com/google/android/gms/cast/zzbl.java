package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class zzbl extends com.google.android.gms.cast.internal.zzai {
    final /* synthetic */ zzbm zza;

    public zzbl(zzbm zzbmVar) {
        Objects.requireNonNull(zzbmVar);
        this.zza = zzbmVar;
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzb(final int i10) {
        this.zza.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbk
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                zzbl zzblVar = this.zza;
                zzbm zzbmVar = zzblVar.zza;
                zzbmVar.zzL();
                zzbmVar.zzU(1);
                List listZzT = zzbmVar.zzT();
                int i11 = i10;
                synchronized (listZzT) {
                    try {
                        Iterator it = zzbmVar.zzT().iterator();
                        while (it.hasNext()) {
                            ((zzp) it.next()).zzd(i11);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                zzbm zzbmVar2 = zzblVar.zza;
                zzbmVar2.zzD();
                zzbmVar2.zzC(zzbmVar2.zza);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzc(final int i10) {
        this.zza.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbe
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                int i11 = i10;
                zzbl zzblVar = this.zza;
                if (i11 != 0) {
                    zzbm zzbmVar = zzblVar.zza;
                    zzbmVar.zzU(1);
                    synchronized (zzbmVar.zzT()) {
                        try {
                            Iterator it = zzbmVar.zzT().iterator();
                            while (it.hasNext()) {
                                ((zzp) it.next()).zzb(i11);
                            }
                        } finally {
                        }
                    }
                    zzblVar.zza.zzD();
                    return;
                }
                zzbm zzbmVar2 = zzblVar.zza;
                zzbmVar2.zzU(3);
                zzbmVar2.zzN(true);
                zzbmVar2.zzO(true);
                synchronized (zzbmVar2.zzT()) {
                    try {
                        Iterator it2 = zzbmVar2.zzT().iterator();
                        while (it2.hasNext()) {
                            ((zzp) it2.next()).zza();
                        }
                    } finally {
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzd(final int i10) {
        this.zza.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbf
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                zzbm zzbmVar = this.zza.zza;
                zzbmVar.zzU(4);
                List listZzT = zzbmVar.zzT();
                int i11 = i10;
                synchronized (listZzT) {
                    try {
                        Iterator it = zzbmVar.zzT().iterator();
                        while (it.hasNext()) {
                            ((zzp) it.next()).zzc(i11);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zze(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzbm zzbmVar = this.zza;
        zzbmVar.zzP(applicationMetadata);
        zzbmVar.zzQ(str);
        zzbmVar.zzG(new com.google.android.gms.cast.internal.zzr(new Status(0, null, null, null), applicationMetadata, str, str2, z));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzf(int i10) {
        this.zza.zzH(i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzg(int i10) {
        this.zza.zzI(i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzh(int i10) {
        this.zza.zzI(i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzi(final int i10) {
        zzbm zzbmVar = this.zza;
        zzbmVar.zzI(i10);
        if (zzbmVar.zzS() != null) {
            zzbmVar.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbg
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    this.zza.zza.zzS().onApplicationDisconnected(i10);
                }
            });
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzj(String str, double d4, boolean z) {
        zzbm.zzg.d("Deprecated callback: \"onStatusReceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzk(final com.google.android.gms.cast.internal.zzac zzacVar) {
        this.zza.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbh
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                this.zza.zza.zzE(zzacVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzl(final com.google.android.gms.cast.internal.zza zzaVar) {
        this.zza.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbi
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                this.zza.zza.zzF(zzaVar);
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzm(final String str, final String str2) {
        zzbm.zzg.d("Receive (type=text, ns=%s) %s", str, str2);
        this.zza.zzK().post(new Runnable() { // from class: com.google.android.gms.cast.zzbj
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                Cast.MessageReceivedCallback messageReceivedCallback;
                zzbl zzblVar = this.zza;
                Map map = zzblVar.zza.zze;
                String str3 = str;
                synchronized (map) {
                    messageReceivedCallback = (Cast.MessageReceivedCallback) map.get(str3);
                }
                if (messageReceivedCallback != null) {
                    messageReceivedCallback.onMessageReceived(zzblVar.zza.zzR(), str3, str2);
                } else {
                    zzbm.zzg.d("Discarded message for unknown namespace '%s'", str3);
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzn(String str, byte[] bArr) {
        int i10 = zzbm.zzf;
        zzbm.zzg.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzo(String str, long j10, int i10) {
        this.zza.zzJ(j10, i10);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzp(String str, long j10) {
        this.zza.zzJ(j10, 0);
    }
}
