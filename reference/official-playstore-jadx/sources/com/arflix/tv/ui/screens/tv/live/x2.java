package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import com.arflix.tv.data.model.IptvChannel;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x2 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11911i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11912l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11913m;

    public /* synthetic */ x2(EnrichedChannel enrichedChannel, String str) {
        this.f11911i = 5;
        this.f11913m = enrichedChannel;
        this.f11912l = str;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11911i) {
            case 0:
                return SearchOverlayKt$SearchResultRow$3$1.invokeSuspend$lambda$0((r7.l) this.f11912l, (EnrichedChannel) this.f11913m, (Offset) obj);
            case 1:
                return CategorySidebarKt.SearchEntry$lambda$6$0((r7.l) this.f11912l, (MutableState) this.f11913m, (FocusState) obj);
            case 2:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$14((String) this.f11912l, (String) this.f11913m, (IptvChannel) obj));
            case 3:
                return LiveTvEnhancementsKt.ProviderSelector$lambda$6$0$2$0((TvProviderFilter) this.f11912l, (MutableState) this.f11913m, (FocusState) obj);
            case 4:
                return Boolean.valueOf(LiveTvEnhancementsKt.providerMatcher$lambda$3((HashSet) this.f11912l, (String) this.f11913m, (EnrichedChannel) obj));
            default:
                return Boolean.valueOf(LiveTvScreenKt.catchupPlaybackVariant$lambda$0((EnrichedChannel) this.f11913m, (String) this.f11912l, (EnrichedChannel) obj));
        }
    }

    public /* synthetic */ x2(Object obj, Object obj2, int i10) {
        this.f11911i = i10;
        this.f11912l = obj;
        this.f11913m = obj2;
    }
}
