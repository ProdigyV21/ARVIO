package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.UnsafeHeaderException;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H$¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH$¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H$¢\u0006\u0004\b\u001a\u0010\rR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b8\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lio/ktor/server/response/ResponseHeaders;", "", "<init>", "()V", "", ContentDisposition.Parameters.Name, "", "contains", "(Ljava/lang/String;)Z", "get", "(Ljava/lang/String;)Ljava/lang/String;", "", "values", "(Ljava/lang/String;)Ljava/util/List;", "Lio/ktor/http/Headers;", "allValues", "()Lio/ktor/http/Headers;", "value", "safeOnly", "Lx6/t0;", "append", "(Ljava/lang/String;Ljava/lang/String;Z)V", "engineAppendHeader", "(Ljava/lang/String;Ljava/lang/String;)V", "getEngineHeaderNames", "()Ljava/util/List;", "getEngineHeaderValues", "", "managedByEngineHeaders", "Ljava/util/Set;", "getManagedByEngineHeaders", "()Ljava/util/Set;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ResponseHeaders {
    private final Set<String> managedByEngineHeaders = b0.f19686i;

    public static /* synthetic */ void append$default(ResponseHeaders responseHeaders, String str, String str2, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: append");
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        responseHeaders.append(str, str2, z);
    }

    public final Headers allValues() {
        Headers.Companion companion = Headers.INSTANCE;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        for (String str : x.g1(getEngineHeaderNames())) {
            headersBuilder.appendAll(str, getEngineHeaderValues(str));
        }
        return headersBuilder.build();
    }

    public final void append(String name, String value, boolean safeOnly) {
        if (getManagedByEngineHeaders().contains(name)) {
            return;
        }
        if (safeOnly && HttpHeaders.INSTANCE.isUnsafe(name)) {
            throw new UnsafeHeaderException(name);
        }
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        httpHeaders.checkHeaderName(name);
        httpHeaders.checkHeaderValue(value);
        engineAppendHeader(name, value);
    }

    public final boolean contains(String name) {
        return get(name) != null;
    }

    public abstract void engineAppendHeader(String name, String value);

    public String get(String name) {
        return (String) x.o0(getEngineHeaderValues(name));
    }

    public abstract List<String> getEngineHeaderNames();

    public abstract List<String> getEngineHeaderValues(String name);

    public Set<String> getManagedByEngineHeaders() {
        return this.managedByEngineHeaders;
    }

    public final List<String> values(String name) {
        return getEngineHeaderValues(name);
    }
}
