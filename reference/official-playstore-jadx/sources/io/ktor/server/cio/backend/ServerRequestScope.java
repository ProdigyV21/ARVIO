package io.ktor.server.cio.backend;

import d7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.net.SocketAddress;
import ka.k0;
import ka.r;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\u0018\u00002\u00020\u0001BQ\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u000e\u0010\n\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t\u0012\u000e\u0010\u000b\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\n\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u000b\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010 \u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lio/ktor/server/cio/backend/ServerRequestScope;", "Lka/k0;", "Ld7/j;", "coroutineContext", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "remoteAddress", "localAddress", "Lka/r;", "", "upgraded", "<init>", "(Ld7/j;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ljava/net/SocketAddress;Ljava/net/SocketAddress;Lka/r;)V", "withContext", "(Ld7/j;)Lio/ktor/server/cio/backend/ServerRequestScope;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Lio/ktor/utils/io/ByteReadChannel;", "getInput", "()Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "getOutput", "()Lio/ktor/utils/io/ByteWriteChannel;", "Ljava/net/SocketAddress;", "getRemoteAddress", "()Ljava/net/SocketAddress;", "getLocalAddress", "Lka/r;", "getUpgraded", "()Lka/r;", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ServerRequestScope implements k0 {
    private final j coroutineContext;
    private final ByteReadChannel input;
    private final SocketAddress localAddress;
    private final ByteWriteChannel output;
    private final SocketAddress remoteAddress;
    private final r<Boolean> upgraded;

    public ServerRequestScope(j jVar, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, SocketAddress socketAddress, SocketAddress socketAddress2, r<Boolean> rVar) {
        this.coroutineContext = jVar;
        this.input = byteReadChannel;
        this.output = byteWriteChannel;
        this.remoteAddress = socketAddress;
        this.localAddress = socketAddress2;
        this.upgraded = rVar;
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    public final ByteReadChannel getInput() {
        return this.input;
    }

    public final SocketAddress getLocalAddress() {
        return this.localAddress;
    }

    public final ByteWriteChannel getOutput() {
        return this.output;
    }

    public final SocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    public final r<Boolean> getUpgraded() {
        return this.upgraded;
    }

    public final ServerRequestScope withContext(j coroutineContext) {
        return new ServerRequestScope(getCoroutineContext().plus(coroutineContext), this.input, this.output, this.remoteAddress, this.localAddress, this.upgraded);
    }
}
