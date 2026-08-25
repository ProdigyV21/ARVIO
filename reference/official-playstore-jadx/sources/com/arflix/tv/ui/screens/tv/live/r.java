package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11733i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f11734l;

    public /* synthetic */ r(String str, int i10) {
        this.f11733i = i10;
        this.f11734l = str;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11733i) {
            case 0:
                return ChannelLogoKt.decodeLegacyLogoUrl$lambda$1(this.f11734l, ((Integer) obj).intValue());
            case 1:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$4(this.f11734l, (IptvChannel) obj));
            case 2:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$13(this.f11734l, (EnrichedChannel) obj));
            case 3:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$4(this.f11734l, (EnrichedChannel) obj));
            default:
                return Boolean.valueOf(LiveCategoryKt.rawCategoryMatcher$lambda$13(this.f11734l, (IptvChannel) obj));
        }
    }
}
