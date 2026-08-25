package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.Review;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8781i;

    public /* synthetic */ g(int i10) {
        this.f8781i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f8781i;
        int iIntValue = ((Integer) obj).intValue();
        switch (i10) {
            case 0:
                return DetailsScreenKt.DetailsSeasonRail_4EFweAY$lambda$3$0$0(iIntValue, ((Integer) obj2).intValue());
            case 1:
                return DetailsScreenKt.DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$0(iIntValue, (MediaItem) obj2);
            case 2:
                return DetailsScreenKt.DetailsContent$lambda$16$0$7$0$0(iIntValue, (MediaItem) obj2);
            case 3:
                return DetailsScreenKt.DetailsReviewRail_BMayB_o$lambda$0$0$0$0(iIntValue, (Review) obj2);
            case 4:
                return DetailsScreenKt.DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0$0(iIntValue, (MediaItem) obj2);
            case 5:
                return DetailsScreenKt.DetailsEpisodeRail_3GLzNTs$lambda$2$0$0$0(iIntValue, (Episode) obj2);
            case 6:
                return DetailsScreenKt.DetailsContent$lambda$16$0$5$0$0(iIntValue, (CastMember) obj2);
            case 7:
                return DetailsScreenKt.DetailsCastRail_6PoWaU8$lambda$0$0$0$0(iIntValue, (CastMember) obj2);
            case 8:
                return DetailsScreenKt.DetailsContent$lambda$16$0$9$0$0(iIntValue, (MediaItem) obj2);
            case 9:
                return DetailsScreenKt.DetailsContent$lambda$16$0$11$0$0(iIntValue, (Review) obj2);
            case 10:
                return DetailsScreenKt.DetailsContent$lambda$16$0$2$0$0(iIntValue, (Episode) obj2);
            default:
                return DetailsScreenKt.DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0$0(iIntValue, (Episode) obj2);
        }
    }
}
