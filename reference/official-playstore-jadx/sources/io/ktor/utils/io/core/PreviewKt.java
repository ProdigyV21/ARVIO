package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aM\u0010\b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\b\u001a\u00020\u0003*\u00020\u0001H\u0001¢\u0006\u0004\b\b\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"R", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/ByteReadPacket;", "Lx6/y;", ContentDisposition.Parameters.Name, "tmp", "block", "preview", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lr7/l;)Ljava/lang/Object;", "(Lio/ktor/utils/io/core/BytePacketBuilder;)Lio/ktor/utils/io/core/ByteReadPacket;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PreviewKt {
    public static final <R> R preview(BytePacketBuilder bytePacketBuilder, l<? super ByteReadPacket, ? extends R> lVar) {
        ByteReadPacket byteReadPacketPreview = preview(bytePacketBuilder);
        try {
            return (R) lVar.invoke(byteReadPacketPreview);
        } finally {
            byteReadPacketPreview.release();
        }
    }

    public static final ByteReadPacket preview(BytePacketBuilder bytePacketBuilder) {
        ChunkBuffer head$ktor_io = bytePacketBuilder.getHead$ktor_io();
        return head$ktor_io == ChunkBuffer.INSTANCE.getEmpty() ? ByteReadPacket.INSTANCE.getEmpty() : new ByteReadPacket(BuffersKt.copyAll(head$ktor_io), bytePacketBuilder.get_pool());
    }
}
