package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import n4.b;

/* JADX INFO: loaded from: classes4.dex */
public final class SmsRetriever {
    public static final String EXTRA_CONSENT_INTENT = "com.google.android.gms.auth.api.phone.EXTRA_CONSENT_INTENT";
    public static final String EXTRA_SIM_SUBSCRIPTION_ID = "com.google.android.gms.auth.api.phone.EXTRA_SIM_SUBSCRIPTION_ID";
    public static final String EXTRA_SMS_MESSAGE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE";
    public static final String EXTRA_STATUS = "com.google.android.gms.auth.api.phone.EXTRA_STATUS";
    public static final String SEND_PERMISSION = "com.google.android.gms.auth.api.phone.permission.SEND";
    public static final String SMS_RETRIEVED_ACTION = "com.google.android.gms.auth.api.phone.SMS_RETRIEVED";

    private SmsRetriever() {
    }

    public static SmsRetrieverClient getClient(Activity activity) {
        return new b(activity);
    }

    public static SmsRetrieverClient getClient(Context context) {
        return new b(context);
    }
}
