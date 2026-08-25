package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11702i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f11703l;

    public /* synthetic */ q(int i10, r7.a aVar) {
        this.f11702i = i10;
        this.f11703l = aVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11702i) {
            case 0:
                return CategorySidebarKt$CategoryMenuItem$2$1.invokeSuspend$lambda$0(this.f11703l, (Offset) obj);
            case 1:
                return CategorySidebarKt$SearchEntry$7$1.invokeSuspend$lambda$0(this.f11703l, (Offset) obj);
            case 2:
                return CategorySidebarKt$SidebarRow$1$3$1.invokeSuspend$lambda$0(this.f11703l, (Offset) obj);
            case 3:
                return CategorySidebarKt$SidebarRow$1$3$1.invokeSuspend$lambda$1(this.f11703l, (Offset) obj);
            case 4:
                return ProgramCellKt$ProgramCell$9$1.invokeSuspend$lambda$0(this.f11703l, (Offset) obj);
            case 5:
                return SearchOverlayKt$SearchOverlay$5$1.invokeSuspend$lambda$0(this.f11703l, (Offset) obj);
            default:
                return CategorySidebarKt.CategorySidebar$lambda$36$0(this.f11703l, (FocusState) obj);
        }
    }
}
