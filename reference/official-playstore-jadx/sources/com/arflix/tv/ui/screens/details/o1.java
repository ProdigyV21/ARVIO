package com.arflix.tv.ui.screens.details;

import com.arflix.tv.ui.screens.details.DetailsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8939i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8940l;

    public /* synthetic */ o1(String str, int i10) {
        this.f8939i = i10;
        this.f8940l = str;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8939i) {
            case 0:
                return DetailsViewModel.C13181.AnonymousClass7.AnonymousClass2.invokeSuspend$lambda$1(this.f8940l, (DetailsUiState) obj);
            case 1:
                return DetailsViewModel.C13181.AnonymousClass8.invokeSuspend$lambda$1(this.f8940l, (DetailsUiState) obj);
            default:
                return DetailsViewModel.C13181.AnonymousClass9.invokeSuspend$lambda$1(this.f8940l, (DetailsUiState) obj);
        }
    }
}
