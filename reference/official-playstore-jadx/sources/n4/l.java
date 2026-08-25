package n4;

import com.google.android.gms.auth.api.phone.SmsCodeAutofillClient;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.auth.j4;

/* JADX INFO: loaded from: classes4.dex */
public final class l extends com.google.android.gms.common.api.n implements SmsCodeAutofillClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f20496a = new com.google.android.gms.common.api.i("SmsCodeAutofill.API", new j4(7), new com.google.android.gms.common.api.h());

    @Override // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final com.google.android.gms.tasks.h checkPermissionState() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12809c = new o3.d[]{c.f20486a};
        xVarBuilder.f12807a = new io.sentry.util.l(this, 9);
        xVarBuilder.f12810d = 1564;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final com.google.android.gms.tasks.h hasOngoingSmsRequest(String str) {
        t.i(str);
        t.a("The package name cannot be empty.", !str.isEmpty());
        x xVarBuilder = y.builder();
        xVarBuilder.f12809c = new o3.d[]{c.f20486a};
        xVarBuilder.f12807a = new c2.a(this, str, 3);
        xVarBuilder.f12810d = 1565;
        return doRead(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final com.google.android.gms.tasks.h startSmsCodeRetriever() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12809c = new o3.d[]{c.f20486a};
        xVarBuilder.f12807a = new io.sentry.util.l(this, 10);
        xVarBuilder.f12810d = 1563;
        return doWrite(xVarBuilder.a());
    }
}
