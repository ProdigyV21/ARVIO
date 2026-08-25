package io.ktor.client.engine.android;

import d7.d;
import d7.j;
import f7.c;
import f7.e;
import io.ktor.client.network.sockets.TimeoutExceptionsCommonKt;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.ReadingKt;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import ka.l0;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\t\u001a9\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\n*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00028\u00000\fH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a#\u0010\u0013\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0013\u0010\u0017\u001a\u00020\u0016*\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Ljava/net/HttpURLConnection;", "Lio/ktor/client/request/HttpRequestData;", "requestData", "Lx6/t0;", "setupTimeoutAttributes", "(Ljava/net/HttpURLConnection;Lio/ktor/client/request/HttpRequestData;)V", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "timeoutAttributes", "setupRequestTimeoutAttributes", "(Ljava/net/HttpURLConnection;Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;)V", "T", "request", "Lkotlin/Function1;", "block", "timeoutAwareConnection", "(Ljava/net/HttpURLConnection;Lio/ktor/client/request/HttpRequestData;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/j;", "callContext", "Lio/ktor/utils/io/ByteReadChannel;", "content", "(Ljava/net/HttpURLConnection;Ld7/j;Lio/ktor/client/request/HttpRequestData;)Lio/ktor/utils/io/ByteReadChannel;", "", "", "isTimeoutException", "(Ljava/lang/Throwable;)Z", "ktor-client-android"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidURLConnectionUtilsKt {

    /* JADX INFO: renamed from: io.ktor.client.engine.android.AndroidURLConnectionUtilsKt$timeoutAwareConnection$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.engine.android.AndroidURLConnectionUtilsKt", f = "AndroidURLConnectionUtils.kt", l = {60}, m = "timeoutAwareConnection")
    public static final class AnonymousClass1<T> extends c {
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
            return AndroidURLConnectionUtilsKt.timeoutAwareConnection(null, null, null, this);
        }
    }

    public static final ByteReadChannel content(HttpURLConnection httpURLConnection, j jVar, HttpRequestData httpRequestData) {
        ByteReadChannel byteReadChannel;
        ByteReadChannel byteReadChannelMapEngineExceptions;
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedInputStream = inputStream != null ? inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, 8192) : null;
        } catch (IOException unused) {
            InputStream errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                bufferedInputStream = errorStream instanceof BufferedInputStream ? (BufferedInputStream) errorStream : new BufferedInputStream(errorStream, 8192);
            }
        }
        return (bufferedInputStream == null || (byteReadChannel = ReadingKt.toByteReadChannel(bufferedInputStream, jVar, ByteBufferPoolKt.getKtorDefaultPool())) == null || (byteReadChannelMapEngineExceptions = TimeoutExceptionsCommonKt.mapEngineExceptions(l0.a(jVar), byteReadChannel, httpRequestData)) == null) ? ByteReadChannel.INSTANCE.getEmpty() : byteReadChannelMapEngineExceptions;
    }

    private static final boolean isTimeoutException(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return true;
        }
        if (th instanceof ConnectException) {
            String message = th.getMessage();
            if (message != null ? o.T(message, "timed out", false) : false) {
                return true;
            }
        }
        return false;
    }

    private static final void setupRequestTimeoutAttributes(HttpURLConnection httpURLConnection, HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
        Long l10 = httpTimeoutCapabilityConfiguration.get_requestTimeoutMillis();
        if (l10 != null) {
            long jLongValue = l10.longValue();
            if (jLongValue != Long.MAX_VALUE) {
                if (httpURLConnection.getConnectTimeout() == 0 || httpURLConnection.getConnectTimeout() > jLongValue) {
                    httpURLConnection.setConnectTimeout(HttpTimeoutKt.convertLongTimeoutToIntWithInfiniteAsZero(jLongValue));
                }
            }
        }
    }

    public static final void setupTimeoutAttributes(HttpURLConnection httpURLConnection, HttpRequestData httpRequestData) {
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestData.getCapabilityOrNull(HttpTimeout.INSTANCE);
        if (httpTimeoutCapabilityConfiguration != null) {
            Long l10 = httpTimeoutCapabilityConfiguration.get_connectTimeoutMillis();
            if (l10 != null) {
                httpURLConnection.setConnectTimeout(HttpTimeoutKt.convertLongTimeoutToIntWithInfiniteAsZero(l10.longValue()));
            }
            Long l11 = httpTimeoutCapabilityConfiguration.get_socketTimeoutMillis();
            if (l11 != null) {
                httpURLConnection.setReadTimeout(HttpTimeoutKt.convertLongTimeoutToIntWithInfiniteAsZero(l11.longValue()));
            }
            setupRequestTimeoutAttributes(httpURLConnection, httpTimeoutCapabilityConfiguration);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object timeoutAwareConnection(java.net.HttpURLConnection r4, io.ktor.client.request.HttpRequestData r5, r7.l<? super java.net.HttpURLConnection, ? extends T> r6, d7.d<? super T> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.client.engine.android.AndroidURLConnectionUtilsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.engine.android.AndroidURLConnectionUtilsKt$timeoutAwareConnection$1 r0 = (io.ktor.client.engine.android.AndroidURLConnectionUtilsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.engine.android.AndroidURLConnectionUtilsKt$timeoutAwareConnection$1 r0 = new io.ktor.client.engine.android.AndroidURLConnectionUtilsKt$timeoutAwareConnection$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r4 = r0.L$1
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.client.request.HttpRequestData r5 = (io.ktor.client.request.HttpRequestData) r5
            k2.c.G(r7)
            goto L4d
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            k2.c.G(r7)
            java.lang.Object r4 = r6.invoke(r4)     // Catch: java.lang.Throwable -> L3d
            return r4
        L3d:
            r4 = move-exception
            r0.L$0 = r5
            r0.L$1 = r4
            r0.label = r2
            java.lang.Object r6 = ka.m0.B(r0)
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L4d
            return r7
        L4d:
            boolean r6 = isTimeoutException(r4)
            if (r6 == 0) goto L57
            io.ktor.client.network.sockets.ConnectTimeoutException r4 = io.ktor.client.plugins.HttpTimeoutKt.ConnectTimeoutException(r5, r4)
        L57:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.android.AndroidURLConnectionUtilsKt.timeoutAwareConnection(java.net.HttpURLConnection, io.ktor.client.request.HttpRequestData, r7.l, d7.d):java.lang.Object");
    }
}
