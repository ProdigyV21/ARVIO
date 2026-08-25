package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e2 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10429i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f10430l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f10431m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10432n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f10433o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f10434p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ String f10435q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ String f10436r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f10437s;

    public /* synthetic */ e2(boolean z, FocusRequester focusRequester, r7.a aVar, r7.a aVar2, MutableIntState mutableIntState, String str, String str2, boolean z5, int i10) {
        this.f10429i = i10;
        this.f10430l = z;
        this.f10431m = focusRequester;
        this.f10432n = aVar;
        this.f10433o = aVar2;
        this.f10434p = mutableIntState;
        this.f10435q = str;
        this.f10436r = str2;
        this.f10437s = z5;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f10429i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return SettingsScreenKt.CloudPairModal$lambda$6$0(this.f10430l, this.f10431m, this.f10432n, this.f10433o, this.f10434p, this.f10435q, this.f10436r, this.f10437s, (BoxScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.CloudPairModal$lambda$6$0$0(this.f10430l, this.f10431m, this.f10432n, this.f10433o, this.f10434p, this.f10435q, this.f10436r, this.f10437s, (BoxWithConstraintsScope) obj, (Composer) obj2, iIntValue2);
        }
    }
}
