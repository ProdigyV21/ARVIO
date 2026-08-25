package com.google.android.gms.cast.framework;

import a3.a;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.loader.content.j;
import androidx.mediarouter.media.k0;
import androidx.mediarouter.media.z;
import com.google.android.datatransport.runtime.w;
import com.google.android.gms.cast.CredentialsData;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.b1;
import com.google.android.gms.internal.cast.c0;
import com.google.android.gms.internal.cast.c1;
import com.google.android.gms.internal.cast.d0;
import com.google.android.gms.internal.cast.f0;
import com.google.android.gms.internal.cast.h1;
import com.google.android.gms.internal.cast.l2;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m5;
import com.google.android.gms.internal.cast.n4;
import com.google.android.gms.internal.cast.o2;
import com.google.android.gms.internal.cast.q0;
import com.google.android.gms.internal.cast.t0;
import com.google.android.gms.internal.cast.u0;
import com.google.android.gms.internal.cast.u9;
import com.google.android.gms.internal.cast.v0;
import com.google.android.gms.internal.cast.v9;
import com.google.android.gms.internal.cast.x0;
import com.google.android.gms.internal.cast.y0;
import com.google.android.gms.internal.cast.y9;
import com.google.android.gms.internal.cast.z4;
import com.google.android.gms.internal.cast.z7;
import com.google.android.gms.tasks.e;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.s;
import e4.c;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import qb.l;
import z2.b;

/* JADX INFO: loaded from: classes4.dex */
public class CastContext {
    public static final String OPTIONS_PROVIDER_CLASS_NAME_KEY = "com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME";
    private static final Logger zzb = new Logger("CastContext");
    private static final Object zzc = new Object();
    private static volatile CastContext zzd;
    final c0 zza;
    private final Context zze;
    private final zzah zzf;
    private final SessionManager zzg;
    private final zzaa zzh;
    private final PrecacheManager zzi;
    private final MediaNotificationManager zzj;
    private final CastOptions zzk;
    private final com.google.android.gms.cast.internal.zzn zzl;
    private final x0 zzm;
    private final t0 zzn;
    private final List zzo;
    private final c1 zzp;
    private f0 zzq;
    private CastReasonCodes zzr;

    private CastContext(Context context, CastOptions castOptions, List list, x0 x0Var, com.google.android.gms.cast.internal.zzn zznVar) throws ModuleUnavailableException {
        this.zze = context;
        this.zzk = castOptions;
        this.zzm = x0Var;
        this.zzl = zznVar;
        this.zzo = list;
        t0 t0Var = new t0(context);
        this.zzn = t0Var;
        c1 c1Var = x0Var.f13676f;
        this.zzp = c1Var;
        zzi();
        Map mapZzh = zzh();
        castOptions.zzg(new zzl(1));
        try {
            zzah zzahVarF = d0.a(context).f(new d(context.getApplicationContext()), castOptions, x0Var, mapZzh);
            this.zzf = zzahVarF;
            try {
                this.zzh = new zzaa(zzahVarF.zzh());
                try {
                    SessionManager sessionManager = new SessionManager(zzahVarF.zzg(), context);
                    this.zzg = sessionManager;
                    this.zzj = new MediaNotificationManager(sessionManager);
                    this.zzi = new PrecacheManager(castOptions, sessionManager, zznVar);
                    if (c1Var != null) {
                        c1Var.f13214g = sessionManager;
                        j jVar = c1Var.f13210c;
                        t.i(jVar);
                        jVar.post(new b1(c1Var, 0));
                    }
                    ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(3);
                    o2 o2Var = new o2(context, executorServiceNewFixedThreadPool instanceof u9 ? (u9) executorServiceNewFixedThreadPool : executorServiceNewFixedThreadPool instanceof ScheduledExecutorService ? new y9((ScheduledExecutorService) executorServiceNewFixedThreadPool) : new v9(executorServiceNewFixedThreadPool));
                    new Logger("BaseNetUtils");
                    o2Var.a();
                    c0 c0Var = new c0();
                    this.zza = c0Var;
                    try {
                        zzahVarF.zzf(c0Var);
                        c0Var.f13205c.add(t0Var.f13605a);
                        if (!castOptions.zze().isEmpty()) {
                            zzb.i("Setting Route Discovery for appIds: ".concat(String.valueOf(castOptions.zze())), new Object[0]);
                            List listZze = castOptions.zze();
                            Logger logger = t0.f13604f;
                            int size = listZze.size();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(size).length() + 26);
                            sb2.append("SetRouteDiscovery for ");
                            sb2.append(size);
                            sb2.append(" IDs");
                            logger.d(sb2.toString(), new Object[0]);
                            LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
                            Iterator it = listZze.iterator();
                            while (it.hasNext()) {
                                linkedHashSet.add(u0.l((String) it.next()));
                            }
                            Map map = t0Var.f13607c;
                            logger.d("resetting routes. appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(map.keySet())), new Object[0]);
                            HashMap map2 = new HashMap();
                            synchronized (map) {
                                try {
                                    for (String str : linkedHashSet) {
                                        q0 q0Var = (q0) map.get(u0.l(str));
                                        if (q0Var != null) {
                                            map2.put(str, q0Var);
                                        }
                                    }
                                    map.clear();
                                    map.putAll(map2);
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            logger.d("Routes reset. appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(map.keySet())), new Object[0]);
                            LinkedHashSet linkedHashSet2 = t0Var.f13608d;
                            synchronized (linkedHashSet2) {
                                linkedHashSet2.clear();
                                linkedHashSet2.addAll(linkedHashSet);
                            }
                            t0Var.b();
                        }
                        h hVarZzb = zznVar.zzb(new String[]{"com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED", "com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE", "com.google.android.gms.cast.FLAG_FIRELOG_UPLOAD_MODE", "com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE", "com.google.android.gms.cast.FLAG_CLIENT_FEATURE_USAGE_ANALYTICS_ENABLED", "com.google.android.gms.cast.FLAG_CLIENT_ANALYTICS_ENABLED", "com.google.android.gms.cast.FLAG_ANALYTICS_CONSENT_TIMEOUT_SECONDS"});
                        e eVar = new e() { // from class: com.google.android.gms.cast.framework.zzg
                            @Override // com.google.android.gms.tasks.e
                            public final /* synthetic */ void onSuccess(Object obj) {
                                this.zza.zze((Bundle) obj);
                            }
                        };
                        com.google.android.gms.tasks.t tVar = (com.google.android.gms.tasks.t) hVarZzb;
                        tVar.getClass();
                        s sVar = com.google.android.gms.tasks.j.f13856a;
                        tVar.c(sVar, eVar);
                        h hVarZzd = zznVar.zzd(new String[]{"com.google.android.gms.cast.MAP_CAST_STATUS_CODES_TO_CAST_REASON_CODES"});
                        e eVar2 = new e() { // from class: com.google.android.gms.cast.framework.zzh
                            @Override // com.google.android.gms.tasks.e
                            public final /* synthetic */ void onSuccess(Object obj) {
                                this.zza.zzf((Bundle) obj);
                            }
                        };
                        com.google.android.gms.tasks.t tVar2 = (com.google.android.gms.tasks.t) hVarZzd;
                        tVar2.getClass();
                        tVar2.c(sVar, eVar2);
                    } catch (RemoteException e5) {
                        throw new IllegalStateException("Failed to call addAppVisibilityListener", e5);
                    }
                } catch (RemoteException e6) {
                    throw new IllegalStateException("Failed to call getSessionManagerImpl", e6);
                }
            } catch (RemoteException e10) {
                throw new IllegalStateException("Failed to call getDiscoveryManagerImpl", e10);
            }
        } catch (RemoteException e11) {
            throw new IllegalStateException("Failed to call newCastContextImpl", e11);
        }
    }

    public static CastContext getSharedInstance() {
        t.e("Must be called from the main thread.");
        return zzd;
    }

    public static CastContext zza(Context context) throws IllegalStateException {
        t.e("Must be called from the main thread.");
        try {
            return getSharedInstance(context);
        } catch (RuntimeException e5) {
            zzb.e("Failed to load module from Google Play services. Cast will not work properly. Might due to outdated Google Play services. Ignoring this failure silently.", e5);
            return null;
        }
    }

    public static int zzb(int i10) {
        if (zzd != null) {
            return zzd.getCastReasonCodeForCastStatusCode(i10);
        }
        return 0;
    }

    public static /* synthetic */ CastContext zzd(Context context, CastOptions castOptions, OptionsProvider optionsProvider, x0 x0Var, com.google.android.gms.cast.internal.zzn zznVar) {
        synchronized (zzc) {
            try {
                if (zzd == null) {
                    zzd = new CastContext(context, castOptions, optionsProvider.getAdditionalSessionProviders(context), x0Var, zznVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzd;
    }

    private static OptionsProvider zzg(Context context) throws IllegalStateException {
        a8.e eVarA;
        try {
            eVarA = c.a(context);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e5) {
            e = e5;
        }
        try {
            Bundle bundle = ((Context) eVarA.f183l).getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                zzb.e("Bundle is null", new Object[0]);
            }
            String string = bundle.getString(OPTIONS_PROVIDER_CLASS_NAME_KEY);
            if (string != null) {
                return (OptionsProvider) Class.forName(string).asSubclass(OptionsProvider.class).getDeclaredConstructor(null).newInstance(null);
            }
            throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");
        } catch (ClassNotFoundException e6) {
            e = e6;
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        } catch (IllegalAccessException e10) {
            e = e10;
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        } catch (InstantiationException e11) {
            e = e11;
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        } catch (NoSuchMethodException e12) {
            e = e12;
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        } catch (NullPointerException e13) {
            e = e13;
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        } catch (InvocationTargetException e14) {
            e = e14;
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        }
    }

    private final Map zzh() {
        HashMap map = new HashMap();
        f0 f0Var = this.zzq;
        if (f0Var != null) {
            map.put(f0Var.getCategory(), f0Var.zza());
        }
        List<SessionProvider> list = this.zzo;
        if (list != null) {
            for (SessionProvider sessionProvider : list) {
                t.j(sessionProvider, "Additional SessionProvider must not be null.");
                String category = sessionProvider.getCategory();
                t.g(category, "Category for SessionProvider must not be null or empty string.");
                t.a("SessionProvider for category " + category + " already added", !map.containsKey(category));
                map.put(category, sessionProvider.zza());
            }
        }
        return map;
    }

    @RequiresNonNull({"castOptions", "mediaRouter", "appContext"})
    private final void zzi() {
        CastOptions castOptions = this.zzk;
        if (TextUtils.isEmpty(castOptions.getReceiverApplicationId())) {
            this.zzq = null;
        } else {
            this.zzq = new f0(this.zze, castOptions, this.zzm);
        }
    }

    @Deprecated
    public void addAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException, NullPointerException {
    }

    public void addCastStateListener(CastStateListener castStateListener) throws IllegalStateException, NullPointerException {
        t.e("Must be called from the main thread.");
        t.i(castStateListener);
        this.zzg.zzb(castStateListener);
    }

    public void addSessionTransferCallback(SessionTransferCallback sessionTransferCallback) {
        t.e("Must be called from the main thread.");
        t.i(sessionTransferCallback);
        x0 x0Var = this.zzm;
        c1 c1Var = x0Var.f13676f;
        if (c1Var != null) {
            c1Var.a(sessionTransferCallback);
            k0 k0Var = x0Var.f13673c;
            v0 v0Var = new v0(c1Var);
            k0Var.getClass();
            k0.b();
            k0.c().f4331f = v0Var;
        }
    }

    public CastOptions getCastOptions() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        return this.zzk;
    }

    public int getCastReasonCodeForCastStatusCode(int i10) {
        CastReasonCodes castReasonCodes = this.zzr;
        if (castReasonCodes != null) {
            return castReasonCodes.zza(i10);
        }
        zzb.w("castReasonCodes hasn't been initialized yet", new Object[0]);
        return 0;
    }

    public int getCastState() {
        t.e("Must be called from the main thread.");
        return this.zzg.zza();
    }

    public MediaNotificationManager getMediaNotificationManager() {
        t.e("Must be called from the main thread.");
        return this.zzj;
    }

    public z getMergedSelector() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        try {
            return z.b(this.zzf.zze());
        } catch (RemoteException e5) {
            zzb.d(e5, "Unable to call %s on %s.", "getMergedSelectorAsBundle", "zzah");
            return null;
        }
    }

    public PrecacheManager getPrecacheManager() {
        t.e("Must be called from the main thread.");
        return this.zzi;
    }

    public SessionManager getSessionManager() throws IllegalStateException {
        t.e("Must be called from the main thread.");
        return this.zzg;
    }

    @Deprecated
    public boolean isAppVisible() throws IllegalStateException {
        return false;
    }

    @Deprecated
    public boolean onDispatchVolumeKeyEventBeforeJellyBean(KeyEvent keyEvent) {
        t.e("Must be called from the main thread.");
        return false;
    }

    @Deprecated
    public void removeAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException {
    }

    public void removeCastStateListener(CastStateListener castStateListener) throws IllegalStateException {
        t.e("Must be called from the main thread.");
        if (castStateListener == null) {
            return;
        }
        this.zzg.zzc(castStateListener);
    }

    public void removeSessionTransferCallback(SessionTransferCallback sessionTransferCallback) {
        t.e("Must be called from the main thread.");
        t.i(sessionTransferCallback);
        x0 x0Var = this.zzm;
        c1 c1Var = x0Var.f13676f;
        if (c1Var != null) {
            c1.f13207j.d("unregister callback = %s", sessionTransferCallback);
            t.e("Must be called from the main thread.");
            c1Var.f13209b.remove(sessionTransferCallback);
            x0Var.f13673c.getClass();
            k0.b();
            k0.c().f4331f = null;
        }
    }

    public void setLaunchCredentialsData(CredentialsData credentialsData) {
        CastOptions castOptions = this.zzk;
        LaunchOptions.Builder builder = new LaunchOptions.Builder(castOptions.getLaunchOptions());
        builder.setCredentialsData(credentialsData);
        castOptions.zzb(builder.build());
        zzi();
    }

    public void setReceiverApplicationId(String str) {
        t.e("Must be called from the main thread.");
        CastOptions castOptions = this.zzk;
        if (TextUtils.equals(str, castOptions.getReceiverApplicationId())) {
            return;
        }
        castOptions.zza(str);
        zzi();
        try {
            this.zzf.zzi(str, zzh());
        } catch (RemoteException e5) {
            zzb.d(e5, "Unable to call %s on %s.", "setReceiverApplicationId", "zzah");
        }
        zzo zzoVar = new zzo();
        zzoVar.zza(getCastReasonCodeForCastStatusCode(2423));
        zzoVar.zzb(2423);
        zzoVar.zzc();
        Context context = this.zze;
        CastOptions castOptions2 = this.zzk;
        c0 c0Var = this.zza;
        if (l2.f13395l == null) {
            l2.f13395l = new l2(context, castOptions2, c0Var, new y0(context));
        }
        l2 l2Var = l2.f13395l;
        synchronized (l2Var.f13406j) {
            Iterator it = l2Var.f13402f.entrySet().iterator();
            if (it.hasNext()) {
                if (((Map.Entry) it.next()).getValue() != null) {
                    throw new ClassCastException();
                }
                throw null;
            }
        }
        CastButtonFactory.zza(context);
    }

    public final zzaa zzc() {
        t.e("Must be called from the main thread.");
        return this.zzh;
    }

    public final void zze(Bundle bundle) {
        l6 l6Var;
        m5 m5VarC;
        if (z4.k) {
            Context context = this.zze;
            com.google.android.gms.cast.internal.zzn zznVar = this.zzl;
            z4 z4Var = new z4(context, zznVar, this.zzg, this.zzp, this.zza);
            int i10 = bundle.containsKey("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE") ? bundle.getInt("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE", 0) : (bundle.containsKey("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED") && bundle.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED", false)) ? 1 : 0;
            boolean z = bundle.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_FEATURE_USAGE_ANALYTICS_ENABLED", false);
            boolean z5 = bundle.getBoolean("com.google.android.gms.cast.FLAG_CLIENT_ANALYTICS_ENABLED", false);
            z4.k = z5;
            if (i10 == 0) {
                if (!z && !z5) {
                    return;
                } else {
                    i10 = 0;
                }
            }
            z4Var.f13716h = new h1(context, bundle.getLong("com.google.android.gms.cast.FLAG_ANALYTICS_CONSENT_TIMEOUT_SECONDS", 5L));
            String packageName = context.getPackageName();
            Locale locale = Locale.ROOT;
            String strM = androidx.compose.material3.d.m(packageName, ".client_cast_analytics_data");
            z4Var.f13717i = bundle.getLong("com.google.android.gms.cast.FLAG_FIRELOG_UPLOAD_MODE") != 0 ? 2 : 1;
            w.b(context);
            z4Var.f13715g = w.a().c(a.f107e).a("CAST_SENDER_SDK", new b("proto"), z7.f13723p);
            if (bundle.containsKey("com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE")) {
                z4Var.f13713e = Long.valueOf(bundle.getLong("com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE"));
            }
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(strM, 0);
            if (i10 != 0) {
                h hVarZzc = zznVar.zzc(new String[]{"com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR", "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON"});
                n4 n4Var = new n4(z4Var, packageName, i10, sharedPreferences);
                com.google.android.gms.tasks.t tVar = (com.google.android.gms.tasks.t) hVarZzc;
                tVar.getClass();
                tVar.c(com.google.android.gms.tasks.j.f13856a, n4Var);
            }
            if (z) {
                t.i(sharedPreferences);
                Logger logger = l6.f13416i;
                synchronized (l6.class) {
                    try {
                        if (l6.k == null) {
                            l6.k = new l6(sharedPreferences, z4Var, packageName);
                        }
                        l6Var = l6.k;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                String str = l6Var.f13420c;
                SharedPreferences sharedPreferences2 = l6Var.f13419b;
                HashSet hashSet = l6Var.f13423f;
                String string = sharedPreferences2.getString("feature_usage_sdk_version", null);
                String string2 = sharedPreferences2.getString("feature_usage_package_name", null);
                hashSet.clear();
                HashSet hashSet2 = l6Var.f13424g;
                hashSet2.clear();
                l6Var.f13425h = 0L;
                String str2 = l6.f13417j;
                if (str2.equals(string) && str.equals(string2)) {
                    l6Var.f13425h = sharedPreferences2.getLong("feature_usage_last_report_time", 0L);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    HashSet hashSet3 = new HashSet();
                    for (String str3 : sharedPreferences2.getAll().keySet()) {
                        if (str3.startsWith("feature_usage_timestamp_")) {
                            long j10 = sharedPreferences2.getLong(str3, 0L);
                            if (j10 != 0 && jCurrentTimeMillis - j10 > 1209600000) {
                                hashSet3.add(str3);
                            } else if (str3.startsWith("feature_usage_timestamp_reported_feature_")) {
                                m5 m5VarC2 = l6.c(str3.substring(41));
                                if (m5VarC2 != null) {
                                    hashSet2.add(m5VarC2);
                                    hashSet.add(m5VarC2);
                                }
                            } else if (str3.startsWith("feature_usage_timestamp_detected_feature_") && (m5VarC = l6.c(str3.substring(41))) != null) {
                                hashSet.add(m5VarC);
                            }
                        }
                    }
                    l6Var.b(hashSet3);
                    t.i(l6Var.f13422e);
                    t.i(l6Var.f13421d);
                    l6Var.f13422e.post(l6Var.f13421d);
                } else {
                    HashSet hashSet4 = new HashSet();
                    for (String str4 : sharedPreferences2.getAll().keySet()) {
                        if (str4.startsWith("feature_usage_timestamp_")) {
                            hashSet4.add(str4);
                        }
                    }
                    hashSet4.add("feature_usage_last_report_time");
                    l6Var.b(hashSet4);
                    sharedPreferences2.edit().putString("feature_usage_sdk_version", str2).putString("feature_usage_package_name", str).apply();
                }
                l6.a(m5.CAST_CONTEXT);
            }
            if (z4.k) {
                z7.a();
            }
        }
    }

    public final /* synthetic */ void zzf(Bundle bundle) {
        this.zzr = new CastReasonCodes(bundle);
    }

    public static CastContext getSharedInstance(Context context) throws IllegalStateException {
        t.e("Must be called from the main thread.");
        if (zzd == null) {
            synchronized (zzc) {
                if (zzd == null) {
                    Context applicationContext = context.getApplicationContext();
                    OptionsProvider optionsProviderZzg = zzg(applicationContext);
                    CastOptions castOptions = optionsProviderZzg.getCastOptions(applicationContext);
                    com.google.android.gms.cast.internal.zzn zznVar = new com.google.android.gms.cast.internal.zzn(applicationContext);
                    try {
                        zzd = new CastContext(applicationContext, castOptions, optionsProviderZzg.getAdditionalSessionProviders(applicationContext), new x0(applicationContext, k0.d(applicationContext), castOptions, zznVar), zznVar);
                    } catch (ModuleUnavailableException e5) {
                        throw new RuntimeException(e5);
                    }
                }
            }
        }
        return zzd;
    }

    public static h getSharedInstance(Context context, Executor executor) {
        t.e("Must be called from the main thread.");
        if (zzd == null) {
            final Context applicationContext = context.getApplicationContext();
            final OptionsProvider optionsProviderZzg = zzg(applicationContext);
            final CastOptions castOptions = optionsProviderZzg.getCastOptions(applicationContext);
            final com.google.android.gms.cast.internal.zzn zznVar = new com.google.android.gms.cast.internal.zzn(applicationContext);
            final x0 x0Var = new x0(applicationContext, k0.d(applicationContext), castOptions, zznVar);
            return l.d(executor, new Callable() { // from class: com.google.android.gms.cast.framework.zzi
                @Override // java.util.concurrent.Callable
                public final /* synthetic */ Object call() {
                    return CastContext.zzd(applicationContext, castOptions, optionsProviderZzg, x0Var, zznVar);
                }
            });
        }
        return l.n(zzd);
    }
}
