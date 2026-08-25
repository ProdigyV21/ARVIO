package io.ktor.util;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import ka.k0;
import ka.m0;
import ka.n1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u001a%\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000b\u001a\u0017\u0010\r\u001a\u00020\f*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lka/k0;", "coroutineScope", "Lx6/x;", "split", "(Lio/ktor/utils/io/ByteReadChannel;Lka/k0;)Lx6/x;", "Lio/ktor/utils/io/ByteWriteChannel;", "first", "second", "Lx6/t0;", "copyToBoth", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/ByteWriteChannel;)V", "", "toByteArray", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "", "CHUNK_BUFFER_SIZE", "J", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteChannelsKt {
    private static final long CHUNK_BUFFER_SIZE = 4096;

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$copyToBoth$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.ByteChannelsKt$copyToBoth$1", f = "ByteChannels.kt", l = {59, 61, 62}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ByteWriteChannel $first;
        final /* synthetic */ ByteWriteChannel $second;
        final /* synthetic */ ByteReadChannel $this_copyToBoth;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, ByteWriteChannel byteWriteChannel2, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$this_copyToBoth = byteReadChannel;
            this.$first = byteWriteChannel;
            this.$second = byteWriteChannel2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$this_copyToBoth, this.$first, this.$second, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00cc, code lost:
        
            if (r6.writePacket(r12, r11) != r5) goto L42;
         */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0069 A[Catch: all -> 0x005b, TryCatch #5 {all -> 0x005b, blocks: (B:42:0x00d8, B:24:0x0061, B:26:0x0069, B:28:0x0071, B:30:0x0079, B:33:0x0090, B:51:0x00e7, B:52:0x00e8, B:55:0x00fb, B:19:0x0057, B:49:0x00e5, B:48:0x00e2, B:45:0x00dd, B:34:0x0099, B:41:0x00cf), top: B:72:0x0057, inners: #3, #6 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b6 A[Catch: all -> 0x0025, PHI: r0 r6 r7 r8 r10 r12
          0x00b6: PHI (r0v6 io.ktor.utils.io.ByteReadChannel) = (r0v7 io.ktor.utils.io.ByteReadChannel), (r0v11 io.ktor.utils.io.ByteReadChannel) binds: [B:36:0x00b3, B:16:0x0049] A[DONT_GENERATE, DONT_INLINE]
          0x00b6: PHI (r6v1 io.ktor.utils.io.ByteWriteChannel) = (r6v2 io.ktor.utils.io.ByteWriteChannel), (r6v7 io.ktor.utils.io.ByteWriteChannel) binds: [B:36:0x00b3, B:16:0x0049] A[DONT_GENERATE, DONT_INLINE]
          0x00b6: PHI (r7v1 io.ktor.utils.io.ByteWriteChannel) = (r7v2 io.ktor.utils.io.ByteWriteChannel), (r7v6 io.ktor.utils.io.ByteWriteChannel) binds: [B:36:0x00b3, B:16:0x0049] A[DONT_GENERATE, DONT_INLINE]
          0x00b6: PHI (r8v1 java.io.Closeable) = (r8v3 java.io.Closeable), (r8v8 java.io.Closeable) binds: [B:36:0x00b3, B:16:0x0049] A[DONT_GENERATE, DONT_INLINE]
          0x00b6: PHI (r10v0 int) = (r10v1 int), (r10v4 int) binds: [B:36:0x00b3, B:16:0x0049] A[DONT_GENERATE, DONT_INLINE]
          0x00b6: PHI (r12v7 io.ktor.utils.io.core.ByteReadPacket) = (r12v14 io.ktor.utils.io.core.ByteReadPacket), (r12v26 io.ktor.utils.io.core.ByteReadPacket) binds: [B:36:0x00b3, B:16:0x0049] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #2 {all -> 0x0025, blocks: (B:8:0x0020, B:35:0x009c, B:38:0x00b6), top: B:67:0x0020 }] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00fb A[Catch: all -> 0x005b, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x005b, blocks: (B:42:0x00d8, B:24:0x0061, B:26:0x0069, B:28:0x0071, B:30:0x0079, B:33:0x0090, B:51:0x00e7, B:52:0x00e8, B:55:0x00fb, B:19:0x0057, B:49:0x00e5, B:48:0x00e2, B:45:0x00dd, B:34:0x0099, B:41:0x00cf), top: B:72:0x0057, inners: #3, #6 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00cc -> B:42:0x00d8). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 278
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$copyToBoth$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ ByteWriteChannel $first;
        final /* synthetic */ ByteWriteChannel $second;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteWriteChannel byteWriteChannel, ByteWriteChannel byteWriteChannel2) {
            super(1);
            this.$first = byteWriteChannel;
            this.$second = byteWriteChannel2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            if (th == null) {
                return;
            }
            this.$first.close(th);
            this.$second.close(th);
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$split$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.ByteChannelsKt$split$1", f = "ByteChannels.kt", l = {25, 29}, m = "invokeSuspend")
    public static final class C19391 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ByteChannel $first;
        final /* synthetic */ ByteChannel $second;
        final /* synthetic */ ByteReadChannel $this_split;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C19391(ByteReadChannel byteReadChannel, ByteChannel byteChannel, ByteChannel byteChannel2, d<? super C19391> dVar) {
            super(2, dVar);
            this.$this_split = byteReadChannel;
            this.$first = byteChannel;
            this.$second = byteChannel2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C19391 c19391 = new C19391(this.$this_split, this.$first, this.$second, dVar);
            c19391.L$0 = obj;
            return c19391;
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0087, code lost:
        
            if (r11 != r4) goto L29;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0040 A[Catch: all -> 0x002e, TryCatch #4 {all -> 0x002e, blocks: (B:30:0x008c, B:19:0x0038, B:21:0x0040, B:25:0x0053, B:39:0x009c, B:42:0x00af, B:38:0x009b, B:14:0x0029, B:36:0x0099, B:35:0x0096, B:32:0x0091, B:7:0x0015, B:29:0x008a, B:26:0x005a), top: B:57:0x0007, inners: #2, #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x009c A[Catch: all -> 0x002e, TRY_LEAVE, TryCatch #4 {all -> 0x002e, blocks: (B:30:0x008c, B:19:0x0038, B:21:0x0040, B:25:0x0053, B:39:0x009c, B:42:0x00af, B:38:0x009b, B:14:0x0029, B:36:0x0099, B:35:0x0096, B:32:0x0091, B:7:0x0015, B:29:0x008a, B:26:0x005a), top: B:57:0x0007, inners: #2, #3 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0087 -> B:29:0x008a). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 207
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt.C19391.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C19391) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$split$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19402 extends r implements l<Throwable, t0> {
        final /* synthetic */ ByteChannel $first;
        final /* synthetic */ ByteChannel $second;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C19402(ByteChannel byteChannel, ByteChannel byteChannel2) {
            super(1);
            this.$first = byteChannel;
            this.$second = byteChannel2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            if (th == null) {
                return;
            }
            this.$first.cancel(th);
            this.$second.cancel(th);
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.ByteChannelsKt$toByteArray$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.util.ByteChannelsKt", f = "ByteChannels.kt", l = {TsExtractor.TS_STREAM_TYPE_DVBSUBS}, m = "toByteArray")
    public static final class C19411 extends c {
        int label;
        /* synthetic */ Object result;

        public C19411(d<? super C19411> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelsKt.toByteArray(null, this);
        }
    }

    public static final void copyToBoth(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, ByteWriteChannel byteWriteChannel2) {
        m0.p(n1.f19624i, x0.f19654c, 0, new AnonymousClass1(byteReadChannel, byteWriteChannel, byteWriteChannel2, null), 2).invokeOnCompletion(new AnonymousClass2(byteWriteChannel, byteWriteChannel2));
    }

    public static final x split(ByteReadChannel byteReadChannel, k0 k0Var) {
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(true);
        ByteChannel ByteChannel2 = ByteChannelKt.ByteChannel(true);
        m0.p(k0Var, null, 0, new C19391(byteReadChannel, ByteChannel, ByteChannel2, null), 3).invokeOnCompletion(new C19402(ByteChannel, ByteChannel2));
        return new x(ByteChannel, ByteChannel2);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object toByteArray(io.ktor.utils.io.ByteReadChannel r8, d7.d<? super byte[]> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.util.ByteChannelsKt.C19411
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.util.ByteChannelsKt$toByteArray$1 r0 = (io.ktor.util.ByteChannelsKt.C19411) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            io.ktor.util.ByteChannelsKt$toByteArray$1 r0 = new io.ktor.util.ByteChannelsKt$toByteArray$1
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r9 = r4.result
            int r0 = r4.label
            r7 = 1
            if (r0 == 0) goto L2f
            if (r0 != r7) goto L27
            k2.c.G(r9)
            goto L42
        L27:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2f:
            k2.c.G(r9)
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            r1 = r8
            java.lang.Object r9 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            e7.a r8 = e7.a.f15033i
            if (r9 != r8) goto L42
            return r8
        L42:
            io.ktor.utils.io.core.ByteReadPacket r9 = (io.ktor.utils.io.core.ByteReadPacket) r9
            r8 = 0
            r0 = 0
            byte[] r8 = io.ktor.utils.io.core.StringsKt.readBytes$default(r9, r8, r7, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt.toByteArray(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }
}
