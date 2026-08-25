package com.google.firebase.crashlytics.internal.settings;

import a0.c;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.compose.material3.d;
import com.google.android.gms.tasks.g;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.i;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
public class SettingsController implements SettingsProvider {
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private static final String SETTINGS_URL_FORMAT = "https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/%s/settings";
    private final CachedSettingsIo cachedSettingsIo;
    private final Context context;
    private final CurrentTimeProvider currentTimeProvider;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final AtomicReference<Settings> settings;
    private final SettingsJsonParser settingsJsonParser;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;
    private final AtomicReference<i> settingsTask;

    public SettingsController(Context context, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonParser settingsJsonParser, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall, DataCollectionArbiter dataCollectionArbiter) {
        AtomicReference<Settings> atomicReference = new AtomicReference<>();
        this.settings = atomicReference;
        this.settingsTask = new AtomicReference<>(new i());
        this.context = context;
        this.settingsRequest = settingsRequest;
        this.currentTimeProvider = currentTimeProvider;
        this.settingsJsonParser = settingsJsonParser;
        this.cachedSettingsIo = cachedSettingsIo;
        this.settingsSpiCall = settingsSpiCall;
        this.dataCollectionArbiter = dataCollectionArbiter;
        atomicReference.set(DefaultSettingsJsonTransform.defaultSettings(currentTimeProvider));
    }

    public static SettingsController create(Context context, String str, IdManager idManager, HttpRequestFactory httpRequestFactory, String str2, String str3, FileStore fileStore, DataCollectionArbiter dataCollectionArbiter) {
        String installerPackageName = idManager.getInstallerPackageName();
        SystemCurrentTimeProvider systemCurrentTimeProvider = new SystemCurrentTimeProvider();
        SettingsJsonParser settingsJsonParser = new SettingsJsonParser(systemCurrentTimeProvider);
        CachedSettingsIo cachedSettingsIo = new CachedSettingsIo(fileStore);
        Locale locale = Locale.US;
        return new SettingsController(context, new SettingsRequest(str, idManager.getModelName(), idManager.getOsBuildVersionString(), idManager.getOsDisplayVersionString(), idManager, CommonUtils.createInstanceIdFrom(CommonUtils.getMappingFileId(context), str, str3, str2), str3, str2, DeliveryMechanism.determineFrom(installerPackageName).getId()), systemCurrentTimeProvider, settingsJsonParser, cachedSettingsIo, new DefaultSettingsSpiCall(c.l("https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/", str, "/settings"), httpRequestFactory), dataCollectionArbiter);
    }

    private Settings getCachedSettingsData(SettingsCacheBehavior settingsCacheBehavior) throws Throwable {
        Settings settings = null;
        try {
            if (!SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                JSONObject cachedSettings = this.cachedSettingsIo.readCachedSettings();
                if (cachedSettings != null) {
                    Settings settingsJson = this.settingsJsonParser.parseSettingsJson(cachedSettings);
                    if (settingsJson == null) {
                        Logger.getLogger().e("Failed to parse cached settings data.", null);
                        return null;
                    }
                    logSettings(cachedSettings, "Loaded cached settings: ");
                    long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                    if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) && settingsJson.isExpired(currentTimeMillis)) {
                        Logger.getLogger().v("Cached settings have expired.");
                        return null;
                    }
                    try {
                        Logger.getLogger().v("Returning cached settings.");
                        return settingsJson;
                    } catch (Exception e5) {
                        e = e5;
                        settings = settingsJson;
                        Logger.getLogger().e("Failed to get cached settings", e);
                        return settings;
                    }
                }
                Logger.getLogger().d("No cached settings data found.");
            }
            return null;
        } catch (Exception e6) {
            e = e6;
        }
    }

    private String getStoredBuildInstanceIdentifier() {
        return CommonUtils.getSharedPrefs(this.context).getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logSettings(JSONObject jSONObject, String str) throws JSONException {
        Logger logger = Logger.getLogger();
        StringBuilder sbS = d.s(str);
        sbS.append(jSONObject.toString());
        logger.d(sbS.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setStoredBuildInstanceIdentifier(String str) {
        SharedPreferences.Editor editorEdit = CommonUtils.getSharedPrefs(this.context).edit();
        editorEdit.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, str);
        editorEdit.apply();
        return true;
    }

    public boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(this.settingsRequest.instanceId);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsProvider
    public h getSettingsAsync() {
        return this.settingsTask.get().f13855a;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsProvider
    public Settings getSettingsSync() {
        return this.settings.get();
    }

    public h loadSettingsData(Executor executor) {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE, executor);
    }

    public h loadSettingsData(SettingsCacheBehavior settingsCacheBehavior, Executor executor) throws Throwable {
        Settings cachedSettingsData;
        if (!buildInstanceIdentifierChanged() && (cachedSettingsData = getCachedSettingsData(settingsCacheBehavior)) != null) {
            this.settings.set(cachedSettingsData);
            this.settingsTask.get().d(cachedSettingsData);
            return l.n(null);
        }
        Settings cachedSettingsData2 = getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
        if (cachedSettingsData2 != null) {
            this.settings.set(cachedSettingsData2);
            this.settingsTask.get().d(cachedSettingsData2);
        }
        return this.dataCollectionArbiter.waitForDataCollectionPermission(executor).i(executor, new g<Void, Void>() { // from class: com.google.firebase.crashlytics.internal.settings.SettingsController.1
            @Override // com.google.android.gms.tasks.g
            public h then(Void r52) throws Exception {
                JSONObject jSONObjectInvoke = SettingsController.this.settingsSpiCall.invoke(SettingsController.this.settingsRequest, true);
                if (jSONObjectInvoke != null) {
                    Settings settingsJson = SettingsController.this.settingsJsonParser.parseSettingsJson(jSONObjectInvoke);
                    SettingsController.this.cachedSettingsIo.writeCachedSettings(settingsJson.expiresAtMillis, jSONObjectInvoke);
                    SettingsController.this.logSettings(jSONObjectInvoke, "Loaded settings: ");
                    SettingsController settingsController = SettingsController.this;
                    settingsController.setStoredBuildInstanceIdentifier(settingsController.settingsRequest.instanceId);
                    SettingsController.this.settings.set(settingsJson);
                    ((i) SettingsController.this.settingsTask.get()).d(settingsJson);
                }
                return l.n(null);
            }
        });
    }
}
