package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R,\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u00048G¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/compose/material3/SwipeToDismissBoxDefaults;", "", "<init>", "()V", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "totalDistance", "getPositionalThreshold", "(Landroidx/compose/runtime/Composer;I)Lr7/l;", "positionalThreshold", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwipeToDismissBoxDefaults {
    public static final int $stable = 0;
    public static final SwipeToDismissBoxDefaults INSTANCE = new SwipeToDismissBoxDefaults();

    private SwipeToDismissBoxDefaults() {
    }

    public final l<Float, Float> getPositionalThreshold(Composer composer, int i10) {
        composer.startReplaceableGroup(1545861529);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1545861529, i10, -1, "androidx.compose.material3.SwipeToDismissBoxDefaults.<get-positionalThreshold> (SwipeToDismissBox.kt:325)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        composer.startReplaceableGroup(308181361);
        boolean zChanged = composer.changed(density);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SwipeToDismissBoxDefaults$positionalThreshold$1$1$1(density);
            composer.updateRememberedValue(objRememberedValue);
        }
        l<Float, Float> lVar = (l) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return lVar;
    }
}
