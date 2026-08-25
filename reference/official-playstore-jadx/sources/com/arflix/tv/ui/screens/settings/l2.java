package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.repository.IptvPlaylistEntry;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l2 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10648i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10649l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ IptvPlaylistEntry f10650m;

    public /* synthetic */ l2(r7.l lVar, IptvPlaylistEntry iptvPlaylistEntry, int i10) {
        this.f10648i = i10;
        this.f10649l = lVar;
        this.f10650m = iptvPlaylistEntry;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10648i) {
            case 0:
                return SettingsScreenKt.IptvSettings$lambda$8$1$0$1$2$2$0(this.f10649l, this.f10650m);
            default:
                return SettingsScreenKt.IptvSettings$lambda$9$0$2$1$0(this.f10649l, this.f10650m);
        }
    }
}
