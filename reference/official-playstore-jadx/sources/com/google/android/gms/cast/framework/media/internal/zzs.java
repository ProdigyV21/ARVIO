package com.google.android.gms.cast.framework.media.internal;

import a0.c;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.g;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.b;
import android.support.v4.media.session.l;
import android.support.v4.media.session.o;
import android.support.v4.media.session.v;
import android.support.v4.media.session.w;
import android.support.v4.media.session.x;
import android.support.v4.media.session.y;
import android.text.TextUtils;
import android.util.Log;
import androidx.loader.content.j;
import androidx.media3.common.C;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.internal.cast.x0;
import java.lang.reflect.Field;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import t.e;

/* JADX INFO: loaded from: classes4.dex */
public final class zzs {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("MediaSessionManager");
    private final Context zzc;
    private final CastOptions zzd;
    private final x0 zze;
    private final SessionManager zzf;
    private final NotificationOptions zzg;
    private final ComponentName zzh;
    private final ComponentName zzi;
    private final zzb zzj;
    private final zzb zzk;
    private final zzm zzl;
    private final Handler zzm;
    private final Runnable zzn;
    private final RemoteMediaClient.Callback zzo;
    private RemoteMediaClient zzp;
    private CastDevice zzq;
    private v zzr;
    private l zzs;
    private boolean zzt;
    private PlaybackStateCompat.CustomAction zzu;
    private PlaybackStateCompat.CustomAction zzv;
    private PlaybackStateCompat.CustomAction zzw;
    private PlaybackStateCompat.CustomAction zzx;

    /* JADX WARN: Multi-variable type inference failed */
    public zzs(Context context, CastOptions castOptions, x0 x0Var) {
        this.zzc = context;
        this.zzd = castOptions;
        this.zze = x0Var;
        CastContext sharedInstance = CastContext.getSharedInstance();
        Object[] objArr = 0;
        this.zzf = sharedInstance != null ? sharedInstance.getSessionManager() : null;
        CastMediaOptions castMediaOptions = castOptions.getCastMediaOptions();
        this.zzg = castMediaOptions == null ? null : castMediaOptions.getNotificationOptions();
        this.zzo = new zzr(this, objArr == true ? 1 : 0);
        String expandedControllerActivityClassName = castMediaOptions == null ? null : castMediaOptions.getExpandedControllerActivityClassName();
        this.zzh = !TextUtils.isEmpty(expandedControllerActivityClassName) ? new ComponentName(context, expandedControllerActivityClassName) : null;
        String mediaIntentReceiverClassName = castMediaOptions == null ? null : castMediaOptions.getMediaIntentReceiverClassName();
        this.zzi = !TextUtils.isEmpty(mediaIntentReceiverClassName) ? new ComponentName(context, mediaIntentReceiverClassName) : null;
        zzb zzbVar = new zzb(context);
        this.zzj = zzbVar;
        zzbVar.zza(new zzn(this));
        zzb zzbVar2 = new zzb(context);
        this.zzk = zzbVar2;
        zzbVar2.zza(new zzo(this));
        this.zzm = new j(Looper.getMainLooper(), 2);
        this.zzl = zzm.zzb(castOptions) ? new zzm(context) : null;
        this.zzn = new Runnable() { // from class: com.google.android.gms.cast.framework.media.internal.zzq
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                this.zza.zzf();
            }
        };
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    private final void zzm(int i10, MediaInfo mediaInfo) {
        PlaybackStateCompat playbackStateCompatA;
        v vVar;
        MediaMetadata metadata;
        PendingIntent activity;
        v vVar2 = this.zzr;
        if (vVar2 == null) {
            return;
        }
        Bundle bundle = new Bundle();
        y yVar = new y();
        RemoteMediaClient remoteMediaClient = this.zzp;
        if (remoteMediaClient == null || this.zzl == null) {
            playbackStateCompatA = yVar.a();
        } else {
            long approximateStreamPosition = (remoteMediaClient.zzk() == 0 || remoteMediaClient.isLiveStream()) ? 0L : remoteMediaClient.getApproximateStreamPosition();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            yVar.f901b = i10;
            yVar.f902c = approximateStreamPosition;
            yVar.f905f = jElapsedRealtime;
            yVar.f903d = 1.0f;
            if (i10 == 0) {
                playbackStateCompatA = yVar.a();
            } else {
                NotificationOptions notificationOptions = this.zzg;
                com.google.android.gms.cast.framework.media.zzg zzgVarZzo = notificationOptions != null ? notificationOptions.zzo() : null;
                RemoteMediaClient remoteMediaClient2 = this.zzp;
                long jZzn = (remoteMediaClient2 == null || remoteMediaClient2.isLiveStream() || this.zzp.isPlayingAd()) ? 0L : 256L;
                if (zzgVarZzo != null) {
                    List<NotificationAction> listZzb = zzt.zzb(zzgVarZzo);
                    if (listZzb != null) {
                        for (NotificationAction notificationAction : listZzb) {
                            String action = notificationAction.getAction();
                            if (zzu(action)) {
                                jZzn |= zzn(action, i10, bundle);
                            } else {
                                zzo(yVar, action, notificationAction);
                            }
                        }
                    }
                } else if (notificationOptions != null) {
                    for (String str : notificationOptions.getActions()) {
                        if (zzu(str)) {
                            jZzn |= zzn(str, i10, bundle);
                        } else {
                            zzo(yVar, str, null);
                        }
                    }
                }
                yVar.f904e = jZzn;
                playbackStateCompatA = yVar.a();
            }
        }
        o oVar = vVar2.f897a;
        oVar.f891f = playbackStateCompatA;
        synchronized (oVar.f889d) {
            for (int iBeginBroadcast = oVar.f890e.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((b) oVar.f890e.getBroadcastItem(iBeginBroadcast)).c0(playbackStateCompatA);
                } catch (RemoteException unused) {
                }
            }
            oVar.f890e.finishBroadcast();
        }
        MediaSession mediaSession = oVar.f886a;
        if (playbackStateCompatA.f869v == null) {
            PlaybackState.Builder builderD = w.d();
            w.x(builderD, playbackStateCompatA.f858i, playbackStateCompatA.f859l, playbackStateCompatA.f861n, playbackStateCompatA.f865r);
            w.u(builderD, playbackStateCompatA.f860m);
            w.s(builderD, playbackStateCompatA.f862o);
            w.v(builderD, playbackStateCompatA.f864q);
            for (PlaybackStateCompat.CustomAction customAction : playbackStateCompatA.f866s) {
                PlaybackState.CustomAction.Builder builderE = w.e(customAction.f870i, customAction.f871l, customAction.f872m);
                w.w(builderE, customAction.f873n);
                w.a(builderD, w.b(builderE));
            }
            w.t(builderD, playbackStateCompatA.f867t);
            x.b(builderD, playbackStateCompatA.f868u);
            playbackStateCompatA.f869v = w.c(builderD);
        }
        mediaSession.setPlaybackState(playbackStateCompatA.f869v);
        NotificationOptions notificationOptions2 = this.zzg;
        if (notificationOptions2 != null && notificationOptions2.zzm()) {
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
        }
        if (notificationOptions2 != null && notificationOptions2.zzn()) {
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        }
        if (bundle.containsKey("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") || bundle.containsKey("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT")) {
            vVar2.f897a.f886a.setExtras(bundle);
        }
        if (i10 == 0) {
            vVar2.d(new MediaMetadataCompat(new Bundle()));
            return;
        }
        if (this.zzp != null) {
            ComponentName componentName = this.zzh;
            if (componentName == null) {
                activity = null;
            } else {
                Intent intent = new Intent();
                intent.setComponent(componentName);
                activity = PendingIntent.getActivity(this.zzc, 0, intent, 201326592);
            }
            if (activity != null) {
                vVar2.f897a.f886a.setSessionActivity(activity);
            }
        }
        if (this.zzp == null || (vVar = this.zzr) == null || mediaInfo == null || (metadata = mediaInfo.getMetadata()) == null) {
            return;
        }
        RemoteMediaClient remoteMediaClient3 = this.zzp;
        long streamDuration = (remoteMediaClient3 == null || !remoteMediaClient3.isLiveStream()) ? mediaInfo.getStreamDuration() : 0L;
        String string = metadata.getString(MediaMetadata.KEY_TITLE);
        String string2 = metadata.getString(MediaMetadata.KEY_SUBTITLE);
        g gVarZzq = zzq();
        gVarZzq.getClass();
        e eVar = MediaMetadataCompat.f838m;
        if (eVar.containsKey(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DURATION) && ((Integer) eVar.get(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DURATION)).intValue() != 0) {
            throw new IllegalArgumentException("The android.media.metadata.DURATION key cannot be used to put a long");
        }
        gVarZzq.f844a.putLong(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DURATION, streamDuration);
        if (string != null) {
            gVarZzq.a(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_TITLE, string);
            gVarZzq.a(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, string);
        }
        if (string2 != null) {
            gVarZzq.a(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, string2);
        }
        vVar.d(new MediaMetadataCompat(gVarZzq.f844a));
        Uri uriZzp = zzp(metadata, 0);
        if (uriZzp != null) {
            this.zzj.zzb(uriZzp);
        } else {
            zze(null, 0);
        }
        Uri uriZzp2 = zzp(metadata, 3);
        if (uriZzp2 != null) {
            this.zzk.zzb(uriZzp2);
        } else {
            zze(null, 3);
        }
    }

    private final long zzn(String str, int i10, Bundle bundle) {
        long j10;
        int iHashCode = str.hashCode();
        if (iHashCode != -945151566) {
            if (iHashCode != -945080078) {
                if (iHashCode == 235550565 && str.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK)) {
                    if (i10 == 3) {
                        j10 = 514;
                        i10 = 3;
                    } else {
                        j10 = 512;
                    }
                    if (i10 != 2) {
                        return j10;
                    }
                    return 516L;
                }
            } else if (str.equals(MediaIntentReceiver.ACTION_SKIP_PREV)) {
                RemoteMediaClient remoteMediaClient = this.zzp;
                if (remoteMediaClient != null && remoteMediaClient.zzl()) {
                    return 16L;
                }
                bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
                return 0L;
            }
        } else if (str.equals(MediaIntentReceiver.ACTION_SKIP_NEXT)) {
            RemoteMediaClient remoteMediaClient2 = this.zzp;
            if (remoteMediaClient2 != null && remoteMediaClient2.zzm()) {
                return 32L;
            }
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        }
        return 0L;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:81:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void zzo(android.support.v4.media.session.y r7, java.lang.String r8, com.google.android.gms.cast.framework.media.NotificationAction r9) {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.internal.zzs.zzo(android.support.v4.media.session.y, java.lang.String, com.google.android.gms.cast.framework.media.NotificationAction):void");
    }

    private final Uri zzp(MediaMetadata mediaMetadata, int i10) {
        CastMediaOptions castMediaOptions = this.zzd.getCastMediaOptions();
        ImagePicker imagePicker = castMediaOptions == null ? null : castMediaOptions.getImagePicker();
        r3.b bVarOnPickImage = imagePicker != null ? imagePicker.onPickImage(mediaMetadata, i10) : mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null;
        if (bVarOnPickImage == null) {
            return null;
        }
        return bVarOnPickImage.f21388l;
    }

    private final g zzq() {
        android.media.MediaMetadata metadata;
        v vVar = this.zzr;
        MediaMetadataCompat mediaMetadataCompat = null;
        if (vVar != null && (metadata = ((android.support.v4.media.session.g) vVar.f898b.f17367l).f877a.getMetadata()) != null) {
            e eVar = MediaMetadataCompat.f838m;
            Parcel parcelObtain = Parcel.obtain();
            metadata.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            MediaMetadataCompat mediaMetadataCompatCreateFromParcel = MediaMetadataCompat.CREATOR.createFromParcel(parcelObtain);
            parcelObtain.recycle();
            mediaMetadataCompatCreateFromParcel.f840l = metadata;
            mediaMetadataCompat = mediaMetadataCompatCreateFromParcel;
        }
        return mediaMetadataCompat == null ? new g() : new g(mediaMetadataCompat);
    }

    private final void zzr() {
        zzm zzmVar = this.zzl;
        if (zzmVar != null) {
            zzb.d("Stopping media notification.", new Object[0]);
            zzmVar.zza();
        }
    }

    @RequiresNonNull({"appContext", "handler", "options"})
    private final void zzs(boolean z) {
        if (this.zzd.getEnableReconnectionService()) {
            Runnable runnable = this.zzn;
            if (runnable != null) {
                this.zzm.removeCallbacks(runnable);
            }
            Context context = this.zzc;
            Intent intent = new Intent(context, (Class<?>) ReconnectionService.class);
            intent.setPackage(context.getPackageName());
            try {
                context.startService(intent);
            } catch (IllegalStateException unused) {
                if (z) {
                    this.zzm.postDelayed(this.zzn, 1000L);
                }
            }
        }
    }

    private final void zzt() {
        if (this.zzd.getEnableReconnectionService()) {
            this.zzm.removeCallbacks(this.zzn);
            Context context = this.zzc;
            Intent intent = new Intent(context, (Class<?>) ReconnectionService.class);
            intent.setPackage(context.getPackageName());
            context.stopService(intent);
        }
    }

    private static final boolean zzu(String str) {
        return TextUtils.equals(str, MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) || TextUtils.equals(str, MediaIntentReceiver.ACTION_SKIP_PREV) || TextUtils.equals(str, MediaIntentReceiver.ACTION_SKIP_NEXT);
    }

    public final void zza(RemoteMediaClient remoteMediaClient, CastDevice castDevice) {
        ComponentName componentName;
        CastOptions castOptions = this.zzd;
        CastMediaOptions castMediaOptions = castOptions == null ? null : castOptions.getCastMediaOptions();
        if (this.zzt || castOptions == null || castMediaOptions == null || this.zzg == null || remoteMediaClient == null || castDevice == null || (componentName = this.zzi) == null) {
            zzb.d("skip attaching media session", new Object[0]);
            return;
        }
        this.zzp = remoteMediaClient;
        remoteMediaClient.registerCallback(this.zzo);
        this.zzq = castDevice;
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(componentName);
        Context context = this.zzc;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, C.BUFFER_FLAG_NOT_DEPENDED_ON);
        if (castMediaOptions.getMediaSessionEnabled()) {
            v vVar = new v(context, componentName, broadcast);
            this.zzr = vVar;
            zzm(0, null);
            CastDevice castDevice2 = this.zzq;
            if (castDevice2 != null && !TextUtils.isEmpty(castDevice2.getFriendlyName())) {
                Bundle bundle = new Bundle();
                String string = context.getResources().getString(R.string.cast_casting_to_device, this.zzq.getFriendlyName());
                e eVar = MediaMetadataCompat.f838m;
                if (eVar.containsKey(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST) && ((Integer) eVar.get(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST)).intValue() != 1) {
                    throw new IllegalArgumentException("The android.media.metadata.ALBUM_ARTIST key cannot be used to put a String");
                }
                bundle.putCharSequence(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, string);
                vVar.d(new MediaMetadataCompat(bundle));
            }
            zzp zzpVar = new zzp(this);
            this.zzs = zzpVar;
            vVar.c(zzpVar, null);
            vVar.b(true);
            this.zze.a(vVar);
        }
        this.zzt = true;
        zzd(false);
    }

    public final void zzb(int i10) {
        if (this.zzt) {
            this.zzt = false;
            RemoteMediaClient remoteMediaClient = this.zzp;
            if (remoteMediaClient != null) {
                remoteMediaClient.unregisterCallback(this.zzo);
            }
            AudioManager audioManager = (AudioManager) this.zzc.getSystemService("audio");
            if (audioManager != null) {
                audioManager.abandonAudioFocus(null);
            }
            this.zze.a(null);
            zzb zzbVar = this.zzj;
            if (zzbVar != null) {
                zzbVar.zzc();
            }
            zzb zzbVar2 = this.zzk;
            if (zzbVar2 != null) {
                zzbVar2.zzc();
            }
            v vVar = this.zzr;
            if (vVar != null) {
                vVar.c(null, null);
                this.zzr.d(new MediaMetadataCompat(new Bundle()));
                zzm(0, null);
            }
            v vVar2 = this.zzr;
            if (vVar2 != null) {
                vVar2.b(false);
                o oVar = this.zzr.f897a;
                MediaSession mediaSession = oVar.f886a;
                oVar.f890e.kill();
                if (Build.VERSION.SDK_INT == 27) {
                    try {
                        Field declaredField = mediaSession.getClass().getDeclaredField("mCallback");
                        declaredField.setAccessible(true);
                        Handler handler = (Handler) declaredField.get(mediaSession);
                        if (handler != null) {
                            handler.removeCallbacksAndMessages(null);
                        }
                    } catch (Exception e5) {
                        Log.w("MediaSessionCompat", "Exception happened while accessing MediaSession.mCallback.", e5);
                    }
                }
                mediaSession.setCallback(null);
                oVar.f887b.f885c.set(null);
                mediaSession.release();
                this.zzr = null;
            }
            this.zzp = null;
            this.zzq = null;
            this.zzs = null;
            zzr();
            if (i10 == 0) {
                zzt();
            }
        }
    }

    public final void zzc(CastDevice castDevice) {
        zzb.i("update Cast device to %s", castDevice);
        this.zzq = castDevice;
        zzd(false);
    }

    public final void zzd(boolean z) {
        MediaQueueItem loadingItem;
        RemoteMediaClient remoteMediaClient = this.zzp;
        if (remoteMediaClient == null) {
            return;
        }
        int iZzk = remoteMediaClient.zzk();
        MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
        if (remoteMediaClient.isLoadingNextItem() && (loadingItem = remoteMediaClient.getLoadingItem()) != null && loadingItem.getMedia() != null) {
            mediaInfo = loadingItem.getMedia();
        }
        zzm(iZzk, mediaInfo);
        if (!remoteMediaClient.hasMediaSession()) {
            zzr();
            zzt();
        } else if (iZzk != 0) {
            zzm zzmVar = this.zzl;
            if (zzmVar != null) {
                zzb.d("Update media notification.", new Object[0]);
                zzmVar.zzc(this.zzq, this.zzp, this.zzr, z);
            }
            if (remoteMediaClient.isLoadingNextItem()) {
                return;
            }
            zzs(true);
        }
    }

    public final void zze(Bitmap bitmap, int i10) {
        v vVar = this.zzr;
        if (vVar == null) {
            return;
        }
        if (bitmap == null || bitmap.getWidth() <= 1 || bitmap.getHeight() <= 1) {
            bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(0);
        }
        g gVarZzq = zzq();
        String str = i10 == 0 ? androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON : androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ART;
        gVarZzq.getClass();
        Bundle bundle = gVarZzq.f844a;
        e eVar = MediaMetadataCompat.f838m;
        if (eVar.containsKey(str) && ((Integer) eVar.get(str)).intValue() != 2) {
            throw new IllegalArgumentException(c.l("The ", str, " key cannot be used to put a Bitmap"));
        }
        bundle.putParcelable(str, bitmap);
        vVar.d(new MediaMetadataCompat(bundle));
    }

    public final /* synthetic */ void zzf() {
        zzs(false);
    }

    public final /* synthetic */ Context zzh() {
        return this.zzc;
    }

    public final /* synthetic */ SessionManager zzi() {
        return this.zzf;
    }

    public final /* synthetic */ NotificationOptions zzj() {
        return this.zzg;
    }

    public final /* synthetic */ ComponentName zzk() {
        return this.zzi;
    }

    public final /* synthetic */ RemoteMediaClient zzl() {
        return this.zzp;
    }
}
