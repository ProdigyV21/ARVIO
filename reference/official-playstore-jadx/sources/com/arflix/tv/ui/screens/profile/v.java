package com.arflix.tv.ui.screens.profile;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10072i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f10073l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.l f10074m;

    public /* synthetic */ v(int i10, int i11, r7.l lVar) {
        this.f10072i = i11;
        this.f10073l = i10;
        this.f10074m = lVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i10 = this.f10072i;
        LazyItemScope lazyItemScope = (LazyItemScope) obj;
        Composer composer = (Composer) obj2;
        int iIntValue = ((Integer) obj3).intValue();
        switch (i10) {
            case 0:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$5$0$0$0(this.f10073l, this.f10074m, lazyItemScope, composer, iIntValue);
            default:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$1$0$0$0$0(this.f10073l, this.f10074m, lazyItemScope, composer, iIntValue);
        }
    }
}
