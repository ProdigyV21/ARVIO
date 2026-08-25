package io.ktor.client.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLUtilsJvmKt;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\u001a:\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a:\u0010\u000b\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\n\u001a:\u0010\f\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a:\u0010\r\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\n\u001a:\u0010\u000e\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\n\u001a:\u0010\u000f\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\n\u001a:\u0010\u0010\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\n\u001a:\u0010\u0011\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\n\u001a:\u0010\u0013\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\n\u001a:\u0010\u0014\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\n\u001a:\u0010\u0015\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\n\u001a:\u0010\u0016\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\n\u001a:\u0010\u0017\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\n\u001a:\u0010\u0018\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\n\u001a:\u0010\u0019\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\n\u001a:\u0010\u001a\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lio/ktor/client/HttpClient;", "Ljava/net/URL;", "url", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/client/statement/HttpResponse;", "request", "(Lio/ktor/client/HttpClient;Ljava/net/URL;Lr7/l;Ld7/d;)Ljava/lang/Object;", "get", "post", "put", "patch", "options", TtmlNode.TAG_HEAD, "delete", "Lio/ktor/client/statement/HttpStatement;", "prepareRequest", "prepareGet", "preparePost", "preparePut", "preparePatch", "prepareOptions", "prepareHead", "prepareDelete", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuildersJvmKt {

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$delete$2, reason: invalid class name */
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$get$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17572 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17572 INSTANCE = new C17572();

        public C17572() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$head$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17582 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17582 INSTANCE = new C17582();

        public C17582() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$options$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17592 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17592 INSTANCE = new C17592();

        public C17592() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$patch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17602 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17602 INSTANCE = new C17602();

        public C17602() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$post$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17612 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17612 INSTANCE = new C17612();

        public C17612() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$prepareDelete$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17622 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17622 INSTANCE = new C17622();

        public C17622() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$prepareGet$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17632 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17632 INSTANCE = new C17632();

        public C17632() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$prepareHead$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17642 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17642 INSTANCE = new C17642();

        public C17642() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$prepareOptions$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17652 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17652 INSTANCE = new C17652();

        public C17652() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$preparePatch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17662 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17662 INSTANCE = new C17662();

        public C17662() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$preparePost$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17672 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17672 INSTANCE = new C17672();

        public C17672() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$preparePut$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17682 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17682 INSTANCE = new C17682();

        public C17682() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$prepareRequest$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17692 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17692 INSTANCE = new C17692();

        public C17692() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$put$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17702 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17702 INSTANCE = new C17702();

        public C17702() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersJvmKt$request$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17712 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17712 INSTANCE = new C17712();

        public C17712() {
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

    public static final Object delete(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object delete$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return delete(httpClient, url, lVar, dVar);
    }

    public static final Object get(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object get$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17572.INSTANCE;
        }
        return get(httpClient, url, lVar, dVar);
    }

    public static final Object head(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object head$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17582.INSTANCE;
        }
        return head(httpClient, url, lVar, dVar);
    }

    public static final Object options(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object options$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17592.INSTANCE;
        }
        return options(httpClient, url, lVar, dVar);
    }

    public static final Object patch(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object patch$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17602.INSTANCE;
        }
        return patch(httpClient, url, lVar, dVar);
    }

    public static final Object post(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object post$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17612.INSTANCE;
        }
        return post(httpClient, url, lVar, dVar);
    }

    public static final Object prepareDelete(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareDelete$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17622.INSTANCE;
        }
        return prepareDelete(httpClient, url, lVar, dVar);
    }

    public static final Object prepareGet(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareGet$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17632.INSTANCE;
        }
        return prepareGet(httpClient, url, lVar, dVar);
    }

    public static final Object prepareHead(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareHead$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17642.INSTANCE;
        }
        return prepareHead(httpClient, url, lVar, dVar);
    }

    public static final Object prepareOptions(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareOptions$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17652.INSTANCE;
        }
        return prepareOptions(httpClient, url, lVar, dVar);
    }

    public static final Object preparePatch(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object preparePatch$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17662.INSTANCE;
        }
        return preparePatch(httpClient, url, lVar, dVar);
    }

    public static final Object preparePost(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object preparePost$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17672.INSTANCE;
        }
        return preparePost(httpClient, url, lVar, dVar);
    }

    public static final Object preparePut(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object preparePut$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17682.INSTANCE;
        }
        return preparePut(httpClient, url, lVar, dVar);
    }

    public static final Object prepareRequest(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object prepareRequest$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17692.INSTANCE;
        }
        return prepareRequest(httpClient, url, lVar, dVar);
    }

    public static final Object put(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object put$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17702.INSTANCE;
        }
        return put(httpClient, url, lVar, dVar);
    }

    public static final Object request(HttpClient httpClient, URL url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object request$default(HttpClient httpClient, URL url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17712.INSTANCE;
        }
        return request(httpClient, url, lVar, dVar);
    }
}
