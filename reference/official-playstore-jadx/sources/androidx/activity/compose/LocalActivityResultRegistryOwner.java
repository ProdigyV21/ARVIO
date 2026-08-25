package androidx.activity.compose;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.activity.result.l;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import e.c;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroidx/activity/compose/LocalActivityResultRegistryOwner;", "", "Landroidx/activity/result/l;", "getCurrent", "(Landroidx/compose/runtime/Composer;I)Landroidx/activity/result/l;", "current", "activity-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LocalActivityResultRegistryOwner {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LocalActivityResultRegistryOwner f925a = new LocalActivityResultRegistryOwner();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ProvidableCompositionLocal f926b = CompositionLocalKt.compositionLocalOf$default(null, c.f14936m, 1, null);

    public final l getCurrent(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1418020823, i10, -1, "androidx.activity.compose.LocalActivityResultRegistryOwner.<get-current> (ActivityResultRegistry.kt:48)");
        }
        l lVar = (l) composer.consume(f926b);
        if (lVar == null) {
            composer.startReplaceGroup(1006590171);
            Object baseContext = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            while (true) {
                if (!(baseContext instanceof ContextWrapper)) {
                    baseContext = null;
                    break;
                }
                if (baseContext instanceof l) {
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            }
            lVar = (l) baseContext;
        } else {
            composer.startReplaceGroup(1006589303);
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return lVar;
    }
}
