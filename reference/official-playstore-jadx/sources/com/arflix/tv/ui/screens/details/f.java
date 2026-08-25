package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8768i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8769l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8770m;

    public /* synthetic */ f(String str, int i10, int i11) {
        this.f8768i = i11;
        this.f8769l = str;
        this.f8770m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f8768i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return DetailsScreenKt.StatusBadge$lambda$1(this.f8769l, this.f8770m, composer, iIntValue);
            case 1:
                return DetailsScreenKt.MetaPill$lambda$1(this.f8769l, this.f8770m, composer, iIntValue);
            case 2:
                return DetailsScreenKt.BudgetBadge$lambda$1(this.f8769l, this.f8770m, composer, iIntValue);
            case 3:
                return DetailsScreenKt.GenreBadge$lambda$1(this.f8769l, this.f8770m, composer, iIntValue);
            case 4:
                return DetailsScreenKt.ImdbBadge$lambda$1(this.f8769l, this.f8770m, composer, iIntValue);
            default:
                return DetailsScreenKt.LanguageBadge$lambda$1(this.f8769l, this.f8770m, composer, iIntValue);
        }
    }
}
