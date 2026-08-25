package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m5;

/* JADX INFO: loaded from: classes4.dex */
public class PrecacheManager {
    private final Logger zza = new Logger("PrecacheManager");
    private final CastOptions zzb;
    private final SessionManager zzc;
    private final com.google.android.gms.cast.internal.zzn zzd;

    public PrecacheManager(CastOptions castOptions, SessionManager sessionManager, com.google.android.gms.cast.internal.zzn zznVar) {
        this.zzb = castOptions;
        this.zzc = sessionManager;
        this.zzd = zznVar;
    }

    public void precache(String str) {
        l6.a(m5.PRECACHE);
        Session currentSession = this.zzc.getCurrentSession();
        if (str == null) {
            throw new IllegalArgumentException("No precache data found to be precached");
        }
        if (currentSession == null) {
            this.zzd.zza(new String[]{this.zzb.getReceiverApplicationId()}, str, null);
            return;
        }
        if (!(currentSession instanceof CastSession)) {
            this.zza.e("Current session is not a CastSession. Precache is not supported.", new Object[0]);
            return;
        }
        RemoteMediaClient remoteMediaClient = ((CastSession) currentSession).getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.zzh(str, null);
        } else {
            this.zza.e("Failed to get RemoteMediaClient from current cast session.", new Object[0]);
        }
    }
}
