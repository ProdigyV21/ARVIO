package io.ktor.client.request;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.util.AttributesKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a*\u0010\u0006\u001a\u00020\u0002*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0019\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\f\u001a*\u0010\u000e\u001a\u00020\u0003*\u00020\b2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\n\u001a\u00020\u0010¢\u0006\u0004\b\u000b\u0010\u0011\u001a-\u0010\u0013\u001a\u00020\b*\u00020\u00122\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\\\u0010\u000e\u001a\u00020\u0003*\u00020\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u000e\u0010\u001b\u001a_\u0010\u0013\u001a\u00020\b*\u00020\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u001c\u001a\u0019\u0010\u000e\u001a\u00020\u0003*\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0015¢\u0006\u0004\b\u000e\u0010\u001e\u001a\u0013\u0010 \u001a\u00020\u001f*\u00020\u0010H\u0007¢\u0006\u0004\b \u0010!¨\u0006\""}, d2 = {"Lio/ktor/http/HttpMessageBuilder;", "Lkotlin/Function1;", "Lio/ktor/http/HeadersBuilder;", "Lx6/t0;", "Lx6/n;", "block", "headers", "(Lio/ktor/http/HttpMessageBuilder;Lr7/l;)Lio/ktor/http/HeadersBuilder;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/request/HttpRequest;", "request", "takeFrom", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/request/HttpRequest;)Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/http/URLBuilder;", "url", "(Lio/ktor/client/request/HttpRequestBuilder;Lr7/l;)V", "Lio/ktor/client/request/HttpRequestData;", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/request/HttpRequestData;)Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/request/HttpRequestBuilder$Companion;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder$Companion;Lr7/l;)Lio/ktor/client/request/HttpRequestBuilder;", "", "scheme", "host", "", "port", "path", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lr7/l;)V", "(Lio/ktor/client/request/HttpRequestBuilder$Companion;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lr7/l;)Lio/ktor/client/request/HttpRequestBuilder;", "urlString", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;)V", "", "isUpgradeRequest", "(Lio/ktor/client/request/HttpRequestData;)Z", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestKt {

    /* JADX INFO: renamed from: io.ktor.client.request.HttpRequestKt$invoke$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/URLBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/URLBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<URLBuilder, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(URLBuilder uRLBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((URLBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.HttpRequestKt$url$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/URLBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/URLBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<URLBuilder, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(URLBuilder uRLBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((URLBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final HeadersBuilder headers(HttpMessageBuilder httpMessageBuilder, l<? super HeadersBuilder, t0> lVar) {
        HeadersBuilder headers = httpMessageBuilder.getHeaders();
        lVar.invoke(headers);
        return headers;
    }

    public static final HttpRequestBuilder invoke(HttpRequestBuilder.Companion companion, l<? super URLBuilder, t0> lVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        url(httpRequestBuilder, lVar);
        return httpRequestBuilder;
    }

    public static /* synthetic */ HttpRequestBuilder invoke$default(HttpRequestBuilder.Companion companion, String str, String str2, Integer num, String str3, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return invoke(companion, str, str2, num, str3, lVar);
    }

    @InternalAPI
    public static final boolean isUpgradeRequest(HttpRequestData httpRequestData) {
        return httpRequestData.getBody() instanceof ClientUpgradeContent;
    }

    public static final HttpRequestBuilder takeFrom(HttpRequestBuilder httpRequestBuilder, HttpRequest httpRequest) {
        httpRequestBuilder.setMethod(httpRequest.getMethod());
        httpRequestBuilder.setBody(httpRequest.getContent());
        httpRequestBuilder.setBodyType((TypeInfo) httpRequestBuilder.getAttributes().getOrNull(RequestBodyKt.getBodyTypeAttributeKey()));
        URLUtilsKt.takeFrom(httpRequestBuilder.getUrl(), httpRequest.getUrl());
        httpRequestBuilder.getHeaders().appendAll(httpRequest.getHeaders());
        AttributesKt.putAll(httpRequestBuilder.getAttributes(), httpRequest.getAttributes());
        return httpRequestBuilder;
    }

    public static final void url(HttpRequestBuilder httpRequestBuilder, l<? super URLBuilder, t0> lVar) {
        lVar.invoke(httpRequestBuilder.getUrl());
    }

    public static /* synthetic */ void url$default(HttpRequestBuilder httpRequestBuilder, String str, String str2, Integer num, String str3, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        url(httpRequestBuilder, str, str2, num, str3, lVar);
    }

    public static final HttpRequestBuilder invoke(HttpRequestBuilder.Companion companion, String str, String str2, Integer num, String str3, l<? super URLBuilder, t0> lVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        url(httpRequestBuilder, str, str2, num, str3, lVar);
        return httpRequestBuilder;
    }

    public static final void url(HttpRequestBuilder httpRequestBuilder, String str, String str2, Integer num, String str3, l<? super URLBuilder, t0> lVar) {
        URLBuilderKt.set(httpRequestBuilder.getUrl(), str, str2, num, str3, lVar);
    }

    public static final void url(HttpRequestBuilder httpRequestBuilder, String str) {
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), str);
    }

    public static final HttpRequestBuilder takeFrom(HttpRequestBuilder httpRequestBuilder, HttpRequestData httpRequestData) {
        httpRequestBuilder.setMethod(httpRequestData.getMethod());
        httpRequestBuilder.setBody(httpRequestData.getBody());
        httpRequestBuilder.setBodyType((TypeInfo) httpRequestBuilder.getAttributes().getOrNull(RequestBodyKt.getBodyTypeAttributeKey()));
        URLUtilsKt.takeFrom(httpRequestBuilder.getUrl(), httpRequestData.getUrl());
        httpRequestBuilder.getHeaders().appendAll(httpRequestData.getHeaders());
        AttributesKt.putAll(httpRequestBuilder.getAttributes(), httpRequestData.getAttributes());
        return httpRequestBuilder;
    }
}
