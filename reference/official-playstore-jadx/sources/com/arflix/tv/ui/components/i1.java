package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.CatalogPackManifest;
import com.arflix.tv.data.model.PersonDetails;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8079i = 2;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f8080l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f8081m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8082n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f8083o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f8084p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8085q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8086r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f8087s;

    public /* synthetic */ i1(MutableIntState mutableIntState, FocusRequester focusRequester, CatalogPackManifest catalogPackManifest, String str, r7.a aVar, r7.l lVar, boolean z, boolean z5) {
        this.f8082n = z;
        this.f8080l = focusRequester;
        this.f8081m = aVar;
        this.f8083o = mutableIntState;
        this.f8084p = z5;
        this.f8085q = str;
        this.f8086r = catalogPackManifest;
        this.f8087s = lVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8079i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return PersonModalKt.PersonModal$lambda$10(this.f8080l, this.f8081m, this.f8082n, (PersonDetails) this.f8085q, (r7.p) this.f8086r, this.f8083o, this.f8084p, (ScrollState) this.f8087s, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj3).intValue();
                return QuickActionMenuKt.QuickActionMenu$lambda$9(this.f8080l, this.f8081m, (r7.a) this.f8085q, this.f8082n, (r7.a) this.f8086r, this.f8083o, (MutableState) this.f8087s, this.f8084p, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj3).intValue();
                return SettingsScreenKt.CatalogPackImportDialog$lambda$5$1(this.f8082n, this.f8080l, this.f8081m, this.f8083o, this.f8084p, (String) this.f8085q, (CatalogPackManifest) this.f8086r, (r7.l) this.f8087s, (BoxScope) obj, (Composer) obj2, iIntValue3);
        }
    }

    public /* synthetic */ i1(FocusRequester focusRequester, r7.a aVar, r7.a aVar2, boolean z, r7.a aVar3, MutableIntState mutableIntState, MutableState mutableState, boolean z5) {
        this.f8080l = focusRequester;
        this.f8081m = aVar;
        this.f8085q = aVar2;
        this.f8082n = z;
        this.f8086r = aVar3;
        this.f8083o = mutableIntState;
        this.f8087s = mutableState;
        this.f8084p = z5;
    }

    public /* synthetic */ i1(FocusRequester focusRequester, r7.a aVar, boolean z, PersonDetails personDetails, r7.p pVar, MutableIntState mutableIntState, boolean z5, ScrollState scrollState) {
        this.f8080l = focusRequester;
        this.f8081m = aVar;
        this.f8082n = z;
        this.f8085q = personDetails;
        this.f8086r = pVar;
        this.f8083o = mutableIntState;
        this.f8084p = z5;
        this.f8087s = scrollState;
    }
}
