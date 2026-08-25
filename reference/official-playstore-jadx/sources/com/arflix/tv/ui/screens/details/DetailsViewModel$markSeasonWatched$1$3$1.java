package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.repository.TraktRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonWatched$1$3$1", f = "DetailsViewModel.kt", l = {2038}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$markSeasonWatched$1$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ int $epNum;
    final /* synthetic */ int $season;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$markSeasonWatched$1$3$1(DetailsViewModel detailsViewModel, int i10, int i11, d7.d<? super DetailsViewModel$markSeasonWatched$1$3$1> dVar) {
        super(2, dVar);
        this.this$0 = detailsViewModel;
        this.$season = i10;
        this.$epNum = i11;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        DetailsViewModel$markSeasonWatched$1$3$1 detailsViewModel$markSeasonWatched$1$3$1 = new DetailsViewModel$markSeasonWatched$1$3$1(this.this$0, this.$season, this.$epNum, dVar);
        detailsViewModel$markSeasonWatched$1$3$1.L$0 = obj;
        return detailsViewModel$markSeasonWatched$1$3$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                DetailsViewModel detailsViewModel = this.this$0;
                int i11 = this.$season;
                int i12 = this.$epNum;
                TraktRepository traktRepository = detailsViewModel.traktRepository;
                int i13 = detailsViewModel.currentMediaId;
                this.L$0 = null;
                this.L$1 = null;
                this.I$0 = 0;
                this.label = 1;
                Object objMarkEpisodeWatchedWithoutTraktSync = traktRepository.markEpisodeWatchedWithoutTraktSync(i13, i11, i12, this);
                e7.a aVar = e7.a.f15033i;
                if (objMarkEpisodeWatchedWithoutTraktSync == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            c0Var = x6.t0.f22605a;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((DetailsViewModel$markSeasonWatched$1$3$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
