package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10554i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10555l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f10556m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.l f10557n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.l f10558o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f10559p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ String f10560q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f10561r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f10562s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f10563t;

    public /* synthetic */ i0(int i10, LazyListState lazyListState, FocusRequester focusRequester, String str, String str2, List list, r7.a aVar, r7.l lVar, r7.l lVar2) {
        this.f10555l = aVar;
        this.f10561r = focusRequester;
        this.f10556m = i10;
        this.f10557n = lVar;
        this.f10562s = list;
        this.f10558o = lVar2;
        this.f10559p = str;
        this.f10563t = lazyListState;
        this.f10560q = str2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10554i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.CloudEmailPasswordModal$lambda$8(this.f10559p, this.f10560q, this.f10557n, this.f10558o, this.f10555l, (r7.a) this.f10561r, (r7.a) this.f10562s, (r7.a) this.f10563t, this.f10556m, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.SubtitlePickerModal$lambda$3(this.f10555l, (FocusRequester) this.f10561r, this.f10556m, this.f10557n, (List) this.f10562s, this.f10558o, this.f10559p, (LazyListState) this.f10563t, this.f10560q, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ i0(String str, String str2, r7.l lVar, r7.l lVar2, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i10) {
        this.f10559p = str;
        this.f10560q = str2;
        this.f10557n = lVar;
        this.f10558o = lVar2;
        this.f10555l = aVar;
        this.f10561r = aVar2;
        this.f10562s = aVar3;
        this.f10563t = aVar4;
        this.f10556m = i10;
    }
}
