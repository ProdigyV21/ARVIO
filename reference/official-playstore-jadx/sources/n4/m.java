package n4;

import com.google.android.gms.auth.api.phone.SmsCodeBrowserClient;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.internal.auth.j4;

/* JADX INFO: loaded from: classes4.dex */
public final class m extends com.google.android.gms.common.api.n implements SmsCodeBrowserClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f20497a = new com.google.android.gms.common.api.i("SmsCodeBrowser.API", new j4(8), new com.google.android.gms.common.api.h());

    @Override // com.google.android.gms.auth.api.phone.SmsCodeBrowserClient
    public final com.google.android.gms.tasks.h startSmsCodeRetriever() {
        x xVarBuilder = y.builder();
        xVarBuilder.f12809c = new o3.d[]{c.f20487b};
        xVarBuilder.f12807a = new io.sentry.util.l(this, 11);
        xVarBuilder.f12810d = 1566;
        return doWrite(xVarBuilder.a());
    }
}
