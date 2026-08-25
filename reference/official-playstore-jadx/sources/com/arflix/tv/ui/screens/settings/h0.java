package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.repository.HomeServerConnection;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.EpgGridKt;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10512i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10513l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10514m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10515n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f10516o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f10517p;

    public /* synthetic */ h0(androidx.lifecycle.y yVar, ExoPlayer exoPlayer, TvViewModel tvViewModel, MutableState mutableState, State state) {
        this.f10512i = 3;
        this.f10514m = yVar;
        this.f10515n = exoPlayer;
        this.f10516o = tvViewModel;
        this.f10513l = mutableState;
        this.f10517p = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10512i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$67$0((MutableState) this.f10513l, (MutableState) this.f10514m, (MutableState) this.f10515n, (MutableState) this.f10516o, (MutableState) this.f10517p, (HomeServerConnection) obj);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$206$0((SettingsViewModel) this.f10515n, (MutableState) this.f10513l, (MutableState) this.f10514m, (MutableIntState) this.f10516o, (MutableIntState) this.f10517p, (String) obj);
            case 2:
                return EpgGridKt.ProgramsRow_n3xTMnA$lambda$0$5$0((Map) this.f10513l, (EnrichedChannel) this.f10514m, (List) this.f10515n, (Map) this.f10516o, (List) this.f10517p, (DisposableEffectScope) obj);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$213$0((androidx.lifecycle.y) this.f10514m, (ExoPlayer) this.f10515n, (TvViewModel) this.f10516o, (MutableState) this.f10513l, (State) this.f10517p, (DisposableEffectScope) obj);
        }
    }

    public /* synthetic */ h0(SettingsViewModel settingsViewModel, MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        this.f10512i = 1;
        this.f10515n = settingsViewModel;
        this.f10513l = mutableState;
        this.f10514m = mutableState2;
        this.f10516o = mutableIntState;
        this.f10517p = mutableIntState2;
    }

    public /* synthetic */ h0(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i10) {
        this.f10512i = i10;
        this.f10513l = obj;
        this.f10514m = obj2;
        this.f10515n = obj3;
        this.f10516o = obj4;
        this.f10517p = obj5;
    }
}
