package com.arflix.tv.ui.screens.tv;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.SkipNextKt;
import androidx.compose.material.icons.filled.SkipPreviousKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import com.arflix.tv.R;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$TvScreenKt {
    public static final ComposableSingletons$TvScreenKt INSTANCE = new ComposableSingletons$TvScreenKt();
    private static r7.p<Composer, Integer, t0> lambda$2056658595 = ComposableLambdaKt.composableLambdaInstance(2056658595, false, new a(0));

    /* JADX INFO: renamed from: lambda$-1162273427, reason: not valid java name */
    private static r7.p<Composer, Integer, t0> f77lambda$1162273427 = ComposableLambdaKt.composableLambdaInstance(-1162273427, false, new a(1));
    private static r7.p<Composer, Integer, t0> lambda$821190870 = ComposableLambdaKt.composableLambdaInstance(821190870, false, new a(2));

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_2056658595$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2056658595, i10, -1, "com.arflix.tv.ui.screens.tv.ComposableSingletons$TvScreenKt.lambda$2056658595.<anonymous> (TvScreen.kt:1422)");
            }
            IconKt.m1610Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.back, composer, 0), SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(24)), Color.INSTANCE.m3509getWhite0d7_KjU(), composer, 3456, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_821190870$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(821190870, i10, -1, "com.arflix.tv.ui.screens.tv.ComposableSingletons$TvScreenKt.lambda$821190870.<anonymous> (TvScreen.kt:1476)");
            }
            IconKt.m1610Iconww6aTOc(SkipNextKt.getSkipNext(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.next, composer, 0), SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(24)), Color.INSTANCE.m3509getWhite0d7_KjU(), composer, 3456, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda__1162273427$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1162273427, i10, -1, "com.arflix.tv.ui.screens.tv.ComposableSingletons$TvScreenKt.lambda$-1162273427.<anonymous> (TvScreen.kt:1453)");
            }
            IconKt.m1610Iconww6aTOc(SkipPreviousKt.getSkipPrevious(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.back, composer, 0), SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(24)), Color.INSTANCE.m3509getWhite0d7_KjU(), composer, 3456, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-1162273427$app_playRelease, reason: not valid java name */
    public final r7.p<Composer, Integer, t0> m6330getLambda$1162273427$app_playRelease() {
        return f77lambda$1162273427;
    }

    public final r7.p<Composer, Integer, t0> getLambda$2056658595$app_playRelease() {
        return lambda$2056658595;
    }

    public final r7.p<Composer, Integer, t0> getLambda$821190870$app_playRelease() {
        return lambda$821190870;
    }
}
