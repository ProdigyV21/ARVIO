package com.arflix.tv.ui.components;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$PremiumEffectsKt {
    public static final ComposableSingletons$PremiumEffectsKt INSTANCE = new ComposableSingletons$PremiumEffectsKt();

    /* JADX INFO: renamed from: lambda$-1685682046, reason: not valid java name */
    private static r7.q<BoxScope, Composer, Integer, x6.t0> f58lambda$1685682046 = ComposableLambdaKt.composableLambdaInstance(-1685682046, false, new v(0));

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__1685682046$lambda$0(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1685682046, i10, -1, "com.arflix.tv.ui.components.ComposableSingletons$PremiumEffectsKt.lambda$-1685682046.<anonymous> (PremiumEffects.kt:53)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-1685682046$app_playRelease, reason: not valid java name */
    public final r7.q<BoxScope, Composer, Integer, x6.t0> m6154getLambda$1685682046$app_playRelease() {
        return f58lambda$1685682046;
    }
}
