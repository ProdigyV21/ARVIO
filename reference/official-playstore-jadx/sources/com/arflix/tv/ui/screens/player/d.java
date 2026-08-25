package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9428i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9429l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f9430m;

    public /* synthetic */ d(Object obj, State state, int i10) {
        this.f9428i = i10;
        this.f9429l = obj;
        this.f9430m = state;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9428i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$361$0((MutableIntState) this.f9429l, (MutableIntState) this.f9430m);
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$11$0((MutableState) this.f9429l, (MutableState) this.f9430m);
            default:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$24$0((FocusRequester) this.f9429l, this.f9430m);
        }
    }
}
