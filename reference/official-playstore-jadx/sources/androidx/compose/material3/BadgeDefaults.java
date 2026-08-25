package androidx.compose.material3;

import androidx.compose.material3.tokens.BadgeTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\u00020\u00048Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0007"}, d2 = {"Landroidx/compose/material3/BadgeDefaults;", "", "()V", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BadgeDefaults {
    public static final int $stable = 0;
    public static final BadgeDefaults INSTANCE = new BadgeDefaults();

    private BadgeDefaults() {
    }

    public final long getContainerColor(Composer composer, int i10) {
        composer.startReplaceableGroup(-867931977);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-867931977, i10, -1, "androidx.compose.material3.BadgeDefaults.<get-containerColor> (Badge.kt:222)");
        }
        long value = ColorSchemeKt.getValue(BadgeTokens.INSTANCE.getColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return value;
    }
}
