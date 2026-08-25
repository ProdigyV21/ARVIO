package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt;
import com.arflix.tv.ui.screens.tv.live.ProgramCellKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11155i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f11156l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f11157m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f11158n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f11159o;

    public /* synthetic */ z1(String str, long j10, long j11, int i10, int i11) {
        this.f11155i = i11;
        this.f11156l = str;
        this.f11157m = j10;
        this.f11158n = j11;
        this.f11159o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f11155i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.AddonStatusChip_1wkBAMs$lambda$1(this.f11156l, this.f11157m, this.f11158n, this.f11159o, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return FullscreenGuideOverlayKt.GuideChip_1wkBAMs$lambda$1(this.f11156l, this.f11157m, this.f11158n, this.f11159o, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return ProgramCellKt.Badge_1wkBAMs$lambda$1(this.f11156l, this.f11157m, this.f11158n, this.f11159o, (Composer) obj, iIntValue3);
        }
    }
}
