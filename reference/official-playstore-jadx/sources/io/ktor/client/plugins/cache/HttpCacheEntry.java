package io.ktor.client.plugins.cache;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.client.call.SavedHttpCall;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\r\u0010\u001d\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Lio/ktor/client/plugins/cache/HttpCacheEntry;", "", "expires", "Lio/ktor/util/date/GMTDate;", "varyKeys", "", "", "response", "Lio/ktor/client/statement/HttpResponse;", TtmlNode.TAG_BODY, "", "(Lio/ktor/util/date/GMTDate;Ljava/util/Map;Lio/ktor/client/statement/HttpResponse;[B)V", "getBody", "()[B", "getExpires", "()Lio/ktor/util/date/GMTDate;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "responseHeaders", "Lio/ktor/http/Headers;", "getResponseHeaders$ktor_client_core", "()Lio/ktor/http/Headers;", "getVaryKeys", "()Ljava/util/Map;", "equals", "", "other", "hashCode", "", "produceResponse", "produceResponse$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpCacheEntry {
    private final byte[] body;
    private final GMTDate expires;
    private final HttpResponse response;
    private final Headers responseHeaders;
    private final Map<String, String> varyKeys;

    public HttpCacheEntry(GMTDate gMTDate, Map<String, String> map, HttpResponse httpResponse, byte[] bArr) {
        this.expires = gMTDate;
        this.varyKeys = map;
        this.response = httpResponse;
        this.body = bArr;
        Headers.Companion companion = Headers.INSTANCE;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        headersBuilder.appendAll(httpResponse.getHeaders());
        this.responseHeaders = headersBuilder.build();
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof HttpCacheEntry)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        return p.a(this.varyKeys, ((HttpCacheEntry) other).varyKeys);
    }

    public final byte[] getBody() {
        return this.body;
    }

    public final GMTDate getExpires() {
        return this.expires;
    }

    public final HttpResponse getResponse() {
        return this.response;
    }

    /* JADX INFO: renamed from: getResponseHeaders$ktor_client_core, reason: from getter */
    public final Headers getResponseHeaders() {
        return this.responseHeaders;
    }

    public final Map<String, String> getVaryKeys() {
        return this.varyKeys;
    }

    public int hashCode() {
        return this.varyKeys.hashCode();
    }

    public final HttpResponse produceResponse$ktor_client_core() {
        return new SavedHttpCall(this.response.getCall().getClient(), this.response.getCall().getRequest(), this.response, this.body).getResponse();
    }
}
