package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.a2;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.i;
import com.google.android.gms.tasks.t;
import com.google.firebase.crashlytics.internal.Logger;
import java.util.concurrent.Executor;
import z4.g;

/* JADX INFO: loaded from: classes4.dex */
public class DataCollectionArbiter {
    private static final String FIREBASE_CRASHLYTICS_COLLECTION_ENABLED = "firebase_crashlytics_collection_enabled";
    private Boolean crashlyticsDataCollectionEnabled;
    i dataCollectionEnabledTask;
    private final i dataCollectionExplicitlyApproved;
    private final g firebaseApp;
    private boolean setInManifest;
    private final SharedPreferences sharedPreferences;
    private final Object taskLock;
    boolean taskResolved;

    public DataCollectionArbiter(g gVar) {
        Object obj = new Object();
        this.taskLock = obj;
        this.dataCollectionEnabledTask = new i();
        this.taskResolved = false;
        this.setInManifest = false;
        this.dataCollectionExplicitlyApproved = new i();
        gVar.a();
        Context context = gVar.f23184a;
        this.firebaseApp = gVar;
        this.sharedPreferences = CommonUtils.getSharedPrefs(context);
        Boolean dataCollectionValueFromSharedPreferences = getDataCollectionValueFromSharedPreferences();
        this.crashlyticsDataCollectionEnabled = dataCollectionValueFromSharedPreferences == null ? getDataCollectionValueFromManifest(context) : dataCollectionValueFromSharedPreferences;
        synchronized (obj) {
            try {
                if (isAutomaticDataCollectionEnabled()) {
                    this.dataCollectionEnabledTask.d(null);
                    this.taskResolved = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private Boolean getDataCollectionValueFromManifest(Context context) {
        Boolean crashlyticsDataCollectionEnabledFromManifest = readCrashlyticsDataCollectionEnabledFromManifest(context);
        if (crashlyticsDataCollectionEnabledFromManifest == null) {
            this.setInManifest = false;
            return null;
        }
        this.setInManifest = true;
        return Boolean.valueOf(Boolean.TRUE.equals(crashlyticsDataCollectionEnabledFromManifest));
    }

    private Boolean getDataCollectionValueFromSharedPreferences() {
        if (!this.sharedPreferences.contains(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
            return null;
        }
        this.setInManifest = false;
        return Boolean.valueOf(this.sharedPreferences.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, true));
    }

    private boolean isFirebaseDataCollectionDefaultEnabled() {
        boolean z;
        try {
            g gVar = this.firebaseApp;
            gVar.a();
            r5.a aVar = (r5.a) gVar.f23190g.get();
            synchronized (aVar) {
                z = aVar.f21392b;
            }
            return z;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    private void logDataCollectionState(boolean z) {
        Logger.getLogger().d(a2.m("Crashlytics automatic data collection ", z ? "ENABLED" : "DISABLED", " by ", this.crashlyticsDataCollectionEnabled == null ? "global Firebase setting" : this.setInManifest ? "firebase_crashlytics_collection_enabled manifest flag" : "API", "."));
    }

    private static Boolean readCrashlyticsDataCollectionEnabledFromManifest(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
                return null;
            }
            return Boolean.valueOf(applicationInfo.metaData.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED));
        } catch (PackageManager.NameNotFoundException e5) {
            Logger.getLogger().e("Could not read data collection permission from manifest", e5);
            return null;
        }
    }

    private static void storeDataCollectionValueInSharedPreferences(SharedPreferences sharedPreferences, Boolean bool) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (bool != null) {
            editorEdit.putBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, bool.booleanValue());
        } else {
            editorEdit.remove(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED);
        }
        editorEdit.apply();
    }

    public void grantDataCollectionPermission(boolean z) {
        if (!z) {
            throw new IllegalStateException("An invalid data collection token was used.");
        }
        this.dataCollectionExplicitlyApproved.d(null);
    }

    public synchronized boolean isAutomaticDataCollectionEnabled() {
        boolean zBooleanValue;
        try {
            Boolean bool = this.crashlyticsDataCollectionEnabled;
            zBooleanValue = bool != null ? bool.booleanValue() : isFirebaseDataCollectionDefaultEnabled();
            logDataCollectionState(zBooleanValue);
        } catch (Throwable th) {
            throw th;
        }
        return zBooleanValue;
    }

    public synchronized void setCrashlyticsDataCollectionEnabled(Boolean bool) {
        Boolean dataCollectionValueFromManifest;
        if (bool != null) {
            try {
                this.setInManifest = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (bool != null) {
            dataCollectionValueFromManifest = bool;
        } else {
            g gVar = this.firebaseApp;
            gVar.a();
            dataCollectionValueFromManifest = getDataCollectionValueFromManifest(gVar.f23184a);
        }
        this.crashlyticsDataCollectionEnabled = dataCollectionValueFromManifest;
        storeDataCollectionValueInSharedPreferences(this.sharedPreferences, bool);
        synchronized (this.taskLock) {
            try {
                if (isAutomaticDataCollectionEnabled()) {
                    if (!this.taskResolved) {
                        this.dataCollectionEnabledTask.d(null);
                        this.taskResolved = true;
                    }
                } else if (this.taskResolved) {
                    this.dataCollectionEnabledTask = new i();
                    this.taskResolved = false;
                }
            } finally {
            }
        }
    }

    public h waitForAutomaticDataCollectionEnabled() {
        t tVar;
        synchronized (this.taskLock) {
            tVar = this.dataCollectionEnabledTask.f13855a;
        }
        return tVar;
    }

    public h waitForDataCollectionPermission(Executor executor) {
        return Utils.race(executor, this.dataCollectionExplicitlyApproved.f13855a, waitForAutomaticDataCollectionEnabled());
    }
}
