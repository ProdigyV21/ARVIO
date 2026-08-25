package io.ktor.client.plugins;

import a0.c;
import io.ktor.client.network.sockets.ConnectTimeoutException;
import io.ktor.client.network.sockets.SocketTimeoutException;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.utils.ExceptionUtilsJvmKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import r7.a;
import r7.l;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a!\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000e\u001a+\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u0012\u001a!\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001d\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0081\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\"\u0018\u0010!\u001a\u00060\u001fj\u0002` 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/Function1;", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "Lx6/t0;", "Lx6/n;", "block", "timeout", "(Lio/ktor/client/request/HttpRequestBuilder;Lr7/l;)V", "Lio/ktor/client/request/HttpRequestData;", "request", "", "cause", "Lio/ktor/client/network/sockets/ConnectTimeoutException;", "ConnectTimeoutException", "(Lio/ktor/client/request/HttpRequestData;Ljava/lang/Throwable;)Lio/ktor/client/network/sockets/ConnectTimeoutException;", "", "url", "", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Throwable;)Lio/ktor/client/network/sockets/ConnectTimeoutException;", "Lio/ktor/client/network/sockets/SocketTimeoutException;", "SocketTimeoutException", "(Lio/ktor/client/request/HttpRequestData;Ljava/lang/Throwable;)Lio/ktor/client/network/sockets/SocketTimeoutException;", "", "convertLongTimeoutToIntWithInfiniteAsZero", "(J)I", "convertLongTimeoutToLongWithInfiniteAsZero", "(J)J", "T", "Lkotlin/Function0;", "unwrapRequestTimeoutException", "(Lr7/a;)Ljava/lang/Object;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpTimeoutKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpTimeout");

    public static final ConnectTimeoutException ConnectTimeoutException(HttpRequestData httpRequestData, Throwable th) {
        Object obj;
        StringBuilder sb2 = new StringBuilder("Connect timeout has expired [url=");
        sb2.append(httpRequestData.getUrl());
        sb2.append(", connect_timeout=");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestData.getCapabilityOrNull(HttpTimeout.INSTANCE);
        if (httpTimeoutCapabilityConfiguration == null || (obj = httpTimeoutCapabilityConfiguration.get_connectTimeoutMillis()) == null) {
            obj = "unknown";
        }
        sb2.append(obj);
        sb2.append(" ms]");
        return new ConnectTimeoutException(sb2.toString(), th);
    }

    public static /* synthetic */ ConnectTimeoutException ConnectTimeoutException$default(HttpRequestData httpRequestData, Throwable th, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            th = null;
        }
        return ConnectTimeoutException(httpRequestData, th);
    }

    public static final SocketTimeoutException SocketTimeoutException(HttpRequestData httpRequestData, Throwable th) {
        Object obj;
        StringBuilder sb2 = new StringBuilder("Socket timeout has expired [url=");
        sb2.append(httpRequestData.getUrl());
        sb2.append(", socket_timeout=");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestData.getCapabilityOrNull(HttpTimeout.INSTANCE);
        if (httpTimeoutCapabilityConfiguration == null || (obj = httpTimeoutCapabilityConfiguration.get_socketTimeoutMillis()) == null) {
            obj = "unknown";
        }
        sb2.append(obj);
        sb2.append("] ms");
        return new SocketTimeoutException(sb2.toString(), th);
    }

    public static /* synthetic */ SocketTimeoutException SocketTimeoutException$default(HttpRequestData httpRequestData, Throwable th, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            th = null;
        }
        return SocketTimeoutException(httpRequestData, th);
    }

    @InternalAPI
    public static final int convertLongTimeoutToIntWithInfiniteAsZero(long j10) {
        if (j10 == Long.MAX_VALUE) {
            return 0;
        }
        if (j10 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j10 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j10;
    }

    @InternalAPI
    public static final long convertLongTimeoutToLongWithInfiniteAsZero(long j10) {
        if (j10 == Long.MAX_VALUE) {
            return 0L;
        }
        return j10;
    }

    public static final void timeout(HttpRequestBuilder httpRequestBuilder, l<? super HttpTimeout.HttpTimeoutCapabilityConfiguration, t0> lVar) {
        HttpTimeout.Companion companion = HttpTimeout.INSTANCE;
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = new HttpTimeout.HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
        lVar.invoke(httpTimeoutCapabilityConfiguration);
        httpRequestBuilder.setCapability(companion, httpTimeoutCapabilityConfiguration);
    }

    public static final <T> T unwrapRequestTimeoutException(a<? extends T> aVar) throws Throwable {
        try {
            return (T) aVar.invoke();
        } catch (CancellationException e5) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(e5);
        }
    }

    public static /* synthetic */ ConnectTimeoutException ConnectTimeoutException$default(String str, Long l10, Throwable th, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            th = null;
        }
        return ConnectTimeoutException(str, l10, th);
    }

    public static final ConnectTimeoutException ConnectTimeoutException(String str, Long l10, Throwable th) {
        StringBuilder sbT = c.t("Connect timeout has expired [url=", str, ", connect_timeout=");
        Object obj = l10;
        if (l10 == null) {
            obj = "unknown";
        }
        sbT.append(obj);
        sbT.append(" ms]");
        return new ConnectTimeoutException(sbT.toString(), th);
    }
}
