package com.arflix.tv.ui.screens.home;

import androidx.compose.ui.draw.CacheDrawScope;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.home.HomeViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9103i;

    public /* synthetic */ d(int i10) {
        this.f9103i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9103i) {
            case 0:
                return HomeFocusState.Saver$lambda$1((List) obj);
            case 1:
                return HomeViewModel.C13641.invokeSuspend$lambda$13((Category) obj);
            case 2:
                return HomeViewModel.C13811.invokeSuspend$lambda$1$0((Category) obj);
            case 3:
                return HomeScreenKt.HomeHeroLayer_WSk2ftU$lambda$3$0((MediaItem) obj);
            case 4:
                return HomeScreenKt.MobileHomeRowsLayer_uS7v9H0$lambda$2$0((String) obj);
            case 5:
                return HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$0$0((String) obj);
            case 6:
                return HomeScreenKt.HomeHeroLayer_WSk2ftU$lambda$4$0(((Integer) obj).intValue());
            case 7:
                return HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$1$0((MediaItem) obj);
            case 8:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$3$0((String) obj);
            case 9:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$4$0((MediaItem) obj);
            case 10:
                return Boolean.valueOf(HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$6$0((MediaItem) obj));
            case 11:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$7$0((MediaItem) obj);
            case 12:
                return HomeScreenKt.HomeRowsLayer_HGFtVhc$lambda$0$0((String) obj);
            case 13:
                return HomeScreenKt.HomeRowsLayer_HGFtVhc$lambda$1$0((MediaItem) obj);
            case 14:
                return HomeScreenKt.HomeScreen$lambda$77$6$4$0((CacheDrawScope) obj);
            case 15:
                return HomeScreenKt.HomeScreen$lambda$1$0((String) obj);
            case 16:
                return HomeScreenKt.HomeScreen$lambda$77$8$4$0((MediaItem) obj);
            case 17:
                return Boolean.valueOf(HomeScreenKt.HomeHeroLayer_WSk2ftU$lambda$2$0((MediaItem) obj));
            case 18:
                return HomeViewModel.chooseInitialHero$lambda$2((Category) obj);
            case 19:
                return Boolean.valueOf(HomeViewModel.scheduleStartupCatalogImageWarmup$lambda$0((Category) obj));
            default:
                return Boolean.valueOf(HomeViewModelKt.compactHomeCategoriesForCache$lambda$0$0((MediaItem) obj));
        }
    }
}
