package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusState;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8465i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f8466l;

    public /* synthetic */ w1(MutableIntState mutableIntState, int i10) {
        this.f8465i = i10;
        this.f8466l = mutableIntState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8465i) {
            case 0:
                return ProfileIndicatorKt.ProfileIndicator$lambda$5$0(this.f8466l, (FocusState) obj);
            case 1:
                return ProfileDialogsKt.DialogButton$lambda$7$0(this.f8466l, (FocusState) obj);
            case 2:
                return ProfileDialogsKt.AvatarGridItem$lambda$6$0(this.f8466l, (FocusState) obj);
            case 3:
                return ProfileDialogsKt.AvatarGridItem$lambda$8$0(this.f8466l, (FocusState) obj);
            case 4:
                return ProfileSelectionScreenKt.ManageProfilesButton$lambda$5$0(this.f8466l, (FocusState) obj);
            case 5:
                return ProfileSelectionScreenKt.CloudConnectButton$lambda$5$0(this.f8466l, (FocusState) obj);
            case 6:
                return ProfileSelectionScreenKt.AddProfileButton_Kz89ssw$lambda$4$2$0(this.f8466l, (FocusState) obj);
            case 7:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$0$3$0(this.f8466l, ((Integer) obj).intValue());
            case 8:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$0$7$0(this.f8466l, ((Integer) obj).intValue());
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$4$0(this.f8466l, ((Integer) obj).intValue());
        }
    }
}
