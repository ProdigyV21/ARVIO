package com.arflix.tv.ui.screens.search;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10255i;

    public /* synthetic */ x(int i10) {
        this.f10255i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10255i) {
            case 0:
                return SearchScreenKt.DiscoverFilterStrip$lambda$3$0$0(((Integer) obj).intValue(), (DiscoverQuickFilter) obj2);
            case 1:
                return ComposableSingletons$SearchScreenKt.lambda_1196201055$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 2:
                return SearchScreenKt.SearchScreen$lambda$0$0((MediaType) obj, ((Integer) obj2).intValue());
            default:
                return SearchScreenKt.RowsLayer$lambda$4$0$0$1$3$0$8$0$0(((Integer) obj).intValue(), (MediaItem) obj2);
        }
    }
}
