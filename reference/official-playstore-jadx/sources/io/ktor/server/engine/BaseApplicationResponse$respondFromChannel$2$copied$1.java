package io.ktor.server.engine;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
import io.ktor.utils.io.ByteWriteChannel;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)J"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$2$copied$1", f = "BaseApplicationResponse.kt", l = {211}, m = "invokeSuspend")
public final class BaseApplicationResponse$respondFromChannel$2$copied$1 extends j implements p<k0, d<? super Long>, Object> {
    final /* synthetic */ Long $length;
    final /* synthetic */ ByteReadChannel $readChannel;
    final /* synthetic */ ByteWriteChannel $this_use;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseApplicationResponse$respondFromChannel$2$copied$1(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, Long l10, d<? super BaseApplicationResponse$respondFromChannel$2$copied$1> dVar) {
        super(2, dVar);
        this.$readChannel = byteReadChannel;
        this.$this_use = byteWriteChannel;
        this.$length = l10;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new BaseApplicationResponse$respondFromChannel$2$copied$1(this.$readChannel, this.$this_use, this.$length, dVar);
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
        ByteReadChannel byteReadChannel = this.$readChannel;
        ByteWriteChannel byteWriteChannel = this.$this_use;
        Long l10 = this.$length;
        long jLongValue = l10 != null ? l10.longValue() : Long.MAX_VALUE;
        this.label = 1;
        Object objCopyTo = ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, jLongValue, this);
        a aVar = a.f15033i;
        return objCopyTo == aVar ? aVar : objCopyTo;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super Long> dVar) {
        return ((BaseApplicationResponse$respondFromChannel$2$copied$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
