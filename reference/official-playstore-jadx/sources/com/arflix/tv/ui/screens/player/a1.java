package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a1 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9385i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f9386l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9387m;

    public /* synthetic */ a1(int i10, int i11, r7.l lVar) {
        this.f9385i = i11;
        this.f9386l = lVar;
        this.f9387m = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9385i) {
            case 0:
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$1$4$0$4$0(this.f9386l, this.f9387m);
            case 1:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$1$1$0$0$0$1$0$0(this.f9386l, this.f9387m);
            case 2:
                return ProfileDialogsKt.ProfileDialogContent$lambda$10$0$0$5$0$0$1$0$0(this.f9386l, this.f9387m);
            case 3:
                return SettingsScreenKt.IptvSettings$lambda$8$1$0$1$2$3$0(this.f9386l, this.f9387m);
            case 4:
                return SettingsScreenKt.IptvSettings$lambda$9$0$2$3$0(this.f9386l, this.f9387m);
            case 5:
                return SettingsScreenKt.IptvSettings$lambda$9$0$2$4$0(this.f9386l, this.f9387m);
            case 6:
                return SettingsScreenKt.IptvSettings$lambda$9$0$2$5$0(this.f9386l, this.f9387m);
            case 7:
                return SettingsScreenKt.IptvSettings$lambda$9$0$2$6$0(this.f9386l, this.f9387m);
            case 8:
                return SettingsScreenKt.IptvSettings$lambda$9$0$1$0(this.f9386l, this.f9387m);
            default:
                return SettingsScreenKt.IptvSettings$lambda$9$0$2$2$0(this.f9386l, this.f9387m);
        }
    }
}
