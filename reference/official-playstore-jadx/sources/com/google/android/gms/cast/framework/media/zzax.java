package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.d;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.j;
import com.google.android.gms.tasks.t;
import j$.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes4.dex */
final class zzax implements com.google.android.gms.cast.internal.zzas {
    final /* synthetic */ RemoteMediaClient zza;
    private com.google.android.gms.cast.zzq zzb;
    private final AtomicLong zzc;

    public zzax(RemoteMediaClient remoteMediaClient) {
        Objects.requireNonNull(remoteMediaClient);
        this.zza = remoteMediaClient;
        this.zzc = new AtomicLong((CastUtils.zzb() & 65535) * 10000);
    }

    public final void zza(com.google.android.gms.cast.zzq zzqVar) {
        this.zzb = zzqVar;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final void zzb(String str, String str2, final long j10, String str3) {
        com.google.android.gms.cast.zzq zzqVar = this.zzb;
        if (zzqVar == null) {
            throw new IllegalStateException("Device is not connected");
        }
        h hVarZzf = zzqVar.zzf(str, str2);
        d dVar = new d() { // from class: com.google.android.gms.cast.framework.media.zzaw
            @Override // com.google.android.gms.tasks.d
            public final /* synthetic */ void onFailure(Exception exc) {
                this.zza.zza.zzt().zzN(j10, exc instanceof ApiException ? ((ApiException) exc).getStatusCode() : 13);
            }
        };
        t tVar = (t) hVarZzf;
        tVar.getClass();
        tVar.b(j.f13856a, dVar);
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final long zzc() {
        return this.zzc.getAndIncrement();
    }
}
