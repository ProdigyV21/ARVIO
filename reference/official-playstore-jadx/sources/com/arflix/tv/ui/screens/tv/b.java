package com.arflix.tv.ui.screens.tv;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import androidx.media3.ui.PlayerView;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11186i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f11187l;

    public /* synthetic */ b(MutableState mutableState, int i10) {
        this.f11186i = i10;
        this.f11187l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11186i) {
            case 0:
                return TvScreenKt.FocusableMenuItem_cf5BqRc$lambda$3$0(this.f11187l, (FocusState) obj);
            case 1:
                return TvScreenKt.TvScreen$lambda$118$2$1$0$3$0$0(this.f11187l, (Context) obj);
            case 2:
                return TvScreenKt.TvScreen$lambda$118$4$1$0(this.f11187l, (Context) obj);
            default:
                return TvScreenKt.TvScreen$lambda$118$4$2$0(this.f11187l, (PlayerView) obj);
        }
    }
}
