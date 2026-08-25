package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaType;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements r7.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9073i;

    public /* synthetic */ b(int i10) {
        this.f9073i = i10;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f9073i) {
            case 0:
                return ComposableSingletons$HomeScreenKt.lambda__299145821$lambda$0((BoxScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, ((Integer) obj4).intValue());
            case 1:
                return HomeScreenKt.MobileHomeRowsLayer_uS7v9H0$lambda$3$0((MediaType) obj, ((Integer) obj2).intValue(), (Integer) obj3, (Integer) obj4);
            case 2:
                return HomeScreenKt.HomeRowsLayer_HGFtVhc$lambda$6$0((MediaType) obj, ((Integer) obj2).intValue(), (Integer) obj3, (Integer) obj4);
            case 3:
                return HomeScreenKt.HomeScreen$lambda$0$0((MediaType) obj, ((Integer) obj2).intValue(), (Integer) obj3, (Integer) obj4);
            default:
                return HomeScreenKt.HomeHeroLayer_WSk2ftU$lambda$0$0((MediaType) obj, ((Integer) obj2).intValue(), (Integer) obj3, (Integer) obj4);
        }
    }
}
