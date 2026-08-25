package io.ktor.utils.io.jvm.nio;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.channels.Pipe;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Ljava/nio/channels/WritableByteChannel;", "channel", "", "limit", "copyTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/channels/WritableByteChannel;JLd7/d;)Ljava/lang/Object;", "Ljava/nio/channels/Pipe;", "pipe", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/channels/Pipe;JLd7/d;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WritingKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.jvm.nio.WritingKt", f = "Writing.kt", l = {50}, m = "copyTo")
    public static final class AnonymousClass1 extends c {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WritingKt.copyTo((ByteReadChannel) null, (WritableByteChannel) null, 0L, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[PHI: r1 r6 r8 r10
      0x007c: PHI (r1v2 r7.l<? super java.nio.ByteBuffer, x6.t0>) = (r1v1 r7.l<? super java.nio.ByteBuffer, x6.t0>), (r1v3 r7.l<? super java.nio.ByteBuffer, x6.t0>) binds: [B:30:0x0072, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]
      0x007c: PHI (r6v10 io.ktor.utils.io.ByteReadChannel) = (r6v0 io.ktor.utils.io.ByteReadChannel), (r6v11 io.ktor.utils.io.ByteReadChannel) binds: [B:30:0x0072, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]
      0x007c: PHI (r8v2 long) = (r8v0 long), (r8v3 long) binds: [B:30:0x0072, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]
      0x007c: PHI (r10v10 kotlin.jvm.internal.j0) = (r10v5 kotlin.jvm.internal.j0), (r10v11 kotlin.jvm.internal.j0) binds: [B:30:0x0072, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0093 -> B:36:0x0096). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(io.ktor.utils.io.ByteReadChannel r6, java.nio.channels.WritableByteChannel r7, long r8, d7.d<? super java.lang.Long> r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.jvm.nio.WritingKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1 r0 = (io.ktor.utils.io.jvm.nio.WritingKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1 r0 = new io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L40
            if (r1 != r2) goto L38
            long r6 = r0.J$0
            java.lang.Object r8 = r0.L$2
            r7.l r8 = (r7.l) r8
            java.lang.Object r9 = r0.L$1
            kotlin.jvm.internal.j0 r9 = (kotlin.jvm.internal.j0) r9
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r1 = (io.ktor.utils.io.ByteReadChannel) r1
            k2.c.G(r10)
            r10 = r9
            r5 = r1
            r1 = r8
            r8 = r6
            r6 = r5
            goto L96
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            k2.c.G(r10)
            r3 = 0
            int r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r10 < 0) goto Lab
            boolean r10 = r7 instanceof java.nio.channels.SelectableChannel
            if (r10 == 0) goto L5f
            r10 = r7
            java.nio.channels.SelectableChannel r10 = (java.nio.channels.SelectableChannel) r10
            boolean r10 = r10.isBlocking()
            if (r10 == 0) goto L57
            goto L5f
        L57:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Non-blocking channels are not supported"
            r6.<init>(r7)
            throw r6
        L5f:
            boolean r10 = r6.isClosedForRead()
            if (r10 == 0) goto L72
            java.lang.Throwable r6 = r6.getClosedCause()
            if (r6 != 0) goto L71
            java.lang.Long r6 = new java.lang.Long
            r6.<init>(r3)
            return r6
        L71:
            throw r6
        L72:
            kotlin.jvm.internal.j0 r10 = new kotlin.jvm.internal.j0
            r10.<init>()
            io.ktor.utils.io.jvm.nio.WritingKt$copyTo$copy$1 r1 = new io.ktor.utils.io.jvm.nio.WritingKt$copyTo$copy$1
            r1.<init>(r8, r10, r7)
        L7c:
            long r3 = r10.f19745i
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 >= 0) goto L9c
            r0.L$0 = r6
            r0.L$1 = r10
            r0.L$2 = r1
            r0.J$0 = r8
            r0.label = r2
            r7 = 0
            java.lang.Object r7 = r6.read(r7, r1, r0)
            e7.a r3 = e7.a.f15033i
            if (r7 != r3) goto L96
            return r3
        L96:
            boolean r7 = r6.isClosedForRead()
            if (r7 == 0) goto L7c
        L9c:
            java.lang.Throwable r6 = r6.getClosedCause()
            if (r6 != 0) goto Laa
            long r6 = r10.f19745i
            java.lang.Long r8 = new java.lang.Long
            r8.<init>(r6)
            return r8
        Laa:
            throw r6
        Lab:
            java.lang.String r6 = "Limit shouldn't be negative: "
            java.lang.String r6 = androidx.compose.foundation.c.q(r8, r6)
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.nio.WritingKt.copyTo(io.ktor.utils.io.ByteReadChannel, java.nio.channels.WritableByteChannel, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, WritableByteChannel writableByteChannel, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, writableByteChannel, j10, (d<? super Long>) dVar);
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, Pipe pipe, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, pipe, j10, (d<? super Long>) dVar);
    }

    public static final Object copyTo(ByteReadChannel byteReadChannel, Pipe pipe, long j10, d<? super Long> dVar) {
        return copyTo(byteReadChannel, pipe.sink(), j10, dVar);
    }
}
