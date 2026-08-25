package io.ktor.utils.io;

import d7.d;
import f7.e;
import f7.j;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1", f = "Delimited.kt", l = {85, 95}, m = "invokeSuspend")
public final class DelimitedKt$readUntilDelimiterSuspend$copied$1 extends j implements p<LookAheadSuspendSession, d<? super Integer>, Object> {
    final /* synthetic */ int $copied0;
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ ByteBuffer $dst;
    final /* synthetic */ f0 $endFound;
    final /* synthetic */ ByteReadChannel $this_readUntilDelimiterSuspend;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$readUntilDelimiterSuspend$copied$1(int i10, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, f0 f0Var, ByteReadChannel byteReadChannel, d<? super DelimitedKt$readUntilDelimiterSuspend$copied$1> dVar) {
        super(2, dVar);
        this.$copied0 = i10;
        this.$delimiter = byteBuffer;
        this.$dst = byteBuffer2;
        this.$endFound = f0Var;
        this.$this_readUntilDelimiterSuspend = byteReadChannel;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        DelimitedKt$readUntilDelimiterSuspend$copied$1 delimitedKt$readUntilDelimiterSuspend$copied$1 = new DelimitedKt$readUntilDelimiterSuspend$copied$1(this.$copied0, this.$delimiter, this.$dst, this.$endFound, this.$this_readUntilDelimiterSuspend, dVar);
        delimitedKt$readUntilDelimiterSuspend$copied$1.L$0 = obj;
        return delimitedKt$readUntilDelimiterSuspend$copied$1;
    }

    @Override // r7.p
    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, d<? super Integer> dVar) {
        return ((DelimitedKt$readUntilDelimiterSuspend$copied$1) create(lookAheadSuspendSession, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0075, code lost:
    
        if (r4.awaitAtLeast(r7, r6) == r3) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008f, code lost:
    
        if (r6.$endFound.f19738i == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0078, code lost:
    
        r0 = r0 + r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0075 -> B:26:0x0078). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L28
            if (r0 == r2) goto L1e
            if (r0 != r1) goto L16
            int r0 = r6.I$0
            java.lang.Object r4 = r6.L$0
            io.ktor.utils.io.LookAheadSuspendSession r4 = (io.ktor.utils.io.LookAheadSuspendSession) r4
            k2.c.G(r7)
            goto L78
        L16:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1e:
            int r0 = r6.I$0
            java.lang.Object r4 = r6.L$0
            io.ktor.utils.io.LookAheadSuspendSession r4 = (io.ktor.utils.io.LookAheadSuspendSession) r4
            k2.c.G(r7)
            goto L3f
        L28:
            k2.c.G(r7)
            java.lang.Object r7 = r6.L$0
            io.ktor.utils.io.LookAheadSuspendSession r7 = (io.ktor.utils.io.LookAheadSuspendSession) r7
            int r0 = r6.$copied0
        L31:
            r6.L$0 = r7
            r6.I$0 = r0
            r6.label = r2
            java.lang.Object r4 = r7.awaitAtLeast(r2, r6)
            if (r4 != r3) goto L3e
            goto L77
        L3e:
            r4 = r7
        L3f:
            java.nio.ByteBuffer r7 = r6.$delimiter
            java.nio.ByteBuffer r5 = r6.$dst
            int r7 = io.ktor.utils.io.DelimitedKt.access$tryCopyUntilDelimiter(r4, r7, r5)
            if (r7 != 0) goto L7a
            java.nio.ByteBuffer r7 = r6.$delimiter
            int r7 = io.ktor.utils.io.DelimitedKt.access$startsWithDelimiter(r4, r7)
            java.nio.ByteBuffer r5 = r6.$delimiter
            int r5 = r5.remaining()
            if (r7 != r5) goto L5c
            kotlin.jvm.internal.f0 r7 = r6.$endFound
            r7.f19738i = r2
            goto L91
        L5c:
            io.ktor.utils.io.ByteReadChannel r7 = r6.$this_readUntilDelimiterSuspend
            boolean r7 = r7.isClosedForWrite()
            if (r7 == 0) goto L65
            goto L91
        L65:
            java.nio.ByteBuffer r7 = r6.$delimiter
            int r7 = r7.remaining()
            r6.L$0 = r4
            r6.I$0 = r0
            r6.label = r1
            java.lang.Object r7 = r4.awaitAtLeast(r7, r6)
            if (r7 != r3) goto L78
        L77:
            return r3
        L78:
            r7 = r4
            goto L83
        L7a:
            if (r7 > 0) goto L81
            kotlin.jvm.internal.f0 r5 = r6.$endFound
            r5.f19738i = r2
            int r7 = -r7
        L81:
            int r0 = r0 + r7
            goto L78
        L83:
            java.nio.ByteBuffer r4 = r6.$dst
            boolean r4 = r4.hasRemaining()
            if (r4 == 0) goto L91
            kotlin.jvm.internal.f0 r4 = r6.$endFound
            boolean r4 = r4.f19738i
            if (r4 == 0) goto L31
        L91:
            java.lang.Integer r7 = new java.lang.Integer
            r7.<init>(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
