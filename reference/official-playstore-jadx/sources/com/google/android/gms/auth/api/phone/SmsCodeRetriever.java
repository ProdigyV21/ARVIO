package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.m;
import n4.l;

/* JADX INFO: loaded from: classes4.dex */
public final class SmsCodeRetriever {
    public static final String EXTRA_SMS_CODE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_CODE";
    public static final String EXTRA_SMS_CODE_LINE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_CODE_LINE";
    public static final String EXTRA_STATUS = "com.google.android.gms.auth.api.phone.EXTRA_STATUS";
    public static final String SMS_CODE_RETRIEVED_ACTION = "com.google.android.gms.auth.api.phone.SMS_CODE_RETRIEVED";

    private SmsCodeRetriever() {
    }

    public static SmsCodeAutofillClient getAutofillClient(Activity activity) {
        return new l(activity, activity, l.f20496a, d.f12682a, m.f12824c);
    }

    public static SmsCodeBrowserClient getBrowserClient(Activity activity) {
        return new n4.m(activity, activity, n4.m.f20497a, d.f12682a, m.f12824c);
    }

    public static SmsCodeAutofillClient getAutofillClient(Context context) {
        return new l(context, null, l.f20496a, d.f12682a, m.f12824c);
    }

    public static SmsCodeBrowserClient getBrowserClient(Context context) {
        return new n4.m(context, null, n4.m.f20497a, d.f12682a, m.f12824c);
    }
}
