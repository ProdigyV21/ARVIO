package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11433i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f11434l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f11435m;

    public /* synthetic */ g(r7.a aVar, MutableState mutableState, int i10) {
        this.f11433i = i10;
        this.f11434l = aVar;
        this.f11435m = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11433i) {
            case 0:
                return CategorySidebarKt.SidebarRow_cR7IE_o$lambda$12$0$0(this.f11434l, this.f11435m, (FocusState) obj);
            case 1:
                return ChannelRowKt.ChannelRow_ZSrRDQQ$lambda$12$0(this.f11434l, this.f11435m, (FocusState) obj);
            default:
                return ProgramCellKt.ProgramCell_dOl9XNk$lambda$12$0(this.f11434l, this.f11435m, (FocusState) obj);
        }
    }
}
