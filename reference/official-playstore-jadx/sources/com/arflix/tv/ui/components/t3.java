package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.ui.screens.login.LoginScreenKt;
import com.arflix.tv.ui.screens.login.LoginViewModel;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t3 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8408i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f8409l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8410m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8411n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8412o;

    public /* synthetic */ t3(int i10, MutableState mutableState, Object obj, Object obj2, Object obj3) {
        this.f8408i = i10;
        this.f8411n = obj;
        this.f8412o = obj2;
        this.f8409l = mutableState;
        this.f8410m = obj3;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8408i) {
            case 0:
                return TextInputModalKt.TextInputModal$lambda$17$1$0$1$1$0((r7.l) this.f8411n, (Context) this.f8412o, this.f8409l, (MutableState) this.f8410m);
            case 1:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$9$0((LoginViewModel) this.f8411n, this.f8409l, (MutableState) this.f8410m, (MutableState) this.f8412o);
            case 2:
                return SearchScreenKt.SearchScreen$lambda$69$0$4$0((ColumnScope) this.f8411n, (MutableIntState) this.f8412o, this.f8409l, (FocusRequester) this.f8410m);
            case 3:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$15$0(this.f8409l, (MutableState) this.f8410m, (MutableState) this.f8411n, (MutableState) this.f8412o);
            case 4:
                return LiveTvScreenKt.LiveTvScreen$lambda$238$0((String) this.f8411n, this.f8409l, (MutableState) this.f8410m, (MutableState) this.f8412o);
            case 5:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$5$0((ExoPlayer) this.f8411n, this.f8409l, (MutableState) this.f8410m, (MutableState) this.f8412o);
            default:
                return QuickZapOverlayKt.QuickZapOverlay$lambda$15$0((r7.l) this.f8411n, (r7.a) this.f8412o, this.f8409l, (MutableState) this.f8410m);
        }
    }

    public /* synthetic */ t3(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        this.f8408i = 3;
        this.f8409l = mutableState;
        this.f8410m = mutableState2;
        this.f8411n = mutableState3;
        this.f8412o = mutableState4;
    }

    public /* synthetic */ t3(Object obj, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, int i10) {
        this.f8408i = i10;
        this.f8411n = obj;
        this.f8409l = mutableState;
        this.f8410m = mutableState2;
        this.f8412o = mutableState3;
    }
}
