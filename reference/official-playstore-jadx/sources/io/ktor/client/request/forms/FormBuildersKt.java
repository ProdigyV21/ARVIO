package io.ktor.client.request.forms;

import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.Parameters;
import io.ktor.http.content.PartData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aF\u0010\u000b\u001a\u00020\n*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001aN\u0010\u000b\u001a\u00020\n*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u000f\u001a@\u0010\u0013\u001a\u00020\n*\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001aH\u0010\u0013\u001a\u00020\n*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0015\u001aF\u0010\u0017\u001a\u00020\u0016*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\f\u001aN\u0010\u0017\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u000f\u001a@\u0010\u0018\u001a\u00020\u0016*\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0019\b\u0006\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0014\u001aH\u0010\u0018\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0019\b\u0006\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/ktor/client/HttpClient;", "Lio/ktor/http/Parameters;", "formParameters", "", "encodeInQuery", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/client/statement/HttpResponse;", "submitForm", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Parameters;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "", "url", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lio/ktor/http/Parameters;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "", "Lio/ktor/http/content/PartData;", "formData", "submitFormWithBinaryData", "(Lio/ktor/client/HttpClient;Ljava/util/List;Lr7/l;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/util/List;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpStatement;", "prepareForm", "prepareFormWithBinaryData", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormBuildersKt {

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$prepareForm$2, reason: invalid class name */
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$prepareForm$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$prepareFormWithBinaryData$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18012 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18012 INSTANCE = new C18012();

        public C18012() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$prepareFormWithBinaryData$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18025 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18025 INSTANCE = new C18025();

        public C18025() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$submitForm$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18032 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18032 INSTANCE = new C18032();

        public C18032() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$submitForm$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18045 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18045 INSTANCE = new C18045();

        public C18045() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18052 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18052 INSTANCE = new C18052();

        public C18052() {
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

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18065 extends r implements l<HttpRequestBuilder, t0> {
        public static final C18065 INSTANCE = new C18065();

        public C18065() {
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

    public static final Object prepareForm(HttpClient httpClient, Parameters parameters, boolean z, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareForm$$forInline(HttpClient httpClient, Parameters parameters, boolean z, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static Object prepareForm$default(HttpClient httpClient, Parameters parameters, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            parameters = Parameters.INSTANCE.getEmpty();
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareFormWithBinaryData(HttpClient httpClient, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareFormWithBinaryData$$forInline(HttpClient httpClient, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static Object prepareFormWithBinaryData$default(HttpClient httpClient, List list, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C18012.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object submitForm(HttpClient httpClient, Parameters parameters, boolean z, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object submitForm$$forInline(HttpClient httpClient, Parameters parameters, boolean z, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static Object submitForm$default(HttpClient httpClient, Parameters parameters, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            parameters = Parameters.INSTANCE.getEmpty();
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            lVar = C18032.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object submitFormWithBinaryData(HttpClient httpClient, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object submitFormWithBinaryData$$forInline(HttpClient httpClient, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static Object submitFormWithBinaryData$default(HttpClient httpClient, List list, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C18052.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object prepareFormWithBinaryData(HttpClient httpClient, String str, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareFormWithBinaryData$$forInline(HttpClient httpClient, String str, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object submitFormWithBinaryData(HttpClient httpClient, String str, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object submitFormWithBinaryData$$forInline(HttpClient httpClient, String str, List<? extends PartData> list, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static Object prepareFormWithBinaryData$default(HttpClient httpClient, String str, List list, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            lVar = C18025.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static Object submitFormWithBinaryData$default(HttpClient httpClient, String str, List list, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            lVar = C18065.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        httpRequestBuilder.setBody(new MultiPartFormDataContent(list, null, null, 6, null));
        httpRequestBuilder.setBodyType(null);
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object prepareForm(HttpClient httpClient, String str, Parameters parameters, boolean z, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object submitForm(HttpClient httpClient, String str, Parameters parameters, boolean z, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        if (z) {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
            httpRequestBuilder.getUrl().getParameters().appendAll(parameters);
        } else {
            httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
            httpRequestBuilder.setBody(new FormDataContent(parameters));
            httpRequestBuilder.setBodyType(null);
        }
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object prepareForm$default(HttpClient httpClient, String str, Parameters parameters, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            parameters = Parameters.INSTANCE.getEmpty();
        }
        Parameters parameters2 = parameters;
        if ((i10 & 4) != 0) {
            z = false;
        }
        boolean z5 = z;
        if ((i10 & 8) != 0) {
            lVar = AnonymousClass5.INSTANCE;
        }
        return prepareForm(httpClient, str, parameters2, z5, lVar, dVar);
    }

    public static /* synthetic */ Object submitForm$default(HttpClient httpClient, String str, Parameters parameters, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            parameters = Parameters.INSTANCE.getEmpty();
        }
        Parameters parameters2 = parameters;
        if ((i10 & 4) != 0) {
            z = false;
        }
        boolean z5 = z;
        if ((i10 & 8) != 0) {
            lVar = C18045.INSTANCE;
        }
        return submitForm(httpClient, str, parameters2, z5, lVar, dVar);
    }
}
