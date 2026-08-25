package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.navigation.AppNavigationKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11266i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f11267l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.l f11268m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.l f11269n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f11270o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f11271p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f11272q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f11273r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f11274s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f11275t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f11276u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f11277v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ x6.o f11278w;

    public /* synthetic */ a(androidx.navigation.l0 l0Var, List list, MediaItem mediaItem, String str, Map map, Profile profile, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.l lVar2, boolean z) {
        this.f11271p = l0Var;
        this.f11272q = list;
        this.f11273r = mediaItem;
        this.f11267l = str;
        this.f11274s = map;
        this.f11275t = profile;
        this.f11268m = lVar;
        this.f11276u = aVar;
        this.f11277v = aVar2;
        this.f11278w = aVar3;
        this.f11269n = lVar2;
        this.f11270o = z;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11266i) {
            case 0:
                return CategorySidebarKt.CategorySidebar$lambda$38$2$0((LiveCategoryTree) this.f11271p, this.f11267l, (FocusRequester) this.f11272q, (FocusRequester) this.f11273r, this.f11270o, (MutableState) this.f11274s, this.f11268m, this.f11269n, (MutableState) this.f11275t, (MutableState) this.f11276u, (r7.p) this.f11278w, (MutableState) this.f11277v, (LazyListScope) obj);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0((androidx.navigation.l0) this.f11271p, (List) this.f11272q, (MediaItem) this.f11273r, this.f11267l, (Map) this.f11274s, (Profile) this.f11275t, this.f11268m, (r7.a) this.f11276u, (r7.a) this.f11277v, (r7.a) this.f11278w, this.f11269n, this.f11270o, (androidx.navigation.i0) obj);
        }
    }

    public /* synthetic */ a(LiveCategoryTree liveCategoryTree, String str, FocusRequester focusRequester, FocusRequester focusRequester2, boolean z, MutableState mutableState, r7.l lVar, r7.l lVar2, MutableState mutableState2, MutableState mutableState3, r7.p pVar, MutableState mutableState4) {
        this.f11271p = liveCategoryTree;
        this.f11267l = str;
        this.f11272q = focusRequester;
        this.f11273r = focusRequester2;
        this.f11270o = z;
        this.f11274s = mutableState;
        this.f11268m = lVar;
        this.f11269n = lVar2;
        this.f11275t = mutableState2;
        this.f11276u = mutableState3;
        this.f11278w = pVar;
        this.f11277v = mutableState4;
    }
}
