package io.ktor.server.request;

import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.BadContentTypeFormatException;
import io.ktor.http.HttpHeaders;
import io.ktor.http.LinkHeader;
import io.ktor.http.Parameters;
import io.ktor.http.content.MultiPartData;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.plugins.CannotTransformContentToTypeException;
import io.ktor.util.AttributeKey;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import kotlin.reflect.q;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0003\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\u0005\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a!\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0004\u001a/\u0010\u0005\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\t\u001a'\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\f\u001a%\u0010\u0005\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\f\u001a+\u0010\u0003\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\f\u001a1\u0010\u0003\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\t\u001a\u0017\u0010\u000e\u001a\u00020\r*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0004\u001a\u0017\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0004\u001a\u0017\u0010\u0012\u001a\u00020\u0011*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0004\u001a\u0017\u0010\u0014\u001a\u00020\u0013*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0004\"\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018*\n\u0010\u001a\"\u00020\u00192\u00020\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"", "T", "Lio/ktor/server/application/ApplicationCall;", "receiveOrNull", "(Lio/ktor/server/application/ApplicationCall;Ld7/d;)Ljava/lang/Object;", "receive", "receiveNullable", "Lkotlin/reflect/d;", LinkHeader.Parameters.Type, "(Lio/ktor/server/application/ApplicationCall;Lkotlin/reflect/d;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "", "receiveText", "Lio/ktor/utils/io/ByteReadChannel;", "receiveChannel", "Lio/ktor/http/content/MultiPartData;", "receiveMultipart", "Lio/ktor/http/Parameters;", "receiveParameters", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/request/DoubleReceivePreventionToken;", "DoubleReceivePreventionTokenKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/plugins/ContentTransformationException;", "ContentTransformationException", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationReceiveFunctionsKt {
    private static final AttributeKey<DoubleReceivePreventionToken> DoubleReceivePreventionTokenKey = new AttributeKey<>("DoubleReceivePreventionToken");

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {86}, m = "receive")
    public static final class AnonymousClass2<T> extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receive((ApplicationCall) null, (kotlin.reflect.d) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {121}, m = "receive")
    public static final class AnonymousClass3<T> extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receive((ApplicationCall) null, (TypeInfo) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {218}, m = "receiveChannel")
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
            return ApplicationReceiveFunctionsKt.receiveChannel(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {218}, m = "receiveMultipart")
    public static final class C19061 extends c {
        int label;
        /* synthetic */ Object result;

        public C19061(d<? super C19061> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receiveMultipart(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {MediaError.DetailedErrorCode.MEDIA_NETWORK}, m = "receiveNullable")
    public static final class C19072<T> extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19072(d<? super C19072> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receiveNullable(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "receiveOrNull")
    public static final class C19082<T> extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19082(d<? super C19082> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receiveOrNull((ApplicationCall) null, (TypeInfo) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {155}, m = "receiveOrNull")
    public static final class C19093<T> extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19093(d<? super C19093> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receiveOrNull((ApplicationCall) null, (kotlin.reflect.d) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {218}, m = "receiveParameters")
    public static final class C19101 extends c {
        int label;
        /* synthetic */ Object result;

        public C19101(d<? super C19101> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receiveParameters(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", l = {219, TsExtractor.TS_STREAM_TYPE_AC4}, m = "receiveText")
    public static final class C19111 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19111(d<? super C19111> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationReceiveFunctionsKt.receiveText(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object receive(io.ktor.server.application.ApplicationCall r4, kotlin.reflect.d<T> r5, d7.d<? super T> r6) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$2
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L48
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r6)
            kotlin.reflect.q r6 = io.ktor.server.application.internal.TypeUtilsJvmKt.starProjectedTypeBridge(r5)
            io.ktor.util.reflect.TypeInfo r1 = new io.ktor.util.reflect.TypeInfo
            java.lang.reflect.Type r3 = io.ktor.util.reflect.TypeInfoJvmKt.getPlatformType(r6)
            r1.<init>(r5, r3, r6)
            r0.label = r2
            java.lang.Object r6 = receiveNullable(r4, r1, r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L48
            return r4
        L48:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receive(io.ktor.server.application.ApplicationCall, kotlin.reflect.d, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object receiveChannel(io.ktor.server.application.ApplicationCall r5, d7.d<? super io.ktor.utils.io.ByteReadChannel> r6) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveChannel$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r3 = io.ktor.utils.io.ByteReadChannel.class
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r6)
            goto L4f
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            k2.c.G(r6)
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r3)
            java.lang.reflect.Type r1 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r4 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r4 = r4.b(r3)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r1, r4, r6)
            r0.label = r2
            java.lang.Object r6 = receiveNullable(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L4f
            return r5
        L4f:
            if (r6 == 0) goto L52
            return r6
        L52:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r3)
            java.lang.reflect.Type r0 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r1 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r1.b(r3)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.q r6 = r6.getKotlinType()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveChannel(io.ktor.server.application.ApplicationCall, d7.d):java.lang.Object");
    }

    private static final Object receiveChannel$$forInline(ApplicationCall applicationCall, d<? super ByteReadChannel> dVar) throws CannotTransformContentToTypeException {
        q qVarA = l0.a(ByteReadChannel.class);
        Type typeT = b0.t(qVarA);
        m0 m0Var = l0.f19747a;
        Object objReceiveNullable = receiveNullable(applicationCall, TypeInfoJvmKt.typeInfoImpl(typeT, m0Var.b(ByteReadChannel.class), qVarA), dVar);
        if (objReceiveNullable != null) {
            return objReceiveNullable;
        }
        q qVarA2 = l0.a(ByteReadChannel.class);
        throw new CannotTransformContentToTypeException(TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA2), m0Var.b(ByteReadChannel.class), qVarA2).getKotlinType());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object receiveMultipart(io.ktor.server.application.ApplicationCall r5, d7.d<? super io.ktor.http.content.MultiPartData> r6) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.C19061
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.C19061) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveMultipart$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            java.lang.Class<io.ktor.http.content.MultiPartData> r3 = io.ktor.http.content.MultiPartData.class
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r6)
            goto L4f
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            k2.c.G(r6)
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r3)
            java.lang.reflect.Type r1 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r4 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r4 = r4.b(r3)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r1, r4, r6)
            r0.label = r2
            java.lang.Object r6 = receiveNullable(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L4f
            return r5
        L4f:
            if (r6 == 0) goto L52
            return r6
        L52:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r3)
            java.lang.reflect.Type r0 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r1 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r1.b(r3)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.q r6 = r6.getKotlinType()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveMultipart(io.ktor.server.application.ApplicationCall, d7.d):java.lang.Object");
    }

    private static final Object receiveMultipart$$forInline(ApplicationCall applicationCall, d<? super MultiPartData> dVar) throws CannotTransformContentToTypeException {
        q qVarA = l0.a(MultiPartData.class);
        Type typeT = b0.t(qVarA);
        m0 m0Var = l0.f19747a;
        Object objReceiveNullable = receiveNullable(applicationCall, TypeInfoJvmKt.typeInfoImpl(typeT, m0Var.b(MultiPartData.class), qVarA), dVar);
        if (objReceiveNullable != null) {
            return objReceiveNullable;
        }
        q qVarA2 = l0.a(MultiPartData.class);
        throw new CannotTransformContentToTypeException(TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA2), m0Var.b(MultiPartData.class), qVarA2).getKotlinType());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object receiveNullable(io.ktor.server.application.ApplicationCall r5, io.ktor.util.reflect.TypeInfo r6, d7.d<? super T> r7) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r7 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.C19072
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.C19072) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveNullable$2
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r5 = r0.L$0
            r6 = r5
            io.ktor.util.reflect.TypeInfo r6 = (io.ktor.util.reflect.TypeInfo) r6
            k2.c.G(r7)
            goto L6e
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            k2.c.G(r7)
            io.ktor.util.Attributes r7 = r5.getAttributes()
            io.ktor.util.AttributeKey<io.ktor.server.request.DoubleReceivePreventionToken> r1 = io.ktor.server.request.ApplicationReceiveFunctionsKt.DoubleReceivePreventionTokenKey
            java.lang.Object r7 = r7.getOrNull(r1)
            io.ktor.server.request.DoubleReceivePreventionToken r7 = (io.ktor.server.request.DoubleReceivePreventionToken) r7
            if (r7 != 0) goto L4c
            io.ktor.util.Attributes r3 = r5.getAttributes()
            io.ktor.server.request.DoubleReceivePreventionToken r4 = io.ktor.server.request.DoubleReceivePreventionToken.INSTANCE
            r3.put(r1, r4)
        L4c:
            io.ktor.server.application.ApplicationCallKt.setReceiveType(r5, r6)
            if (r7 != 0) goto L59
            io.ktor.server.request.ApplicationRequest r7 = r5.getRequest()
            io.ktor.utils.io.ByteReadChannel r7 = r7.getInput()
        L59:
            io.ktor.server.request.ApplicationRequest r1 = r5.getRequest()
            io.ktor.server.request.ApplicationReceivePipeline r1 = r1.getPipeline()
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r7 = r1.execute(r5, r7, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L6e
            return r5
        L6e:
            io.ktor.http.content.NullBody r5 = io.ktor.http.content.NullBody.INSTANCE
            boolean r5 = kotlin.jvm.internal.p.a(r7, r5)
            if (r5 == 0) goto L78
            r5 = 0
            return r5
        L78:
            io.ktor.server.request.DoubleReceivePreventionToken r5 = io.ktor.server.request.DoubleReceivePreventionToken.INSTANCE
            if (r7 == r5) goto L91
            kotlin.reflect.d r5 = r6.getType()
            boolean r5 = r5.l(r7)
            if (r5 == 0) goto L87
            return r7
        L87:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            kotlin.reflect.q r6 = r6.getKotlinType()
            r5.<init>(r6)
            throw r5
        L91:
            io.ktor.server.request.RequestAlreadyConsumedException r5 = new io.ktor.server.request.RequestAlreadyConsumedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(io.ktor.server.application.ApplicationCall, io.ktor.util.reflect.TypeInfo, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object receiveOrNull(io.ktor.server.application.ApplicationCall r4, io.ktor.util.reflect.TypeInfo r5, d7.d<? super T> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.C19082
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.C19082) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$2
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r4 = r0.L$0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            k2.c.G(r6)     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            return r6
        L29:
            r5 = move-exception
            goto L44
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            r0.L$0 = r4     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            r0.label = r2     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            java.lang.Object r4 = receiveNullable(r4, r5, r0)     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L43
            return r5
        L43:
            return r4
        L44:
            io.ktor.server.application.Application r4 = r4.getApplication()
            vc.b r4 = io.ktor.server.application.ApplicationKt.getLog(r4)
            java.lang.String r6 = "Conversion failed, null returned"
            r4.e(r6, r5)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveOrNull(io.ktor.server.application.ApplicationCall, io.ktor.util.reflect.TypeInfo, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object receiveParameters(io.ktor.server.application.ApplicationCall r5, d7.d<? super io.ktor.http.Parameters> r6) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.C19101
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.C19101) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveParameters$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            java.lang.Class<io.ktor.http.Parameters> r3 = io.ktor.http.Parameters.class
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r6)
            goto L4f
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            k2.c.G(r6)
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r3)
            java.lang.reflect.Type r1 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r4 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r4 = r4.b(r3)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r1, r4, r6)
            r0.label = r2
            java.lang.Object r6 = receiveNullable(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L4f
            return r5
        L4f:
            if (r6 == 0) goto L52
            return r6
        L52:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r3)
            java.lang.reflect.Type r0 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r1 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r1.b(r3)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.q r6 = r6.getKotlinType()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveParameters(io.ktor.server.application.ApplicationCall, d7.d):java.lang.Object");
    }

    private static final Object receiveParameters$$forInline(ApplicationCall applicationCall, d<? super Parameters> dVar) throws CannotTransformContentToTypeException {
        q qVarA = l0.a(Parameters.class);
        Type typeT = b0.t(qVarA);
        m0 m0Var = l0.f19747a;
        Object objReceiveNullable = receiveNullable(applicationCall, TypeInfoJvmKt.typeInfoImpl(typeT, m0Var.b(Parameters.class), qVarA), dVar);
        if (objReceiveNullable != null) {
            return objReceiveNullable;
        }
        q qVarA2 = l0.a(Parameters.class);
        throw new CannotTransformContentToTypeException(TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA2), m0Var.b(Parameters.class), qVarA2).getKotlinType());
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0086, code lost:
    
        if (r11 == r8) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object receiveText(io.ktor.server.application.ApplicationCall r10, d7.d<? super java.lang.String> r11) throws io.ktor.server.plugins.CannotTransformContentToTypeException, io.ktor.server.plugins.BadRequestException {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveText(io.ktor.server.application.ApplicationCall, d7.d):java.lang.Object");
    }

    private static final Object receiveText$$forInline(ApplicationCall applicationCall, d<? super String> dVar) throws CannotTransformContentToTypeException, BadRequestException {
        try {
            Charset charsetContentCharset = ApplicationRequestPropertiesKt.contentCharset(applicationCall.getRequest());
            if (charsetContentCharset == null) {
                charsetContentCharset = a.f19924a;
            }
            q qVarA = l0.a(ByteReadChannel.class);
            Type typeT = b0.t(qVarA);
            m0 m0Var = l0.f19747a;
            Object objReceiveNullable = receiveNullable(applicationCall, TypeInfoJvmKt.typeInfoImpl(typeT, m0Var.b(ByteReadChannel.class), qVarA), dVar);
            if (objReceiveNullable != null) {
                return StringsKt.readText$default((Input) ByteReadChannel.DefaultImpls.readRemaining$default((ByteReadChannel) objReceiveNullable, 0L, dVar, 1, null), charsetContentCharset, 0, 2, (Object) null);
            }
            q qVarA2 = l0.a(ByteReadChannel.class);
            throw new CannotTransformContentToTypeException(TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA2), m0Var.b(ByteReadChannel.class), qVarA2).getKotlinType());
        } catch (BadContentTypeFormatException e5) {
            throw new BadRequestException("Illegal Content-Type format: " + applicationCall.getRequest().getHeaders().get(HttpHeaders.INSTANCE.getContentType()), e5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object receive(io.ktor.server.application.ApplicationCall r4, io.ktor.util.reflect.TypeInfo r5, d7.d<? super T> r6) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receive$3
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L3b
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r6)
            r0.label = r2
            java.lang.Object r6 = receiveNullable(r4, r5, r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L3b
            return r4
        L3b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receive(io.ktor.server.application.ApplicationCall, io.ktor.util.reflect.TypeInfo, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object receiveOrNull(io.ktor.server.application.ApplicationCall r4, kotlin.reflect.d<T> r5, d7.d<? super T> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsKt.C19093
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsKt.C19093) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsKt$receiveOrNull$3
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r4 = r0.L$0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            k2.c.G(r6)     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            return r6
        L29:
            r5 = move-exception
            goto L44
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            r0.L$0 = r4     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            r0.label = r2     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            java.lang.Object r4 = receive(r4, r5, r0)     // Catch: io.ktor.server.plugins.ContentTransformationException -> L29
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L43
            return r5
        L43:
            return r4
        L44:
            io.ktor.server.application.Application r4 = r4.getApplication()
            vc.b r4 = io.ktor.server.application.ApplicationKt.getLog(r4)
            java.lang.String r6 = "Conversion failed, null returned"
            r4.e(r6, r5)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveOrNull(io.ktor.server.application.ApplicationCall, kotlin.reflect.d, d7.d):java.lang.Object");
    }

    public static final <T> Object receive(ApplicationCall applicationCall, d<? super T> dVar) {
        p.h();
        throw null;
    }

    @x6.e
    public static final <T> Object receiveOrNull(ApplicationCall applicationCall, d<? super T> dVar) {
        p.h();
        throw null;
    }

    public static final <T> Object receiveNullable(ApplicationCall applicationCall, d<? super T> dVar) {
        p.h();
        throw null;
    }
}
