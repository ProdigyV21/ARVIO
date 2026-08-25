package io.ktor.utils.io.core;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a9\u0010\u0006\u001a\u00020\u00052\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0011\u0010\b\u001a\u00020\u0002*\u00020\u0001¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/utils/io/core/ByteReadPacket;", "buildPacket", "(Lr7/l;)Lio/ktor/utils/io/core/ByteReadPacket;", "reset", "(Lio/ktor/utils/io/core/BytePacketBuilder;)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuilderKt {
    public static final ByteReadPacket buildPacket(l<? super BytePacketBuilder, t0> lVar) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            lVar.invoke(bytePacketBuilder);
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final void reset(BytePacketBuilder bytePacketBuilder) {
        bytePacketBuilder.release();
    }
}
