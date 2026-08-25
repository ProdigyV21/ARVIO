package io.ktor.http.content;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import r7.p;
import x6.t0;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.http.content.OutgoingContent$ReadChannelContent$readFrom$1", f = "OutgoingContent.kt", l = {93, 95}, m = "invokeSuspend")
public final class OutgoingContent$ReadChannelContent$readFrom$1 extends j implements p<WriterScope, d<? super t0>, Object> {
    final /* synthetic */ l $range;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OutgoingContent.ReadChannelContent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutgoingContent$ReadChannelContent$readFrom$1(OutgoingContent.ReadChannelContent readChannelContent, l lVar, d<? super OutgoingContent$ReadChannelContent$readFrom$1> dVar) {
        super(2, dVar);
        this.this$0 = readChannelContent;
        this.$range = lVar;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        OutgoingContent$ReadChannelContent$readFrom$1 outgoingContent$ReadChannelContent$readFrom$1 = new OutgoingContent$ReadChannelContent$readFrom$1(this.this$0, this.$range, dVar);
        outgoingContent$ReadChannelContent$readFrom$1.L$0 = obj;
        return outgoingContent$ReadChannelContent$readFrom$1;
    }

    @Override // r7.p
    public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
        return ((OutgoingContent$ReadChannelContent$readFrom$1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x005e, code lost:
    
        if (io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r0, r9, r4, r8) == r3) goto L16;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            int r0 = r8.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L24
            if (r0 == r2) goto L18
            if (r0 != r1) goto L10
            k2.c.G(r9)
            goto L61
        L10:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L18:
            java.lang.Object r0 = r8.L$1
            io.ktor.utils.io.ByteReadChannel r0 = (io.ktor.utils.io.ByteReadChannel) r0
            java.lang.Object r2 = r8.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            k2.c.G(r9)
            goto L43
        L24:
            k2.c.G(r9)
            java.lang.Object r9 = r8.L$0
            io.ktor.utils.io.WriterScope r9 = (io.ktor.utils.io.WriterScope) r9
            io.ktor.http.content.OutgoingContent$ReadChannelContent r0 = r8.this$0
            io.ktor.utils.io.ByteReadChannel r0 = r0.readFrom()
            x7.l r4 = r8.$range
            long r4 = r4.f22627i
            r8.L$0 = r9
            r8.L$1 = r0
            r8.label = r2
            java.lang.Object r2 = r0.discard(r4, r8)
            if (r2 != r3) goto L42
            goto L60
        L42:
            r2 = r9
        L43:
            x7.l r9 = r8.$range
            long r4 = r9.f22628l
            x7.l r9 = r8.$range
            long r6 = r9.f22627i
            long r4 = r4 - r6
            r6 = 1
            long r4 = r4 + r6
            io.ktor.utils.io.ByteWriteChannel r9 = r2.getChannel()
            r2 = 0
            r8.L$0 = r2
            r8.L$1 = r2
            r8.label = r1
            java.lang.Object r9 = io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r0, r9, r4, r8)
            if (r9 != r3) goto L61
        L60:
            return r3
        L61:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.OutgoingContent$ReadChannelContent$readFrom$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
