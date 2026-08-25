package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11577i;

    public /* synthetic */ l0(int i10) {
        this.f11577i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f11577i) {
            case 0:
                return FullscreenGuideOverlayKt.FullscreenGuideContent$lambda$0$3$0$1(((Integer) obj).intValue(), (GuideProgramItem) obj2);
            case 1:
                return LiveTvScreenKt$LiveTvScreen$49$14$1$1.invokeSuspend$lambda$2(((Integer) obj).intValue(), (IptvChannel) obj2);
            case 2:
                return SearchOverlayKt.SearchOverlay$lambda$16$1$1$0$0(((Integer) obj).intValue(), (SearchResult) obj2);
            case 3:
                return TouchCategoryRailKt.TouchCategoryRail$lambda$0$0$1(((Integer) obj).intValue(), (TouchCategoryRailItem) obj2);
            case 4:
                return CategorySidebarKt.CategorySidebar$lambda$0$0((String) obj, (String) obj2);
            case 5:
                return CategorySidebarKt.CategorySidebar$lambda$1$0((String) obj, (String) obj2);
            case 6:
                return CategorySidebarKt.CategorySidebar$lambda$2$0((String) obj, (String) obj2);
            case 7:
                return CategorySidebarKt.CategorySidebar$lambda$3$0((String) obj, (String) obj2);
            case 8:
                return CategorySidebarKt.CategorySidebar$lambda$4$0((String) obj, (String) obj2);
            case 9:
                return CategorySidebarKt.CategorySidebar$lambda$38$2$0$0(((Integer) obj).intValue(), (LiveCategory) obj2);
            case 10:
                return CategorySidebarKt.CategorySidebar$lambda$38$2$0$3(((Integer) obj).intValue(), (LiveCategory) obj2);
            case 11:
                return CategorySidebarKt.CategorySidebar$lambda$38$2$0$6(((Integer) obj).intValue(), (LiveCategory) obj2);
            case 12:
                return CategorySidebarKt.CategorySidebar$lambda$38$2$0$9(((Integer) obj).intValue(), (LiveCategory) obj2);
            case 13:
                return CategorySidebarKt.CategorySidebar$lambda$38$2$0$12(((Integer) obj).intValue(), (LiveCategory) obj2);
            case 14:
                return EpgGridKt.EpgGrid$lambda$45$1$1$0$0$0(((Integer) obj).intValue(), (EnrichedChannel) obj2);
            case 15:
                return Integer.valueOf(LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$0((EnrichedChannel) obj, (EnrichedChannel) obj2));
            case 16:
                return Integer.valueOf(LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$2((EnrichedChannel) obj, (EnrichedChannel) obj2));
            default:
                return LiveTvEnhancementsKt.VariantPickerOverlay$lambda$5$0$1$0$0(((Integer) obj).intValue(), (EnrichedChannel) obj2);
        }
    }
}
