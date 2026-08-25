package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10478i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10479l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f10480m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f10481n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f10482o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f10483p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f10484q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ String f10485r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.l f10486s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ r7.l f10487t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f10488u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f10489v;

    public /* synthetic */ g0(r7.a aVar, FocusRequester focusRequester, String str, r7.a aVar2, FocusRequester focusRequester2, FocusRequester focusRequester3, MutableIntState mutableIntState, String str2, String str3, r7.l lVar, r7.l lVar2) {
        this.f10479l = aVar;
        this.f10480m = focusRequester;
        this.f10481n = str;
        this.f10482o = aVar2;
        this.f10483p = focusRequester2;
        this.f10488u = focusRequester3;
        this.f10484q = mutableIntState;
        this.f10485r = str2;
        this.f10489v = str3;
        this.f10486s = lVar;
        this.f10487t = lVar2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10478i) {
            case 0:
                return SettingsScreenKt.CloudEmailPasswordModal$lambda$7(this.f10479l, this.f10482o, (r7.a) this.f10488u, (r7.a) this.f10489v, this.f10484q, this.f10480m, this.f10481n, this.f10486s, this.f10483p, this.f10485r, this.f10487t, (Composer) obj, ((Integer) obj2).intValue());
            default:
                return SettingsScreenKt.QualityFilterEditorModal$lambda$8(this.f10479l, this.f10480m, this.f10481n, this.f10482o, 4, this.f10483p, (FocusRequester) this.f10488u, this.f10484q, this.f10485r, (String) this.f10489v, this.f10486s, this.f10487t, (Composer) obj, ((Integer) obj2).intValue());
        }
    }

    public /* synthetic */ g0(r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, MutableIntState mutableIntState, FocusRequester focusRequester, String str, r7.l lVar, FocusRequester focusRequester2, String str2, r7.l lVar2) {
        this.f10479l = aVar;
        this.f10482o = aVar2;
        this.f10488u = aVar3;
        this.f10489v = aVar4;
        this.f10484q = mutableIntState;
        this.f10480m = focusRequester;
        this.f10481n = str;
        this.f10486s = lVar;
        this.f10483p = focusRequester2;
        this.f10485r = str2;
        this.f10487t = lVar2;
    }
}
