package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.repository.SkipInterval;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import com.arflix.tv.ui.screens.player.SkipIntroButtonKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7960i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7961l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7962m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f7963n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f7964o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f7965p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f7966q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7967r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f7968s;

    public /* synthetic */ f2(SkipInterval skipInterval, boolean z, boolean z5, r7.a aVar, FocusRequester focusRequester, Modifier modifier, int i10, int i11) {
        this.f7960i = 2;
        this.f7961l = skipInterval;
        this.f7963n = z;
        this.f7964o = z5;
        this.f7962m = aVar;
        this.f7967r = focusRequester;
        this.f7968s = modifier;
        this.f7965p = i10;
        this.f7966q = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7960i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsRowsKt.SettingsToggleRow$lambda$3((String) this.f7961l, (String) this.f7962m, this.f7963n, this.f7964o, (r7.l) this.f7967r, (Modifier) this.f7968s, this.f7965p, this.f7966q, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PlayerScreenKt.TrackMenuItem$lambda$2((String) this.f7961l, (String) this.f7962m, this.f7963n, this.f7964o, (r7.a) this.f7967r, (String) this.f7968s, this.f7965p, this.f7966q, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SkipIntroButtonKt.SkipIntroButton$lambda$12((SkipInterval) this.f7961l, this.f7963n, this.f7964o, (r7.a) this.f7962m, (FocusRequester) this.f7967r, (Modifier) this.f7968s, this.f7965p, this.f7966q, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ f2(String str, String str2, boolean z, boolean z5, x6.o oVar, Object obj, int i10, int i11, int i12) {
        this.f7960i = i12;
        this.f7961l = str;
        this.f7962m = str2;
        this.f7963n = z;
        this.f7964o = z5;
        this.f7967r = oVar;
        this.f7968s = obj;
        this.f7965p = i10;
        this.f7966q = i11;
    }
}
