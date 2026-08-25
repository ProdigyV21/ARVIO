package com.arflix.tv.ui.screens.crash;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import com.arflix.tv.ui.components.v;
import kotlin.Metadata;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$CrashReportActivityKt {
    public static final ComposableSingletons$CrashReportActivityKt INSTANCE = new ComposableSingletons$CrashReportActivityKt();

    /* JADX INFO: renamed from: lambda$-138077762, reason: not valid java name */
    private static q<RowScope, Composer, Integer, t0> f60lambda$138077762 = ComposableLambdaKt.composableLambdaInstance(-138077762, false, new v(1));
    private static q<RowScope, Composer, Integer, t0> lambda$694758435 = ComposableLambdaKt.composableLambdaInstance(694758435, false, new v(2));

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_694758435$lambda$0(RowScope rowScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(694758435, i10, -1, "com.arflix.tv.ui.screens.crash.ComposableSingletons$CrashReportActivityKt.lambda$694758435.<anonymous> (CrashReportActivity.kt:257)");
            }
            TextKt.m2153Text4IGK_g("Restart ARVIO", (Modifier) null, Color.INSTANCE.m3498getBlack0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 196998, 0, 131034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda__138077762$lambda$0(RowScope rowScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-138077762, i10, -1, "com.arflix.tv.ui.screens.crash.ComposableSingletons$CrashReportActivityKt.lambda$-138077762.<anonymous> (CrashReportActivity.kt:235)");
            }
            TextKt.m2153Text4IGK_g("Report on Discord", (Modifier) null, Color.INSTANCE.m3509getWhite0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 196998, 0, 131034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-138077762$app_playRelease, reason: not valid java name */
    public final q<RowScope, Composer, Integer, t0> m6204getLambda$138077762$app_playRelease() {
        return f60lambda$138077762;
    }

    public final q<RowScope, Composer, Integer, t0> getLambda$694758435$app_playRelease() {
        return lambda$694758435;
    }
}
