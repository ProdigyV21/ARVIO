package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import com.arflix.tv.ui.components.LoadingIndicatorKt;
import com.arflix.tv.ui.theme.ColorKt;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$WatchlistScreenKt {
    public static final ComposableSingletons$WatchlistScreenKt INSTANCE = new ComposableSingletons$WatchlistScreenKt();
    private static p<Composer, Integer, t0> lambda$1087281494 = ComposableLambdaKt.composableLambdaInstance(1087281494, false, new n(2));
    private static q<LazyGridItemScope, Composer, Integer, t0> lambda$1983092834 = ComposableLambdaKt.composableLambdaInstance(1983092834, false, new a());

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_1087281494$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1087281494, i10, -1, "com.arflix.tv.ui.screens.watchlist.ComposableSingletons$WatchlistScreenKt.lambda$1087281494.<anonymous> (WatchlistScreen.kt:928)");
            }
            BoxKt.Box(com.arflix.tv.data.repository.g.l(2, SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(8)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null)), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_1983092834$lambda$0(LazyGridItemScope lazyGridItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1983092834, i10, -1, "com.arflix.tv.ui.screens.watchlist.ComposableSingletons$WatchlistScreenKt.lambda$1983092834.<anonymous> (WatchlistScreen.kt:1216)");
            }
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(16));
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(null, Dp.m5678constructorimpl(34), ColorKt.getPink(), 0.0f, composer, 432, 9);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    public final p<Composer, Integer, t0> getLambda$1087281494$app_playRelease() {
        return lambda$1087281494;
    }

    public final q<LazyGridItemScope, Composer, Integer, t0> getLambda$1983092834$app_playRelease() {
        return lambda$1983092834;
    }
}
