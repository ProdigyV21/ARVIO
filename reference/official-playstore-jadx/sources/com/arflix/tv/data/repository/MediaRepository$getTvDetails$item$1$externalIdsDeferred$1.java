package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbExternalIds;
import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/TmdbExternalIds;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/TmdbExternalIds;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getTvDetails$item$1$externalIdsDeferred$1", f = "MediaRepository.kt", l = {2886}, m = "invokeSuspend", v = 2)
public final class MediaRepository$getTvDetails$item$1$externalIdsDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super TmdbExternalIds>, Object> {
    final /* synthetic */ int $tvId;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$getTvDetails$item$1$externalIdsDeferred$1(MediaRepository mediaRepository, int i10, d7.d<? super MediaRepository$getTvDetails$item$1$externalIdsDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$tvId = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MediaRepository$getTvDetails$item$1$externalIdsDeferred$1(this.this$0, this.$tvId, dVar);
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
        MediaRepository mediaRepository = this.this$0;
        MediaType mediaType = MediaType.TV;
        int i11 = this.$tvId;
        this.label = 1;
        Object objResolveExternalIds = mediaRepository.resolveExternalIds(mediaType, i11, this);
        e7.a aVar = e7.a.f15033i;
        return objResolveExternalIds == aVar ? aVar : objResolveExternalIds;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super TmdbExternalIds> dVar) {
        return ((MediaRepository$getTvDetails$item$1$externalIdsDeferred$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
