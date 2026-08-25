package io.ktor.utils.io.jvm.javaio;

import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0013\u0010\u0003\u001a\u00020\u0002H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"io/ktor/utils/io/jvm/javaio/InputAdapter$loop$1", "Lio/ktor/utils/io/jvm/javaio/BlockingAdapter;", "Lx6/t0;", "loop", "(Ld7/d;)Ljava/lang/Object;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InputAdapter$loop$1 extends BlockingAdapter {
    final /* synthetic */ InputAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputAdapter$loop$1(v1 v1Var, InputAdapter inputAdapter) {
        super(v1Var);
        this.this$0 = inputAdapter;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0071, code lost:
    
        if (r10 != r1) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056 A[PHI: r2 r10
      0x0056: PHI (r2v2 io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1) = (r2v3 io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1), (r2v8 io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1) binds: [B:18:0x0053, B:15:0x0036] A[DONT_GENERATE, DONT_INLINE]
      0x0056: PHI (r10v3 java.lang.Object) = (r10v7 java.lang.Object), (r10v1 java.lang.Object) binds: [B:18:0x0053, B:15:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0071 -> B:23:0x0074). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.jvm.javaio.BlockingAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loop(d7.d<? super x6.t0> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1 r0 = (io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1 r0 = new io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1
            r0.<init>(r9, r10)
        L18:
            java.lang.Object r10 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1 r2 = (io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1) r2
            k2.c.G(r10)
            goto L74
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L36:
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.jvm.javaio.BlockingAdapter r2 = (io.ktor.utils.io.jvm.javaio.BlockingAdapter) r2
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1 r2 = (io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1) r2
            k2.c.G(r10)
            goto L56
        L42:
            k2.c.G(r10)
            r10 = 0
            r2 = r9
        L47:
            r2.result = r10
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r10 = io.ktor.utils.io.jvm.javaio.BlockingAdapter.access$rendezvousBlock(r2, r0)
            if (r10 != r1) goto L56
            goto L73
        L56:
            byte[] r10 = (byte[]) r10
            io.ktor.utils.io.jvm.javaio.InputAdapter r5 = r2.this$0
            io.ktor.utils.io.ByteReadChannel r5 = io.ktor.utils.io.jvm.javaio.InputAdapter.access$getChannel$p(r5)
            int r6 = r2.getOffset()
            int r7 = r2.getLength()
            r0.L$0 = r2
            r8 = 0
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r10 = r5.readAvailable(r10, r6, r7, r0)
            if (r10 != r1) goto L74
        L73:
            return r1
        L74:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            r5 = -1
            if (r10 != r5) goto L47
            io.ktor.utils.io.jvm.javaio.InputAdapter r0 = r2.this$0
            ka.t r0 = io.ktor.utils.io.jvm.javaio.InputAdapter.access$getContext$p(r0)
            r0.complete()
            r2.finish(r10)
            x6.t0 r10 = x6.t0.f22605a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1.loop(d7.d):java.lang.Object");
    }
}
