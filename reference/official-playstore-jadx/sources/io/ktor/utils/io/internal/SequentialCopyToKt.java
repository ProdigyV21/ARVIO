package io.ktor.utils.io.internal;

import androidx.media3.container.NalUnitUtil;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\u001a'\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\t\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0080@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a'\u0010\u000b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lio/ktor/utils/io/ByteChannelSequentialBase;", "dst", "", "closeOnEnd", "Lx6/t0;", "joinToImpl", "(Lio/ktor/utils/io/ByteChannelSequentialBase;Lio/ktor/utils/io/ByteChannelSequentialBase;ZLd7/d;)Ljava/lang/Object;", "", "limit", "copyToSequentialImpl", "(Lio/ktor/utils/io/ByteChannelSequentialBase;Lio/ktor/utils/io/ByteChannelSequentialBase;JLd7/d;)Ljava/lang/Object;", "copyToTail", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SequentialCopyToKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.internal.SequentialCopyToKt$copyToSequentialImpl$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.internal.SequentialCopyToKt", f = "SequentialCopyTo.kt", l = {26, 31, NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI}, m = "copyToSequentialImpl")
    public static final class AnonymousClass1 extends c {
        long J$0;
        long J$1;
        long J$2;
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
            return SequentialCopyToKt.copyToSequentialImpl(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.internal.SequentialCopyToKt", f = "SequentialCopyTo.kt", l = {60, 66}, m = "copyToTail")
    public static final class C20481 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20481(d<? super C20481> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SequentialCopyToKt.copyToTail(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.internal.SequentialCopyToKt", f = "SequentialCopyTo.kt", l = {7}, m = "joinToImpl")
    public static final class C20491 extends c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C20491(d<? super C20491> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SequentialCopyToKt.joinToImpl(null, null, false, this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f4, code lost:
    
        if (r0.awaitAtLeastNBytesAvailableForWrite$ktor_io(1, r2) == r9) goto L48;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00d7 -> B:43:0x00dc). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00e2 -> B:49:0x00f7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00f4 -> B:49:0x00f7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyToSequentialImpl(io.ktor.utils.io.ByteChannelSequentialBase r18, io.ktor.utils.io.ByteChannelSequentialBase r19, long r20, d7.d<? super java.lang.Long> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.SequentialCopyToKt.copyToSequentialImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteChannelSequentialBase, long, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v9, types: [io.ktor.utils.io.core.internal.ChunkBuffer] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyToTail(io.ktor.utils.io.ByteChannelSequentialBase r8, io.ktor.utils.io.ByteChannelSequentialBase r9, long r10, d7.d<? super java.lang.Long> r12) throws java.lang.Throwable {
        /*
            boolean r0 = r12 instanceof io.ktor.utils.io.internal.SequentialCopyToKt.C20481
            if (r0 == 0) goto L13
            r0 = r12
            io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1 r0 = (io.ktor.utils.io.internal.SequentialCopyToKt.C20481) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1 r0 = new io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L4d
            if (r1 == r3) goto L3c
            if (r1 != r2) goto L34
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.core.internal.ChunkBuffer r9 = (io.ktor.utils.io.core.internal.ChunkBuffer) r9
            k2.c.G(r12)     // Catch: java.lang.Throwable -> L31
            goto Lad
        L31:
            r8 = move-exception
            goto Lbd
        L34:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3c:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            io.ktor.utils.io.core.internal.ChunkBuffer r9 = (io.ktor.utils.io.core.internal.ChunkBuffer) r9
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            k2.c.G(r12)     // Catch: java.lang.Throwable -> L31
            r7 = r9
            r9 = r8
            r8 = r12
            r12 = r7
            goto L77
        L4d:
            k2.c.G(r12)
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r12 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r12 = r12.getPool()
            java.lang.Object r12 = r12.borrow()
            io.ktor.utils.io.core.internal.ChunkBuffer r12 = (io.ktor.utils.io.core.internal.ChunkBuffer) r12
            int r1 = r12.getCapacity()     // Catch: java.lang.Throwable -> L9a
            long r5 = (long) r1     // Catch: java.lang.Throwable -> L9a
            int r1 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r1 <= 0) goto L66
            r10 = r5
        L66:
            int r10 = (int) r10     // Catch: java.lang.Throwable -> L9a
            r12.resetForWrite(r10)     // Catch: java.lang.Throwable -> L9a
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L9a
            r0.L$1 = r12     // Catch: java.lang.Throwable -> L9a
            r0.label = r3     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r8 = r8.readAvailable(r12, r0)     // Catch: java.lang.Throwable -> L9a
            if (r8 != r4) goto L77
            goto Lab
        L77:
            java.lang.Number r8 = (java.lang.Number) r8     // Catch: java.lang.Throwable -> L9a
            int r8 = r8.intValue()     // Catch: java.lang.Throwable -> L9a
            r10 = -1
            if (r8 != r10) goto L9c
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r8 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE     // Catch: java.lang.Throwable -> L9a
            io.ktor.utils.io.pool.ObjectPool r9 = r8.getPool()     // Catch: java.lang.Throwable -> L9a
            r12.release(r9)     // Catch: java.lang.Throwable -> L9a
            java.lang.Long r9 = new java.lang.Long     // Catch: java.lang.Throwable -> L9a
            r10 = 0
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L9a
            io.ktor.utils.io.pool.ObjectPool r8 = r8.getPool()
            r12.release(r8)
            return r9
        L98:
            r9 = r12
            goto Lbd
        L9a:
            r8 = move-exception
            goto L98
        L9c:
            r0.L$0 = r12     // Catch: java.lang.Throwable -> L9a
            r10 = 0
            r0.L$1 = r10     // Catch: java.lang.Throwable -> L9a
            r0.I$0 = r8     // Catch: java.lang.Throwable -> L9a
            r0.label = r2     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r9 = r9.writeFully(r12, r0)     // Catch: java.lang.Throwable -> L9a
            if (r9 != r4) goto Lac
        Lab:
            return r4
        Lac:
            r9 = r12
        Lad:
            long r10 = (long) r8
            java.lang.Long r8 = new java.lang.Long     // Catch: java.lang.Throwable -> L31
            r8.<init>(r10)     // Catch: java.lang.Throwable -> L31
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r10 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r10 = r10.getPool()
            r9.release(r10)
            return r8
        Lbd:
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r10 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r10 = r10.getPool()
            r9.release(r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.SequentialCopyToKt.copyToTail(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteChannelSequentialBase, long, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object joinToImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, io.ktor.utils.io.ByteChannelSequentialBase r5, boolean r6, d7.d<? super x6.t0> r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.internal.SequentialCopyToKt.C20491
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1 r0 = (io.ktor.utils.io.internal.SequentialCopyToKt.C20491) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1 r0 = new io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L34
            if (r1 != r2) goto L2c
            boolean r6 = r0.Z$0
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.ByteChannelSequentialBase r5 = (io.ktor.utils.io.ByteChannelSequentialBase) r5
            k2.c.G(r7)
            goto L4b
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.Z$0 = r6
            r0.label = r2
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r4 = copyToSequentialImpl(r4, r5, r1, r0)
            e7.a r7 = e7.a.f15033i
            if (r4 != r7) goto L4b
            return r7
        L4b:
            if (r6 == 0) goto L50
            io.ktor.utils.io.ByteWriteChannelKt.close(r5)
        L50:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.SequentialCopyToKt.joinToImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteChannelSequentialBase, boolean, d7.d):java.lang.Object");
    }
}
