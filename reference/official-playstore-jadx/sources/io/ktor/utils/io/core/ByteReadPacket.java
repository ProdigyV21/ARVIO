package io.ktor.utils.io.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB'\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\b\u0010\nJ\r\u0010\u000b\u001a\u00020\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0004¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\r\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/Input;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", TtmlNode.TAG_HEAD, "", "remaining", "Lio/ktor/utils/io/pool/ObjectPool;", "pool", "<init>", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;JLio/ktor/utils/io/pool/ObjectPool;)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/pool/ObjectPool;)V", "copy", "()Lio/ktor/utils/io/core/ByteReadPacket;", "fill", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Lio/ktor/utils/io/bits/Memory;", "destination", "", "offset", "length", "fill-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "Lx6/t0;", "closeSource", "()V", "", "toString", "()Ljava/lang/String;", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ByteReadPacket extends Input {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ByteReadPacket Empty;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/utils/io/core/ByteReadPacket$Companion;", "", "()V", "Empty", "Lio/ktor/utils/io/core/ByteReadPacket;", "getEmpty", "()Lio/ktor/utils/io/core/ByteReadPacket;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ByteReadPacket getEmpty() {
            return ByteReadPacket.Empty;
        }

        private Companion() {
        }
    }

    static {
        ChunkBuffer.Companion companion = ChunkBuffer.INSTANCE;
        Empty = new ByteReadPacket(companion.getEmpty(), 0L, companion.getEmptyPool());
    }

    public ByteReadPacket(ChunkBuffer chunkBuffer, long j10, ObjectPool<ChunkBuffer> objectPool) {
        super(chunkBuffer, j10, objectPool);
        markNoMoreChunksAvailable();
    }

    @Override // io.ktor.utils.io.core.Input
    public final void closeSource() {
    }

    public final ByteReadPacket copy() {
        return new ByteReadPacket(BuffersKt.copyAll(getHead()), getRemaining(), getPool());
    }

    @Override // io.ktor.utils.io.core.Input
    public final ChunkBuffer fill() {
        return null;
    }

    @Override // io.ktor.utils.io.core.Input
    /* JADX INFO: renamed from: fill-62zg_DM */
    public final int mo6682fill62zg_DM(ByteBuffer destination, int offset, int length) {
        return 0;
    }

    public String toString() {
        return "ByteReadPacket[" + hashCode() + ']';
    }

    public ByteReadPacket(ChunkBuffer chunkBuffer, ObjectPool<ChunkBuffer> objectPool) {
        this(chunkBuffer, BuffersKt.remainingAll(chunkBuffer), objectPool);
    }
}
