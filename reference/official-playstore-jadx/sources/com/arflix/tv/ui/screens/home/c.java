package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.saveable.SaverScope;
import com.arflix.tv.data.model.Category;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9088i;

    public /* synthetic */ c(int i10) {
        this.f9088i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9088i) {
            case 0:
                return HomeFocusState.Saver$lambda$0((SaverScope) obj, (HomeFocusState) obj2);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$4$0((String) obj, (String) obj2);
            case 2:
                return HomeScreenKt.MobileHomeRowsLayer_uS7v9H0$lambda$4$0((String) obj, ((Integer) obj2).intValue());
            case 3:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$5$0((String) obj, ((Integer) obj2).intValue());
            case 4:
                return HomeScreenKt.MobileHomeRowsLayer_uS7v9H0$lambda$5$0$1(((Integer) obj).intValue(), (Category) obj2);
            case 5:
                return HomeScreenKt.HomeRowsLayer_HGFtVhc$lambda$7$0((String) obj, ((Integer) obj2).intValue());
            case 6:
                return HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$0(((Integer) obj).intValue(), (Category) obj2);
            default:
                return HomeScreenKt.HomeHeroLayer_WSk2ftU$lambda$1$0((String) obj, (String) obj2);
        }
    }
}
