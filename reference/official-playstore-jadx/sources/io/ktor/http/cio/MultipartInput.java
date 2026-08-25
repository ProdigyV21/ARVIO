package io.ktor.http.cio;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.k;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import ka.m0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/http/cio/MultipartInput;", "Lio/ktor/utils/io/core/Input;", "Ljava/nio/ByteBuffer;", TtmlNode.TAG_HEAD, "Lio/ktor/utils/io/ByteReadChannel;", "tail", "<init>", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;)V", "Lio/ktor/utils/io/bits/Memory;", "destination", "", "offset", "length", "fill-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "fill", "Lx6/t0;", "closeSource", "()V", "Ljava/nio/ByteBuffer;", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MultipartInput extends Input {
    private final ByteBuffer head;
    private final ByteReadChannel tail;

    public MultipartInput(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel) {
        super(null, 0L, null, 7, null);
        this.head = byteBuffer;
        this.tail = byteReadChannel;
    }

    @Override // io.ktor.utils.io.core.Input
    public void closeSource() {
        ByteReadChannelKt.cancel(this.tail);
    }

    @Override // io.ktor.utils.io.core.Input
    /* JADX INFO: renamed from: fill-62zg_DM, reason: not valid java name */
    public int mo6682fill62zg_DM(ByteBuffer destination, int offset, int length) {
        if (!this.head.hasRemaining()) {
            return ((Number) m0.s(k.f14688i, new MultipartInput$fill$1(this, length, destination, offset, null))).intValue();
        }
        if (destination.hasArray() && !destination.isReadOnly()) {
            int iMin = Math.min(this.head.remaining(), length);
            this.head.get(destination.array(), offset, iMin);
            if (iMin < 0) {
                return 0;
            }
            return iMin;
        }
        byte[] bArrBorrow = ByteArrayPoolKt.getByteArrayPool().borrow();
        try {
            int iMin2 = Math.min(this.head.remaining(), length);
            this.head.get(bArrBorrow, 0, iMin2);
            Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArrBorrow, 0, iMin2).slice().order(ByteOrder.BIG_ENDIAN)), destination, 0, iMin2, offset);
            return iMin2;
        } finally {
            ByteArrayPoolKt.getByteArrayPool().recycle(bArrBorrow);
        }
    }
}
