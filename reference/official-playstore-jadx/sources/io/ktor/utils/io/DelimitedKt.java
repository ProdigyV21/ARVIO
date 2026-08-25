package io.ktor.utils.io;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.utils.io.internal.UtilsKt;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a'\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\t\u001a/\u0010\f\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a#\u0010\u000f\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001b\u0010\u0011\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u001b\u0010\u0013\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0013\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Ljava/nio/ByteBuffer;", TtmlNode.RUBY_DELIMITER, "dst", "", "readUntilDelimiter", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "skipDelimiter", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "skipDelimiterSuspend", "copied0", "readUntilDelimiterSuspend", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;ILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/LookAheadSession;", "tryCopyUntilDelimiter", "(Lio/ktor/utils/io/LookAheadSession;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)I", "tryEnsureDelimiter", "(Lio/ktor/utils/io/LookAheadSession;Ljava/nio/ByteBuffer;)I", "startsWithDelimiter", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DelimitedKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.DelimitedKt$readUntilDelimiter$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSession;", "Lx6/t0;", "invoke", "(Lio/ktor/utils/io/LookAheadSession;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<LookAheadSession, t0> {
        final /* synthetic */ i0 $copied;
        final /* synthetic */ ByteBuffer $delimiter;
        final /* synthetic */ ByteBuffer $dst;
        final /* synthetic */ f0 $endFound;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, f0 f0Var, i0 i0Var) {
            super(1);
            this.$delimiter = byteBuffer;
            this.$dst = byteBuffer2;
            this.$endFound = f0Var;
            this.$copied = i0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LookAheadSession) obj);
            return t0.f22605a;
        }

        public final void invoke(LookAheadSession lookAheadSession) {
            do {
                int iTryCopyUntilDelimiter = DelimitedKt.tryCopyUntilDelimiter(lookAheadSession, this.$delimiter, this.$dst);
                if (iTryCopyUntilDelimiter == 0) {
                    return;
                }
                if (iTryCopyUntilDelimiter < 0) {
                    this.$endFound.f19738i = true;
                    iTryCopyUntilDelimiter = -iTryCopyUntilDelimiter;
                }
                this.$copied.f19744i += iTryCopyUntilDelimiter;
                if (!this.$dst.hasRemaining()) {
                    return;
                }
            } while (!this.$endFound.f19738i);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.DelimitedKt", f = "Delimited.kt", l = {81, 113}, m = "readUntilDelimiterSuspend")
    public static final class AnonymousClass1 extends c {
        int I$0;
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
            return DelimitedKt.readUntilDelimiterSuspend(null, null, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.DelimitedKt$skipDelimiter$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSession;", "Lx6/t0;", "invoke", "(Lio/ktor/utils/io/LookAheadSession;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C20412 extends r implements l<LookAheadSession, t0> {
        final /* synthetic */ ByteBuffer $delimiter;
        final /* synthetic */ f0 $found;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C20412(f0 f0Var, ByteBuffer byteBuffer) {
            super(1);
            this.$found = f0Var;
            this.$delimiter = byteBuffer;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LookAheadSession) obj);
            return t0.f22605a;
        }

        public final void invoke(LookAheadSession lookAheadSession) {
            this.$found.f19738i = DelimitedKt.tryEnsureDelimiter(lookAheadSession, this.$delimiter) == this.$delimiter.remaining();
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.DelimitedKt$skipDelimiterSuspend$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSuspendSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/LookAheadSuspendSession;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.utils.io.DelimitedKt$skipDelimiterSuspend$2", f = "Delimited.kt", l = {66}, m = "invokeSuspend")
    public static final class C20422 extends j implements p<LookAheadSuspendSession, d<? super t0>, Object> {
        final /* synthetic */ ByteBuffer $delimiter;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C20422(ByteBuffer byteBuffer, d<? super C20422> dVar) {
            super(2, dVar);
            this.$delimiter = byteBuffer;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C20422 c20422 = new C20422(this.$delimiter, dVar);
            c20422.L$0 = obj;
            return c20422;
        }

        @Override // r7.p
        public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, d<? super t0> dVar) {
            return ((C20422) create(lookAheadSuspendSession, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IOException {
            LookAheadSuspendSession lookAheadSuspendSession;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                lookAheadSuspendSession = (LookAheadSuspendSession) this.L$0;
                int iRemaining = this.$delimiter.remaining();
                this.L$0 = lookAheadSuspendSession;
                this.label = 1;
                Object objAwaitAtLeast = lookAheadSuspendSession.awaitAtLeast(iRemaining, this);
                a aVar = a.f15033i;
                if (objAwaitAtLeast == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                lookAheadSuspendSession = (LookAheadSuspendSession) this.L$0;
                k2.c.G(obj);
            }
            if (DelimitedKt.tryEnsureDelimiter(lookAheadSuspendSession, this.$delimiter) == this.$delimiter.remaining()) {
                return t0.f22605a;
            }
            throw new IOException("Broken delimiter occurred");
        }
    }

    public static final Object readUntilDelimiter(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, d<? super Integer> dVar) {
        int i10;
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (byteBuffer == byteBuffer2) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        i0 i0Var = new i0();
        f0 f0Var = new f0();
        byteReadChannel.lookAhead(new AnonymousClass2(byteBuffer, byteBuffer2, f0Var, i0Var));
        if (i0Var.f19744i == 0 && byteReadChannel.isClosedForRead()) {
            i10 = -1;
        } else {
            if (byteBuffer2.hasRemaining() && !f0Var.f19738i) {
                return readUntilDelimiterSuspend(byteReadChannel, byteBuffer, byteBuffer2, i0Var.f19744i, dVar);
            }
            i10 = i0Var.f19744i;
        }
        return new Integer(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readUntilDelimiterSuspend(io.ktor.utils.io.ByteReadChannel r12, java.nio.ByteBuffer r13, java.nio.ByteBuffer r14, int r15, d7.d<? super java.lang.Integer> r16) {
        /*
            r0 = r16
            boolean r1 = r0 instanceof io.ktor.utils.io.DelimitedKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1 r1 = (io.ktor.utils.io.DelimitedKt.AnonymousClass1) r1
            int r2 = r1.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r2 & r4
            if (r5 == 0) goto L16
            int r2 = r2 - r4
            r1.label = r2
        L14:
            r7 = r1
            goto L1c
        L16:
            io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1 r1 = new io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1
            r1.<init>(r0)
            goto L14
        L1c:
            java.lang.Object r0 = r7.result
            int r1 = r7.label
            r8 = 2
            r9 = 1
            e7.a r10 = e7.a.f15033i
            if (r1 == 0) goto L49
            if (r1 == r9) goto L39
            if (r1 != r8) goto L31
            int r1 = r7.I$0
            k2.c.G(r0)
            goto L9a
        L31:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L39:
            java.lang.Object r1 = r7.L$2
            kotlin.jvm.internal.f0 r1 = (kotlin.jvm.internal.f0) r1
            java.lang.Object r2 = r7.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r3 = r7.L$0
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            k2.c.G(r0)
            goto L73
        L49:
            k2.c.G(r0)
            java.lang.String r0 = "Failed requirement."
            if (r13 == r14) goto Lba
            if (r15 < 0) goto Lb4
            kotlin.jvm.internal.f0 r4 = new kotlin.jvm.internal.f0
            r4.<init>()
            io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1 r0 = new io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1
            r6 = 0
            r5 = r12
            r2 = r13
            r3 = r14
            r1 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7.L$0 = r12
            r7.L$1 = r14
            r7.L$2 = r4
            r7.label = r9
            java.lang.Object r0 = r12.lookAheadSuspend(r0, r7)
            if (r0 != r10) goto L70
            goto L96
        L70:
            r3 = r12
            r2 = r14
            r1 = r4
        L73:
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            if (r0 <= 0) goto La5
            boolean r4 = r3.isClosedForWrite()
            if (r4 == 0) goto La5
            boolean r1 = r1.f19738i
            if (r1 != 0) goto La5
            r1 = 0
            r7.L$0 = r1
            r7.L$1 = r1
            r7.L$2 = r1
            r7.I$0 = r0
            r7.label = r8
            java.lang.Object r1 = r3.readAvailable(r2, r7)
            if (r1 != r10) goto L97
        L96:
            return r10
        L97:
            r11 = r1
            r1 = r0
            r0 = r11
        L9a:
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            if (r0 >= 0) goto La3
            r0 = 0
        La3:
            int r0 = r0 + r1
            goto Lae
        La5:
            if (r0 != 0) goto Lae
            boolean r1 = r3.isClosedForRead()
            if (r1 == 0) goto Lae
            r0 = -1
        Lae:
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r0)
            return r1
        Lb4:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        Lba:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.DelimitedKt.readUntilDelimiterSuspend(io.ktor.utils.io.ByteReadChannel, java.nio.ByteBuffer, java.nio.ByteBuffer, int, d7.d):java.lang.Object");
    }

    public static final Object skipDelimiter(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, d<? super t0> dVar) {
        Object objSkipDelimiterSuspend;
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        f0 f0Var = new f0();
        byteReadChannel.lookAhead(new C20412(f0Var, byteBuffer));
        return (f0Var.f19738i || (objSkipDelimiterSuspend = skipDelimiterSuspend(byteReadChannel, byteBuffer, dVar)) != a.f15033i) ? t0.f22605a : objSkipDelimiterSuspend;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object skipDelimiterSuspend(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, d<? super t0> dVar) {
        Object objLookAheadSuspend = byteReadChannel.lookAheadSuspend(new C20422(byteBuffer, null), dVar);
        return objLookAheadSuspend == a.f15033i ? objLookAheadSuspend : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int startsWithDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        ByteBuffer byteBufferRequest = lookAheadSession.request(0, 1);
        if (byteBufferRequest == null) {
            return 0;
        }
        int iIndexOfPartial = UtilsKt.indexOfPartial(byteBufferRequest, byteBuffer);
        if (iIndexOfPartial != 0) {
            return -1;
        }
        int iMin = Math.min(byteBufferRequest.remaining() - iIndexOfPartial, byteBuffer.remaining());
        int iRemaining = byteBuffer.remaining() - iMin;
        if (iRemaining > 0) {
            ByteBuffer byteBufferRequest2 = lookAheadSession.request(iIndexOfPartial + iMin, iRemaining);
            if (byteBufferRequest2 == null) {
                return iMin;
            }
            if (!UtilsKt.startsWith(byteBufferRequest2, byteBuffer, iMin)) {
                return -1;
            }
        }
        return byteBuffer.remaining();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryCopyUntilDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int iPutAtMost$default;
        boolean z = false;
        ByteBuffer byteBufferRequest = lookAheadSession.request(0, 1);
        if (byteBufferRequest == null) {
            return 0;
        }
        int iIndexOfPartial = UtilsKt.indexOfPartial(byteBufferRequest, byteBuffer);
        if (iIndexOfPartial != -1) {
            int iMin = Math.min(byteBufferRequest.remaining() - iIndexOfPartial, byteBuffer.remaining());
            int iRemaining = byteBuffer.remaining() - iMin;
            if (iRemaining == 0) {
                iPutAtMost$default = UtilsKt.putLimited(byteBuffer2, byteBufferRequest, byteBufferRequest.position() + iIndexOfPartial);
            } else {
                ByteBuffer byteBufferDuplicate = byteBufferRequest.duplicate();
                ByteBuffer byteBufferRequest2 = lookAheadSession.request(iIndexOfPartial + iMin, 1);
                if (byteBufferRequest2 == null) {
                    iPutAtMost$default = UtilsKt.putLimited(byteBuffer2, byteBufferDuplicate, byteBufferDuplicate.position() + iIndexOfPartial);
                } else if (!UtilsKt.startsWith(byteBufferRequest2, byteBuffer, iMin)) {
                    iPutAtMost$default = UtilsKt.putLimited(byteBuffer2, byteBufferDuplicate, byteBufferDuplicate.position() + iIndexOfPartial + 1);
                } else if (byteBufferRequest2.remaining() >= iRemaining) {
                    iPutAtMost$default = UtilsKt.putLimited(byteBuffer2, byteBufferDuplicate, byteBufferDuplicate.position() + iIndexOfPartial);
                } else {
                    iPutAtMost$default = UtilsKt.putLimited(byteBuffer2, byteBufferDuplicate, byteBufferDuplicate.position() + iIndexOfPartial);
                }
            }
            z = true;
        } else {
            iPutAtMost$default = UtilsKt.putAtMost$default(byteBuffer2, byteBufferRequest, 0, 2, null);
        }
        lookAheadSession.mo7011consumed(iPutAtMost$default);
        return z ? -iPutAtMost$default : iPutAtMost$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryEnsureDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) throws IOException {
        int iStartsWithDelimiter = startsWithDelimiter(lookAheadSession, byteBuffer);
        if (iStartsWithDelimiter == -1) {
            throw new IOException("Failed to skip delimiter: actual bytes differ from delimiter bytes");
        }
        if (iStartsWithDelimiter < byteBuffer.remaining()) {
            return iStartsWithDelimiter;
        }
        lookAheadSession.mo7011consumed(byteBuffer.remaining());
        return byteBuffer.remaining();
    }
}
