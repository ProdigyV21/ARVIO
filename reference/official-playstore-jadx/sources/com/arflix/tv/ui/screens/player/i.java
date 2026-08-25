package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.platform.ClipboardManager;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9550i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9551l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9552m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9553n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9554o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9555p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9556q;

    public /* synthetic */ i(ClipboardManager clipboardManager, List list, List list2, FocusRequester focusRequester, MutableIntState mutableIntState, MutableState mutableState) {
        this.f9550i = 3;
        this.f9556q = clipboardManager;
        this.f9552m = list;
        this.f9553n = list2;
        this.f9554o = focusRequester;
        this.f9555p = mutableIntState;
        this.f9551l = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9550i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$141$0((r7.r) this.f9556q, this.f9551l, (MutableState) this.f9552m, (MutableState) this.f9553n, (MutableState) this.f9554o, (MutableState) this.f9555p);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$11$0((State) this.f9556q, this.f9551l, (MutableState) this.f9552m, (MutableState) this.f9553n, (MutableState) this.f9554o, (MutableState) this.f9555p);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$25$0((SettingsViewModel) this.f9556q, this.f9551l, (MutableState) this.f9552m, (MutableState) this.f9553n, (MutableState) this.f9554o, (MutableState) this.f9555p);
            case 3:
                return SettingsScreenKt.InputModal$lambda$19$2$1$2$0((ClipboardManager) this.f9556q, (List) this.f9552m, (List) this.f9553n, (FocusRequester) this.f9554o, (MutableIntState) this.f9555p, this.f9551l);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$4$0((EnrichedChannel) this.f9556q, (TvViewModel) this.f9555p, this.f9551l, (MutableState) this.f9552m, (MutableState) this.f9553n, (MutableState) this.f9554o);
        }
    }

    public /* synthetic */ i(EnrichedChannel enrichedChannel, TvViewModel tvViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        this.f9550i = 4;
        this.f9556q = enrichedChannel;
        this.f9555p = tvViewModel;
        this.f9551l = mutableState;
        this.f9552m = mutableState2;
        this.f9553n = mutableState3;
        this.f9554o = mutableState4;
    }

    public /* synthetic */ i(Object obj, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, int i10) {
        this.f9550i = i10;
        this.f9556q = obj;
        this.f9551l = mutableState;
        this.f9552m = mutableState2;
        this.f9553n = mutableState3;
        this.f9554o = mutableState4;
        this.f9555p = mutableState5;
    }
}
