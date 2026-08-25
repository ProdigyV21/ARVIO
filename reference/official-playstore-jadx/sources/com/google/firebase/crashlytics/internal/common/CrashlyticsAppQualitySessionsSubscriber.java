package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import x5.e;
import x5.f;

/* JADX INFO: loaded from: classes4.dex */
public class CrashlyticsAppQualitySessionsSubscriber implements f {
    private final CrashlyticsAppQualitySessionsStore appQualitySessionsStore;
    private final DataCollectionArbiter dataCollectionArbiter;

    public CrashlyticsAppQualitySessionsSubscriber(DataCollectionArbiter dataCollectionArbiter, FileStore fileStore) {
        this.dataCollectionArbiter = dataCollectionArbiter;
        this.appQualitySessionsStore = new CrashlyticsAppQualitySessionsStore(fileStore);
    }

    public String getAppQualitySessionId(String str) {
        return this.appQualitySessionsStore.getAppQualitySessionId(str);
    }

    @Override // x5.f
    public x5.d getSessionSubscriberName() {
        return x5.d.f22572i;
    }

    @Override // x5.f
    public boolean isDataCollectionEnabled() {
        return this.dataCollectionArbiter.isAutomaticDataCollectionEnabled();
    }

    @Override // x5.f
    public void onSessionChanged(e eVar) {
        Logger.getLogger().d("App Quality Sessions session changed: " + eVar);
        this.appQualitySessionsStore.rotateAppQualitySessionId(eVar.f22575a);
    }

    public void setSessionId(String str) {
        this.appQualitySessionsStore.rotateSessionId(str);
    }
}
