package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o2 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11668i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Set f11669l;

    public /* synthetic */ o2(Set set, int i10) {
        this.f11668i = i10;
        this.f11669l = set;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        boolean zInvokeSuspend$lambda$1;
        switch (this.f11668i) {
            case 0:
                zInvokeSuspend$lambda$1 = LiveTvScreenKt$LiveTvScreen$49$14$1$1.invokeSuspend$lambda$1(this.f11669l, (IptvChannel) obj);
                break;
            case 1:
                zInvokeSuspend$lambda$1 = LiveCategoryKt.buildCategoryTree$lambda$12(this.f11669l, (IptvChannel) obj);
                break;
            case 2:
                zInvokeSuspend$lambda$1 = LiveCategoryKt.rawCategoryMatcher$lambda$1(this.f11669l, (IptvChannel) obj);
                break;
            case 3:
                zInvokeSuspend$lambda$1 = LiveCategoryKt.rawCategoryMatcher$lambda$2(this.f11669l, (IptvChannel) obj);
                break;
            case 4:
                zInvokeSuspend$lambda$1 = LiveCategoryKt.categoryMatcher$lambda$1(this.f11669l, (EnrichedChannel) obj);
                break;
            case 5:
                zInvokeSuspend$lambda$1 = LiveCategoryKt.categoryMatcher$lambda$2(this.f11669l, (EnrichedChannel) obj);
                break;
            default:
                zInvokeSuspend$lambda$1 = LiveTvScreenKt.LiveTvScreen$lambda$114$1$0(this.f11669l, (EnrichedChannel) obj);
                break;
        }
        return Boolean.valueOf(zInvokeSuspend$lambda$1);
    }
}
