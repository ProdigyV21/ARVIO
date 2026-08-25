package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.State;
import com.arflix.tv.data.model.StreamSource;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8982i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ DetailsViewModel f8983l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f8984m;

    public /* synthetic */ t(State state, DetailsViewModel detailsViewModel) {
        this.f8984m = state;
        this.f8983l = detailsViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8982i) {
            case 0:
                return DetailsScreenKt.DetailsScreen$lambda$92$0(this.f8984m, this.f8983l, ((Integer) obj).intValue());
            default:
                return DetailsScreenKt.DetailsScreen$lambda$97$6$0(this.f8983l, this.f8984m, (StreamSource) obj);
        }
    }

    public /* synthetic */ t(DetailsViewModel detailsViewModel, State state) {
        this.f8983l = detailsViewModel;
        this.f8984m = state;
    }
}
