package io.ktor.client.plugins.cache.storage;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.Url;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J)\u0010$\u001a\u00020\u00002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u000b\u001a\u00020\u0007H\u0000¢\u0006\u0002\b%J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010)\u001a\u00020*H\u0016R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006+"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "", "url", "Lio/ktor/http/Url;", "statusCode", "Lio/ktor/http/HttpStatusCode;", "requestTime", "Lio/ktor/util/date/GMTDate;", "responseTime", "version", "Lio/ktor/http/HttpProtocolVersion;", "expires", "headers", "Lio/ktor/http/Headers;", "varyKeys", "", "", TtmlNode.TAG_BODY, "", "(Lio/ktor/http/Url;Lio/ktor/http/HttpStatusCode;Lio/ktor/util/date/GMTDate;Lio/ktor/util/date/GMTDate;Lio/ktor/http/HttpProtocolVersion;Lio/ktor/util/date/GMTDate;Lio/ktor/http/Headers;Ljava/util/Map;[B)V", "getBody", "()[B", "getExpires", "()Lio/ktor/util/date/GMTDate;", "getHeaders", "()Lio/ktor/http/Headers;", "getRequestTime", "getResponseTime", "getStatusCode", "()Lio/ktor/http/HttpStatusCode;", "getUrl", "()Lio/ktor/http/Url;", "getVaryKeys", "()Ljava/util/Map;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "copy", "copy$ktor_client_core", "equals", "", "other", "hashCode", "", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CachedResponseData {
    private final byte[] body;
    private final GMTDate expires;
    private final Headers headers;
    private final GMTDate requestTime;
    private final GMTDate responseTime;
    private final HttpStatusCode statusCode;
    private final Url url;
    private final Map<String, String> varyKeys;
    private final HttpProtocolVersion version;

    public CachedResponseData(Url url, HttpStatusCode httpStatusCode, GMTDate gMTDate, GMTDate gMTDate2, HttpProtocolVersion httpProtocolVersion, GMTDate gMTDate3, Headers headers, Map<String, String> map, byte[] bArr) {
        this.url = url;
        this.statusCode = httpStatusCode;
        this.requestTime = gMTDate;
        this.responseTime = gMTDate2;
        this.version = httpProtocolVersion;
        this.expires = gMTDate3;
        this.headers = headers;
        this.varyKeys = map;
        this.body = bArr;
    }

    public final CachedResponseData copy$ktor_client_core(Map<String, String> varyKeys, GMTDate expires) {
        return new CachedResponseData(this.url, this.statusCode, this.requestTime, this.responseTime, this.version, expires, this.headers, varyKeys, this.body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CachedResponseData)) {
            return false;
        }
        CachedResponseData cachedResponseData = (CachedResponseData) other;
        return p.a(this.url, cachedResponseData.url) && p.a(this.varyKeys, cachedResponseData.varyKeys);
    }

    public final byte[] getBody() {
        return this.body;
    }

    public final GMTDate getExpires() {
        return this.expires;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final GMTDate getRequestTime() {
        return this.requestTime;
    }

    public final GMTDate getResponseTime() {
        return this.responseTime;
    }

    public final HttpStatusCode getStatusCode() {
        return this.statusCode;
    }

    public final Url getUrl() {
        return this.url;
    }

    public final Map<String, String> getVaryKeys() {
        return this.varyKeys;
    }

    public final HttpProtocolVersion getVersion() {
        return this.version;
    }

    public int hashCode() {
        return this.varyKeys.hashCode() + (this.url.hashCode() * 31);
    }
}
