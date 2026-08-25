package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.lazy.LazyListScope;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8788i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f8789l;

    public /* synthetic */ g1(List list, int i10) {
        this.f8788i = i10;
        this.f8789l = list;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8788i) {
            case 0:
                return DetailsViewModel.C13181.AnonymousClass10.invokeSuspend$lambda$1(this.f8789l, (DetailsUiState) obj);
            case 1:
                return DetailsViewModel.C13181.AnonymousClass12.invokeSuspend$lambda$2(this.f8789l, (DetailsUiState) obj);
            case 2:
                return DetailsScreenKt.DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0(this.f8789l, (LazyListScope) obj);
            default:
                return DetailsScreenKt.DetailsContent$lambda$16$0$11$0(this.f8789l, (LazyListScope) obj);
        }
    }
}
