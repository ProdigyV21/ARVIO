package io.ktor.util;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceBlocking$1", f = "CryptoJvm.kt", l = {66}, m = "invokeSuspend")
public final class CryptoKt__CryptoJvmKt$generateNonceBlocking$1 extends j implements p<k0, d<? super String>, Object> {
    int label;

    public CryptoKt__CryptoJvmKt$generateNonceBlocking$1(d<? super CryptoKt__CryptoJvmKt$generateNonceBlocking$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new CryptoKt__CryptoJvmKt$generateNonceBlocking$1(dVar);
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
        ma.j<String> seedChannel = NonceKt.getSeedChannel();
        this.label = 1;
        Object objReceive = seedChannel.receive(this);
        a aVar = a.f15033i;
        return objReceive == aVar ? aVar : objReceive;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super String> dVar) {
        return ((CryptoKt__CryptoJvmKt$generateNonceBlocking$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
