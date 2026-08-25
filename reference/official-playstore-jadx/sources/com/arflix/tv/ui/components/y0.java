package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8514i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8515l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8516m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x6.o f8517n;

    public /* synthetic */ y0(Object obj, Object obj2, x6.o oVar, int i10) {
        this.f8514i = i10;
        this.f8515l = obj;
        this.f8516m = obj2;
        this.f8517n = oVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8514i) {
            case 0:
                return MediaContextMenuKt.MediaContextMenu$lambda$8$3$2((String) this.f8515l, (List) this.f8516m, (r7.a) this.f8517n, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$20$2((SettingsUiState) this.f8515l, (r7.p) this.f8516m, (r7.p) this.f8517n, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }
}
