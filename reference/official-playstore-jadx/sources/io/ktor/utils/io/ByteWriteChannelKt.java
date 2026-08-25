package io.ktor.utils.io;

import androidx.media3.exoplayer.upstream.CmcdData;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.network.sockets.DatagramKt;
import io.ktor.utils.io.core.ByteOrder;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001f\u0010\t\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a'\u0010\t\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\r\u001a\u001f\u0010\u000f\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\n\u001a\u001f\u0010\u0012\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\u0012\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0014\u001a\u0011\u0010\u0016\u001a\u00020\u0015*\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001f\u0010\u0019\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001f\u0010\u0019\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\b\u001a\u00020\u001bH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010!\u001a\u00020\u0006*\u00020\u00002\u0006\u0010 \u001a\u00020\u001fH\u0086@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a0\u0010'\u001a\u00020\u0006*\u00020\u00002\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00060#¢\u0006\u0002\b%H\u0086Hø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a@\u0010,\u001a\u00020\u0006*\u00020\u00002'\u0010&\u001a#\b\u0001\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060*\u0012\u0006\u0012\u0004\u0018\u00010+0)¢\u0006\u0002\b%H\u0086@ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "", "src", "", "writeAvailable", "(Lio/ktor/utils/io/ByteWriteChannel;[BLd7/d;)Ljava/lang/Object;", "Lx6/t0;", "writeFully", CmcdData.STREAMING_FORMAT_SS, "writeShort", "(Lio/ktor/utils/io/ByteWriteChannel;ILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/ByteOrder;", "byteOrder", "(Lio/ktor/utils/io/ByteWriteChannel;ILio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "b", "writeByte", "", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "writeInt", "(Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "(Lio/ktor/utils/io/ByteWriteChannel;JLio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "", "close", "(Lio/ktor/utils/io/ByteWriteChannel;)Z", "", "writeStringUtf8", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/CharSequence;Ld7/d;)Ljava/lang/Object;", "", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "writeBoolean", "(Lio/ktor/utils/io/ByteWriteChannel;ZLd7/d;)Ljava/lang/Object;", "", "ch", "writeChar", "(Lio/ktor/utils/io/ByteWriteChannel;CLd7/d;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lx6/n;", "builder", "writePacket", "(Lio/ktor/utils/io/ByteWriteChannel;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function2;", "Ld7/d;", "", "writePacketSuspend", "(Lio/ktor/utils/io/ByteWriteChannel;Lr7/p;Ld7/d;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteWriteChannelKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteWriteChannelKt", f = "ByteWriteChannel.kt", l = {202, 202}, m = "writePacketSuspend")
    public static final class AnonymousClass1 extends c {
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
            return ByteWriteChannelKt.writePacketSuspend(null, null, this);
        }
    }

    public static final boolean close(ByteWriteChannel byteWriteChannel) {
        return byteWriteChannel.close(null);
    }

    public static final Object writeAvailable(ByteWriteChannel byteWriteChannel, byte[] bArr, d<? super Integer> dVar) {
        return byteWriteChannel.writeAvailable(bArr, 0, bArr.length, dVar);
    }

    public static final Object writeBoolean(ByteWriteChannel byteWriteChannel, boolean z, d<? super t0> dVar) {
        Object objWriteByte = byteWriteChannel.writeByte(z ? (byte) 1 : (byte) 0, dVar);
        return objWriteByte == a.f15033i ? objWriteByte : t0.f22605a;
    }

    public static final Object writeByte(ByteWriteChannel byteWriteChannel, int i10, d<? super t0> dVar) {
        Object objWriteByte = byteWriteChannel.writeByte((byte) (i10 & 255), dVar);
        return objWriteByte == a.f15033i ? objWriteByte : t0.f22605a;
    }

    public static final Object writeChar(ByteWriteChannel byteWriteChannel, char c10, d<? super t0> dVar) {
        Object objWriteShort = writeShort(byteWriteChannel, c10, dVar);
        return objWriteShort == a.f15033i ? objWriteShort : t0.f22605a;
    }

    public static final Object writeFully(ByteWriteChannel byteWriteChannel, byte[] bArr, d<? super t0> dVar) {
        Object objWriteFully = byteWriteChannel.writeFully(bArr, 0, bArr.length, dVar);
        return objWriteFully == a.f15033i ? objWriteFully : t0.f22605a;
    }

    public static final Object writeInt(ByteWriteChannel byteWriteChannel, long j10, d<? super t0> dVar) {
        Object objWriteInt = byteWriteChannel.writeInt((int) j10, dVar);
        return objWriteInt == a.f15033i ? objWriteInt : t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object writePacket(ByteWriteChannel byteWriteChannel, l<? super BytePacketBuilder, t0> lVar, d<? super t0> dVar) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            lVar.invoke(bytePacketBuilder);
            Object objWritePacket = byteWriteChannel.writePacket(bytePacketBuilder.build(), dVar);
            return objWritePacket == a.f15033i ? objWritePacket : t0.f22605a;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Object writePacket$$forInline(ByteWriteChannel byteWriteChannel, l<? super BytePacketBuilder, t0> lVar, d<? super t0> dVar) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            lVar.invoke(bytePacketBuilder);
            byteWriteChannel.writePacket(bytePacketBuilder.build(), dVar);
            return t0.f22605a;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
    
        if (r6.writePacket(r7, r0) == r5) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writePacketSuspend(io.ktor.utils.io.ByteWriteChannel r6, r7.p<? super io.ktor.utils.io.core.BytePacketBuilder, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteWriteChannelKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1 r0 = (io.ktor.utils.io.ByteWriteChannelKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1 r0 = new io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L41
            if (r1 == r3) goto L33
            if (r1 != r2) goto L2b
            k2.c.G(r8)
            goto L68
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.core.BytePacketBuilder r7 = (io.ktor.utils.io.core.BytePacketBuilder) r7
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L3f
            goto L57
        L3f:
            r6 = move-exception
            goto L6d
        L41:
            k2.c.G(r8)
            io.ktor.utils.io.core.BytePacketBuilder r8 = new io.ktor.utils.io.core.BytePacketBuilder
            r8.<init>(r4, r3, r4)
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L6b
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L6b
            r0.label = r3     // Catch: java.lang.Throwable -> L6b
            java.lang.Object r7 = r7.invoke(r8, r0)     // Catch: java.lang.Throwable -> L6b
            if (r7 != r5) goto L56
            goto L67
        L56:
            r7 = r8
        L57:
            io.ktor.utils.io.core.ByteReadPacket r7 = r7.build()     // Catch: java.lang.Throwable -> L3f
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r2
            java.lang.Object r6 = r6.writePacket(r7, r0)
            if (r6 != r5) goto L68
        L67:
            return r5
        L68:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L6b:
            r6 = move-exception
            r7 = r8
        L6d:
            r7.release()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteWriteChannelKt.writePacketSuspend(io.ktor.utils.io.ByteWriteChannel, r7.p, d7.d):java.lang.Object");
    }

    public static final Object writeShort(ByteWriteChannel byteWriteChannel, int i10, d<? super t0> dVar) {
        Object objWriteShort = byteWriteChannel.writeShort((short) (i10 & DatagramKt.MAX_DATAGRAM_SIZE), dVar);
        return objWriteShort == a.f15033i ? objWriteShort : t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object writeStringUtf8(ByteWriteChannel byteWriteChannel, CharSequence charSequence, d<? super t0> dVar) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            StringsKt.writeText$default(bytePacketBuilder, charSequence, 0, 0, (Charset) null, 14, (Object) null);
            Object objWritePacket = byteWriteChannel.writePacket(bytePacketBuilder.build(), dVar);
            return objWritePacket == a.f15033i ? objWritePacket : t0.f22605a;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final Object writeInt(ByteWriteChannel byteWriteChannel, long j10, ByteOrder byteOrder, d<? super t0> dVar) {
        Object objWriteInt = ChannelLittleEndianKt.writeInt(byteWriteChannel, (int) j10, byteOrder, dVar);
        return objWriteInt == a.f15033i ? objWriteInt : t0.f22605a;
    }

    public static final Object writeShort(ByteWriteChannel byteWriteChannel, int i10, ByteOrder byteOrder, d<? super t0> dVar) {
        Object objWriteShort = ChannelLittleEndianKt.writeShort(byteWriteChannel, (short) (i10 & DatagramKt.MAX_DATAGRAM_SIZE), byteOrder, dVar);
        return objWriteShort == a.f15033i ? objWriteShort : t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object writeStringUtf8(ByteWriteChannel byteWriteChannel, String str, d<? super t0> dVar) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            StringsKt.writeText$default(bytePacketBuilder, str, 0, 0, (Charset) null, 14, (Object) null);
            Object objWritePacket = byteWriteChannel.writePacket(bytePacketBuilder.build(), dVar);
            return objWritePacket == a.f15033i ? objWritePacket : t0.f22605a;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }
}
