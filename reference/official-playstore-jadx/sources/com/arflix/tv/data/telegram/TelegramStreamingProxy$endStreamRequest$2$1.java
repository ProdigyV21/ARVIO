package com.arflix.tv.data.telegram;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$2$1", f = "TelegramStreamingProxy.kt", l = {217, 219, 223}, m = "invokeSuspend", v = 2)
public final class TelegramStreamingProxy$endStreamRequest$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $fileId;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ TelegramStreamingProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramStreamingProxy$endStreamRequest$2$1(TelegramStreamingProxy telegramStreamingProxy, int i10, d7.d<? super TelegramStreamingProxy$endStreamRequest$2$1> dVar) {
        super(2, dVar);
        this.this$0 = telegramStreamingProxy;
        this.$fileId = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TelegramStreamingProxy$endStreamRequest$2$1(this.this$0, this.$fileId, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x008f, code lost:
    
        if (r9.deleteFile(r0, r8) != r5) goto L31;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((TelegramStreamingProxy$endStreamRequest$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
