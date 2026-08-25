package io.ktor.client.request;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.Cookie;
import io.ktor.http.CookieKt;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.util.Base64Kt;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a#\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a{\u0010\u0014\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0017\u001a\u00020\u0005*\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0019\u0010\u001b\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001c\u001a!\u0010\u001f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0001¢\u0006\u0004\b\u001f\u0010 \u001a\u0019\u0010\"\u001a\u00020\u0005*\u00020\u00002\u0006\u0010!\u001a\u00020\u0001¢\u0006\u0004\b\"\u0010#\"(\u0010(\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'\"(\u0010-\u001a\u00020\t*\u00020\u00162\u0006\u0010\u0004\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006."}, d2 = {"Lio/ktor/http/HttpMessageBuilder;", "", "key", "", "value", "Lx6/t0;", "header", "(Lio/ktor/http/HttpMessageBuilder;Ljava/lang/String;Ljava/lang/Object;)V", ContentDisposition.Parameters.Name, "", "maxAge", "Lio/ktor/util/date/GMTDate;", "expires", "domain", "path", "", "secure", "httpOnly", "", "extensions", "cookie", "(Lio/ktor/http/HttpMessageBuilder;Ljava/lang/String;Ljava/lang/String;ILio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;)V", "Lio/ktor/client/request/HttpRequestBuilder;", "parameter", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;Ljava/lang/Object;)V", "Lio/ktor/http/ContentType;", "contentType", "accept", "(Lio/ktor/http/HttpMessageBuilder;Lio/ktor/http/ContentType;)V", "username", "password", "basicAuth", "(Lio/ktor/http/HttpMessageBuilder;Ljava/lang/String;Ljava/lang/String;)V", "token", "bearerAuth", "(Lio/ktor/http/HttpMessageBuilder;Ljava/lang/String;)V", "getHost", "(Lio/ktor/client/request/HttpRequestBuilder;)Ljava/lang/String;", "setHost", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;)V", "host", "getPort", "(Lio/ktor/client/request/HttpRequestBuilder;)I", "setPort", "(Lio/ktor/client/request/HttpRequestBuilder;I)V", "port", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    public static final void accept(HttpMessageBuilder httpMessageBuilder, ContentType contentType) {
        httpMessageBuilder.getHeaders().append(HttpHeaders.INSTANCE.getAccept(), contentType.toString());
    }

    public static final void basicAuth(HttpMessageBuilder httpMessageBuilder, String str, String str2) {
        String authorization = HttpHeaders.INSTANCE.getAuthorization();
        StringBuilder sb2 = new StringBuilder("Basic ");
        sb2.append(Base64Kt.encodeBase64(str + ':' + str2));
        header(httpMessageBuilder, authorization, sb2.toString());
    }

    public static final void bearerAuth(HttpMessageBuilder httpMessageBuilder, String str) {
        header(httpMessageBuilder, HttpHeaders.INSTANCE.getAuthorization(), "Bearer ".concat(str));
    }

    public static final void cookie(HttpMessageBuilder httpMessageBuilder, String str, String str2, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map<String, String> map) {
        String strRenderCookieHeader = CookieKt.renderCookieHeader(new Cookie(str, str2, null, i10, gMTDate, str3, str4, z, z5, map, 4, null));
        HeadersBuilder headers = httpMessageBuilder.getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        if (!headers.contains(httpHeaders.getCookie())) {
            httpMessageBuilder.getHeaders().append(httpHeaders.getCookie(), strRenderCookieHeader);
            return;
        }
        httpMessageBuilder.getHeaders().set(httpHeaders.getCookie(), httpMessageBuilder.getHeaders().get(httpHeaders.getCookie()) + "; " + strRenderCookieHeader);
    }

    public static /* synthetic */ void cookie$default(HttpMessageBuilder httpMessageBuilder, String str, String str2, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map map, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            i10 = 0;
        }
        if ((i11 & 8) != 0) {
            gMTDate = null;
        }
        if ((i11 & 16) != 0) {
            str3 = null;
        }
        if ((i11 & 32) != 0) {
            str4 = null;
        }
        if ((i11 & 64) != 0) {
            z = false;
        }
        if ((i11 & 128) != 0) {
            z5 = false;
        }
        if ((i11 & 256) != 0) {
            map = a0.f19683i;
        }
        cookie(httpMessageBuilder, str, str2, i10, gMTDate, str3, str4, z, z5, map);
    }

    public static final String getHost(HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.getUrl().getHost();
    }

    public static final int getPort(HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.getUrl().getPort();
    }

    public static final void header(HttpMessageBuilder httpMessageBuilder, String str, Object obj) {
        if (obj != null) {
            httpMessageBuilder.getHeaders().append(str, obj.toString());
        }
    }

    public static final void parameter(HttpRequestBuilder httpRequestBuilder, String str, Object obj) {
        if (obj != null) {
            httpRequestBuilder.getUrl().getParameters().append(str, obj.toString());
        }
    }

    public static final void setHost(HttpRequestBuilder httpRequestBuilder, String str) {
        httpRequestBuilder.getUrl().setHost(str);
    }

    public static final void setPort(HttpRequestBuilder httpRequestBuilder, int i10) {
        httpRequestBuilder.getUrl().setPort(i10);
    }
}
