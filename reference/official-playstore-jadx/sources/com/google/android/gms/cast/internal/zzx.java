package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbn;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.e;
import com.google.android.gms.common.api.o;
import com.google.android.gms.common.api.p;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.u0;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import o3.b;

/* JADX INFO: loaded from: classes4.dex */
public final class zzx extends k {
    public static final /* synthetic */ int zze = 0;
    private final Map zzA;
    private e zzB;
    private e zzC;
    private ApplicationMetadata zzg;
    private final CastDevice zzh;
    private final Cast.Listener zzi;
    private final Map zzj;
    private final long zzk;
    private final Bundle zzl;
    private zzw zzm;
    private String zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private double zzs;
    private com.google.android.gms.cast.zzao zzt;
    private int zzu;
    private int zzv;
    private final AtomicLong zzw;
    private String zzx;
    private String zzy;
    private Bundle zzz;
    private static final Logger zzf = new Logger("CastClientImpl");
    private static final Object zzD = new Object();
    private static final Object zzE = new Object();

    public zzx(Context context, Looper looper, h hVar, CastDevice castDevice, long j10, Cast.Listener listener, Bundle bundle, o oVar, p pVar) {
        super(context, looper, 10, hVar, oVar, pVar);
        this.zzh = castDevice;
        this.zzi = listener;
        this.zzk = j10;
        this.zzl = bundle;
        this.zzj = new HashMap();
        this.zzw = new AtomicLong(0L);
        this.zzA = new HashMap();
        zzI();
        zzG();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzY, reason: merged with bridge method [inline-methods] */
    public final void zzI() {
        this.zzr = false;
        this.zzu = -1;
        this.zzv = -1;
        this.zzg = null;
        this.zzn = null;
        this.zzs = 0.0d;
        zzG();
        this.zzo = false;
        this.zzt = null;
    }

    private final void zzZ(e eVar) {
        synchronized (zzD) {
            try {
                e eVar2 = this.zzB;
                if (eVar2 != null) {
                    eVar2.setResult(new zzr(new Status(2477, null, null, null), null, null, null, false));
                }
                this.zzB = eVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void zzaa(e eVar) {
        synchronized (zzE) {
            try {
                if (this.zzC != null) {
                    eVar.setResult(new Status(2001, null, null, null));
                } else {
                    this.zzC = eVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void zzab() {
        zzf.d("removing all MessageReceivedCallbacks", new Object[0]);
        Map map = this.zzj;
        synchronized (map) {
            map.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzac, reason: merged with bridge method [inline-methods] */
    public final void zzL(long j10, int i10) {
        e eVar;
        Map map = this.zzA;
        synchronized (map) {
            eVar = (e) map.remove(Long.valueOf(j10));
        }
        if (eVar != null) {
            eVar.setResult(new Status(i10, null, null, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzad, reason: merged with bridge method [inline-methods] */
    public final void zzM(int i10) {
        synchronized (zzE) {
            try {
                e eVar = this.zzC;
                if (eVar != null) {
                    eVar.setResult(new Status(i10, null, null, null));
                    this.zzC = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.f
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        return iInterfaceQueryLocalInterface instanceof zzah ? (zzah) iInterfaceQueryLocalInterface : new zzah(iBinder);
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final void disconnect() {
        Logger logger = zzf;
        logger.d("disconnect(); ServiceListener=%s, isConnected=%b", this.zzm, Boolean.valueOf(isConnected()));
        zzw zzwVar = this.zzm;
        this.zzm = null;
        if (zzwVar == null || zzwVar.zzq() == null) {
            logger.d("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzab();
        try {
            try {
                zzah zzahVar = (zzah) getService();
                getContext();
                zzahVar.zze(u0.i());
            } finally {
                super.disconnect();
            }
        } catch (RemoteException | IllegalStateException e5) {
            zzf.d(e5, "Error while disconnecting the controller interface", new Object[0]);
        }
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getConnectionHint() {
        Bundle bundle = this.zzz;
        if (bundle == null) {
            return super.getConnectionHint();
        }
        this.zzz = null;
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.f
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zzf.d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzx, this.zzy);
        this.zzh.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzk);
        Bundle bundle2 = this.zzl;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        zzw zzwVar = new zzw(this);
        this.zzm = zzwVar;
        bundle.putParcelable("listener", new BinderWrapper(zzwVar));
        String str = this.zzx;
        if (str != null) {
            bundle.putString("last_application_id", str);
            String str2 = this.zzy;
            if (str2 != null) {
                bundle.putString("last_session_id", str2);
            }
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.f, com.google.android.gms.common.api.g
    public final int getMinApkVersion() {
        return 12800000;
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    @Override // com.google.android.gms.common.internal.f
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.f
    public final void onConnectionFailed(b bVar) {
        super.onConnectionFailed(bVar);
        zzab();
    }

    @Override // com.google.android.gms.common.internal.f
    public final void onPostInitHandler(int i10, IBinder iBinder, Bundle bundle, int i11) {
        zzf.d("in onPostInitHandler; statusCode=%d", Integer.valueOf(i10));
        if (i10 == 0 || i10 == 2300) {
            this.zzr = true;
            this.zzp = true;
            this.zzq = true;
        } else {
            this.zzr = false;
        }
        if (i10 == 2300) {
            Bundle bundle2 = new Bundle();
            this.zzz = bundle2;
            bundle2.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i10 = 0;
        }
        super.onPostInitHandler(i10, iBinder, bundle, i11);
    }

    public final int zzA() throws IllegalStateException {
        checkConnected();
        return this.zzv;
    }

    public final void zzB(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, RemoteException, IllegalArgumentException {
        CastUtils.throwIfInvalidNamespace(str);
        zzC(str);
        if (messageReceivedCallback != null) {
            Map map = this.zzj;
            synchronized (map) {
                map.put(str, messageReceivedCallback);
            }
            zzah zzahVar = (zzah) getService();
            if (zzF()) {
                getContext();
                zzahVar.zzl(str, u0.i());
            }
        }
    }

    public final void zzC(String str) throws RemoteException, IllegalArgumentException {
        Cast.MessageReceivedCallback messageReceivedCallback;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        Map map = this.zzj;
        synchronized (map) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) map.remove(str);
        }
        if (messageReceivedCallback != null) {
            try {
                zzah zzahVar = (zzah) getService();
                getContext();
                zzahVar.zzm(str, u0.i());
            } catch (IllegalStateException e5) {
                zzf.d(e5, "Error unregistering namespace (%s)", str);
            }
        }
    }

    public final ApplicationMetadata zzD() throws IllegalStateException {
        checkConnected();
        return this.zzg;
    }

    public final String zzE() throws IllegalStateException {
        checkConnected();
        return this.zzn;
    }

    public final boolean zzF() {
        zzw zzwVar;
        return (!this.zzr || (zzwVar = this.zzm) == null || zzwVar.zzr()) ? false : true;
    }

    public final double zzG() {
        CastDevice castDevice = this.zzh;
        t.j(castDevice, "device should not be null");
        if (castDevice.hasCapability(2048)) {
            return 0.02d;
        }
        return (!castDevice.hasCapability(4) || castDevice.hasCapability(1) || "Chromecast Audio".equals(castDevice.getModelName())) ? 0.05d : 0.02d;
    }

    public final void zzH(int i10) {
        synchronized (zzD) {
            try {
                e eVar = this.zzB;
                if (eVar != null) {
                    eVar.setResult(new zzr(new Status(i10, null, null, null), null, null, null, false));
                    this.zzB = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void zzJ(zzac zzacVar) {
        boolean z;
        boolean z5;
        boolean z10;
        ApplicationMetadata applicationMetadataZze = zzacVar.zze();
        if (!CastUtils.zza(applicationMetadataZze, this.zzg)) {
            this.zzg = applicationMetadataZze;
            this.zzi.onApplicationMetadataChanged(applicationMetadataZze);
        }
        double dZza = zzacVar.zza();
        if (Double.isNaN(dZza) || Math.abs(dZza - this.zzs) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzs = dZza;
            z = true;
        }
        boolean zZzb = zzacVar.zzb();
        if (zZzb != this.zzo) {
            this.zzo = zZzb;
            z = true;
        }
        Double.isNaN(zzacVar.zzg());
        Logger logger = zzf;
        logger.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzq));
        Cast.Listener listener = this.zzi;
        if (listener != null && (z || this.zzq)) {
            listener.onVolumeChanged();
        }
        int iZzc = zzacVar.zzc();
        if (iZzc != this.zzu) {
            this.zzu = iZzc;
            z5 = true;
        } else {
            z5 = false;
        }
        logger.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z5), Boolean.valueOf(this.zzq));
        if (listener != null && (z5 || this.zzq)) {
            listener.onActiveInputStateChanged(this.zzu);
        }
        int iZzd = zzacVar.zzd();
        if (iZzd != this.zzv) {
            this.zzv = iZzd;
            z10 = true;
        } else {
            z10 = false;
        }
        logger.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z10), Boolean.valueOf(this.zzq));
        if (listener != null && (z10 || this.zzq)) {
            listener.onStandbyStateChanged(this.zzv);
        }
        if (!CastUtils.zza(this.zzt, zzacVar.zzf())) {
            this.zzt = zzacVar.zzf();
        }
        this.zzq = false;
    }

    public final /* synthetic */ void zzK(zza zzaVar) {
        boolean z;
        String strZza = zzaVar.zza();
        if (CastUtils.zza(strZza, this.zzn)) {
            z = false;
        } else {
            this.zzn = strZza;
            z = true;
        }
        zzf.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzp));
        Cast.Listener listener = this.zzi;
        if (listener != null && (z || this.zzp)) {
            listener.onApplicationStatusChanged();
        }
        this.zzp = false;
    }

    public final /* synthetic */ void zzO(ApplicationMetadata applicationMetadata) {
        this.zzg = applicationMetadata;
    }

    public final /* synthetic */ CastDevice zzP() {
        return this.zzh;
    }

    public final /* synthetic */ Cast.Listener zzQ() {
        return this.zzi;
    }

    public final /* synthetic */ Map zzR() {
        return this.zzj;
    }

    public final /* synthetic */ void zzS(String str) {
        this.zzn = str;
    }

    public final /* synthetic */ void zzT(String str) {
        this.zzx = str;
    }

    public final /* synthetic */ void zzU(String str) {
        this.zzy = str;
    }

    public final /* synthetic */ e zzV() {
        return this.zzB;
    }

    public final /* synthetic */ void zzW(e eVar) {
        this.zzB = null;
    }

    public final void zzp(String str, String str2, e eVar) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() > 524288) {
            zzf.w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        CastUtils.throwIfInvalidNamespace(str);
        long jIncrementAndGet = this.zzw.incrementAndGet();
        try {
            this.zzA.put(Long.valueOf(jIncrementAndGet), eVar);
            zzah zzahVar = (zzah) getService();
            if (!zzF()) {
                zzL(jIncrementAndGet, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
            } else {
                getContext();
                zzahVar.zzk(str, str2, jIncrementAndGet, u0.i());
            }
        } catch (Throwable th) {
            this.zzA.remove(Long.valueOf(jIncrementAndGet));
            throw th;
        }
    }

    public final void zzq(String str, LaunchOptions launchOptions, e eVar) throws IllegalStateException, RemoteException {
        zzZ(eVar);
        zzah zzahVar = (zzah) getService();
        if (!zzF()) {
            zzH(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        } else {
            getContext();
            zzahVar.zzn(str, launchOptions, u0.i());
        }
    }

    public final void zzr(String str, String str2, zzbn zzbnVar, e eVar) throws IllegalStateException, RemoteException {
        zzZ(eVar);
        zzbn zzbnVar2 = new zzbn();
        zzah zzahVar = (zzah) getService();
        if (!zzF()) {
            zzH(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        } else {
            getContext();
            zzahVar.zzo(str, str2, zzbnVar2, u0.i());
        }
    }

    public final void zzs(e eVar) throws IllegalStateException, RemoteException {
        zzaa(eVar);
        zzah zzahVar = (zzah) getService();
        if (!zzF()) {
            zzM(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        } else {
            getContext();
            zzahVar.zzf(u0.i());
        }
    }

    public final void zzt(String str, e eVar) throws IllegalStateException, RemoteException {
        zzaa(eVar);
        zzah zzahVar = (zzah) getService();
        if (!zzF()) {
            zzM(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        } else {
            getContext();
            zzahVar.zzg(str, u0.i());
        }
    }

    public final void zzu() throws IllegalStateException, RemoteException {
        zzah zzahVar = (zzah) getService();
        if (zzF()) {
            getContext();
            zzahVar.zzh(u0.i());
        }
    }

    public final void zzv(double d4) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (Double.isInfinite(d4) || Double.isNaN(d4)) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(d4).length() + 17);
            sb2.append("Volume cannot be ");
            sb2.append(d4);
            throw new IllegalArgumentException(sb2.toString());
        }
        zzah zzahVar = (zzah) getService();
        if (zzF()) {
            double d10 = this.zzs;
            boolean z = this.zzo;
            getContext();
            zzahVar.zzi(d4, d10, z, u0.i());
        }
    }

    public final void zzw(boolean z) throws IllegalStateException, RemoteException {
        zzah zzahVar = (zzah) getService();
        if (zzF()) {
            double d4 = this.zzs;
            boolean z5 = this.zzo;
            getContext();
            zzahVar.zzj(z, d4, z5, u0.i());
        }
    }

    public final double zzx() throws IllegalStateException {
        checkConnected();
        return this.zzs;
    }

    public final boolean zzy() throws IllegalStateException {
        checkConnected();
        return this.zzo;
    }

    public final int zzz() throws IllegalStateException {
        checkConnected();
        return this.zzu;
    }
}
