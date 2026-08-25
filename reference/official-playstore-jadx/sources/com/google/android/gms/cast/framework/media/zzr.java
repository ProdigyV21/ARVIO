package com.google.android.gms.cast.framework.media;

import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import j$.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzr extends zzf {
    final /* synthetic */ NotificationActionsProvider zza;

    public /* synthetic */ zzr(NotificationActionsProvider notificationActionsProvider, byte[] bArr) {
        Objects.requireNonNull(notificationActionsProvider);
        this.zza = notificationActionsProvider;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final b zze() {
        return new d(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final List zzf() {
        return this.zza.getNotificationActions();
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final int[] zzg() {
        return this.zza.getCompactViewActionIndices();
    }
}
