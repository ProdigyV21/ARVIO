package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzdb implements RemoteMediaPlayer.MediaChannelResult {
    final /* synthetic */ Status zza;

    public zzdb(zzdc zzdcVar, Status status) {
        this.zza = status;
        Objects.requireNonNull(zzdcVar);
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
    public final JSONObject getCustomData() {
        return null;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.zza;
    }
}
