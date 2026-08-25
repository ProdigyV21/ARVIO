package com.arflix.tv.ui.components;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i2 implements r7.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8088i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SkeletonCardType f8089l;

    public /* synthetic */ i2(SkeletonCardType skeletonCardType, int i10) {
        this.f8088i = i10;
        this.f8089l = skeletonCardType;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f8088i) {
            case 0:
                int iIntValue = ((Integer) obj4).intValue();
                return SkeletonLoaderKt.SkeletonCategoryRow$lambda$0$0$0$0(this.f8089l, (LazyItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj4).intValue();
                return SkeletonLoaderKt.SkeletonCategoryRow$lambda$0$1$0$0(this.f8089l, (androidx.tv.foundation.lazy.list.c0) obj, ((Integer) obj2).intValue(), (Composer) obj3, iIntValue2);
        }
    }
}
