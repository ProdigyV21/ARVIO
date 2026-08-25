package io.ktor.websocket;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Ljava/nio/ByteBuffer;", "it", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "io/ktor/utils/io/core/ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$1", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class RawWebSocketCommonKt$writeFrame$$inlined$ByteReadPacket$default$1 extends r implements l<ByteBuffer, t0> {
    final /* synthetic */ byte[] $array;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RawWebSocketCommonKt$writeFrame$$inlined$ByteReadPacket$default$1(byte[] bArr) {
        super(1);
        this.$array = bArr;
    }

    public final void invoke(ByteBuffer byteBuffer) {
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ByteBuffer) obj);
        return t0.f22605a;
    }
}
