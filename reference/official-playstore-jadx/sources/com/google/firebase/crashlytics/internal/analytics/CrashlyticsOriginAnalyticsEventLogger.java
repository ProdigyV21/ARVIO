package com.google.firebase.crashlytics.internal.analytics;

import a5.a;
import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public class CrashlyticsOriginAnalyticsEventLogger implements AnalyticsEventLogger {
    static final String FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS = "clx";
    private final a analyticsConnector;

    public CrashlyticsOriginAnalyticsEventLogger(a aVar) {
        this.analyticsConnector = aVar;
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
    public void logEvent(String str, Bundle bundle) {
        this.analyticsConnector.a();
    }
}
