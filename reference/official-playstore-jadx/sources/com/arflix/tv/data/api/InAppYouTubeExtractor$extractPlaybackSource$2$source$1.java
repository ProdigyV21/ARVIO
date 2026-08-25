package com.arflix.tv.data.api;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/TrailerPlaybackSource;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/TrailerPlaybackSource;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.api.InAppYouTubeExtractor$extractPlaybackSource$2$source$1", f = "YouTubeExtractor.kt", l = {177}, m = "invokeSuspend", v = 2)
public final class InAppYouTubeExtractor$extractPlaybackSource$2$source$1 extends j implements p<k0, d7.d<? super TrailerPlaybackSource>, Object> {
    final /* synthetic */ String $videoId;
    int label;
    final /* synthetic */ InAppYouTubeExtractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InAppYouTubeExtractor$extractPlaybackSource$2$source$1(InAppYouTubeExtractor inAppYouTubeExtractor, String str, d7.d<? super InAppYouTubeExtractor$extractPlaybackSource$2$source$1> dVar) {
        super(2, dVar);
        this.this$0 = inAppYouTubeExtractor;
        this.$videoId = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new InAppYouTubeExtractor$extractPlaybackSource$2$source$1(this.this$0, this.$videoId, dVar);
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
        InAppYouTubeExtractor inAppYouTubeExtractor = this.this$0;
        String str = this.$videoId;
        this.label = 1;
        Object objExtractPlaybackSourceInternal = inAppYouTubeExtractor.extractPlaybackSourceInternal(str, this);
        e7.a aVar = e7.a.f15033i;
        return objExtractPlaybackSourceInternal == aVar ? aVar : objExtractPlaybackSourceInternal;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super TrailerPlaybackSource> dVar) {
        return ((InAppYouTubeExtractor$extractPlaybackSource$2$source$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
