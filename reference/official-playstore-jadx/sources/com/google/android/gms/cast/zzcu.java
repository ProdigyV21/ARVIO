package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcu extends zzdc {
    final /* synthetic */ long zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcu(RemoteMediaPlayer remoteMediaPlayer, q qVar, long j10, int i10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = j10;
        this.zzb = i10;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzd = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        com.google.android.gms.cast.internal.zzat zzatVarZzb = zzb();
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(this.zza);
        builder.setResumeState(this.zzb);
        builder.setCustomData(this.zzc);
        this.zzd.zzg().zzn(zzatVarZzb, builder.build());
    }
}
