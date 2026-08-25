package io.ktor.server.request;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.CannotTransformContentToTypeException;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.io.InputStream;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.b0;
import kotlin.reflect.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "Ljava/io/InputStream;", "receiveStream", "(Lio/ktor/server/application/ApplicationCall;Ld7/d;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationReceiveFunctionsJvmKt {

    /* JADX INFO: renamed from: io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.server.request.ApplicationReceiveFunctionsJvmKt", f = "ApplicationReceiveFunctionsJvm.kt", l = {18}, m = "receiveStream")
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
            return ApplicationReceiveFunctionsJvmKt.receiveStream(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object receiveStream(io.ktor.server.application.ApplicationCall r5, d7.d<? super java.io.InputStream> r6) throws io.ktor.server.plugins.CannotTransformContentToTypeException {
        /*
            boolean r0 = r6 instanceof io.ktor.server.request.ApplicationReceiveFunctionsJvmKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1 r0 = (io.ktor.server.request.ApplicationReceiveFunctionsJvmKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1 r0 = new io.ktor.server.request.ApplicationReceiveFunctionsJvmKt$receiveStream$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
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
            java.lang.Object r6 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r5, r6, r0)
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
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationReceiveFunctionsJvmKt.receiveStream(io.ktor.server.application.ApplicationCall, d7.d):java.lang.Object");
    }

    private static final Object receiveStream$$forInline(ApplicationCall applicationCall, d<? super InputStream> dVar) throws CannotTransformContentToTypeException {
        q qVarA = l0.a(InputStream.class);
        Type typeT = b0.t(qVarA);
        m0 m0Var = l0.f19747a;
        Object objReceiveNullable = ApplicationReceiveFunctionsKt.receiveNullable(applicationCall, TypeInfoJvmKt.typeInfoImpl(typeT, m0Var.b(InputStream.class), qVarA), dVar);
        if (objReceiveNullable != null) {
            return objReceiveNullable;
        }
        q qVarA2 = l0.a(InputStream.class);
        throw new CannotTransformContentToTypeException(TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA2), m0Var.b(InputStream.class), qVarA2).getKotlinType());
    }
}
