package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.api.TraktComment;
import com.arflix.tv.data.model.Review;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8766i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ DetailsViewModel f8767l;

    public /* synthetic */ e1(DetailsViewModel detailsViewModel, int i10) {
        this.f8766i = i10;
        this.f8767l = detailsViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8766i) {
            case 0:
                return Boolean.valueOf(DetailsViewModel.toFilteredCommunityReviews$lambda$2(this.f8767l, (TraktComment) obj));
            case 1:
                return this.f8767l.toCommunityReview((TraktComment) obj);
            case 2:
                return Boolean.valueOf(DetailsViewModel.loadFilteredTmdbReviews$lambda$0(this.f8767l, (Review) obj));
            default:
                return DetailsViewModel.loadFilteredTmdbReviews$lambda$1(this.f8767l, (Review) obj);
        }
    }
}
