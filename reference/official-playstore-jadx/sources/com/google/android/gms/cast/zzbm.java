package com.google.android.gms.cast;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.l;
import com.google.android.gms.common.api.internal.r;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.api.j;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.u0;
import com.google.android.gms.tasks.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import o3.d;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbm extends n implements zzq {
    public static final /* synthetic */ int zzf = 0;
    private static final Logger zzg = new Logger("CastClient");
    private static final a zzh;
    private static final i zzi;
    final zzbl zza;
    com.google.android.gms.tasks.i zzb;
    com.google.android.gms.tasks.i zzc;
    final Map zzd;
    final Map zze;
    private Handler zzj;
    private boolean zzk;
    private boolean zzl;
    private final AtomicLong zzm;
    private final Object zzn;
    private final Object zzo;
    private ApplicationMetadata zzp;
    private String zzq;
    private double zzr;
    private boolean zzs;
    private int zzt;
    private int zzu;
    private zzao zzv;
    private final CastDevice zzw;
    private final Cast.Listener zzx;
    private final List zzy;
    private int zzz;

    static {
        zzar zzarVar = new zzar();
        zzh = zzarVar;
        zzi = new i("Cast.API_CXLESS", zzarVar, com.google.android.gms.cast.internal.zzal.zzb);
    }

    public zzbm(Context context, Cast.CastOptions castOptions) {
        super(context, null, zzi, castOptions, m.f12824c);
        this.zza = new zzbl(this);
        this.zzn = new Object();
        this.zzo = new Object();
        this.zzy = Collections.synchronizedList(new ArrayList());
        t.j(castOptions, "CastOptions cannot be null");
        this.zzx = castOptions.zzb;
        this.zzw = castOptions.zza;
        this.zzd = new HashMap();
        this.zze = new HashMap();
        this.zzm = new AtomicLong(0L);
        this.zzz = 1;
        zzt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzV, reason: merged with bridge method [inline-methods] */
    public final h zzC(com.google.android.gms.cast.internal.zzaj zzajVar) {
        l lVar = registerListener(zzajVar, "castDeviceControllerListenerKey").f12769c;
        t.j(lVar, "Key must not be null");
        return doUnregisterEventListener(lVar, 8415);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzW, reason: merged with bridge method [inline-methods] */
    public final void zzD() {
        zzg.d("removing all MessageReceivedCallbacks", new Object[0]);
        Map map = this.zze;
        synchronized (map) {
            map.clear();
        }
    }

    private final void zzX() {
        t.k("Not active connection", this.zzz != 1);
    }

    private final void zzY() {
        t.k("Not connected to device", zza());
    }

    private final void zzZ(com.google.android.gms.tasks.i iVar) {
        synchronized (this.zzn) {
            try {
                if (this.zzb != null) {
                    zzH(2477);
                }
                this.zzb = iVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzaa, reason: merged with bridge method [inline-methods] */
    public final void zzH(int i10) {
        synchronized (this.zzn) {
            try {
                com.google.android.gms.tasks.i iVar = this.zzb;
                if (iVar != null) {
                    iVar.a(zzab(i10));
                }
                this.zzb = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static ApiException zzab(int i10) {
        return t.m(new Status(i10, null, null, null));
    }

    public final /* synthetic */ void zzA(String str, Cast.MessageReceivedCallback messageReceivedCallback, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        zzX();
        zzyVar.getContext();
        j jVarI = u0.i();
        ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzm(str, jVarI);
        if (messageReceivedCallback != null) {
            ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzl(str, jVarI);
        }
        iVar.b(null);
    }

    public final /* synthetic */ void zzB(Cast.MessageReceivedCallback messageReceivedCallback, String str, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        zzX();
        if (messageReceivedCallback != null) {
            com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
            zzyVar.getContext();
            zzahVar.zzm(str, u0.i());
        }
        iVar.b(null);
    }

    public final /* synthetic */ void zzE(com.google.android.gms.cast.internal.zzac zzacVar) {
        boolean z;
        boolean z5;
        boolean z10;
        ApplicationMetadata applicationMetadataZze = zzacVar.zze();
        if (!CastUtils.zza(applicationMetadataZze, this.zzp)) {
            this.zzp = applicationMetadataZze;
            this.zzx.onApplicationMetadataChanged(applicationMetadataZze);
        }
        double dZza = zzacVar.zza();
        if (Double.isNaN(dZza) || Math.abs(dZza - this.zzr) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzr = dZza;
            z = true;
        }
        boolean zZzb = zzacVar.zzb();
        if (zZzb != this.zzs) {
            this.zzs = zZzb;
            z = true;
        }
        Logger logger = zzg;
        logger.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzk));
        Cast.Listener listener = this.zzx;
        if (listener != null && (z || this.zzk)) {
            listener.onVolumeChanged();
        }
        Double.isNaN(zzacVar.zzg());
        int iZzc = zzacVar.zzc();
        if (iZzc != this.zzt) {
            this.zzt = iZzc;
            z5 = true;
        } else {
            z5 = false;
        }
        logger.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z5), Boolean.valueOf(this.zzk));
        if (listener != null && (z5 || this.zzk)) {
            listener.onActiveInputStateChanged(this.zzt);
        }
        int iZzd = zzacVar.zzd();
        if (iZzd != this.zzu) {
            this.zzu = iZzd;
            z10 = true;
        } else {
            z10 = false;
        }
        logger.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z10), Boolean.valueOf(this.zzk));
        if (listener != null && (z10 || this.zzk)) {
            listener.onStandbyStateChanged(this.zzu);
        }
        if (!CastUtils.zza(this.zzv, zzacVar.zzf())) {
            this.zzv = zzacVar.zzf();
        }
        this.zzk = false;
    }

    public final /* synthetic */ void zzF(com.google.android.gms.cast.internal.zza zzaVar) {
        boolean z;
        String strZza = zzaVar.zza();
        if (CastUtils.zza(strZza, this.zzq)) {
            z = false;
        } else {
            this.zzq = strZza;
            z = true;
        }
        zzg.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzl));
        Cast.Listener listener = this.zzx;
        if (listener != null && (z || this.zzl)) {
            listener.onApplicationStatusChanged();
        }
        this.zzl = false;
    }

    public final /* synthetic */ void zzG(Cast.ApplicationConnectionResult applicationConnectionResult) {
        synchronized (this.zzn) {
            try {
                com.google.android.gms.tasks.i iVar = this.zzb;
                if (iVar != null) {
                    iVar.b(applicationConnectionResult);
                }
                this.zzb = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzI(int i10) {
        synchronized (this.zzo) {
            try {
                com.google.android.gms.tasks.i iVar = this.zzc;
                if (iVar == null) {
                    return;
                }
                if (i10 == 0) {
                    iVar.b(new Status(0, null, null, null));
                } else {
                    iVar.a(zzab(i10));
                }
                this.zzc = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void zzJ(long j10, int i10) {
        com.google.android.gms.tasks.i iVar;
        Map map = this.zzd;
        synchronized (map) {
            Long lValueOf = Long.valueOf(j10);
            iVar = (com.google.android.gms.tasks.i) map.get(lValueOf);
            map.remove(lValueOf);
        }
        if (iVar != null) {
            if (i10 == 0) {
                iVar.b(null);
            } else {
                iVar.a(zzab(i10));
            }
        }
    }

    public final /* synthetic */ Handler zzK() {
        if (this.zzj == null) {
            this.zzj = new androidx.loader.content.j(getLooper(), 2);
        }
        return this.zzj;
    }

    public final /* synthetic */ void zzL() {
        this.zzt = -1;
        this.zzu = -1;
        this.zzp = null;
        this.zzq = null;
        this.zzr = 0.0d;
        zzt();
        this.zzs = false;
        this.zzv = null;
    }

    public final /* synthetic */ void zzN(boolean z) {
        this.zzk = true;
    }

    public final /* synthetic */ void zzO(boolean z) {
        this.zzl = true;
    }

    public final /* synthetic */ void zzP(ApplicationMetadata applicationMetadata) {
        this.zzp = applicationMetadata;
    }

    public final /* synthetic */ void zzQ(String str) {
        this.zzq = str;
    }

    public final /* synthetic */ CastDevice zzR() {
        return this.zzw;
    }

    public final /* synthetic */ Cast.Listener zzS() {
        return this.zzx;
    }

    public final /* synthetic */ List zzT() {
        return this.zzy;
    }

    public final /* synthetic */ void zzU(int i10) {
        this.zzz = i10;
    }

    @Override // com.google.android.gms.cast.zzq
    public final boolean zza() {
        return this.zzz == 3;
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzb(zzp zzpVar) {
        t.i(zzpVar);
        this.zzy.add(zzpVar);
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzc() {
        com.google.android.gms.common.api.internal.n nVarRegisterListener = registerListener(this.zza, "castDeviceControllerListenerKey");
        r rVar = new r();
        com.google.android.gms.common.api.internal.t tVar = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzbd
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.cast.internal.zzy zzyVar = (com.google.android.gms.cast.internal.zzy) obj;
                com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
                zzyVar.getContext();
                zzahVar.zzq(this.zza.zza, u0.i());
                com.google.android.gms.cast.internal.zzah zzahVar2 = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
                zzyVar.getContext();
                zzahVar2.zzp(u0.i());
                ((com.google.android.gms.tasks.i) obj2).b(null);
            }
        };
        zzas zzasVar = zzas.zza;
        this.zzz = 2;
        rVar.f12789d = nVarRegisterListener;
        rVar.f12787b = tVar;
        rVar.f12788c = zzasVar;
        rVar.f12790e = new d[]{zzaq.zzb};
        rVar.f12786a = 8428;
        return doRegisterEventListener(rVar.b());
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzd() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = zzau.zza;
        xVarBuilder.f12810d = 8403;
        h hVarDoWrite = doWrite(xVarBuilder.a());
        zzD();
        zzC(this.zza);
        return hVarDoWrite;
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zze() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = zzav.zza;
        xVarBuilder.f12810d = 8404;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzf(final String str, final String str2) {
        CastUtils.throwIfInvalidNamespace(str);
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() > 524288) {
            zzg.w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size524288");
        }
        x xVarBuilder = y.builder();
        final String str3 = null;
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t(str3, str, str2) { // from class: com.google.android.gms.cast.zzaw
            private final /* synthetic */ String zzb;
            private final /* synthetic */ String zzc;

            {
                this.zzb = str;
                this.zzc = str2;
            }

            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) {
                zzbm zzbmVar = this.zza;
                zzbmVar.zzu(null, this.zzb, this.zzc, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8405;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzg(final String str, final LaunchOptions launchOptions) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzax
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzv(str, launchOptions, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8406;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzh(final String str) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzaz
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzx(str, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8409;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzi(final double d4) {
        if (Double.isInfinite(d4) || Double.isNaN(d4)) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(d4).length() + 17);
            sb2.append("Volume cannot be ");
            sb2.append(d4);
            throw new IllegalArgumentException(sb2.toString());
        }
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzba
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzy(d4, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8411;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final double zzj() {
        zzY();
        return this.zzr;
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzk(final boolean z) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzbb
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzz(z, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8412;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final boolean zzl() {
        zzY();
        return this.zzs;
    }

    @Override // com.google.android.gms.cast.zzq
    public final int zzm() {
        zzY();
        return this.zzt;
    }

    @Override // com.google.android.gms.cast.zzq
    public final int zzn() {
        zzY();
        return this.zzu;
    }

    @Override // com.google.android.gms.cast.zzq
    public final ApplicationMetadata zzo() {
        zzY();
        return this.zzp;
    }

    @Override // com.google.android.gms.cast.zzq
    public final String zzp() {
        zzY();
        return this.zzq;
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzq(final String str, final Cast.MessageReceivedCallback messageReceivedCallback) {
        CastUtils.throwIfInvalidNamespace(str);
        if (messageReceivedCallback != null) {
            Map map = this.zze;
            synchronized (map) {
                map.put(str, messageReceivedCallback);
            }
        }
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzbc
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzA(str, messageReceivedCallback, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8413;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.cast.zzq
    public final h zzr(final String str) {
        final Cast.MessageReceivedCallback messageReceivedCallback;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        Map map = this.zze;
        synchronized (map) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) map.remove(str);
        }
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t() { // from class: com.google.android.gms.cast.zzat
            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzB(messageReceivedCallback, str, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8414;
        return doWrite(xVarBuilder.a());
    }

    public final h zzs(final String str, final String str2, zzbn zzbnVar) {
        x xVarBuilder = y.builder();
        final zzbn zzbnVar2 = null;
        xVarBuilder.f12807a = new com.google.android.gms.common.api.internal.t(str, str2, zzbnVar2) { // from class: com.google.android.gms.cast.zzay
            private final /* synthetic */ String zzb;
            private final /* synthetic */ String zzc;

            @Override // com.google.android.gms.common.api.internal.t
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                zzbm zzbmVar = this.zza;
                String str3 = this.zzb;
                String str4 = this.zzc;
                zzbmVar.zzw(str3, str4, null, (com.google.android.gms.cast.internal.zzy) obj, (com.google.android.gms.tasks.i) obj2);
            }
        };
        xVarBuilder.f12810d = 8407;
        return doWrite(xVarBuilder.a());
    }

    @RequiresNonNull({"device"})
    public final double zzt() {
        CastDevice castDevice = this.zzw;
        if (castDevice.hasCapability(2048)) {
            return 0.02d;
        }
        return (!castDevice.hasCapability(4) || castDevice.hasCapability(1) || "Chromecast Audio".equals(castDevice.getModelName())) ? 0.05d : 0.02d;
    }

    public final /* synthetic */ void zzu(String str, String str2, String str3, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) {
        long jIncrementAndGet = this.zzm.incrementAndGet();
        zzY();
        try {
            this.zzd.put(Long.valueOf(jIncrementAndGet), iVar);
            zzyVar.getContext();
            ((com.google.android.gms.cast.internal.zzah) zzyVar.getService()).zzk(str2, str3, jIncrementAndGet, u0.i());
        } catch (RemoteException e5) {
            this.zzd.remove(Long.valueOf(jIncrementAndGet));
            iVar.a(e5);
        }
    }

    public final /* synthetic */ void zzv(String str, LaunchOptions launchOptions, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        zzY();
        com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
        zzyVar.getContext();
        zzahVar.zzn(str, launchOptions, u0.i());
        zzZ(iVar);
    }

    public final /* synthetic */ void zzw(String str, String str2, zzbn zzbnVar, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        zzY();
        com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
        zzyVar.getContext();
        zzahVar.zzo(str, str2, null, u0.i());
        zzZ(iVar);
    }

    public final /* synthetic */ void zzx(String str, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        zzY();
        com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
        zzyVar.getContext();
        zzahVar.zzg(str, u0.i());
        synchronized (this.zzo) {
            try {
                if (this.zzc != null) {
                    iVar.a(zzab(2001));
                } else {
                    this.zzc = iVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void zzy(double d4, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
        double d10 = this.zzr;
        boolean z = this.zzs;
        zzyVar.getContext();
        zzahVar.zzi(d4, d10, z, u0.i());
        iVar.b(null);
    }

    public final /* synthetic */ void zzz(boolean z, com.google.android.gms.cast.internal.zzy zzyVar, com.google.android.gms.tasks.i iVar) throws RemoteException {
        com.google.android.gms.cast.internal.zzah zzahVar = (com.google.android.gms.cast.internal.zzah) zzyVar.getService();
        double d4 = this.zzr;
        boolean z5 = this.zzs;
        zzyVar.getContext();
        zzahVar.zzj(z, d4, z5, u0.i());
        iVar.b(null);
    }
}
