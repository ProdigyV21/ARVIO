package io.ktor.websocket;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\u001a\u001b\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a'\u0010\u000b\u001a\u00020\n*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0087@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a'\u0010\u0011\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0001H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/utils/io/core/ByteReadPacket;", "", "maskKey", "mask", "(Lio/ktor/utils/io/core/ByteReadPacket;I)Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/websocket/Frame;", "frame", "", "masking", "Lx6/t0;", "writeFrame", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/websocket/Frame;ZLd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "", "maxFrameSize", "lastOpcode", "readFrame", "(Lio/ktor/utils/io/ByteReadChannel;JILd7/d;)Ljava/lang/Object;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RawWebSocketCommonKt {

    /* JADX INFO: renamed from: io.ktor.websocket.RawWebSocketCommonKt$readFrame$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.RawWebSocketCommonKt", f = "RawWebSocketCommon.kt", l = {212, 213, 232, 233, 241, 249}, m = "readFrame")
    public static final class AnonymousClass1 extends c {
        byte B$0;
        byte B$1;
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RawWebSocketCommonKt.readFrame(null, 0L, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.RawWebSocketCommonKt$writeFrame$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.RawWebSocketCommonKt", f = "RawWebSocketCommon.kt", l = {174, 184, 187, TsExtractor.TS_PACKET_SIZE, 196, 201}, m = "writeFrame")
    public static final class C20591 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C20591(d<? super C20591> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RawWebSocketCommonKt.writeFrame(null, null, false, this);
        }
    }

    private static final ByteReadPacket mask(ByteReadPacket byteReadPacket, int i10) {
        DefaultAllocator defaultAllocator = DefaultAllocator.INSTANCE;
        ByteBuffer byteBufferMo6740allocgFvZug = defaultAllocator.mo6740allocgFvZug(4);
        try {
            byteBufferMo6740allocgFvZug.putInt(0, i10);
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
            try {
                int remaining = (int) byteReadPacket.getRemaining();
                for (int i11 = 0; i11 < remaining; i11++) {
                    bytePacketBuilder.writeByte((byte) (byteReadPacket.readByte() ^ byteBufferMo6740allocgFvZug.get(i11 % 4)));
                }
                return bytePacketBuilder.build();
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        } finally {
            defaultAllocator.mo6741free3GNKZMM(byteBufferMo6740allocgFvZug);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01da  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFrame(io.ktor.utils.io.ByteReadChannel r19, long r20, int r22, d7.d<? super io.ktor.websocket.Frame> r23) throws io.ktor.websocket.FrameTooBigException, io.ktor.websocket.ProtocolViolationException {
        /*
            Method dump skipped, instruction units count: 596
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommonKt.readFrame(io.ktor.utils.io.ByteReadChannel, long, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x010b, code lost:
    
        if (r4.writeLong(r11, r0) == r7) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x010e, code lost:
    
        r10 = r12;
        r11 = r1;
        r12 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0123, code lost:
    
        if (r4.writeShort((short) r11, r0) == r7) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0165, code lost:
    
        if (r4.writePacket(r11, r0) == r7) goto L71;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeFrame(io.ktor.utils.io.ByteWriteChannel r10, io.ktor.websocket.Frame r11, boolean r12, d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommonKt.writeFrame(io.ktor.utils.io.ByteWriteChannel, io.ktor.websocket.Frame, boolean, d7.d):java.lang.Object");
    }
}
