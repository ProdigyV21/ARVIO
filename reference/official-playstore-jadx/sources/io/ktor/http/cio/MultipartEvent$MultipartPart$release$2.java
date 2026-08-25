package io.ktor.http.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.cio.MultipartEvent;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)J"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.http.cio.MultipartEvent$MultipartPart$release$2", f = "Multipart.kt", l = {56}, m = "invokeSuspend")
public final class MultipartEvent$MultipartPart$release$2 extends j implements p<k0, d<? super Long>, Object> {
    int label;
    final /* synthetic */ MultipartEvent.MultipartPart this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartEvent$MultipartPart$release$2(MultipartEvent.MultipartPart multipartPart, d<? super MultipartEvent$MultipartPart$release$2> dVar) {
        super(2, dVar);
        this.this$0 = multipartPart;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new MultipartEvent$MultipartPart$release$2(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        c.G(obj);
        ByteReadChannel body = this.this$0.getBody();
        this.label = 1;
        Object objDiscard = ByteReadChannelKt.discard(body, this);
        a aVar = a.f15033i;
        return objDiscard == aVar ? aVar : objDiscard;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super Long> dVar) {
        return ((MultipartEvent$MultipartPart$release$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
