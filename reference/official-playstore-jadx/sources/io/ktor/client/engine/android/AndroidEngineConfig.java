package io.ktor.client.engine.android;

import io.ktor.client.engine.HttpClientEngineConfig;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR.\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R3\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u001a\u0010\u0014\"\u0004\b\u001b\u0010\u0016¨\u0006\u001c"}, d2 = {"Lio/ktor/client/engine/android/AndroidEngineConfig;", "Lio/ktor/client/engine/HttpClientEngineConfig;", "<init>", "()V", "", "connectTimeout", "I", "getConnectTimeout", "()I", "setConnectTimeout", "(I)V", "socketTimeout", "getSocketTimeout", "setSocketTimeout", "Lkotlin/Function1;", "Ljavax/net/ssl/HttpsURLConnection;", "Lx6/t0;", "sslManager", "Lr7/l;", "getSslManager", "()Lr7/l;", "setSslManager", "(Lr7/l;)V", "Ljava/net/HttpURLConnection;", "Lx6/n;", "requestConfig", "getRequestConfig", "setRequestConfig", "ktor-client-android"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidEngineConfig extends HttpClientEngineConfig {
    private int connectTimeout = 100000;
    private int socketTimeout = 100000;
    private l<? super HttpsURLConnection, t0> sslManager = AndroidEngineConfig$sslManager$1.INSTANCE;
    private l<? super HttpURLConnection, t0> requestConfig = AndroidEngineConfig$requestConfig$1.INSTANCE;

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    public final l<HttpURLConnection, t0> getRequestConfig() {
        return this.requestConfig;
    }

    public final int getSocketTimeout() {
        return this.socketTimeout;
    }

    public final l<HttpsURLConnection, t0> getSslManager() {
        return this.sslManager;
    }

    public final void setConnectTimeout(int i10) {
        this.connectTimeout = i10;
    }

    public final void setRequestConfig(l<? super HttpURLConnection, t0> lVar) {
        this.requestConfig = lVar;
    }

    public final void setSocketTimeout(int i10) {
        this.socketTimeout = i10;
    }

    public final void setSslManager(l<? super HttpsURLConnection, t0> lVar) {
        this.sslManager = lVar;
    }
}
