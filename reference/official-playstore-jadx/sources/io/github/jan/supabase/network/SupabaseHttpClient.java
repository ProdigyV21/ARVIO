package io.github.jan.supabase.network;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH¦@¢\u0006\u0004\b\f\u0010\rJ1\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH¦@¢\u0006\u0004\b\u000f\u0010\rJ3\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u0010\u0010\rJ3\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u0011\u0010\rJM\u0010\u0011\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u0011\u0010\u0016JC\u0010\u0017\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u0017\u0010\u0018J3\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u0019\u0010\rJM\u0010\u0019\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u0019\u0010\u0016JC\u0010\u001a\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001a\u0010\u0018J3\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001b\u0010\rJM\u0010\u001b\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001b\u0010\u0016JC\u0010\u001c\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001c\u0010\u0018J3\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001d\u0010\rJM\u0010\u001d\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001d\u0010\u0016JC\u0010\u001e\u001a\u00020\u000b\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00028\u00002\u0019\b\u0006\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\u001e\u0010\u0018¨\u0006\u001f"}, d2 = {"Lio/github/jan/supabase/network/SupabaseHttpClient;", "", "<init>", "()V", "", "url", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "Lio/ktor/client/statement/HttpResponse;", "request", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpStatement;", "prepareRequest", "get", "post", "T", TtmlNode.TAG_BODY, "Lio/ktor/http/ContentType;", "contentType", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/ContentType;Lr7/l;Ld7/d;)Ljava/lang/Object;", "postJson", "(Ljava/lang/String;Ljava/lang/Object;Lr7/l;Ld7/d;)Ljava/lang/Object;", "delete", "deleteJson", "patch", "patchJson", "put", "putJson", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class SupabaseHttpClient {

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$delete$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$delete$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass3 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
            this.$builder.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$delete$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass5 extends r implements l<HttpRequestBuilder, t0> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$delete$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass6 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ T $body;
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;
        final /* synthetic */ ContentType $contentType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(l<? super HttpRequestBuilder, t0> lVar, ContentType contentType, T t2) {
            super(1);
            this.$builder = lVar;
            this.$contentType = contentType;
            this.$body = t2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
            this.$builder.invoke(httpRequestBuilder);
            HttpMessagePropertiesKt.contentType(httpRequestBuilder, this.$contentType);
            Object obj = this.$body;
            if (obj == null) {
                httpRequestBuilder.setBody(NullBody.INSTANCE);
                p.h();
                throw null;
            }
            if (obj instanceof OutgoingContent) {
                httpRequestBuilder.setBody(obj);
                httpRequestBuilder.setBodyType(null);
            } else {
                httpRequestBuilder.setBody(obj);
                p.h();
                throw null;
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$deleteJson$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17052 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17052 INSTANCE = new C17052();

        public C17052() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$get$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17062 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17062 INSTANCE = new C17062();

        public C17062() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$get$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17073 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17073(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            this.$builder.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$patch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17082 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17082 INSTANCE = new C17082();

        public C17082() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$patch$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17093 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17093(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
            this.$builder.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$patch$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17105 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17105 INSTANCE = new C17105();

        public C17105() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$patch$6, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17116 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ T $body;
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;
        final /* synthetic */ ContentType $contentType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17116(l<? super HttpRequestBuilder, t0> lVar, ContentType contentType, T t2) {
            super(1);
            this.$builder = lVar;
            this.$contentType = contentType;
            this.$body = t2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
            this.$builder.invoke(httpRequestBuilder);
            HttpMessagePropertiesKt.contentType(httpRequestBuilder, this.$contentType);
            Object obj = this.$body;
            if (obj == null) {
                httpRequestBuilder.setBody(NullBody.INSTANCE);
                p.h();
                throw null;
            }
            if (obj instanceof OutgoingContent) {
                httpRequestBuilder.setBody(obj);
                httpRequestBuilder.setBodyType(null);
            } else {
                httpRequestBuilder.setBody(obj);
                p.h();
                throw null;
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$patchJson$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17122 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17122 INSTANCE = new C17122();

        public C17122() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$post$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17132 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17132 INSTANCE = new C17132();

        public C17132() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$post$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17143 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17143(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            this.$builder.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$post$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17155 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17155 INSTANCE = new C17155();

        public C17155() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$post$6, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17166 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ T $body;
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;
        final /* synthetic */ ContentType $contentType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17166(l<? super HttpRequestBuilder, t0> lVar, ContentType contentType, T t2) {
            super(1);
            this.$builder = lVar;
            this.$contentType = contentType;
            this.$body = t2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            this.$builder.invoke(httpRequestBuilder);
            HttpMessagePropertiesKt.contentType(httpRequestBuilder, this.$contentType);
            Object obj = this.$body;
            if (obj == null) {
                httpRequestBuilder.setBody(NullBody.INSTANCE);
                p.h();
                throw null;
            }
            if (obj instanceof OutgoingContent) {
                httpRequestBuilder.setBody(obj);
                httpRequestBuilder.setBodyType(null);
            } else {
                httpRequestBuilder.setBody(obj);
                p.h();
                throw null;
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$postJson$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17172 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17172 INSTANCE = new C17172();

        public C17172() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$put$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17182 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17182 INSTANCE = new C17182();

        public C17182() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$put$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17193 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17193(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
            this.$builder.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$put$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17205 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17205 INSTANCE = new C17205();

        public C17205() {
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

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$put$6, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17216 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ T $body;
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;
        final /* synthetic */ ContentType $contentType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17216(l<? super HttpRequestBuilder, t0> lVar, ContentType contentType, T t2) {
            super(1);
            this.$builder = lVar;
            this.$contentType = contentType;
            this.$body = t2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
            this.$builder.invoke(httpRequestBuilder);
            HttpMessagePropertiesKt.contentType(httpRequestBuilder, this.$contentType);
            Object obj = this.$body;
            if (obj == null) {
                httpRequestBuilder.setBody(NullBody.INSTANCE);
                p.h();
                throw null;
            }
            if (obj instanceof OutgoingContent) {
                httpRequestBuilder.setBody(obj);
                httpRequestBuilder.setBodyType(null);
            } else {
                httpRequestBuilder.setBody(obj);
                p.h();
                throw null;
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseHttpClient$putJson$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17222 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17222 INSTANCE = new C17222();

        public C17222() {
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

    private final Object delete$$forInline(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new AnonymousClass3(lVar), dVar);
    }

    public static /* synthetic */ Object delete$default(SupabaseHttpClient supabaseHttpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return supabaseHttpClient.request(str, new AnonymousClass3(lVar), dVar);
    }

    public static Object deleteJson$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteJson");
        }
        if ((i10 & 4) != 0) {
            C17052 c17052 = C17052.INSTANCE;
        }
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    private final Object get$$forInline(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17073(lVar), dVar);
    }

    public static /* synthetic */ Object get$default(SupabaseHttpClient supabaseHttpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
        }
        if ((i10 & 2) != 0) {
            lVar = C17062.INSTANCE;
        }
        return supabaseHttpClient.request(str, new C17073(lVar), dVar);
    }

    private final Object patch$$forInline(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17093(lVar), dVar);
    }

    public static /* synthetic */ Object patch$default(SupabaseHttpClient supabaseHttpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: patch");
        }
        if ((i10 & 2) != 0) {
            lVar = C17082.INSTANCE;
        }
        return supabaseHttpClient.request(str, new C17093(lVar), dVar);
    }

    public static Object patchJson$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: patchJson");
        }
        if ((i10 & 4) != 0) {
            C17122 c17122 = C17122.INSTANCE;
        }
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    private final Object post$$forInline(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17143(lVar), dVar);
    }

    public static /* synthetic */ Object post$default(SupabaseHttpClient supabaseHttpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: post");
        }
        if ((i10 & 2) != 0) {
            lVar = C17132.INSTANCE;
        }
        return supabaseHttpClient.request(str, new C17143(lVar), dVar);
    }

    public static Object postJson$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postJson");
        }
        if ((i10 & 4) != 0) {
            C17172 c17172 = C17172.INSTANCE;
        }
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    private final Object put$$forInline(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17193(lVar), dVar);
    }

    public static /* synthetic */ Object put$default(SupabaseHttpClient supabaseHttpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: put");
        }
        if ((i10 & 2) != 0) {
            lVar = C17182.INSTANCE;
        }
        return supabaseHttpClient.request(str, new C17193(lVar), dVar);
    }

    public static Object putJson$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: putJson");
        }
        if ((i10 & 4) != 0) {
            C17222 c17222 = C17222.INSTANCE;
        }
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    public final Object delete(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new AnonymousClass3(lVar), dVar);
    }

    public final <T> Object deleteJson(String str, T t2, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    public final Object get(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17073(lVar), dVar);
    }

    public final Object patch(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17093(lVar), dVar);
    }

    public final <T> Object patchJson(String str, T t2, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    public final Object post(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17143(lVar), dVar);
    }

    public final <T> Object postJson(String str, T t2, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    public abstract Object prepareRequest(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar);

    public final Object put(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return request(str, new C17193(lVar), dVar);
    }

    public final <T> Object putJson(String str, T t2, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        ContentType.Application.INSTANCE.getJson();
        p.h();
        throw null;
    }

    public abstract Object request(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar);

    public static Object delete$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, ContentType contentType, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        if ((i10 & 4) != 0) {
            ContentType.INSTANCE.getAny();
        }
        if ((i10 & 8) != 0) {
            AnonymousClass5 anonymousClass5 = AnonymousClass5.INSTANCE;
        }
        p.h();
        throw null;
    }

    public static Object patch$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, ContentType contentType, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: patch");
        }
        if ((i10 & 4) != 0) {
            ContentType.INSTANCE.getAny();
        }
        if ((i10 & 8) != 0) {
            C17105 c17105 = C17105.INSTANCE;
        }
        p.h();
        throw null;
    }

    public static Object post$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, ContentType contentType, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: post");
        }
        if ((i10 & 4) != 0) {
            ContentType.INSTANCE.getAny();
        }
        if ((i10 & 8) != 0) {
            C17155 c17155 = C17155.INSTANCE;
        }
        p.h();
        throw null;
    }

    public static Object put$default(SupabaseHttpClient supabaseHttpClient, String str, Object obj, ContentType contentType, l lVar, d dVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: put");
        }
        if ((i10 & 4) != 0) {
            ContentType.INSTANCE.getAny();
        }
        if ((i10 & 8) != 0) {
            C17205 c17205 = C17205.INSTANCE;
        }
        p.h();
        throw null;
    }

    public final <T> Object delete(String str, T t2, ContentType contentType, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        p.h();
        throw null;
    }

    public final <T> Object patch(String str, T t2, ContentType contentType, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        p.h();
        throw null;
    }

    public final <T> Object post(String str, T t2, ContentType contentType, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        p.h();
        throw null;
    }

    public final <T> Object put(String str, T t2, ContentType contentType, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        p.h();
        throw null;
    }
}
