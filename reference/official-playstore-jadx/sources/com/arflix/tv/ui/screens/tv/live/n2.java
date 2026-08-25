package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.ui.geometry.Offset;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n2 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11645i;

    public /* synthetic */ n2(int i10) {
        this.f11645i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11645i) {
            case 0:
                return ((EnrichedChannel) obj).getId();
            case 1:
                return Boolean.valueOf(LiveTvScreenKt$LiveTvScreen$26$1.invokeSuspend$lambda$1((String) obj));
            case 2:
                return Boolean.valueOf(LiveTvScreenKt$LiveTvScreen$49$14$1$1.invokeSuspend$lambda$0((IptvChannel) obj));
            case 3:
                return Boolean.valueOf(SearchOverlayKt$SearchOverlay$3$1.AnonymousClass4.invokeSuspend$lambda$1((x6.x) obj));
            case 4:
                return SearchOverlayKt$SearchOverlay$3$1.AnonymousClass4.invokeSuspend$lambda$3((x6.x) obj);
            case 5:
                return SearchOverlayKt$SearchOverlay$6$1$1.invokeSuspend$lambda$0((Offset) obj);
            case 6:
                return CategorySidebarKt.CategorySidebar$lambda$8$0(((Boolean) obj).booleanValue());
            case 7:
                return CategorySidebarKt.SearchEntry$lambda$2$0(((Boolean) obj).booleanValue());
            case 8:
                return EpgGridKt.EpgGrid$lambda$0$0((EnrichedChannel) obj);
            case 9:
                return Integer.valueOf(EpgGridKt.EpgGrid$lambda$1$0((EnrichedChannel) obj));
            case 10:
                return EpgGridKt.EpgGrid$lambda$2$0((EnrichedChannel) obj);
            case 11:
                return EpgGridKt.EpgGrid$lambda$4$0((EnrichedChannel) obj);
            case 12:
                return EpgGridKt.EpgGrid$lambda$5$0((EnrichedChannel) obj);
            case 13:
                return Integer.valueOf(FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$14$0(((Integer) obj).intValue()));
            case 14:
                return FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$6$1((IptvProgram) obj);
            case 15:
                return FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$8$2((IptvProgram) obj);
            case 16:
                return FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$8$4((IptvProgram) obj);
            case 17:
                return Integer.valueOf(FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$11$0(((Integer) obj).intValue()));
            case 18:
                return Integer.valueOf(FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$12$0(((Integer) obj).intValue()));
            case 19:
                return Integer.valueOf(FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$13$0(((Integer) obj).intValue()));
            case 20:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$15((IptvChannel) obj));
            case 21:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$3((IptvChannel) obj));
            case 22:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$5((IptvChannel) obj));
            case 23:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$6((IptvChannel) obj));
            case 24:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$7((IptvChannel) obj));
            case 25:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$8((IptvChannel) obj));
            case 26:
                return ((IptvChannel) obj).getId();
            case 27:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$9((IptvChannel) obj));
            case 28:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$0((EnrichedChannel) obj));
            default:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$10((EnrichedChannel) obj));
        }
    }
}
