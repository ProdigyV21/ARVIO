package io.ktor.util.cio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "buffer", "Ljava/nio/ByteBuffer;", "invoke", "(Ljava/nio/ByteBuffer;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FileChannelsKt$readChannel$1$3$2 extends r implements l<ByteBuffer, Boolean> {
    final /* synthetic */ long $endInclusive;
    final /* synthetic */ FileChannel $fileChannel;
    final /* synthetic */ j0 $position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$readChannel$1$3$2(long j10, j0 j0Var, FileChannel fileChannel) {
        super(1);
        this.$endInclusive = j10;
        this.$position = j0Var;
        this.$fileChannel = fileChannel;
    }

    @Override // r7.l
    public final Boolean invoke(ByteBuffer byteBuffer) throws IOException {
        int i10;
        long j10 = (this.$endInclusive - this.$position.f19745i) + 1;
        if (j10 < byteBuffer.remaining()) {
            int iLimit = byteBuffer.limit();
            byteBuffer.limit(byteBuffer.position() + ((int) j10));
            i10 = this.$fileChannel.read(byteBuffer);
            byteBuffer.limit(iLimit);
        } else {
            i10 = this.$fileChannel.read(byteBuffer);
        }
        if (i10 > 0) {
            this.$position.f19745i += (long) i10;
        }
        return Boolean.valueOf(i10 != -1 && this.$position.f19745i <= this.$endInclusive);
    }
}
