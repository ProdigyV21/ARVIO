package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9934i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f9935l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f9936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9937n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f9938o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9939p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9940q;

    public /* synthetic */ e(String str, ImageVector imageVector, long j10, r7.a aVar, int i10, int i11) {
        this.f9934i = 1;
        this.f9935l = str;
        this.f9940q = imageVector;
        this.f9936m = j10;
        this.f9937n = aVar;
        this.f9938o = i10;
        this.f9939p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9934i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PinEntryDialogKt.PinActionButton_cf5BqRc$lambda$4(this.f9935l, (r7.a) this.f9937n, this.f9936m, (Modifier) this.f9940q, this.f9938o, this.f9939p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return TvScreenKt.FocusableMenuItem_cf5BqRc$lambda$6(this.f9935l, (ImageVector) this.f9940q, this.f9936m, (r7.a) this.f9937n, this.f9938o, this.f9939p, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return FullscreenGuideOverlayKt.GuideTimelinePill_cf5BqRc$lambda$1(this.f9935l, (String) this.f9937n, this.f9936m, (Modifier) this.f9940q, this.f9938o, this.f9939p, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ e(String str, Object obj, long j10, Modifier modifier, int i10, int i11, int i12) {
        this.f9934i = i12;
        this.f9935l = str;
        this.f9937n = obj;
        this.f9936m = j10;
        this.f9940q = modifier;
        this.f9938o = i10;
        this.f9939p = i11;
    }
}
