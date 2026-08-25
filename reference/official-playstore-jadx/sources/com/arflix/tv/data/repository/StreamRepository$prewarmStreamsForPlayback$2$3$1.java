package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$prewarmStreamsForPlayback$2$3$1", f = "StreamRepository.kt", l = {3631}, m = "invokeSuspend", v = 2)
public final class StreamRepository$prewarmStreamsForPlayback$2$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ boolean $allowNetworkWarmup;
    final /* synthetic */ StreamSource $stream;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$prewarmStreamsForPlayback$2$3$1(StreamRepository streamRepository, StreamSource streamSource, boolean z, d7.d<? super StreamRepository$prewarmStreamsForPlayback$2$3$1> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$stream = streamSource;
        this.$allowNetworkWarmup = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        StreamRepository$prewarmStreamsForPlayback$2$3$1 streamRepository$prewarmStreamsForPlayback$2$3$1 = new StreamRepository$prewarmStreamsForPlayback$2$3$1(this.this$0, this.$stream, this.$allowNetworkWarmup, dVar);
        streamRepository$prewarmStreamsForPlayback$2$3$1.L$0 = obj;
        return streamRepository$prewarmStreamsForPlayback$2$3$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                StreamRepository streamRepository = this.this$0;
                StreamSource streamSource = this.$stream;
                boolean z = this.$allowNetworkWarmup;
                this.L$0 = null;
                this.L$1 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = streamRepository.prewarmStreamForPlayback(streamSource, z, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            c0Var = (StreamSource) obj;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((StreamRepository$prewarmStreamsForPlayback$2$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
