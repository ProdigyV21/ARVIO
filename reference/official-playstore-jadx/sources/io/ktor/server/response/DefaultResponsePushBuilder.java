package io.ktor.server.response;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLBuilder;
import io.ktor.http.content.Version;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.util.URLBuilderKt;
import io.ktor.util.InternalAPI;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB3\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R*\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0019j\b\u0012\u0004\u0012\u00020\u000f`\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lio/ktor/server/response/DefaultResponsePushBuilder;", "Lio/ktor/server/response/ResponsePushBuilder;", "url", "Lio/ktor/http/URLBuilder;", "headers", "Lio/ktor/http/Headers;", "(Lio/ktor/http/URLBuilder;Lio/ktor/http/Headers;)V", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "method", "Lio/ktor/http/HttpMethod;", "Lio/ktor/http/HeadersBuilder;", "versions", "", "Lio/ktor/http/content/Version;", "(Lio/ktor/http/HttpMethod;Lio/ktor/http/URLBuilder;Lio/ktor/http/HeadersBuilder;Ljava/util/List;)V", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", "getMethod", "()Lio/ktor/http/HttpMethod;", "setMethod", "(Lio/ktor/http/HttpMethod;)V", "getUrl", "()Lio/ktor/http/URLBuilder;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getVersions", "()Ljava/util/ArrayList;", "setVersions", "(Ljava/util/ArrayList;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@UseHttp2Push
public final class DefaultResponsePushBuilder implements ResponsePushBuilder {
    private final HeadersBuilder headers;
    private HttpMethod method;
    private final URLBuilder url;
    private ArrayList<Version> versions;

    public DefaultResponsePushBuilder() {
        this(null, null, null, null, 15, null);
    }

    @Override // io.ktor.server.response.ResponsePushBuilder
    public HeadersBuilder getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.server.response.ResponsePushBuilder
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // io.ktor.server.response.ResponsePushBuilder
    public URLBuilder getUrl() {
        return this.url;
    }

    @Override // io.ktor.server.response.ResponsePushBuilder
    public void setMethod(HttpMethod httpMethod) {
        this.method = httpMethod;
    }

    public void setVersions(ArrayList<Version> arrayList) {
        this.versions = arrayList;
    }

    public DefaultResponsePushBuilder(HttpMethod httpMethod, URLBuilder uRLBuilder, HeadersBuilder headersBuilder, List<? extends Version> list) {
        this.method = httpMethod;
        this.url = uRLBuilder;
        this.headers = headersBuilder;
        this.versions = list.isEmpty() ? new ArrayList<>() : new ArrayList<>(list);
    }

    @Override // io.ktor.server.response.ResponsePushBuilder
    public ArrayList<Version> getVersions() {
        return this.versions;
    }

    public /* synthetic */ DefaultResponsePushBuilder(HttpMethod httpMethod, URLBuilder uRLBuilder, HeadersBuilder headersBuilder, List list, int i10, h hVar) {
        this((i10 & 1) != 0 ? HttpMethod.INSTANCE.getGet() : httpMethod, (i10 & 2) != 0 ? new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null) : uRLBuilder, (i10 & 4) != 0 ? new HeadersBuilder(0, 1, null) : headersBuilder, (i10 & 8) != 0 ? z.f19728i : list);
    }

    public DefaultResponsePushBuilder(URLBuilder uRLBuilder, Headers headers) {
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        headersBuilder.appendAll(headers);
        this(null, uRLBuilder, headersBuilder, null, 9, null);
    }

    public DefaultResponsePushBuilder(ApplicationCall applicationCall) {
        URLBuilder.Companion companion = URLBuilder.INSTANCE;
        URLBuilder uRLBuilderCreateFromCall = URLBuilderKt.createFromCall(companion, applicationCall);
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        headersBuilder.appendAll(applicationCall.getRequest().getHeaders());
        headersBuilder.set(HttpHeaders.INSTANCE.getReferrer(), URLBuilderKt.createFromCall(companion, applicationCall).buildString());
        this(null, uRLBuilderCreateFromCall, headersBuilder, null, 9, null);
    }
}
