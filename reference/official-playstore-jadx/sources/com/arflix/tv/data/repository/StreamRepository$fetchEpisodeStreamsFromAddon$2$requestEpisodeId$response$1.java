package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.StremioStreamResponse;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/StremioStreamResponse;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/StremioStreamResponse;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$response$1", f = "StreamRepository.kt", l = {1978}, m = "invokeSuspend", v = 2)
public final class StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$response$1 extends f7.j implements r7.p<ka.k0, d7.d<? super StremioStreamResponse>, Object> {
    final /* synthetic */ String $requestUrl;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$response$1(StreamRepository streamRepository, String str, d7.d<? super StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$response$1> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$requestUrl = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$response$1(this.this$0, this.$requestUrl, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        StreamApi streamApi = this.this$0.streamApi;
        String str = this.$requestUrl;
        this.label = 1;
        Object addonStreams = streamApi.getAddonStreams(str, this);
        e7.a aVar = e7.a.f15033i;
        return addonStreams == aVar ? aVar : addonStreams;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super StremioStreamResponse> dVar) {
        return ((StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$response$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
