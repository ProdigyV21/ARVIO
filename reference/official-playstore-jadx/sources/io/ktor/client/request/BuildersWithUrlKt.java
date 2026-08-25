package io.ktor.client.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import g8.b;
import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a:\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a:\u0010\f\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a:\u0010\r\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\r\u0010\n\u001a:\u0010\u000e\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\n\u001a:\u0010\u000f\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\n\u001a:\u0010\u0010\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\n\u001a:\u0010\u0011\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\n\u001a:\u0010\u0012\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a:\u0010\u0013\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\n\u001a:\u0010\u0014\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\n\u001a:\u0010\u0015\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\n\u001a:\u0010\u0016\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\n\u001a:\u0010\u0017\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\n\u001a:\u0010\u0018\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\n\u001a\u0019\u0010\u0002\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0002\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/client/HttpClient;", "Lio/ktor/http/Url;", "url", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/client/statement/HttpResponse;", "get", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Url;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpStatement;", "prepareGet", "post", "preparePost", "put", "preparePut", "patch", "preparePatch", "options", "prepareOptions", TtmlNode.TAG_HEAD, "prepareHead", "delete", "prepareDelete", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/http/Url;)V", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuildersWithUrlKt {

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$delete$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<HttpRequestBuilder, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$get$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17882 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17882 INSTANCE = new C17882();

        public C17882() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$head$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17892 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17892 INSTANCE = new C17892();

        public C17892() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$options$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17902 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17902 INSTANCE = new C17902();

        public C17902() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$patch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17912 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17912 INSTANCE = new C17912();

        public C17912() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$post$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17922 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17922 INSTANCE = new C17922();

        public C17922() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$prepareDelete$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17932 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17932 INSTANCE = new C17932();

        public C17932() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$prepareGet$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17942 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17942 INSTANCE = new C17942();

        public C17942() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$prepareHead$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17952 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17952 INSTANCE = new C17952();

        public C17952() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$prepareOptions$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17962 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17962 INSTANCE = new C17962();

        public C17962() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$preparePatch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17972 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17972 INSTANCE = new C17972();

        public C17972() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$preparePost$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17982 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17982 INSTANCE = new C17982();

        public C17982() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$preparePut$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17992 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17992 INSTANCE = new C17992();

        public C17992() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersWithUrlKt$put$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18002 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18002 INSTANCE = new C18002();

        public C18002() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final Object delete(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object delete$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object delete$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object get(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object get$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object get$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17882.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object head(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object head$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object head$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17892.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object options(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object options$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object options$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17902.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object patch(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object patch$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object patch$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17912.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object post(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object post$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object post$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17922.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object prepareDelete(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareDelete$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareDelete$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17932.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareGet(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareGet$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareGet$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17942.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareHead(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareHead$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareHead$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17952.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareOptions(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareOptions$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareOptions$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17962.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object preparePatch(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object preparePatch$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object preparePatch$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17972.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object preparePost(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object preparePost$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object preparePost$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17982.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object preparePut(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object preparePut$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object preparePut$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17992.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object put(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object put$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object put$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C18002.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        b.k(httpRequestBuilder, url, lVar, httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final void url(HttpRequestBuilder httpRequestBuilder, Url url) {
        URLUtilsKt.takeFrom(httpRequestBuilder.getUrl(), url);
    }
}
