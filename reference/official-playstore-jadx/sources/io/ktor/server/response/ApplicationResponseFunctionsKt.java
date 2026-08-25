package io.ktor.server.response;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.URLBuilder;
import io.ktor.http.Url;
import io.ktor.http.content.ByteArrayContent;
import io.ktor.http.content.ChannelWriterContent;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.util.URLBuilderKt;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import kotlin.reflect.b0;
import kotlin.reflect.q;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a+\u0010\u0007\u001a\u00020\u0004\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\u0007\u001a\u00020\u0004*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00002\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\n\u001a'\u0010\u000b\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0006\u001a3\u0010\u0007\u001a\u00020\u0004\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00028\u0000H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u000e\u001a1\u0010\u0007\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00002\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u000f\u001a/\u0010\u000b\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00028\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u000e\u001a)\u0010\u0014\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a)\u0010\u0014\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00162\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0017\u001a:\u0010\u0014\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00040\u0018¢\u0006\u0002\b\u001aH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u001c\u001aR\u0010\"\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00102\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010!\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00040\u0018¢\u0006\u0002\b\u001aH\u0086@ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aM\u0010\"\u001a\u00020\u0004*\u00020\u00022\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u001c\u0010%\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100$\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0018H\u0086@ø\u0001\u0000¢\u0006\u0004\b\"\u0010&\u001aM\u0010(\u001a\u00020\u0004*\u00020\u00022\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u001c\u0010%\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0$\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0018H\u0086@ø\u0001\u0000¢\u0006\u0004\b(\u0010&\u001aR\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020'2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010!\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00040\u0018¢\u0006\u0002\b\u001aH\u0086@ø\u0001\u0000¢\u0006\u0004\b(\u0010*\u001ad\u00100\u001a\u00020\u0004*\u00020\u00022\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+2'\u0010/\u001a#\b\u0001\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040$\u0012\u0006\u0012\u0004\u0018\u00010\u00000-¢\u0006\u0002\b\u001aH\u0086@ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u001b\u00102\u001a\u00020\u001e*\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b2\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"", "T", "Lio/ktor/server/application/ApplicationCall;", "message", "Lx6/t0;", "respondWithType", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "respond", "Lio/ktor/util/reflect/TypeInfo;", "messageType", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "respondNullable", "Lio/ktor/http/HttpStatusCode;", "status", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/HttpStatusCode;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/HttpStatusCode;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "", "url", "", "permanent", "respondRedirect", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "Lio/ktor/http/Url;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/Url;ZLd7/d;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lio/ktor/http/URLBuilder;", "Lx6/n;", "block", "(Lio/ktor/server/application/ApplicationCall;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "text", "Lio/ktor/http/ContentType;", "contentType", "Lio/ktor/http/content/OutgoingContent;", "configure", "respondText", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/d;", "provider", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "respondBytes", "bytes", "(Lio/ktor/server/application/ApplicationCall;[BLio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "contentLength", "Lkotlin/Function2;", "Lio/ktor/utils/io/ByteWriteChannel;", "producer", "respondBytesWriter", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Ljava/lang/Long;Lr7/p;Ld7/d;)Ljava/lang/Object;", "defaultTextContentType", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;)Lio/ktor/http/ContentType;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationResponseFunctionsKt {

    /* JADX INFO: renamed from: io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.response.ApplicationResponseFunctionsKt", f = "ApplicationResponseFunctions.kt", l = {154, 224}, m = "respondBytes")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationResponseFunctionsKt.respondBytes(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Lx6/t0;", "invoke", "(Lio/ktor/http/content/OutgoingContent;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<OutgoingContent, t0> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final void invoke(OutgoingContent outgoingContent) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((OutgoingContent) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Lx6/t0;", "invoke", "(Lio/ktor/http/content/OutgoingContent;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<OutgoingContent, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(OutgoingContent outgoingContent) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((OutgoingContent) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.response.ApplicationResponseFunctionsKt", f = "ApplicationResponseFunctions.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_UHD, 224}, m = "respondText")
    public static final class C19123 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C19123(d<? super C19123> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationResponseFunctionsKt.respondText(null, null, null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.http.ContentType defaultTextContentType(io.ktor.server.application.ApplicationCall r0, io.ktor.http.ContentType r1) {
        /*
            if (r1 != 0) goto L28
            io.ktor.server.response.ApplicationResponse r0 = r0.getResponse()
            io.ktor.server.response.ResponseHeaders r0 = r0.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getContentType()
            java.lang.String r0 = r0.get(r1)
            if (r0 == 0) goto L22
            io.ktor.http.ContentType$Companion r1 = io.ktor.http.ContentType.INSTANCE     // Catch: io.ktor.http.BadContentTypeFormatException -> L1e
            io.ktor.http.ContentType r0 = r1.parse(r0)     // Catch: io.ktor.http.BadContentTypeFormatException -> L1e
        L1c:
            r1 = r0
            goto L20
        L1e:
            r0 = 0
            goto L1c
        L20:
            if (r1 != 0) goto L28
        L22:
            io.ktor.http.ContentType$Text r0 = io.ktor.http.ContentType.Text.INSTANCE
            io.ktor.http.ContentType r1 = r0.getPlain()
        L28:
            java.nio.charset.Charset r0 = io.ktor.http.ContentTypesKt.charset(r1)
            if (r0 != 0) goto L40
            io.ktor.http.ContentType$Text r0 = io.ktor.http.ContentType.Text.INSTANCE
            io.ktor.http.ContentType r0 = r0.getAny()
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L40
            java.nio.charset.Charset r0 = kotlin.text.a.f19924a
            io.ktor.http.ContentType r1 = io.ktor.http.ContentTypesKt.withCharset(r1, r0)
        L40:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.ApplicationResponseFunctionsKt.defaultTextContentType(io.ktor.server.application.ApplicationCall, io.ktor.http.ContentType):io.ktor.http.ContentType");
    }

    public static final Object respond(ApplicationCall applicationCall, Object obj, TypeInfo typeInfo, d<? super t0> dVar) {
        ResponseTypeKt.setResponseType(applicationCall.getResponse(), typeInfo);
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        if (obj == null) {
            obj = NullBody.INSTANCE;
        }
        Object objExecute = pipeline.execute(applicationCall, obj, dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0071, code lost:
    
        if (r6.execute(r5, r8, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondBytes(io.ktor.server.application.ApplicationCall r5, io.ktor.http.ContentType r6, io.ktor.http.HttpStatusCode r7, r7.l<? super d7.d<? super byte[]>, ? extends java.lang.Object> r8, d7.d<? super x6.t0> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.server.response.ApplicationResponseFunctionsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1 r0 = (io.ktor.server.response.ApplicationResponseFunctionsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1 r0 = new io.ktor.server.response.ApplicationResponseFunctionsKt$respondBytes$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L43
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r9)
            goto L74
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            java.lang.Object r5 = r0.L$2
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            java.lang.Object r6 = r0.L$1
            r7 = r6
            io.ktor.http.HttpStatusCode r7 = (io.ktor.http.HttpStatusCode) r7
            java.lang.Object r6 = r0.L$0
            io.ktor.http.ContentType r6 = (io.ktor.http.ContentType) r6
            k2.c.G(r9)
            goto L55
        L43:
            k2.c.G(r9)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r9 = r8.invoke(r0)
            if (r9 != r4) goto L55
            goto L73
        L55:
            byte[] r9 = (byte[]) r9
            io.ktor.http.content.ByteArrayContent r8 = new io.ktor.http.content.ByteArrayContent
            r8.<init>(r9, r6, r7)
            io.ktor.server.response.ApplicationResponse r6 = r5.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r6 = r6.getPipeline()
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r2
            java.lang.Object r5 = r6.execute(r5, r8, r0)
            if (r5 != r4) goto L74
        L73:
            return r4
        L74:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.ApplicationResponseFunctionsKt.respondBytes(io.ktor.server.application.ApplicationCall, io.ktor.http.ContentType, io.ktor.http.HttpStatusCode, r7.l, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object respondBytes$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        return respondBytes(applicationCall, contentType, httpStatusCode, lVar, dVar);
    }

    public static final Object respondBytesWriter(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, p<? super ByteWriteChannel, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, new ChannelWriterContent(pVar, contentType, httpStatusCode, l10), dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static /* synthetic */ Object respondBytesWriter$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        if ((i10 & 4) != 0) {
            l10 = null;
        }
        return respondBytesWriter(applicationCall, contentType, httpStatusCode, l10, pVar, dVar);
    }

    public static final <T> Object respondNullable(ApplicationCall applicationCall, T t2, d<? super t0> dVar) {
        if (!(t2 instanceof OutgoingContent) && !(t2 instanceof byte[])) {
            applicationCall.getResponse();
            kotlin.jvm.internal.p.h();
            throw null;
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        if (t2 == null) {
            t2 = (T) NullBody.INSTANCE;
        }
        pipeline.execute(applicationCall, t2, dVar);
        return t0.f22605a;
    }

    public static final Object respondRedirect(ApplicationCall applicationCall, String str, boolean z, d<? super t0> dVar) {
        ResponseHeaders.append$default(applicationCall.getResponse().getHeaders(), HttpHeaders.INSTANCE.getLocation(), str, false, 4, null);
        HttpStatusCode.Companion companion = HttpStatusCode.INSTANCE;
        HttpStatusCode movedPermanently = z ? companion.getMovedPermanently() : companion.getFound();
        if (!(movedPermanently instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            q qVarA = l0.a(HttpStatusCode.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA), l0.f19747a.b(HttpStatusCode.class), qVarA));
        }
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, movedPermanently, dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    private static final Object respondRedirect$$forInline(ApplicationCall applicationCall, boolean z, l<? super URLBuilder, t0> lVar, d<? super t0> dVar) {
        URLBuilder uRLBuilderCreateFromCall = URLBuilderKt.createFromCall(URLBuilder.INSTANCE, applicationCall);
        lVar.invoke(uRLBuilderCreateFromCall);
        respondRedirect(applicationCall, uRLBuilderCreateFromCall.buildString(), z, dVar);
        return t0.f22605a;
    }

    public static /* synthetic */ Object respondRedirect$default(ApplicationCall applicationCall, String str, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return respondRedirect(applicationCall, str, z, (d<? super t0>) dVar);
    }

    public static final Object respondText(ApplicationCall applicationCall, String str, ContentType contentType, HttpStatusCode httpStatusCode, l<? super OutgoingContent, t0> lVar, d<? super t0> dVar) {
        TextContent textContent = new TextContent(str, defaultTextContentType(applicationCall, contentType), httpStatusCode);
        lVar.invoke(textContent);
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, textContent, dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static /* synthetic */ Object respondText$default(ApplicationCall applicationCall, String str, ContentType contentType, HttpStatusCode httpStatusCode, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            contentType = null;
        }
        if ((i10 & 4) != 0) {
            httpStatusCode = null;
        }
        if ((i10 & 8) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return respondText(applicationCall, str, contentType, httpStatusCode, lVar, dVar);
    }

    public static final <T> Object respondWithType(ApplicationCall applicationCall, T t2, d<? super t0> dVar) {
        if ((t2 instanceof OutgoingContent) || (t2 instanceof byte[])) {
            applicationCall.getResponse().getPipeline().execute(applicationCall, t2, dVar);
            return t0.f22605a;
        }
        applicationCall.getResponse();
        kotlin.jvm.internal.p.h();
        throw null;
    }

    public static /* synthetic */ Object respondBytes$default(ApplicationCall applicationCall, byte[] bArr, ContentType contentType, HttpStatusCode httpStatusCode, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            contentType = null;
        }
        if ((i10 & 4) != 0) {
            httpStatusCode = null;
        }
        if ((i10 & 8) != 0) {
            lVar = AnonymousClass3.INSTANCE;
        }
        return respondBytes(applicationCall, bArr, contentType, httpStatusCode, lVar, dVar);
    }

    public static /* synthetic */ Object respondRedirect$default(ApplicationCall applicationCall, Url url, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return respondRedirect(applicationCall, url, z, (d<? super t0>) dVar);
    }

    public static final Object respond(ApplicationCall applicationCall, HttpStatusCode httpStatusCode, Object obj, TypeInfo typeInfo, d<? super t0> dVar) {
        applicationCall.getResponse().status(httpStatusCode);
        Object objRespond = respond(applicationCall, obj, typeInfo, dVar);
        return objRespond == a.f15033i ? objRespond : t0.f22605a;
    }

    public static /* synthetic */ Object respondRedirect$default(ApplicationCall applicationCall, boolean z, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        URLBuilder uRLBuilderCreateFromCall = URLBuilderKt.createFromCall(URLBuilder.INSTANCE, applicationCall);
        lVar.invoke(uRLBuilderCreateFromCall);
        respondRedirect(applicationCall, uRLBuilderCreateFromCall.buildString(), z, (d<? super t0>) dVar);
        return t0.f22605a;
    }

    public static /* synthetic */ Object respondText$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        return respondText(applicationCall, contentType, httpStatusCode, lVar, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0076, code lost:
    
        if (r6.execute(r5, r8, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondText(io.ktor.server.application.ApplicationCall r5, io.ktor.http.ContentType r6, io.ktor.http.HttpStatusCode r7, r7.l<? super d7.d<? super java.lang.String>, ? extends java.lang.Object> r8, d7.d<? super x6.t0> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.server.response.ApplicationResponseFunctionsKt.C19123
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3 r0 = (io.ktor.server.response.ApplicationResponseFunctionsKt.C19123) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3 r0 = new io.ktor.server.response.ApplicationResponseFunctionsKt$respondText$3
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L44
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r9)
            goto L79
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            java.lang.Object r5 = r0.L$2
            r7 = r5
            io.ktor.http.HttpStatusCode r7 = (io.ktor.http.HttpStatusCode) r7
            java.lang.Object r5 = r0.L$1
            r6 = r5
            io.ktor.http.ContentType r6 = (io.ktor.http.ContentType) r6
            java.lang.Object r5 = r0.L$0
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            k2.c.G(r9)
            goto L56
        L44:
            k2.c.G(r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r9 = r8.invoke(r0)
            if (r9 != r4) goto L56
            goto L78
        L56:
            java.lang.String r9 = (java.lang.String) r9
            io.ktor.http.ContentType r6 = defaultTextContentType(r5, r6)
            io.ktor.http.content.TextContent r8 = new io.ktor.http.content.TextContent
            r8.<init>(r9, r6, r7)
            io.ktor.server.response.ApplicationResponse r6 = r5.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r6 = r6.getPipeline()
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r2
            java.lang.Object r5 = r6.execute(r5, r8, r0)
            if (r5 != r4) goto L79
        L78:
            return r4
        L79:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.response.ApplicationResponseFunctionsKt.respondText(io.ktor.server.application.ApplicationCall, io.ktor.http.ContentType, io.ktor.http.HttpStatusCode, r7.l, d7.d):java.lang.Object");
    }

    public static final Object respondBytes(ApplicationCall applicationCall, byte[] bArr, ContentType contentType, HttpStatusCode httpStatusCode, l<? super OutgoingContent, t0> lVar, d<? super t0> dVar) {
        ByteArrayContent byteArrayContent = new ByteArrayContent(bArr, contentType, httpStatusCode);
        lVar.invoke(byteArrayContent);
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, byteArrayContent, dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static final <T> Object respondNullable(ApplicationCall applicationCall, HttpStatusCode httpStatusCode, T t2, d<? super t0> dVar) {
        applicationCall.getResponse().status(httpStatusCode);
        if (!(t2 instanceof OutgoingContent) && !(t2 instanceof byte[])) {
            applicationCall.getResponse();
            kotlin.jvm.internal.p.h();
            throw null;
        }
        ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
        if (t2 == null) {
            t2 = (T) NullBody.INSTANCE;
        }
        pipeline.execute(applicationCall, t2, dVar);
        return t0.f22605a;
    }

    public static final <T> Object respondWithType(ApplicationCall applicationCall, HttpStatusCode httpStatusCode, T t2, d<? super t0> dVar) {
        applicationCall.getResponse().status(httpStatusCode);
        if (!(t2 instanceof OutgoingContent) && !(t2 instanceof byte[])) {
            applicationCall.getResponse();
            kotlin.jvm.internal.p.h();
            throw null;
        }
        applicationCall.getResponse().getPipeline().execute(applicationCall, t2, dVar);
        return t0.f22605a;
    }

    public static final Object respondRedirect(ApplicationCall applicationCall, Url url, boolean z, d<? super t0> dVar) {
        Object objRespondRedirect = respondRedirect(applicationCall, url.getUrlString(), z, dVar);
        return objRespondRedirect == a.f15033i ? objRespondRedirect : t0.f22605a;
    }

    public static final Object respondRedirect(ApplicationCall applicationCall, boolean z, l<? super URLBuilder, t0> lVar, d<? super t0> dVar) {
        URLBuilder uRLBuilderCreateFromCall = URLBuilderKt.createFromCall(URLBuilder.INSTANCE, applicationCall);
        lVar.invoke(uRLBuilderCreateFromCall);
        Object objRespondRedirect = respondRedirect(applicationCall, uRLBuilderCreateFromCall.buildString(), z, dVar);
        return objRespondRedirect == a.f15033i ? objRespondRedirect : t0.f22605a;
    }
}
