package androidx.activity.compose;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.activity.o0;
import androidx.activity.x;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import e.c;
import ga.r;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroidx/activity/compose/LocalFullyDrawnReporterOwner;", "", "Landroidx/activity/x;", "getCurrent", "(Landroidx/compose/runtime/Composer;I)Landroidx/activity/x;", "current", "activity-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LocalFullyDrawnReporterOwner {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LocalFullyDrawnReporterOwner f927a = new LocalFullyDrawnReporterOwner();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ProvidableCompositionLocal f928b = CompositionLocalKt.compositionLocalOf$default(null, c.f14937n, 1, null);

    public final x getCurrent(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(540186968, i10, -1, "androidx.activity.compose.LocalFullyDrawnReporterOwner.<get-current> (ReportDrawn.kt:95)");
        }
        x xVar = (x) composer.consume(f928b);
        if (xVar == null) {
            composer.startReplaceGroup(-1738308180);
            xVar = (x) r.K(r.Q(r.M((View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView()), o0.f978l), o0.f979m));
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1738310474);
            composer.endReplaceGroup();
        }
        if (xVar == null) {
            composer.startReplaceGroup(-1738306337);
            Object baseContext = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            while (true) {
                if (!(baseContext instanceof ContextWrapper)) {
                    baseContext = null;
                    break;
                }
                if (baseContext instanceof x) {
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            }
            xVar = (x) baseContext;
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1738310398);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return xVar;
    }
}
