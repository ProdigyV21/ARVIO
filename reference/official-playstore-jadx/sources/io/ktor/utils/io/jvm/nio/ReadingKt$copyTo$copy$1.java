package io.ktor.utils.io.jvm.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "bb", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ReadingKt$copyTo$copy$1 extends r implements l<ByteBuffer, t0> {
    final /* synthetic */ j0 $copied;
    final /* synthetic */ f0 $eof;
    final /* synthetic */ long $limit;
    final /* synthetic */ ReadableByteChannel $this_copyTo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReadingKt$copyTo$copy$1(long j10, j0 j0Var, ReadableByteChannel readableByteChannel, f0 f0Var) {
        super(1);
        this.$limit = j10;
        this.$copied = j0Var;
        this.$this_copyTo = readableByteChannel;
        this.$eof = f0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
        invoke((ByteBuffer) obj);
        return t0.f22605a;
    }

    public final void invoke(ByteBuffer byteBuffer) throws IOException {
        long j10 = this.$limit - this.$copied.f19745i;
        if (j10 >= byteBuffer.remaining()) {
            int i10 = this.$this_copyTo.read(byteBuffer);
            if (i10 == -1) {
                this.$eof.f19738i = true;
                return;
            } else {
                this.$copied.f19745i += (long) i10;
                return;
            }
        }
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + ((int) j10));
        int i11 = this.$this_copyTo.read(byteBuffer);
        if (i11 == -1) {
            this.$eof.f19738i = true;
        } else {
            this.$copied.f19745i += (long) i11;
        }
        byteBuffer.limit(iLimit);
    }
}
