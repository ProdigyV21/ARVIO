package com.google.android.gms.cast.framework.media.internal;

import android.os.RemoteException;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.internal.Logger;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzt {
    private static final Logger zza = new Logger("MediaSessionUtils");

    /* JADX WARN: Removed duplicated region for block: B:13:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002c A[PHI: r1
      0x002c: PHI (r1v5 java.lang.String) = (r1v3 java.lang.String), (r1v4 java.lang.String) binds: [B:18:0x002a, B:21:0x0034] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String zza(com.google.android.gms.cast.MediaMetadata r4) {
        /*
            java.lang.String r0 = "com.google.android.gms.cast.metadata.SUBTITLE"
            boolean r1 = r4.containsKey(r0)
            if (r1 != 0) goto L3c
            int r1 = r4.getMediaType()
            r2 = 1
            if (r1 == r2) goto L3a
            r2 = 2
            if (r1 == r2) goto L37
            r2 = 3
            java.lang.String r3 = "com.google.android.gms.cast.metadata.ARTIST"
            if (r1 == r2) goto L1d
            r2 = 4
            if (r1 == r2) goto L1b
            goto L3c
        L1b:
            r0 = r3
            goto L3c
        L1d:
            boolean r1 = r4.containsKey(r3)
            if (r1 == 0) goto L24
            goto L1b
        L24:
            java.lang.String r1 = "com.google.android.gms.cast.metadata.ALBUM_ARTIST"
            boolean r2 = r4.containsKey(r1)
            if (r2 == 0) goto L2e
        L2c:
            r0 = r1
            goto L3c
        L2e:
            java.lang.String r1 = "com.google.android.gms.cast.metadata.COMPOSER"
            boolean r2 = r4.containsKey(r1)
            if (r2 == 0) goto L3c
            goto L2c
        L37:
            java.lang.String r0 = "com.google.android.gms.cast.metadata.SERIES_TITLE"
            goto L3c
        L3a:
            java.lang.String r0 = "com.google.android.gms.cast.metadata.STUDIO"
        L3c:
            java.lang.String r4 = r4.getString(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.internal.zzt.zza(com.google.android.gms.cast.MediaMetadata):java.lang.String");
    }

    public static List zzb(com.google.android.gms.cast.framework.media.zzg zzgVar) {
        try {
            return zzgVar.zzf();
        } catch (RemoteException e5) {
            zza.e(e5, "Unable to call %s on %s.", "getNotificationActions", "zzg");
            return null;
        }
    }

    public static int[] zzc(com.google.android.gms.cast.framework.media.zzg zzgVar) {
        try {
            return zzgVar.zzg();
        } catch (RemoteException e5) {
            zza.e(e5, "Unable to call %s on %s.", "getCompactViewActionIndices", "zzg");
            return null;
        }
    }

    public static int zzd(NotificationOptions notificationOptions, long j10) {
        return j10 == 10000 ? notificationOptions.getForward10DrawableResId() : j10 != 30000 ? notificationOptions.getForwardDrawableResId() : notificationOptions.getForward30DrawableResId();
    }

    public static int zze(NotificationOptions notificationOptions, long j10) {
        return j10 == 10000 ? notificationOptions.zzg() : j10 != 30000 ? notificationOptions.zzf() : notificationOptions.zzh();
    }

    public static int zzf(NotificationOptions notificationOptions, long j10) {
        return j10 == 10000 ? notificationOptions.getRewind10DrawableResId() : j10 != 30000 ? notificationOptions.getRewindDrawableResId() : notificationOptions.getRewind30DrawableResId();
    }

    public static int zzg(NotificationOptions notificationOptions, long j10) {
        return j10 == 10000 ? notificationOptions.zzj() : j10 != 30000 ? notificationOptions.zzi() : notificationOptions.zzk();
    }
}
