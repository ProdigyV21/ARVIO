package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.SkipInterval;
import com.arflix.tv.ui.screens.player.SkipIntroButtonKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9317i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f9318l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9319m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9320n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9321o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9322p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9323q;

    public /* synthetic */ u(FocusRequester focusRequester, r7.a aVar, State state, RoundedCornerShape roundedCornerShape, MutableState mutableState, SkipInterval skipInterval) {
        this.f9319m = focusRequester;
        this.f9318l = aVar;
        this.f9320n = state;
        this.f9321o = roundedCornerShape;
        this.f9322p = mutableState;
        this.f9323q = skipInterval;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f9317i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return HomeScreenKt.MobileHomeRowsLayer_uS7v9H0$lambda$5$0$0((List) this.f9319m, (Map) this.f9320n, (Profile) this.f9321o, this.f9318l, (r7.a) this.f9322p, (r7.r) this.f9323q, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SkipIntroButtonKt.SkipIntroButton$lambda$11((FocusRequester) this.f9319m, this.f9318l, (State) this.f9320n, (RoundedCornerShape) this.f9321o, (MutableState) this.f9322p, (SkipInterval) this.f9323q, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj3).intValue();
                return SettingsScreenKt.CatalogDiscoveryTextInputDialog$lambda$3$0((String) this.f9319m, (MutableState) this.f9320n, (String) this.f9321o, (String) this.f9322p, (r7.l) this.f9323q, this.f9318l, (BoxWithConstraintsScope) obj, (Composer) obj2, iIntValue3);
        }
    }

    public /* synthetic */ u(String str, MutableState mutableState, String str2, String str3, r7.l lVar, r7.a aVar) {
        this.f9319m = str;
        this.f9320n = mutableState;
        this.f9321o = str2;
        this.f9322p = str3;
        this.f9323q = lVar;
        this.f9318l = aVar;
    }

    public /* synthetic */ u(List list, Map map, Profile profile, r7.a aVar, r7.a aVar2, r7.r rVar) {
        this.f9319m = list;
        this.f9320n = map;
        this.f9321o = profile;
        this.f9318l = aVar;
        this.f9322p = aVar2;
        this.f9323q = rVar;
    }
}
