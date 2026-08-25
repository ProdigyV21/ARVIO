package io.ktor.client.engine.android;

import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Ljava/net/HttpURLConnection;", "Lx6/t0;", "invoke", "(Ljava/net/HttpURLConnection;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidEngineConfig$requestConfig$1 extends r implements l<HttpURLConnection, t0> {
    public static final AndroidEngineConfig$requestConfig$1 INSTANCE = new AndroidEngineConfig$requestConfig$1();

    public AndroidEngineConfig$requestConfig$1() {
        super(1);
    }

    public final void invoke(HttpURLConnection httpURLConnection) {
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpURLConnection) obj);
        return t0.f22605a;
    }
}
