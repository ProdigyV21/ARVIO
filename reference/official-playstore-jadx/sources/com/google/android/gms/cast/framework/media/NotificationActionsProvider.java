package com.google.android.gms.cast.framework.media;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class NotificationActionsProvider {
    private final Context zza;
    private final zzg zzb = new zzr(this, null);

    public NotificationActionsProvider(Context context) {
        this.zza = context.getApplicationContext();
    }

    public Context getApplicationContext() {
        return this.zza;
    }

    public abstract int[] getCompactViewActionIndices();

    public abstract List<NotificationAction> getNotificationActions();

    public final zzg zza() {
        return this.zzb;
    }
}
