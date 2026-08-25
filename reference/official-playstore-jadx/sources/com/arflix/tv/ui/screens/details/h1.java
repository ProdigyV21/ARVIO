package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.unit.Density;
import com.arflix.tv.data.api.TmdbCollectionRef;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import com.arflix.tv.util.AnimeSeasonStructure;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8798i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8799l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8800m;

    public /* synthetic */ h1(TmdbCollectionRef tmdbCollectionRef, List list) {
        this.f8798i = 1;
        this.f8800m = tmdbCollectionRef;
        this.f8799l = list;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8798i) {
            case 0:
                return DetailsViewModel.C13181.AnonymousClass11.invokeSuspend$lambda$3((List) this.f8799l, (Map) this.f8800m, (DetailsUiState) obj);
            case 1:
                return DetailsViewModel.C13181.AnonymousClass13.AnonymousClass2.invokeSuspend$lambda$1((TmdbCollectionRef) this.f8800m, (List) this.f8799l, (DetailsUiState) obj);
            case 2:
                return DetailsViewModel.C13181.AnonymousClass5.invokeSuspend$lambda$2((SeasonProgressResult) this.f8799l, (AnimeSeasonStructure) this.f8800m, (DetailsUiState) obj);
            case 3:
                return DetailsViewModel.C13181.AnonymousClass7.invokeSuspend$lambda$1((String) this.f8799l, (Integer) this.f8800m, (DetailsUiState) obj);
            case 4:
                return DetailsScreenKt.DetailsContent$lambda$16$0$0$0$0((ScrollState) this.f8799l, (State) this.f8800m, (GraphicsLayerScope) obj);
            case 5:
                return DetailsScreenKt.DetailsScreen$lambda$90$0((MutableIntState) this.f8799l, (MutableState) this.f8800m, ((Integer) obj).intValue());
            case 6:
                return DetailsScreenKt.PremiumActionButton$lambda$10$0$0$0((Density) this.f8799l, (State) this.f8800m, (GraphicsLayerScope) obj);
            case 7:
                return DetailsScreenKt.DetailsContent$lambda$16$0$0$2$1$0((ScrollState) this.f8799l, (MutableState) this.f8800m, (GraphicsLayerScope) obj);
            default:
                return DetailsScreenKt.DetailsContent$lambda$16$0$5$0((List) this.f8799l, (r7.l) this.f8800m, (LazyListScope) obj);
        }
    }

    public /* synthetic */ h1(Object obj, Object obj2, int i10) {
        this.f8798i = i10;
        this.f8799l = obj;
        this.f8800m = obj2;
    }
}
