package io.ktor.utils.io;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.f0;
import ka.k0;
import ka.u1;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "S", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.utils.io.CoroutinesKt$launchChannel$job$1", f = "Coroutines.kt", l = {147}, m = "invokeSuspend")
public final class CoroutinesKt$launchChannel$job$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ boolean $attachJob;
    final /* synthetic */ p<S, d<? super t0>, Object> $block;
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ f0 $dispatcher;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CoroutinesKt$launchChannel$job$1(boolean z, ByteChannel byteChannel, p<? super S, ? super d<? super t0>, ? extends Object> pVar, f0 f0Var, d<? super CoroutinesKt$launchChannel$job$1> dVar) {
        super(2, dVar);
        this.$attachJob = z;
        this.$channel = byteChannel;
        this.$block = pVar;
        this.$dispatcher = f0Var;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        CoroutinesKt$launchChannel$job$1 coroutinesKt$launchChannel$job$1 = new CoroutinesKt$launchChannel$job$1(this.$attachJob, this.$channel, this.$block, this.$dispatcher, dVar);
        coroutinesKt$launchChannel$job$1.L$0 = obj;
        return coroutinesKt$launchChannel$job$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                c.G(obj);
                k0 k0Var = (k0) this.L$0;
                if (this.$attachJob) {
                    this.$channel.attachJob((v1) k0Var.getCoroutineContext().get(u1.f19642i));
                }
                ChannelScope channelScope = new ChannelScope(k0Var, this.$channel);
                p<S, d<? super t0>, Object> pVar = this.$block;
                this.label = 1;
                Object objInvoke = pVar.invoke(channelScope, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
        } catch (Throwable th) {
            if (!kotlin.jvm.internal.p.a(this.$dispatcher, x0.f19654c) && this.$dispatcher != null) {
                throw th;
            }
            this.$channel.cancel(th);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((CoroutinesKt$launchChannel$job$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
