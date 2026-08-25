package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.State;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8947i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8948l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f8949m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8950n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f8951o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8952p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8953q;

    public /* synthetic */ q(List list, boolean z, FocusSection focusSection, int i10, boolean z5, State state) {
        this.f8949m = list;
        this.f8948l = z;
        this.f8952p = focusSection;
        this.f8950n = i10;
        this.f8951o = z5;
        this.f8953q = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8947i) {
            case 0:
                return DetailsScreenKt.DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0(this.f8949m, this.f8948l, (FocusSection) this.f8952p, this.f8950n, this.f8951o, (State) this.f8953q, (androidx.tv.foundation.lazy.list.g0) obj);
            default:
                return DetailsViewModel.C13181.AnonymousClass15.invokeSuspend$lambda$10(this.f8948l, (DetailsViewModel) this.f8952p, this.f8949m, this.f8950n, (Episode) this.f8953q, this.f8951o, (DetailsUiState) obj);
        }
    }

    public /* synthetic */ q(boolean z, DetailsViewModel detailsViewModel, List list, int i10, Episode episode, boolean z5) {
        this.f8948l = z;
        this.f8952p = detailsViewModel;
        this.f8949m = list;
        this.f8950n = i10;
        this.f8953q = episode;
        this.f8951o = z5;
    }
}
