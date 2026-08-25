package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.w;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.d0;
import com.google.android.gms.internal.cast.e1;
import com.google.android.gms.internal.cast.h0;
import com.google.android.gms.internal.cast.x0;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.j;
import com.google.android.gms.tasks.s;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: loaded from: classes4.dex */
public class CastSession extends Session {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("CastSession");
    private final Context zzc;
    private final Set zzd;
    private final zzak zze;
    private final CastOptions zzf;
    private final x0 zzg;
    private final com.google.android.gms.cast.framework.media.internal.zzs zzh;
    private com.google.android.gms.cast.zzq zzi;
    private RemoteMediaClient zzj;
    private CastDevice zzk;
    private Cast.ApplicationConnectionResult zzl;
    private zzr zzm;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.google.android.gms.cast.framework.zzak] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    public CastSession(Context context, String str, String str2, CastOptions castOptions, x0 x0Var, com.google.android.gms.cast.framework.media.internal.zzs zzsVar) {
        super(context, str, str2);
        this.zzd = new HashSet();
        this.zzc = context.getApplicationContext();
        this.zzf = castOptions;
        this.zzg = x0Var;
        this.zzh = zzsVar;
        b bVarZzn = zzn();
        ?? B = 0;
        B = 0;
        zzu zzuVar = new zzu(this, B);
        Logger logger = d0.f13226a;
        if (bVarZzn != null) {
            try {
                B = d0.a(context).B(castOptions, bVarZzn, zzuVar);
            } catch (RemoteException | ModuleUnavailableException e5) {
                d0.f13226a.d(e5, "Unable to call %s on %s.", "newCastSessionImpl", h0.class.getSimpleName());
            }
        }
        this.zze = B;
    }

    private final void zzo(Bundle bundle) {
        CastOptions castOptions;
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        this.zzk = fromBundle;
        if (fromBundle == null) {
            if (isResuming()) {
                notifyFailedToResumeSession(2153);
                return;
            } else {
                notifyFailedToStartSession(2151);
                return;
            }
        }
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        byte[] bArr = null;
        if (zzqVar != null) {
            zzqVar.zzd();
            this.zzi = null;
        }
        zzb.d("Acquiring a connection to Google Play Services for %s", this.zzk);
        CastDevice castDevice = this.zzk;
        t.i(castDevice);
        Bundle bundle2 = new Bundle();
        CastOptions castOptions2 = this.zzf;
        CastMediaOptions castMediaOptions = castOptions2 == null ? null : castOptions2.getCastMediaOptions();
        NotificationOptions notificationOptions = castMediaOptions == null ? null : castMediaOptions.getNotificationOptions();
        boolean z = castMediaOptions != null && castMediaOptions.zza();
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", notificationOptions != null);
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", z);
        x0 x0Var = this.zzg;
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_ALWAYS_FOLLOW_SESSION_ENABLED", x0Var.f13677g && x0Var.f13678h && (castOptions = x0Var.f13674d) != null && castOptions.zzf());
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_USE_ROUTE_CONNECTION", x0Var.f13679i);
        Cast.CastOptions.Builder builder = new Cast.CastOptions.Builder(castDevice, new zzv(this, bArr));
        builder.zza(bundle2);
        com.google.android.gms.cast.zzq zzqVarZza = Cast.zza(this.zzc, builder.build());
        zzqVarZza.zzb(new zzy(this, bArr));
        this.zzi = zzqVarZza;
        zzqVarZza.zzc();
    }

    public void addCastListener(Cast.Listener listener) {
        t.e("Must be called from the main thread.");
        if (listener != null) {
            this.zzd.add(listener);
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void end(boolean z) {
        zzak zzakVar = this.zze;
        if (zzakVar != null) {
            try {
                zzakVar.zzj(z, 0);
            } catch (RemoteException e5) {
                zzb.d(e5, "Unable to call %s on %s.", "disconnectFromDevice", "zzak");
            }
            notifySessionEnded(0);
        }
    }

    public int getActiveInputState() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return -1;
        }
        return zzqVar.zzm();
    }

    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        t.e("Must be called from the main thread.");
        return this.zzl;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return null;
        }
        return zzqVar.zzo();
    }

    public String getApplicationStatus() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return null;
        }
        return zzqVar.zzp();
    }

    @Pure
    public CastDevice getCastDevice() {
        t.e("Must be called from the main thread.");
        return this.zzk;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        t.e("Must be called from the main thread.");
        return this.zzj;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public long getSessionRemainingTimeMs() {
        t.e("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzj;
        if (remoteMediaClient == null) {
            return 0L;
        }
        return remoteMediaClient.getStreamDuration() - this.zzj.getApproximateStreamPosition();
    }

    public int getStandbyState() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return -1;
        }
        return zzqVar.zzn();
    }

    public double getVolume() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return 0.0d;
        }
        return zzqVar.zzj();
    }

    public boolean isMute() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        return zzqVar != null && zzqVar.zza() && zzqVar.zzl();
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void onResuming(Bundle bundle) {
        this.zzk = CastDevice.getFromBundle(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void onStarting(Bundle bundle) {
        this.zzk = CastDevice.getFromBundle(bundle);
    }

    public void removeCastListener(Cast.Listener listener) {
        t.e("Must be called from the main thread.");
        if (listener != null) {
            this.zzd.remove(listener);
        }
    }

    public void removeMessageReceivedCallbacks(String str) throws IOException, IllegalArgumentException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar != null) {
            zzqVar.zzr(str);
        }
    }

    public void requestStatus() throws IllegalStateException, IOException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar != null) {
            zzqVar.zze();
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void resume(Bundle bundle) {
        zzo(bundle);
    }

    public v sendMessage(String str, String str2) {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        q qVar = null;
        if (zzqVar == null) {
            Status status = new Status(17, null, null, null);
            w wVar = new w(Looper.getMainLooper());
            wVar.setResult(status);
            return wVar;
        }
        h hVarZzf = zzqVar.zzf(str, str2);
        zzx zzxVar = zzx.zza;
        zzw zzwVar = zzw.zza;
        com.google.android.gms.common.api.d0 d0Var = new com.google.android.gms.common.api.d0(qVar, 1);
        e1 e1Var = new e1(d0Var);
        com.google.android.gms.tasks.t tVar = (com.google.android.gms.tasks.t) hVarZzf;
        tVar.getClass();
        s sVar = j.f13856a;
        tVar.c(sVar, e1Var);
        tVar.b(sVar, new e1(d0Var));
        return d0Var;
    }

    public void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return;
        }
        zzqVar.zzq(str, messageReceivedCallback);
    }

    public void setMute(boolean z) throws IllegalStateException, IOException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return;
        }
        zzqVar.zzk(z);
    }

    public void setVolume(double d4) throws IOException {
        t.e("Must be called from the main thread.");
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar == null || !zzqVar.zza()) {
            return;
        }
        zzqVar.zzi(d4);
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void start(Bundle bundle) {
        zzo(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    public final void zza(Bundle bundle) {
        CastDevice castDevice;
        CastDevice castDevice2;
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        if (fromBundle == null || fromBundle.equals(this.zzk)) {
            return;
        }
        boolean z = !TextUtils.isEmpty(fromBundle.getFriendlyName()) && ((castDevice2 = this.zzk) == null || !TextUtils.equals(castDevice2.getFriendlyName(), fromBundle.getFriendlyName()));
        this.zzk = fromBundle;
        zzb.d("update to device (%s) with name %s", fromBundle, true != z ? "unchanged" : "changed");
        if (!z || (castDevice = this.zzk) == null) {
            return;
        }
        com.google.android.gms.cast.framework.media.internal.zzs zzsVar = this.zzh;
        if (zzsVar != null) {
            zzsVar.zzc(castDevice);
        }
        Iterator it = new HashSet(this.zzd).iterator();
        while (it.hasNext()) {
            ((Cast.Listener) it.next()).onDeviceNameChanged();
        }
        zzr zzrVar = this.zzm;
        if (zzrVar != null) {
            zzrVar.zzd();
        }
    }

    public final void zzb(zzr zzrVar) {
        this.zzm = zzrVar;
    }

    public final com.google.android.gms.cast.framework.media.internal.zzs zzc() {
        return this.zzh;
    }

    public final boolean zzd() {
        CastOptions castOptions;
        x0 x0Var = this.zzg;
        return x0Var.f13677g && x0Var.f13678h && (castOptions = x0Var.f13674d) != null && castOptions.zzf();
    }

    public final void zze(String str, h hVar) {
        zzak zzakVar = this.zze;
        if (zzakVar == null) {
            return;
        }
        try {
            if (hVar.h()) {
                Cast.ApplicationConnectionResult applicationConnectionResult = (Cast.ApplicationConnectionResult) hVar.f();
                this.zzl = applicationConnectionResult;
                if (applicationConnectionResult.getStatus() != null && applicationConnectionResult.getStatus().d()) {
                    zzb.d("%s() -> success result", str);
                    RemoteMediaClient remoteMediaClient = new RemoteMediaClient(new com.google.android.gms.cast.internal.zzar(null));
                    this.zzj = remoteMediaClient;
                    remoteMediaClient.zza(this.zzi);
                    this.zzj.registerCallback(new zzq(this));
                    this.zzj.zzb();
                    this.zzh.zza(this.zzj, getCastDevice());
                    ApplicationMetadata applicationMetadata = applicationConnectionResult.getApplicationMetadata();
                    t.i(applicationMetadata);
                    String applicationStatus = applicationConnectionResult.getApplicationStatus();
                    String sessionId = applicationConnectionResult.getSessionId();
                    t.i(sessionId);
                    zzakVar.zzh(applicationMetadata, applicationStatus, sessionId, applicationConnectionResult.getWasLaunched());
                    return;
                }
                if (applicationConnectionResult.getStatus() != null) {
                    zzb.d("%s() -> failure result", str);
                    zzakVar.zzi(applicationConnectionResult.getStatus().f12675i);
                    return;
                }
            } else {
                Exception excE = hVar.e();
                if (excE instanceof ApiException) {
                    zzakVar.zzi(((ApiException) excE).getStatusCode());
                    return;
                }
            }
            zzakVar.zzi(2476);
        } catch (RemoteException e5) {
            zzb.d(e5, "Unable to call %s on %s.", "methods", "zzak");
        }
    }

    public final /* synthetic */ void zzf(int i10) {
        this.zzh.zzb(i10);
        com.google.android.gms.cast.zzq zzqVar = this.zzi;
        if (zzqVar != null) {
            zzqVar.zzd();
            this.zzi = null;
        }
        this.zzk = null;
        RemoteMediaClient remoteMediaClient = this.zzj;
        if (remoteMediaClient != null) {
            remoteMediaClient.zza(null);
            this.zzj = null;
        }
        this.zzl = null;
    }

    public final /* synthetic */ Set zzh() {
        return this.zzd;
    }

    public final /* synthetic */ zzak zzi() {
        return this.zze;
    }

    public final /* synthetic */ com.google.android.gms.cast.zzq zzj() {
        return this.zzi;
    }

    public final /* synthetic */ RemoteMediaClient zzk() {
        return this.zzj;
    }

    public final /* synthetic */ zzr zzl() {
        return this.zzm;
    }
}
