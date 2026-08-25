package com.google.android.gms.cast.framework;

/* JADX INFO: loaded from: classes4.dex */
public class MediaNotificationManager {
    private final SessionManager zza;

    public MediaNotificationManager(SessionManager sessionManager) {
        this.zza = sessionManager;
    }

    public void updateNotification() {
        CastSession currentCastSession = this.zza.getCurrentCastSession();
        if (currentCastSession != null) {
            currentCastSession.zzc().zzd(true);
        }
    }
}
