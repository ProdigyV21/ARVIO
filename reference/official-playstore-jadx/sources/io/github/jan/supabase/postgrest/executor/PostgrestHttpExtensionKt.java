package io.github.jan.supabase.postgrest.executor;

import d7.d;
import db.m;
import f7.c;
import f7.e;
import g8.b;
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder;
import io.github.jan.supabase.postgrest.query.Returning;
import io.github.jan.supabase.postgrest.request.PostgrestRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.UtilsKt;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.x;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import kotlin.reflect.q;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\n\u001a\u00020\t*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0080@¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lio/github/jan/supabase/postgrest/request/PostgrestRequest;", "request", "Lx6/t0;", "configurePostgrestRequest", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/github/jan/supabase/postgrest/request/PostgrestRequest;)V", "Lio/ktor/client/statement/HttpResponse;", "Lio/github/jan/supabase/postgrest/Postgrest;", "postgrest", "Lio/github/jan/supabase/postgrest/result/PostgrestResult;", "asPostgrestResult", "(Lio/ktor/client/statement/HttpResponse;Lio/github/jan/supabase/postgrest/Postgrest;Ld7/d;)Ljava/lang/Object;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PostgrestHttpExtensionKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt$asPostgrestResult$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt", f = "PostgrestHttpExtension.kt", l = {41}, m = "asPostgrestResult")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PostgrestHttpExtensionKt.asPostgrestResult(null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object asPostgrestResult(io.ktor.client.statement.HttpResponse r4, io.github.jan.supabase.postgrest.Postgrest r5, d7.d<? super io.github.jan.supabase.postgrest.result.PostgrestResult> r6) {
        /*
            boolean r0 = r6 instanceof io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt$asPostgrestResult$1 r0 = (io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt$asPostgrestResult$1 r0 = new io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt$asPostgrestResult$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.github.jan.supabase.postgrest.Postgrest r5 = (io.github.jan.supabase.postgrest.Postgrest) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.client.statement.HttpResponse r4 = (io.ktor.client.statement.HttpResponse) r4
            k2.c.G(r6)
            goto L49
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            r6 = 0
            java.lang.Object r6 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r4, r6, r0, r2, r6)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L49
            return r0
        L49:
            java.lang.String r6 = (java.lang.String) r6
            io.ktor.http.Headers r4 = r4.getHeaders()
            io.github.jan.supabase.postgrest.result.PostgrestResult r0 = new io.github.jan.supabase.postgrest.result.PostgrestResult
            r0.<init>(r6, r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.postgrest.executor.PostgrestHttpExtensionKt.asPostgrestResult(io.ktor.client.statement.HttpResponse, io.github.jan.supabase.postgrest.Postgrest, d7.d):java.lang.Object");
    }

    public static final void configurePostgrestRequest(HttpRequestBuilder httpRequestBuilder, PostgrestRequest postgrestRequest) {
        httpRequestBuilder.setMethod(postgrestRequest.getMethod());
        HttpMessagePropertiesKt.contentType(httpRequestBuilder, ContentType.Application.INSTANCE.getJson());
        httpRequestBuilder.getHeaders().appendAll(postgrestRequest.getHeaders());
        httpRequestBuilder.getHeaders().set(PostgrestQueryBuilder.HEADER_PREFER, x.u0(postgrestRequest.getPrefer(), ",", null, null, null, 62));
        ParametersBuilder parameters = httpRequestBuilder.getUrl().getParameters();
        Map<String, String> urlParams = postgrestRequest.getUrlParams();
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(urlParams.size()));
        Iterator<T> it = urlParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), Collections.singletonList((String) entry.getValue()));
        }
        parameters.appendAll(ParametersKt.parametersOf(linkedHashMap));
        if (postgrestRequest.getReturning() instanceof Returning.Representation) {
            parameters.append("select", ((Returning.Representation) postgrestRequest.getReturning()).m6661getColumnsU9NzzuM());
        }
        m body = postgrestRequest.getBody();
        if (body != null) {
            httpRequestBuilder.setBody(body);
            q qVarA = l0.a(m.class);
            b.o(l0.f19747a, m.class, b0.t(qVarA), qVarA, httpRequestBuilder);
        }
        if (o.h0(postgrestRequest.getSchema())) {
            return;
        }
        HttpMethod method = httpRequestBuilder.getMethod();
        HttpMethod.Companion companion = HttpMethod.INSTANCE;
        if (p.a(method, companion.getGet()) ? true : p.a(method, companion.getHead())) {
            UtilsKt.header(httpRequestBuilder, "Accept-Profile", postgrestRequest.getSchema());
        } else {
            UtilsKt.header(httpRequestBuilder, "Content-Profile", postgrestRequest.getSchema());
        }
    }
}
