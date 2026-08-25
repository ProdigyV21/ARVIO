package androidx.compose.material3;

import android.content.res.Configuration;
import android.os.Build;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002H\u0001¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"defaultLocale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "(Landroidx/compose/runtime/Composer;I)Ljava/util/Locale;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ActualAndroid_androidKt {
    public static final Locale defaultLocale(Composer composer, int i10) {
        Locale localeC;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(661006346, i10, -1, "androidx.compose.material3.defaultLocale (ActualAndroid.android.kt:32)");
        }
        if (Build.VERSION.SDK_INT >= 24) {
            composer.startReplaceableGroup(-273864580);
            localeC = Locale24.INSTANCE.defaultLocale(composer, 6);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-273864534);
            localeC = xc.d.m0((Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).c(0);
            if (localeC == null) {
                localeC = Locale.getDefault();
            }
            composer.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return localeC;
    }
}
