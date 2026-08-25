package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzay implements RemoteMediaClient.MediaChannelResult {
    final /* synthetic */ Status zza;

    public zzay(zzaz zzazVar, Status status) {
        this.zza = status;
        Objects.requireNonNull(zzazVar);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    public final JSONObject getCustomData() {
        return null;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    public final MediaError getMediaError() {
        return null;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.zza;
    }
}
