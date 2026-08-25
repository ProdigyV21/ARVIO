package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11234i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11235l;

    public /* synthetic */ g0(Object obj, int i10) {
        this.f11234i = i10;
        this.f11235l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11234i) {
            case 0:
                return Boolean.valueOf(((LinkedHashSet) this.f11235l).contains((String) obj));
            case 1:
                return TvScreenKt.TvScreen$lambda$118$2$0$8$0((State) this.f11235l, (GraphicsLayerScope) obj);
            default:
                return Boolean.valueOf(TvViewModel.currentVisiblePlaylistGroups$lambda$1((String) this.f11235l, (x6.j0) obj));
        }
    }
}
