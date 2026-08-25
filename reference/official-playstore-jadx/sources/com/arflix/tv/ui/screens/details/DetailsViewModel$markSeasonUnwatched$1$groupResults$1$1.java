package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.Episode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1", f = "DetailsViewModel.kt", l = {2196, 2209}, m = "invokeSuspend", v = 2)
public final class DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
    final /* synthetic */ List<Episode> $episodes;
    final /* synthetic */ int $tmdbSeason;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;
    final /* synthetic */ DetailsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1(List<Episode> list, DetailsViewModel detailsViewModel, int i10, d7.d<? super DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1> dVar) {
        super(2, dVar);
        this.$episodes = list;
        this.this$0 = detailsViewModel;
        this.$tmdbSeason = i10;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1 detailsViewModel$markSeasonUnwatched$1$groupResults$1$1 = new DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1(this.$episodes, this.this$0, this.$tmdbSeason, dVar);
        detailsViewModel$markSeasonUnwatched$1$groupResults$1$1.L$0 = obj;
        return detailsViewModel$markSeasonUnwatched$1$groupResults$1$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0132  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0123 -> B:39:0x0124). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((DetailsViewModel$markSeasonUnwatched$1$groupResults$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
