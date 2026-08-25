package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9557i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9558l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9559m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9560n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9561o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9562p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9563q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f9564r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f9565s;

    public /* synthetic */ i0(ExoPlayer exoPlayer, MutableIntState mutableIntState, MutableState mutableState, String str, MutableState mutableState2, MutableIntState mutableIntState2, MutableIntState mutableIntState3, State state) {
        this.f9558l = exoPlayer;
        this.f9561o = mutableIntState;
        this.f9559m = mutableState;
        this.f9564r = str;
        this.f9560n = mutableState2;
        this.f9562p = mutableIntState2;
        this.f9563q = mutableIntState3;
        this.f9565s = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9557i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$1$0((ExoPlayer) this.f9558l, (MutableIntState) this.f9561o, (MutableState) this.f9559m, (String) this.f9564r, (MutableState) this.f9560n, (MutableIntState) this.f9562p, (MutableIntState) this.f9563q, (State) this.f9565s, (FullViewportSubtitlePlayerView) obj);
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$2$0((ExoPlayer) this.f9558l, (ka.k0) this.f9562p, (MutableState) this.f9559m, (MutableState) this.f9560n, (MutableIntState) this.f9561o, (MutableState) this.f9563q, (MutableState) this.f9564r, (FocusRequester) this.f9565s, (AudioTrackInfo) obj);
            default:
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0((SettingsUiState) this.f9558l, (r7.a) this.f9561o, (r7.a) this.f9562p, (r7.a) this.f9563q, (r7.a) this.f9559m, (r7.l) this.f9560n, (r7.a) this.f9564r, (SettingsViewModel) this.f9565s, (LazyListScope) obj);
        }
    }

    public /* synthetic */ i0(ExoPlayer exoPlayer, ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState, MutableState mutableState3, MutableState mutableState4, FocusRequester focusRequester) {
        this.f9558l = exoPlayer;
        this.f9562p = k0Var;
        this.f9559m = mutableState;
        this.f9560n = mutableState2;
        this.f9561o = mutableIntState;
        this.f9563q = mutableState3;
        this.f9564r = mutableState4;
        this.f9565s = focusRequester;
    }

    public /* synthetic */ i0(SettingsUiState settingsUiState, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.l lVar, r7.a aVar5, SettingsViewModel settingsViewModel) {
        this.f9558l = settingsUiState;
        this.f9561o = aVar;
        this.f9562p = aVar2;
        this.f9563q = aVar3;
        this.f9559m = aVar4;
        this.f9560n = lVar;
        this.f9564r = aVar5;
        this.f9565s = settingsViewModel;
    }
}
