package com.arflix.tv.ui.screens.profile;

import android.net.Uri;
import com.arflix.tv.navigation.AppNavigationKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10007i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10008l;

    public /* synthetic */ m(r7.l lVar, int i10) {
        this.f10007i = i10;
        this.f10008l = lVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10007i) {
            case 0:
                return ProfileDialogsKt.ProfileDialogContent$lambda$6$0(this.f10008l, (Uri) obj);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$3$4$0(this.f10008l, (String) obj);
        }
    }
}
