package com.arflix.tv.ui.screens.plugin;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.components.l1;
import com.arflix.tv.ui.theme.ColorKt;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$PluginScreenKt {
    public static final ComposableSingletons$PluginScreenKt INSTANCE = new ComposableSingletons$PluginScreenKt();
    private static p<Composer, Integer, t0> lambda$891478482 = ComposableLambdaKt.composableLambdaInstance(891478482, false, new l1(3));

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 lambda_891478482$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(891478482, i10, -1, "com.arflix.tv.ui.screens.plugin.ComposableSingletons$PluginScreenKt.lambda$891478482.<anonymous> (PluginScreen.kt:524)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.plugin_screen_repo_url, composer, 0), null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    public final p<Composer, Integer, t0> getLambda$891478482$app_playRelease() {
        return lambda$891478482;
    }
}
