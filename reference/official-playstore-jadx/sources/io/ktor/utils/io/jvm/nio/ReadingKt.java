package io.ktor.utils.io.jvm.nio;

import androidx.media3.extractor.AacUtil;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteWriteChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\u0005\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Ljava/nio/channels/ReadableByteChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "ch", "", "limit", "copyTo", "(Ljava/nio/channels/ReadableByteChannel;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "Ljava/nio/channels/Pipe;", "(Ljava/nio/channels/Pipe;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReadingKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.nio.ReadingKt$copyTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.jvm.nio.ReadingKt", f = "Reading.kt", l = {AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE}, m = "copyTo")
    public static final class AnonymousClass1 extends c {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReadingKt.copyTo((ReadableByteChannel) null, (ByteWriteChannel) null, 0L, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0099 -> B:31:0x009c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(java.nio.channels.ReadableByteChannel r9, io.ktor.utils.io.ByteWriteChannel r10, long r11, d7.d<? super java.lang.Long> r13) {
        /*
            boolean r0 = r13 instanceof io.ktor.utils.io.jvm.nio.ReadingKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r13
            io.ktor.utils.io.jvm.nio.ReadingKt$copyTo$1 r0 = (io.ktor.utils.io.jvm.nio.ReadingKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.jvm.nio.ReadingKt$copyTo$1 r0 = new io.ktor.utils.io.jvm.nio.ReadingKt$copyTo$1
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L45
            if (r1 != r2) goto L3d
            int r9 = r0.I$0
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$3
            r7.l r12 = (r7.l) r12
            java.lang.Object r1 = r0.L$2
            kotlin.jvm.internal.f0 r1 = (kotlin.jvm.internal.f0) r1
            java.lang.Object r3 = r0.L$1
            kotlin.jvm.internal.j0 r3 = (kotlin.jvm.internal.j0) r3
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r4 = (io.ktor.utils.io.ByteWriteChannel) r4
            k2.c.G(r13)
            r6 = r3
            r3 = r12
            r11 = r10
            r10 = r4
            goto L9c
        L3d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L45:
            k2.c.G(r13)
            r3 = 0
            int r13 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r13 < 0) goto La8
            boolean r13 = r9 instanceof java.nio.channels.SelectableChannel
            if (r13 == 0) goto L64
            r13 = r9
            java.nio.channels.SelectableChannel r13 = (java.nio.channels.SelectableChannel) r13
            boolean r13 = r13.isBlocking()
            if (r13 == 0) goto L5c
            goto L64
        L5c:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Non-blocking channels are not supported"
            r9.<init>(r10)
            throw r9
        L64:
            kotlin.jvm.internal.j0 r6 = new kotlin.jvm.internal.j0
            r6.<init>()
            kotlin.jvm.internal.f0 r8 = new kotlin.jvm.internal.f0
            r8.<init>()
            io.ktor.utils.io.jvm.nio.ReadingKt$copyTo$copy$1 r3 = new io.ktor.utils.io.jvm.nio.ReadingKt$copyTo$copy$1
            r7 = r9
            r4 = r11
            r3.<init>(r4, r6, r7, r8)
            boolean r9 = r10.getAutoFlush()
            r9 = r9 ^ r2
            r1 = r8
        L7b:
            long r4 = r6.f19745i
            int r13 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r13 >= 0) goto La2
            boolean r13 = r1.f19738i
            if (r13 != 0) goto La2
            r0.L$0 = r10
            r0.L$1 = r6
            r0.L$2 = r1
            r0.L$3 = r3
            r0.J$0 = r11
            r0.I$0 = r9
            r0.label = r2
            java.lang.Object r13 = r10.write(r2, r3, r0)
            e7.a r4 = e7.a.f15033i
            if (r13 != r4) goto L9c
            return r4
        L9c:
            if (r9 == 0) goto L7b
            r10.flush()
            goto L7b
        La2:
            java.lang.Long r9 = new java.lang.Long
            r9.<init>(r4)
            return r9
        La8:
            r4 = r11
            java.lang.String r9 = "Limit shouldn't be negative: "
            java.lang.String r9 = androidx.compose.foundation.c.q(r4, r9)
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r9 = r9.toString()
            r10.<init>(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.nio.ReadingKt.copyTo(java.nio.channels.ReadableByteChannel, io.ktor.utils.io.ByteWriteChannel, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(ReadableByteChannel readableByteChannel, ByteWriteChannel byteWriteChannel, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyTo(readableByteChannel, byteWriteChannel, j10, (d<? super Long>) dVar);
    }

    public static /* synthetic */ Object copyTo$default(Pipe pipe, ByteWriteChannel byteWriteChannel, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyTo(pipe, byteWriteChannel, j10, (d<? super Long>) dVar);
    }

    public static final Object copyTo(Pipe pipe, ByteWriteChannel byteWriteChannel, long j10, d<? super Long> dVar) {
        return copyTo(pipe.source(), byteWriteChannel, j10, dVar);
    }
}
