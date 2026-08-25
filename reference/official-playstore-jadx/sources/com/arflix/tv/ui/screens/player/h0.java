package com.arflix.tv.ui.screens.player;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9536i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f9537l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f9538m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9539n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9540o;

    public /* synthetic */ h0(MutableIntState mutableIntState, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, int i10) {
        this.f9536i = i10;
        this.f9537l = mutableIntState;
        this.f9538m = mutableLongState;
        this.f9539n = mutableState;
        this.f9540o = mutableState2;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f9536i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return PlayerScreenKt.PlayerScreen$lambda$362$2$18(this.f9537l, (MutableLongState) this.f9538m, (MutableLongState) this.f9539n, (MutableLongState) this.f9540o, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj3).intValue();
                return PlayerScreenKt.PlayerScreen$lambda$362$2$19(this.f9538m, (PlayerViewModel) this.f9539n, (r7.a) this.f9540o, this.f9537l, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj3).intValue();
                return PlayerScreenKt.PlayerScreen$lambda$362$2$10(this.f9537l, (MutableLongState) this.f9538m, (MutableIntState) this.f9539n, (MutableIntState) this.f9540o, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue3);
        }
    }

    public /* synthetic */ h0(State state, PlayerViewModel playerViewModel, r7.a aVar, MutableIntState mutableIntState) {
        this.f9536i = 1;
        this.f9538m = state;
        this.f9539n = playerViewModel;
        this.f9540o = aVar;
        this.f9537l = mutableIntState;
    }
}
