package io.ktor.client.request.forms;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/ByteReadPacket;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class MultiPartFormDataContent$rawParts$1$provider$1 extends r implements a<ByteReadPacket> {
    final /* synthetic */ byte[] $bytes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiPartFormDataContent$rawParts$1$provider$1(byte[] bArr) {
        super(0);
        this.$bytes = bArr;
    }

    @Override // r7.a
    public final ByteReadPacket invoke() {
        byte[] bArr = this.$bytes;
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            OutputKt.writeFully$default((Output) bytePacketBuilder, bArr, 0, 0, 6, (Object) null);
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }
}
