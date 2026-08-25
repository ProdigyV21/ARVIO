package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class CachedSettingsIo {
    private static final String SETTINGS_CACHE_FILENAME = "com.crashlytics.settings.json";
    private final File cachedSettingsFile;

    public CachedSettingsIo(FileStore fileStore) {
        this.cachedSettingsFile = fileStore.getCommonFile(SETTINGS_CACHE_FILENAME);
    }

    private File getSettingsFile() {
        return this.cachedSettingsFile;
    }

    public JSONObject readCachedSettings() throws Throwable {
        Throwable th;
        FileInputStream fileInputStream;
        JSONObject jSONObject;
        Logger.getLogger().d("Checking for cached settings...");
        FileInputStream fileInputStream2 = null;
        try {
            try {
                File settingsFile = getSettingsFile();
                if (settingsFile.exists()) {
                    fileInputStream = new FileInputStream(settingsFile);
                    try {
                        jSONObject = new JSONObject(CommonUtils.streamToString(fileInputStream));
                        fileInputStream2 = fileInputStream;
                    } catch (Exception e5) {
                        e = e5;
                        Logger.getLogger().e("Failed to fetch cached settings", e);
                        CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    }
                } else {
                    Logger.getLogger().v("Settings file does not exist.");
                    jSONObject = null;
                }
                CommonUtils.closeOrLog(fileInputStream2, "Error while closing settings cache file.");
                return jSONObject;
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.closeOrLog(null, "Error while closing settings cache file.");
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeOrLog(null, "Error while closing settings cache file.");
            throw th;
        }
    }

    public void writeCachedSettings(long j10, JSONObject jSONObject) throws Throwable {
        FileWriter fileWriter;
        Logger.getLogger().v("Writing settings to cache file...");
        if (jSONObject != null) {
            FileWriter fileWriter2 = null;
            try {
                try {
                    jSONObject.put("expires_at", j10);
                    fileWriter = new FileWriter(getSettingsFile());
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e5) {
                e = e5;
            }
            try {
                fileWriter.write(jSONObject.toString());
                fileWriter.flush();
                CommonUtils.closeOrLog(fileWriter, "Failed to close settings writer.");
            } catch (Exception e6) {
                e = e6;
                fileWriter2 = fileWriter;
                Logger.getLogger().e("Failed to cache settings", e);
                CommonUtils.closeOrLog(fileWriter2, "Failed to close settings writer.");
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                CommonUtils.closeOrLog(fileWriter2, "Failed to close settings writer.");
                throw th;
            }
        }
    }
}
