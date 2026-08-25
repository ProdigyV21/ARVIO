package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9831i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ka.k0 f9832l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f9833m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f9834n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f9835o;

    public /* synthetic */ z(ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester, int i10) {
        this.f9831i = i10;
        this.f9832l = k0Var;
        this.f9833m = mutableState;
        this.f9834n = mutableState2;
        this.f9835o = focusRequester;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9831i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$6$0(this.f9832l, this.f9833m, this.f9834n, this.f9835o);
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$14$0(this.f9832l, this.f9833m, this.f9834n, this.f9835o);
            case 2:
                return PlayerScreenKt.PlayerScreen$lambda$355$0(this.f9832l, this.f9833m, this.f9834n, this.f9835o);
            case 3:
                return PlayerScreenKt.PlayerScreen$lambda$356$0(this.f9832l, this.f9833m, this.f9834n, this.f9835o);
            case 4:
                return PlayerScreenKt.PlayerScreen$lambda$357$0(this.f9832l, this.f9833m, this.f9834n, this.f9835o);
            default:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$6$0(this.f9832l, this.f9833m, (MutableIntState) this.f9834n, this.f9835o);
        }
    }
}
