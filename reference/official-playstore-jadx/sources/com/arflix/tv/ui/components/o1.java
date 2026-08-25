package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.LiveCategoryTree;
import com.arflix.tv.ui.screens.tv.live.SearchOverlayKt;
import com.arflix.tv.ui.screens.tv.live.TouchCategoryRailKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8222i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8223l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8224m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8225n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8226o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8227p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f8228q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8229r;

    public /* synthetic */ o1(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i10, int i11, int i12) {
        this.f8222i = i12;
        this.f8225n = obj;
        this.f8223l = obj2;
        this.f8224m = obj3;
        this.f8226o = obj4;
        this.f8229r = obj5;
        this.f8227p = i10;
        this.f8228q = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8222i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PlayerLoadingScreenKt.PlayerLoadingScreen$lambda$6((Modifier) this.f8225n, (String) this.f8223l, (String) this.f8224m, (String) this.f8226o, (String) this.f8229r, this.f8227p, this.f8228q, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PlayerScreenKt.PulsingLogo$lambda$4((String) this.f8223l, (String) this.f8224m, (Modifier) this.f8225n, (Float) this.f8229r, (String) this.f8226o, this.f8227p, this.f8228q, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.AccountDisconnectConfirmDialog$lambda$8((String) this.f8223l, (String) this.f8224m, (String) this.f8226o, (r7.a) this.f8225n, (r7.a) this.f8229r, this.f8227p, this.f8228q, (Composer) obj, iIntValue3);
            case 3:
                int iIntValue4 = ((Integer) obj2).intValue();
                return SettingsScreenKt.InputModal$lambda$20((String) this.f8223l, (String) this.f8224m, (List) this.f8225n, (r7.a) this.f8226o, (r7.a) this.f8229r, this.f8227p, this.f8228q, (Composer) obj, iIntValue4);
            case 4:
                int iIntValue5 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogDiscoveryInputButton$lambda$5((String) this.f8223l, (String) this.f8224m, (String) this.f8226o, (Modifier) this.f8225n, (r7.a) this.f8229r, this.f8227p, this.f8228q, (Composer) obj, iIntValue5);
            case 5:
                int iIntValue6 = ((Integer) obj2).intValue();
                return SearchOverlayKt.SearchOverlay$lambda$17((List) this.f8225n, (Map) this.f8223l, (r7.p) this.f8224m, (r7.a) this.f8226o, (r7.l) this.f8229r, this.f8227p, this.f8228q, (Composer) obj, iIntValue6);
            case 6:
                int iIntValue7 = ((Integer) obj2).intValue();
                return SearchOverlayKt.SearchResultRow$lambda$7((EnrichedChannel) this.f8224m, (String) this.f8223l, (r7.l) this.f8226o, (r7.a) this.f8229r, (Modifier) this.f8225n, this.f8227p, this.f8228q, (Composer) obj, iIntValue7);
            default:
                int iIntValue8 = ((Integer) obj2).intValue();
                return TouchCategoryRailKt.TouchCategoryRail$lambda$1((LiveCategoryTree) this.f8224m, (String) this.f8223l, (r7.l) this.f8226o, (r7.a) this.f8229r, (Modifier) this.f8225n, this.f8227p, this.f8228q, (Composer) obj, iIntValue8);
        }
    }

    public /* synthetic */ o1(Object obj, String str, r7.l lVar, r7.a aVar, Modifier modifier, int i10, int i11, int i12) {
        this.f8222i = i12;
        this.f8224m = obj;
        this.f8223l = str;
        this.f8226o = lVar;
        this.f8229r = aVar;
        this.f8225n = modifier;
        this.f8227p = i10;
        this.f8228q = i11;
    }

    public /* synthetic */ o1(String str, String str2, Modifier modifier, Float f10, String str3, int i10, int i11) {
        this.f8222i = 1;
        this.f8223l = str;
        this.f8224m = str2;
        this.f8225n = modifier;
        this.f8229r = f10;
        this.f8226o = str3;
        this.f8227p = i10;
        this.f8228q = i11;
    }

    public /* synthetic */ o1(String str, String str2, String str3, Object obj, r7.a aVar, int i10, int i11, int i12) {
        this.f8222i = i12;
        this.f8223l = str;
        this.f8224m = str2;
        this.f8226o = str3;
        this.f8225n = obj;
        this.f8229r = aVar;
        this.f8227p = i10;
        this.f8228q = i11;
    }

    public /* synthetic */ o1(String str, String str2, List list, r7.a aVar, r7.a aVar2, int i10, int i11) {
        this.f8222i = 3;
        this.f8223l = str;
        this.f8224m = str2;
        this.f8225n = list;
        this.f8226o = aVar;
        this.f8229r = aVar2;
        this.f8227p = i10;
        this.f8228q = i11;
    }
}
