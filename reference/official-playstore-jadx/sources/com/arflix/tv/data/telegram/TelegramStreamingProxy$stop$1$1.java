package com.arflix.tv.data.telegram;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$stop$1$1", f = "TelegramStreamingProxy.kt", l = {165}, m = "invokeSuspend", v = 2)
public final class TelegramStreamingProxy$stop$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $it;
    int label;
    final /* synthetic */ TelegramStreamingProxy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramStreamingProxy$stop$1$1(TelegramStreamingProxy telegramStreamingProxy, int i10, d7.d<? super TelegramStreamingProxy$stop$1$1> dVar) {
        super(2, dVar);
        this.this$0 = telegramStreamingProxy;
        this.$it = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TelegramStreamingProxy$stop$1$1(this.this$0, this.$it, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            TelegramStreamingProxy telegramStreamingProxy = this.this$0;
            int i11 = this.$it;
            this.label = 1;
            Object objDeleteFile = telegramStreamingProxy.deleteFile(i11, this);
            e7.a aVar = e7.a.f15033i;
            if (objDeleteFile == aVar) {
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
        return ((TelegramStreamingProxy$stop$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
