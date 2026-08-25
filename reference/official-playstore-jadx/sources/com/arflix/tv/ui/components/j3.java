package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j3 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8116i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8117l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8118m;

    public /* synthetic */ j3(Object obj, Object obj2, int i10) {
        this.f8116i = i10;
        this.f8117l = obj;
        this.f8118m = obj2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f8116i) {
            case 0:
                return StreamSelectorKt.OledSourceSelectorTv$lambda$0$0$0$2$0$0$0$0$0$0((r7.l) this.f8117l, (SourcePresentation) this.f8118m);
            case 1:
                return AppBottomBarKt.AppBottomBar$lambda$0$0$0$5$0((r7.l) this.f8117l, (BottomBarItem) this.f8118m);
            case 2:
                return ContextMenuKt.ContextMenu$lambda$9$3$2$2$0$0$0((r7.l) this.f8117l, (ContextAction) this.f8118m);
            default:
                return ScreensaverKt.ScreensaverHost$lambda$7$0((MutableState) this.f8117l, (MutableState) this.f8118m);
        }
    }
}
