package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.tv.TvViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11253i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11254l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11255m;

    public /* synthetic */ k0(Object obj, Object obj2, int i10) {
        this.f11253i = i10;
        this.f11254l = obj;
        this.f11255m = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11253i) {
            case 0:
                return Boolean.valueOf(TvViewModel.AnonymousClass3.invokeSuspend$lambda$1((TvViewModel) this.f11254l, (TvUiState) this.f11255m, (String) obj));
            default:
                return TvScreenKt.TvScreen$lambda$118$2$0$1$0((MutableIntState) this.f11254l, (MutableState) this.f11255m, ((Integer) obj).intValue());
        }
    }
}
