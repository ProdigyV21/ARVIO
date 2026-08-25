package com.google.android.gms.cast;

import androidx.compose.foundation.c;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import j$.util.Objects;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcp extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcp(RemoteMediaPlayer remoteMediaPlayer, q qVar, int i10, int i11, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = i10;
        this.zzb = i11;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzd = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        RemoteMediaPlayer remoteMediaPlayer = this.zzd;
        int i10 = this.zza;
        int iZza = remoteMediaPlayer.zza(i10);
        if (iZza == -1) {
            setResult(new zzdb(this, new Status(0, null, null, null)));
            return;
        }
        int i11 = this.zzb;
        if (i11 < 0) {
            Locale locale = Locale.ROOT;
            setResult(new zzdb(this, new Status(2001, c.o(i11, "Invalid request: Invalid newIndex ", "."), null, null)));
            return;
        }
        if (iZza == i11) {
            setResult(new zzdb(this, new Status(0, null, null, null)));
            return;
        }
        MediaStatus mediaStatus = remoteMediaPlayer.getMediaStatus();
        if (mediaStatus == null) {
            Locale locale2 = Locale.ROOT;
            setResult(new zzdb(this, new Status(2001, "Invalid request: Invalid MediaStatus", null, null)));
        } else {
            if (i11 > iZza) {
                i11++;
            }
            MediaQueueItem queueItem = mediaStatus.getQueueItem(i11);
            remoteMediaPlayer.zzg().zzG(zzb(), new int[]{i10}, queueItem != null ? queueItem.getItemId() : 0, this.zzc);
        }
    }
}
