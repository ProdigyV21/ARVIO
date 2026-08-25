package io.ktor.utils.io;

import d7.j;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\f8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lio/ktor/utils/io/ChannelScope;", "Lio/ktor/utils/io/ReaderScope;", "Lio/ktor/utils/io/WriterScope;", "Lka/k0;", "delegate", "Lio/ktor/utils/io/ByteChannel;", "channel", "<init>", "(Lka/k0;Lio/ktor/utils/io/ByteChannel;)V", "Lio/ktor/utils/io/ByteChannel;", "getChannel", "()Lio/ktor/utils/io/ByteChannel;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ChannelScope implements ReaderScope, WriterScope, k0 {
    private final /* synthetic */ k0 $$delegate_0;
    private final ByteChannel channel;

    public ChannelScope(k0 k0Var, ByteChannel byteChannel) {
        this.channel = byteChannel;
        this.$$delegate_0 = k0Var;
    }

    @Override // io.ktor.utils.io.ReaderScope, ka.k0
    public j getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // io.ktor.utils.io.WriterScope
    public ByteChannel getChannel() {
        return this.channel;
    }
}
