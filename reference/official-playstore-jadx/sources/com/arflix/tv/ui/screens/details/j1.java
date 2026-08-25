package com.arflix.tv.ui.screens.details;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import com.arflix.tv.data.api.TraktComment;
import com.arflix.tv.ui.screens.details.DetailsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8844i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8845l;

    public /* synthetic */ j1(boolean z, int i10) {
        this.f8844i = i10;
        this.f8845l = z;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8844i) {
            case 0:
                return DetailsViewModel.C13181.AnonymousClass16.invokeSuspend$lambda$1(this.f8845l, (DetailsUiState) obj);
            case 1:
                return DetailsScreenKt.DetailsContent$lambda$18$1$0$5$0(this.f8845l, (GraphicsLayerScope) obj);
            default:
                return Boolean.valueOf(DetailsViewModel.toFilteredCommunityReviews$lambda$1(this.f8845l, (TraktComment) obj));
        }
    }
}
