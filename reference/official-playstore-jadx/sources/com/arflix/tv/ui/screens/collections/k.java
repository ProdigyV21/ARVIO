package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.ui.screens.tv.live.CategorySidebarKt;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;
import com.arflix.tv.ui.screens.tv.live.Quality;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8639i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8640l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8641m;

    public /* synthetic */ k(Object obj, int i10, int i11) {
        this.f8639i = i11;
        this.f8641m = obj;
        this.f8640l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8639i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return CollectionDetailsScreenKt.CollectionBackdrop$lambda$3((CatalogConfig) this.f8641m, this.f8640l, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return CategorySidebarKt.CategoryContextMenu$lambda$1((List) this.f8641m, this.f8640l, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return MiniPlayerKt.QualityBadge$lambda$1((Quality) this.f8641m, this.f8640l, (Composer) obj, iIntValue3);
        }
    }
}
