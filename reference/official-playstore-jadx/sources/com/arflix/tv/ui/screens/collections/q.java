package com.arflix.tv.ui.screens.collections;

import android.content.Context;
import androidx.tv.foundation.lazy.grid.p0;
import androidx.tv.foundation.lazy.grid.s0;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8673i;

    public /* synthetic */ q(int i10) {
        this.f8673i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8673i) {
            case 0:
                return Integer.valueOf(CollectionDetailsScreenKt$CollectionItemsGrid$1$1.invokeSuspend$lambda$0$0((p0) obj));
            case 1:
                return Boolean.valueOf(CollectionDetailsScreenKt$CollectionItemsGrid$1$1.invokeSuspend$lambda$0$1(((Integer) obj).intValue()));
            case 2:
                return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0$0((s0) obj);
            case 3:
                return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0$2((s0) obj);
            case 4:
                return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0$5((s0) obj);
            case 5:
                return CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$5$0$10((s0) obj);
            default:
                return VideoHeroKt.VideoHero$lambda$4$0((Context) obj);
        }
    }
}
