package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.server.http.HttpDateJvmKt;
import j$.time.LocalDateTime;
import j$.time.ZonedDateTime;
import j$.time.temporal.Temporal;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0019\u0010\n\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000b\u001a\u0019\u0010\u000e\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\n\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u0011\u001a\u0019\u0010\u000e\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/server/response/ApplicationResponse;", "", ContentDisposition.Parameters.Name, "j$/time/temporal/Temporal", "date", "Lx6/t0;", "header", "(Lio/ktor/server/response/ApplicationResponse;Ljava/lang/String;Lj$/time/temporal/Temporal;)V", "j$/time/ZonedDateTime", "dateTime", "lastModified", "(Lio/ktor/server/response/ApplicationResponse;Lj$/time/ZonedDateTime;)V", "j$/time/LocalDateTime", "value", "expires", "(Lio/ktor/server/response/ApplicationResponse;Lj$/time/LocalDateTime;)V", "Lio/ktor/http/HeadersBuilder;", "(Lio/ktor/http/HeadersBuilder;Lj$/time/ZonedDateTime;)V", "(Lio/ktor/http/HeadersBuilder;Lj$/time/LocalDateTime;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationResponsePropertiesJvmKt {
    public static final void expires(ApplicationResponse applicationResponse, LocalDateTime localDateTime) {
        header(applicationResponse, HttpHeaders.INSTANCE.getExpires(), localDateTime);
    }

    public static final void header(ApplicationResponse applicationResponse, String str, Temporal temporal) {
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, HttpDateJvmKt.toHttpDateString(temporal), false, 4, null);
    }

    public static final void lastModified(ApplicationResponse applicationResponse, ZonedDateTime zonedDateTime) {
        header(applicationResponse, HttpHeaders.INSTANCE.getLastModified(), zonedDateTime);
    }

    public static final void expires(HeadersBuilder headersBuilder, LocalDateTime localDateTime) {
        headersBuilder.set(HttpHeaders.INSTANCE.getExpires(), HttpDateJvmKt.toHttpDateString(localDateTime));
    }

    public static final void lastModified(HeadersBuilder headersBuilder, ZonedDateTime zonedDateTime) {
        headersBuilder.set(HttpHeaders.INSTANCE.getLastModified(), HttpDateJvmKt.toHttpDateString(zonedDateTime));
    }
}
