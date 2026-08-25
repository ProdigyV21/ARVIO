package androidx.activity.compose;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.activity.n0;
import androidx.activity.o0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import e.c;
import ga.r;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroidx/activity/compose/LocalOnBackPressedDispatcherOwner;", "", "Landroidx/activity/n0;", "getCurrent", "(Landroidx/compose/runtime/Composer;I)Landroidx/activity/n0;", "current", "activity-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LocalOnBackPressedDispatcherOwner {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LocalOnBackPressedDispatcherOwner f929a = new LocalOnBackPressedDispatcherOwner();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ProvidableCompositionLocal f930b = CompositionLocalKt.compositionLocalOf$default(null, c.f14938o, 1, null);

    public final n0 getCurrent(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2068013981, i10, -1, "androidx.activity.compose.LocalOnBackPressedDispatcherOwner.<get-current> (BackHandler.kt:50)");
        }
        n0 n0Var = (n0) composer.consume(f930b);
        if (n0Var == null) {
            composer.startReplaceGroup(544166745);
            n0Var = (n0) r.K(r.Q(r.M((View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView()), o0.f980n), o0.f981o));
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(544164296);
            composer.endReplaceGroup();
        }
        if (n0Var == null) {
            composer.startReplaceGroup(544168748);
            Object baseContext = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            while (true) {
                if (!(baseContext instanceof ContextWrapper)) {
                    baseContext = null;
                    break;
                }
                if (baseContext instanceof n0) {
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            }
            n0Var = (n0) baseContext;
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(544164377);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return n0Var;
    }
}
