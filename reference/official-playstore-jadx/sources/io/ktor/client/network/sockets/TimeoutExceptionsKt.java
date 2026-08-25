package io.ktor.client.network.sockets;

import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.ThrowableKt;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"ByteChannelWithMappedExceptions", "Lio/ktor/utils/io/ByteChannel;", "request", "Lio/ktor/client/request/HttpRequestData;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimeoutExceptionsKt {

    /* JADX INFO: renamed from: io.ktor.client.network.sockets.TimeoutExceptionsKt$ByteChannelWithMappedExceptions$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "cause", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Throwable, Throwable> {
        final /* synthetic */ HttpRequestData $request;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpRequestData httpRequestData) {
            super(1);
            this.$request = httpRequestData;
        }

        @Override // r7.l
        public final Throwable invoke(Throwable th) {
            return (th != null ? ThrowableKt.getRootCause(th) : null) instanceof java.net.SocketTimeoutException ? HttpTimeoutKt.SocketTimeoutException(this.$request, th) : th;
        }
    }

    public static final ByteChannel ByteChannelWithMappedExceptions(HttpRequestData httpRequestData) {
        return ByteChannelKt.ByteChannel$default(false, new AnonymousClass1(httpRequestData), 1, null);
    }
}
