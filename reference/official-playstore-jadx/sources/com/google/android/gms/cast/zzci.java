package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzci extends zzdc {
    final /* synthetic */ int[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzci(RemoteMediaPlayer remoteMediaPlayer, q qVar, int[] iArr, int i10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = iArr;
        this.zzb = i10;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzd = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        this.zzd.zzg().zzG(zzb(), this.zza, this.zzb, this.zzc);
    }
}
