package com.google.android.gms.cast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.app.d0;
import androidx.core.app.x;
import androidx.loader.content.j;
import androidx.media3.common.C;
import androidx.mediarouter.media.a0;
import androidx.mediarouter.media.i0;
import androidx.mediarouter.media.k0;
import androidx.mediarouter.media.z;
import b4.a;
import c4.d;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.t;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public abstract class CastRemoteDisplayLocalService extends Service {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("CastRDLocalService");
    private static final int zzc = R.id.cast_notification_id;
    private static final Object zzd = new Object();
    private static final AtomicBoolean zze = new AtomicBoolean(false);
    private static CastRemoteDisplayLocalService zzv;
    private String zzf;
    private WeakReference zzg;
    private zzak zzh;
    private NotificationSettings zzi;
    private Notification zzj;
    private boolean zzk;
    private PendingIntent zzl;
    private CastDevice zzm;
    private Display zzn;
    private Context zzo;
    private ServiceConnection zzp;
    private Handler zzq;
    private k0 zzr;
    private CastRemoteDisplayClient zzt;
    private boolean zzs = false;
    private final a0 zzu = new zzab(this);
    private final IBinder zzw = new zzaj(this);

    public interface Callbacks {
        void onRemoteDisplayMuteStateChanged(boolean z);

        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    public static final class NotificationSettings {
        private Notification zza;
        private PendingIntent zzb;
        private String zzc;
        private String zzd;

        public static final class Builder {
            private final NotificationSettings zza = new NotificationSettings(null);

            public NotificationSettings build() {
                NotificationSettings notificationSettings = this.zza;
                if (notificationSettings.zza() != null) {
                    if (!TextUtils.isEmpty(notificationSettings.zze())) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    }
                    if (!TextUtils.isEmpty(notificationSettings.zzg())) {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    }
                    if (notificationSettings.zzc() != null) {
                        throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(notificationSettings.zze()) && TextUtils.isEmpty(notificationSettings.zzg()) && notificationSettings.zzc() == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return notificationSettings;
            }

            public Builder setNotification(Notification notification) {
                this.zza.zzb(notification);
                return this;
            }

            public Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                this.zza.zzd(pendingIntent);
                return this;
            }

            public Builder setNotificationText(String str) {
                this.zza.zzh(str);
                return this;
            }

            public Builder setNotificationTitle(String str) {
                this.zza.zzf(str);
                return this;
            }
        }

        private NotificationSettings() {
            throw null;
        }

        public final /* synthetic */ Notification zza() {
            return this.zza;
        }

        public final /* synthetic */ void zzb(Notification notification) {
            this.zza = notification;
        }

        public final /* synthetic */ PendingIntent zzc() {
            return this.zzb;
        }

        public final /* synthetic */ void zzd(PendingIntent pendingIntent) {
            this.zzb = pendingIntent;
        }

        public final /* synthetic */ String zze() {
            return this.zzc;
        }

        public final /* synthetic */ void zzf(String str) {
            this.zzc = str;
        }

        public final /* synthetic */ String zzg() {
            return this.zzd;
        }

        public final /* synthetic */ void zzh(String str) {
            this.zzd = str;
        }

        public /* synthetic */ NotificationSettings(byte[] bArr) {
        }

        public /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, byte[] bArr) {
            this.zza = notificationSettings.zza;
            this.zzb = notificationSettings.zzb;
            this.zzc = notificationSettings.zzc;
            this.zzd = notificationSettings.zzd;
        }
    }

    public static class Options {
        int zza = 2;

        public int getConfigPreset() {
            return this.zza;
        }

        public void setConfigPreset(int i10) {
            this.zza = i10;
        }
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzd) {
            castRemoteDisplayLocalService = zzv;
        }
        return castRemoteDisplayLocalService;
    }

    public static void setDebugEnabled() {
        zzb.zzb(true);
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Callbacks callbacks) {
        Logger logger = zzb;
        logger.d("Starting Service", new Object[0]);
        synchronized (zzd) {
            try {
                if (zzv != null) {
                    logger.w("An existing service had not been stopped before starting one", new Object[0]);
                    zzv(true);
                }
            } finally {
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo != null && serviceInfo.exported) {
                throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
            }
            t.j(cls, "serviceClass is required.");
            t.j(str, "applicationId is required.");
            t.j(castDevice, "device is required.");
            t.j(options, "options is required.");
            t.j(notificationSettings, "notificationSettings is required.");
            t.j(callbacks, "callbacks is required.");
            if (notificationSettings.zza() == null && notificationSettings.zzc() == null) {
                throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
            }
            if (zze.getAndSet(true)) {
                logger.e("Service is already being started, startService has been called twice", new Object[0]);
                return;
            }
            Intent intent = new Intent(context, cls);
            context.startService(intent);
            a.a().c(context, context.getClass().getName(), intent, new zzad(str, castDevice, options, notificationSettings, context, callbacks), 64, null);
        } catch (PackageManager.NameNotFoundException e5) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?", e5);
        }
    }

    public static void stopService() {
        zzv(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzu, reason: merged with bridge method [inline-methods] */
    public final void zza(boolean z) {
        zzg("Stopping Service");
        t.e("stopServiceInstanceInternal must be called on the main thread");
        if (!z && this.zzr != null) {
            zzg("Setting default route");
            this.zzr.getClass();
            k0.b();
            i0 i0Var = k0.c().f4347w;
            if (i0Var == null) {
                throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
            }
            i0Var.g(true);
        }
        if (this.zzh != null) {
            zzg("Unregistering notification receiver");
            unregisterReceiver(this.zzh);
        }
        zzg("stopRemoteDisplaySession");
        zzg("stopRemoteDisplay");
        this.zzt.stopRemoteDisplay().a(new zzai(this));
        Callbacks callbacks = (Callbacks) this.zzg.get();
        if (callbacks != null) {
            callbacks.onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzg("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzr != null) {
            t.e("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzg("removeMediaRouterCallback");
            this.zzr.e(this.zzu);
        }
        Context context = this.zzo;
        ServiceConnection serviceConnection = this.zzp;
        if (context != null && serviceConnection != null) {
            try {
                a.a().b(context, serviceConnection);
            } catch (IllegalArgumentException unused) {
                zzg("No need to unbind service, already unbound");
            }
        }
        this.zzp = null;
        this.zzo = null;
        this.zzf = null;
        this.zzj = null;
        this.zzn = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzv(boolean z) {
        Logger logger = zzb;
        logger.d("Stopping Service", new Object[0]);
        zze.set(false);
        synchronized (zzd) {
            try {
                CastRemoteDisplayLocalService castRemoteDisplayLocalService = zzv;
                if (castRemoteDisplayLocalService == null) {
                    logger.e("Service is already being stopped", new Object[0]);
                    return;
                }
                zzv = null;
                if (castRemoteDisplayLocalService.zzq != null) {
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        castRemoteDisplayLocalService.zzq.post(new zzae(castRemoteDisplayLocalService, z));
                    } else {
                        castRemoteDisplayLocalService.zza(z);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final Notification zzw(boolean z) {
        int i10;
        int i11;
        zzg("createDefaultNotification");
        String strZze = this.zzi.zze();
        String strZzg = this.zzi.zzg();
        if (z) {
            i10 = R.string.cast_notification_connected_message;
            i11 = R.drawable.cast_ic_notification_on;
        } else {
            i10 = R.string.cast_notification_connecting_message;
            i11 = R.drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(strZze)) {
            strZze = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        }
        if (TextUtils.isEmpty(strZzg)) {
            strZzg = getString(i10, this.zzm.getFriendlyName());
        }
        d0 d0Var = new d0(this, "cast_remote_display_local_service");
        d0Var.f1966e = d0.b(strZze);
        d0Var.f1967f = d0.b(strZzg);
        d0Var.f1968g = this.zzi.zzc();
        d0Var.B.icon = i11;
        d0Var.c(2, true);
        String string = getString(R.string.cast_notification_disconnect);
        if (this.zzl == null) {
            t.j(this.zzo, "activityContext is required.");
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzo.getPackageName());
            this.zzl = PendingIntent.getBroadcast(this, 0, intent, 201326592);
        }
        d0Var.f1963b.add(new x(android.R.drawable.ic_menu_close_clear_cancel, string, this.zzl));
        return d0Var.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzx, reason: merged with bridge method [inline-methods] */
    public final void zzg(String str) {
        zzb.d("[Instance: %s] %s", this, str);
    }

    public Display getCastRemoteDisplay() {
        return this.zzn;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        zzg("onBind");
        return this.zzw;
    }

    @Override // android.app.Service
    public void onCreate() {
        zzg("onCreate");
        super.onCreate();
        j jVar = new j(getMainLooper(), 2);
        this.zzq = jVar;
        jVar.postDelayed(new zzac(this), 100L);
        if (this.zzt == null) {
            this.zzt = CastRemoteDisplay.getClient(this);
        }
        if (d.c()) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            NotificationChannel notificationChannel = new NotificationChannel("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
            notificationChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        zzg("onStartCommand");
        this.zzs = true;
        return 2;
    }

    @Deprecated
    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        if (Build.VERSION.SDK_INT >= 31) {
            return;
        }
        t.j(notificationSettings, "notificationSettings is required.");
        t.j(this.zzq, "Service is not ready yet.");
        this.zzq.post(new zzaf(this, notificationSettings));
    }

    public final /* synthetic */ void zzc(NotificationSettings notificationSettings) {
        t.e("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzi == null) {
            throw new IllegalStateException("No current notification settings to update");
        }
        if (!this.zzk) {
            t.j(notificationSettings.zza(), "notification is required.");
            Notification notificationZza = notificationSettings.zza();
            this.zzj = notificationZza;
            this.zzi.zzb(notificationZza);
        } else {
            if (notificationSettings.zza() != null) {
                throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
            }
            if (notificationSettings.zzc() != null) {
                this.zzi.zzd(notificationSettings.zzc());
            }
            if (!TextUtils.isEmpty(notificationSettings.zze())) {
                this.zzi.zzf(notificationSettings.zze());
            }
            if (!TextUtils.isEmpty(notificationSettings.zzg())) {
                this.zzi.zzh(notificationSettings.zzg());
            }
            this.zzj = zzw(true);
        }
        startForeground(zzc, this.zzj);
    }

    public final /* synthetic */ void zzd(Display display) {
        if (display == null) {
            zzb.e("Cast Remote Display session created without display", new Object[0]);
            return;
        }
        this.zzn = display;
        if (this.zzk) {
            Notification notificationZzw = zzw(true);
            this.zzj = notificationZzw;
            startForeground(zzc, notificationZzw);
        }
        Callbacks callbacks = (Callbacks) this.zzg.get();
        if (callbacks != null) {
            callbacks.onRemoteDisplaySessionStarted(this);
        }
        t.j(this.zzn, "display is required.");
        onCreatePresentation(this.zzn);
    }

    public final boolean zze(String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Context context, ServiceConnection serviceConnection, Callbacks callbacks) {
        zzg("startRemoteDisplaySession");
        t.e("Starting the Cast Remote Display must be done on the main thread");
        synchronized (zzd) {
            try {
                if (zzv != null) {
                    zzb.w("An existing service had not been stopped before starting one", new Object[0]);
                    return false;
                }
                zzv = this;
                this.zzg = new WeakReference(callbacks);
                this.zzf = str;
                this.zzm = castDevice;
                this.zzo = context;
                this.zzp = serviceConnection;
                if (this.zzr == null) {
                    this.zzr = k0.d(getApplicationContext());
                }
                t.j(this.zzf, "applicationId is required.");
                String strCategoryForCast = CastMediaControlIntent.categoryForCast(this.zzf);
                if (strCategoryForCast == null) {
                    throw new IllegalArgumentException("category must not be null");
                }
                ArrayList<String> arrayList = new ArrayList<>();
                if (!arrayList.contains(strCategoryForCast)) {
                    arrayList.add(strCategoryForCast);
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("controlCategories", arrayList);
                z zVar = new z(bundle, arrayList);
                zzg("addMediaRouterCallback");
                this.zzr.a(zVar, this.zzu, 4);
                this.zzj = notificationSettings.zza();
                byte[] bArr = null;
                this.zzh = new zzak(bArr);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
                intentFilter.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
                if (Build.VERSION.SDK_INT >= 33) {
                    registerReceiver(this.zzh, intentFilter, 4);
                } else {
                    qb.d.N(this, this.zzh, intentFilter, 2);
                }
                NotificationSettings notificationSettings2 = new NotificationSettings(notificationSettings, bArr);
                this.zzi = notificationSettings2;
                if (notificationSettings2.zza() == null) {
                    this.zzk = true;
                    this.zzj = zzw(false);
                } else {
                    this.zzk = false;
                    this.zzj = this.zzi.zza();
                }
                startForeground(zzc, this.zzj);
                zzg("startRemoteDisplay");
                Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
                t.j(this.zzo, "activityContext is required.");
                intent.setPackage(this.zzo.getPackageName());
                PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, C.BUFFER_FLAG_NOT_DEPENDED_ON);
                zzag zzagVar = new zzag(this);
                t.j(this.zzf, "applicationId is required.");
                this.zzt.zze(castDevice, this.zzf, options.getConfigPreset(), broadcast, zzagVar).a(new zzah(this));
                Callbacks callbacks2 = (Callbacks) this.zzg.get();
                if (callbacks2 == null) {
                    return true;
                }
                callbacks2.onServiceCreated(this);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzf() {
        Callbacks callbacks = (Callbacks) this.zzg.get();
        if (callbacks != null) {
            callbacks.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED, null, null, null));
        }
        stopService();
    }

    public final /* synthetic */ void zzh(String str) {
        zzb.e("[Instance: %s] %s", this, "The local service has not been been started, stopping it");
    }

    public final /* synthetic */ WeakReference zzl() {
        return this.zzg;
    }

    public final /* synthetic */ CastDevice zzm() {
        return this.zzm;
    }

    public final /* synthetic */ void zzn(Display display) {
        this.zzn = null;
    }

    public final /* synthetic */ Context zzo() {
        return this.zzo;
    }

    public final /* synthetic */ void zzp(Context context) {
        this.zzo = null;
    }

    public final /* synthetic */ ServiceConnection zzq() {
        return this.zzp;
    }

    public final /* synthetic */ void zzr(ServiceConnection serviceConnection) {
        this.zzp = null;
    }

    public final /* synthetic */ boolean zzs() {
        return this.zzs;
    }
}
