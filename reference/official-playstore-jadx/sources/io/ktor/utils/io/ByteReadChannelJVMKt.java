package io.ktor.utils.io;

import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.utils.io.internal.SequentialCopyToKt;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\u001a'\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\t\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0007\u001a'\u0010\f\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a'\u0010\u000e\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "dst", "", "closeOnEnd", "Lx6/t0;", "joinTo", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;ZLd7/d;)Ljava/lang/Object;", "close", "joinToImplSuspend", "", "limit", "copyTo", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "copyToImpl", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteReadChannelJVMKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteReadChannelJVMKt", f = "ByteReadChannelJVM.kt", l = {309, MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST}, m = "copyToImpl")
    public static final class AnonymousClass1 extends c {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
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
            return ByteReadChannelJVMKt.copyToImpl(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteReadChannelJVMKt", f = "ByteReadChannelJVM.kt", l = {267}, m = "joinToImplSuspend")
    public static final class C20301 extends c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C20301(d<? super C20301> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelJVMKt.joinToImplSuspend(null, null, false, this);
        }
    }

    public static final Object copyTo(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, d<? super Long> dVar) {
        if (byteReadChannel != byteWriteChannel) {
            return j10 == 0 ? new Long(0L) : ((byteReadChannel instanceof ByteBufferChannel) && (byteWriteChannel instanceof ByteBufferChannel)) ? ((ByteBufferChannel) byteWriteChannel).copyDirect$ktor_io((ByteBufferChannel) byteReadChannel, j10, null, dVar) : ((byteReadChannel instanceof ByteChannelSequentialBase) && (byteWriteChannel instanceof ByteChannelSequentialBase)) ? SequentialCopyToKt.copyToSequentialImpl((ByteChannelSequentialBase) byteReadChannel, (ByteChannelSequentialBase) byteWriteChannel, Long.MAX_VALUE, dVar) : copyToImpl(byteReadChannel, byteWriteChannel, j10, dVar);
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008d A[Catch: all -> 0x00ee, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00ee, blocks: (B:38:0x00e4, B:40:0x00ea, B:26:0x008d), top: B:59:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c2 A[Catch: all -> 0x0046, TRY_LEAVE, TryCatch #1 {all -> 0x0046, blocks: (B:13:0x003e, B:30:0x00b9, B:32:0x00c2, B:47:0x00fd, B:21:0x0065), top: B:57:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v3, types: [int] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v4, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00d9 -> B:15:0x0042). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyToImpl(io.ktor.utils.io.ByteReadChannel r19, io.ktor.utils.io.ByteWriteChannel r20, long r21, d7.d<? super java.lang.Long> r23) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelJVMKt.copyToImpl(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, d7.d):java.lang.Object");
    }

    public static final Object joinTo(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, boolean z, d<? super t0> dVar) throws Throwable {
        if (byteWriteChannel == byteReadChannel) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        boolean z5 = byteReadChannel instanceof ByteBufferChannel;
        t0 t0Var = t0.f22605a;
        a aVar = a.f15033i;
        if (z5 && (byteWriteChannel instanceof ByteBufferChannel)) {
            Object objJoinFrom$ktor_io = ((ByteBufferChannel) byteWriteChannel).joinFrom$ktor_io((ByteBufferChannel) byteReadChannel, z, dVar);
            return objJoinFrom$ktor_io == aVar ? objJoinFrom$ktor_io : t0Var;
        }
        Object objJoinToImplSuspend = joinToImplSuspend(byteReadChannel, byteWriteChannel, z, dVar);
        return objJoinToImplSuspend == aVar ? objJoinToImplSuspend : t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object joinToImplSuspend(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.ByteWriteChannel r5, boolean r6, d7.d<? super x6.t0> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteReadChannelJVMKt.C20301
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1 r0 = (io.ktor.utils.io.ByteReadChannelJVMKt.C20301) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1 r0 = new io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1
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
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
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
            java.lang.Object r4 = copyTo(r4, r5, r1, r0)
            e7.a r7 = e7.a.f15033i
            if (r4 != r7) goto L4b
            return r7
        L4b:
            if (r6 == 0) goto L51
            io.ktor.utils.io.ByteWriteChannelKt.close(r5)
            goto L54
        L51:
            r5.flush()
        L54:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelJVMKt.joinToImplSuspend(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, boolean, d7.d):java.lang.Object");
    }
}
