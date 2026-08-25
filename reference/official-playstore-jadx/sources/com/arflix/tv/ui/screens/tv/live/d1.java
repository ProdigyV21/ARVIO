package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11353i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11354l;

    public /* synthetic */ d1(Object obj, int i10) {
        this.f11353i = i10;
        this.f11354l = obj;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f11353i) {
            case 0:
                return LiveTvEnhancementsKt.PlaybackDiagnosticBanner$lambda$0((PlaybackDiagnostic) this.f11354l, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return SearchOverlayKt.SearchOverlay$lambda$16$1$0$4((MutableState) this.f11354l, (r7.p) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return TouchCategoryRailKt.TouchCategoryRail$lambda$0$0$0((r7.a) this.f11354l, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }
}
