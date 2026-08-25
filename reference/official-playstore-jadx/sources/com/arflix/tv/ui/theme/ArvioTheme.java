package com.arflix.tv.ui.theme;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/theme/ArvioTheme;", "", "<init>", "()V", "colors", "Lcom/arflix/tv/ui/theme/ArvioColors;", "getColors", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/theme/ArvioColors;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArvioTheme {
    public static final int $stable = 0;
    public static final ArvioTheme INSTANCE = new ArvioTheme();

    private ArvioTheme() {
    }

    public final ArvioColors getColors(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1792096463, i10, -1, "com.arflix.tv.ui.theme.ArvioTheme.<get-colors> (Theme.kt:155)");
        }
        ArvioColors arvioColors = (ArvioColors) composer.consume(ThemeKt.getLocalArvioColors());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return arvioColors;
    }
}
