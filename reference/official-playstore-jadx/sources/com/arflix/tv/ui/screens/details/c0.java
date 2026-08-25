package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8728i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8729l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f8730m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ImageVector f8731n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f8732o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f8733p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Modifier f8734q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f8735r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f8736s;

    public /* synthetic */ c0(ImageVector imageVector, String str, boolean z, boolean z5, Modifier modifier, r7.a aVar, int i10, int i11) {
        this.f8731n = imageVector;
        this.f8729l = str;
        this.f8732o = z;
        this.f8733p = z5;
        this.f8734q = modifier;
        this.f8730m = aVar;
        this.f8735r = i10;
        this.f8736s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8728i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return DetailsScreenKt.MobileIconActionButton$lambda$1(this.f8731n, this.f8729l, this.f8732o, this.f8733p, this.f8734q, this.f8730m, this.f8735r, this.f8736s, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                ImageVector imageVector = this.f8731n;
                String str = this.f8729l;
                boolean z = this.f8732o;
                boolean z5 = this.f8733p;
                Modifier modifier = this.f8734q;
                return SettingsScreenKt.DiscoveryActionButton$lambda$5(str, this.f8730m, imageVector, z, z5, modifier, this.f8735r, this.f8736s, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ c0(String str, r7.a aVar, ImageVector imageVector, boolean z, boolean z5, Modifier modifier, int i10, int i11) {
        this.f8729l = str;
        this.f8730m = aVar;
        this.f8731n = imageVector;
        this.f8732o = z;
        this.f8733p = z5;
        this.f8734q = modifier;
        this.f8735r = i10;
        this.f8736s = i11;
    }
}
