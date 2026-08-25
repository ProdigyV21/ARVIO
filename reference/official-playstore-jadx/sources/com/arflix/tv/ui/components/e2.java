package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.tv.live.LivePanesKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7932i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f7933l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f7934m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f7935n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Modifier f7936o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f7937p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f7938q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f7939r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f7940s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f7941t;

    public /* synthetic */ e2(ImageVector imageVector, String str, String str2, String str3, boolean z, r7.a aVar, Modifier modifier, int i10, int i11) {
        this.f7940s = imageVector;
        this.f7933l = str;
        this.f7934m = str2;
        this.f7941t = str3;
        this.f7937p = z;
        this.f7935n = aVar;
        this.f7936o = modifier;
        this.f7938q = i10;
        this.f7939r = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7932i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsRowsKt.SettingsRow$lambda$2((ImageVector) this.f7940s, this.f7933l, this.f7934m, (String) this.f7941t, this.f7937p, this.f7935n, this.f7936o, this.f7938q, this.f7939r, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return LivePanesKt.EmptyStatePane$lambda$1(this.f7933l, this.f7934m, this.f7935n, this.f7936o, this.f7937p, (r7.a) this.f7940s, (FocusRequester) this.f7941t, this.f7938q, this.f7939r, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ e2(String str, String str2, r7.a aVar, Modifier modifier, boolean z, r7.a aVar2, FocusRequester focusRequester, int i10, int i11) {
        this.f7933l = str;
        this.f7934m = str2;
        this.f7935n = aVar;
        this.f7936o = modifier;
        this.f7937p = z;
        this.f7940s = aVar2;
        this.f7941t = focusRequester;
        this.f7938q = i10;
        this.f7939r = i11;
    }
}
