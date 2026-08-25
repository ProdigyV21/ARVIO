package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.screens.details.DetailsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8779i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8780l;

    public /* synthetic */ f1(int i10, int i11) {
        this.f8779i = i11;
        this.f8780l = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8779i) {
            case 0:
                return DetailsViewModel.C13181.invokeSuspend$lambda$13(this.f8780l, (DetailsUiState) obj);
            default:
                return Boolean.valueOf(AutoPlaySourcePlannerKt.bestAutoPlayStream$lambda$0(this.f8780l, (StreamSource) obj));
        }
    }
}
