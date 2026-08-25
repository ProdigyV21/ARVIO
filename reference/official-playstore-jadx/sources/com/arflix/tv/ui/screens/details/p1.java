package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.api.TmdbTvSeason;
import com.arflix.tv.data.api.TraktComment;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.Review;
import com.arflix.tv.data.model.StreamSource;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8946i;

    public /* synthetic */ p1(int i10) {
        this.f8946i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8946i) {
            case 0:
                return ((Addon) obj).getName();
            case 1:
                return DetailsScreenKt.DetailsContent$lambda$2$0(((Integer) obj).intValue());
            case 2:
                return DetailsScreenKt.DetailsContent$lambda$3$0(((Integer) obj).intValue());
            case 3:
                return DetailsScreenKt.DetailsContent$lambda$4$0(((Integer) obj).intValue());
            case 4:
                return DetailsScreenKt.DetailsContent$lambda$5$0(((Integer) obj).intValue());
            case 5:
                return DetailsScreenKt.DetailsContent$lambda$6$0(((Integer) obj).intValue());
            case 6:
                return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$0$0(((Integer) obj).intValue());
            case 7:
                return DetailsScreenKt.DetailsScreen$lambda$0$0((String) obj);
            case 8:
                return DetailsScreenKt.DetailsContent$lambda$1$0(((Integer) obj).intValue());
            case 9:
                return Boolean.valueOf(DetailsViewModel.toFilteredCommunityReviews$lambda$0((TraktComment) obj));
            case 10:
                return DetailsViewModel.prewarmVisibleStreams$lambda$0((StreamSource) obj);
            case 11:
                return DetailsViewModel.toFilteredCommunityReviews$lambda$8((Review) obj);
            case 12:
                return Boolean.valueOf(DetailsViewModel.fetchSeasonProgress$lambda$3((TmdbTvSeason) obj));
            case 13:
                return Integer.valueOf(((TmdbTvSeason) obj).getSeasonNumber());
            default:
                return DetailsViewModel.loadFilteredTmdbReviews$lambda$2((Review) obj);
        }
    }
}
