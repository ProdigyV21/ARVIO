package com.google.firebase.crashlytics;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.fragment.app.a2;
import com.google.android.gms.tasks.h;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponentDeferredProxy;
import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.RemoteConfigDeferredProxy;
import com.google.firebase.crashlytics.internal.common.AppData;
import com.google.firebase.crashlytics.internal.common.BuildIdInfo;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsAppQualitySessionsSubscriber;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.ExecutorUtils;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.installations.c;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import qb.l;
import x5.d;
import z4.g;

/* JADX INFO: loaded from: classes4.dex */
public class FirebaseCrashlytics {
    static final int APP_EXCEPTION_CALLBACK_TIMEOUT_MS = 500;
    static final String FIREBASE_CRASHLYTICS_ANALYTICS_ORIGIN = "clx";
    static final String LEGACY_CRASH_ANALYTICS_ORIGIN = "crash";
    final CrashlyticsCore core;

    private FirebaseCrashlytics(CrashlyticsCore crashlyticsCore) {
        this.core = crashlyticsCore;
    }

    public static FirebaseCrashlytics getInstance() {
        FirebaseCrashlytics firebaseCrashlytics = (FirebaseCrashlytics) g.c().b(FirebaseCrashlytics.class);
        if (firebaseCrashlytics != null) {
            return firebaseCrashlytics;
        }
        throw new NullPointerException("FirebaseCrashlytics component is not present.");
    }

    public static FirebaseCrashlytics init(g gVar, c cVar, m5.a<CrashlyticsNativeComponent> aVar, m5.a<a5.a> aVar2, m5.a<u5.a> aVar3) {
        gVar.a();
        Context context = gVar.f23184a;
        String packageName = context.getPackageName();
        Logger.getLogger().i("Initializing Firebase Crashlytics " + CrashlyticsCore.getVersion() + " for " + packageName);
        FileStore fileStore = new FileStore(context);
        DataCollectionArbiter dataCollectionArbiter = new DataCollectionArbiter(gVar);
        IdManager idManager = new IdManager(context, packageName, cVar, dataCollectionArbiter);
        CrashlyticsNativeComponentDeferredProxy crashlyticsNativeComponentDeferredProxy = new CrashlyticsNativeComponentDeferredProxy(aVar);
        AnalyticsDeferredProxy analyticsDeferredProxy = new AnalyticsDeferredProxy(aVar2);
        ExecutorService executorServiceBuildSingleThreadExecutorService = ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler");
        CrashlyticsAppQualitySessionsSubscriber crashlyticsAppQualitySessionsSubscriber = new CrashlyticsAppQualitySessionsSubscriber(dataCollectionArbiter, fileStore);
        x5.c cVar2 = x5.c.f22570a;
        d sessionSubscriberName = crashlyticsAppQualitySessionsSubscriber.getSessionSubscriberName();
        x5.c cVar3 = x5.c.f22570a;
        x5.a aVarA = x5.c.a(sessionSubscriberName);
        FirebaseCrashlytics firebaseCrashlytics = null;
        if (aVarA.f22560b != null) {
            Log.d("SessionsDependencies", "Subscriber " + sessionSubscriberName + " already registered.");
        } else {
            aVarA.f22560b = crashlyticsAppQualitySessionsSubscriber;
            Log.d("SessionsDependencies", "Subscriber " + sessionSubscriberName + " registered.");
            aVarA.f22559a.b(null);
        }
        final CrashlyticsCore crashlyticsCore = new CrashlyticsCore(gVar, idManager, crashlyticsNativeComponentDeferredProxy, dataCollectionArbiter, analyticsDeferredProxy.getDeferredBreadcrumbSource(), analyticsDeferredProxy.getAnalyticsEventLogger(), fileStore, executorServiceBuildSingleThreadExecutorService, crashlyticsAppQualitySessionsSubscriber, new RemoteConfigDeferredProxy(aVar3));
        gVar.a();
        String str = gVar.f23186c.f23200b;
        String mappingFileId = CommonUtils.getMappingFileId(context);
        List<BuildIdInfo> buildIdInfo = CommonUtils.getBuildIdInfo(context);
        Logger.getLogger().d("Mapping file ID is: " + mappingFileId);
        for (BuildIdInfo buildIdInfo2 : buildIdInfo) {
            Logger logger = Logger.getLogger();
            String libraryName = buildIdInfo2.getLibraryName();
            String arch = buildIdInfo2.getArch();
            String buildId = buildIdInfo2.getBuildId();
            StringBuilder sbR = a2.r("Build id for ", libraryName, " on ", arch, ": ");
            sbR.append(buildId);
            logger.d(sbR.toString());
            firebaseCrashlytics = firebaseCrashlytics;
        }
        FirebaseCrashlytics firebaseCrashlytics2 = firebaseCrashlytics;
        try {
            AppData appDataCreate = AppData.create(context, idManager, str, mappingFileId, buildIdInfo, new DevelopmentPlatformProvider(context));
            Logger.getLogger().v("Installer package name is: " + appDataCreate.installerPackageName);
            ExecutorService executorServiceBuildSingleThreadExecutorService2 = ExecutorUtils.buildSingleThreadExecutorService("com.google.firebase.crashlytics.startup");
            final SettingsController settingsControllerCreate = SettingsController.create(context, str, idManager, new HttpRequestFactory(), appDataCreate.versionCode, appDataCreate.versionName, fileStore, dataCollectionArbiter);
            settingsControllerCreate.loadSettingsData(executorServiceBuildSingleThreadExecutorService2).d(executorServiceBuildSingleThreadExecutorService2, new com.google.android.gms.tasks.a<Void, Object>() { // from class: com.google.firebase.crashlytics.FirebaseCrashlytics.1
                @Override // com.google.android.gms.tasks.a
                public Object then(h hVar) throws Exception {
                    if (hVar.h()) {
                        return null;
                    }
                    Logger.getLogger().e("Error fetching settings.", hVar.e());
                    return null;
                }
            });
            final boolean zOnPreExecute = crashlyticsCore.onPreExecute(appDataCreate, settingsControllerCreate);
            l.d(executorServiceBuildSingleThreadExecutorService2, new Callable<Void>() { // from class: com.google.firebase.crashlytics.FirebaseCrashlytics.2
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    if (!zOnPreExecute) {
                        return null;
                    }
                    crashlyticsCore.doBackgroundInitializationAsync(settingsControllerCreate);
                    return null;
                }
            });
            return new FirebaseCrashlytics(crashlyticsCore);
        } catch (PackageManager.NameNotFoundException e5) {
            Logger.getLogger().e("Error retrieving app package info.", e5);
            return firebaseCrashlytics2;
        }
    }

    public h checkForUnsentReports() {
        return this.core.checkForUnsentReports();
    }

    public void deleteUnsentReports() {
        this.core.deleteUnsentReports();
    }

    public boolean didCrashOnPreviousExecution() {
        return this.core.didCrashOnPreviousExecution();
    }

    public void log(String str) {
        this.core.log(str);
    }

    public void recordException(Throwable th) {
        if (th == null) {
            Logger.getLogger().w("A null value was passed to recordException. Ignoring.");
        } else {
            this.core.logException(th);
        }
    }

    public void sendUnsentReports() {
        this.core.sendUnsentReports();
    }

    public void setCrashlyticsCollectionEnabled(boolean z) {
        this.core.setCrashlyticsCollectionEnabled(Boolean.valueOf(z));
    }

    public void setCustomKey(String str, boolean z) {
        this.core.setCustomKey(str, Boolean.toString(z));
    }

    public void setCustomKeys(CustomKeysAndValues customKeysAndValues) {
        this.core.setCustomKeys(customKeysAndValues.keysAndValues);
    }

    public void setUserId(String str) {
        this.core.setUserId(str);
    }

    public void setCrashlyticsCollectionEnabled(Boolean bool) {
        this.core.setCrashlyticsCollectionEnabled(bool);
    }

    public void setCustomKey(String str, double d4) {
        this.core.setCustomKey(str, Double.toString(d4));
    }

    public void setCustomKey(String str, float f10) {
        this.core.setCustomKey(str, Float.toString(f10));
    }

    public void setCustomKey(String str, int i10) {
        this.core.setCustomKey(str, Integer.toString(i10));
    }

    public void setCustomKey(String str, long j10) {
        this.core.setCustomKey(str, Long.toString(j10));
    }

    public void setCustomKey(String str, String str2) {
        this.core.setCustomKey(str, str2);
    }
}
