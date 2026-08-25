package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.Profile;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7874i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Profile f7875l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7876m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7877n;

    public /* synthetic */ d(Profile profile, boolean z, int i10, int i11) {
        this.f7874i = i11;
        this.f7875l = profile;
        this.f7876m = z;
        this.f7877n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f7874i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return AppTopBarKt.TopBarProfileAvatar$lambda$4(this.f7875l, this.f7876m, this.f7877n, composer, iIntValue);
            default:
                return SidebarKt.SidebarProfileAvatar$lambda$3(this.f7875l, this.f7876m, this.f7877n, composer, iIntValue);
        }
    }
}
