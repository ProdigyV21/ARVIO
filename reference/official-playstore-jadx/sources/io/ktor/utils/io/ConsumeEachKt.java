package io.ktor.utils.io;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aS\u0010\u000b\u001a\u00020\n*\u00020\u00002:\u0010\t\u001a6\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\bH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f*j\u0010\r\"2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00060\u000122\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00060\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lx6/y;", ContentDisposition.Parameters.Name, "buffer", "", "last", "Lio/ktor/utils/io/ConsumeEachBufferVisitor;", "visitor", "Lx6/t0;", "consumeEachBufferRange", "(Lio/ktor/utils/io/ByteReadChannel;Lr7/p;Ld7/d;)Ljava/lang/Object;", "ConsumeEachBufferVisitor", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConsumeEachKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ConsumeEachKt$consumeEachBufferRange$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ConsumeEachKt", f = "ConsumeEach.kt", l = {46, 50, 53}, m = "consumeEachBufferRange")
    public static final class AnonymousClass1 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConsumeEachKt.consumeEachBufferRange(null, null, this);
        }
    }

    /* JADX WARN: Path cross not found for [B:39:0x00ef, B:42:0x00f7], limit reached: 63 */
    /* JADX WARN: Path cross not found for [B:49:0x012c, B:52:0x0133], limit reached: 63 */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d3 A[Catch: all -> 0x00d9, TryCatch #0 {all -> 0x00d9, blocks: (B:31:0x00c1, B:33:0x00d3, B:37:0x00e5, B:39:0x00ef, B:43:0x00f8, B:36:0x00df), top: B:61:0x00c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00df A[Catch: all -> 0x00d9, TryCatch #0 {all -> 0x00d9, blocks: (B:31:0x00c1, B:33:0x00d3, B:37:0x00e5, B:39:0x00ef, B:43:0x00f8, B:36:0x00df), top: B:61:0x00c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x0125 -> B:47:0x0128). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object consumeEachBufferRange(io.ktor.utils.io.ByteReadChannel r18, r7.p<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r19, d7.d<? super x6.t0> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ConsumeEachKt.consumeEachBufferRange(io.ktor.utils.io.ByteReadChannel, r7.p, d7.d):java.lang.Object");
    }

    private static final Object consumeEachBufferRange$$forInline(ByteReadChannel byteReadChannel, p<? super ByteBuffer, ? super Boolean, Boolean> pVar, d<? super t0> dVar) {
        boolean zBooleanValue;
        do {
            boolean z = true;
            Buffer empty = (Buffer) ReadSessionKt.requestBuffer(byteReadChannel, 1, dVar);
            if (empty == null) {
                empty = Buffer.INSTANCE.getEmpty();
            }
            try {
                Memory memoryM6745boximpl = Memory.m6745boximpl(empty.getMemory());
                Long lValueOf = Long.valueOf(empty.getReadPosition());
                long jLongValue = Long.valueOf(empty.getWritePosition()).longValue();
                long jLongValue2 = lValueOf.longValue();
                ByteBuffer byteBufferM6757slice87lwejk = jLongValue > jLongValue2 ? Memory.m6757slice87lwejk(memoryM6745boximpl.m6761unboximpl(), jLongValue2, jLongValue - jLongValue2) : Memory.INSTANCE.m6762getEmptySK3TCg8();
                if (byteBufferM6757slice87lwejk.remaining() != byteReadChannel.get_availableForRead() || !byteReadChannel.isClosedForWrite()) {
                    z = false;
                }
                zBooleanValue = ((Boolean) pVar.invoke(byteBufferM6757slice87lwejk, Boolean.valueOf(z))).booleanValue();
                ReadSessionKt.completeReadingFromBuffer(byteReadChannel, empty, Integer.valueOf(byteBufferM6757slice87lwejk.position()).intValue(), dVar);
                if (z && byteReadChannel.isClosedForRead()) {
                    break;
                }
            } catch (Throwable th) {
                ReadSessionKt.completeReadingFromBuffer(byteReadChannel, empty, 0, dVar);
                throw th;
            }
        } while (zBooleanValue);
        return t0.f22605a;
    }
}
