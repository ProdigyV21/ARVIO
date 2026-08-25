package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.StreamRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$prefetchStreamsInBackground$1$1$3", f = "DetailsViewModel.kt", l = {1517}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$prefetchStreamsInBackground$1$1$3 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Integer $episode;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ int $requestMediaId;
    final /* synthetic */ Integer $season;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$prefetchStreamsInBackground$1$1$3(DetailsViewModel detailsViewModel, String str, Integer num, Integer num2, int i10, d7.d<? super DetailsViewModel$prefetchStreamsInBackground$1$1$3> dVar) {
        super(2, dVar);
        this.this$0 = detailsViewModel;
        this.$imdbId = str;
        this.$season = num;
        this.$episode = num2;
        this.$requestMediaId = i10;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsViewModel$prefetchStreamsInBackground$1$1$3(this.this$0, this.$imdbId, this.$season, this.$episode, this.$requestMediaId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            StreamRepository streamRepository = this.this$0.streamRepository;
            String str = this.$imdbId;
            int iIntValue = this.$season.intValue();
            int iIntValue2 = this.$episode.intValue();
            MediaItem item = ((DetailsUiState) this.this$0._uiState.getValue()).getItem();
            String title = item != null ? item.getTitle() : null;
            if (title == null) {
                title = "";
            }
            Integer num = new Integer(this.$requestMediaId);
            Integer tvdbId = ((DetailsUiState) this.this$0._uiState.getValue()).getTvdbId();
            this.label = 1;
            Object objResolveEpisodeHomeServerSources = streamRepository.resolveEpisodeHomeServerSources(str, iIntValue, iIntValue2, title, num, tvdbId, 5000L, this);
            e7.a aVar = e7.a.f15033i;
            if (objResolveEpisodeHomeServerSources == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsViewModel$prefetchStreamsInBackground$1$1$3) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
