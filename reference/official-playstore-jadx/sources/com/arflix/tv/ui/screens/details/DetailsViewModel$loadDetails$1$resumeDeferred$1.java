package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/details/ResumeInfo;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/details/ResumeInfo;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$resumeDeferred$1", f = "DetailsViewModel.kt", l = {403}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$loadDetails$1$resumeDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super ResumeInfo>, Object> {
    final /* synthetic */ Integer $initialEpisode;
    final /* synthetic */ Integer $initialSeason;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$loadDetails$1$resumeDeferred$1(DetailsViewModel detailsViewModel, int i10, MediaType mediaType, Integer num, Integer num2, d7.d<? super DetailsViewModel$loadDetails$1$resumeDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = detailsViewModel;
        this.$mediaId = i10;
        this.$mediaType = mediaType;
        this.$initialSeason = num;
        this.$initialEpisode = num2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsViewModel$loadDetails$1$resumeDeferred$1(this.this$0, this.$mediaId, this.$mediaType, this.$initialSeason, this.$initialEpisode, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        DetailsViewModel detailsViewModel = this.this$0;
        int i11 = this.$mediaId;
        MediaType mediaType = this.$mediaType;
        Integer num = this.$initialSeason;
        Integer num2 = this.$initialEpisode;
        this.label = 1;
        Object objFetchResumeInfo = detailsViewModel.fetchResumeInfo(i11, mediaType, num, num2, this);
        e7.a aVar = e7.a.f15033i;
        return objFetchResumeInfo == aVar ? aVar : objFetchResumeInfo;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super ResumeInfo> dVar) {
        return ((DetailsViewModel$loadDetails$1$resumeDeferred$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
