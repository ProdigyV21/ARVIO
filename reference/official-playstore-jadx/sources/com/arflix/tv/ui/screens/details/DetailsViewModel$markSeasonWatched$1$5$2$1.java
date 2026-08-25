package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.Episode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonWatched$1$5$2$1", f = "DetailsViewModel.kt", l = {2053, 2058}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$markSeasonWatched$1$5$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ Episode $ep;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$markSeasonWatched$1$5$2$1(DetailsViewModel detailsViewModel, Episode episode, d7.d<? super DetailsViewModel$markSeasonWatched$1$5$2$1> dVar) {
        super(2, dVar);
        this.this$0 = detailsViewModel;
        this.$ep = episode;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        DetailsViewModel$markSeasonWatched$1$5$2$1 detailsViewModel$markSeasonWatched$1$5$2$1 = new DetailsViewModel$markSeasonWatched$1$5$2$1(this.this$0, this.$ep, dVar);
        detailsViewModel$markSeasonWatched$1$5$2$1.L$0 = obj;
        return detailsViewModel$markSeasonWatched$1$5$2$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x008e, code lost:
    
        if (r11.markEpisodeWatchedWithoutTraktSync(r5, r6, r2, r10) == r4) goto L23;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.L$0
            ka.k0 r0 = (ka.k0) r0
            int r0 = r10.label
            r1 = 2
            r2 = 1
            r3 = 0
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L37
            if (r0 == r2) goto L25
            if (r0 != r1) goto L1d
            java.lang.Object r0 = r10.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L1a
            goto L91
        L1a:
            r11 = move-exception
            goto L94
        L1d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L25:
            int r0 = r10.I$0
            java.lang.Object r2 = r10.L$3
            ka.k0 r2 = (ka.k0) r2
            java.lang.Object r2 = r10.L$2
            com.arflix.tv.data.model.Episode r2 = (com.arflix.tv.data.model.Episode) r2
            java.lang.Object r5 = r10.L$1
            com.arflix.tv.ui.screens.details.DetailsViewModel r5 = (com.arflix.tv.ui.screens.details.DetailsViewModel) r5
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L1a
            goto L6e
        L37:
            k2.c.G(r11)
            com.arflix.tv.ui.screens.details.DetailsViewModel r5 = r10.this$0
            com.arflix.tv.data.model.Episode r11 = r10.$ep
            com.arflix.tv.data.repository.WatchHistoryRepository r0 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getWatchHistoryRepository$p(r5)     // Catch: java.lang.Throwable -> L1a
            int r6 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getCurrentMediaId$p(r5)     // Catch: java.lang.Throwable -> L1a
            int r7 = r11.getTmdbSeasonNumber()     // Catch: java.lang.Throwable -> L1a
            java.lang.Integer r8 = new java.lang.Integer     // Catch: java.lang.Throwable -> L1a
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L1a
            int r7 = r11.getTmdbEpisodeNumber()     // Catch: java.lang.Throwable -> L1a
            java.lang.Integer r9 = new java.lang.Integer     // Catch: java.lang.Throwable -> L1a
            r9.<init>(r7)     // Catch: java.lang.Throwable -> L1a
            r10.L$0 = r3     // Catch: java.lang.Throwable -> L1a
            r10.L$1 = r5     // Catch: java.lang.Throwable -> L1a
            r10.L$2 = r11     // Catch: java.lang.Throwable -> L1a
            r10.L$3 = r3     // Catch: java.lang.Throwable -> L1a
            r7 = 0
            r10.I$0 = r7     // Catch: java.lang.Throwable -> L1a
            r10.label = r2     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r0 = r0.removeFromHistory(r6, r8, r9, r10)     // Catch: java.lang.Throwable -> L1a
            if (r0 != r4) goto L6c
            goto L90
        L6c:
            r2 = r11
            r0 = r7
        L6e:
            com.arflix.tv.data.repository.TraktRepository r11 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getTraktRepository$p(r5)     // Catch: java.lang.Throwable -> L1a
            int r5 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getCurrentMediaId$p(r5)     // Catch: java.lang.Throwable -> L1a
            int r6 = r2.getTmdbSeasonNumber()     // Catch: java.lang.Throwable -> L1a
            int r2 = r2.getTmdbEpisodeNumber()     // Catch: java.lang.Throwable -> L1a
            r10.L$0 = r3     // Catch: java.lang.Throwable -> L1a
            r10.L$1 = r3     // Catch: java.lang.Throwable -> L1a
            r10.L$2 = r3     // Catch: java.lang.Throwable -> L1a
            r10.L$3 = r3     // Catch: java.lang.Throwable -> L1a
            r10.I$0 = r0     // Catch: java.lang.Throwable -> L1a
            r10.label = r1     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r11 = r11.markEpisodeWatchedWithoutTraktSync(r5, r6, r2, r10)     // Catch: java.lang.Throwable -> L1a
            if (r11 != r4) goto L91
        L90:
            return r4
        L91:
            x6.t0 r11 = x6.t0.f22605a     // Catch: java.lang.Throwable -> L1a
            goto L9a
        L94:
            x6.c0 r0 = new x6.c0
            r0.<init>(r11)
            r11 = r0
        L9a:
            x6.d0 r0 = new x6.d0
            r0.<init>(r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonWatched$1$5$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((DetailsViewModel$markSeasonWatched$1$5$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
