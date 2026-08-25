package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.navigation.AppNavigationKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11873i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f11874l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f11875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Map f11876n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f11877o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f11878p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.l f11879q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f11880r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f11881s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f11882t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f11883u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f11884v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Object f11885w;
    public final /* synthetic */ Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ Object f11886y;

    public /* synthetic */ w2(androidx.navigation.l0 l0Var, String str, List list, MediaItem mediaItem, String str2, Map map, Profile profile, boolean z, r7.a aVar, r7.l lVar, r7.a aVar2, int i10, int i11, int i12) {
        this.f11883u = l0Var;
        this.f11874l = str;
        this.f11875m = list;
        this.f11884v = mediaItem;
        this.f11885w = str2;
        this.f11876n = map;
        this.x = profile;
        this.f11877o = z;
        this.f11878p = aVar;
        this.f11879q = lVar;
        this.f11886y = aVar2;
        this.f11880r = i10;
        this.f11881s = i11;
        this.f11882t = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f11873i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return QuickZapOverlayKt.QuickZapOverlay$lambda$19(this.f11877o, (EnrichedChannel) this.f11883u, this.f11875m, this.f11876n, (LiveCategoryTree) this.f11884v, this.f11874l, this.f11879q, this.f11878p, (r7.l) this.f11885w, (r7.l) this.x, (Modifier) this.f11886y, this.f11880r, this.f11881s, this.f11882t, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return AppNavigationKt.AppNavigation$lambda$10((androidx.navigation.l0) this.f11883u, this.f11874l, this.f11875m, (MediaItem) this.f11884v, (String) this.f11885w, this.f11876n, (Profile) this.x, this.f11877o, this.f11878p, this.f11879q, (r7.a) this.f11886y, this.f11880r, this.f11881s, this.f11882t, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ w2(boolean z, EnrichedChannel enrichedChannel, List list, Map map, LiveCategoryTree liveCategoryTree, String str, r7.l lVar, r7.a aVar, r7.l lVar2, r7.l lVar3, Modifier modifier, int i10, int i11, int i12) {
        this.f11877o = z;
        this.f11883u = enrichedChannel;
        this.f11875m = list;
        this.f11876n = map;
        this.f11884v = liveCategoryTree;
        this.f11874l = str;
        this.f11879q = lVar;
        this.f11878p = aVar;
        this.f11885w = lVar2;
        this.x = lVar3;
        this.f11886y = modifier;
        this.f11880r = i10;
        this.f11881s = i11;
        this.f11882t = i12;
    }
}
