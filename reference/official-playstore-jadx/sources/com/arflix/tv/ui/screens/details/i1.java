package com.arflix.tv.ui.screens.details;

import androidx.compose.animation.core.Animatable;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import com.arflix.tv.data.api.TmdbCollectionRef;
import com.arflix.tv.data.repository.StreamingServicesResult;
import com.arflix.tv.ui.screens.details.DetailsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8824i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8825l;

    public /* synthetic */ i1(Object obj, int i10) {
        this.f8824i = i10;
        this.f8825l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8824i) {
            case 0:
                return DetailsViewModel.C13181.AnonymousClass13.invokeSuspend$lambda$1((TmdbCollectionRef) this.f8825l, (DetailsUiState) obj);
            case 1:
                return DetailsViewModel.C13181.AnonymousClass14.invokeSuspend$lambda$1((StreamingServicesResult) this.f8825l, (DetailsUiState) obj);
            case 2:
                return DetailsViewModel.C13181.AnonymousClass7.invokeSuspend$lambda$2((Integer) this.f8825l, (DetailsUiState) obj);
            default:
                return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$12$0((Animatable) this.f8825l, (GraphicsLayerScope) obj);
        }
    }
}
