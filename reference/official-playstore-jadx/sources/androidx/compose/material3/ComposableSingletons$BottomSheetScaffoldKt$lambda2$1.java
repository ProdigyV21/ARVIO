package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.material3.ComposableSingletons$BottomSheetScaffoldKt$lambda-2$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/SnackbarHostState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ComposableSingletons$BottomSheetScaffoldKt$lambda2$1 extends r implements q<SnackbarHostState, Composer, Integer, t0> {
    public static final ComposableSingletons$BottomSheetScaffoldKt$lambda2$1 INSTANCE = new ComposableSingletons$BottomSheetScaffoldKt$lambda2$1();

    public ComposableSingletons$BottomSheetScaffoldKt$lambda2$1() {
        super(3);
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((SnackbarHostState) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(SnackbarHostState snackbarHostState, Composer composer, int i10) {
        if ((i10 & 6) == 0) {
            i10 |= composer.changed(snackbarHostState) ? 4 : 2;
        }
        if ((i10 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(923568898, i10, -1, "androidx.compose.material3.ComposableSingletons$BottomSheetScaffoldKt.lambda-2.<anonymous> (BottomSheetScaffold.kt:114)");
        }
        SnackbarHostKt.SnackbarHost(snackbarHostState, null, null, composer, i10 & 14, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
