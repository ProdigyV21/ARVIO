package com.google.firebase.crashlytics;

import a5.a;
import android.os.Bundle;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
class CrashlyticsAnalyticsListener implements a.b {
    static final String CRASHLYTICS_ORIGIN = "clx";
    static final String EVENT_NAME_KEY = "name";
    static final String EVENT_ORIGIN_KEY = "_o";
    static final String EVENT_PARAMS_KEY = "params";
    private AnalyticsEventReceiver breadcrumbEventReceiver;
    private AnalyticsEventReceiver crashlyticsOriginEventReceiver;

    private static void notifyEventReceiver(AnalyticsEventReceiver analyticsEventReceiver, String str, Bundle bundle) {
        if (analyticsEventReceiver == null) {
            return;
        }
        analyticsEventReceiver.onEvent(str, bundle);
    }

    private void notifyEventReceivers(String str, Bundle bundle) {
        notifyEventReceiver(CRASHLYTICS_ORIGIN.equals(bundle.getString(EVENT_ORIGIN_KEY)) ? this.crashlyticsOriginEventReceiver : this.breadcrumbEventReceiver, str, bundle);
    }

    public void onMessageTriggered(int i10, Bundle bundle) {
        String string;
        Logger logger = Logger.getLogger();
        Locale locale = Locale.US;
        logger.v("Analytics listener received message. ID: " + i10 + ", Extras: " + bundle);
        if (bundle == null || (string = bundle.getString("name")) == null) {
            return;
        }
        Bundle bundle2 = bundle.getBundle(EVENT_PARAMS_KEY);
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        notifyEventReceivers(string, bundle2);
    }

    public void setBreadcrumbEventReceiver(AnalyticsEventReceiver analyticsEventReceiver) {
        this.breadcrumbEventReceiver = analyticsEventReceiver;
    }

    public void setCrashlyticsOriginEventReceiver(AnalyticsEventReceiver analyticsEventReceiver) {
        this.crashlyticsOriginEventReceiver = analyticsEventReceiver;
    }
}
