package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.AttributionSource;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class f {
    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private volatile String zzA;
    private volatile e4.a zzB;
    private o3.b zzC;
    private boolean zzD;
    private volatile t0 zzE;
    z0 zza;
    final Handler zzb;
    protected d zzc;
    protected AtomicInteger zzd;
    private int zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private long zzj;
    private volatile String zzk;
    private final Context zzl;
    private final Looper zzm;
    private final l zzn;
    private final o3.f zzo;
    private final Object zzp;
    private final Object zzq;
    private q zzr;
    private IInterface zzs;
    private final ArrayList zzt;
    private q0 zzu;
    private int zzv;
    private final b zzw;
    private final c zzx;
    private final int zzy;
    private final String zzz;
    private static final o3.d[] zze = new o3.d[0];
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};

    public f(Context context, Looper looper, x0 x0Var, int i10, u uVar, u uVar2, String str) {
        o3.e eVar = o3.e.f20741d;
        this.zzk = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList();
        this.zzv = 1;
        this.zzC = null;
        this.zzD = false;
        this.zzE = null;
        this.zzd = new AtomicInteger(0);
        t.j(context, "Context must not be null");
        this.zzl = context;
        t.j(looper, "Looper must not be null");
        this.zzm = looper;
        t.j(x0Var, "Supervisor must not be null");
        this.zzn = x0Var;
        this.zzo = eVar;
        this.zzb = new o0(this, looper);
        this.zzy = i10;
        this.zzw = uVar;
        this.zzx = uVar2;
        this.zzz = str;
    }

    public final void c(int i10, IInterface iInterface) {
        z0 z0Var;
        t.b((i10 == 4) == (iInterface != null));
        synchronized (this.zzp) {
            try {
                this.zzv = i10;
                this.zzs = iInterface;
                Bundle bundle = null;
                if (i10 == 1) {
                    q0 q0Var = this.zzu;
                    if (q0Var != null) {
                        l lVar = this.zzn;
                        String str = this.zza.f12943a;
                        t.i(str);
                        String str2 = this.zza.f12944b;
                        zza();
                        boolean z = this.zza.f12945c;
                        lVar.getClass();
                        lVar.d(new u0(str, str2, z), q0Var);
                        this.zzu = null;
                    }
                } else if (i10 == 2 || i10 == 3) {
                    q0 q0Var2 = this.zzu;
                    if (q0Var2 != null && (z0Var = this.zza) != null) {
                        String str3 = z0Var.f12943a;
                        String str4 = z0Var.f12944b;
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 70 + String.valueOf(str4).length());
                        sb2.append("Calling connect() while still connected, missing disconnect() for ");
                        sb2.append(str3);
                        sb2.append(" on ");
                        sb2.append(str4);
                        Log.e("GmsClient", sb2.toString());
                        l lVar2 = this.zzn;
                        String str5 = this.zza.f12943a;
                        t.i(str5);
                        String str6 = this.zza.f12944b;
                        zza();
                        boolean z5 = this.zza.f12945c;
                        lVar2.getClass();
                        lVar2.d(new u0(str5, str6, z5), q0Var2);
                        this.zzd.incrementAndGet();
                    }
                    q0 q0Var3 = new q0(this, this.zzd.get());
                    this.zzu = q0Var3;
                    z0 z0Var2 = (this.zzv != 3 || getLocalStartServiceAction() == null) ? new z0(getStartServicePackage(), getStartServiceAction(), getUseDynamicLookup()) : new z0(getContext().getPackageName(), getLocalStartServiceAction(), false);
                    this.zza = z0Var2;
                    if (z0Var2.f12945c && getMinApkVersion() < 17895000) {
                        throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(this.zza.f12943a)));
                    }
                    l lVar3 = this.zzn;
                    String str7 = this.zza.f12943a;
                    t.i(str7);
                    o3.b bVarC = lVar3.c(new u0(str7, this.zza.f12944b, this.zza.f12945c), q0Var3, zza(), getBindServiceExecutor());
                    if (!bVarC.d()) {
                        z0 z0Var3 = this.zza;
                        String str8 = z0Var3.f12943a;
                        String str9 = z0Var3.f12944b;
                        StringBuilder sb3 = new StringBuilder(String.valueOf(str8).length() + 34 + String.valueOf(str9).length());
                        sb3.append("unable to connect to service: ");
                        sb3.append(str8);
                        sb3.append(" on ");
                        sb3.append(str9);
                        Log.w("GmsClient", sb3.toString());
                        int i11 = bVarC.f20729l;
                        if (i11 == -1) {
                            i11 = 16;
                        }
                        if (bVarC.f20730m != null) {
                            bundle = new Bundle();
                            bundle.putParcelable(KEY_PENDING_INTENT, bVarC.f20730m);
                        }
                        zzb(i11, bundle, this.zzd.get());
                    }
                } else if (i10 == 4) {
                    t.i(iInterface);
                    onConnectedLocked(iInterface);
                }
            } finally {
            }
        }
    }

    public void checkAvailabilityAndConnect() {
        int iB = this.zzo.b(this.zzl, getMinApkVersion());
        if (iB == 0) {
            connect(new u(this));
        } else {
            c(1, null);
            triggerNotAvailable(new u(this), iB, null);
        }
    }

    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void connect(d dVar) {
        t.j(dVar, "Connection progress callbacks cannot be null.");
        this.zzc = dVar;
        c(2, null);
    }

    public abstract IInterface createServiceInterface(IBinder iBinder);

    public void disconnect() {
        this.zzd.incrementAndGet();
        ArrayList arrayList = this.zzt;
        synchronized (arrayList) {
            try {
                int size = arrayList.size();
                for (int i10 = 0; i10 < size; i10++) {
                    ((k0) arrayList.get(i10)).d();
                }
                arrayList.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        synchronized (this.zzq) {
            this.zzr = null;
        }
        c(1, null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i10;
        IInterface iInterface;
        q qVar;
        long j10;
        synchronized (this.zzp) {
            i10 = this.zzv;
            iInterface = this.zzs;
        }
        synchronized (this.zzq) {
            qVar = this.zzr;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        if (i10 == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i10 == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i10 == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i10 == 4) {
            printWriter.print("CONNECTED");
        } else if (i10 != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) getServiceDescriptor()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (qVar == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(qVar.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzh > 0) {
            PrintWriter printWriterAppend = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j11 = this.zzh;
            String str2 = simpleDateFormat.format(new Date(j11));
            j10 = 0;
            StringBuilder sb2 = new StringBuilder(String.valueOf(j11).length() + 1 + String.valueOf(str2).length());
            sb2.append(j11);
            sb2.append(" ");
            sb2.append(str2);
            printWriterAppend.println(sb2.toString());
        } else {
            j10 = 0;
        }
        if (this.zzg > j10) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            int i11 = this.zzf;
            if (i11 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i11 == 2) {
                printWriter.append("CAUSE_NETWORK_LOST");
            } else if (i11 != 3) {
                printWriter.append((CharSequence) String.valueOf(i11));
            } else {
                printWriter.append("CAUSE_DEAD_OBJECT_EXCEPTION");
            }
            PrintWriter printWriterAppend2 = printWriter.append(" lastSuspendedTime=");
            long j12 = this.zzg;
            String str3 = simpleDateFormat.format(new Date(j12));
            StringBuilder sb3 = new StringBuilder(String.valueOf(j12).length() + 1 + String.valueOf(str3).length());
            sb3.append(j12);
            sb3.append(" ");
            sb3.append(str3);
            printWriterAppend2.println(sb3.toString());
        }
        if (this.zzj > j10) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) com.google.android.gms.common.api.k.getStatusCodeString(this.zzi));
            PrintWriter printWriterAppend3 = printWriter.append(" lastFailedTime=");
            long j13 = this.zzj;
            String str4 = simpleDateFormat.format(new Date(j13));
            StringBuilder sb4 = new StringBuilder(String.valueOf(j13).length() + 1 + String.valueOf(str4).length());
            sb4.append(j13);
            sb4.append(" ");
            sb4.append(str4);
            printWriterAppend3.println(sb4.toString());
        }
    }

    public boolean enableLocalFallback() {
        return false;
    }

    public abstract Account getAccount();

    public o3.d[] getApiFeatures() {
        return zze;
    }

    public e4.a getAttributionSourceWrapper() {
        return this.zzB;
    }

    public final o3.d[] getAvailableFeatures() {
        t0 t0Var = this.zzE;
        if (t0Var == null) {
            return null;
        }
        return t0Var.f12914l;
    }

    public abstract Executor getBindServiceExecutor();

    public Bundle getConnectionHint() {
        return null;
    }

    public final Context getContext() {
        return this.zzl;
    }

    public String getEndpointPackageName() {
        z0 z0Var;
        if (!isConnected() || (z0Var = this.zza) == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
        return z0Var.f12944b;
    }

    public int getGCoreServiceId() {
        return this.zzy;
    }

    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    public String getLastDisconnectMessage() {
        return this.zzk;
    }

    public String getLocalStartServiceAction() {
        return null;
    }

    public final Looper getLooper() {
        return this.zzm;
    }

    public abstract int getMinApkVersion();

    public void getRemoteService(n nVar, Set<Scope> set) {
        AttributionSource attributionSource;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        String attributionTag = (Build.VERSION.SDK_INT < 31 || this.zzB == null || (attributionSource = this.zzB.f15028a) == null || attributionSource.getAttributionTag() == null) ? this.zzA : attributionSource.getAttributionTag();
        String str = attributionTag;
        int i10 = this.zzy;
        int i11 = o3.f.f20742a;
        Scope[] scopeArr = j.f12863y;
        Bundle bundle = new Bundle();
        o3.d[] dVarArr = j.z;
        j jVar = new j(6, i10, i11, null, null, scopeArr, bundle, null, dVarArr, dVarArr, true, 0, false, str);
        jVar.f12867n = this.zzl.getPackageName();
        jVar.f12870q = getServiceRequestExtraArgs;
        if (set != null) {
            jVar.f12869p = (Scope[]) set.toArray(new Scope[0]);
        }
        if (requiresSignIn()) {
            Account account = getAccount();
            if (account == null) {
                account = new Account(DEFAULT_ACCOUNT, "com.google");
            }
            jVar.f12871r = account;
            if (nVar != null) {
                jVar.f12868o = nVar.asBinder();
            }
        } else if (requiresAccount()) {
            jVar.f12871r = getAccount();
        }
        jVar.f12872s = zze;
        jVar.f12873t = getApiFeatures();
        if (usesClientTelemetry()) {
            jVar.f12876w = true;
        }
        try {
            synchronized (this.zzq) {
                try {
                    q qVar = this.zzr;
                    if (qVar != null) {
                        qVar.h(new p0(this, this.zzd.get()), jVar);
                    } else {
                        Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                    }
                } finally {
                }
            }
        } catch (DeadObjectException e5) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e5);
            triggerConnectionSuspended(3);
        } catch (RemoteException e6) {
            e = e6;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.zzd.get());
        } catch (SecurityException e10) {
            throw e10;
        } catch (RuntimeException e11) {
            e = e11;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.zzd.get());
        }
    }

    public abstract Set getScopes();

    public final IInterface getService() throws DeadObjectException {
        IInterface iInterface;
        synchronized (this.zzp) {
            try {
                if (this.zzv == 5) {
                    throw new DeadObjectException();
                }
                checkConnected();
                iInterface = this.zzs;
                t.j(iInterface, "Client is connected but service is null");
            } catch (Throwable th) {
                throw th;
            }
        }
        return iInterface;
    }

    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzq) {
            try {
                q qVar = this.zzr;
                if (qVar == null) {
                    return null;
                }
                return qVar.asBinder();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract String getServiceDescriptor();

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public abstract String getStartServiceAction();

    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    public i getTelemetryConfiguration() {
        t0 t0Var = this.zzE;
        if (t0Var == null) {
            return null;
        }
        return t0Var.f12916n;
    }

    public boolean getUseDynamicLookup() {
        return getMinApkVersion() >= 211700000;
    }

    public boolean hasConnectionInfo() {
        return this.zzE != null;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzp) {
            z = this.zzv == 4;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzp) {
            int i10 = this.zzv;
            z = true;
            if (i10 != 2 && i10 != 3) {
                z = false;
            }
        }
        return z;
    }

    public void onConnectedLocked(IInterface iInterface) {
        this.zzh = System.currentTimeMillis();
    }

    public void onConnectionFailed(o3.b bVar) {
        this.zzi = bVar.f20729l;
        this.zzj = System.currentTimeMillis();
    }

    public void onConnectionSuspended(int i10) {
        this.zzf = i10;
        this.zzg = System.currentTimeMillis();
    }

    public void onPostInitHandler(int i10, IBinder iBinder, Bundle bundle, int i11) {
        r0 r0Var = new r0(this, i10, iBinder, bundle);
        Handler handler = this.zzb;
        handler.sendMessage(handler.obtainMessage(1, i11, -1, r0Var));
    }

    public void onUserSignOut(e eVar) {
        eVar.m();
    }

    public boolean providesSignIn() {
        return false;
    }

    public boolean requiresAccount() {
        return false;
    }

    public boolean requiresGooglePlayServices() {
        return true;
    }

    public boolean requiresSignIn() {
        return false;
    }

    public void setAttributionSourceWrapper(e4.a aVar) {
        this.zzB = aVar;
    }

    public void setAttributionTag(String str) {
        this.zzA = str;
    }

    public void triggerConnectionSuspended(int i10) {
        int i11 = this.zzd.get();
        Handler handler = this.zzb;
        handler.sendMessage(handler.obtainMessage(6, i11, i10));
    }

    public void triggerNotAvailable(d dVar, int i10, PendingIntent pendingIntent) {
        t.j(dVar, "Connection progress callbacks cannot be null.");
        this.zzc = dVar;
        int i11 = this.zzd.get();
        Handler handler = this.zzb;
        handler.sendMessage(handler.obtainMessage(3, i11, i10, pendingIntent));
    }

    public boolean usesClientTelemetry() {
        return false;
    }

    public final String zza() {
        String str = this.zzz;
        return str == null ? this.zzl.getClass().getName() : str;
    }

    public final void zzb(int i10, Bundle bundle, int i11) {
        s0 s0Var = new s0(this, i10, bundle);
        Handler handler = this.zzb;
        handler.sendMessage(handler.obtainMessage(7, i11, -1, s0Var));
    }

    public final void zzc(t0 t0Var) {
        this.zzE = t0Var;
        if (usesClientTelemetry()) {
            i iVar = t0Var.f12916n;
            u uVarD = u.d();
            v vVar = iVar == null ? null : iVar.f12853i;
            synchronized (uVarD) {
                if (vVar == null) {
                    uVarD.f12919a = u.f12918c;
                    return;
                }
                v vVar2 = (v) uVarD.f12919a;
                if (vVar2 == null || vVar2.f12924i < vVar.f12924i) {
                    uVarD.f12919a = vVar;
                }
            }
        }
    }

    public final /* synthetic */ void zzd(int i10, IInterface iInterface) {
        c(i10, null);
    }

    public final /* synthetic */ boolean zze(int i10, int i11, IInterface iInterface) {
        synchronized (this.zzp) {
            try {
                if (this.zzv != i10) {
                    return false;
                }
                c(i11, iInterface);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void zzf(int i10) {
        int i11;
        int i12;
        synchronized (this.zzp) {
            i11 = this.zzv;
        }
        if (i11 == 3) {
            this.zzD = true;
            i12 = 5;
        } else {
            i12 = 4;
        }
        Handler handler = this.zzb;
        handler.sendMessage(handler.obtainMessage(i12, this.zzd.get(), 16));
    }

    public final /* synthetic */ boolean zzg() {
        if (this.zzD || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final /* synthetic */ Object zzh() {
        return this.zzq;
    }

    public final /* synthetic */ void zzi(q qVar) {
        this.zzr = qVar;
    }

    public final /* synthetic */ ArrayList zzj() {
        return this.zzt;
    }

    public final /* synthetic */ b zzk() {
        return this.zzw;
    }

    public final /* synthetic */ c zzl() {
        return this.zzx;
    }

    public final /* synthetic */ o3.b zzm() {
        return this.zzC;
    }

    public final /* synthetic */ void zzn(o3.b bVar) {
        this.zzC = bVar;
    }

    public final /* synthetic */ boolean zzo() {
        return this.zzD;
    }

    public void disconnect(String str) {
        this.zzk = str;
        disconnect();
    }
}
