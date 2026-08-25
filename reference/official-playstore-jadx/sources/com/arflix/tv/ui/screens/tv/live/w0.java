package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11858i;

    public /* synthetic */ w0(int i10) {
        this.f11858i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11858i) {
            case 0:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$11((EnrichedChannel) obj));
            case 1:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$15((EnrichedChannel) obj));
            case 2:
                return Boolean.valueOf(((EnrichedChannel) obj).isAdult());
            case 3:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$5((EnrichedChannel) obj));
            case 4:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$6((EnrichedChannel) obj));
            case 5:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$7((EnrichedChannel) obj));
            case 6:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$8((EnrichedChannel) obj));
            case 7:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$9((EnrichedChannel) obj));
            case 8:
                return LiveCategoryKt.orderPlaylistGroups$lambda$0((String) obj);
            case 9:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$0((IptvChannel) obj));
            case 10:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$10((IptvChannel) obj));
            case 11:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$11((IptvChannel) obj));
            case 12:
                return Boolean.valueOf(LiveTvEnhancementsKt.providerMatcher$lambda$0((EnrichedChannel) obj));
            case 13:
                return Boolean.valueOf(LiveTvScreenKt.hasExplicitCatchupSource((EnrichedChannel) obj));
            case 14:
                return LiveTvScreenKt.LiveTvScreen$lambda$0$0(((Boolean) obj).booleanValue());
            case 15:
                return ((EnrichedChannel) obj).getId();
            case 16:
                return Integer.valueOf(QuickZapOverlayKt.QuickZapOverlay$lambda$16$0(((Integer) obj).intValue()));
            default:
                return Integer.valueOf(QuickZapOverlayKt.QuickZapOverlay$lambda$17$0(((Integer) obj).intValue()));
        }
    }
}
