package io.ktor.http;

import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\r8&X§\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0005\u001a\u0004\b\u0016\u0010\u000fR\u0012\u0010\u0017\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0007R\u0012\u0010\u0019\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0007R\u0012\u0010\u001b\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000fR\u0012\u0010\u001d\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0007R\u0012\u0010\u001f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0007R\u0012\u0010!\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u000fR\u0012\u0010#\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0007R\u0012\u0010%\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0007¨\u0006'"}, d2 = {"Lio/ktor/http/RequestConnectionPoint;", "", "host", "", "getHost$annotations", "()V", "getHost", "()Ljava/lang/String;", "localAddress", "getLocalAddress", "localHost", "getLocalHost", "localPort", "", "getLocalPort", "()I", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "port", "getPort$annotations", "getPort", "remoteAddress", "getRemoteAddress", "remoteHost", "getRemoteHost", "remotePort", "getRemotePort", "scheme", "getScheme", "serverHost", "getServerHost", "serverPort", "getServerPort", "uri", "getUri", "version", "getVersion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface RequestConnectionPoint {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @e
        public static /* synthetic */ void getHost$annotations() {
        }

        @e
        public static /* synthetic */ void getPort$annotations() {
        }
    }

    String getHost();

    String getLocalAddress();

    String getLocalHost();

    int getLocalPort();

    HttpMethod getMethod();

    int getPort();

    String getRemoteAddress();

    String getRemoteHost();

    int getRemotePort();

    String getScheme();

    String getServerHost();

    int getServerPort();

    String getUri();

    String getVersion();
}
