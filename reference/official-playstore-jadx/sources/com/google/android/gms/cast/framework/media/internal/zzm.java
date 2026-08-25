package com.google.android.gms.cast.framework.media.internal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.v;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.d0;
import androidx.core.app.u;
import androidx.core.app.x;
import androidx.media3.common.C;
import c4.d;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m5;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import m2.f0;
import q1.c;

/* JADX INFO: loaded from: classes4.dex */
final class zzm {
    private static final Logger zza = new Logger("MediaNotificationProxy");
    private final Context zzb;
    private final NotificationManager zzc;
    private final CastContext zzd;
    private final NotificationOptions zze;
    private final ImagePicker zzf;
    private final ComponentName zzg;
    private final ComponentName zzh;
    private List zzi = new ArrayList();
    private int[] zzj;
    private final long zzk;
    private final zzb zzl;
    private final ImageHints zzm;
    private final Resources zzn;
    private zzk zzo;
    private zzl zzp;
    private Notification zzq;
    private x zzr;
    private x zzs;
    private x zzt;
    private x zzu;
    private x zzv;
    private x zzw;
    private x zzx;
    private x zzy;

    public zzm(Context context) {
        this.zzb = context;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        this.zzc = notificationManager;
        CastContext sharedInstance = CastContext.getSharedInstance();
        t.i(sharedInstance);
        this.zzd = sharedInstance;
        CastOptions castOptions = sharedInstance.getCastOptions();
        t.i(castOptions);
        CastMediaOptions castMediaOptions = castOptions.getCastMediaOptions();
        t.i(castMediaOptions);
        NotificationOptions notificationOptions = castMediaOptions.getNotificationOptions();
        t.i(notificationOptions);
        this.zze = notificationOptions;
        this.zzf = castMediaOptions.getImagePicker();
        Resources resources = context.getResources();
        this.zzn = resources;
        this.zzg = new ComponentName(context.getApplicationContext(), castMediaOptions.getMediaIntentReceiverClassName());
        if (TextUtils.isEmpty(notificationOptions.getTargetActivityClassName())) {
            this.zzh = null;
        } else {
            this.zzh = new ComponentName(context.getApplicationContext(), notificationOptions.getTargetActivityClassName());
        }
        this.zzk = notificationOptions.getSkipStepMs();
        int dimensionPixelSize = resources.getDimensionPixelSize(notificationOptions.zza());
        ImageHints imageHints = new ImageHints(1, dimensionPixelSize, dimensionPixelSize);
        this.zzm = imageHints;
        this.zzl = new zzb(context.getApplicationContext(), imageHints);
        if (d.c() && notificationManager != null) {
            NotificationChannel notificationChannel = new NotificationChannel("cast_media_notification", context.getResources().getString(R.string.media_notification_channel_name), 2);
            notificationChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        l6.a(m5.CAF_MEDIA_NOTIFICATION_PROXY);
    }

    public static boolean zzb(CastOptions castOptions) {
        NotificationOptions notificationOptions;
        CastMediaOptions castMediaOptions = castOptions.getCastMediaOptions();
        if (castMediaOptions == null || (notificationOptions = castMediaOptions.getNotificationOptions()) == null) {
            return false;
        }
        com.google.android.gms.cast.framework.media.zzg zzgVarZzo = notificationOptions.zzo();
        if (zzgVarZzo == null) {
            return true;
        }
        List listZzb = zzt.zzb(zzgVarZzo);
        int[] iArrZzc = zzt.zzc(zzgVarZzo);
        int size = listZzb == null ? 0 : listZzb.size();
        if (listZzb == null || listZzb.isEmpty()) {
            zza.e("NotificationActionsProvider".concat(" doesn't provide any action."), new Object[0]);
        } else if (listZzb.size() > 5) {
            zza.e("NotificationActionsProvider".concat(" provides more than 5 actions."), new Object[0]);
        } else {
            if (iArrZzc != null && (iArrZzc.length) != 0) {
                for (int i10 : iArrZzc) {
                    if (i10 < 0 || i10 >= size) {
                        zza.e("NotificationActionsProvider".concat("provides a compact view action whose index is out of bounds."), new Object[0]);
                    }
                }
                return true;
            }
            zza.e("NotificationActionsProvider".concat(" doesn't provide any actions for compact view."), new Object[0]);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final void zzd() {
        Bitmap bitmap;
        PendingIntent activities;
        x xVarZzg;
        NotificationManager notificationManager = this.zzc;
        if (notificationManager == null || this.zzo == null) {
            return;
        }
        zzl zzlVar = this.zzp;
        if (zzlVar == null || (bitmap = zzlVar.zzb) == null || bitmap.getWidth() <= 1 || bitmap.getHeight() <= 1) {
            bitmap = null;
        }
        Context context = this.zzb;
        d0 d0Var = new d0(context, "cast_media_notification");
        d0Var.d(bitmap);
        NotificationOptions notificationOptions = this.zze;
        d0Var.B.icon = notificationOptions.getSmallIconDrawableResId();
        d0Var.f1966e = d0.b(this.zzo.zzd);
        d0Var.f1967f = d0.b(this.zzn.getString(notificationOptions.getCastingToDeviceStringResId(), this.zzo.zze));
        d0Var.c(2, true);
        d0Var.f1971j = false;
        d0Var.f1983w = 1;
        ComponentName componentName = this.zzh;
        if (componentName == null) {
            activities = null;
        } else {
            Intent intent = new Intent();
            intent.putExtra("targetActivity", componentName);
            intent.setAction(componentName.flattenToString());
            intent.setComponent(componentName);
            ArrayList arrayList = new ArrayList();
            ComponentName component = intent.getComponent();
            if (component == null) {
                component = intent.resolveActivity(context.getPackageManager());
            }
            if (component != null) {
                int size = arrayList.size();
                try {
                    for (Intent intentU = f0.u(context, component); intentU != null; intentU = f0.u(context, intentU.getComponent())) {
                        arrayList.add(size, intentU);
                    }
                } catch (PackageManager.NameNotFoundException e5) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e5);
                }
            }
            arrayList.add(intent);
            if (arrayList.isEmpty()) {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
            }
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            activities = PendingIntent.getActivities(context, 1, intentArr, 201326592, null);
        }
        if (activities != null) {
            d0Var.f1968g = activities;
        }
        com.google.android.gms.cast.framework.media.zzg zzgVarZzo = notificationOptions.zzo();
        if (zzgVarZzo != null) {
            zza.d("actionsProvider != null", new Object[0]);
            int[] iArrZzc = zzt.zzc(zzgVarZzo);
            this.zzj = iArrZzc == null ? null : (int[]) iArrZzc.clone();
            List<NotificationAction> listZzb = zzt.zzb(zzgVarZzo);
            this.zzi = new ArrayList();
            if (listZzb != null) {
                for (NotificationAction notificationAction : listZzb) {
                    String action = notificationAction.getAction();
                    if (action.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) || action.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) || action.equals(MediaIntentReceiver.ACTION_SKIP_PREV) || action.equals(MediaIntentReceiver.ACTION_FORWARD) || action.equals(MediaIntentReceiver.ACTION_REWIND) || action.equals(MediaIntentReceiver.ACTION_STOP_CASTING) || action.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                        xVarZzg = zzg(notificationAction.getAction());
                    } else {
                        Intent intent2 = new Intent(notificationAction.getAction());
                        intent2.setComponent(this.zzg);
                        xVarZzg = new u(notificationAction.getIconResId(), PendingIntent.getBroadcast(context, 0, intent2, C.BUFFER_FLAG_NOT_DEPENDED_ON), notificationAction.getContentDescription()).a();
                    }
                    if (xVarZzg != null) {
                        this.zzi.add(xVarZzg);
                    }
                }
            }
        } else {
            zza.d("actionsProvider == null", new Object[0]);
            this.zzi = new ArrayList();
            Iterator<String> it = notificationOptions.getActions().iterator();
            while (it.hasNext()) {
                x xVarZzg2 = zzg(it.next());
                if (xVarZzg2 != null) {
                    this.zzi.add(xVarZzg2);
                }
            }
            this.zzj = (int[]) notificationOptions.getCompatActionIndices().clone();
        }
        for (x xVar : this.zzi) {
            if (xVar != null) {
                d0Var.f1963b.add(xVar);
            }
        }
        c cVar = new c();
        cVar.f21323a = null;
        int[] iArr = this.zzj;
        if (iArr != null) {
            cVar.f21323a = iArr;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = this.zzo.zza;
        if (mediaSessionCompat$Token != null) {
            cVar.f21324b = mediaSessionCompat$Token;
        }
        d0Var.e(cVar);
        Notification notificationA = d0Var.a();
        this.zzq = notificationA;
        notificationManager.notify("castMediaNotification", 1, notificationA);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final x zzg(String str) {
        int pauseDrawableResId;
        int iZzb;
        PendingIntent broadcast = null;
        switch (str.hashCode()) {
            case -1699820260:
                if (str.equals(MediaIntentReceiver.ACTION_REWIND)) {
                    long j10 = this.zzk;
                    if (this.zzw == null) {
                        Intent intent = new Intent(MediaIntentReceiver.ACTION_REWIND);
                        intent.setComponent(this.zzg);
                        intent.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, j10);
                        PendingIntent broadcast2 = PendingIntent.getBroadcast(this.zzb, 0, intent, 201326592);
                        NotificationOptions notificationOptions = this.zze;
                        this.zzw = new u(zzt.zzf(notificationOptions, j10), broadcast2, this.zzn.getString(zzt.zzg(notificationOptions, j10))).a();
                    }
                    return this.zzw;
                }
                break;
            case -945151566:
                if (str.equals(MediaIntentReceiver.ACTION_SKIP_NEXT)) {
                    boolean z = this.zzo.zzf;
                    if (this.zzt == null) {
                        if (z) {
                            Intent intent2 = new Intent(MediaIntentReceiver.ACTION_SKIP_NEXT);
                            intent2.setComponent(this.zzg);
                            broadcast = PendingIntent.getBroadcast(this.zzb, 0, intent2, C.BUFFER_FLAG_NOT_DEPENDED_ON);
                        }
                        NotificationOptions notificationOptions2 = this.zze;
                        this.zzt = new u(notificationOptions2.getSkipNextDrawableResId(), broadcast, this.zzn.getString(notificationOptions2.zzd())).a();
                    }
                    return this.zzt;
                }
                break;
            case -945080078:
                if (str.equals(MediaIntentReceiver.ACTION_SKIP_PREV)) {
                    boolean z5 = this.zzo.zzg;
                    if (this.zzu == null) {
                        if (z5) {
                            Intent intent3 = new Intent(MediaIntentReceiver.ACTION_SKIP_PREV);
                            intent3.setComponent(this.zzg);
                            broadcast = PendingIntent.getBroadcast(this.zzb, 0, intent3, C.BUFFER_FLAG_NOT_DEPENDED_ON);
                        }
                        NotificationOptions notificationOptions3 = this.zze;
                        this.zzu = new u(notificationOptions3.getSkipPrevDrawableResId(), broadcast, this.zzn.getString(notificationOptions3.zze())).a();
                    }
                    return this.zzu;
                }
                break;
            case -668151673:
                if (str.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                    if (this.zzy == null) {
                        Intent intent4 = new Intent(MediaIntentReceiver.ACTION_STOP_CASTING);
                        intent4.setComponent(this.zzg);
                        PendingIntent broadcast3 = PendingIntent.getBroadcast(this.zzb, 0, intent4, C.BUFFER_FLAG_NOT_DEPENDED_ON);
                        NotificationOptions notificationOptions4 = this.zze;
                        this.zzy = new u(notificationOptions4.getDisconnectDrawableResId(), broadcast3, this.zzn.getString(notificationOptions4.zzl())).a();
                    }
                    return this.zzy;
                }
                break;
            case -124479363:
                if (str.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                    if (this.zzx == null) {
                        Intent intent5 = new Intent(MediaIntentReceiver.ACTION_DISCONNECT);
                        intent5.setComponent(this.zzg);
                        PendingIntent broadcast4 = PendingIntent.getBroadcast(this.zzb, 0, intent5, C.BUFFER_FLAG_NOT_DEPENDED_ON);
                        NotificationOptions notificationOptions5 = this.zze;
                        this.zzx = new u(notificationOptions5.getDisconnectDrawableResId(), broadcast4, this.zzn.getString(notificationOptions5.zzl(), "")).a();
                    }
                    return this.zzx;
                }
                break;
            case 235550565:
                if (str.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK)) {
                    zzk zzkVar = this.zzo;
                    int i10 = zzkVar.zzc;
                    if (!zzkVar.zzb) {
                        if (this.zzr == null) {
                            Intent intent6 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
                            intent6.setComponent(this.zzg);
                            PendingIntent broadcast5 = PendingIntent.getBroadcast(this.zzb, 0, intent6, C.BUFFER_FLAG_NOT_DEPENDED_ON);
                            NotificationOptions notificationOptions6 = this.zze;
                            this.zzr = new u(notificationOptions6.getPlayDrawableResId(), broadcast5, this.zzn.getString(notificationOptions6.zzc())).a();
                        }
                        return this.zzr;
                    }
                    if (this.zzs == null) {
                        if (i10 == 2) {
                            NotificationOptions notificationOptions7 = this.zze;
                            pauseDrawableResId = notificationOptions7.getStopLiveStreamDrawableResId();
                            iZzb = notificationOptions7.getStopLiveStreamTitleResId();
                        } else {
                            NotificationOptions notificationOptions8 = this.zze;
                            pauseDrawableResId = notificationOptions8.getPauseDrawableResId();
                            iZzb = notificationOptions8.zzb();
                        }
                        Intent intent7 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
                        intent7.setComponent(this.zzg);
                        this.zzs = new u(pauseDrawableResId, PendingIntent.getBroadcast(this.zzb, 0, intent7, C.BUFFER_FLAG_NOT_DEPENDED_ON), this.zzn.getString(iZzb)).a();
                    }
                    return this.zzs;
                }
                break;
            case 1362116196:
                if (str.equals(MediaIntentReceiver.ACTION_FORWARD)) {
                    long j11 = this.zzk;
                    if (this.zzv == null) {
                        Intent intent8 = new Intent(MediaIntentReceiver.ACTION_FORWARD);
                        intent8.setComponent(this.zzg);
                        intent8.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, j11);
                        PendingIntent broadcast6 = PendingIntent.getBroadcast(this.zzb, 0, intent8, 201326592);
                        NotificationOptions notificationOptions9 = this.zze;
                        this.zzv = new u(zzt.zzd(notificationOptions9, j11), broadcast6, this.zzn.getString(zzt.zze(notificationOptions9, j11))).a();
                    }
                    return this.zzv;
                }
                break;
        }
        zza.e("Action: %s is not a pre-defined action.", str);
        return null;
    }

    public final void zza() {
        this.zzl.zzc();
        NotificationManager notificationManager = this.zzc;
        if (notificationManager != null) {
            notificationManager.cancel("castMediaNotification", 1);
        }
    }

    public final void zzc(CastDevice castDevice, RemoteMediaClient remoteMediaClient, v vVar, boolean z) {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        boolean z5;
        boolean z10;
        zzk zzkVar;
        if (castDevice == null || remoteMediaClient == null || vVar == null || (mediaInfo = remoteMediaClient.getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null) {
            return;
        }
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        if (mediaStatus == null) {
            z5 = false;
            z10 = z5;
        } else {
            int queueRepeatMode = mediaStatus.getQueueRepeatMode();
            if (queueRepeatMode == 1 || queueRepeatMode == 2 || queueRepeatMode == 3) {
                z5 = true;
                z10 = z5;
            } else {
                Integer indexById = mediaStatus.getIndexById(mediaStatus.getCurrentItemId());
                if (indexById != null) {
                    boolean z11 = indexById.intValue() > 0;
                    z5 = indexById.intValue() < mediaStatus.getQueueItemCount() + (-1);
                    z10 = z11;
                }
                z5 = false;
                z10 = z5;
            }
        }
        zzk zzkVar2 = new zzk(remoteMediaClient.getPlayerState() == 2, mediaInfo.getStreamType(), metadata.getString(MediaMetadata.KEY_TITLE), castDevice.getFriendlyName(), vVar.f897a.f888c, z5, z10);
        if (z || (zzkVar = this.zzo) == null || zzkVar2.zzb != zzkVar.zzb || zzkVar2.zzc != zzkVar.zzc || !CastUtils.zza(zzkVar2.zzd, zzkVar.zzd) || !CastUtils.zza(zzkVar2.zze, zzkVar.zze) || zzkVar2.zzf != zzkVar.zzf || zzkVar2.zzg != zzkVar.zzg) {
            this.zzo = zzkVar2;
            zzd();
        }
        ImagePicker imagePicker = this.zzf;
        zzl zzlVar = new zzl(imagePicker != null ? imagePicker.onPickImage(metadata, this.zzm) : metadata.hasImages() ? metadata.getImages().get(0) : null);
        zzl zzlVar2 = this.zzp;
        if (zzlVar2 == null || !CastUtils.zza(zzlVar.zza, zzlVar2.zza)) {
            zzb zzbVar = this.zzl;
            zzbVar.zza(new zzj(this, zzlVar));
            zzbVar.zzb(zzlVar.zza);
        }
    }

    public final /* synthetic */ void zze(zzl zzlVar) {
        this.zzp = zzlVar;
    }
}
