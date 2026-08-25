package com.arflix.tv.data.telegram;

import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.TsExtractor;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteWriteChannel;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1", f = "TelegramStreamingProxy.kt", l = {326, 111, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 335, 153, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 153, 153}, m = "invokeSuspend", v = 2)
public final class TelegramStreamingProxy$start$1$1$1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d7.d<? super t0>, Object> {
    int I$0;
    long J$0;
    long J$1;
    long J$2;
    long J$3;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ TelegramStreamingProxy this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/ByteWriteChannel;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1$1", f = "TelegramStreamingProxy.kt", l = {146, 148}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements p<ByteWriteChannel, d7.d<? super t0>, Object> {
        final /* synthetic */ long $end;
        final /* synthetic */ Integer $fileId;
        final /* synthetic */ String $localPath;
        final /* synthetic */ long $start;
        final /* synthetic */ long $totalSize;
        int I$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ TelegramStreamingProxy this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(long j10, long j11, TelegramStreamingProxy telegramStreamingProxy, Integer num, String str, long j12, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$start = j10;
            this.$end = j11;
            this.this$0 = telegramStreamingProxy;
            this.$fileId = num;
            this.$localPath = str;
            this.$totalSize = j12;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$start, this.$end, this.this$0, this.$fileId, this.$localPath, this.$totalSize, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(ByteWriteChannel byteWriteChannel, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(byteWriteChannel, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0081 -> B:26:0x0084). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
            /*
                r16 = this;
                r8 = r16
                java.lang.Object r0 = r8.L$0
                r9 = r0
                io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
                int r0 = r8.label
                r10 = 2
                r11 = 1
                e7.a r12 = e7.a.f15033i
                if (r0 == 0) goto L31
                if (r0 == r11) goto L26
                if (r0 != r10) goto L1e
                long r0 = r8.J$0
                java.lang.Object r2 = r8.L$1
                byte[] r2 = (byte[]) r2
                k2.c.G(r17)
                goto L84
            L1e:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L26:
                int r0 = r8.I$0
                long r1 = r8.J$0
                k2.c.G(r17)
                r5 = r0
                r0 = r17
                goto L68
            L31:
                k2.c.G(r17)
                long r0 = r8.$start
            L36:
                r3 = r0
                long r0 = r8.$end
                int r2 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r2 > 0) goto L88
                long r0 = r0 - r3
                r5 = 1
                long r0 = r0 + r5
                r5 = 2097152(0x200000, double:1.036131E-317)
                long r0 = java.lang.Math.min(r5, r0)
                int r5 = (int) r0
                com.arflix.tv.data.telegram.TelegramStreamingProxy r0 = r8.this$0
                java.lang.Integer r1 = r8.$fileId
                int r1 = r1.intValue()
                java.lang.String r2 = r8.$localPath
                long r6 = r8.$totalSize
                r8.L$0 = r9
                r13 = 0
                r8.L$1 = r13
                r8.J$0 = r3
                r8.I$0 = r5
                r8.label = r11
                java.lang.Object r0 = com.arflix.tv.data.telegram.TelegramStreamingProxy.access$downloadChunk(r0, r1, r2, r3, r5, r6, r8)
                if (r0 != r12) goto L67
                goto L80
            L67:
                r1 = r3
            L68:
                byte[] r0 = (byte[]) r0
                if (r0 == 0) goto L88
                int r3 = r0.length
                if (r3 != 0) goto L70
                goto L88
            L70:
                r8.L$0 = r9
                r8.L$1 = r0
                r8.J$0 = r1
                r8.I$0 = r5
                r8.label = r10
                java.lang.Object r3 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r9, r0, r8)
                if (r3 != r12) goto L81
            L80:
                return r12
            L81:
                r14 = r1
                r2 = r0
                r0 = r14
            L84:
                int r2 = r2.length
                long r2 = (long) r2
                long r0 = r0 + r2
                goto L36
            L88:
                x6.t0 r0 = x6.t0.f22605a
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1$2", f = "TelegramStreamingProxy.kt", l = {154}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Integer $fileId;
        int label;
        final /* synthetic */ TelegramStreamingProxy this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TelegramStreamingProxy telegramStreamingProxy, Integer num, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.this$0 = telegramStreamingProxy;
            this.$fileId = num;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.this$0, this.$fileId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TelegramStreamingProxy telegramStreamingProxy = this.this$0;
                int iIntValue = this.$fileId.intValue();
                this.label = 1;
                Object objEndStreamRequest = telegramStreamingProxy.endStreamRequest(iIntValue, this);
                e7.a aVar = e7.a.f15033i;
                if (objEndStreamRequest == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramStreamingProxy$start$1$1$1(TelegramStreamingProxy telegramStreamingProxy, d7.d<? super TelegramStreamingProxy$start$1$1$1> dVar) {
        super(3, dVar);
        this.this$0 = telegramStreamingProxy;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d7.d<? super t0> dVar) {
        TelegramStreamingProxy$start$1$1$1 telegramStreamingProxy$start$1$1$1 = new TelegramStreamingProxy$start$1$1$1(this.this$0, dVar);
        telegramStreamingProxy$start$1$1$1.L$0 = pipelineContext;
        return telegramStreamingProxy$start$1$1$1.invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x016a, code lost:
    
        if (r2.execute(r0, r1, r28) == r10) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0285, code lost:
    
        if (ka.m0.y(r3, r4, r28) != r10) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0387, code lost:
    
        return r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x03b7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01c8 A[EXC_TOP_SPLITTER, PHI: r3 r4 r11 r12 r13
      0x01c8: PHI (r3v14 java.lang.Integer) = (r3v11 java.lang.Integer), (r3v19 java.lang.Integer) binds: [B:45:0x01c4, B:23:0x00bb] A[DONT_GENERATE, DONT_INLINE]
      0x01c8: PHI (r4v3 java.lang.Object) = (r4v2 java.lang.Object), (r4v16 java.lang.Object) binds: [B:45:0x01c4, B:23:0x00bb] A[DONT_GENERATE, DONT_INLINE]
      0x01c8: PHI (r11v18 java.lang.Long) = (r11v17 java.lang.Long), (r11v28 java.lang.Long) binds: [B:45:0x01c4, B:23:0x00bb] A[DONT_GENERATE, DONT_INLINE]
      0x01c8: PHI (r12v7 java.lang.String) = (r12v6 java.lang.String), (r12v9 java.lang.String) binds: [B:45:0x01c4, B:23:0x00bb] A[DONT_GENERATE, DONT_INLINE]
      0x01c8: PHI (r13v7 java.lang.Long) = (r13v4 java.lang.Long), (r13v14 java.lang.Long) binds: [B:45:0x01c4, B:23:0x00bb] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01e1 A[Catch: all -> 0x01d7, TRY_LEAVE, TryCatch #2 {all -> 0x01d7, blocks: (B:50:0x01ce, B:55:0x01e1, B:61:0x020a, B:63:0x021a, B:64:0x0233, B:72:0x028b, B:76:0x0296, B:44:0x0187), top: B:109:0x0187 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x020a A[Catch: all -> 0x01d7, TRY_ENTER, TryCatch #2 {all -> 0x01d7, blocks: (B:50:0x01ce, B:55:0x01e1, B:61:0x020a, B:63:0x021a, B:64:0x0233, B:72:0x028b, B:76:0x0296, B:44:0x0187), top: B:109:0x0187 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0289  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 976
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy$start$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
