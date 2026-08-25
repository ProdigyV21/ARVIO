package n4;

import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends SmsRetrieverClient {
    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverClient, com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public final com.google.android.gms.tasks.h startSmsRetriever() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new io.sentry.util.l(this, 12);
        xVarBuilder.f12809c = new o3.d[]{c.f20488c};
        xVarBuilder.f12810d = 1567;
        return doWrite(xVarBuilder.a());
    }

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverClient, com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public final com.google.android.gms.tasks.h startSmsUserConsent(String str) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12807a = new c2.a(this, str, 4);
        xVarBuilder.f12809c = new o3.d[]{c.f20489d};
        xVarBuilder.f12810d = 1568;
        return doWrite(xVarBuilder.a());
    }
}
