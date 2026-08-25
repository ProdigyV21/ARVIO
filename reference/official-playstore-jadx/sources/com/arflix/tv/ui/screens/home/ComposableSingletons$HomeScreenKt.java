package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$HomeScreenKt {
    public static final ComposableSingletons$HomeScreenKt INSTANCE = new ComposableSingletons$HomeScreenKt();

    /* JADX INFO: renamed from: lambda$-793339492, reason: not valid java name */
    private static r7.q<RowScope, Composer, Integer, t0> f67lambda$793339492 = ComposableLambdaKt.composableLambdaInstance(-793339492, false, new a());

    /* JADX INFO: renamed from: lambda$-299145821, reason: not valid java name */
    private static r7.r<BoxScope, Boolean, Composer, Integer, t0> f66lambda$299145821 = ComposableLambdaKt.composableLambdaInstance(-299145821, false, new b(0));

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda__299145821$lambda$0(BoxScope boxScope, boolean z, Composer composer, int i10) {
        if ((i10 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-299145821, i10, -1, "com.arflix.tv.ui.screens.home.ComposableSingletons$HomeScreenKt.lambda$-299145821.<anonymous> (HomeScreen.kt:3807)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda__793339492$lambda$0(RowScope rowScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-793339492, i10, -1, "com.arflix.tv.ui.screens.home.ComposableSingletons$HomeScreenKt.lambda$-793339492.<anonymous> (HomeScreen.kt:1277)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.retry, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-299145821$app_playRelease, reason: not valid java name */
    public final r7.r<BoxScope, Boolean, Composer, Integer, t0> m6236getLambda$299145821$app_playRelease() {
        return f66lambda$299145821;
    }

    /* JADX INFO: renamed from: getLambda$-793339492$app_playRelease, reason: not valid java name */
    public final r7.q<RowScope, Composer, Integer, t0> m6237getLambda$793339492$app_playRelease() {
        return f67lambda$793339492;
    }
}
