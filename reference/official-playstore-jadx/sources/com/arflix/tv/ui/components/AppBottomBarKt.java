package com.arflix.tv.ui.components;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.BookmarkKt;
import androidx.compose.material.icons.filled.HomeKt;
import androidx.compose.material.icons.filled.LiveTvKt;
import androidx.compose.material.icons.filled.SearchKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusState;
import com.arflix.tv.R;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a7\u0010\u0007\u001a\u00020\u00032\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\"\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011²\u0006\u000e\u0010\u0010\u001a\u00020\u000f8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "currentRoute", "Lkotlin/Function1;", "Lx6/t0;", "onNavigate", "Landroidx/compose/ui/Modifier;", "modifier", "AppBottomBar", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", "Lcom/arflix/tv/ui/components/BottomBarItem;", "bottomBarItems", "Ljava/util/List;", "getBottomBarItems", "()Ljava/util/List;", "", "isFocused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AppBottomBarKt {
    private static final List<BottomBarItem> bottomBarItems;

    static {
        int i10 = R.string.home;
        Icons icons = Icons.INSTANCE;
        bottomBarItems = t7.a.E(new BottomBarItem(i10, HomeKt.getHome(icons.getDefault()), "home"), new BottomBarItem(R.string.search, SearchKt.getSearch(icons.getDefault()), "search"), new BottomBarItem(R.string.library_default, BookmarkKt.getBookmark(icons.getDefault()), "watchlist"), new BottomBarItem(R.string.topbar_tv, LiveTvKt.getLiveTv(icons.getDefault()), "tv"), new BottomBarItem(R.string.settings, SettingsKt.getSettings(icons.getDefault()), "settings"));
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:180:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppBottomBar(java.lang.String r74, r7.l<? super java.lang.String, x6.t0> r75, androidx.compose.ui.Modifier r76, androidx.compose.runtime.Composer r77, int r78, int r79) {
        /*
            Method dump skipped, instruction units count: 1522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.AppBottomBarKt.AppBottomBar(java.lang.String, r7.l, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean AppBottomBar$lambda$0$0$0$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AppBottomBar$lambda$0$0$0$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppBottomBar$lambda$0$0$0$3$0(MutableState mutableState, FocusState focusState) {
        AppBottomBar$lambda$0$0$0$2(mutableState, focusState.isFocused());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppBottomBar$lambda$0$0$0$5$0(r7.l lVar, BottomBarItem bottomBarItem) {
        lVar.invoke(bottomBarItem.getRoute());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AppBottomBar$lambda$1(String str, r7.l lVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        AppBottomBar(str, lVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final List<BottomBarItem> getBottomBarItems() {
        return bottomBarItems;
    }
}
