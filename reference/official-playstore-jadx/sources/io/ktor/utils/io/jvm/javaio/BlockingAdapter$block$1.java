package io.ktor.utils.io.jvm.javaio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.utils.io.jvm.javaio.BlockingAdapter$block$1", f = "Blocking.kt", l = {186}, m = "invokeSuspend")
public final class BlockingAdapter$block$1 extends j implements l<d<? super t0>, Object> {
    int label;
    final /* synthetic */ BlockingAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlockingAdapter$block$1(BlockingAdapter blockingAdapter, d<? super BlockingAdapter$block$1> dVar) {
        super(1, dVar);
        this.this$0 = blockingAdapter;
    }

    @Override // f7.a
    public final d<t0> create(d<?> dVar) {
        return new BlockingAdapter$block$1(this.this$0, dVar);
    }

    @Override // r7.l
    public final Object invoke(d<? super t0> dVar) {
        return ((BlockingAdapter$block$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            BlockingAdapter blockingAdapter = this.this$0;
            this.label = 1;
            Object objLoop = blockingAdapter.loop(this);
            a aVar = a.f15033i;
            if (objLoop == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }
}
