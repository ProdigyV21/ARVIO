package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import com.arflix.tv.ui.screens.details.DetailsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8861i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ DetailsViewModel f8862l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8863m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8864n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8865o;

    public /* synthetic */ k1(DetailsViewModel detailsViewModel, Object obj, Object obj2, Object obj3, int i10) {
        this.f8861i = i10;
        this.f8862l = detailsViewModel;
        this.f8863m = obj;
        this.f8864n = obj2;
        this.f8865o = obj3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8861i) {
            case 0:
                return DetailsViewModel.C13181.AnonymousClass18.invokeSuspend$lambda$2(this.f8862l, (Integer) this.f8863m, (Integer) this.f8864n, (ResumeInfo) this.f8865o, (DetailsUiState) obj);
            default:
                return DetailsScreenKt.DetailsScreen$lambda$89$0(this.f8862l, (MutableIntState) this.f8863m, (MutableIntState) this.f8864n, (MutableIntState) this.f8865o, ((Integer) obj).intValue());
        }
    }
}
