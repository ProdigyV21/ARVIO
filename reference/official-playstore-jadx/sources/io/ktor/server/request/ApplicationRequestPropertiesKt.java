package io.ktor.server.request;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.RangesKt;
import io.ktor.http.RangesSpecifier;
import io.ktor.server.plugins.OriginConnectionPointKt;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u000b\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\f\u0010\f\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\f\u0010\u0013\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\f\u0010\u0016\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0012\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019*\u00020\u0002\u001a\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a\n\u0010\u001d\u001a\u00020\u001e*\u00020\u0002\u001a\n\u0010\u001f\u001a\u00020\u0006*\u00020\u0002\u001a\u0014\u0010 \u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010!\u001a\u00020\u0006\u001a\n\u0010\"\u001a\u00020\u0006*\u00020\u0002\u001a\n\u0010#\u001a\u00020$*\u00020\u0002\u001a\n\u0010%\u001a\u00020$*\u00020\u0002\u001a\f\u0010&\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\n\u0010'\u001a\u00020\u0006*\u00020\u0002\u001a\n\u0010(\u001a\u00020)*\u00020\u0002\u001a\n\u0010*\u001a\u00020\u0006*\u00020\u0002\u001a\f\u0010+\u001a\u0004\u0018\u00010,*\u00020\u0002\u001a\f\u0010-\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006."}, d2 = {"httpMethod", "Lio/ktor/http/HttpMethod;", "Lio/ktor/server/request/ApplicationRequest;", "getHttpMethod", "(Lio/ktor/server/request/ApplicationRequest;)Lio/ktor/http/HttpMethod;", "httpVersion", "", "getHttpVersion", "(Lio/ktor/server/request/ApplicationRequest;)Ljava/lang/String;", "uri", "getUri", "accept", "acceptCharset", "acceptCharsetItems", "", "Lio/ktor/http/HeaderValue;", "acceptEncoding", "acceptEncodingItems", "acceptItems", "acceptLanguage", "acceptLanguageItems", "authorization", "cacheControl", "contentCharset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "contentLength", "", "(Lio/ktor/server/request/ApplicationRequest;)Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "document", "header", ContentDisposition.Parameters.Name, "host", "isChunked", "", "isMultipart", "location", "path", "port", "", "queryString", "ranges", "Lio/ktor/http/RangesSpecifier;", "userAgent", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationRequestPropertiesKt {
    public static final String accept(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getAccept());
    }

    public static final String acceptCharset(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getAcceptCharset());
    }

    public static final List<HeaderValue> acceptCharsetItems(ApplicationRequest applicationRequest) {
        return HttpHeaderValueParserKt.parseAndSortHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAcceptCharset()));
    }

    public static final String acceptEncoding(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getAcceptEncoding());
    }

    public static final List<HeaderValue> acceptEncodingItems(ApplicationRequest applicationRequest) {
        return HttpHeaderValueParserKt.parseAndSortHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAcceptEncoding()));
    }

    public static final List<HeaderValue> acceptItems(ApplicationRequest applicationRequest) {
        return HttpHeaderValueParserKt.parseAndSortContentTypeHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAccept()));
    }

    public static final String acceptLanguage(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getAcceptLanguage());
    }

    public static final List<HeaderValue> acceptLanguageItems(ApplicationRequest applicationRequest) {
        return HttpHeaderValueParserKt.parseAndSortHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAcceptLanguage()));
    }

    public static final String authorization(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getAuthorization());
    }

    public static final String cacheControl(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getCacheControl());
    }

    public static final Charset contentCharset(ApplicationRequest applicationRequest) {
        return ContentTypesKt.charset(contentType(applicationRequest));
    }

    public static final Long contentLength(ApplicationRequest applicationRequest) {
        String strHeader = header(applicationRequest, HttpHeaders.INSTANCE.getContentLength());
        if (strHeader != null) {
            return u.S(strHeader);
        }
        return null;
    }

    public static final ContentType contentType(ApplicationRequest applicationRequest) {
        ContentType contentType;
        String strHeader = header(applicationRequest, HttpHeaders.INSTANCE.getContentType());
        return (strHeader == null || (contentType = ContentType.INSTANCE.parse(strHeader)) == null) ? ContentType.INSTANCE.getAny() : contentType;
    }

    public static final String document(ApplicationRequest applicationRequest) {
        String strPath = path(applicationRequest);
        return o.E0('/', strPath, strPath);
    }

    public static final HttpMethod getHttpMethod(ApplicationRequest applicationRequest) {
        return OriginConnectionPointKt.getOrigin(applicationRequest).getMethod();
    }

    public static final String getHttpVersion(ApplicationRequest applicationRequest) {
        return OriginConnectionPointKt.getOrigin(applicationRequest).getVersion();
    }

    public static final String getUri(ApplicationRequest applicationRequest) {
        return OriginConnectionPointKt.getOrigin(applicationRequest).getUri();
    }

    public static final String header(ApplicationRequest applicationRequest, String str) {
        return applicationRequest.getHeaders().get(str);
    }

    public static final String host(ApplicationRequest applicationRequest) {
        return OriginConnectionPointKt.getOrigin(applicationRequest).getServerHost();
    }

    public static final boolean isChunked(ApplicationRequest applicationRequest) {
        String strHeader = header(applicationRequest, HttpHeaders.INSTANCE.getTransferEncoding());
        return strHeader != null && strHeader.compareToIgnoreCase("chunked") == 0;
    }

    public static final boolean isMultipart(ApplicationRequest applicationRequest) {
        return contentType(applicationRequest).match(ContentType.MultiPart.INSTANCE.getAny());
    }

    public static final String location(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getLocation());
    }

    public static final String path(ApplicationRequest applicationRequest) {
        String uri = OriginConnectionPointKt.getOrigin(applicationRequest).getUri();
        return o.G0('?', uri, uri);
    }

    public static final int port(ApplicationRequest applicationRequest) {
        return OriginConnectionPointKt.getOrigin(applicationRequest).getServerPort();
    }

    public static final String queryString(ApplicationRequest applicationRequest) {
        return o.C0('?', OriginConnectionPointKt.getOrigin(applicationRequest).getUri(), "");
    }

    public static final RangesSpecifier ranges(ApplicationRequest applicationRequest) {
        String strHeader = header(applicationRequest, HttpHeaders.INSTANCE.getRange());
        if (strHeader != null) {
            return RangesKt.parseRangesSpecifier(strHeader);
        }
        return null;
    }

    public static final String userAgent(ApplicationRequest applicationRequest) {
        return header(applicationRequest, HttpHeaders.INSTANCE.getUserAgent());
    }
}
