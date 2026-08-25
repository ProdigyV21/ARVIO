package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbd implements RemoteMediaClient.MediaChannelResult {
    private final Status zza;
    private final JSONObject zzb;
    private final MediaError zzc;

    public zzbd(Status status, JSONObject jSONObject, MediaError mediaError) {
        this.zza = status;
        this.zzb = jSONObject;
        this.zzc = mediaError;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    public final JSONObject getCustomData() {
        return this.zzb;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    public final MediaError getMediaError() {
        return this.zzc;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.zza;
    }
}
