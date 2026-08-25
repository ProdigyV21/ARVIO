package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzm implements RemoteMediaClient.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public /* synthetic */ zzm(ExpandedControllerActivity expandedControllerActivity, byte[] bArr) {
        Objects.requireNonNull(expandedControllerActivity);
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onAdBreakStatusUpdated() {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onMetadataUpdated() {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onPreloadStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onQueueStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onSendingRemoteMediaRequest() {
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        expandedControllerActivity.zzf().setText(expandedControllerActivity.getResources().getString(R.string.cast_expanded_controller_loading));
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onStatusUpdated() {
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        RemoteMediaClient remoteMediaClientZza = expandedControllerActivity.zza();
        if (remoteMediaClientZza == null || !remoteMediaClientZza.hasMediaSession()) {
            if (expandedControllerActivity.zzd) {
                return;
            }
            expandedControllerActivity.finish();
        } else {
            expandedControllerActivity.zzd = false;
            expandedControllerActivity.zzc();
            expandedControllerActivity.zzd();
        }
    }
}
