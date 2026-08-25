package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.crash.ComposableSingletons$CrashReportActivityKt;
import com.arflix.tv.ui.screens.player.ComposableSingletons$PlayerScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8432i;

    public /* synthetic */ v(int i10) {
        this.f8432i = i10;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8432i) {
            case 0:
                return ComposableSingletons$PremiumEffectsKt.lambda__1685682046$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return ComposableSingletons$CrashReportActivityKt.lambda__138077762$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 2:
                return ComposableSingletons$CrashReportActivityKt.lambda_694758435$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 3:
                return ComposableSingletons$PlayerScreenKt.lambda_1581803292$lambda$0((AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 4:
                return ComposableSingletons$PlayerScreenKt.lambda__619373845$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return ComposableSingletons$PlayerScreenKt.lambda__406282834$lambda$0((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }
}
