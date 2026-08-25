package io.ktor.server.response;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentRangeKt;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.RangeUnits;
import kotlin.Metadata;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\b\u001a!\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\t¢\u0006\u0004\b\u0005\u0010\n\u001a\u0019\u0010\u000b\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\u0019\u0010\u000e\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u000e\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u0011\u001a1\u0010\u0016\u001a\u00020\u0004*\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0015\u001a\u00020\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a/\u0010\u0016\u001a\u00020\u0004*\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0015\u001a\u00020\u0018¢\u0006\u0004\b\u0016\u0010\u0019\u001a1\u0010\u0016\u001a\u00020\u0004*\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0015\u001a\u00020\u0001¢\u0006\u0004\b\u0016\u0010\u001a¨\u0006\u001b"}, d2 = {"Lio/ktor/server/response/ApplicationResponse;", "", ContentDisposition.Parameters.Name, "value", "Lx6/t0;", "header", "(Lio/ktor/server/response/ApplicationResponse;Ljava/lang/String;Ljava/lang/String;)V", "", "(Lio/ktor/server/response/ApplicationResponse;Ljava/lang/String;I)V", "", "(Lio/ktor/server/response/ApplicationResponse;Ljava/lang/String;J)V", "etag", "(Lio/ktor/server/response/ApplicationResponse;Ljava/lang/String;)V", "Lio/ktor/http/CacheControl;", "cacheControl", "(Lio/ktor/server/response/ApplicationResponse;Lio/ktor/http/CacheControl;)V", "Lio/ktor/http/HeadersBuilder;", "(Lio/ktor/http/HeadersBuilder;Lio/ktor/http/CacheControl;)V", "Lx7/l;", "range", "fullLength", "unit", "contentRange", "(Lio/ktor/http/HeadersBuilder;Lx7/l;Ljava/lang/Long;Ljava/lang/String;)V", "Lio/ktor/http/RangeUnits;", "(Lio/ktor/server/response/ApplicationResponse;Lx7/l;Ljava/lang/Long;Lio/ktor/http/RangeUnits;)V", "(Lio/ktor/server/response/ApplicationResponse;Lx7/l;Ljava/lang/Long;Ljava/lang/String;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationResponsePropertiesKt {
    public static final void cacheControl(ApplicationResponse applicationResponse, CacheControl cacheControl) {
        header(applicationResponse, HttpHeaders.INSTANCE.getCacheControl(), cacheControl.toString());
    }

    public static final void contentRange(HeadersBuilder headersBuilder, l lVar, Long l10, String str) {
        headersBuilder.append(HttpHeaders.INSTANCE.getContentRange(), ContentRangeKt.contentRangeHeaderValue(lVar, l10, str));
    }

    public static /* synthetic */ void contentRange$default(HeadersBuilder headersBuilder, l lVar, Long l10, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            l10 = null;
        }
        if ((i10 & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        contentRange(headersBuilder, lVar, l10, str);
    }

    public static final void etag(ApplicationResponse applicationResponse, String str) {
        header(applicationResponse, HttpHeaders.INSTANCE.getETag(), str);
    }

    public static final void header(ApplicationResponse applicationResponse, String str, String str2) {
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, str2, false, 4, null);
    }

    public static final void cacheControl(HeadersBuilder headersBuilder, CacheControl cacheControl) {
        headersBuilder.set(HttpHeaders.INSTANCE.getCacheControl(), cacheControl.toString());
    }

    public static final void contentRange(ApplicationResponse applicationResponse, l lVar, Long l10, RangeUnits rangeUnits) {
        contentRange(applicationResponse, lVar, l10, rangeUnits.getUnitToken());
    }

    public static final void header(ApplicationResponse applicationResponse, String str, int i10) {
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, String.valueOf(i10), false, 4, null);
    }

    public static final void contentRange(ApplicationResponse applicationResponse, l lVar, Long l10, String str) {
        header(applicationResponse, HttpHeaders.INSTANCE.getContentRange(), ContentRangeKt.contentRangeHeaderValue(lVar, l10, str));
    }

    public static /* synthetic */ void contentRange$default(ApplicationResponse applicationResponse, l lVar, Long l10, RangeUnits rangeUnits, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            l10 = null;
        }
        contentRange(applicationResponse, lVar, l10, rangeUnits);
    }

    public static final void header(ApplicationResponse applicationResponse, String str, long j10) {
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, String.valueOf(j10), false, 4, null);
    }

    public static /* synthetic */ void contentRange$default(ApplicationResponse applicationResponse, l lVar, Long l10, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            l10 = null;
        }
        if ((i10 & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        contentRange(applicationResponse, lVar, l10, str);
    }
}
