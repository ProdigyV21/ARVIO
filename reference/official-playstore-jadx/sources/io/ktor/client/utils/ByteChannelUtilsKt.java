package io.ktor.client.utils;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import ka.k0;
import ka.n1;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001ap\u0010\r\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032F\u0010\f\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Ld7/j;", "context", "", "contentLength", "Lkotlin/Function3;", "Lx6/y;", ContentDisposition.Parameters.Name, "bytesSentTotal", "Ld7/d;", "Lx6/t0;", "", "listener", "observable", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/j;Ljava/lang/Long;Lr7/q;)Lio/ktor/utils/io/ByteReadChannel;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteChannelUtilsKt {

    /* JADX INFO: renamed from: io.ktor.client.utils.ByteChannelUtilsKt$observable$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.utils.ByteChannelUtilsKt$observable$1", f = "ByteChannelUtils.kt", l = {23, 24, 26, 31}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ Long $contentLength;
        final /* synthetic */ q<Long, Long, d<? super t0>, Object> $listener;
        final /* synthetic */ ByteReadChannel $this_observable;
        int I$0;
        long J$0;
        long J$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Long l10, ByteReadChannel byteReadChannel, q<? super Long, ? super Long, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$contentLength = l10;
            this.$this_observable = byteReadChannel;
            this.$listener = qVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$contentLength, this.$this_observable, this.$listener, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x0192, code lost:
        
            if (r11.invoke(r0, r4, r19) == r8) goto L60;
         */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00e7 A[Catch: all -> 0x0020, TryCatch #0 {all -> 0x0020, blocks: (B:9:0x001b, B:39:0x00e1, B:41:0x00e7, B:44:0x0101, B:54:0x0163, B:58:0x0174), top: B:65:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0159  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0163 A[Catch: all -> 0x0020, TRY_ENTER, TryCatch #0 {all -> 0x0020, blocks: (B:9:0x001b, B:39:0x00e1, B:41:0x00e7, B:44:0x0101, B:54:0x0163, B:58:0x0174), top: B:65:0x000a }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0159 -> B:53:0x0160). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 415
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.utils.ByteChannelUtilsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final ByteReadChannel observable(ByteReadChannel byteReadChannel, d7.j jVar, Long l10, q<? super Long, ? super Long, ? super d<? super t0>, ? extends Object> qVar) {
        return CoroutinesKt.writer((k0) n1.f19624i, jVar, true, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new AnonymousClass1(l10, byteReadChannel, qVar, null)).getChannel();
    }
}
