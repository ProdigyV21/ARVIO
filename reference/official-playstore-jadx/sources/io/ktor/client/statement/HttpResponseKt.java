package io.ktor.client.statement;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.client.request.HttpRequest;
import io.ktor.util.InternalAPI;
import java.nio.charset.Charset;
import ka.t;
import ka.u1;
import kotlin.Metadata;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a%\u0010\b\u001a\u00020\u0007*\u00020\u00002\f\b\u0002\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\u000b\u001a\u00020\n*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\"\u0015\u0010\u0010\u001a\u00020\r*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/statement/HttpResponse;", "Lx6/t0;", "complete", "(Lio/ktor/client/statement/HttpResponse;)V", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "fallbackCharset", "", "bodyAsText", "(Lio/ktor/client/statement/HttpResponse;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "bodyAsChannel", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/request/HttpRequest;", "getRequest", "(Lio/ktor/client/statement/HttpResponse;)Lio/ktor/client/request/HttpRequest;", "request", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpResponseKt {

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.HttpResponseKt", f = "HttpResponse.kt", l = {97}, m = "bodyAsChannel")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpResponseKt.bodyAsChannel(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpResponseKt$bodyAsText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.HttpResponseKt", f = "HttpResponse.kt", l = {97}, m = "bodyAsText")
    public static final class C18071 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18071(d<? super C18071> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpResponseKt.bodyAsText(null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object bodyAsChannel(io.ktor.client.statement.HttpResponse r5, d7.d<? super io.ktor.utils.io.ByteReadChannel> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.client.statement.HttpResponseKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1 r0 = (io.ktor.client.statement.HttpResponseKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1 r0 = new io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L53
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r6)
            io.ktor.client.call.HttpClientCall r5 = r5.getCall()
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r6 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.q r1 = kotlin.jvm.internal.l0.a(r6)
            java.lang.reflect.Type r3 = kotlin.reflect.b0.t(r1)
            kotlin.jvm.internal.m0 r4 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r6 = r4.b(r6)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r3, r6, r1)
            r0.label = r2
            java.lang.Object r6 = r5.bodyNullable(r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L53
            return r5
        L53:
            if (r6 == 0) goto L58
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            return r6
        L58:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "null cannot be cast to non-null type io.ktor.utils.io.ByteReadChannel"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpResponseKt.bodyAsChannel(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object bodyAsText(io.ktor.client.statement.HttpResponse r5, java.nio.charset.Charset r6, d7.d<? super java.lang.String> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.client.statement.HttpResponseKt.C18071
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.statement.HttpResponseKt$bodyAsText$1 r0 = (io.ktor.client.statement.HttpResponseKt.C18071) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpResponseKt$bodyAsText$1 r0 = new io.ktor.client.statement.HttpResponseKt$bodyAsText$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.nio.charset.CharsetDecoder r5 = (java.nio.charset.CharsetDecoder) r5
            k2.c.G(r7)
            goto L66
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r7)
            java.nio.charset.Charset r7 = io.ktor.http.HttpMessagePropertiesKt.charset(r5)
            if (r7 != 0) goto L3b
            goto L3c
        L3b:
            r6 = r7
        L3c:
            java.nio.charset.CharsetDecoder r6 = r6.newDecoder()
            io.ktor.client.call.HttpClientCall r5 = r5.getCall()
            java.lang.Class<io.ktor.utils.io.core.ByteReadPacket> r7 = io.ktor.utils.io.core.ByteReadPacket.class
            kotlin.reflect.q r1 = kotlin.jvm.internal.l0.a(r7)
            java.lang.reflect.Type r3 = kotlin.reflect.b0.t(r1)
            kotlin.jvm.internal.m0 r4 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r7 = r4.b(r7)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r3, r7, r1)
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r7 = r5.bodyNullable(r7, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L65
            return r5
        L65:
            r5 = r6
        L66:
            if (r7 == 0) goto L72
            io.ktor.utils.io.core.ByteReadPacket r7 = (io.ktor.utils.io.core.ByteReadPacket) r7
            r6 = 2
            r0 = 0
            r1 = 0
            java.lang.String r5 = io.ktor.utils.io.charsets.EncodingKt.decode$default(r5, r7, r1, r6, r0)
            return r5
        L72:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "null cannot be cast to non-null type io.ktor.utils.io.core.ByteReadPacket"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpResponseKt.bodyAsText(io.ktor.client.statement.HttpResponse, java.nio.charset.Charset, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object bodyAsText$default(HttpResponse httpResponse, Charset charset, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = a.f19924a;
        }
        return bodyAsText(httpResponse, charset, dVar);
    }

    @InternalAPI
    public static final void complete(HttpResponse httpResponse) {
        ((t) httpResponse.getCoroutineContext().get(u1.f19642i)).complete();
    }

    public static final HttpRequest getRequest(HttpResponse httpResponse) {
        return httpResponse.getCall().getRequest();
    }
}
