package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.data.repository.HomeServerLibrarySort;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.tv.live.FullscreenHudKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7920i = 2;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7921l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7922m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f7923n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f7924o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f7925p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f7926q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7927r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f7928s;

    public /* synthetic */ e0(ImageVector imageVector, String str, Modifier modifier, boolean z, FocusRequester focusRequester, r7.a aVar, int i10, int i11) {
        this.f7922m = imageVector;
        this.f7921l = str;
        this.f7927r = modifier;
        this.f7923n = z;
        this.f7928s = focusRequester;
        this.f7924o = aVar;
        this.f7925p = i10;
        this.f7926q = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7920i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ContextMenuKt.ContextMenu$lambda$10(this.f7923n, (String) this.f7921l, (String) this.f7922m, (List) this.f7927r, (r7.l) this.f7928s, this.f7924o, this.f7925p, this.f7926q, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.SettingsActionRow$lambda$2((String) this.f7921l, (String) this.f7922m, (String) this.f7927r, this.f7923n, this.f7924o, (Modifier) this.f7928s, this.f7925p, this.f7926q, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return FullscreenHudKt.HudIconButton$lambda$5((ImageVector) this.f7922m, (String) this.f7921l, (Modifier) this.f7927r, this.f7923n, (FocusRequester) this.f7928s, this.f7924o, this.f7925p, this.f7926q, (Composer) obj, iIntValue3);
            default:
                int iIntValue4 = ((Integer) obj2).intValue();
                return WatchlistScreenKt.SortSelectionOverlay$lambda$2((List) this.f7927r, (HomeServerLibrarySort) this.f7921l, this.f7925p, this.f7923n, (r7.l) this.f7928s, (r7.l) this.f7922m, this.f7924o, this.f7926q, (Composer) obj, iIntValue4);
        }
    }

    public /* synthetic */ e0(String str, String str2, String str3, boolean z, r7.a aVar, Modifier modifier, int i10, int i11) {
        this.f7921l = str;
        this.f7922m = str2;
        this.f7927r = str3;
        this.f7923n = z;
        this.f7924o = aVar;
        this.f7928s = modifier;
        this.f7925p = i10;
        this.f7926q = i11;
    }

    public /* synthetic */ e0(List list, HomeServerLibrarySort homeServerLibrarySort, int i10, boolean z, r7.l lVar, r7.l lVar2, r7.a aVar, int i11) {
        this.f7927r = list;
        this.f7921l = homeServerLibrarySort;
        this.f7925p = i10;
        this.f7923n = z;
        this.f7928s = lVar;
        this.f7922m = lVar2;
        this.f7924o = aVar;
        this.f7926q = i11;
    }

    public /* synthetic */ e0(boolean z, String str, String str2, List list, r7.l lVar, r7.a aVar, int i10, int i11) {
        this.f7923n = z;
        this.f7921l = str;
        this.f7922m = str2;
        this.f7927r = list;
        this.f7928s = lVar;
        this.f7924o = aVar;
        this.f7925p = i10;
        this.f7926q = i11;
    }
}
