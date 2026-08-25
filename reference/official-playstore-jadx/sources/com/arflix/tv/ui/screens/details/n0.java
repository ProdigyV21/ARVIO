package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.ScrollState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.Density;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import com.arflix.tv.util.AnimeSeasonStructure;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8917i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8918l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8919m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8920n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8921o;

    public /* synthetic */ n0(int i10, int i11, Object obj, Object obj2, Object obj3) {
        this.f8917i = i11;
        this.f8919m = obj;
        this.f8920n = obj2;
        this.f8918l = i10;
        this.f8921o = obj3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8917i) {
            case 0:
                return DetailsScreenKt.DetailsCastRail_6PoWaU8$lambda$0$0$0((List) this.f8919m, (FocusSection) this.f8920n, this.f8918l, (State) this.f8921o, (androidx.tv.foundation.lazy.list.g0) obj);
            case 1:
                return DetailsViewModel.C13181.AnonymousClass17.invokeSuspend$lambda$4((AnimeSeasonStructure) this.f8919m, (DetailsViewModel) this.f8920n, this.f8918l, (Map) this.f8921o, (DetailsUiState) obj);
            default:
                return DetailsScreenKt.DetailsContent$lambda$16$0$0$2$0$0((ScrollState) this.f8919m, this.f8918l, (Density) this.f8920n, (MutableState) this.f8921o, (LayoutCoordinates) obj);
        }
    }

    public /* synthetic */ n0(ScrollState scrollState, int i10, Density density, MutableState mutableState) {
        this.f8917i = 2;
        this.f8919m = scrollState;
        this.f8918l = i10;
        this.f8920n = density;
        this.f8921o = mutableState;
    }
}
