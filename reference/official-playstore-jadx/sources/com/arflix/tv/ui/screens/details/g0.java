package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.profile.ProfileViewModel;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.EpgGridFocusMode;
import com.arflix.tv.ui.screens.tv.live.EpgGridKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8782i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8783l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8784m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8785n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8786o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8787p;

    public /* synthetic */ g0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5) {
        this.f8782i = 3;
        this.f8783l = mutableState;
        this.f8785n = mutableState2;
        this.f8786o = mutableState3;
        this.f8787p = mutableState4;
        this.f8784m = mutableState5;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8782i) {
            case 0:
                return DetailsScreenKt.DetailsScreen$lambda$97$9$1$0((DetailsViewModel) this.f8786o, (Episode) this.f8787p, (MutableState) this.f8783l, (MutableState) this.f8785n, (State) this.f8784m);
            case 1:
                return ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$13$0$2$0$0$0((ProfileViewModel) this.f8786o, (Profile) this.f8787p, (MutableState) this.f8783l, (State) this.f8784m, (MutableState) this.f8785n);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$63$0((SettingsViewModel) this.f8786o, (MutableState) this.f8783l, (MutableState) this.f8785n, (MutableState) this.f8787p, (MutableState) this.f8784m);
            case 3:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$65$0((MutableState) this.f8783l, (MutableState) this.f8785n, (MutableState) this.f8786o, (MutableState) this.f8787p, (MutableState) this.f8784m);
            case 4:
                return TvScreenKt.TvScreen$lambda$60$0((r7.a) this.f8786o, (MutableState) this.f8783l, (MutableState) this.f8785n, (MutableState) this.f8787p, (MutableState) this.f8784m);
            default:
                return EpgGridKt.EpgGrid$lambda$38$0((EpgGridFocusMode) this.f8786o, (r7.l) this.f8787p, (EnrichedChannel) this.f8783l, (r7.a) this.f8785n, (FocusRequester) this.f8784m);
        }
    }

    public /* synthetic */ g0(ProfileViewModel profileViewModel, Profile profile, MutableState mutableState, State state, MutableState mutableState2) {
        this.f8782i = 1;
        this.f8786o = profileViewModel;
        this.f8787p = profile;
        this.f8783l = mutableState;
        this.f8784m = state;
        this.f8785n = mutableState2;
    }

    public /* synthetic */ g0(Object obj, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, int i10) {
        this.f8782i = i10;
        this.f8786o = obj;
        this.f8783l = mutableState;
        this.f8785n = mutableState2;
        this.f8787p = mutableState3;
        this.f8784m = mutableState4;
    }

    public /* synthetic */ g0(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i10) {
        this.f8782i = i10;
        this.f8786o = obj;
        this.f8787p = obj2;
        this.f8783l = obj3;
        this.f8785n = obj4;
        this.f8784m = obj5;
    }
}
