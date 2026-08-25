package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11315i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11316l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11317m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11318n;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, int i10) {
        this.f11315i = i10;
        this.f11316l = obj;
        this.f11317m = obj2;
        this.f11318n = obj3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11315i) {
            case 0:
                return CategorySidebarKt.CategorySidebar$lambda$38$4$0((CategoryMenuState) this.f11316l, (List) this.f11317m, (MutableState) this.f11318n, ((Integer) obj).intValue());
            case 1:
                return SearchOverlayKt$SearchOverlay$3$1.AnonymousClass4.invokeSuspend$lambda$0((Map) this.f11316l, (String) this.f11317m, (GuideMatchLabels) this.f11318n, (EnrichedChannel) obj);
            default:
                return Boolean.valueOf(LiveCategoryKt.categoryMatcher$lambda$14((String) this.f11316l, (Genre) this.f11317m, (Quality) this.f11318n, (EnrichedChannel) obj));
        }
    }
}
