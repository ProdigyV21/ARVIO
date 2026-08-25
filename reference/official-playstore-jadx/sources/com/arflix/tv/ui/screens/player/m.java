package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9624i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ImageVector f9625l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f9626m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f9627n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f9628o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f9629p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f9630q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f9631r;

    public /* synthetic */ m(ImageVector imageVector, String str, boolean z, boolean z5, r7.a aVar, int i10, int i11) {
        this.f9625l = imageVector;
        this.f9626m = str;
        this.f9627n = z;
        this.f9628o = z5;
        this.f9629p = aVar;
        this.f9630q = i10;
        this.f9631r = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9624i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                ImageVector imageVector = this.f9625l;
                return PlayerScreenKt.ErrorButton$lambda$4(this.f9626m, imageVector, this.f9627n, this.f9628o, this.f9629p, this.f9630q, this.f9631r, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.SettingsSectionItem$lambda$3(this.f9625l, this.f9626m, this.f9627n, this.f9628o, this.f9629p, this.f9630q, this.f9631r, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ m(String str, ImageVector imageVector, boolean z, boolean z5, r7.a aVar, int i10, int i11) {
        this.f9626m = str;
        this.f9625l = imageVector;
        this.f9627n = z;
        this.f9628o = z5;
        this.f9629p = aVar;
        this.f9630q = i10;
        this.f9631r = i11;
    }
}
